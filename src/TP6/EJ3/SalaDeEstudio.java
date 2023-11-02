package TP6.EJ3;

import java.util.concurrent.Semaphore;

public class SalaDeEstudio {

    private Semaphore cantMesas;


    public SalaDeEstudio(int lasMesas) {
        cantMesas = new Semaphore(lasMesas, true);
    }


    public void entrar() throws InterruptedException {
        cantMesas.acquire();
        System.out.println(Thread.currentThread().getName()+ " entró a la sala");
    }

    public void salir() {
        System.out.println(Thread.currentThread().getName()+ " salió de la sala");
        cantMesas.release();
    }
}
