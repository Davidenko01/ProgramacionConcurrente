package TP4;

import java.util.concurrent.Semaphore;

public class Taxi {
    private Semaphore taxista = new Semaphore(0);
    private Semaphore taxi = new Semaphore(1);
    private Semaphore salirTaxi = new Semaphore(0);

    public Taxi() {
    }

    public void despertarTaxista() {
        System.out.println("DESPERTATEEE!");
        taxista.release();
    }

    public void dormir() throws InterruptedException {
        System.out.println("ZzZzZz");
        taxista.acquire();
    }

    public void subirAlTaxi() throws InterruptedException {
        taxi.acquire();
    }

    public void bajarDelTaxi() {
        System.out.println("Chau, tenga wenas noches");
        taxi.release();
    }

    public void llegarADestino() {
        System.out.println("Llegamos kpo");
        salirTaxi.release();
    }

    public void abrirPuerta() throws InterruptedException{
        salirTaxi.acquire();
    }

}
