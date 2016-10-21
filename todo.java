package grafos;

public class Arista implements Comparable {

    private int origen;
    private int destino;
    private double peso;

    public Arista(int o, int d, int p) {
        origen = o;
        destino = d;
        peso = p;
    }

    public int origen() {
        return origen;
    }

    public int destino() {
        return destino;
    }

    public double peso() {
        return peso;
    }

    public boolean sigueElMismoCaminoQue(Arista a){
        return this.origen == a.origen && this.destino == a.destino;
    }

    @Override
    public String toString() {
        return "\n" + String.valueOf(origen) + " ---- " + String.valueOf(peso) + " -----> " + String.valueOf(destino);
    }

    @Override
    public int compareTo(Object o) {
        Arista arista = ((Arista) o);

        if (arista == null) {
            throw new UnsupportedOperationException("The parameter is not an Arista.");
        }

        if (this.peso > arista.peso) {
            return 1;
        } else if (this.peso < arista.peso) {
            return -1;
        } else {
            return 0;
        }
    }

}
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

    public Digrafo(int v) {

        aristas = new ArrayList<Arista>();
        vertices = new ArrayList<Integer>();

        for (int i = 0; i < v; i++) {
            vertices.add(i);
        }
    }

    public int vertices() {
        return vertices.size();
    }

    public int aristas() {
        return aristas.size();
    }

    public List<Arista> incidentesA(int v) {

        List<Arista> incidentes = new ArrayList<Arista>();

        for (Arista arista : aristas) {
            if (arista.destino() == v) {
                incidentes.add(arista);
            }
        }

        return incidentes;
    }

    public List<Integer> adyacentesA(int v) {
        List<Integer> adyacentes = new ArrayList<Integer>();

        for (Arista arista : aristas) {
            if (arista.origen() == v) {
                adyacentes.add(arista.destino());
            }
        }

        return adyacentes;
    }

    public Arista aristaEntre(int origen, int destino) {
        for (Arista arista : aristas) {
            if (arista.origen() == origen && arista.destino() == destino) {
                return arista;
            }
        }
        return null;
    }

    public void agregarArista(int origen, int destino, int peso) {
        Arista aAgregar = new Arista(origen, destino, peso);

        for (Arista a : aristas) {
            if (aAgregar.sigueElMismoCaminoQue(a)) {
                System.out.println("No se pueden agregar dos aristas con el mismo origen y destino.");
                break;
            }
        }

        aristas.add(aAgregar);
    }

    public Iterator<Integer> iterador() {
        return vertices.iterator();
    }

    public Iterator<Arista> iterador_aristas() {
        return aristas.iterator();
    }
}
package recorridos;

import grafos.Arista;
import grafos.Digrafo;
import java.util.ArrayList;
import java.util.List;

public abstract class Caminos {

    protected static int origen;
    protected static int destino;
    protected static Digrafo grafo;
    protected List<Integer> nodosPrevios = new ArrayList<>();

    protected Caminos(Digrafo g, int o) {
        origen = o;
        grafo = g;
    }

    protected Caminos(Digrafo g, int o, int d) {

        origen = o;
        grafo = g;
        destino = d;

        if (o > grafo.vertices() || o < 0) {
            throw new IllegalArgumentException("El vértice origen indicado no existe en el grafo.");
        }

        if (d > grafo.vertices() || d < 0) {
            throw new IllegalArgumentException("El vértice destino indicado no existe en el grafo.");
        }

    }

    public abstract double distancia(int v);

    protected abstract Arista edge_to(int v);

    public boolean visitado(int v) {
        return distancia(v) < Double.POSITIVE_INFINITY;
    }

