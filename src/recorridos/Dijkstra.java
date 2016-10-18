package recorridos;

import grafos.Arista;
import grafos.Digrafo;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra extends Caminos {

    private int destino = 0;
    private List<Double> dist = new ArrayList<>();
    Queue<Integer> verticesNoVisitados = new PriorityQueue<Integer>();

    public Dijkstra(Digrafo grafo, int origen, int destino) {
        super(grafo, origen);

        this.destino = destino;

        dijkstra();
    }

    public final void dijkstra() {

        if (destino > grafo.vertices() || destino < 0) {
            throw new IllegalArgumentException("El vertice destino indicado no existe en el grafo.");
        }

        for (int i = 0; i < grafo.vertices(); i++) {
            dist.add(Double.POSITIVE_INFINITY);
            nodosPrevios.add(null);
            verticesNoVisitados.add(i);
        }

        dist.set(origen, 0.0);

        int verticeConMenorDistanciaAcumulada;

        while (!verticesNoVisitados.isEmpty()) {

            verticeConMenorDistanciaAcumulada = obtenerVerticeConMenorDistanciaAcumulada();

            if (verticeConMenorDistanciaAcumulada != destino) {
                verticesNoVisitados.remove(verticeConMenorDistanciaAcumulada);

                for (Integer adyacente : grafo.adyacentesA(verticeConMenorDistanciaAcumulada)) {

                    Arista aristaAAdyacente = obtenerAristaAAdyacente(verticeConMenorDistanciaAcumulada, adyacente);
                    double alt = dist.get(verticeConMenorDistanciaAcumulada) + aristaAAdyacente.peso();

                    if (alt < dist.get(adyacente)) {
                        dist.set(adyacente, alt);
                        nodosPrevios.set(adyacente, verticeConMenorDistanciaAcumulada);
                    }
                }
            } else {
                break;
            }
        }
    }

    private int obtenerVerticeConMenorDistanciaAcumulada() {

        double distanciaMinima = Double.POSITIVE_INFINITY;
        Integer aDevolver = -1;

        for (Integer vertice : verticesNoVisitados) {
            if (distanciaMinima > dist.get(vertice)) {
                distanciaMinima = dist.get(vertice);
                aDevolver = vertice;
            }
        }

        return aDevolver;
    }

    private Arista obtenerAristaAAdyacente(int origen, int adyacente) {
        List<Arista> aristas = grafo.incidentesA(adyacente);

        for (Arista arista : aristas) {
            if (arista.origen() == origen) {
                return arista;
            }
        }
        return null;
    }

    @Override
    public double distancia(int v) {
        return dist.get(v);
    }

    @Override
    protected Arista edge_to(int v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
