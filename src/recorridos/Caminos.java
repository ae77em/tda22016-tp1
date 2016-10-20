package recorridos;

import grafos.Arista;
import grafos.Digrafo;
import java.util.ArrayList;
import java.util.List;

public abstract class Caminos {

    protected static int origen;
    protected static int destino;
    protected static Digrafo grafo;
    protected List<Integer> nodosPrevios = new ArrayList<>();

    protected Caminos(Digrafo g, int o) {
        origen = o;
        grafo = g;
    }

    protected Caminos(Digrafo g, int o, int d) {

        if (o > grafo.vertices() || o < 0) {
            throw new IllegalArgumentException("El vértice origen indicado no existe en el grafo.");
        }

        if (d > grafo.vertices() || d < 0) {
            throw new IllegalArgumentException("El vértice destino indicado no existe en el grafo.");
        }

        origen = o;
        grafo = g;
        destino = d;
    }

    public abstract double distancia(int v);

    protected abstract Arista edge_to(int v);

    public boolean visitado(int v) {
        return distancia(v) < Double.POSITIVE_INFINITY;
    }

    public List<Arista> camino(int v) {

        int verticeActual = v;
        int verticePrevio = -1;

        List<Arista> caminoAVertice = new ArrayList<>();

        if (visitado(verticeActual)) {
            while (verticeActual != origen) {
                verticePrevio = nodosPrevios.get(verticeActual);
                caminoAVertice.add(0, grafo.aristaEntre(verticePrevio, verticeActual));
                verticeActual = verticePrevio;
            }
        } else {
            caminoAVertice = null;
        }

        return caminoAVertice;
    }

}
