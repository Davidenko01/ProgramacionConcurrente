package TP5.EJ3;

public class Perro extends Thread {

    private Comedor elComedor;
    private String elNombre;

    public Perro(String nombre, Comedor unComedor) {
        super(nombre);
        elComedor = unComedor;
        elNombre = nombre;
    }

    public void run() {
        try {
            elComedor.entrarPerro();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(elNombre+" COMIENDO");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(elNombre+" TERMINO DE COMER");
        try {
            elComedor.irsePerro();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}
