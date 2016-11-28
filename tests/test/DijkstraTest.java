package test;

import java.util.Stack;

import grafos.Arista;
import recorridos.Dijkstra;
import grafos.Grafo;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import recorridos.Caminos;

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

    public void testDijkstra() throws Exception {

        Grafo grafo = new Grafo(5);

        grafo.agregarVertice(0, 1, 7);
        grafo.agregarVertice(0, 3, 2);
        grafo.agregarVertice(1, 2, 1);
        grafo.agregarVertice(1, 3, 2);
        grafo.agregarVertice(2, 4, 5);
        grafo.agregarVertice(3, 1, 3);
        grafo.agregarVertice(3, 2, 8);
        grafo.agregarVertice(3, 4, 5);
        grafo.agregarVertice(4, 2, 4);

        Caminos caminos = new Dijkstra(grafo, 0, 4);

        System.out.println(caminos.distancia(4));
        assertEquals(7.0, caminos.distancia(4));

    }

    public void testDijkstra2() throws Exception {
        Grafo grafo = new Grafo(6);

        /* tomado de https://www.youtube.com/watch?v=VENf0GXRd6E */
        grafo.agregarVertice(0, 1, 2);
        grafo.agregarVertice(0, 2, 1);
        grafo.agregarVertice(1, 3, 1);
        grafo.agregarVertice(2, 3, 3);
        grafo.agregarVertice(2, 4, 4);
        grafo.agregarVertice(3, 5, 2);
        grafo.agregarVertice(4, 5, 2);

        Caminos caminos = new Dijkstra(grafo, 0, 5);

        assertEquals(5.0, caminos.distancia(5));
    }

    public void testDijkstra3() {
        Grafo grafo = new Grafo(8);

        /* tomado de https://grafos-caminosminimos.wikispaces.com/C.M.+-+Algoritmo+de+Dijkstra */
        /* ida */
        grafo.agregarVertice(0, 1, 16);
        grafo.agregarVertice(0, 2, 10);
        grafo.agregarVertice(0, 3, 5);
        /* vuelta */
        grafo.agregarVertice(1, 0, 16);
        grafo.agregarVertice(2, 0, 10);
        grafo.agregarVertice(3, 0, 5);

        /* ida */
        grafo.agregarVertice(1, 2, 2);
        grafo.agregarVertice(1, 5, 4);
        grafo.agregarVertice(1, 6, 6);
        /* vuelta */
        grafo.agregarVertice(2, 1, 2);
        grafo.agregarVertice(5, 1, 4);
        grafo.agregarVertice(6, 1, 6);

        /* ida */
        grafo.agregarVertice(2, 3, 4);
        grafo.agregarVertice(2, 4, 10);
        grafo.agregarVertice(2, 5, 12);
        /* vuelta */
        grafo.agregarVertice(3, 2, 4);
        grafo.agregarVertice(4, 2, 10);
        grafo.agregarVertice(5, 2, 12);

        /* ida */
        grafo.agregarVertice(3, 4, 15);
        /* vuelta */
        grafo.agregarVertice(4, 3, 15);

        /* ida */
        grafo.agregarVertice(4, 5, 3);
        grafo.agregarVertice(4, 7, 5);

        /* vuelta */
        grafo.agregarVertice(5, 4, 3);
        grafo.agregarVertice(7, 4, 5);

        /* ida */
        grafo.agregarVertice(5, 6, 8);
        grafo.agregarVertice(5, 7, 16);
        /* vuelta */
        grafo.agregarVertice(6, 5, 8);
        grafo.agregarVertice(7, 5, 16);

        /* ida */
        grafo.agregarVertice(6, 7, 7);
        /* vuelta */
        grafo.agregarVertice(7, 6, 7);

        Caminos caminos = new Dijkstra(grafo, 0, 7);

        assertEquals(23.00, caminos.distancia(7));
    }

}
