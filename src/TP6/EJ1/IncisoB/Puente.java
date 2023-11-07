package TP6.EJ1.IncisoB;

import java.util.LinkedList;
import java.util.Queue;

public class Puente {
    private int cantNorte;
    private int cantSur;
    private Queue<Integer> colaSur = new LinkedList<>();
    private Queue<Integer> colaNorte = new LinkedList<>();

    private String turno = "";

    public Puente(int norte, int sur) {
        cantNorte = norte;
        cantSur = sur;
    }

    public synchronized void entrar(char direccion) throws InterruptedException {
        switch (direccion) {
            case 'N': entrarNorte();break;
            case 'S': entrarSur();break;
            default:
                System.out.println("NADA");break;
        }
    }

    private void entrarNorte() throws InterruptedException {
        if (turno.isEmpty()) {
            turno = "NORTE";
        }
        colaNorte.add(Integer.valueOf(Thread.currentThread().getName()));
        while (turno.equals("SUR")) {
            this.wait();
        }
        System.out.println("Auto "+ Thread.currentThread().getName()+" entra puente hacia el NORTE");
    }

    private void entrarSur() throws InterruptedException {
        if (turno.isEmpty()) {
            turno = "SUR";
        }
        colaSur.add(Integer.valueOf(Thread.currentThread().getName()));
        while (turno.equals("NORTE")) {
            this.wait();
        }
        System.out.println("Auto "+ Thread.currentThread().getName()+" entra puente hacia el SUR");
    }

    public synchronized void salir(char direccion) throws InterruptedException {
        switch (direccion) {
            case 'N': salirNorte();break;
            case 'S': salirSur();break;
            default:
                System.out.println("NADA");break;
        }
    }

    public void salirSur() throws InterruptedException {
        while (colaSur.peek() != Integer.valueOf(Thread.currentThread().getName())) {
            this.wait();
        }
        colaSur.remove(Integer.valueOf(Thread.currentThread().getName()));
        cantSur--;
        System.out.println("Auto "+ Thread.currentThread().getName()+" sale del puente hacia el SUR");
        if (cantSur == 0) {
            turno = "NORTE";
        }
        this.notifyAll();
    }

    public void salirNorte() throws InterruptedException {
        while (colaNorte.peek() != Integer.valueOf(Thread.currentThread().getName())) {
            this.wait();
        }
        colaNorte.remove(Integer.valueOf(Thread.currentThread().getName()));
        cantNorte--;
        System.out.println("Auto "+ Thread.currentThread().getName()+" sale del puente hacia el NORTE");
        if (cantNorte == 0) {
            turno = "SUR";
        }
        this.notifyAll();
    }
}
