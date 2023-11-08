package TP7.EJ4;

public class Cocinero extends Thread {
    private char receta;

    private Cocina cocina;

    private String nombre;

    public Cocinero(String nombre, char laReceta, Cocina laCocina) {
        super(nombre);
        receta = laReceta;
        cocina = laCocina;
        this.nombre = nombre;
    }

    public void run() {
        try {
            cocinar();
            System.out.println(nombre+" esta cocinando");
            Thread.sleep(10000);
            reponer();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void cocinar() throws InterruptedException {
        switch (receta) {
            case 'V': cocina.cocinarVerdura();break;
            case 'C': cocina.cocinarCarne();break;
            case 'P': cocina.cocinarPasta();break;
            default:
                System.err.println("ERROR");break;
        }
    }

    private void reponer() {
        switch (receta) {
            case 'V': cocina.reponerVerdura();break;
            case 'C': cocina.reponerCarne();break;
            case 'P': cocina.reponerPasta();break;
            default:
                System.err.println("ERROR");break;
        }
    }
}
