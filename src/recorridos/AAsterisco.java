package recorridos;

import grafos.Arista;
import grafos.Digrafo;
import heuristicas.Heuristica;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AAsterisco extends Caminos {

    private List<Double> dist = new ArrayList<>();
    private List<Arista> edge = new ArrayList<>();
    private List<Double> h = new ArrayList<>();
    private List<Object> heuResponse = new ArrayList<>();
    private Queue<Integer> verticesNoVisitados = new PriorityQueue<Integer>();

    public AAsterisco(Digrafo g, int origen, int destino, Heuristica heuristica) {
        super(g, origen, destino);

        heuResponse = heuristica.execute(g, origen, destino);

        for (Object o : heuResponse) {
            h.add((Double) o);
        }

        aasterisco();
    }

    private void aasterisco() {

        for (int i = 0; i < grafo.vertices(); i++) {
            dist.add(Double.POSITIVE_INFINITY);
            nodosPrevios.add(null);
            verticesNoVisitados.add(i);
        }

        dist.set(origen, 0.0);

        int verticeConMenorDistanciaAcumulada;

        while (!verticesNoVisitados.isEmpty()) {

            verticeConMenorDistanciaAcumulada = obtenerVerticeConMenorDistanciaAcumuladaConHeuristica();

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

    private int obtenerVerticeConMenorDistanciaAcumuladaConHeuristica() {

        double distanciaMinima = Double.POSITIVE_INFINITY;
        Integer aDevolver = -1;

        for (Integer vertice : verticesNoVisitados) {
            double heu = dist.get(vertice) + h.get(vertice);
            if (distanciaMinima > heu) {
                distanciaMinima = heu;
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
        return edge.get(v);
    }

}
