package TP6.EJ7;

public class Ferry {

    private int espacio;
    private int pasajerosEsperando;
    private int autosEsperando;
    private boolean enViaje = false;
    private boolean desembarcando = false;
    private int lugaresOcupados = 0;

    public Ferry(int espacioT, int cantPasEsperando, int cantAutosEsperando) {
        espacio = espacioT;
        pasajerosEsperando = cantPasEsperando;
        autosEsperando = cantAutosEsperando;
    }

    public boolean estaLleno() {
        return lugaresOcupados == espacio;
    }
    public synchronized void subirsePasajero() throws InterruptedException{
        while (estaLleno() || enViaje || desembarcando) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName()+ " subió al ferry");
        lugaresOcupados++;
        pasajerosEsperando--;
        this.notifyAll();
    }

    private boolean entra() {
        return ((lugaresOcupados + 4) <= espacio);
    }
    public synchronized void subirAutomovil() throws InterruptedException {
        while (estaLleno() || !entra() || enViaje || desembarcando) {
            this.wait();
        }
        System.out.println(Thread.currentThread().getName()+ " subió al ferry");
        lugaresOcupados += 4;
        autosEsperando--;
        this.notifyAll();
    }

    public synchronized void zarpar() throws InterruptedException {
        if (autosEsperando == 0 && pasajerosEsperando == 0) {
            System.out.println("NO HAY NADIE");
            this.wait();
        } else {
            while (!puedeZarpar() || desembarcando) {
                this.wait();
            }
            enViaje = true;
            System.out.println("El Ferry salió del puerto");
        }
    }

    public synchronized void desembarcar() throws InterruptedException {
        desembarcando = true;
        enViaje = false;
        System.out.println("El ferry llegó al puerto, permite desembarcar");
        this.notifyAll();
    }

    public synchronized void bajarsePasajero() throws  InterruptedException {
        while (enViaje || !desembarcando) {
            this.wait();
        }
        lugaresOcupados--;
        System.out.println(Thread.currentThread().getName()+ " bajó del ferry");
        if (lugaresOcupados == 0) {
            desembarcando = false;
            this.notifyAll();
        }
    }
    public synchronized void bajarseAutomovil() throws InterruptedException {
        while (enViaje || !desembarcando) {
            this.wait();
        }
        lugaresOcupados-= 4;
        System.out.println(Thread.currentThread().getName()+ " bajó del ferry");
        if (lugaresOcupados == 0) {
            desembarcando = false;
            this.notifyAll();
        }
    }

    private boolean puedeZarpar() {
        if (estaLleno()) {
            return true;
        } else if (pasajerosEsperando == 0) {
            if (autosEsperando > 0) {
                return !entra();
            } else {
                return true;
            }
        }
        return false;
    }
}
