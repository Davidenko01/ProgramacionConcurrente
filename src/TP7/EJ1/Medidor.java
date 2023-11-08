package TP7.EJ1;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Medidor extends Thread {
    private SalaMuseo sala;
    private Random r;

    public Medidor(SalaMuseo sala) {
        this.sala = sala;
        r = new Random();
    }

    public void run() {
        while (true) {
            sala.notificarTemperatura(r.nextInt(35 - 25 + 1) + 25);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


