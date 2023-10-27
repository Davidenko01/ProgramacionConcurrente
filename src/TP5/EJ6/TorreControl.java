package TP5.EJ6;

public class TorreControl extends Thread {
    private Pista laPista;
    public TorreControl(Pista unaPista) {
        laPista = unaPista;
    }

    public void run() {
        while (true) {
            try {
                laPista.concederPermiso();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
