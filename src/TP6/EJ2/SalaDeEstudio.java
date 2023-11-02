package TP6.EJ2;

public class SalaDeEstudio {

    private int mesasOcupadas = 0;
    private int cantMesas;

    public SalaDeEstudio(int lasMesas) {
        cantMesas = lasMesas;
    }
    private boolean salaLlena() {
        return cantMesas == mesasOcupadas;
    }

    public synchronized void entrar() throws InterruptedException {
        while (salaLlena()) {
            this.wait();
        }
        mesasOcupadas++;
        System.out.println(Thread.currentThread().getName()+ " entró a la sala, quedan "+(cantMesas-mesasOcupadas)+ " mesas disponibles");
    }

    public synchronized void salir() {
        mesasOcupadas--;
        System.out.println(Thread.currentThread().getName()+ " salió de la sala, quedan "+(cantMesas-mesasOcupadas)+ " mesas disponibles");
        this.notifyAll();
    }
}
