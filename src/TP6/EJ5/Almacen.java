package TP6.EJ5;

public class Almacen {
    private int capacidad = 5;
    private int usados = 0;

    public Almacen() {
    }

    private boolean lleno() {
        return capacidad == usados;
    }
    private boolean vacio() {
        return usados == 0;
    }

    public synchronized void producir() throws InterruptedException {
        while (lleno()) {
            this.wait();
        }
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
