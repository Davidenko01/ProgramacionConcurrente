package TP8.EJ2;

public class Mantenimiento extends Thread {
    private Observatorio observatorio;
    public Mantenimiento(Observatorio elObs, String nombre) {
        super(nombre);
        observatorio = elObs;
    }

    public void run() {
        try {
            observatorio.entrarMantenimiento();
            System.out.println("LIMPIANDO");
            Thread.sleep(2000);
            observatorio.salirMantenimiento();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
