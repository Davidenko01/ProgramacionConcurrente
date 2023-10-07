package ActividadObligatoria;

public class Pasajero extends Thread {
    private Tren unTren;

    public Pasajero(String unNombre, Tren unTren) {
        super(unNombre);
        this.unTren = unTren;
    }

    public void run() {
        unTren.solicitarSubirse();
        try {
            unTren.subirse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            unTren.bajarse();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
