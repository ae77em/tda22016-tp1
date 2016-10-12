package recorridos;

import grafos.Arista;
import grafos.Digrafo;
import java.util.List;

public abstract class Caminos {

    private int src;

    protected Caminos(Digrafo g, int origin) {
        src = origin;
    }

    public abstract double distancia(int v);

    protected abstract Arista edge_to(int v);

    public boolean visitado(int v) {
        return distancia(v) < Double.POSITIVE_INFINITY;
    }

    public List<Arista> camino(int v) {
        /* ... */
        return null;
    }
}
