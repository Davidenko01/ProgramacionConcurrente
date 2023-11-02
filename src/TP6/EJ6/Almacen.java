package TP6.EJ6;

public class Almacen {
    private int usados = 0;

    public Almacen() {
    }

    private boolean vacio() {
        return usados == 0;
    }

    public synchronized void producir() throws InterruptedException {
        usados++;
        System.out.println(Thread.currentThread().getName()+" colocó producto en el almacen, cantidad ACTUAL = "+usados);
        this.notifyAll();
    }

    public synchronized void consumir() throws InterruptedException {
        while (vacio()) {
            this.wait();
        }
        usados--;
        System.out.println(Thread.currentThread().getName()+" consumió un producto del almacen, canidad ACTUAL = "+usados);
        this.notifyAll();
    }
}
