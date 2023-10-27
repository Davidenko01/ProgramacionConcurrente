package TP5.EJ6;

import java.sql.SQLOutput;
import java.util.concurrent.Semaphore;

public class Pista {

    private Semaphore torreDeControl = new Semaphore(0);
    private Semaphore pista = new Semaphore(1);
    private Semaphore mutex = new Semaphore(1);
    private Semaphore accionRealizada = new Semaphore(1);
    private Semaphore permisoDespegue = new Semaphore(0, true);
    private Semaphore permisosAterrizaje = new Semaphore(0, true);
    private int cantAterrizajes = 0;

    private int quierenAterrizar = 0;

    private int quierenDespegar = 0;

    public Pista() {
    }

    public void realizarAccion(String laAccion) throws InterruptedException {
        if (laAccion.equals("Aterrizar")) {
            mutex.acquire();
            quierenAterrizar--;
            mutex.release();
        } else {
            mutex.acquire();
            quierenDespegar--;
            mutex.release();
        }
        pista.acquire(1);
        System.out.println(Thread.currentThread().getName() + " obtiene la pista y: " + laAccion);
    }

    public void salirPista(String laAccion) {
        if (laAccion.equals("Aterrizar")) {
            cantAterrizajes++;
        } else {
            cantAterrizajes = 0;
        }
        pista.release();
        accionRealizada.release(1);
    }


    public void solicitarPermiso(String laAccion) throws InterruptedException {
        mutex.acquire();
        System.out.println("El " + Thread.currentThread().getName() + " quiere: " + laAccion);
        switch (laAccion) {
            case "Aterrizar":
                solicitarPermisoAterrizaje();
                break;
            case "Despegue":
                solicitarPermisoDespegue();
                break;
            default:
                System.out.println("NOSE");
                break;
        }
    }

    private void solicitarPermisoAterrizaje() throws InterruptedException {
        quierenAterrizar++;
        torreDeControl.release(1);
        mutex.release();
        permisosAterrizaje.acquire();
    }

    private void solicitarPermisoDespegue() throws InterruptedException {
        quierenDespegar++;
        torreDeControl.release(1);
        mutex.release();
        permisoDespegue.acquire();
    }

    public void concederPermiso() throws InterruptedException {
        torreDeControl.acquire();
        accionRealizada.acquire(1);
        mutex.acquire();
        if (cantAterrizajes < 10) {
            if (quierenAterrizar > 0) {
                permisosAterrizaje.release(1);
            } else {
                permisoDespegue.release(1);
            }
        } else {
            if (quierenDespegar > 0) {
                permisoDespegue.release(1);
            } else {
                permisosAterrizaje.release(10);
            }
        }
        mutex.release();
    }
}
