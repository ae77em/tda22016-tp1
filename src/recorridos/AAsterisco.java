package recorridos;

import grafos.Arista;
import grafos.Digrafo;

import java.util.ArrayList;
import java.util.List;

public class AAsterisco extends Caminos {

    public AAsterisco(Digrafo g, int origin) {
        super(g, origin);
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
        // Hacer el mismo algoritmo que para bfs, pero usando elvalor de f para elegir siguiente nodo


    }

    private double f(int v){
        return h(v) + g(v);
    }

    private double h(int v) {

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
