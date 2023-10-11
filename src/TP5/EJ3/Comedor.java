package TP5.EJ3;

import java.util.concurrent.Semaphore;

public class Comedor {
    private Semaphore comederos = new Semaphore(10);
    private boolean noHayGatos = true;
    private boolean noHayPerros = true;

    private int cantPerrosComiendo = 0;

    private int cantGatosComiendo = 0;

    private int cantPerrosQueComieron = 0;

    private int cantGatosQueComieron = 0;
    private Semaphore mutex = new Semaphore(1);

    public Comedor() {
    }

    public void entrarCusco() throws InterruptedException {
        boolean entro = false, esperar = false;
        do {
            mutex.acquire();
            if (noHayGatos && !esperar) {
                comederos.acquire(1);
                noHayPerros = false;
                entro = true;
                cantPerrosComiendo++;
                cantPerrosQueComieron++;
                if (calcularDiferencia()) {
                    esperar = true;
                }
            }
            mutex.release();
        } while(!entro);
    }

    private boolean calcularDiferencia() {
        return ((cantPerrosQueComieron - cantGatosQueComieron) >= 5);
    }

    private boolean calcularDiferencia2() {
        return (cantGatosQueComieron - cantPerrosQueComieron) >= 5;
    }

    public void entrarMichi() throws InterruptedException {
        boolean entro = false, esperar = false;
        do {
            mutex.acquire();
            if (noHayPerros && !esperar) {
                comederos.acquire(1);
                noHayGatos = false;
                entro = true;
                cantGatosComiendo++;
                cantGatosQueComieron++;
                if (calcularDiferencia2()) {
                    esperar = true;
                }
            }
            mutex.release();
        } while(!entro);
    }

    public void irsePerro() {
        cantPerrosComiendo--;
        if (cantPerrosComiendo == 0) {
            noHayPerros = true;
        }
        comederos.release(1);
    }

    public void irseGato() {
        cantGatosComiendo--;
        if (cantGatosComiendo == 0) {
            noHayGatos = true;
        }
        comederos.release(1);
    }
}
