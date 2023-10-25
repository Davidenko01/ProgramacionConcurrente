package TP5.EJ3;

import java.util.concurrent.Semaphore;

public class Comedor {
    private Semaphore comederos;
    private boolean noHayGatos = true;
    private boolean noHayPerros = true;
    private int cantPerrosEsperando = 0;

    private int gatosQueComieron = 0;

    private int cantGatosEsperando = 0;
    private int platosEnUso = 0;
    private int perrosQueComieron = 0;

    //SEMAFORO PARA EXCLUSION MUTUA
    private Semaphore mutex = new Semaphore(1);
    private Semaphore accesoPerro = new Semaphore(0);
    private Semaphore accesoGato = new Semaphore(0);

    private int limite;

    public Comedor(int losComederos, int unLimite) {
        comederos = new Semaphore(losComederos);
        limite = unLimite;
    }

    //COMEDOR VACIO REOTRNA TRUE SI NO HAY PERROS NI GATOS Y FALSE EN CASO CONTRARIO
    private boolean comedorVacio() {
        return noHayPerros && noHayGatos;
    }

    //ESTE METODO SE USA PARA CAMBIAR EL TURNO A LA OTRA ESPECIE CUANDO SE LLEGA AL LIMITE ESTABLECIDO
    private synchronized void cambiarTurno(String especie) {
        if (especie.equals("Perro")) {
            //SE LE BRINDA XX CANTIDAD DE ACCESOS DEFINIDOS EN EL LIMITE
            accesoPerro.release(limite);
            noHayPerros = false;
        } else {
            accesoGato.release(limite);
            noHayGatos = false;
        }
    }

    public void entrarPerro() throws InterruptedException {
        mutex.acquire();
        if (comedorVacio()) {
            //SI EL COMEDOR ESTA VACIO LE ASIGNA EL TURNO A LOS PERROS
            cambiarTurno("Perro");
        }
        //PASA A ZONA DE ESPERA
        cantPerrosEsperando++;
        mutex.release();
        //VAN A ADQUIRIR EL PERMISO LOS PRIMEROS X = LIMITE PERROS, EL RESTO QUEDA ESPERANDO
        accesoPerro.acquire();
        //ADQUIEREN UN COMEDERO
        comederos.acquire();
        mutex.acquire();
        //DICHO PERRO DEJA DE ESPERAR Y UTILIZA UN PLATO
        cantPerrosEsperando--;
        platosEnUso++;
        mutex.release();
    }


    //EXTACTAMENTE LO MISMO QUE LOS PERROS SOLO QUE CON LOS GATOS
    public void entrarGato() throws InterruptedException {
        mutex.acquire();
        if (comedorVacio()) {
            cambiarTurno("Gato");
        }
        cantGatosEsperando++;
        mutex.release();
        accesoGato.acquire();
        comederos.acquire(1);
        mutex.acquire();
        cantGatosEsperando--;
        platosEnUso++;
        mutex.release();
    }

    public void irsePerro() throws InterruptedException {
        mutex.acquire();
        //DICHO PERRO YA COMIÓ, POR LO TANTO SE SUMA AL CONTADOR
        perrosQueComieron++;
        //COMO DEJÓ DE UTILIZAR EL PLATO RESTA AL CONTADOR
        platosEnUso--;
        //LIBERA UN COMEDERO
        comederos.release(1);
        //SI EL PERRO FUE EL ULTIMO EN COMER, ES DECIR, NO HABIA OTRO PERRO USANDO ALGUN COMEDERO
        if (platosEnUso == 0) {
            //VERIFICAMOS SI SE CUMPLIÓ QUE HAYAN COMIDO TANTOS PERROS COMO EL LIMITE ESTABLECIDO
            if (perrosQueComieron%limite == 0) {
                //SI HAY GATOS ESPERANDO
                if (cantGatosEsperando > 0) {
                    //SE CAMBIA EL TURNO PARA QUE AHORA COMAN LOS GATOS
                    cambiarTurno("Gato");
                    noHayPerros = true;
                } else {
                    //SI NO HAY GATOS ESPERANDO, SE VERIFICA SI HAY MAS PERROS ESPERANDO
                    if (cantPerrosEsperando > 0) {
                        //SI HAY MAS PERROS SE LE CONCEDEN MAS PERMISOS PARA QUE PUEDAN COMER
                        accesoPerro.release(limite);
                    } else {
                        //SI NO HAY MAS PERROS O GATOS ESPERANDO TERMINA LA EJECUCION (TODOS COMIERON)
                        System.out.println("NO HAY PERROS NI GATOS ESPERANDO");
                        noHayPerros = true;
                        noHayGatos = true;
                    }
                }
            } else {
                //SI TODAVIA NO SE LLEGO AL LIMITE
                if (cantPerrosEsperando == 0) {
                    //SE VERIFICA QUE NO HAYA OTROS PERROS ESPERANDO
                    if (cantGatosEsperando > 0) {
                        //SI NO HAY PERROS ESPERANDO PERO HAY GATOS ESPERANDO
                        accesoPerro.acquire(limite-(perrosQueComieron%limite));
                        //LE QUITAMOS LOS PERMISOS SOBRANTES A LOS PERROS Y LE DAMOS EL TURNO A LOS GATOS
                        cambiarTurno("Gato");
                    } else {
                        //SI NO HAY PERROS O GATOS ESPERANDO, LE QUITAMOS LOS PERMISOS A LOS PERROS Y TERMINA
                        accesoPerro.acquire(limite-(perrosQueComieron%limite));
                        noHayGatos = true;
                        noHayPerros = true;
                        System.out.println("NO HAY PERROS NI GATOS ESPERANDO");
                    }
                }
            }
        }
        mutex.release();
    }


    //LO MISMO QUE CON LOS PERROS, SOLO QUE AL REVÉS
    public void irseGato() throws InterruptedException {
        mutex.acquire();
        gatosQueComieron++;
        platosEnUso--;
        comederos.release(1);
        if (platosEnUso == 0) {
            if (gatosQueComieron%limite == 0) {
                if (cantPerrosEsperando > 0) {
                    cambiarTurno("Perro");
                    noHayGatos = true;
                } else {
                    if (cantGatosEsperando > 0) {
                        accesoGato.release(limite);
                    } else {
                        System.out.println("NO HAY PERROS NI GATOS ESPERANDO");
                    }
                }
            } else {
                if (cantGatosEsperando == 0) {
                    if (cantPerrosEsperando > 0) {
                        accesoGato.acquire(limite-(gatosQueComieron%limite));
                        cambiarTurno("Perro");
                        noHayGatos = true;
                    } else {
                        accesoGato.acquire(limite-(gatosQueComieron%limite));
                        noHayGatos = true;
                        noHayPerros = true;
                    }
                }
            }
        }
        mutex.release();
    }
}




