package TP6.EJ4;

public class Consumidor extends Thread {
    private Almacen unAlmacen;

    public Consumidor(Almacen elAlmacen) {
        super("Consumidor");
        unAlmacen = elAlmacen;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                unAlmacen.consumir();
                Thread.sleep((int)Math.random() *100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
