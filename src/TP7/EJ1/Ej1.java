package TP7.EJ1;
import java.util.Scanner;
public class Ej1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cantPersonas;
        System.out.println("Ingrese la cantidad de Personas: ");
        cantPersonas = sc.nextInt();
        SalaMuseo sala = new SalaMuseo(cantPersonas);
        Persona[] lasPersonas = new Persona[cantPersonas];
        for (int i = 0; i < cantPersonas; i++) {
            if (i % 5 == 0) {
                lasPersonas[i] = new Persona("Jubilado", "Jubilado "+(i+1), sala);
            } else {
                lasPersonas[i] = new Persona("Normal", "Persona "+(i+1), sala);
            }
        }
        Medidor medidor = new Medidor(sala);
        medidor.start();
        for (int i = 0; i < cantPersonas; i++) {
            lasPersonas[i].start();
        }
    }
}
