package TP5.EJ1;

import java.util.concurrent.Semaphore;

public class Piscina {

    private Semaphore capacidad;

    public Piscina(int cantidad) {
        capacidad = new Semaphore(cantidad, true);
    }

    public void entrar() throws InterruptedException{
        capacidad.acquire(1);
        System.out.println(Thread.currentThread().getName()+ " entró a la piscina");
    }

    public void salir() {
        System.out.println(Thread.currentThread().getName()+ " sale de la piscina");
        capacidad.release(1);
    }
}
