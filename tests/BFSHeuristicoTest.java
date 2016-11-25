import java.util.Stack;

import grafos.Arista;
import grafos.BFSHeuristico;
import grafos.Grafo;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class BusquedaHeuristicaTest extends TestCase {
    Grafo grafoCuadrado;
    BFSHeuristico buscadorHeuristica;
    int origen;
    int destino;
    
    public BusquedaHeuristicaTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        origen = 0;
        destino = 8;
        grafoCuadrado = new Grafo(9);
        grafoCuadrado.agregarVertice(0, 1, 10);
        grafoCuadrado.agregarVertice(1, 2, 10);
        grafoCuadrado.agregarVertice(3, 4, 10);
        grafoCuadrado.agregarVertice(4, 5, 10);
        grafoCuadrado.agregarVertice(6, 7, 1);
        grafoCuadrado.agregarVertice(7, 8, 1);
        grafoCuadrado.agregarVertice(0, 3, 1);
        grafoCuadrado.agregarVertice(3, 6, 1);
        grafoCuadrado.agregarVertice(1, 4, 10);
        grafoCuadrado.agregarVertice(4, 7, 10);
        grafoCuadrado.agregarVertice(2, 5, 10);
        grafoCuadrado.agregarVertice(5, 8, 10);
        
        buscadorHeuristica = new BFSHeuristico(grafoCuadrado, origen, destino, new Manhattan(), false);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDistancia() {
        assertEquals(4.0, buscadorHeuristica.distancia(destino));
        assertEquals(3.0, buscadorHeuristica.distancia(7));
        assertEquals(2.0, buscadorHeuristica.distancia(2));
        assertEquals(0.0, buscadorHeuristica.distancia(origen));
    }

    public void testEdge_to() {
        Stack<Arista> camino = (Stack<Arista>) buscadorHeuristica.camino();
        assertNotNull(camino);
        int verticeAnterior = origen, i = 1;

        while (! camino.empty()) {
            Arista aristaActual = camino.pop();
            assertEquals(verticeAnterior, aristaActual.src());
            verticeAnterior = aristaActual.dst();
        }
    }

    private static void pruebaHBSF() {
        Digrafo_old grafo = new Digrafo_old(7);

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
