package TP7.EJ2;

public class Ej2 {
    public static void main(String[] args) {
        Programador[] losProgramadores = new Programador[10];
        Oficina oficina = new Oficina(6, 4);
        for (int i = 0; i < 10; i++) {
            losProgramadores[i] = new Programador("Programador "+(i+1), oficina);
        }
        for (int i = 0; i < 10; i++) {
            losProgramadores[i].start();
        }
    }
}
