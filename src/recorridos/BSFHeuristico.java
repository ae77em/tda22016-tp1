package recorridos;

/*
* Similar a BFS, pero encola los vértices en una cola de prioridad según una heurística de acercamiento al destino.
*    Esto es, ignora los pesos de las aristas pero consulta una función heuristica(v) que da una aproximación de la
*    distancia entre un vértice del camino, y el destino final.
* */

import grafos.Arista;
import grafos.Digrafo;

public class BSFHeuristico extends Caminos{

    public BSFHeuristico(Digrafo grafo, int origen, int destino, Heuristica heuristica){
        super(grafo, origen);



    }


    @Override
    public double distancia(int v) {
        return 0;
    }

    @Override
    protected Arista edge_to(int v) {
        return null;
    }
}
