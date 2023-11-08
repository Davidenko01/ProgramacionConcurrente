package TP7.EJ1;

public class Persona extends Thread {
    private String tipo;
    private SalaMuseo sala;

    public Persona(String unTipo, String nombre, SalaMuseo unaSala) {
        super(nombre);
        tipo = unTipo;
        sala = unaSala;
    }

    public void run() {
        try {
            entrarSala(tipo);
            Thread.sleep(10000);
            sala.salirSala();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void entrarSala(String elTipo) {
        if (tipo.equals("Jubilado")) {
            sala.entrarSalaJubilado();
        } else {
            sala.entrarSala();
        }
    }

}
