package TP6.EJ1.IncisoC;

import java.util.Scanner;
public class Ej1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cantNorte, cantSur;
        System.out.println("Ingrese la cantidad de autos que ingresan del NORTE: ");
        cantNorte = sc.nextInt();
        System.out.println("Ingrese la cantidad de autos que ingresan del SUR: ");
        cantSur = sc.nextInt();
        Puente elPuente = new Puente(cantNorte, cantSur);
        Auto[] vanSur = new Auto[cantSur];
        Auto[] vanNorte = new Auto[cantNorte];
        for (int i = 0; i < cantNorte; i++) {
            vanNorte[i] = new Auto(Integer.toString(i), 'N', elPuente);
        }
        for (int i = 0; i < cantSur; i++) {
            vanSur[i] = new Auto(Integer.toString(i), 'S', elPuente);
        }
        for (int i = 0; i < cantNorte; i++) {
            vanNorte[i].start();
        }
        for (int i = 0; i < cantSur; i++) {
            vanSur[i].start();
        }
    }
}
