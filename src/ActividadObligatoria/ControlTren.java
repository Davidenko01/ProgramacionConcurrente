package ActividadObligatoria;

public class ControlTren extends Thread {
    private Tren unTren;

    public ControlTren(Tren unTren) {
        this.unTren = unTren;
    }

    public void run() {
        while(true) {
            try {
                unTren.noHacerNada();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (unTren.tieneLugar()) {
                unTren.dejarSubirse();
            } else {
                System.out.println("TREN LLENO, COMIENZA EL RECORRIDO");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Termino el recorrido");
                try {
                    unTren.dejarBajarse();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
