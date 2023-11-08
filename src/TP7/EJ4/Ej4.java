package TP7.EJ4;

import java.util.Random;

public class Ej4 {

    public static void main(String[] args) {

        Random r = new Random();
        String tipos = "CVP";
        int cantCocineros = 80;
        int cantC = 0;
        int cantV = 0;
        int cantP = 0;
        Cocinero[] cocineros = new Cocinero[cantCocineros];
        Cocina cocina = new Cocina(5,5,5);

        for (int i = 0; i < cantCocineros; i++) {
            char aux = tipos.charAt(r.nextInt(3));
            cocineros[i] = new Cocinero("Cocinero "+(i+1), aux, cocina);
            switch (aux) {
                case 'C': cantC++;break;
                case 'V': cantV++;break;
                case 'P': cantP++;break;
                default:
                    System.err.println("ERROR");{
                }
            }
        }
        System.out.println("Cant. C: "+cantC);
        System.out.println("Cant. V: "+cantV);
        System.out.println("Cant. P: "+cantP);
        for (int i = 0; i < cantCocineros; i++) {
            cocineros[i].start();
        }
    }
}
