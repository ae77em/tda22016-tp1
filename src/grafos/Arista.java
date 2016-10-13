package grafos;

public class Arista {

    private int origen;
    private int destino;
    private double peso;

    public Arista(int o, int d, int p){
        origen = o;
        destino = d;
        peso = p;
    }

    public int getOrigen() {
        return origen;
    }

    public int getDestino() {
        return destino;
    }

    public double getPeso() {
        return peso;
    }
}
