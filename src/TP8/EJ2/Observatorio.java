package TP8.EJ2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Observatorio {

    private Lock lock = new ReentrantLock();
    private Condition discapacitado = lock.newCondition();
    private Condition investigador = lock.newCondition();
    private Condition visitante = lock.newCondition();
    private int mantenimientoEsperando = 0;

    private int visitanteEsperando = 0;
    private Condition mantenimiento = lock.newCondition();
    private int capacidad = 50;

    private int limpiezaEnSala = 0;

    private boolean investigadorEnSala = false;
    private int investigadorEsperando = 0;
    private int sillaRuedasEsperando = 0;
    private int cantVisitantesSala;

    public void entrarVisitante() {
        lock.lock();
        try {
            visitanteEsperando++;
            while (cantVisitantesSala >= capacidad || sillaRuedasEsperando > 0 || investigadorEsperando > 0 || investigadorEnSala) {
                visitante.await();
            }
            cantVisitantesSala++;
            visitanteEsperando--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void entrarSillaRueda() {
        lock.lock();
        try {
            sillaRuedasEsperando++;
            while (cantVisitantesSala >= 30 || investigadorEsperando > 0 || investigadorEnSala) {
                discapacitado.await();
            }
            if (capacidad == 50) {
                capacidad = 30;
            }
            cantVisitantesSala++;
            sillaRuedasEsperando--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void entrarCritico() {
        lock.lock();
        try {
            investigadorEsperando++;
            while (cantVisitantesSala > 0 || limpiezaEnSala > 0 || investigadorEnSala) {
                investigador.await();
            }
            investigadorEnSala = true;
            investigadorEsperando--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void entrarLimpieza() {
        lock.lock();
        try {
            while(cantVisitantesSala > 0 || investigadorEnSala) {
                mantenimiento.wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void salirVisitante() {
        lock.lock();
        try {
            cantVisitantesSala--;
            if (cantVisitantesSala == 0) {
                if (investigadorEsperando > 0) {
                    investigador.signal();
                } else if (mantenimientoEsperando > 0){
                    mantenimiento.signalAll();
                } else if (sillaRuedasEsperando > 0) {
                    discapacitado.signalAll();
                } else if (visitanteEsperando > 0){
                    visitante.signalAll();
                }
            } else {
                if (investigadorEsperando == 0) {
                    if (mantenimientoEsperando == 0) {
                        if (sillaRuedasEsperando > 0) {
                            if (cantVisitantesSala < 30) {
                                discapacitado.signal();
                            }
                        } else if (visitanteEsperando > 0){
                            visitante.signal();
                        }
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void salirInvestigador() {
        lock.lock();
        try {
            investigadorEnSala = false;
            if (investigadorEsperando > 0) {
                investigador.signal();
            } else if (mantenimientoEsperando > 0) {
                mantenimiento.signalAll();
            } else if (sillaRuedasEsperando > 0) {
                discapacitado.signalAll();
            } else if (visitanteEsperando > 0){
                visitante.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}


