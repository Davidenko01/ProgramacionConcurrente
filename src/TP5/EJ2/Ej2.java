package TP5.EJ2;

public class Ej2 {
    public static void main(String[] args) {
        Confiteria laConfi = new Confiteria();
        Empleado empleado1 = new Empleado("Davor", laConfi, 'C');
        Empleado empleado2 = new Empleado("Tomi", laConfi, 'B');
        Empleado empleado3 = new Empleado("Marcos", laConfi, 'A');
        Cocinero elCocinero = new Cocinero("Gordon Ramsey", laConfi);
        Mozo elMozo = new Mozo("Juansito", laConfi);
        elCocinero.start();
        elMozo.start();
        empleado1.start();
        empleado2.start();
        empleado3.start();
    }
}
