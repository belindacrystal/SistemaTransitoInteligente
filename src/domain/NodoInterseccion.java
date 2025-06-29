package domain;

public class NodoInterseccion {
    public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public void setColaVehiculos(ListaSimple<Vehiculo> colaVehiculos) {
		this.colaVehiculos = colaVehiculos;
	}

	public void setListaA(ListaA listaA) {
		this.listaA = listaA;
	}

	private int nombre;
    private int fila;
    private int columna;
    private Semaforo semaforo;
    private ListaSimple<Vehiculo> colaVehiculos;

    private ListaA listaA;

    public NodoInterseccion(int nombre, int fila, int columna) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;
        this.colaVehiculos = new ListaSimple<>();
        this.listaA = new ListaA(); 
    }

    public int getNombre() {
        return nombre;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public Semaforo getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public ListaSimple<Vehiculo> getColaVehiculos() {
        return colaVehiculos;
    }

    public ListaA getListaA() {
        return listaA;
    }
}