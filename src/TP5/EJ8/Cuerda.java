package TP5.EJ8;

import java.util.concurrent.Semaphore;

public class Cuerda {
    private Semaphore permisosIzq = new Semaphore(5, true);
    private Semaphore permisosDerecha = new Semaphore(0, true);

    private int esperandoIzq = 0;
    private int esperandoDer = 0;
    private int cantEnCuerda = 0;
    private Semaphore mutex = new Semaphore(1);

    private Semaphore cruzar = new Semaphore(0);
    public Cuerda() {
    }

    public void subirCuerda(String lado) throws InterruptedException {
        if (lado.equals("Izq")) {
            mutex.acquire();
            esperandoIzq++;
            mutex.release();
            permisosIzq.acquire(1);
            Thread.sleep(1000);
            mutex.acquire();
            esperandoIzq--;
            cantEnCuerda++;
            System.out.println("SE SUBE UN BABUINO DE LA IZQUIERDA");
            if (cantEnCuerda < 5 && esperandoIzq == 0) {
                System.out.println("SE SUBE EL ULTIMO BABUINO DEL LADO IZQUIERDO");
                cruzar.release(cantEnCuerda);
            } else if (cantEnCuerda == 5) {
                cruzar.release(5);
            }
            mutex.release();
        } else {
            mutex.acquire();
            esperandoDer++;
            mutex.release();
            permisosDerecha.acquire(1);
            Thread.sleep(1000);
            mutex.acquire();
            esperandoDer--;
            cantEnCuerda++;
            System.out.println("SE SUBE UN BABUINO DE LA DERECHA");
            if (cantEnCuerda < 5 && esperandoDer == 0) {
                System.out.println("SE SUBE EL ULTIMO BABUINO");
                cruzar.release(cantEnCuerda);
            } else if (cantEnCuerda == 5) {
                cruzar.release(5);
            }
            mutex.release();
        }
    }

    public void cruzar() throws InterruptedException {
        cruzar.acquire(1);
    }

    public void salirCuerda(String lado) throws InterruptedException {
        mutex.acquire();
        cantEnCuerda--;
        System.out.println("BABUINO SALTA");
        if (cantEnCuerda == 0) {
            if (lado.equals("Izq")) {
                System.out.println("SE BAJO EL ULTIMO BABUINO IZQUIERDO");
                if (esperandoDer > 0) {
                    permisosDerecha.release(5);
                } else if (esperandoIzq > 0) {
                    permisosIzq.release(5);
                }
            } else {
                System.out.println("SE BAJO EL ULTIMO BABUINO DERECHO");
                if (esperandoIzq > 0) {
                    permisosDerecha.release(5);
                } else if (esperandoDer > 0) {
                    permisosDerecha.release(5);
                }
            }
        }
        mutex.release();
    }
}
