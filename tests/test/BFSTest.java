package test;

import recorridos.BFS;
import grafos.Grafo;
import junit.framework.TestCase;

public class BFSTest extends TestCase {

    Grafo grafoCuadrado;
    Grafo otroGrafo;
    BFS bfsCuadrado;
    BFS otroBFS;

    public BFSTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

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

        bfsCuadrado = new BFS(grafoCuadrado, 0, 8);

        otroGrafo = new Grafo(7);

        otroGrafo.agregarVertice(0, 1, 1);
        otroGrafo.agregarVertice(0, 2, 1);
        otroGrafo.agregarVertice(1, 3, 1);
        otroGrafo.agregarVertice(1, 4, 1);
        otroGrafo.agregarVertice(0, 1, 1);
        otroGrafo.agregarVertice(2, 5, 1);
        otroGrafo.agregarVertice(2, 6, 1);

        otroBFS = new BFS(otroGrafo, 0, 4);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testBFSCuadrado() {
        assertEquals(4.0, bfsCuadrado.distancia(8));
        assertEquals(3.0, bfsCuadrado.distancia(7));
        assertEquals(2.0, bfsCuadrado.distancia(2));
        assertEquals(0.0, bfsCuadrado.distancia(0));
    }

    public void testBFSOtro() {
        assertEquals(2.0, otroBFS.distancia(4));
        assertEquals(2.0, otroBFS.distancia(3));
        assertEquals(1.0, otroBFS.distancia(2));
        assertEquals(0.0, otroBFS.distancia(0));
    }
}
