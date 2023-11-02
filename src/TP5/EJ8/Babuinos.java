package TP5.EJ8;

public class Babuinos extends Thread {
    private String lado;
    private Cuerda laCuerda;

    public Babuinos(Cuerda unaCuerda, String elLado) {
        lado = elLado;
        laCuerda = unaCuerda;
    }

    public void run() {
        try {
            laCuerda.subirCuerda(lado);
            laCuerda.cruzar();
            Thread.sleep(1000);
            laCuerda.salirCuerda(lado);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
