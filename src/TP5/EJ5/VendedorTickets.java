package TP5.EJ5;

import ActividadObligatoria.Tren;

public class VendedorTickets extends Thread {

    TrenTuristico elTren;
    public VendedorTickets(TrenTuristico unTren) {
        elTren = unTren;
    }

    public void run() {
        while (true) {
            try {
                elTren.venderTickets();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
