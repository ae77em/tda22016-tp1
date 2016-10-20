package recorridos;

import grafos.Arista;
import grafos.Digrafo;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AAsterisco extends Caminos {

    private List<Double> dist = new ArrayList<>();
    private Queue<Integer> verticesNoVisitados = new PriorityQueue<Integer>();
    private double h[];

    public AAsterisco(Digrafo g, int origen, int destino) {
        super(g, origen, destino);

        h = new double[grafo.vertices()];

        BFS bfs = new BFS(grafo, origen, destino);

        while (grafo.iterador().hasNext()){
            int v = grafo.iterador().next();
            double d = bfs.camino(v).size();
            h[v] = d;
        }
    }

    private void aasterisco(){

        List<Integer> ABIERTA = new ArrayList<>();
        List<Integer> CERRADA = new ArrayList<>();

        // ABIERTA contiene todos los vertices adyacentes al actual (inicialmente origen)
        // CERRADA contiene el vertice actual (inicialmente origen)

        // f(v) = h(v) + g(v)
        // g(v) = peso de la arista de actual a adyacente
        // h(v) = distancia de actual a objetivo

        // Calcular distancias
        // Hacer el mismo algoritmo que para dijkstra, pero usando elvalor de f para elegir siguiente nodo

        for (int i = 0; i < grafo.vertices(); i++) {
            dist.add(Double.POSITIVE_INFINITY);
            nodosPrevios.add(null);
            verticesNoVisitados.add(i);
        }

        dist.set(origen, 0.0);

        int proximoVertice;

    /*    while (!verticesNoVisitados.isEmpty()) {

            int v = verticesNoVisitados.poll();
            proximoVertice = obtenerProximoVerticeDesde(v);

            if (proximoVertice != destino) {
                verticesNoVisitados.remove(proximoVertice);

                for (Integer adyacente : grafo.adyacentesA(proximoVertice)) {

                    Arista aristaAAdyacente = obtenerAristaAAdyacente(proximoVertice, adyacente);
                    double alt = dist.get(proximoVertice) + aristaAAdyacente.peso();

                    if (alt < dist.get(adyacente)) {
                        dist.set(adyacente, alt);
                        nodosPrevios.set(adyacente, proximoVertice);
                    }
                }
            } else {
                break;
            }
        }*/



    }

    private int obtenerProximoVerticeDesde(int v) {



        return 0;
    }

    private double f(int v){

        double distancia = 0;
        List<Arista> incidentes = grafo.incidentesA(v);

        for(Arista incidente : incidentes){

            if (incidente.destino() == v){
                distancia = incidente.peso();
                break;
            }
        }

        return distancia;
    }

    private double h(int v) {
        return 0;  

    }

    private double g(int v) {
        return 0;

    }

    @Override
    public double distancia(int v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Arista edge_to(int v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
