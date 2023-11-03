package TP6.EJ7;

public class Ej7 {
    public static void main(String[] args) {
        // Use misma longitud, pero se puede usar distintas para cada objeto
        Automovil[] a = new Automovil[6];
        Pasajero[] p = new Pasajero[1];
        Ferry f = new Ferry(28, p.length, a.length);
        ControlFerry cf = new ControlFerry(f);

        for (int i = 0; i < a.length; i++) {
            a[i] = new Automovil("Auto N°" + (i+1), f);
        }

        for (int i = 0; i < p.length; i++) {
            p[i] = new Pasajero("Pasajero N°" + (i+1), f);
        }
        cf.start();
        for (int i = 0; i < a.length; i++) {
            a[i].start();
        }
        for (int i = 0; i < p.length; i++) {
            p[i].start();
        }
    }
}
