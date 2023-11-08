package TP7.EJ2;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Oficina {
    private int cantLaptops;
    private int cantLibros;

    private Lock lock = new ReentrantLock();
    private Condition hayLaptops = lock.newCondition();
    private Condition hayLibros = lock.newCondition();

    public Oficina(int laptops, int libros) {
        cantLaptops = laptops;
        cantLibros = libros;
    }

    public void agarrarLaptop() {
        lock.lock();
        try {
            while (cantLaptops <= 0) {
                hayLaptops.await();
            }
            System.out.println(Thread.currentThread().getName()+ " agarró una Laptop");
            cantLaptops--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void agarrarLibro() {
        lock.lock();
        try {
            while (cantLibros <= 0) {
                hayLibros.await();
            }
            System.out.println(Thread.currentThread().getName()+ " agarró una Libro");
            cantLibros--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void dejarCosas() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" terminó de usar todo");
            cantLaptops++;
            cantLibros++;
            hayLaptops.signalAll();
            hayLibros.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
