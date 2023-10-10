package TP5.EJ2;


public class Mozo extends Thread {
    private String nombre;
    private Confiteria laConfi;

    public Mozo(String nombre, Confiteria laConfi) {
        this.nombre = nombre;
        this.laConfi = laConfi;
    }

    public void run() {
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
            System.out.println("Pedido bebida tomado");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Pedido bebida listo");
            laConfi.entregarBebida();
        }

    }
}
