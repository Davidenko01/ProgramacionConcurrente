package TP4.Ej7;

public class Empleado extends Thread {

    private String nombre;
    private Confiteria laConfi;

    public Empleado(String nombre, Confiteria unaConfi) {
        this.nombre = nombre;
        this.laConfi = unaConfi;
    }

    public void run() {
        try {
            laConfi.entrarConfiteria();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        laConfi.llamarAtencionMozo();

        try {
            laConfi.comer();
            System.out.println("ÑOM ÑOM ÑOM");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        laConfi.salirConfiteria();
    }
}
