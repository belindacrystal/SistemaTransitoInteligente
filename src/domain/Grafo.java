package domain;

public class Grafo {
    private NodoInterseccion[] nodos;
    private Calle[][] matrizCalles;

    public Grafo(int tamanio) {
        nodos = new NodoInterseccion[tamanio * tamanio];
        matrizCalles = new Calle[tamanio * tamanio][tamanio * tamanio];
    }

    public void agregarNodo(int posicion, NodoInterseccion nodo) {
        nodos[posicion] = nodo;
    }

    public void agregarCalle(int origen, int destino, Calle calle) {
        matrizCalles[origen][destino] = calle;
    }

    public NodoInterseccion getNodo(int posicion) {
        return nodos[posicion];
    }

    public Calle getCalle(int origen, int destino) {
        return matrizCalles[origen][destino];
    }
}

