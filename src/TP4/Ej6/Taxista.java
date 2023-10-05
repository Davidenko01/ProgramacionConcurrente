package TP4;

public class Taxista extends Thread {

    private String nombre;
    private Taxi unTaxi;

    public Taxista(String nombre, Taxi unTaxi) {
        this.nombre = nombre;
        this.unTaxi = unTaxi;
    }

    public void run() {
        while(true) {
            try {
                unTaxi.dormir();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Subi pibe dale");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            unTaxi.llegarADestino();
        }
    }
}
