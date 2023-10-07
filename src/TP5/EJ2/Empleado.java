package TP5.EJ2;

public class Empleado extends Thread {

    private Confiteria unaConfi;
    private char tipoPedido;
    public Empleado(String elNombre, Confiteria laConfi, char elPedido) {
        super(elNombre);
        this.unaConfi = laConfi;
        this.tipoPedido = elPedido;
    }

    public void run() {
        try {
            unaConfi.entrarConfiteria();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            unaConfi.realizarPedido(tipoPedido);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        unaConfi.salirConfiteria();
    }
}
