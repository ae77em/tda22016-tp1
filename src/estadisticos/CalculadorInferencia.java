package estadisticos;

import java.util.Vector;

public class CalculadorInferencia {

    Vector<Integer> contenedorDatos;
    Vector<Integer> selecciones = new Vector<Integer>();
    HeapMinimo heapMinimo = new HeapMinimo();
    HeapMaximo heapMaximo = new HeapMaximo();

    public CalculadorInferencia(Vector<Integer> a) {
        contenedorDatos = a;
    }

	
    public boolean calcularPorFuerzaBruta(int k, int valor) {
        System.out.println("Calculando Por Fuerza Bruta");

    	int cantidadDatos = contenedorDatos.size();
		
		boolean seEncontroElemento = false;
		
		int indice = 0;
		
		while (!seEncontroElemento) {

			int elementoActual = contenedorDatos.elementAt(indice);

			int menores = 0;

			for (int i = 0; i < cantidadDatos; i++) {
				if (i != indice) {//para que no analice el elemento actual
					int elementoAComparar = contenedorDatos.elementAt(i);
					//no se contemplan caso de valores repetidos
					if (elementoAComparar < elementoActual) {
						menores++;
					}
				}
			}
			//sumo uno a menores por que en el bucle evito hacer una comparacion consigo mismo
			seEncontroElemento = ((menores + 1) == k);// si se encontro el k-iesimo
			
			indice++;
		}

		//comparo el si k-iesimo es el elemento consultado, resto al indice la ultima iteracion del bucle
		return (contenedorDatos.elementAt(indice-1) == valor);
    }
    
    public boolean calcularPorOrdenarSeleccionar(Integer pos, Integer valor) {
        System.out.println("Calculando Por Ordenar y Seleccionar");

		mergeSort(0, contenedorDatos.size()-1);

		if (contenedorDatos.elementAt(pos-1) == valor) {
            return true;
        }

        return false;
    }

    private void mergeSort(int ini, int fin) {
		int medio = 0;
		
		if (fin - ini < 1)
			return;

		medio = (ini + fin) / 2;
		
		mergeSort(ini, medio);
		mergeSort(medio + 1, fin);

		merge(ini, medio, fin);
	}

	private void merge( int ini, int medio, int fin) {
		int i = 0;
		
		int iniIndex = ini;
		
		int indiceMedio = medio;
		
		int finIndex = fin;
		
		int indiceIzq = ini;
		
		int indiceDer = indiceMedio + 1;
		
		int indiceAux = 0;
		
		Vector<Integer> arrayOrdenado = new Vector<Integer>();

		arrayOrdenado.setSize(finIndex - iniIndex + 1);
		
		while (indiceIzq <= indiceMedio && indiceDer <= finIndex){
			if (contenedorDatos.elementAt(indiceIzq) < contenedorDatos.elementAt(indiceDer)){
				arrayOrdenado.set(indiceAux,contenedorDatos.elementAt(indiceIzq));
				
				indiceIzq++;
			}
			else{
				arrayOrdenado.set(indiceAux,contenedorDatos.elementAt(indiceDer));
				
				indiceDer++;
			}
			indiceAux++;
		}

		while (indiceIzq <= indiceMedio){
			arrayOrdenado.set(indiceAux,contenedorDatos.elementAt(indiceIzq));
			
			indiceAux++;
			
			indiceIzq++;
		}

		while (indiceDer <= finIndex){
			arrayOrdenado.set(indiceAux,contenedorDatos.elementAt(indiceDer));
			
			indiceAux++;
			
			indiceDer++;
		}
		indiceAux = 0;
		
		for (i = iniIndex; i <= finIndex; i++){
			contenedorDatos.set(i,arrayOrdenado.elementAt(indiceAux));
			
			indiceAux++;
		}		
	}

    public boolean calcularPorKSelecciones(Integer pos, Integer valor) {
        System.out.println("Calculando Por K Selecciones");

        selecciones.setSize(pos);
        
        Integer minimo = Integer.MAX_VALUE, indiceMin = 0;

        for (int i = 0; i < pos; i++) {
            for (int j = 0; j < contenedorDatos.size(); j++) {
                if (contenedorDatos.elementAt(j) < minimo) {
                    minimo = contenedorDatos.elementAt(j);
            
                    indiceMin = j;
                }
            }
            selecciones.set(i, minimo);
            
            contenedorDatos.removeElementAt(indiceMin);
            
            minimo = Integer.MAX_VALUE;
            
            indiceMin = 0;
        }

        return verificarPosPorSeleccion(pos - 1, valor);
    }
  
    public boolean calcularPorKHeapSort(int pos, int valor) {
        System.out.println("Calculando Por K-HeapSort");

        cargarDatosAlheapMinimo();
        
        return buscarEnHeapMinimo(pos,valor);
    }

    private boolean buscarEnHeapMinimo(Integer pos, Integer valor) {
        --pos;

        while (pos > 0) {
            heapMinimo.eliminarMin();
            --pos;
        }

        if (heapMinimo.obtenerMin() == valor) {
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

    public boolean calcularPorHeapSelect(int k, int valor) {
        System.out.println("Calculando Por HeapSelect");

        //cargo el heap con los primeros k valores del contfinor
        for (int i = 0; i < k; i++){
        	heapMaximo.agregar(contenedorDatos.elementAt(i));
        }
        
        //ahora voy intercambiando si es necesario, al ser un heap de maximo, siempre contfinra
        //los minimos valores del contenedor, y como maximo al K valor del contenedor
        for (int i = k; i < contenedorDatos.size(); i++){
        	if(heapMaximo.obtenerMax() > contenedorDatos.elementAt(i)){
        		heapMaximo.eliminarMax();
        		
        		heapMaximo.agregar(contenedorDatos.elementAt(i));
        	}
        }
        return heapMaximo.obtenerMax() == valor;
    }

    private void cargarDatosAlheapMinimo() {
        for (int i = 0; i < contenedorDatos.size(); i++) {
        	heapMinimo.agregar(contenedorDatos.elementAt(i));
        }
    }

    public boolean calcularPorQuickSelect(int pos, int valor) {
        System.out.println("Calculando Por QuickSelect");

        int valorEnPos = quickSelect(0, contenedorDatos.size() - 1, pos);

        if (valorEnPos == valor) {
            return true;
        }

        return false;
    }

    private int quickSelect(int ini, int fin, int pos) {
        if (ini == fin) {
            return contenedorDatos.elementAt(ini);
        }

        int q = partitionSelect(ini, fin);

        int k = q - ini + 1;

        if (pos == k) {
            return contenedorDatos.elementAt(q);
        } else if (pos < k) {
            return quickSelect(ini, q - 1, pos);
        } else {
            return quickSelect(q + 1, fin, pos - k);
        }
    }

    private int partitionSelect(int ini, int fin) {
        int x = contenedorDatos.elementAt(fin);

        int i = ini - 1;

        for (int j = ini; j <= (fin - 1); j++) {
            if (contenedorDatos.elementAt(j) <= x) {
                i = i + 1;
                intercambiar(i, j);
            }
        }
        intercambiar(i + 1, fin);

        return i + 1;
    }

    private void mostrar(int ini, int fin) {
        for (int i = ini; i <= fin; i++) {
            System.out.print(contenedorDatos.elementAt(i) + " ");
        }
        System.out.println(" ");
    }

    private void intercambiar(int i, int j) {
        Integer aux = contenedorDatos.elementAt(i);

        contenedorDatos.set(i, contenedorDatos.elementAt(j));

        contenedorDatos.set(j, aux);
    }

}
