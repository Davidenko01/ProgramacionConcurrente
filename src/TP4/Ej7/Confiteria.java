package TP4.Ej7;

import java.sql.SQLOutput;
import java.util.concurrent.Semaphore;

public class Confiteria {

    private Semaphore mozo = new Semaphore(0);
    private Semaphore confi = new Semaphore(1, true);
    private Semaphore comer = new Semaphore(0);

    public Confiteria() {
    }

    public void llamarAtencionMozo() {
        mozo.release();
        System.out.println("HOLA QUIERO COMER");
    }

    public void hacerHobbie() throws InterruptedException {
        mozo.acquire();
    }

    public void entrarConfiteria() throws InterruptedException {
        confi.acquire();
        System.out.println("Entra cliente");
    }

    public void salirConfiteria() {
        confi.release();
        System.out.println("Chau! Muy rico todo");
    }

    public void avisarQuePuedeComer() {
        comer.release();
        System.out.println("Puede comer señor");
    }

    public void comer() throws InterruptedException {
        comer.acquire();
    }

}
