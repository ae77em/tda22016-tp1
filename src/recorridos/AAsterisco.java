package recorridos;

import grafos.Arista;
import grafos.Grafo;
import heuristicas.IHeuristica;

public class AAsterisco extends BFSHeuristico{
 
    public AAsterisco(Grafo grafo, int verticeOrigen, int verticeDestino,
                      IHeuristica heuristica) throws CaminoNoEncontradoException {
        super(grafo, verticeOrigen, verticeDestino, heuristica, true);
    }

    @Override
    public double distancia(int v) {
        return super.distancia(v);
    }

    @Override
    protected Arista edge_to(int v) {
        return super.edge_to(v);
    }
}
