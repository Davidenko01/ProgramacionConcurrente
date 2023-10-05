package TP4;

import java.sql.SQLOutput;

public class Cliente extends Thread {
    private String nombre;
    private Taxi unTaxi;

    public Cliente (String unNombre, Taxi elTaxi) {
        this.nombre = unNombre;
        this.unTaxi = elTaxi;
    }

    public void run() {
        while (true) {
            System.out.println("Oh, un taxi para que me lleve!");
            unTaxi.despertarTaxista();
            try {
                unTaxi.subirAlTaxi();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try {
                unTaxi.abrirPuerta();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            unTaxi.bajarDelTaxi();
        }
    }
}
