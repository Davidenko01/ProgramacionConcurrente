package TP4.Ej3;

 public class Proceso extends Thread {
    private String name;
    private RC recursoComp;

    public Proceso(String aName, RC unRecurso) {
        this.name = aName;
        this.recursoComp = unRecurso;
    }

    public void run() {
        while (true) {
            try {
                recursoComp.ejecutar(name);
            } catch (InterruptedException e) {
                System.out.println("HOO LEE SHEET");
            }
        }
    }
 }
