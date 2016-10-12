package estadisticos;

/*
    Fuerza bruta:

    Primero se implementa un verificador que dado un conjunto y un candidato devuelve un booleano indicando si el
    valor indicado es el k elemento más pequeño.

    El algoritmo de fuerza bruta itera todos los elementos del conjunto y
    verifica de a uno si es la solución. Una vez el verificador devuelve true, devuelve ese elemento.
 */

import java.util.Vector;

public class FuerzaBruta {


    private Vector<Integer> conjunto;

    private int candidato;

    public FuerzaBruta(Vector<Integer> unConjunto, int unCandidato){
        conjunto = unConjunto;
        candidato = unCandidato;
    }

    public Integer verificar(int posicion){
        Integer aDevolver = null;

        if (esKEsimoMenor(posicion)){
            aDevolver = conjunto.get(posicion);
        }

        return aDevolver;
    }

    private boolean esKEsimoMenor(int k){

        int vecesMenor = 0;

        for(int nro : conjunto){
            if (candidato < nro){
                vecesMenor++;
            }
        }

        return (conjunto.size() - vecesMenor) == k; // devuelve true si es el k-menor elemento....
    }



}
