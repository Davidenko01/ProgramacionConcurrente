package TP5.EJ7;

import java.util.concurrent.Semaphore;

public class Mirador {

    private Semaphore escalera = new Semaphore(10, true);
    private Semaphore tobogan = new Semaphore(0, true);
    private int toboganesEnUso = 0;
    private Semaphore tirarse = new Semaphore(0);
    private int personasEsperando = 0;
    private Semaphore mutex = new Semaphore(1, true);

    public void entrarEscalera() throws InterruptedException {
        escalera.acquire(1);
        mutex.acquire();
        personasEsperando++;
        System.out.println("SE SUBE "+Thread.currentThread().getName());
        mutex.release();
    }

    public void solicitarTirarse() throws InterruptedException {
        tobogan.acquire();
        mutex.acquire();
        toboganesEnUso++;
        System.out.println(Thread.currentThread().getName()+ " se tiró por un tobogan");
        mutex.release();
    }

    public void salir() throws InterruptedException {
        mutex.acquire();
        System.out.println(Thread.currentThread().getName()+ " salió del tobogan");
        toboganesEnUso--;
        tirarse.release(1);
        mutex.release();
    }

    public void controlar() throws InterruptedException {
        mutex.acquire();
        if (personasEsperando >= 2) {
            switch (toboganesEnUso) {
                case 1:
                    tobogan.release();
                    break;
                case 0:
                    tobogan.release(2);
                    break;
                default:
                    break;
            }
        } else {
            if (personasEsperando == 1) {
                switch (toboganesEnUso) {
                    case 1:
                    case 2:
                        tobogan.release(1);break;
                    default: break;
                }
            }
        }
        mutex.release();
        tirarse.acquire(1);
    }

}
