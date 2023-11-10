package TP8.EJ3;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class ContenedorCaja {

    /*
    LA CAJA ES EL RECURSO COMPARTIDO ENTRE LOS EMPAQUETADORES Y EL BRAZO ROBOTICO
    LOS EMPAQUETADORES SON LOS ENCARGADOS DE PONER LOS PASTELES EN LA CAJA
    EL BRAZO ROBOTICO ES EL ENCARGADA DE QUITAR LA CAJA CUANDO ESTA SE LLENA
    Y SE ENCARGA TAMBIEN DE PONER OTRA CAJA NUEVA
     */

    private int pesoActual = 0;
    private int pesoLimite;

    private Lock lock = new ReentrantLock();
    private Condition cajaLlena = lock.newCondition();

    private Condition cajaVacia = lock.newCondition();

    public ContenedorCaja(int peso) {
        pesoLimite = peso;
    }

    public void agregarPastel(int peso) {
        lock.lock();
        try {
            while (peso + pesoActual > pesoLimite) {
                //el pastel no entra en la caja, avisa al brazo robotico de que tiene que sacarla
                cajaLlena.signal();
                //espera a que el brazo robotico ponga una nueva caja
                cajaVacia.await();
            }
            System.out.println("BRAZO ROBOTICO PONE PASTEL EN LA CAJA");
            //agrega el pastel a la caja
            pesoActual += peso;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void quitarCaja() throws InterruptedException {
        lock.lock();
        //brazo robotico espera a que la caja este llena
        cajaLlena.await();
        System.out.println("SE LLENÓ LA CAJA");
        System.out.println("BRAZO ROBOTICO RETIRA CAJA LLENA");
    }

    public void reponerCaja() {
        //pone una nueva caja (poner el peso actual en 0)
        pesoActual = 0;
        //avisa a los empaquetadores que ya hay una nueva caja
        cajaVacia.signalAll();
        System.out.println("BRAZO ROBOTICO REPONE CAJA");
        lock.unlock();
    }
}
