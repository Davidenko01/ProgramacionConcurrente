package TP7.EJ3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cocina {
    private int cantCarne;
    private int cantVerdura;
    private int cantPasta;

    private Lock lock = new ReentrantLock();
    private Condition hayCarne = lock.newCondition();
    private Condition hayVerdura = lock.newCondition();

    private Condition hayPasta = lock.newCondition();

    public Cocina(int carne, int verdura, int pasta) {
        cantCarne = carne;
        cantPasta = pasta;
        cantVerdura = verdura;
    }

    public void cocinarVerdura() {
        lock.lock();
        try {
            while (cantVerdura == 0) {
                hayVerdura.await();
            }
            System.out.println(Thread.currentThread().getName()+" agarra verdura");
            cantVerdura--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void cocinarCarne() {
        lock.lock();
        try {
            while (cantCarne == 0) {
                hayCarne.await();
            }
            System.out.println(Thread.currentThread().getName()+" agarra carne");
            cantCarne--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void cocinarPasta() {
        lock.lock();
        try {
            while (cantPasta == 0) {
                hayPasta.await();
            }
            System.out.println(Thread.currentThread().getName()+" agarra pasta");
            cantPasta--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void reponerCarne() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" repone carne");
            cantCarne++;
            hayCarne.signal();
        } finally {
            lock.unlock();
        }
    }

    public void reponerVerdura() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" repone verdura");
            cantVerdura++;
            hayVerdura.signal();
        } finally {
            lock.unlock();
        }
    }
    public void reponerPasta() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" repone pasta");
            cantPasta++;
            hayPasta.signal();
        } finally {
            lock.unlock();
        }
    }

}
