package TP8.EJ3;

public class BrazoRobotico extends Thread {

    private ContenedorCaja caja;

    public BrazoRobotico(ContenedorCaja laCaja) {
        caja = laCaja;
    }

    public void run() {
        try {
            //quita caja
            caja.quitarCaja();
            Thread.sleep(1500);
            //repone caja
            caja.reponerCaja();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
