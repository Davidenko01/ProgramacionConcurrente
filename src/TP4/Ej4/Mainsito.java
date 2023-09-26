package TP4.Ej4;

public class Mainsito {
    public static void main(String[] args) {
        GestorImpresoras gestor = new GestorImpresoras(6, 7);
        int cantClientes = 20;
        Cliente[] clientes = new Cliente[cantClientes];
        for (int i = 0; i < 8; i++) {
            clientes[i] = new Cliente(gestor, "Cliente "+(i+1)+" A",'A');
        }
        for (int i = 8; i < 16; i++) {
            clientes[i] = new Cliente(gestor, "Cliente "+(i+1)+" B",'B');
        }
        for (int i = 16; i < 20; i++) {
            clientes[i] = new Cliente(gestor, "Cliente "+(i+1)+ " X",'X');
        }
        for (int i = 0; i < cantClientes; i++) {
            clientes[i].start();
        }
    }
}

