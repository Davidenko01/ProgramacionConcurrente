package TP6.EJ6;

public class Productor extends Thread {
    private Almacen unAlmacen;

    public Productor(Almacen elAlmacen, String nombre) {
        super(nombre);
        unAlmacen = elAlmacen;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                unAlmacen.producir();
                Thread.sleep((int)Math.random() *100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
