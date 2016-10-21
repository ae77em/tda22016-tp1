package heuristicas;

import grafos.Digrafo;
import java.util.ArrayList;
import java.util.List;
import recorridos.BFS;

public class Heuristica implements IHeuristica {

    @Override
    public List<Object> execute(Object... data) {

        List<Object> h = new ArrayList<Object>();
        Digrafo grafo = (Digrafo) data[0];
        int origen = (int) data[1];
        int destino = (int) data[2];

        BFS bfs = new BFS(grafo, origen, destino);

        for (int v = 0; v < grafo.vertices(); v++) {
            double d = bfs.camino(v).size();
            h.add(v, d);
        }

        return h;
    }
}
