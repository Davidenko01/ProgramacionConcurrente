package TP4.Ej4;

public class Cliente extends Thread {
    private GestorImpresoras GI;
    private String name;
    private char tipo;

    public Cliente (GestorImpresoras gi, String nombre, char tipo) {
        super(nombre);
        this.GI = gi;
        this.tipo = tipo;
    }

    public void run() {
        try {
            GI.buscarImpresora(tipo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
