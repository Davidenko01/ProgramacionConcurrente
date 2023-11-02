package TP6.EJ5;
import java.util.Scanner;
public class Ej5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Almacen unAlmacen = new Almacen();
        int cantConsumidores, cantProductores;
        System.out.println("Ingrese la cantidad de Productores: ");
        cantProductores = sc.nextInt();
        System.out.println("Ingrese la cantidad de Consumidores: ");
        cantConsumidores = sc.nextInt();
        Productor[] losProductores = new Productor[cantProductores];
        Consumidor[] losConsumidores = new Consumidor[cantConsumidores];
        for (int i = 0; i < cantProductores; i++) {
            losProductores[i] = new Productor(unAlmacen, "Productor "+(i+1));
        }

        for (int i = 0; i < cantConsumidores; i++) {
            losConsumidores[i] = new Consumidor(unAlmacen, "Consumidor "+(i+1));
        }

        for (int i = 0; i < cantConsumidores; i++) {
            losConsumidores[i].start();
        }

        for (int i = 0; i < cantProductores; i++) {
            losProductores[i].start();
        }
    }
}
