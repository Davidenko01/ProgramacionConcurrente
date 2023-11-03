package TP6.EJ7;

public class Automovil extends Thread {
    private Ferry elFerry;

    public Automovil(String nombre, Ferry unFerry) {
        super(nombre);
        elFerry = unFerry;
    }

    public void run() {
        try {
            elFerry.subirAutomovil();
            elFerry.bajarseAutomovil();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
