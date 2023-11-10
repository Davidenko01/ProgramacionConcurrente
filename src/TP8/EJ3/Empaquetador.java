package TP8.EJ3;

public class Empaquetador extends Thread {

    private Mostrador mostrador;
    private ContenedorCaja caja;

    public Empaquetador(Mostrador unMostrador, ContenedorCaja unaCaja) {
        mostrador = unMostrador;
        caja = unaCaja;
    }

    public void run() {
        try {
            while (true) {
                //quita pastel del mostrador
                int peso = mostrador.quitarPastel();
                Thread.sleep(1500);
                //lo agrega a la caja
                caja.agregarPastel(peso);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
