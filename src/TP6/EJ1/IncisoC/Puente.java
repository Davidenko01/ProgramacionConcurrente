package TP6.EJ1.IncisoC;

import java.util.LinkedList;
import java.util.Queue;

public class Puente {
    private int cantNorte;
    private int cantSur;
    private int cantCruzaron = 0;
    private int enPuente = 0;
    private Queue<Integer> ordenPuente = new LinkedList<>();
    private Queue<Integer> colaSur = new LinkedList<>();
    private Queue<Integer> colaNorte = new LinkedList<>();

    private String turno = "";

    public Puente(int norte, int sur) {
        cantNorte = norte;
        cantSur = sur;
    }

    private boolean puedeEntrarSur() {
        return turno.equals("SUR") && (colaSur.peek().equals(Integer.valueOf(Thread.currentThread().getName())));
    }

    private boolean puedeEntrarNorte() {
        return turno.equals("NORTE") && (colaNorte.peek().equals(Integer.valueOf(Thread.currentThread().getName())));
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
        while (!puedeEntrarNorte()) {
            this.wait();
        }
        colaNorte.remove(Integer.valueOf(Thread.currentThread().getName()));
        ordenPuente.add(Integer.valueOf(Thread.currentThread().getName()));
        System.out.println("Auto "+ Thread.currentThread().getName()+" entra puente hacia el NORTE");
        cantCruzaron++;
        enPuente++;
        if (cantCruzaron == 10) {
            turno = "EN ESPERA";
        }

    }

    private void entrarSur() throws InterruptedException {
        if (turno.isEmpty()) {
            turno = "SUR";
        }
        colaSur.add(Integer.valueOf(Thread.currentThread().getName()));
        while (!puedeEntrarSur()) {
            this.wait();
        }
        colaSur.remove(Integer.valueOf(Thread.currentThread().getName()));
        ordenPuente.add(Integer.valueOf(Thread.currentThread().getName()));
        System.out.println("Auto "+ Thread.currentThread().getName()+" entra puente hacia el SUR");
        cantCruzaron++;
        enPuente++;
        if (cantCruzaron == 10) {
            turno = "EN ESPERA";
        }
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
        while (ordenPuente.peek() != (Integer.valueOf(Thread.currentThread().getName()))) {
            this.wait();
        }
        ordenPuente.remove(Integer.valueOf(Thread.currentThread().getName()));
        cantSur--;
        enPuente--;
        System.out.println("Auto "+ Thread.currentThread().getName()+" sale del puente hacia el SUR");
        if (turno.equals("EN ESPERA")) {
            if (enPuente == 0)
                if (cantNorte > 0) {
                    cantCruzaron = 0;
                    turno = "NORTE";
                } else {
                    if (cantSur > 0) {
                        cantCruzaron = 0;
                        turno = "SUR";
                    }
                }
        } else {
            if (enPuente == 0) {
                if (cantSur == 0) {
                    if (cantNorte > 0) {
                        cantCruzaron = 0;
                        turno = "NORTE";
                    }
                }
            }
        }
        this.notifyAll();
    }

    public void salirNorte() throws InterruptedException {
        while (ordenPuente.peek() != (Integer.valueOf(Thread.currentThread().getName()))) {
            this.wait();
        }
        ordenPuente.remove(Integer.valueOf(Thread.currentThread().getName()));
        cantNorte--;
        enPuente--;
        System.out.println("Auto " + Thread.currentThread().getName() + " sale del puente hacia el NORTE");
        if (turno.equals("EN ESPERA")) {
            if (enPuente == 0)
                if (cantSur > 0) {
                    cantCruzaron = 0;
                    turno = "SUR";
                } else {
                    if (cantNorte > 0) {
                        cantCruzaron = 0;
                        turno = "NORTE";
                    }
                }
        } else {
            if (enPuente == 0) {
                if (cantNorte == 0) {
                    if (cantSur > 0) {
                        cantCruzaron = 0;
                        turno = "SUR";
                    }
                }
            }
        }
        this.notifyAll();
    }
}
