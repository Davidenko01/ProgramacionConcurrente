package TP4.Ej3;

public class miMain {


    public static void main(String[] args) {
        RC unRecurso = new RC();
        Proceso proceso1 = new Proceso("P1", unRecurso);
        Proceso proceso3 = new Proceso("P3", unRecurso);
        Proceso proceso2 = new Proceso("P2", unRecurso);

        proceso1.start();
        proceso2.start();
        proceso3.start();
    }
}
