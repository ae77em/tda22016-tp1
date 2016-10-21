package recorridos;

/*
 * Similar a BFS, pero encola los vértices en una cola de prioridad según una heurística de acercamiento al destino.
 *    Esto es, ignora los pesos de las aristas pero consulta una función heuristica(v) que da una aproximación de la
 *    distancia entre un vértice del camino, y el destino final.
 * */
import grafos.Arista;
import grafos.Digrafo;
import heuristicas.Heuristica;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import static recorridos.Caminos.grafo;

public class BSFHeuristico extends Caminos {

    private List<Double> dist = new ArrayList<>();
    private List<Arista> edge = new ArrayList<>();
    private List<Double> h = new ArrayList<>();
    private List<Object> heuResponse = new ArrayList<>();
    private Queue<Integer> verticesNoVisitados = new PriorityQueue<Integer>();

    public BSFHeuristico(Digrafo grafo, int origen, int destino, Heuristica heuristica) {
        super(grafo, origen, destino);

        heuResponse = heuristica.execute(grafo, origen, destino);

        for (Object o : heuResponse) {
            h.add((Double) o);
        }

        bsfheuristico();
    }

    private void bsfheuristico() {

        for (int i = 0; i < grafo.vertices(); i++) {
            dist.add(Double.POSITIVE_INFINITY);
            nodosPrevios.add(null);
        }

        dist.set(0, 0.0);

        List<Integer> adyacentes = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(origen);

        Integer current = queue.getFirst();

        while (!queue.isEmpty() && current != destino) {

            queue.removeFirst();
            adyacentes = grafo.adyacentesA(current);

            for (Integer adyacente : adyacentes) {
                if (!visitado(adyacente)) {
                    dist.set(adyacente, dist.get(current) + 1);
                    edge.add(obtenerNuevaArista(current, adyacente));
                    queue.addLast(adyacente);
                    nodosPrevios.set(adyacente, current);
                }
            }

            current = queue.getFirst();
        }
    }

    @Override
    public double distancia(int v) {
        return dist.get(v);
    }

    @Override
    protected Arista edge_to(int v) {
        return edge.get(v);
    }

    public Arista obtenerNuevaArista(int origen, int destino) {

        Arista nueva = null;

        List<Arista> aristas = grafo.incidentesA(destino);

        for (Arista a : aristas) {                        // M (nro de aristas)
            if (a.origen() == origen && a.destino() == destino) {
                nueva = a;                                  // 1
                break;
            }
        }

        return nueva;
    }

}
