package recorridos;

import grafos.Arista;
import grafos.Grafo;

import java.util.PriorityQueue;

public class Dijkstra extends Caminos {

	private Arista edge[];
	private double dist[];
	private PriorityQueue<Integer> pq;

	public Dijkstra(Grafo g, int src, int dst) {

		super(g, src, dst);
		dist = new double[g.v()];
		edge = new Arista[g.v()];

		for (int v = 0; v < g.v(); v++) {
			dist[v] = Double.POSITIVE_INFINITY;
		}

		dist[src] = 0;

		pq = new PriorityQueue<>(g.v());
		pq.add(src);

		while (!pq.isEmpty()) {
			int v = pq.poll();
			for (Arista e : g.aristasAdjuntasA(v)) {
				actualizarDistancias(e, dst);
			}

		}

	}

	private void actualizarDistancias(Arista e, int dst) {
		int v = e.src(), w = e.dst();
		if ((dist[w] > dist[v] + e.weight())) {
			dist[w] = dist[v] + e.weight();
			edge[w] = e;
			if (! pq.contains(w)) {
				pq.add(w);
			}
		}
	}

	@Override
	public double distancia(int v) {
		return dist[v];

	}

	@Override
	protected Arista edge_to(int v) {
		return edge[v];

	}
}
