package grafos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Digrafo {

    /* Grafo no dirigido con un número fijo de vértices.

    Los vértices son siempre números enteros no negativos. El primer vértice
    es 0.

    El grafo se crea vacío, se añaden las aristas con add_edge(). Una vez
    creadas, las aristas no se pueden eliminar, pero siempre se puede añadir
    nuevas aristas. */

    private List<Arista> aristas;
    private List<Integer> vertices;
    private int aristaActual;

    public Digrafo(int v){
        aristaActual = 0;
        for (int i = 0; i < v; i++) {
            vertices.add(i);
        }
    }

    public int vertices(){
        return vertices.size();
    }

    public int aristas(){
        return aristas.size();
    }

    public List<Arista> incidentesA(int v){

        List<Arista> incidentes = new ArrayList<Arista>();

        for (Arista arista : aristas) {
            if ((arista.getOrigen() == v) || arista.getDestino()==v){
                incidentes.add(arista);
            }
        }

        return incidentes;
    }

    public void adyacentesA(int v){

    }

    public void agregarArista(int origen, int destino, int peso){
        Arista aAgregar = new Arista(origen,destino,peso);
        aristas.add(aAgregar);
    }

    public Iterator<Integer> iterar(){
        return vertices.iterator();
    }

    public Iterator<Arista> iterar_aristas() {
        return aristas.iterator();
    }

}
