package domain;

public class NodoA {
    private Calle calle;
    private NodoA siguiente;

    public NodoA(Calle calle) {
        this.calle = calle;
        this.siguiente = null;
    }

    public Calle getCalle() {
        return calle;
    }

    public void setCalle(Calle calle) {
        this.calle = calle;
    }

    public NodoA getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoA siguiente) {
        this.siguiente = siguiente;
    }
}
