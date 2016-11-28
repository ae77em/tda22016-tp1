package test;

import java.util.Stack;

import grafos.Arista;
import recorridos.BFSHeuristico;
import grafos.Grafo;
import heuristicas.Manhattan;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class BusquedaHeuristicaTest extends TestCase {

    Grafo grafoCuadrado;
    Grafo grafoOtro;
    BFSHeuristico buscadorHeuristicaCuadrado;
    BFSHeuristico buscadorHeuristicaOtro;

    public BusquedaHeuristicaTest(String testName) {
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

        buscadorHeuristicaCuadrado = new BFSHeuristico(grafoCuadrado, 0, 8, new Manhattan(), false);

        Grafo grafo = new Grafo(7);

        grafo.agregarVertice(0, 1, 1);
        grafo.agregarVertice(0, 2, 1);
        grafo.agregarVertice(1, 3, 1);
        grafo.agregarVertice(1, 4, 1);
        grafo.agregarVertice(0, 1, 1);
        grafo.agregarVertice(2, 5, 1);
        grafo.agregarVertice(2, 6, 1);

        buscadorHeuristicaOtro = new BFSHeuristico(grafoCuadrado, 0, 4, new Manhattan(), false);

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCuadrado() {
        assertEquals(4.0, buscadorHeuristicaCuadrado.distancia(8));
        assertEquals(3.0, buscadorHeuristicaCuadrado.distancia(7));
        assertEquals(2.0, buscadorHeuristicaCuadrado.distancia(2));
        assertEquals(0.0, buscadorHeuristicaCuadrado.distancia(0));
    }

    public void testOtro() {
        assertEquals(2.0, buscadorHeuristicaCuadrado.distancia(4));
        assertEquals(1.0, buscadorHeuristicaCuadrado.distancia(3));
        assertEquals(1.0, buscadorHeuristicaCuadrado.distancia(1));
        assertEquals(0.0, buscadorHeuristicaCuadrado.distancia(0));
    }

}
