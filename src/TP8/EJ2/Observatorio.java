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

    private int mantenimientoEnSala = 0;

    private int discapacitadosEnSala = 0;

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
            System.out.println(Thread.currentThread().getName()+ " entra");
            cantVisitantesSala++;
            visitanteEsperando--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void entrarSillaRueda() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+ " esperando, tiene prioridad, no entra nadie (EXCEPTO INV y Mant)");
            sillaRuedasEsperando++;
            while (cantVisitantesSala >= 30 || investigadorEsperando > 0 || investigadorEnSala) {
                discapacitado.await();
            }
            if (capacidad == 50) {
                capacidad = 30;
            }
            System.out.println(Thread.currentThread().getName()+ " entra");
            cantVisitantesSala++;
            discapacitadosEnSala++;
            sillaRuedasEsperando--;
            if (sillaRuedasEsperando == 0) {
                visitante.signalAll();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void entrarInvestigador() {
        lock.lock();
        try {
            investigadorEsperando++;
            System.out.println(Thread.currentThread().getName()+ " esperando, tiene prioridad, no entra nadie");
            while (cantVisitantesSala > 0 || mantenimientoEnSala > 0 || investigadorEnSala) {
                investigador.await();
            }
            System.out.println(Thread.currentThread().getName()+ " entra");
            investigadorEnSala = true;
            investigadorEsperando--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void entrarMantenimiento() {
        lock.lock();
        try {
            mantenimientoEsperando++;
            System.out.println(Thread.currentThread().getName()+ " esperando, tiene prioridad, no entra nadie (excepto INV)");
            while(cantVisitantesSala > 0 || investigadorEnSala) {
                mantenimiento.await();
            }
            System.out.println(Thread.currentThread().getName()+ " entra");
            mantenimientoEnSala++;
            mantenimientoEsperando--;
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
            System.out.println(Thread.currentThread().getName()+ " se va");
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

    public void salirSillaRuedas() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+ " se va");
            cantVisitantesSala--;
            discapacitadosEnSala--;
            if (discapacitadosEnSala == 0) {
                capacidad = 50;
            }
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

    public void salirMantenimiento() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+ " se va");
            mantenimientoEnSala--;
            if (mantenimientoEnSala == 0) {
                if (investigadorEsperando > 0) {
                    investigador.signal();
                } else if (mantenimientoEsperando > 0){
                    mantenimiento.signalAll();
                } else if (sillaRuedasEsperando > 0) {
                    discapacitado.signalAll();
                } else if (visitanteEsperando > 0){
                    visitante.signalAll();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void salirInvestigador() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+ " se va");
            //INVESTIGADOR CUANDO SALE LA SALA ESTA VACIA
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


