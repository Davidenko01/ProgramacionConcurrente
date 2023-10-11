package TP5.EJ3;

public class Perro extends Thread {

    private Comedor elComedor;

    public Perro(String nombre, Comedor unComedor) {
        super(nombre);
        elComedor = unComedor;
    }

    public void run() {
        try {
            elComedor.entrarCusco();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("PERRO COMIENDO");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("LOL");
        }
        System.out.println("TERMINO DE COMER");
        elComedor.irsePerro();
    }


}
