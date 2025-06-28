package domain;


public class Grafo {

    private ListaSimple<NodoInterseccion> listaNodos;

    public Grafo() {
        this.listaNodos = new ListaSimple<>();
    }

    public ListaSimple<NodoInterseccion> getListaNodos() {
        return listaNodos;
    }

    public void setListaNodos(ListaSimple<NodoInterseccion> listaNodos) {
        this.listaNodos = listaNodos;
    }

    public void limpiar() {
        this.listaNodos = new ListaSimple<>();
    }

}
