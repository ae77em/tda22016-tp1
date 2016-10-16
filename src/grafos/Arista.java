package grafos;

public class Arista implements Comparable {

    private int origen;
    private int destino;
    private double peso;

    public Arista(int o, int d, int p) {
        origen = o;
        destino = d;
        peso = p;
    }

    public int origen() {
        return origen;
    }

    public int destino() {
        return destino;
    }

    public double peso() {
        return peso;
    }

    @Override
    public int compareTo(Object o) {
        Arista arista = ((Arista) o);

        if (arista == null) {
            throw new UnsupportedOperationException("The parameter is not an Arista.");
        }

        if (this.peso > arista.peso) {
            return 1;
        } else if (this.peso < arista.peso) {
            return -1;
        } else {
            return 0;
        }
    }

}
