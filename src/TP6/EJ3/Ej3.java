package TP6.EJ3;

import java.util.Scanner;
public class Ej3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cantEstudiantes, cantMesas;
        System.out.println("Ingrese la cantidad total de mesas: ");
        cantMesas = sc.nextInt();
        SalaDeEstudio laSala = new SalaDeEstudio(cantMesas);
        System.out.println("Ingrese la cantidad de estudiantes: ");
        cantEstudiantes = sc.nextInt();
        Estudiante[] losEstudiantes = new Estudiante[cantEstudiantes];
        for (int i = 0; i < cantEstudiantes; i++) {
            losEstudiantes[i] = new Estudiante("Estudiante "+(i+1), laSala);
        }

        for (int i = 0; i < cantEstudiantes; i++) {
            losEstudiantes[i].start();
        }
    }
}
