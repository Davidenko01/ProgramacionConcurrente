package TP8.EJ3;

public class Horno extends Thread {

    private char tipo;
    private int cantPasteles;
    private Mostrador mostrador;
    public Horno(char unTipo, Mostrador unMostrador, int cantAProducir) {
        //tipo de Pastel (A, B o C)
        tipo = unTipo;
        mostrador = unMostrador;
        //cantidad de pasteles que va a hornear
        cantPasteles = cantAProducir;
    }

    public void run() {
        while (cantPasteles > 0) {
            //produce un pastel y lo pone en el mostrador
            mostrador.ponerPastel(hornear());
            cantPasteles--;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Pastel hornear() {
        Pastel unPastel = null;
        switch (tipo) {
            case 'A': unPastel = new Pastel(15);
            case 'B': unPastel = new Pastel(10);
            case 'C': unPastel = new Pastel(5);
            default:
                System.out.println("NO EXISTE ESE TIPO DE PASTEL");break;
        }
        return unPastel;
    }
}
