package estadisticos;

class Main {

    //path tipoMetodo k valor

    public static void main(String[] args) {

        int metodoElegido = Integer.parseInt(args[1]);
        int k = Integer.parseInt(args[2]);
        int valor = Integer.parseInt(args[3]);
        boolean ok = false;

        Parser p = new Parser(args[0]);
        p.parsear();

        CalculadorInferencia calculadorInferencia = new CalculadorInferencia(p.obtenerDatos());

        switch (metodoElegido) {
            case 1:
                ok = calculadorInferencia.calcularPorFuerzaBruta(k, valor);
                break;
            case 2:
                ok = calculadorInferencia.calcularPorOrdenarSeleccionar(k, valor);
                break;
            case 3:
                ok = calculadorInferencia.calcularPorKSelecciones(k, valor);
                break;
            case 4:
                ok = calculadorInferencia.calcularPorKSeleccionesEnHeap(k, valor);
                break;
            case 5:
                ok = calculadorInferencia.calcularPorHeapSelect(k, valor);
                break;
            case 6:
                ok = calculadorInferencia.calcularPorQuickSelect(k, valor);
                break;
        }

        if (ok) {
            System.out.println("El valor " + valor + " ocupa la posicion " + k);
        } else {
            System.out.println("El valor " + valor + " no ocupa la posicion " + k);
        }
    }
}
