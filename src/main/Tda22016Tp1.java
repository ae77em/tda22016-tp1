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
