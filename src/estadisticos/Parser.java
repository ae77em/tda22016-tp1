package estadisticos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Parser {

    String path;
    Vector<Integer> datosParseados;
    BufferedReader bf;

    public Parser(String path) {
        this.path = path;

        datosParseados = new Vector<Integer>();

        bf = null;
    }

    public void parsear() {
        abrirArchivo();

        extraerDatos();
    }

    private void extraerDatos() {
        String sCadena;

        try {
            while ((sCadena = bf.readLine()) != null) {
                pasarVecAInt(sCadena.split(","));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void pasarVecAInt(String[] split) {
        for (String nro : split) {
            datosParseados.add(Integer.parseInt(nro));
        }
    }

    private void abrirArchivo() {
        try {
            bf = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Vector<Integer> obtenerDatos() {
        return datosParseados;
    }
}
