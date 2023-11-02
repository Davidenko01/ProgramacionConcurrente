package TP6.EJ3;

public class Estudiante extends Thread {

    private SalaDeEstudio laSala;

    public Estudiante(String elNombre, SalaDeEstudio unaSala) {
        super(elNombre);
        laSala = unaSala;
    }

    public void run() {
        try {
            laSala.entrar();
            Thread.sleep(5000);
            laSala.salir();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
