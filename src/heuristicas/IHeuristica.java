package heuristicas;

import grafos.Grafo;

public interface IHeuristica {

    public int distancia(Grafo grafo, int origen, int destino);
}
