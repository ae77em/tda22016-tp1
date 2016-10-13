package recorridos;

import grafos.Arista;
import grafos.Digrafo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BFS extends Caminos {

    private ArrayList<Double> dist;  // Inicializar a +∞.
    private ArrayList<Arista> edge;

    public BFS(Digrafo g, int origin, int destino) {
        super(g, origin);

        calcularCamino(g, origin, destino);


    }

    private void calcularCamino(Digrafo g, int origin, int destino) {
        dist.add(Double.valueOf(0));

        for (int i=1; i<g.vertices(); i++){
            dist.add(Double.POSITIVE_INFINITY);
        }

        List<Integer> adyacentes = new ArrayList<>();

        LinkedList<Integer> queue = new LinkedList<Integer>();

        queue.add(origin);

        Integer current = queue.getFirst();

        while (!queue.isEmpty() && current != destino) {

            queue.removeFirst();

            adyacentes = g.adyacentesA(current);

            for (Integer adyacente : adyacentes) {
                if (!visitado(adyacente)) {
                    dist.set(adyacente, dist.get(current) + 1);
                    edge.add(new Arista(current,adyacente,dist.get(adyacente).intValue()));
                    queue.addLast(adyacente);
                }
            }

            current = queue.getFirst();
        }
    }

    public double distancia(int v) {
        return dist.get(v);
    }

    protected Arista edge_to(int v) {
        return edge.get(v);
    }
}
