package TP6.EJ4;

public class Ej4 {
    public static void main(String[] args) {
        Almacen unAlmacen = new Almacen();
        Productor unProductor = new Productor(unAlmacen);
        Consumidor unConsumidor = new Consumidor(unAlmacen);
        unConsumidor.start();
        unProductor.start();
    }
}
