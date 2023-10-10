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
        System.out.println("SU BEBIDA ESTA LISTA SEÑOR, TOME");
        bebidaLista.release();
    }

    public void hacerHobbie() throws InterruptedException {
        System.out.println("MOZO WEVEANDO");
        mozo.acquire();
    }

    public void cocinar() throws InterruptedException {
        System.out.println("Cocinero WEVIANDO");
        cocinero.acquire();
    }

    public void entrarConfiteria() throws InterruptedException {
        confi.acquire();
        System.out.println("Entra cliente "+Thread.currentThread().getName());
    }

    public void salirConfiteria() {
        System.out.println("Chau! Muy rico todo");
        System.out.println(Thread.currentThread().getName()+" se va");
        confi.release();
    }

    public void entregarComida() {
        System.out.println("Su comida esta lista señor");
        comidaLista.release();
    }

    private void pedirAmbos() throws InterruptedException {
        System.out.println("HOLA QUIERO COMER Y BEBER ALGO");
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
