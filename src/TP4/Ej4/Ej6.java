package TP4;

public class Ej6 {
    public static void main(String[] args) {
        Taxi unTaxi = new Taxi();
        Cliente yo = new Cliente("Davor", unTaxi);
        Taxista unGordo = new Taxista("Pepe", unTaxi);
        unGordo.start();
        yo.start();

    }
}
