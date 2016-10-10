package estadisticos;

import java.util.*;
import java.security.*;

public class Heap {

    Vector<Integer> heap;

    public Heap() {
        heap = new Vector<Integer>();
    }

    public void agregar(Integer elem) {
        heap.add(elem);

        if (heap.size() > 1) {
            Integer posNuevoElem = heap.size() - 1;

            buscarLugar(elem, posNuevoElem);
        }

        mostrar();
    }

    public Integer obtenerMax() {
        return heap.elementAt(0);
    }

    public void eliminarMax() {
        heap.set(0, heap.elementAt(heap.size() - 1));

        heap.removeElementAt(heap.size() - 1);

        reordenar();
    }

    private void reordenar() {

    }

    private void mostrar() {
        for (int i = 0; i < heap.size(); i++) {
            System.out.print(heap.elementAt(i) + " ");
        }
        System.out.println(" ");
    }

    private void buscarLugar(Integer elem, Integer posNuevoElem) {
        Integer indPadre = obtenerIndicePadre(posNuevoElem);

        if (heap.elementAt(indPadre) < heap.elementAt(posNuevoElem)) {
            intercambiar(indPadre, posNuevoElem);

            if (indPadre != 0) {
                buscarLugar(elem, indPadre);
            }
        }
    }

    private void intercambiar(Integer indPadre, Integer indHijo) {
        Integer aux = heap.elementAt(indPadre);

        heap.set(indPadre, heap.elementAt(indHijo));

        heap.set(indHijo, aux);
    }

    private Integer obtenerIndicePadre(Integer indHijo) {
        Integer indPadre;

        if (indHijo % 2 == 0) {
            indPadre = indHijo / 2 - 1;
        } else {
            indPadre = indHijo / 2;
        }
        return indPadre;
    }

    void inserta(Integer minimo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
