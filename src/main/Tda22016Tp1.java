package main;

import estadisticos.CalculadorInferencia;
import estadisticos.Heap;
import grafos.Digrafo;

import java.util.Vector;
import recorridos.Caminos;
import recorridos.Dijkstra;

public class Tda22016Tp1 {

    private static Vector<Integer> conjuntoDatos = new Vector<Integer>();

    private static void obtenerDatos(String rutaArchivo) {
        conjuntoDatos.add(2);
        conjuntoDatos.add(8);
        conjuntoDatos.add(7);
        conjuntoDatos.add(1);
        conjuntoDatos.add(3);
        conjuntoDatos.add(5);
        conjuntoDatos.add(6);
        conjuntoDatos.add(4);
    }

    private static void pruebaHeap() {
        Heap h = new Heap();
        h.agregar(30);
        h.agregar(40);
        h.agregar(27);
        h.agregar(19);
        h.agregar(50);
        h.agregar(7);
        h.agregar(67);
    }

    private static void pruebaInferencia() {
        obtenerDatos("path");
        CalculadorInferencia calculadorInferencia = new CalculadorInferencia(conjuntoDatos);

        System.out.println(calculadorInferencia.calcularPorFuerzaBruta(3, 3));
        //System.out.println(calculadorInferencia.calcularPorOrdenarSeleccionar(1,8));
        //System.out.println(calculadorInferencia.calcularPorKSelecciones(1,3));
        //System.out.println(calculadorInferencia.calcularPorKSeleccionesEnHeap(1,8));
        //System.out.println(calculadorInferencia.calcularPorHeapSelect(4,3));
        System.out.println(calculadorInferencia.calcularPorQuickSelect(2, 2));
    }

    public static void main(String[] args) {
//        pruebaHeap();
//        pruebaInferencia();

        pruebaDijkstra();
        pruebaDijkstra2();

    }

    private static void pruebaDijkstra() {

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

        Caminos caminos = new Dijkstra(grafo, 0, 4);

        System.out.println(caminos.camino(2));

    }

    private static void pruebaDijkstra2() {
        Digrafo grafo = new Digrafo(6);

        grafo.agregarArista(0, 1, 2);
        grafo.agregarArista(0, 2, 1);
        grafo.agregarArista(1, 3, 1);
        grafo.agregarArista(2, 3, 3);
        grafo.agregarArista(2, 4, 4);
        grafo.agregarArista(3, 5, 2);
        grafo.agregarArista(4, 5, 2);

        Caminos caminos = new Dijkstra(grafo, 0, 5);

        System.out.println(caminos.camino(5));
    }

}
