package domain;

public class NodoInterseccion {
    private int nombre;
    private int fila;
    private int columna;
    private Semaforo semaforo;
    private ListaSimple<Vehiculo> colaVehiculos;

    private ListaA listaA; // ← Nueva lista de adyacencia (calles que salen de aquí)

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