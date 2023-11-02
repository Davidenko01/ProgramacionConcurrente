package TP5.EJ7;

import java.util.Scanner;

public class Ej7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cantVisitantes;
        Mirador elMirador = new Mirador();
        Encargado elEncargado = new Encargado(elMirador);
        System.out.println("Ingrese la cantidad de visitantes: ");
        cantVisitantes = sc.nextInt();
        Visitante[] losVisitantes = new Visitante[cantVisitantes];
        for (int i = 0; i < cantVisitantes; i++) {
            losVisitantes[i] = new Visitante("Visitante "+(i+1), elMirador);
        }
        elEncargado.start();
        for (int i = 0; i < cantVisitantes; i++) {
            losVisitantes[i].start();
        }
    }
}
