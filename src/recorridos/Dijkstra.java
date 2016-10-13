package recorridos;

import grafos.Arista;
import grafos.Digrafo;

public class Dijkstra extends Caminos {

    private static double dist[];
    private static double pred[];
    private static boolean visited[];
    private static Arista edge[];

    public Dijkstra(Digrafo grafo, int origin) {
        super(grafo, origin);

        dist = new double[grafo.vertices()];
        pred = new double[grafo.vertices()];
        visited = new boolean[grafo.vertices()];
        edge = new Arista[grafo.aristas()];

        dijkstra(grafo,origin);
    }

    public static int [] dijkstra (Digrafo grafo, int origin) {
//        dist[origin] = 0;
//
//        for (int i=1; i<dist.length; i++) {
//            dist[i] = Integer.MAX_VALUE;
//        }
//
//
//        for (int i=0; i<dist.length; i++) {
//
//            final int adyacente = verticeMasCercano(dist, visited);
//
//            visited[adyacente] = true;
//
//            // The shortest path to next is dist[next] and via pred[next].
//
//            List<Integer> adyacentes = grafo.adyacentesA(adyacente);
//
//            for (int j=0; j<adyacentes.size(); j++) {
//
//                final int vertice = adyacentes.get(j);
//                final double distancia = dist[adyacente] + grafo.a.getPeso().intValue();
//
//                if (dist[vertice] > distancia) {
//                    dist[vertice] = distancia;
//                    pred[vertice] = adyacente;
//                }
//            }
//        }
//        return pred;  // (ignore pred[s]==0!)
        return null;
    }


    /*private static int verticeMasCercano(double dist[], boolean v[]) {

        int x = Integer.MAX_VALUE;
        int y = -1;   // graph not connected, or no unvisited vertices

        for (int i=0; i<dist.length; i++) {

           if (!v[i] && dist[i]<x) {
               y=i;
               x=dist[i];
           }

        }

        return y;
    }*/

    @Override
    public double distancia(int v) {
        // distancia(v): la distancia hasta un vértice visitado (si no fue visitado, devolver ∞)
        return Double.parseDouble(null);
    }

    @Override
    protected Arista edge_to(int v) {
        // _edge_to(v): la arista que conduce a un vértice v en el camino de origen a destino
        return null;
    }


}
