package domain;


public class Calle {
    private NodoInterseccion nodoOrigen;
    private NodoInterseccion nodoDestino;
    private int peso;
    private boolean bloqueada;
    private boolean congestionada;
    private boolean enReparacion;
    private ListaSimple<Vehiculo> vehiculosEnCalle;

    public Calle(NodoInterseccion nodoOrigen, NodoInterseccion nodoDestino, int peso) {
        this.nodoOrigen = nodoOrigen;
        this.nodoDestino = nodoDestino;
        this.peso = peso;
        this.bloqueada = false;
        this.congestionada = false;
        this.enReparacion = false;
        this.vehiculosEnCalle = new ListaSimple<>();
    }

    public NodoInterseccion getNodoOrigen() {
        return nodoOrigen;
    }

    public NodoInterseccion getNodoDestino() {
        return nodoDestino;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    public boolean isCongestionada() {
        return congestionada;
    }

    public void setCongestionada(boolean congestionada) {
        this.congestionada = congestionada;
    }

    public boolean isEnReparacion() {
        return enReparacion;
    }

    public void setEnReparacion(boolean enReparacion) {
        this.enReparacion = enReparacion;
    }

    public ListaSimple<Vehiculo> getVehiculosEnCalle() {
        return vehiculosEnCalle;
    }
}
