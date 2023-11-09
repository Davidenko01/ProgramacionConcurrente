package TP6.EJ1.IncisoB;

import java.util.LinkedList;
import java.util.Queue;

public class Puente {
    private int cantNorte;
    private int cantSur;
    private Queue<Integer> colaSur = new LinkedList<>();
    private Queue<Integer> colaNorte = new LinkedList<>();

    private Queue<Integer> ordenPuente = new LinkedList<>();

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

    private boolean turnoNorte() {
        return turno.equals("NORTE") && colaNorte.peek().equals(Integer.valueOf(Thread.currentThread().getName()));
    }

    private boolean turnoSur() {
        return turno.equals("SUR") && colaSur.peek().equals(Integer.valueOf(Thread.currentThread().getName()));
    }

    private void entrarNorte() throws InterruptedException {
        if (turno.isEmpty()) {
            turno = "NORTE";
        }
        colaNorte.add(Integer.valueOf(Thread.currentThread().getName()));
        while (!turnoNorte()) {
            this.wait();
        }
        colaNorte.remove(Integer.valueOf(Thread.currentThread().getName()));
        ordenPuente.add(Integer.valueOf(Thread.currentThread().getName()));
        System.out.println("Auto "+ Thread.currentThread().getName()+" entra puente hacia el NORTE");
    }

    private void entrarSur() throws InterruptedException {
        if (turno.isEmpty()) {
            turno = "SUR";
        }
        colaSur.add(Integer.valueOf(Thread.currentThread().getName()));
        while (!turnoSur()) {
            this.wait();
        }
        colaSur.remove(Integer.valueOf(Thread.currentThread().getName()));
        ordenPuente.add(Integer.valueOf(Thread.currentThread().getName()));
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
        while (ordenPuente.peek() != Integer.valueOf(Thread.currentThread().getName())) {
            this.wait();
        }
        ordenPuente.remove(Integer.valueOf(Thread.currentThread().getName()));
        cantSur--;
        System.out.println("Auto "+ Thread.currentThread().getName()+" sale del puente hacia el SUR");
        if (cantSur == 0) {
            turno = "NORTE";
        }
        this.notifyAll();
    }

    public void salirNorte() throws InterruptedException {
        while (ordenPuente.peek() != Integer.valueOf(Thread.currentThread().getName())) {
            this.wait();
        }
        ordenPuente.remove(Integer.valueOf(Thread.currentThread().getName()));
        cantNorte--;
        System.out.println("Auto "+ Thread.currentThread().getName()+" sale del puente hacia el NORTE");
        if (cantNorte == 0) {
            turno = "SUR";
        }
        this.notifyAll();
    }
}
