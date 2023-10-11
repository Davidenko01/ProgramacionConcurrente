package TP5.EJ3;

public class Gato extends Thread {
    private Comedor elComedor;
    public Gato(String nombre, Comedor unComedor) {
        super(nombre);
        elComedor = unComedor;
    }

    public void run() {
        try {
            elComedor.entrarMichi();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("GATO COMIENDO");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("LOL");
        }
        System.out.println("TERMINO DE COMER");
        elComedor.irseGato();
    }
}
