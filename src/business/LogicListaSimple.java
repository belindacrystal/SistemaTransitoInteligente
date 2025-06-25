package business;

import domain.ListaSimple;
import domain.NodoSimple;

public class LogicListaSimple<T> {

    // 1. Agregar (insertar al final)
    public void agregar(T dato, ListaSimple<T> lista) {
        NodoSimple<T> nuevoNodo = new NodoSimple<>(dato);

        if (lista.getPrimero() == null) {
            lista.setPrimero(nuevoNodo);
            lista.setUltimo(nuevoNodo);
        } else {
            lista.getUltimo().setSiguiente(nuevoNodo);
            lista.setUltimo(nuevoNodo);
        }
    }

    // 2. Buscar un elemento
    public boolean buscar(T dato, ListaSimple<T> lista) {
        NodoSimple<T> actual = lista.getPrimero();

        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }

    // 3. Recorrer e imprimir la lista
    public void recorrer(ListaSimple<T> lista) {
        NodoSimple<T> actual = lista.getPrimero();

        if (actual == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        System.out.print("Elementos en la lista: ");
        while (actual != null) {
            System.out.print(actual.getDato() + " -> ");
            actual = actual.getSiguiente();
        }
        System.out.println("null");
    }
}
