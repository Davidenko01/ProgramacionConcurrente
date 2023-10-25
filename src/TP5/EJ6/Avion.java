package TP5.EJ6;

import java.sql.SQLOutput;

public class Avion extends Thread {

    private String accion;
    private Pista laPista;
    public Avion (String laAccion, Pista unaPista) {
        accion = laAccion;
        laPista = unaPista;
    }

    public void run() {
        try {
            //laPista.solicitarPermiso(accion);
            //laPista.realizarAccion(accion);
            Thread.sleep(10000);
            System.out.println("MANIOBRA REALIZADA");
            if (accion.equals("Aterrizar")) {
                System.out.println("ATERRIZAJE EXITOSO, ESPERANDO FUERA DE PISTA");
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            System.out.println("NOSE");
        }
    }
}
