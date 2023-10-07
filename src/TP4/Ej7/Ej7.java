package TP4.Ej7;

public class Ej7 {
    public static void main(String[] args) {
        Confiteria laPanna = new Confiteria();
        Mozo elMozo = new Mozo("Pepe", laPanna);
        Empleado[] losEmpleados = new Empleado[10];
        for (int i = 0; i < 10; i++) {
            losEmpleados[i] = new Empleado("Cliente "+ (i+1), laPanna);
        }
        elMozo.start();
        for (int i = 0; i < 10; i++) {
            losEmpleados[i].start();
        }
    }
}
