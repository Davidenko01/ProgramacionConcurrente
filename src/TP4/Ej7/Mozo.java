package TP4.Ej7;

public class Mozo extends Thread {
    private String nombre;
    private Confiteria laConfi;

    public Mozo(String nombre, Confiteria laConfi) {
        this.nombre = nombre;
        this.laConfi = laConfi;
    }

    public void run()  {
        while (true) {
            try {
                laConfi.hacerHobbie();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Atendiendo Cliente");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Pedido tomado");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Pedido listo");
            laConfi.avisarQuePuedeComer();

        }

    }
}
