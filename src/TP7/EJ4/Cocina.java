package TP7.EJ4;

import java.util.concurrent.Semaphore;

public class Cocina {

    private Semaphore carne;
    private Semaphore verdura;
    private Semaphore pasta;

    public Cocina(int cantCarne, int cantVerdura, int cantPasta) {
        verdura = new Semaphore(cantVerdura, true);
        pasta = new Semaphore(cantPasta, true);
        carne = new Semaphore(cantCarne, true);
    }

    public void cocinarVerdura() throws InterruptedException {
        verdura.acquire();
        System.out.println(Thread.currentThread().getName()+" agarra verdura");
    }

    public void cocinarCarne() throws InterruptedException {
        carne.acquire();
        System.out.println(Thread.currentThread().getName()+" agarra carne");
    }

    public void cocinarPasta() throws InterruptedException {
        pasta.acquire();
        System.out.println(Thread.currentThread().getName()+" agarra pasta");
    }

    public void reponerCarne() {
        System.out.println(Thread.currentThread().getName()+" repone carne");
        carne.release();

    }

    public void reponerVerdura() {
        System.out.println(Thread.currentThread().getName()+" repone verdura");
        verdura.release();
    }
    public void reponerPasta() {
        System.out.println(Thread.currentThread().getName()+" repone pasta");
        pasta.release();
    }

}
