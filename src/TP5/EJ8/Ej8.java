package TP5.EJ8;

public class Ej8 {
    public static void main(String[] args) {
        Babuinos[] izq = new Babuinos[3];
        Babuinos[] der = new Babuinos[10];
        Cuerda laCuerda = new Cuerda();

        for (int i = 0; i < 3; i++) {
            izq[i] = new Babuinos(laCuerda, "Izq");
        }

        for (int i = 0; i < 10; i++) {
            der[i] = new Babuinos(laCuerda, "Der");
        }

        for (int i = 0; i < 3; i++) {
            izq[i].start();
        }
        for (int i = 0; i < 10; i++) {
            der[i].start();
        }
    }
}
