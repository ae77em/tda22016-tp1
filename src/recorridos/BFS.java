package recorridos;

import grafos.Arista;
import grafos.Digrafo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BFS extends Caminos {

    private ArrayList<Double> dist = new ArrayList<>();  // Inicializar a +∞.
    private ArrayList<Arista> edge = new ArrayList<>();

    private int destino;

    public BFS(Digrafo g, int origen, int destino) {
        super(g, origen);

        this.destino = destino;

        bsf();

    }

    private void bsf() {

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
                    edge.add(new Arista(current, adyacente, dist.get(adyacente).intValue()));
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
}
