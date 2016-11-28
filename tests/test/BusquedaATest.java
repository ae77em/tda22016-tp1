package test;

import recorridos.AAsterisco;
import grafos.Grafo;
import heuristicas.Manhattan;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;

public class BusquedaATest extends TestCase {

    Grafo grafoCuadrado;
    Grafo grafoOtro;
    AAsterisco aasteriscoCuadrado;
    AAsterisco aasteriscoOtro;

    public BusquedaATest(String testName) {
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

        aasteriscoCuadrado = new AAsterisco(grafoCuadrado, 0, 8, new Manhattan());

        grafoOtro = new Grafo(6);

        /* tomado de https://www.youtube.com/watch?v=VENf0GXRd6E */
        grafoOtro.agregarVertice(0, 1, 2);
        grafoOtro.agregarVertice(0, 2, 1);
        /* agrego arista que va directamente desde el origen al destino,
         pero que pesa mas que otra adyacente... */
        grafoOtro.agregarVertice(0, 5, 2);
        //----------
        grafoOtro.agregarVertice(1, 3, 1);
        grafoOtro.agregarVertice(2, 3, 3);
        grafoOtro.agregarVertice(2, 4, 4);
        grafoOtro.agregarVertice(3, 5, 2);
        grafoOtro.agregarVertice(4, 5, 2);

        aasteriscoOtro = new AAsterisco(grafoOtro, 0, 5, new Manhattan());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCuadrado() {
        assertEquals(4.0, aasteriscoCuadrado.distancia(8));
        assertEquals(3.0, aasteriscoCuadrado.distancia(7));
        assertEquals(20.0, aasteriscoCuadrado.distancia(2));
        assertEquals(0.0, aasteriscoCuadrado.distancia(0));
    }

    public void testOtro() {
        assertEquals(2.0, aasteriscoOtro.distancia(5));
        //assertEquals(3.0, aasteriscoCuadrado.distancia(7));
        //assertEquals(20.0, aasteriscoCuadrado.distancia(2));
        assertEquals(0.0, aasteriscoOtro.distancia(0));
    }
}
