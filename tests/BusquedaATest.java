import java.util.Stack;

import grafos.AAsterisco;
import grafos.Arista;
import grafos.Grafo;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class BusquedaATest extends TestCase {
    Grafo grafoCuadrado;
    AAsterisco buscadorA;
    int origen;
    int destino;
    
    public BusquedaATest(String testName) {
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
        
        buscadorA = new AAsterisco(grafoCuadrado, origen, destino, new Manhattan());
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testDistancia() {
        assertEquals(4.0, buscadorA.distancia(destino));
        assertEquals(3.0, buscadorA.distancia(7));
        assertEquals(20.0, buscadorA.distancia(2));
        assertEquals(0.0, buscadorA.distancia(origen));
    }

    public void testEdge_to() {
        Stack<Arista> camino = (Stack<Arista>) buscadorA.camino();
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

    private static void pruebaAAsterisco() throws Exception {
        Grafo grafo = new Grafo(6);

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
    
}
