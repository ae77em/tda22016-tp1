package recorridos;

import grafos.*;

public class BFS extends Caminos {

    private double dist[];  // Inicializar a +∞.
    private Arista edge[];

    public BFS(Digrafo g, int origin, int destino) {
        super(g, origin);

        // código del algoritmo, rellena "dist" y "edge".
    }

    public double distancia(int v) {
        return dist[v];
    }

    protected Arista edge_to(int v) {
        return edge[v];
    }
}
