package TP8.EJ1;

import java.util.concurrent.Semaphore;

public class Cuartel {
    private Semaphore capacidad = new Semaphore(100, true);
    private Semaphore mostradores = new Semaphore(5, true);

    private Semaphore abridores = new Semaphore(10, true);

    private Semaphore mostradorPostre = new Semaphore(3, true);

    public Cuartel() {
    }

    public void irMostradorPostre() throws InterruptedException {
        mostradorPostre.acquire();
        System.out.println(Thread.currentThread().getName()+ " esta pidiendo postre.");
    }

    public void salirMostradorPostre() {
        System.out.println(Thread.currentThread().getName()+ " sale de mostrador postre.");
        mostradorPostre.release();
    }
    public void entrarCuartel() throws InterruptedException {
        capacidad.acquire();
        System.out.println(Thread.currentThread().getName()+ " entra al cuartel.");
    }

    public void irMostrador() throws InterruptedException {
        mostradores.acquire();
        System.out.println(Thread.currentThread().getName()+ " esta pidiendo comida.");
    }

    public void destapar() throws InterruptedException {
        abridores.acquire();
        System.out.println(Thread.currentThread().getName()+ " esta usando un abridor.");
    }

    public void dejarAbridor() {
        System.out.println(Thread.currentThread().getName()+ " deja abridor.");
        abridores.release();
    }

    public void dejarMostrador() {
        System.out.println(Thread.currentThread().getName()+ " sale de mostrador.");
        mostradores.release();
    }

    public void dejarCuartel() {
        System.out.println(Thread.currentThread().getName()+ " sale del cuartel.");
        capacidad.release();
    }
}
