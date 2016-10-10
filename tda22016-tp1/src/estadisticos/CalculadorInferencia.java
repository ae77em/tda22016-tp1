import java.util.Vector;

public class CalculadorInferencia {
	
	Vector<Integer> contenedorDatos;
	Vector<Integer> selecciones = new Vector<Integer>();
	Heap heap = new Heap();

	public CalculadorInferencia(Vector<Integer> a) {
		contenedorDatos = a;
	}
	
	private void ordenarPorSeleccion(){
		int i, j, minIndex, tmp;
	      int n = contenedorDatos.size();
	      for (i = 0; i < n - 1; i++) {
	            minIndex = i;
	            for (j = i + 1; j < n; j++)
	                  if (contenedorDatos.elementAt(j) < contenedorDatos.elementAt(minIndex))
	                        minIndex = j;
	            if (minIndex != i) {
	                  tmp = contenedorDatos.elementAt(i);
	                  
	                  contenedorDatos.set(i,contenedorDatos.elementAt(minIndex));
	                  contenedorDatos.set(minIndex,tmp);
	            }
	      }
	}
	
	private void ordenarPorBurbujeo(){
		int i, j, aux;
       
		for(i=0;i<contenedorDatos.size()-1;i++)
             for(j=0;j<contenedorDatos.size()-i-1;j++)
        
            	 if(contenedorDatos.elementAt(j+1)<contenedorDatos.elementAt(j)){
                     aux=contenedorDatos.elementAt(j+1);
	                 
                     contenedorDatos.set(j+1,contenedorDatos.elementAt(j));
	                 
                     contenedorDatos.set(j,aux);
                  }
	}


	public boolean calcularPorFuerzaBruta(Integer pos, Integer valor){
		ordenarPorSeleccion();
		
		System.out.println(contenedorDatos);

		return verificarPos(pos-1,valor);
	}
	
	private boolean verificarPos(Integer pos, Integer valor){
		if(contenedorDatos.elementAt(pos) == valor)
			return true;
		
		return false;
	}
	
	public boolean calcularPorOrdenarSeleccionar(Integer pos, Integer valor){
		ordenarPorBurbujeo();

		return verificarPos(pos-1,valor);
	}

	public boolean calcularPorKSelecciones(Integer pos, Integer valor){
		
		selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);selecciones.add(0);
		
		Integer minimo = 32767, indiceMin = 0;

		for (int i = 0; i < pos; i++) {
			for (int j = 0; j < contenedorDatos.size(); j++) {
				if(contenedorDatos.elementAt(j) < minimo){
					minimo = contenedorDatos.elementAt(j);
					indiceMin = j;
				}
			}
			selecciones.set(i,minimo);
			contenedorDatos.removeElementAt(indiceMin);
			minimo = 32767; indiceMin = 0;
		}
		
		
		return verificarPosPorSeleccion(pos-1,valor);
	}

	public boolean calcularPorKSeleccionesEnHeap(Integer pos, Integer valor){
		Integer minimo = 32767, indiceMin = 0;

		for (int i = 0; i < pos; i++) {
			for (int j = 0; j < contenedorDatos.size(); j++) {
				if(contenedorDatos.elementAt(j) < minimo){
					minimo = contenedorDatos.elementAt(j);
					indiceMin = j;
				}
			}
			heap.agregar(minimo);
			contenedorDatos.removeElementAt(indiceMin);
			minimo = 32767; indiceMin = 0;
		}
				
		if(	buscarRefEnHeap(pos,valor) )
			return true;
		
		return false;	
	}
		
	private boolean buscarRefEnHeap(Integer pos, Integer valor) {
		--pos;
		
		while(pos > 0){
			heap.eliminarMin();
			--pos;
		}
		
		if (heap.obtenerMin() == valor)
			return true;
		
		return false;
	}

	private boolean verificarPosPorSeleccion(Integer pos, Integer valor) {
		if(selecciones.elementAt(pos) == valor)
			return true;
		
		return false;
	}

	public boolean calcularPorHeapSelect(int pos, int valor) {
		heapSort();
		
		return buscarRefEnHeap(pos, valor);
	}

	private void heapSort() {
		for (int i = 0; i < contenedorDatos.size(); i++) {
			heap.agregar(contenedorDatos.elementAt(i));
		}
	}

	public boolean calcularPorQuickSelect(int pos, int valor) {
		int valorEnPos = quickSelect(0,contenedorDatos.size()-1,pos); 
						
		if(valorEnPos == valor)
			return true;

		return false;
	}

	private int quickSelect(int ini, int fin, int pos){
		if (ini == fin)
			return contenedorDatos.elementAt(ini);
		
		int q = partitionSelect(ini,fin);
		
		int k = q - ini + 1;
		
		if (pos == k)
			return contenedorDatos.elementAt(q);
		else if (pos < k)
			return quickSelect(ini,q-1, pos);
		else
			return quickSelect(q+1, fin, pos-k);
	}

	private int partitionSelect(int ini, int fin) {
		int x = contenedorDatos.elementAt(fin);

		int i = ini - 1;

		for (int j=ini; j <= (fin-1);j++){
			if (contenedorDatos.elementAt(j) <= x){
				i = i +1;
				intercambiar(i,j);
			}
		}
		intercambiar(i+1,fin);
		
		return i + 1;
	}

	private void mostrar(int ini,int fin)
	{
		for(int i=ini; i <= fin; i++){
			System.out.print(contenedorDatos.elementAt(i) + " ");
		}
		System.out.println(" ");
	}
	
	private void intercambiar(int i, int j) {
		Integer aux = contenedorDatos.elementAt(i);
		
		contenedorDatos.set(i,contenedorDatos.elementAt(j));
		
		contenedorDatos.set(j,aux);		
	}

}
