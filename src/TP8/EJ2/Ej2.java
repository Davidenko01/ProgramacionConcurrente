package TP8.EJ2;

public class Ej2 {
    public static void main(String[] args) {
        Observatorio observatorio = new Observatorio();
        Visitante[] losVisitantes = new Visitante[60];
        Visitante[] losDiscapacitados = new Visitante[5];
        Mantenimiento[] losQueLavan = new Mantenimiento[8];
        Investigador[] losInvestigadores = new Investigador[3];

        for (int i = 0; i < 60; i++) {
            losVisitantes[i] = new Visitante(observatorio, false, "Visitante "+(i+1));
        }
        for (int i = 0; i < 5; i++) {
            losDiscapacitados[i] = new Visitante(observatorio, true, "SillaRuedas "+(i+1));
        }

        for (int i = 0; i < 8; i++) {
            losQueLavan[i] = new Mantenimiento(observatorio, "Mantenimiento "+(i+1));
        }

        for (int i = 0; i < 3; i++) {
            losInvestigadores[i] = new Investigador(observatorio, "Investigador "+(i+1));
        }
        for (int i = 0; i < 60; i++) {
            losVisitantes[i].start();
        }
        for (int i = 0; i < 5; i++) {
            losDiscapacitados[i].start();
        }
        for (int i = 0; i < 8; i++) {
            losQueLavan[i].start();
        }
        for (int i = 0; i < 3; i++) {
            losInvestigadores[i].start();
        }
    }
}
