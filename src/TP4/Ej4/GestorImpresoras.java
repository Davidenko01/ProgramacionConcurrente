package TP4.Ej4;

public class GestorImpresoras {
    private int cantidadA;
    private int cantidadB;
    private Impresora[] colImpresorasA;
    private Impresora[] colImpresorasB;

    public GestorImpresoras(int laCantA, int laCantB) {
        colImpresorasA = new Impresora[laCantA];
        this.cantidadA = laCantA;
        for (int i = 0; i < cantidadA ; i++) {
            colImpresorasA[i] = new Impresora(i+1, 'A');
        }

        colImpresorasB = new Impresora[laCantB];
        this.cantidadB = laCantB;
        for (int j = 0; j < cantidadB ; j++) {
            colImpresorasB[j] = new Impresora(j+1, 'B');
        }
    }

    public void buscarImpresora(char tipo) throws InterruptedException {
        switch(tipo) {
            case 'A': buscarImpresoraA(); break;
            case 'B': buscarImpresoraB(); break;
            case 'X': buscarImpresoraX();break;
            default:
                System.out.println("EREH TONTOH EH"); break;
        }
    }

    public void buscarImpresoraA() throws InterruptedException{
        boolean encontro = false;
        int i = 0;
        do {
            Impresora impAux = colImpresorasA[i];
            if (impAux.intentarImprimir()) {
                System.out.println("El "+Thread.currentThread().getName()+" encontró una impresora, con ID "+impAux.getID()+ " de tipo: "+impAux.getTipo());
                encontro = true;
                impAux.usar();
                Thread.sleep(5000);
                impAux.liberar();
                System.out.println(Thread.currentThread().getName()+ " se va...");
            } else {
                i = (i + 1) % cantidadA;
            }
        } while (!encontro);
    }

    public void buscarImpresoraB() throws InterruptedException{
        boolean encontro = false;
        int i = 0;
        do {
            Impresora impAux = colImpresorasB[i];
            if (impAux.intentarImprimir()) {
                System.out.println("El "+Thread.currentThread().getName()+" encontró una impresora, con ID "+impAux.getID()+ " de tipo: "+impAux.getTipo());
                encontro = true;
                impAux.usar();
                Thread.sleep(5000);
                impAux.liberar();
                System.out.println(Thread.currentThread().getName()+ " se va...");
            } else {
                i = (i + 1) % cantidadB;
            }
        } while (!encontro);
    }

    public void buscarImpresoraX() throws InterruptedException{
        boolean encontro = false;
        int i = 0, j = 0;
        do {
            Impresora impAux = colImpresorasA[i];
            if (impAux.intentarImprimir()) {
                System.out.println("El "+Thread.currentThread().getName()+" encontró una impresora, con ID "+impAux.getID()+ " de tipo: "+impAux.getTipo());
                encontro = true;
                impAux.usar();
                Thread.sleep(5000);
                impAux.liberar();
                System.out.println(Thread.currentThread().getName()+ " se va...");
            } else {
                Impresora impAux2 = colImpresorasB[j];
                if (impAux2.intentarImprimir()) {
                    System.out.println("El " + Thread.currentThread().getName() + " encontró una impresora, con ID " + impAux2.getID()+ " de tipo: "+impAux2.getTipo());
                    encontro = true;
                    impAux2.usar();
                    Thread.sleep(5000);
                    impAux2.liberar();
                    System.out.println(Thread.currentThread().getName() + " se va...");
                } else {
                    i = (i + 1) % cantidadA;
                    j = (j + 1) % cantidadB;
                }
            }
        } while (!encontro);
    }

}
