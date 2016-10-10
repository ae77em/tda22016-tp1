package tda22016.tp1;

import estadisticos.CalculadorInferencia;
import estadisticos.Heap;
import java.util.Vector;

public class Tda22016Tp1 {

    private static Vector<Integer> conjuntoDatos = new Vector<Integer>();

    private static void obtenerDatos(String rutaArchivo) {
        conjuntoDatos.add(6);
        conjuntoDatos.add(8);
        conjuntoDatos.add(1);
        conjuntoDatos.add(3);
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
        System.out.println(calculadorInferencia.calcularPorKSeleccionesEnHeap(4, 3));
    }

    public static void main(String[] args) {
        pruebaHeap();
        pruebaInferencia();
    }

}
