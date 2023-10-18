package TP5.EJ5;

public class Ej5 {
    public static void main(String[] args) {
        TrenTuristico tren = new TrenTuristico(5, 20);
        ControlTren inspector = new ControlTren(tren);
        Pasajero[] pasajeros = new Pasajero[21];
        VendedorTickets unVendedor = new VendedorTickets(tren);
        for(int i = 0; i < pasajeros.length; i++) {
            pasajeros[i] = new Pasajero(tren, (i + 1));
        }


        for(int i = 0; i < pasajeros.length; i++) {
            pasajeros[i].start();
        }
        inspector.start();
        unVendedor.start();
    }
}
