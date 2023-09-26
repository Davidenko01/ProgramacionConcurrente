package TP4.Ej4;

import java.util.concurrent.Semaphore;

public class Impresora {
    private int ID;

    private char tipo;
    private boolean enUso;
    private Semaphore sem = new Semaphore(1, true);

    private Semaphore mutex = new Semaphore(1);

    public int getID() {
        return ID;
    }

    public Impresora(int id, char elTipo) {
        this.ID = id;
        this.enUso = false;
        this.tipo = elTipo;
    }

    public boolean estaEnUso() {
        return enUso;
    }

    public char getTipo() {
        return tipo;
    }

    public void usar() throws InterruptedException {
        System.out.println("Imprimiendo..."+ID);
        enUso = true;
    }

    public void liberar() {
        System.out.println("Terminó la impresión de la impresora "+ID);
        enUso = false;
        sem.release();

    }

    public boolean intentarImprimir() {
        return sem.tryAcquire();
    }
}
