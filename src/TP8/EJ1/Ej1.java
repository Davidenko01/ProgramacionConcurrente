package TP8.EJ1;

import java.util.Random;

public class Ej1 {
    public static void main(String[] args) {
        Cuartel cuartel = new Cuartel();
        Random r = new Random();
        Soldado[] losSoldados = new Soldado[211];
        for (int i = 0; i < 211; i++) {
            losSoldados[i] = new Soldado("Soldado "+(i+1), cuartel, r.nextBoolean(),r.nextBoolean());
        }
        for (int i = 0; i < 211; i++) {
            losSoldados[i].start();
        }
    }
}
