package business;

import domain.Calle;
import domain.Grafo;
import domain.ListaSimple;
import domain.NodoA;
import domain.NodoInterseccion;
import domain.NodoSimple;

public class LogicaGrafo {

    private Grafo grafo;

    public LogicaGrafo() {
        this.grafo = AdministradorInstancias.getGrafo(); // Usa singleton
    }

    
    private void insertarCalleEnListaA(NodoInterseccion nodo, Calle calle) {
        NodoA nuevo = new NodoA(calle);

        if (nodo.getListaA().getPrimero() == null) {
            nodo.getListaA().setPrimero(nuevo);
            nodo.getListaA().setUltimo(nuevo);
        } else {
            nodo.getListaA().getUltimo().setSiguiente(nuevo);
            nodo.getListaA().setUltimo(nuevo);
        }
    }

    public void generarCiudad(int size) {
        grafo.limpiar(); // Reinicia el grafo

        ListaSimple<NodoInterseccion> lista = new ListaSimple<>();
        int id = 1;

        // Crear nodos en intersecciones reales
        for (int fila = 0; fila < size; fila++) {
            for (int col = 0; col < size; col++) {
                if (fila % 6 == 0 || col % 6 == 0) {
                    NodoInterseccion nodo = new NodoInterseccion(id, fila, col);
                    insertarNodo(lista, nodo);
                    id++;
                }
            }
        }

        // Guardar en el grafo
        grafo.setListaNodos(lista);

        // Conectar nodos
        conectarNodos(lista);
        
        System.out.println("Ciudad generada con los siguientes nodos:");

        NodoSimple<NodoInterseccion> actual = grafo.getListaNodos().getPrimero();
        while (actual != null) {
            NodoInterseccion nodo = actual.getDato();
            System.out.println("Nodo ID: " + nodo.getNombre() + " [Fila: " + nodo.getFila() + ", Columna: " + nodo.getColumna() + "]");

            NodoA calle = nodo.getListaA().getPrimero();
            while (calle != null) {
                Calle c = calle.getCalle();
                System.out.println("   → Conectado a nodo " + c.getNodoDestino().getNombre() + " con peso: " + c.getPeso());
                calle = calle.getSiguiente();
            }

            actual = actual.getSiguiente();
        }

    }

    private void insertarNodo(ListaSimple<NodoInterseccion> lista, NodoInterseccion nodo) {
        NodoSimple<NodoInterseccion> nuevo = new NodoSimple<>(nodo);
        if (lista.getPrimero() == null) {
            lista.setPrimero(nuevo);
            lista.setUltimo(nuevo);
        } else {
            lista.getUltimo().setSiguiente(nuevo);
            lista.setUltimo(nuevo);
        }
    }
    private void conectarNodos(ListaSimple<NodoInterseccion> lista) {
        NodoSimple<NodoInterseccion> actual = lista.getPrimero();

        while (actual != null) {
            NodoSimple<NodoInterseccion> siguiente = lista.getPrimero();

            while (siguiente != null) {
                if (sonAdyacentes(actual.getDato(), siguiente.getDato())) {
                    Calle calle = new Calle(actual.getDato(), siguiente.getDato(), 3);
                    insertarCalleEnListaA(actual.getDato(), calle);
                }
                siguiente = siguiente.getSiguiente();
            }

            actual = actual.getSiguiente();
        }
    }


    private boolean sonAdyacentes(NodoInterseccion a, NodoInterseccion b) {
        return esVecinoVertical(a, b) || esVecinoHorizontal(a, b);
    }

    private boolean esVecinoVertical(NodoInterseccion a, NodoInterseccion b) {
        return a.getColumna() == b.getColumna() &&
               (a.getFila() == b.getFila() + 6 || a.getFila() == b.getFila() - 6);
    }

    private boolean esVecinoHorizontal(NodoInterseccion a, NodoInterseccion b) {
        return a.getFila() == b.getFila() &&
               (a.getColumna() == b.getColumna() + 6 || a.getColumna() == b.getColumna() - 6);
    }
}
