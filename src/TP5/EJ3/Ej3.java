package TP5.EJ3;

public class Ej3 {
    public static void main(String[] args) {
        Comedor unComedor = new Comedor();
        Gato[] losGatos = new Gato[50];
        Perro[] losPerros = new Perro[50];
        for (int i = 0; i < 50; i++) {
            losGatos[i] = new Gato("GATO "+(i+1), unComedor);
            losPerros[i] = new Perro("PERRO "+(i+1), unComedor);
        }

        for (int j = 0; j < 50; j++) {
            losGatos[j].start();
            losPerros[j].start();
        }
    }
}
