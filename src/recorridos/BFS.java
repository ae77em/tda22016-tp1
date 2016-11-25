package recorridos;

import grafos.Arista;
import grafos.Grafo;

import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Caminos {
	private Arista edge[];
	private double dist[];

	public BFS(Grafo g, int src, int dst) {
		super(g, src, dst);
		Queue<Integer> q = new LinkedList<Integer>();
		dist = new double[g.v()];
		edge = new Arista[g.v()];
		for (int v = 0; v < g.v(); v++) {
			dist[v] = Double.POSITIVE_INFINITY;
                        edge[v] = null;
		}
		dist[src] = 0;
		q.add(src);
       
		boolean terminoRecorrido = (src == dst);

		while (!q.isEmpty() && !terminoRecorrido) {
			int v = q.remove();
			for (Arista arista : g.aristasAdjuntasA(v)) {
				int w = arista.dst();
				if (!visitado(w)) {
					edge[w] = arista;
					dist[w] = dist[v] + 1;
					if(w == dst){
						terminoRecorrido = true;
						break;
					}
					q.add(w);
				}
			}
		}
		
	}

	public double distancia(int v) {
		return dist[v];
	}

	protected Arista edge_to(int v) {
		return edge[v];
	}
}
