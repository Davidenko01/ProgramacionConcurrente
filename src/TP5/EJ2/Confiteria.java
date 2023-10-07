package TP5.EJ2;

import java.util.concurrent.Semaphore;

public class Confiteria {
    private Semaphore mozo = new Semaphore(0, true);
    private Semaphore confi = new Semaphore(2, true);

    private Semaphore bebidaLista = new Semaphore(0);

    private Semaphore comidaLista = new Semaphore(0);
    private Semaphore cocinero = new Semaphore(0, true);

    public Confiteria() {
    }

    public void llamarAtencionMozo() {
        mozo.release();
        System.out.println("HOLA QUIERO BEBER ALGO");
    }

    public void llamarAtencionCocinero() {
        cocinero.release();
        System.out.println("HOLA QUIERO COMER ALGO");
    }

    public void entregarBebida() {
        bebidaLista.release();
    }

    public void hacerHobbie() throws InterruptedException {
        mozo.acquire();
    }

    public void cocinar() throws InterruptedException {
        cocinero.acquire();
    }

    public void entrarConfiteria() throws InterruptedException {
        confi.acquire();
        System.out.println("Entra cliente");
    }

    public void salirConfiteria() {
        confi.release();
        System.out.println("Chau! Muy rico todo");
    }

    public void entregarComida() {
        comidaLista.release();
    }

    private void pedirAmbos() throws InterruptedException {
        pedirBebida();
        llamarAtencionCocinero();
        comidaLista.acquire();
    }

    private void pedirBebida() throws InterruptedException {
        llamarAtencionMozo();
        bebidaLista.acquire();
    }

    private void pedirComida() throws InterruptedException {
        llamarAtencionCocinero();
        comidaLista.acquire();
    }

    public void realizarPedido(char tipoPedido) throws InterruptedException {
        switch(tipoPedido) {
            case 'C': pedirComida(); break;
            case 'B': pedirBebida(); break;
            case 'A': pedirAmbos(); break;
            default:
                System.out.println("NOSE FLACO"); break;
        }
    }
}
