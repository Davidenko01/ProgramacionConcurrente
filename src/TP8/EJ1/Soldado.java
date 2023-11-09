package TP8.EJ1;

public class Soldado extends Thread {

    private Cuartel cuartel;

    private boolean quiereGaseosa;

    private boolean quierePostre;

    public Soldado(String nombre, Cuartel unCuartel, boolean postre, boolean gaseosa) {
        super(nombre);
        cuartel = unCuartel;
        quiereGaseosa = gaseosa;
        quierePostre = postre;
    }

    public void run() {
        try {
            cuartel.entrarCuartel();
            cuartel.irMostrador();
            Thread.sleep(1500);
            if (quiereGaseosa) {
                cuartel.destapar();
                Thread.sleep(1000);
                cuartel.dejarAbridor();
            }
            cuartel.dejarMostrador();
            if (quierePostre) {
                cuartel.irMostradorPostre();
                Thread.sleep(1500);
                cuartel.salirMostradorPostre();
            }
            System.out.println("COMIENDOOOO");
            Thread.sleep(10000);
            System.out.println("TERMINA DE COMER");
            cuartel.dejarCuartel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
