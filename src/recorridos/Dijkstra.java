package recorridos;

import grafos.Arista;
import grafos.Digrafo;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra extends Caminos {

    private List<Double> distA = new ArrayList<>();
    private List<Arista> aristaA = new ArrayList<>();
    Queue<Double> colaDeVertices = new PriorityQueue<Double>();

    public Dijkstra(Digrafo grafo, int origin) {
        super(grafo, origin);

        dijkstra();
    }

    public ArrayList<Integer> dijkstra() {

        for (int i = 0; i < grafo.vertices(); i++) {
            distA.add(Double.POSITIVE_INFINITY);
            aristaA.add(null);
        }

        distA.set(origen, 0.0);

        colaDeVertices.add(distA.get(origen));

//        while (!colaDeVertices.isEmpty()) {
//
//            int verticeMasCercano = colaDeVertices.poll();
//
//            for (Arista a : grafo.incidentesA(verticeMasCercano)) {
//
//                int origen = a.origen();
//                int destino = a.destino();
//
//                if (distA.get(destino) > distA.get(origen) + a.peso()) {
//                    distA.set(destino, distA.get(origen) + a.peso());
//                    aristaA.add(destino, a);
//                }
//            }
//        }
    }

    @Override
    public double distancia(int v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Arista edge_to(int v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
