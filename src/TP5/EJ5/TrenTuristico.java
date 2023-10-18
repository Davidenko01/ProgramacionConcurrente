package TP5.EJ5;

import java.sql.SQLOutput;
import java.util.concurrent.Semaphore;

public class TrenTuristico {

    private int cantidad;
    private Semaphore espacio;
    private Semaphore ocupacion = new Semaphore(0);
    private Semaphore bajarse = new Semaphore(0);
    private Semaphore trenVacio = new Semaphore(0);
    private Semaphore tickets = new Semaphore(0);
    private Semaphore vendedorTickets = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1, true);
    private  Semaphore vender;
    private int cantTickets;

    public TrenTuristico(int laCantidad, int losTickets) {
        cantidad = laCantidad;
        cantTickets = losTickets;
        vender = new Semaphore(laCantidad);
        espacio = new Semaphore(laCantidad);
    }

    public void subirPasajero() throws InterruptedException{
        espacio.acquire(1);
        System.out.println("Pasajero " + Thread.currentThread().getName() + " Se sento en el tren");
        ocupacion.release(1);
    }
    public void iniciaViaje() throws InterruptedException{
        ocupacion.acquire(cantidad);
        System.out.println("Comienza el recorrido");
    }
    public void finRecorrido() throws InterruptedException {
        System.out.println("Finalizo recorrido");
        bajarse.release(cantidad);
        trenVacio.acquire(cantidad);
        System.out.println("Tren VACIO, pueden subirse nuevamente pasajeros");
        espacio.release(cantidad);
    }
    public void bajarAsiento() throws InterruptedException{
        bajarse.acquire(1);
        System.out.println("Pasajero " + Thread.currentThread().getName() + " Se bajo del tren");
        trenVacio.release(1);
    }

    public void comprarTicket() throws InterruptedException {
        mutex.acquire(1);
        vendedorTickets.release(1);
        tickets.acquire(1);
        System.out.println(Thread.currentThread().getName()+ " compro un ticket");
        mutex.release(1);
    }

    public void venderTickets() throws InterruptedException {
        vendedorTickets.acquire(1);
        if (cantTickets > 0) {
            tickets.release(1);
            cantTickets--;
        } else {
            System.out.println("NO HAY MAS TICKETS");
        }
    }
}
