package recorridos;

import grafos.Arista;
import grafos.Grafo;
import heuristicas.IHeuristica;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BFSHeuristico extends Caminos {
    private Arista aristas[];
    private double dist[];
    double[] distAproxPasandoV;
 
    public BFSHeuristico(Grafo grafo, int origen, int destino,
                         IHeuristica heuristica, boolean incluirPesos)
            throws CaminoNoEncontradoException {
        super(grafo, origen, destino);
        LinkedList<Integer> cerrado = new LinkedList<>();
        PriorityQueue<Integer> abierto = new PriorityQueue<>(1, new ComparadorNodos());
        abierto.add(origen);
        
        dist = new double[grafo.v()];
        distAproxPasandoV = new double[grafo.v()];
        aristas = new Arista[grafo.v()];

        for (int v = 0; v < grafo.v(); v++) {
            dist[v] = Double.POSITIVE_INFINITY;
            distAproxPasandoV[v] = Double.POSITIVE_INFINITY;
        }
        dist[origen] = 0;
        distAproxPasandoV[origen] = heuristica.distancia(grafo, origen, destino);

        while (! abierto.isEmpty()) {

            int verticeActual = abierto.poll();

            if (verticeActual == destino) {
                return;
            }

            double distAprox;
            cerrado.add(verticeActual);
            for (Arista arista : grafo.aristasAdjuntasA(verticeActual)) {
                int verticeVecino = arista.dst();
                if (cerrado.contains(verticeVecino)) {
                    continue;
                }

                distAprox = dist[verticeActual];
                distAprox += (incluirPesos) ? arista.weight() : 1;
                
                if (! abierto.contains(verticeVecino)) {
                    abierto.add(verticeVecino);
                } else if (distAprox >= dist[verticeVecino]) {
                    continue;
                }

                aristas[verticeVecino] = arista;
                dist[verticeVecino] = distAprox;
                distAproxPasandoV[verticeVecino] = dist[verticeVecino] + 
                        heuristica.distancia(grafo, verticeVecino, destino);
                
            }
        }
        throw new CaminoNoEncontradoException();
    }
    
    @Override
    public double distancia(int v) {
        return dist[v];
    }
    
    @Override
    protected Arista edge_to(int v) {
        return aristas[v];
    }
    
    private class ComparadorNodos implements Comparator<Object> {
        @Override
        public int compare(Object o1, Object o2) {
            Integer nodo1 = (Integer) o1;
            Integer nodo2 = (Integer) o2;
            return Double.compare(distAproxPasandoV[nodo1], distAproxPasandoV[nodo2]);
        }
    }

}
