package recorridos;

import grafos.Arista;
import grafos.Digrafo;

public class AAsterisco extends Caminos {

    public AAsterisco(Digrafo g, int origin) {
        super(g, origin);
    }

    @Override
    public double distancia(int v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Arista edge_to(int v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
