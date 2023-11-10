package TP8.EJ3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mostrador {

    /*EL MOSTRADOR ES EL RECURSO COMPARTIDO ENTRE LOS HORNOS Y LOS EMPAQUETADORES
    EL HORNO SE ENCARGA DE PRODUCIR LOS PASTELES Y PONERLOS EN EL MOSTRADOR
    LOS EMPAQUETADORES SON LOS ENCARGADOS DE QUITAR LOS PASTELES DEL MOSTRADOR
    PARA LUEGO COLOCARLOS EN LA CAJA*/

    private Queue<Pastel> mostrador = new LinkedList<Pastel>();

    private Lock lock = new ReentrantLock();
    private Condition hayPasteles = lock.newCondition();

    public void ponerPastel(Pastel unPastel) {
        //horno produce un pastel y lo agrega al mostrador (a la cola)
        //asumo que el mostrador es infinito (se podría hacer limitado)
        lock.lock();
        try {
            mostrador.add(unPastel);
            //avisa que hay pastel en el mostrador
            hayPasteles.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int quitarPastel() throws InterruptedException {
        //empaquetador quita pastel del mostrador
        lock.lock();
        while (mostrador.isEmpty()) {
            //si no hay pasteles, espera a que el horno produzca uno
            hayPasteles.await();
        }
        lock.unlock();
        //poll recupera y elimina el elemento al frente de la cola
        return mostrador.poll().getPeso();
    }

}
