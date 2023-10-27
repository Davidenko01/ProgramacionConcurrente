package TP5.EJ6;

import java.sql.SQLOutput;

public class Avion extends Thread {

    private String accion;
    private Pista laPista;
    public Avion (String laAccion, Pista unaPista, String nombre) {
        super(nombre);
        accion = laAccion;
        laPista = unaPista;
    }

    public void run() {
        try {
            laPista.solicitarPermiso(accion);
            laPista.realizarAccion(accion);
            Thread.sleep(1000);
            System.out.println("MANIOBRA REALIZADA");
            laPista.salirPista(accion);
            if (accion.equals("Aterrizar")) {
                System.out.println("ATERRIZAJE EXITOSO, ESPERANDO FUERA DE PISTA");
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            System.out.println("NOSE");
        }
    }
}
