import java.util.Stack;

import grafos.Arista;
import grafos.Dijkstra;
import grafos.Grafo;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class DijkstraTest extends TestCase {
    Grafo grafoCuadrado;
    Dijkstra buscadorDijkstra;
    int origen;
    int destino;
    
    public DijkstraTest(String testName) {
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
        
        buscadorDijkstra = new Dijkstra(grafoCuadrado, origen, destino);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDistancia() {
        assertEquals(4.0, buscadorDijkstra.distancia(destino));
        assertEquals(3.0, buscadorDijkstra.distancia(7));
        assertEquals(20.0, buscadorDijkstra.distancia(2));
        assertEquals(0.0, buscadorDijkstra.distancia(origen));
    }

    public void testEdge_to() {
        Stack<Arista> camino = (Stack<Arista>) buscadorDijkstra.camino();
        assertNotNull(camino);
        int verticeAnterior = origen, i = 1;
        int[] caminoEsperado = {0, 3, 6, 7, 8};
        
        while (! camino.empty()) {
            Arista aristaActual = camino.pop();
            assertEquals(verticeAnterior, aristaActual.src());
            assertEquals(caminoEsperado[i], aristaActual.dst());
            verticeAnterior = aristaActual.dst();
            i++;
        }       
    }

    private static void pruebaDijkstra() throws Exception {

        Grafo grafo = new Grafo(5);

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
        Grafo grafo = new Grafo(6);

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
        Grafo grafo = new Grafo(8);

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
    
}
