package TP5.EJ5;

public class Pasajero extends Thread {
    private TrenTuristico tren;
    private int numero;

    public Pasajero(TrenTuristico tren, int numero) {
        super("Pasajero "+numero);
        this.tren = tren;
        this.numero = numero;
    }

    public void run() {
        try {
            tren.comprarTicket();
            try {
                tren.subirPasajero();
                tren.bajarAsiento();
            } catch (InterruptedException e) {
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
