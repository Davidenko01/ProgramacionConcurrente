package TP6.EJ1.IncisoB;

public class Auto extends Thread {
    private char direccion;
    private Puente puente;
    public Auto(String nombre, char laDireccion, Puente elPuente) {
        super(nombre);
        direccion = laDireccion;
        puente = elPuente;
    }

    public void run() {
        try {
            puente.entrar(direccion);
            Thread.sleep(10000);
            puente.salir(direccion);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
