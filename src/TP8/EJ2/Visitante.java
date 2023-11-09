package TP8.EJ2;

public class Visitante extends Thread {

    private Observatorio observatorio;
    private boolean sillaRuedas;

    public Visitante(Observatorio elObs, boolean discapacitado, String nombre) {
        super(nombre);
        observatorio = elObs;
        sillaRuedas = discapacitado;
    }

    public void run() {
        try {
            entrarSala();
            Thread.sleep(1000);
            salirSala();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void entrarSala() {
        if (sillaRuedas) {
            observatorio.entrarSillaRueda();
        } else {
            observatorio.entrarVisitante();
        }
    }

    private void salirSala() {
        if (sillaRuedas) {
            observatorio.salirSillaRuedas();
        } else {
            observatorio.salirVisitante();
        }
    }
}