    public List<Arista> camino(int v) {

        int verticeActual = v;
        int verticePrevio = -1;

        List<Arista> caminoAVertice = new ArrayList<>();

        if (visitado(verticeActual)) {
            while (verticeActual != origen) {
                verticePrevio = nodosPrevios.get(verticeActual);
                caminoAVertice.add(0, grafo.aristaEntre(verticePrevio, verticeActual));
                verticeActual = verticePrevio;
            }
        } else {
            caminoAVertice = null;
        }

        return caminoAVertice;
    }

}
package recorridos;

/*
 * Similar a BFS, pero encola los vértices en una cola de prioridad según una heurística de acercamiento al destino.
 *    Esto es, ignora los pesos de las aristas pero consulta una función heuristica(v) que da una aproximación de la
 *    distancia entre un vértice del camino, y el destino final.
 * */
import grafos.Arista;
import grafos.Digrafo;
import heuristicas.Heuristica;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import static recorridos.Caminos.grafo;

public class BSFHeuristico extends Caminos {

    private List<Double> dist = new ArrayList<>();
    private List<Arista> edge = new ArrayList<>();
    private List<Double> h = new ArrayList<>();
    private List<Object> heuResponse = new ArrayList<>();
    private Queue<Integer> verticesNoVisitados = new PriorityQueue<Integer>();

    public BSFHeuristico(Digrafo grafo, int origen, int destino, Heuristica heuristica) {
        super(grafo, origen, destino);

        heuResponse = heuristica.execute(grafo, origen, destino);

        for (Object o : heuResponse) {
            h.add((Double) o);
        }

        bsfheuristico();
    }

