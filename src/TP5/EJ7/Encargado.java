package TP5.EJ7;

public class Encargado extends Thread {

    private Mirador elMirador;

    public Encargado(Mirador unMirador) {
        elMirador = unMirador;
    }

    public void run() {
        while (true) {
            try {
                elMirador.controlar();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
