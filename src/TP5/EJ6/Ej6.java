package TP5.EJ6;

import java.util.Scanner;

public class Ej6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cantAterrizan, cantDespegan;
        Pista laPista = new Pista();
        TorreControl laTorre = new TorreControl(laPista);
        System.out.println("Ingrese la cantidad de aviones que van a aterrizar");
        cantAterrizan = sc.nextInt();
        System.out.println("Ingresen la cantidad de aviones que van a despegar");
        cantDespegan = sc.nextInt();
        Avion[] aviones = new Avion[cantDespegan+cantAterrizan];
        for (int i = 0; i < cantDespegan; i++) {
            aviones[i] = new Avion("Despegue", laPista, "Avion "+(i+1));
        }
        for (int i = cantDespegan; i < cantAterrizan+cantDespegan; i++) {
            aviones[i] = new Avion("Aterrizar", laPista, "Avion "+(i+1));
        }

        for (int i = 0; i < cantAterrizan+cantDespegan; i++) {
            aviones[i].start();
        }
        laTorre.start();

    }

}
