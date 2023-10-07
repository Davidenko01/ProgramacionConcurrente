package ActividadObligatoria;

import TP4.Ej4.Cliente;

public class nuestroMain {
    public static void main(String[] args) {
        Tren unTren = new Tren(3);
        Pasajero[] losPasajeros = new Pasajero[5];
        ControlTren unControl = new ControlTren(unTren);
        for (int i = 0; i < 5; i++) {
            losPasajeros[i] = new Pasajero("Pasajero "+(i+1), unTren);
        }
        unControl.start();
        for (int j = 0; j < 5; j++) {
            losPasajeros[j].start();
        }
    }
}
