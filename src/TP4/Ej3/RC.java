package TP4.Ej3;

import java.util.concurrent.Semaphore;

public class RC {
    Semaphore sem1;
    Semaphore sem2;
    Semaphore sem3;

    public RC() {
        sem1 = new Semaphore(1);
        sem2 = new Semaphore(0);
        sem3 = new Semaphore(0);
    }
    public void realizarP1() throws InterruptedException{
        sem1.acquire();
        System.out.println("Proceso 1 ejecutandose: ");
        Thread.sleep(1000);
        System.out.println("Fin Ejecucion P1");
        sem3.release();
    }

    public void realizarP2() throws InterruptedException {
        sem2.acquire();
        System.out.println("Proceso 2 ejecutandose: ");
        Thread.sleep(1000);
        System.out.println("Fin Ejecucion P2");
        sem1.release();
    }
    public void realizarP3() throws InterruptedException {
        sem3.acquire();
        System.out.println("Proceso 3 ejecutandose: ");
        Thread.sleep(1000);
        System.out.println("Fin Ejecucion P3");
        sem2.release();
    }

    public void ejecutar(String nombre) throws InterruptedException{
        switch (nombre) {
            case "P1": realizarP1(); break;
            case "P2": realizarP2(); break;
            case "P3": realizarP3(); break;
            default: System.out.println("Error"); break;
        }
    }

}
