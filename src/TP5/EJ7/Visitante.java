package TP5.EJ7;

public class Visitante extends Thread {
    private Mirador unMirador;
    public Visitante(String nombre, Mirador elMirador) {
        super(nombre);
        unMirador = elMirador;
    }

    public void run() {
        try {
            unMirador.entrarEscalera();
            unMirador.solicitarTirarse();
            Thread.sleep(10000);
            unMirador.salir();
        } catch(InterruptedException e){
                throw new RuntimeException(e);
        }
    }
}
