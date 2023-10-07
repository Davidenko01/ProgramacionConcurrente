package ActividadObligatoria;

import java.util.concurrent.Semaphore;

public class Tren {
    int capacidad;
    int lugaresDisponibles = 3;
    Semaphore subirse = new Semaphore(0, true);
    Semaphore bajarse = new Semaphore(0);
    Semaphore control = new Semaphore(0);

    public Tren(int capacidad) {
        this.capacidad = capacidad;
    }

    public void solicitarSubirse() {
        System.out.println("El pasajero "+Thread.currentThread().getName()+ " solicita subirse al tren");
        control.release();
    }

    public boolean tieneLugar() {
        return lugaresDisponibles > 0;
    }
    public void noHacerNada() throws InterruptedException {
        control.acquire();
    }

    public void dejarSubirse() {
        subirse.release();
    }

    public void dejarBajarse() throws InterruptedException{
        for (int i = 0; i < capacidad; i++) {
            bajarse.release();
        }
        bajarse.acquire();
    }

    public void bajarse() throws InterruptedException {
        bajarse.acquire();
        System.out.println("El pasajero "+Thread.currentThread().getName()+" se bajó");
    }


    public void subirse() throws InterruptedException {
        subirse.acquire();
        System.out.println("El pasajero "+Thread.currentThread().getName()+ " se subió al tren");
        lugaresDisponibles -= 1;
    }
}
