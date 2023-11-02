package TP6.EJ4;

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
        if (lleno()) {
            this.wait();
        }
        usados++;
        System.out.println("Productor colocó producto en el almacen, cantidad ACTUAL = "+usados);
        this.notify();
    }

    public synchronized void consumir() throws InterruptedException {
        if (vacio()) {
            this.wait();
        }
        usados--;
        System.out.println("Consumidor consumió un producto del almacen, canidad ACTUAL = "+usados);
        this.notify();
    }
}