    private void bsfheuristico() {

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
                    edge.add(obtenerNuevaArista(current, adyacente));
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

}
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
package recorridos;

import grafos.Arista;
import grafos.Digrafo;
import heuristicas.Heuristica;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AAsterisco extends Caminos {

    private List<Double> dist = new ArrayList<>();
    private List<Arista> edge = new ArrayList<>();
    private List<Double> h = new ArrayList<>();
    private List<Object> heuResponse = new ArrayList<>();
    private Queue<Integer> verticesNoVisitados = new PriorityQueue<Integer>();

    public AAsterisco(Digrafo g, int origen, int destino, Heuristica heuristica) {
        super(g, origen, destino);

        heuResponse = heuristica.execute(g, origen, destino);

        for (Object o : heuResponse) {
            h.add((Double) o);
        }

        aasterisco();
    }

    private void aasterisco() {

        for (int i = 0; i < grafo.vertices(); i++) {
            dist.add(Double.POSITIVE_INFINITY);
            nodosPrevios.add(null);
            verticesNoVisitados.add(i);
        }

        dist.set(origen, 0.0);

        int verticeConMenorDistanciaAcumulada;

        while (!verticesNoVisitados.isEmpty()) {

            verticeConMenorDistanciaAcumulada = obtenerVerticeConMenorDistanciaAcumuladaConHeuristica();

            if (verticeConMenorDistanciaAcumulada != destino) {
                verticesNoVisitados.remove(verticeConMenorDistanciaAcumulada);

                for (Integer adyacente : grafo.adyacentesA(verticeConMenorDistanciaAcumulada)) {

                    Arista aristaAAdyacente = obtenerAristaAAdyacente(verticeConMenorDistanciaAcumulada, adyacente);
                    double alt = dist.get(verticeConMenorDistanciaAcumulada) + aristaAAdyacente.peso();

                    if (alt < dist.get(adyacente)) {
                        dist.set(adyacente, alt);
                        nodosPrevios.set(adyacente, verticeConMenorDistanciaAcumulada);
                    }
                }
            } else {
                break;
            }
        }
    }

    private int obtenerVerticeConMenorDistanciaAcumuladaConHeuristica() {

        double distanciaMinima = Double.POSITIVE_INFINITY;
        Integer aDevolver = -1;

        for (Integer vertice : verticesNoVisitados) {
            double heu = dist.get(vertice) + h.get(vertice);
            if (distanciaMinima > heu) {
                distanciaMinima = heu;
                aDevolver = vertice;
            }
        }

        return aDevolver;
    }

    private Arista obtenerAristaAAdyacente(int origen, int adyacente) {
        List<Arista> aristas = grafo.incidentesA(adyacente);

        for (Arista arista : aristas) {
            if (arista.origen() == origen) {
                return arista;
            }
        }
        return null;
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
package recorridos;

import grafos.Arista;
import grafos.Digrafo;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra extends Caminos {

    private List<Double> dist = new ArrayList<>();
    private Queue<Integer> verticesNoVisitados = new PriorityQueue<Integer>();

    public Dijkstra(Digrafo grafo, int origen, int destino) {
        super(grafo, origen, destino);

        dijkstra();
    }

    public final void dijkstra() {

        for (int i = 0; i < grafo.vertices(); i++) {
            dist.add(Double.POSITIVE_INFINITY);
            nodosPrevios.add(null);
            verticesNoVisitados.add(i);
        }

        dist.set(origen, 0.0);

        int verticeConMenorDistanciaAcumulada;

        while (!verticesNoVisitados.isEmpty()) {

            verticeConMenorDistanciaAcumulada = obtenerVerticeConMenorDistanciaAcumulada();

            if (verticeConMenorDistanciaAcumulada != destino) {
                verticesNoVisitados.remove(verticeConMenorDistanciaAcumulada);

                for (Integer adyacente : grafo.adyacentesA(verticeConMenorDistanciaAcumulada)) {

                    Arista aristaAAdyacente = obtenerAristaAAdyacente(verticeConMenorDistanciaAcumulada, adyacente);
                    double alt = dist.get(verticeConMenorDistanciaAcumulada) + aristaAAdyacente.peso();

                    if (alt < dist.get(adyacente)) {
                        dist.set(adyacente, alt);
                        nodosPrevios.set(adyacente, verticeConMenorDistanciaAcumulada);
                    }
                }
            } else {
                break;
            }
        }
    }

    private int obtenerVerticeConMenorDistanciaAcumulada() {

        double distanciaMinima = Double.POSITIVE_INFINITY;
        Integer aDevolver = -1;

        for (Integer vertice : verticesNoVisitados) {
            if (distanciaMinima > dist.get(vertice)) {
                distanciaMinima = dist.get(vertice);
                aDevolver = vertice;
            }
        }

        return aDevolver;
    }

    private Arista obtenerAristaAAdyacente(int origen, int adyacente) {
        List<Arista> aristas = grafo.incidentesA(adyacente);

        for (Arista arista : aristas) {
            if (arista.origen() == origen) {
                return arista;
            }
        }
        return null;
    }

    @Override
    public double distancia(int v) {
        return dist.get(v);
    }

    @Override
    protected Arista edge_to(int v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
package estadisticos;

import java.util.Vector;

public class Heap {

    Vector<Integer> heap;

    public Heap() {
        heap = new Vector<Integer>();
    }

    public void agregar(Integer elem) {
        heap.add(elem);

        if (heap.size() > 1) {
            Integer posNuevoElem = heap.size() - 1;

            buscarLugar(elem, posNuevoElem);
        }
    }

    public Integer obtenerMin() {
        return heap.elementAt(0);
    }

    public void eliminarMin() {
        heap.set(0, heap.elementAt(heap.size() - 1));

        heap.removeElementAt(heap.size() - 1);

        reordenar(0);
    }

    private void reordenar(Integer indice) {
        Integer tamanio = heap.size();

        Integer indCambiar = 0;

        if (indice * 2 + 2 < tamanio) {
            if (heap.elementAt(indice * 2 + 1) < heap.elementAt(indice * 2 + 2)) {
                indCambiar = indice * 2 + 1;
            } else {
                indCambiar = indice * 2 + 2;
            }
        } else if (indice * 2 + 1 < tamanio) {
            indCambiar = indice * 2 + 1;
        }

        if (indCambiar == 0) {
            return;
        }

        if (heap.elementAt(indice) > heap.elementAt(indCambiar)) {
            intercambiar(indice, indCambiar);

            reordenar(indCambiar);
        }
    }

    private void mostrar() {
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.elementAt(i) + " ");
        }
        System.out.println(" ");
    }

    private void buscarLugar(Integer elem, Integer posNuevoElem) {
        Integer indPadre = obtenerIndicePadre(posNuevoElem);

        if (heap.elementAt(indPadre) > heap.elementAt(posNuevoElem)) {
            intercambiar(indPadre, posNuevoElem);

            if (indPadre != 0) {
                buscarLugar(elem, indPadre);
            }
        }
    }

    private void intercambiar(Integer indPadre, Integer indHijo) {
        Integer aux = heap.elementAt(indPadre);

        heap.set(indPadre, heap.elementAt(indHijo));

        heap.set(indHijo, aux);
    }

    private Integer obtenerIndicePadre(Integer indHijo) {
        Integer indPadre;

        if (indHijo % 2 == 0) {
            indPadre = indHijo / 2 - 1;
        } else {
            indPadre = indHijo / 2;
        }
        return indPadre;
    }
}
package estadisticos;

class Main {

    //path tipoMetodo k valor

    public static void main(String[] args) {

        int metodoElegido = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        int valor = Integer.parseInt(args[3]);
        boolean ok = false;

        Parser p = new Parser(args[0]);
        p.parsear();

        CalculadorInferencia calculadorInferencia = new CalculadorInferencia(p.obtenerDatos());

        switch (metodoElegido) {
            case 1:
                ok = calculadorInferencia.calcularPorFuerzaBruta(k, valor);
                break;
            case 2:
                ok = calculadorInferencia.calcularPorOrdenarSeleccionar(k, valor);
                break;
            case 3:
                ok = calculadorInferencia.calcularPorKSelecciones(k, valor);
                break;
            case 4:
                ok = calculadorInferencia.calcularPorKSeleccionesEnHeap(k, valor);
                break;
            case 5:
                ok = calculadorInferencia.calcularPorHeapSelect(k, valor);
                break;
            case 6:
                ok = calculadorInferencia.calcularPorQuickSelect(k, valor);
                break;
        }

        if (ok) {
            System.out.println("El valor " + valor + " ocupa la posicion " + k);
        } else {
            System.out.println("El valor " + valor + " no ocupa la posicion " + k);
        }
    }
}
package estadisticos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Parser {

    String path;
    Vector<Integer> datosParseados;
    BufferedReader bf;

    public Parser(String path) {
        this.path = path;

        datosParseados = new Vector<Integer>();

        bf = null;
    }

    public void parsear() {
        abrirArchivo();

        extraerDatos();
    }

    private void extraerDatos() {
        String sCadena;

        try {
            while ((sCadena = bf.readLine()) != null) {
                pasarVecAInt(sCadena.split(","));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void pasarVecAInt(String[] split) {
        for (String nro : split) {
            datosParseados.add(Integer.parseInt(nro));
        }
    }

    private void abrirArchivo() {
        try {
            bf = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Vector<Integer> obtenerDatos() {
        return datosParseados;
    }
}
package estadisticos;

import java.util.Vector;

public class CalculadorInferencia {

    Vector<Integer> contenedorDatos;
    Vector<Integer> selecciones = new Vector<Integer>();
    Heap heap = new Heap();

    public CalculadorInferencia(Vector<Integer> a) {
        contenedorDatos = a;
    }

    private void ordenarPorBurbujeo() {
        int i, j, aux;

        for (i = 0; i < contenedorDatos.size() - 1; i++) {
            for (j = 0; j < contenedorDatos.size() - i - 1; j++) {
                if (contenedorDatos.elementAt(j + 1) < contenedorDatos.elementAt(j)) {
                    aux = contenedorDatos.elementAt(j + 1);

                    contenedorDatos.set(j + 1, contenedorDatos.elementAt(j));

                    contenedorDatos.set(j, aux);
                }
            }
        }
    }

    public boolean calcularPorFuerzaBruta(Integer pos, Integer valor) {
        System.out.println("Calculando Por Fuerza Bruta");

        Integer aDevolver = null;

        if (esKEsimoMenor(pos, valor)) {
            aDevolver = contenedorDatos.get(pos);
        }

        return aDevolver != null;
    }

    private boolean esKEsimoMenor(int k, int valor) {

        int vecesMenor = 0;

        for (int nro : contenedorDatos) {
            if (valor < nro) {
                vecesMenor++;
            }
        }

        return (contenedorDatos.size() - vecesMenor) == k; // devuelve true si es el k-menor elemento....
    }

    private boolean verificarPos(Integer pos, Integer valor) {
        if (contenedorDatos.elementAt(pos) == valor) {
            return true;
        }

        return false;
    }

    public boolean calcularPorOrdenarSeleccionar(Integer pos, Integer valor) {
        System.out.println("Calculando Por Ordenar y Seleccionar");

        ordenarPorBurbujeo();

        return verificarPos(pos - 1, valor);
    }

    public boolean calcularPorKSelecciones(Integer pos, Integer valor) {
        System.out.println("Calculando Por K Selecciones");

        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);

        Integer minimo = 2147483647, indiceMin = 0;

        for (int i = 0; i < pos; i++) {
            for (int j = 0; j < contenedorDatos.size(); j++) {
                if (contenedorDatos.elementAt(j) < minimo) {
                    minimo = contenedorDatos.elementAt(j);
                    indiceMin = j;
                }
            }
            selecciones.set(i, minimo);
            contenedorDatos.removeElementAt(indiceMin);
            minimo = 2147483647;
            indiceMin = 0;
        }

        return verificarPosPorSeleccion(pos - 1, valor);
    }

    public boolean calcularPorKSeleccionesEnHeap(Integer pos, Integer valor) {
        System.out.println("Calculando Por K-HeapSort");

        Integer minimo = 2147483647, indiceMin = 0;

        for (int i = 0; i < pos; i++) {
            for (int j = 0; j < contenedorDatos.size(); j++) {
                if (contenedorDatos.elementAt(j) < minimo) {
                    minimo = contenedorDatos.elementAt(j);
                    indiceMin = j;
                }
            }
            heap.agregar(minimo);
            contenedorDatos.removeElementAt(indiceMin);
            minimo = 2147483647;
            indiceMin = 0;
        }

        if (buscarRefEnHeap(pos, valor)) {
            return true;
        }

        return false;
    }

    private boolean buscarRefEnHeap(Integer pos, Integer valor) {
        --pos;

        while (pos > 0) {
            heap.eliminarMin();
            --pos;
        }

        if (heap.obtenerMin() == valor) {
            return true;
        }

        return false;
    }

    private boolean verificarPosPorSeleccion(Integer pos, Integer valor) {
        if (selecciones.elementAt(pos) == valor) {
            return true;
        }

        return false;
    }

    public boolean calcularPorHeapSelect(int pos, int valor) {
        System.out.println("Calculando Por HeapSelect");

        heapSort();

        return buscarRefEnHeap(pos, valor);
    }

    private void heapSort() {
        for (int i = 0; i < contenedorDatos.size(); i++) {
            heap.agregar(contenedorDatos.elementAt(i));
        }
    }

    public boolean calcularPorQuickSelect(int pos, int valor) {
        System.out.println("Calculando Por QuickSelect");

        int valorEnPos = quickSelect(0, contenedorDatos.size() - 1, pos);

        if (valorEnPos == valor) {
            return true;
        }

        return false;
    }

    private int quickSelect(int ini, int fin, int pos) {
        if (ini == fin) {
            return contenedorDatos.elementAt(ini);
        }

        int q = partitionSelect(ini, fin);

        int k = q - ini + 1;

        if (pos == k) {
            return contenedorDatos.elementAt(q);
        } else if (pos < k) {
            return quickSelect(ini, q - 1, pos);
        } else {
            return quickSelect(q + 1, fin, pos - k);
        }
    }

    private int partitionSelect(int ini, int fin) {
        int x = contenedorDatos.elementAt(fin);

        int i = ini - 1;

        for (int j = ini; j <= (fin - 1); j++) {
            if (contenedorDatos.elementAt(j) <= x) {
                i = i + 1;
                intercambiar(i, j);
            }
        }
        intercambiar(i + 1, fin);

        return i + 1;
    }

    private void mostrar(int ini, int fin) {
        for (int i = ini; i <= fin; i++) {
            System.out.print(contenedorDatos.elementAt(i) + " ");
        }
        System.out.println(" ");
    }

    private void intercambiar(int i, int j) {
        Integer aux = contenedorDatos.elementAt(i);

        contenedorDatos.set(i, contenedorDatos.elementAt(j));

        contenedorDatos.set(j, aux);
    }

}
package main;

import estadisticos.CalculadorInferencia;
import estadisticos.Heap;
import grafos.Digrafo;
import heuristicas.Heuristica;

import java.util.Vector;
import recorridos.AAsterisco;
import recorridos.BFS;
import recorridos.Caminos;
import recorridos.Dijkstra;

public class Tda22016Tp1 {

    public static void main(String[] args) throws Exception {

        pruebaBSF();
        pruebaDijkstra();
        pruebaDijkstra2();
        pruebaDijkstra3();

        pruebaHBSF();
        pruebaAAsterisco();

    }

    private static void pruebaBSF() throws Exception {
        Digrafo grafo = new Digrafo(7);

        grafo.agregarArista(0, 1, 1);
        grafo.agregarArista(0, 2, 1);
        grafo.agregarArista(1, 3, 1);
        grafo.agregarArista(1, 4, 1);
        grafo.agregarArista(0, 1, 1);
        grafo.agregarArista(2, 5, 1);
        grafo.agregarArista(2, 6, 1);

        Caminos caminos = new BFS(grafo, 0, 4);

        System.out.println("BFS");
        System.out.println(caminos.camino(4));
    }

    private static void pruebaDijkstra() throws Exception {

        Digrafo grafo = new Digrafo(5);

        grafo.agregarArista(0, 1, 7);
        grafo.agregarArista(0, 3, 2);
        grafo.agregarArista(1, 2, 1);
        grafo.agregarArista(1, 3, 2);
        grafo.agregarArista(2, 4, 5);
        grafo.agregarArista(3, 1, 3);
        grafo.agregarArista(3, 2, 8);
        grafo.agregarArista(3, 4, 5);
        grafo.agregarArista(4, 2, 4);

        System.out.println("Dijkstra 1");
        Caminos caminos = new Dijkstra(grafo, 0, 4);

        System.out.println(caminos.camino(2));

    }

    private static void pruebaDijkstra2() throws Exception {
        Digrafo grafo = new Digrafo(6);

        /* tomado de https://www.youtube.com/watch?v=VENf0GXRd6E */
        grafo.agregarArista(0, 1, 2);
        grafo.agregarArista(0, 2, 1);
        grafo.agregarArista(1, 3, 1);
        grafo.agregarArista(2, 3, 3);
        grafo.agregarArista(2, 4, 4);
        grafo.agregarArista(3, 5, 2);
        grafo.agregarArista(4, 5, 2);

        System.out.println("Dijkstra 2");
        Caminos caminos = new Dijkstra(grafo, 0, 5);

        System.out.println(caminos.camino(5));
    }

    private static void pruebaDijkstra3() throws Exception {
        Digrafo grafo = new Digrafo(8);

        /* tomado de https://grafos-caminosminimos.wikispaces.com/C.M.+-+Algoritmo+de+Dijkstra */
        /* ida */
        grafo.agregarArista(0, 1, 16);
        grafo.agregarArista(0, 2, 10);
        grafo.agregarArista(0, 3, 5);
        /* vuelta */
        grafo.agregarArista(1, 0, 16);
        grafo.agregarArista(2, 0, 10);
        grafo.agregarArista(3, 0, 5);

        /* ida */
        grafo.agregarArista(1, 2, 2);
        grafo.agregarArista(1, 5, 4);
        grafo.agregarArista(1, 6, 6);
        /* vuelta */
        grafo.agregarArista(2, 1, 2);
        grafo.agregarArista(5, 1, 4);
        grafo.agregarArista(6, 1, 6);

        /* ida */
        grafo.agregarArista(2, 3, 4);
        grafo.agregarArista(2, 4, 10);
        grafo.agregarArista(2, 5, 12);
        /* vuelta */
        grafo.agregarArista(3, 2, 4);
        grafo.agregarArista(4, 2, 10);
        grafo.agregarArista(5, 2, 12);

        /* ida */
        grafo.agregarArista(3, 4, 15);
        /* vuelta */
        grafo.agregarArista(4, 3, 15);

        /* ida */
        grafo.agregarArista(4, 5, 3);
        grafo.agregarArista(4, 7, 5);

        /* vuelta */
        grafo.agregarArista(5, 4, 3);
        grafo.agregarArista(7, 4, 5);

        /* ida */
        grafo.agregarArista(5, 6, 8);
        grafo.agregarArista(5, 7, 16);
        /* vuelta */
        grafo.agregarArista(6, 5, 8);
        grafo.agregarArista(7, 5, 16);

        /* ida */
        grafo.agregarArista(6, 7, 7);
        /* vuelta */
        grafo.agregarArista(7, 6, 7);

        System.out.println("Dijkstra 3");
        Caminos caminos = new Dijkstra(grafo, 0, 7);

        System.out.println(caminos.camino(7));
    }

    private static void pruebaAAsterisco() throws Exception {
        Digrafo grafo = new Digrafo(6);

        /* tomado de https://www.youtube.com/watch?v=VENf0GXRd6E */
        grafo.agregarArista(0, 1, 2);
        grafo.agregarArista(0, 2, 1);
        /* agrego arista que va directamente desde el origen al destino,
         pero que pesa mas que otra adyacente... */
        grafo.agregarArista(0, 5, 2);
        //----------
        grafo.agregarArista(1, 3, 1);
        grafo.agregarArista(2, 3, 3);
        grafo.agregarArista(2, 4, 4);
        grafo.agregarArista(3, 5, 2);
        grafo.agregarArista(4, 5, 2);

        System.out.println("A*");

        Caminos caminos = new AAsterisco(grafo, 0, 5, new Heuristica());

        System.out.println(caminos.camino(5));
    }

    private static void pruebaHBSF() {
        Digrafo grafo = new Digrafo(7);

        grafo.agregarArista(0, 1, 1);
        grafo.agregarArista(0, 2, 1);
        grafo.agregarArista(1, 3, 1);
        grafo.agregarArista(1, 4, 1);
        grafo.agregarArista(0, 1, 1);
        grafo.agregarArista(2, 5, 1);
        grafo.agregarArista(2, 6, 1);

        Caminos caminos = new BFS(grafo, 0, 4);

        System.out.println("BFS con Heuristica");
        System.out.println(caminos.camino(4));
    }

}
package heuristicas;

import java.util.List;

/**
 * Created by ariel on 18/10/16.
 */
public interface IHeuristica {

    List<Object> execute(Object... data);
}
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
package heuristicas;

/**
 * Created by ariel on 18/10/16.
 */
public class CommandHeuristica {

    public static void callCommand(Heuristica command, Object... data) {
        command.execute(data);
    }

}
