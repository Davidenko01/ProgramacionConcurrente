package TP7.EJ2;

public class Programador extends Thread {
    private Oficina oficina;

    public Programador(String nombre, Oficina oficina) {
        super(nombre);
        this.oficina = oficina;
    }

    public void run() {
        try {
            oficina.agarrarLaptop();
            oficina.agarrarLibro();
            Thread.sleep(10000);
            oficina.dejarCosas();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
