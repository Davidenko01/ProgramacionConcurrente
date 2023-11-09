package TP8.EJ2;

public class Investigador extends Thread {
    private Observatorio observatorio;
    public Investigador(Observatorio elObs, String nombre) {
        super(nombre);
        observatorio = elObs;
    }

    public void run() {
        try {
            observatorio.entrarInvestigador();
            System.out.println("TOMA NOTITAS");
            Thread.sleep(1500);
            observatorio.salirInvestigador();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
