package domain;

public class NodoV {
    private int vertice;
    private NodoA aristas;
    private NodoV siguiente;

    public NodoV(int vertice) {
        this.vertice = vertice;
        this.aristas = null; 
        this.siguiente = null;
    }

    public int getVertice() {
        return vertice;
    }

    public void setVertice(int vertice) {
        this.vertice = vertice;
    }

    public NodoA getAristas() {
        return aristas;
    }

    public void setAristas(NodoA aristas) {
        this.aristas = aristas;
    }

    public NodoV getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoV siguiente) {
        this.siguiente = siguiente;
    }
}