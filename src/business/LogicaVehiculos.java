package business;

import domain.Calle;
import domain.Grafo;
import domain.ListaSimple;
import domain.NodoInterseccion;
import domain.NodoSimple;

public class LogicaVehiculos {
    public void crearVehiculo(String id, NodoInterseccion origen, NodoInterseccion destino) {
    	 Grafo grafo = AdministradorInstancias.getGrafo();
    	    LogicListaSimple<NodoInterseccion> logic = new LogicListaSimple<>();

    	    int size = AdministradorInstancias.AppContext.cuadrantesSeleccionados * 6 + 1;// porque esto
    	    
    	    ListaSimple<NodoInterseccion> ruta = logic.calcularRuta(grafo, origen, destino, size);

    	    if (ruta == null) {
    	        System.out.println("No se pudo calcular una ruta.");
    	        return;
    	    }

    	    System.out.println("Ruta del taxi:");
    	    NodoSimple<NodoInterseccion> actual = ruta.getPrimero();
    	    while (actual != null) {
    	        System.out.println(" → Nodo " + actual.getDato().getNombre());
    	        actual = actual.getSiguiente();
    	    }

	}
    
    

    public void iniciarSimulacion() {
	}
    public void detenerTodos() {
	}
    public void reanudarTodos() {
    	
    }
    public void actualizarEstadoCalle(Calle calle) {
	}
}
