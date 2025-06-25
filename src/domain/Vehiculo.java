package domain;

public class Vehiculo {
    private String id;
    private NodoInterseccion origen;
    private NodoInterseccion destino;
    private NodoInterseccion posicionActual;
    private Calle[] ruta;
    private boolean enMovimiento;

    public Vehiculo(String id, NodoInterseccion origen, NodoInterseccion destino, Calle[] ruta) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.ruta = ruta;
        this.posicionActual = origen;
        this.enMovimiento = true;
    }

    public String getId() {
        return id;
    }

    public NodoInterseccion getOrigen() {
        return origen;
    }

    public NodoInterseccion getDestino() {
        return destino;
    }

    public NodoInterseccion getPosicionActual() {
        return posicionActual;
    }

    public void setPosicionActual(NodoInterseccion posicionActual) {
        this.posicionActual = posicionActual;
    }

    public Calle[] getRuta() {
        return ruta;
    }

    public void setRuta(Calle[] ruta) {
        this.ruta = ruta;
    }

    public boolean isEnMovimiento() {
        return enMovimiento;
    }

    public void setEnMovimiento(boolean enMovimiento) {
        this.enMovimiento = enMovimiento;
    }
} 