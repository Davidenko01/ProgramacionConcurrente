package TP6.EJ7;

public class Pasajero extends Thread {
    private Ferry elFerry;

    public Pasajero(String nombre, Ferry unFerry) {
        super(nombre);
        elFerry = unFerry;
    }

    public void run() {
        try {
            elFerry.subirsePasajero();
            elFerry.bajarsePasajero();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
