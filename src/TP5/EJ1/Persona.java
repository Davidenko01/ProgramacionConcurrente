package TP5.EJ1;

public class Persona extends Thread {
    private Piscina unaPiscina;
    public Persona(String unNombre, Piscina laPiscina) {
        super(unNombre);
        this.unaPiscina = laPiscina;
    }

    public void run() {
        try {
            System.out.println(Thread.currentThread().getName()+ " quiere entrar a la piscina");
            unaPiscina.entrar();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("ERROR");
        }
        unaPiscina.salir();
    }
}
