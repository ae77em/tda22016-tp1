package grafos;

import java.util.ArrayList;
import java.util.HashMap;

public class Grafo {

	private int V;
	private int E;
	private HashMap<Integer, ArrayList<Arista>> adjuntas = new HashMap<Integer, ArrayList<Arista>>();

    public Grafo(int V) {

        if (V < 0) {
            throw new IllegalArgumentException("El numero de vertices no puede ser negativo");
        }

        this.V = V;
        this.E = 0;

        for (int v = 0; v < V; v++) {
            adjuntas.put(v, new ArrayList<Arista>());;
        }
    }

    public int v() {
        return V;
    }

    public int e() {
        return E;
    }

    public void agregarVertice(int v, int w, int peso) {

        if (!adjuntas.containsKey(v) || !adjuntas.containsKey(w)) {
            throw new IndexOutOfBoundsException("El vertice ingresado es invalido");
        }
        E++;
        adjuntas.get(v).add(new Arista(v, w, peso));

    }

    public ArrayList<Arista> aristasAdjuntasA(int v) {
        return adjuntas.get(v);
    }

    // no se usa pero lo dejo porque el enunciado dice que tiene que estar...
    public ArrayList<Integer> adj(int v){
        ArrayList<Integer> adj = new ArrayList<Integer>();
        ArrayList<Arista> aristasAdj = this.adjuntas.get(v);
        for (int i=0; i< aristasAdj.size(); i++){
            adj.add(aristasAdj.indexOf(i));
        }
        return adj;
    }

    // no se usa pero lo dejo porque el enunciado dice que tiene que estar...
    public ArrayList<Integer> iter(){
        ArrayList<Integer> vertices = new ArrayList<Integer>();
        for (int v=0; v< this.V; v++){
            vertices.add(v);
         }
        return vertices;
    }

    // no se usa pero lo dejo porque el enunciado dice que tiene que estar...
    public ArrayList<Arista> iter_edges()
    {
        ArrayList<Arista> aristas = new ArrayList<Arista>();
        for (int v=0; v < this.V; v++){
            aristas.addAll(aristasAdjuntasA(v));
         }
        return aristas;
    }

    public String toString() {
        String s = "";
        for (int v = 0; v < V; v++) {
            s += v;
            s += " : ";
            s += adjuntas.get(v);
        }
        return s;
    }
}