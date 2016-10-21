package recorridos;

import grafos.Arista;
import grafos.Digrafo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BFS extends Caminos {

    private ArrayList<Double> dist = new ArrayList<>();  // Inicializar a +∞.
    private ArrayList<Arista> edge = new ArrayList<>();

    public BFS(Digrafo g, int origen, int destino) {
        super(g, origen, destino);

        bsf();
    }

    private void bsf() {

        for (int i = 0; i < grafo.vertices(); i++) {    // N (nro de vertices)
            dist.add(Double.POSITIVE_INFINITY);         // 1
            nodosPrevios.add(null);                     // 1
        }

        dist.set(0, 0.0);                               // 1

        List<Integer> adyacentes = new ArrayList<>();   // 1
        LinkedList<Integer> queue = new LinkedList<Integer>(); // 1
        queue.add(origen);                              // 1

        Integer current = queue.getFirst();             // 1

        while (!queue.isEmpty() && current != destino) { // N (nro de vertices)
            // todos los vertices
            queue.removeFirst();                                // 1
            adyacentes = grafo.adyacentesA(current);                // 1

            for (Integer adyacente : adyacentes) {                  // A / (suma de todas las A)= N
                if (!visitado(adyacente)) {                         // 1
                    dist.set(adyacente, dist.get(current) + 1);     // 1
                    edge.add(obtenerNuevaArista(current, adyacente)); // 1
                    queue.addLast(adyacente);                       // 1
                    nodosPrevios.set(adyacente, current);           // 1
                }
            }

            current = queue.getFirst();                             // 1
        }
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

    @Override
    public double distancia(int v) {
        return dist.get(v);
    }

    @Override
    protected Arista edge_to(int v) {
        return edge.get(v);
    }

}
