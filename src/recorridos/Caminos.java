package recorridos;

import grafos.Arista;
import grafos.Digrafo;
import java.util.List;

public abstract class Caminos {

    protected static int origen;
    protected static Digrafo grafo;

    protected Caminos(Digrafo g, int o) {
        origen = o;
        grafo = g;
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
