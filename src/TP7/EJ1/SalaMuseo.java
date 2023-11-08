package TP7.EJ1;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
public class SalaMuseo {

    private int tUmbral = 30;

    private int cantPersonasEsperando;

    private int capacidadActual = 20;

    private int temperaturaActual = 26;
    private int jubiladosEsperando = 0;
    private int cantPersonasEnSala = 0;

    private Lock lock = new ReentrantLock();
    private Condition hayLugar = lock.newCondition();

    public SalaMuseo(int cantPersonas) {
        this.cantPersonasEsperando = cantPersonas;
    }

    public void entrarSala() {
        lock.lock();
        try {
            while ((cantPersonasEnSala >= capacidadActual) || jubiladosEsperando > 0) {
                hayLugar.await();
            }
            cantPersonasEnSala++;
            cantPersonasEsperando--;
            System.out.println(Thread.currentThread().getName()+" ENTRA A LA SALA");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void entrarSalaJubilado() {
        lock.lock();
        jubiladosEsperando++;
        System.out.println(Thread.currentThread().getName()+" esperando, tiene prioridad");
        try {
            while (cantPersonasEnSala >= capacidadActual) {
                hayLugar.await();
            }
            System.out.println(Thread.currentThread().getName()+" entró a la sala");
            cantPersonasEnSala++;
            jubiladosEsperando--;
            cantPersonasEsperando--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void salirSala() {
        lock.lock();
        try {
            cantPersonasEnSala--;
            System.out.println(Thread.currentThread().getName() + " salió de la sala.");
            hayLugar.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void notificarTemperatura(int temperatura) {
        lock.lock();
        try {
            if (cantPersonasEsperando == 0) {
                System.out.println("NO HAY MAS GENTE ESPERANDO");
            } else {
                if (temperatura >= tUmbral) {
                    System.out.println("SE SUPERO UMBRAL, SE REDUCE CAPACIDAD SALA");
                    capacidadActual = 10;
                } else {
                    System.out.println("TEMPERATURA NORMAL");
                    capacidadActual = 20;
                }
            }
            temperaturaActual = temperatura;
            System.out.println("TEMPERATURA ACTUAL = "+temperaturaActual+"°C");
        } finally {
            lock.unlock();
        }
    }
}
