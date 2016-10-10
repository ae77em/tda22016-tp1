package estadisticos;

import java.util.Vector;

public class CalculadorInferencia {

    Vector<Integer> arr;
    Vector<Integer> selecciones = new Vector<Integer>();

    public CalculadorInferencia(Vector<Integer> a) {
        arr = a;
    }

    private void ordenarPorSeleccion() {
        int i, j, minIndex, tmp;
        int n = arr.size();
        for (i = 0; i < n - 1; i++) {
            minIndex = i;
            for (j = i + 1; j < n; j++) {
                if (arr.elementAt(j) < arr.elementAt(minIndex)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                tmp = arr.elementAt(i);

                arr.set(i, arr.elementAt(minIndex));
                arr.set(minIndex, tmp);
            }
        }
    }

    private void ordenarPorBurbujeo() {
        int i, j, aux;

        for (i = 0; i < arr.size() - 1; i++) {
            for (j = 0; j < arr.size() - i - 1; j++) {
                if (arr.elementAt(j + 1) < arr.elementAt(j)) {
                    aux = arr.elementAt(j + 1);

                    arr.set(j + 1, arr.elementAt(j));

                    arr.set(j, aux);
                }
            }
        }
    }

    public boolean calcularPorFuerzaBruta(Integer pos, Integer valor) {
        ordenarPorSeleccion();

        System.out.println(arr);

        return verificarPos(pos - 1, valor);
    }

    private boolean verificarPos(Integer pos, Integer valor) {
        if (arr.elementAt(pos) == valor) {
            return true;
        }

        return false;
    }

    public boolean calcularPorOrdenarSeleccionar(Integer pos, Integer valor) {
        System.out.println(arr);

        ordenarPorBurbujeo();

        System.out.println(arr);

        return verificarPos(pos - 1, valor);
    }

    public boolean calcularPorKSelecciones(Integer pos, Integer valor) {

        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);
        selecciones.add(0);

        Integer minimo = 32767, indiceMin = 0;

        for (int i = 0; i < pos; i++) {
            for (int j = 0; j < arr.size(); j++) {
                if (arr.elementAt(j) < minimo) {
                    minimo = arr.elementAt(j);
                    indiceMin = j;
                }
            }
            selecciones.set(i, minimo);
            arr.removeElementAt(indiceMin);
            minimo = 32767;
            indiceMin = 0;
        }

        return verificarPosPorSeleccion(pos - 1, valor);
    }

    public boolean calcularPorKSeleccionesEnHeap(Integer pos, Integer valor) {
        Integer minimo = 32767, indiceMin = 0;
        Heap heap = new Heap();

        for (int i = 0; i < pos; i++) {
            for (int j = 0; j < arr.size(); j++) {
                if (arr.elementAt(j) < minimo) {
                    minimo = arr.elementAt(j);
                    indiceMin = j;
                }
            }
            heap.inserta(minimo);
            arr.removeElementAt(indiceMin);
            minimo = 32767;
            indiceMin = 0;
        }

        System.out.println("Cabeza Heap: " + heap.obtenerMax());

        if (heap.obtenerMax() == valor) {
            return true;
        }

        return false;
    }

    private boolean verificarPosPorSeleccion(Integer pos, Integer valor) {
        if (selecciones.elementAt(pos) == valor) {
            return true;
        }

        return false;
    }

}
