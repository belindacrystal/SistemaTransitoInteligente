package domain;

public class ListaSimple<T> {
    private NodoSimple<T> primero;
    private NodoSimple<T> ultimo;

    public NodoSimple<T> getPrimero() {
        return primero;
    }

    public void setPrimero(NodoSimple<T> primero) {
        this.primero = primero;
    }

    public NodoSimple<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoSimple<T> ultimo) {
        this.ultimo = ultimo;
    }
}
