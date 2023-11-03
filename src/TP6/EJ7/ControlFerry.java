package TP6.EJ7;

public class ControlFerry extends Thread {

    private Ferry elFerry;
    public ControlFerry(Ferry unFerry) {
        elFerry = unFerry;
    }

    public void run() {
        try {
            while(true) {
                elFerry.zarpar();
                Thread.sleep(15000);
                elFerry.desembarcar();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
