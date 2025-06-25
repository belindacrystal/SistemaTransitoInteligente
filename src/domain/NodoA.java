package domain;
public class NodoA {
    int adyacente;
    NodoA siguiente;
    public NodoA(int adyacente) {
        this.adyacente = adyacente;
        this.siguiente = null;
    }

    public int getAdyacente() {
        return adyacente;
    }

    public void setAdyacente(int adyacente) {
        this.adyacente = adyacente;
    }

    public NodoA getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoA siguiente) {
        this.siguiente = siguiente;
    }
}