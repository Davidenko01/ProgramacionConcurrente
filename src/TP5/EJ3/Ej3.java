package TP5.EJ3;

import java.util.Scanner;

public class Ej3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cantGatos, cantPerros, limite, cantComederos;
        System.out.println("INGRESE LA CANTIDAD DE GATOS: ");
        cantGatos = sc.nextInt();
        System.out.println("INGRESE LA CANTIDAD DE PERROS: ");
        cantPerros = sc.nextInt();
        /*El limite se refiere a lo indicado en la Observación 2 del ejercicio
            Si ya han comido xx perros, y hay gatos esperando, no debería permitirse que sigan
            ingresando perros, hasta que algunos gatos puedan comer y a la inversa
            En este caso definimos ese xx */
        System.out.println("INGRESE EL LIMITE: ");
        limite = sc.nextInt();
        System.out.println("INGRESE LA CANTIDAD DE COMEDEROS: ");
        cantComederos = sc.nextInt();

        Comedor unComedor = new Comedor(cantComederos, limite);
        Gato[] losGatos = new Gato[cantGatos];
        Perro[] losPerros = new Perro[cantPerros];

        //CREO LOS HILOS PERROS
        for (int i = 0; i < cantPerros; i++) {
            losPerros[i] = new Perro("PERRO "+(i+1), unComedor);
        }
        //CREO LOS HILOS GATOS
        for (int i = 0; i < cantGatos; i++) {
            losGatos[i] = new Gato("GATO "+(i+1), unComedor);
        }
        //INICIALIZO GATOS
        for (int j = 0; j < cantGatos; j++) {
            losGatos[j].start();
        }

        //INICIALIZO PERROS
        for (int j = 0; j < cantPerros; j++) {
            losPerros[j].start();
        }
    }
}
