package recorridos;

import grafos.*;

public class BFS extends Caminos {

    private double dist[];  // Inicializar a +∞.
    private Arista edge[];

    public BFS(Digrafo g, int origin, int destino) {
        super(g, origin);

     /*   for ( int nodo : g.get)

        n.distance = INFINITY

        n.parent = NIL


        create empty queue Q


        root.distance = 0

        Q.enqueue(root)


        while Q is not empty:



        current = Q.dequeue()



        for each node n that is adjacent to current:

        if n.distance == INFINITY:

        n.distance = current.distance + 1

        n.parent = current

        Q.enqueue(n)

        // código del algoritmo, rellena "dist" y "edge".*/
    }

    public double distancia(int v) {
        return dist[v];
    }



    protected Arista edge_to(int v) {
        return edge[v];
    }
}
