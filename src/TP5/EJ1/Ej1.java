package TP5.EJ1;

public class Ej1 {
    public static void main(String[] args) {
        Persona[] lasPersonas = new Persona[10];
        Piscina unaPiscina = new Piscina(5);
        for (int i = 0; i < 10; i++) {
            lasPersonas[i] = new Persona("Cliente "+(i+1), unaPiscina);
        }
        for (int j = 0; j < 10; j++) {
            lasPersonas[j].start();
        }
    }
}
