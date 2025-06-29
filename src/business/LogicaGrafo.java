package business;

import domain.Calle;
import domain.Grafo;
import domain.ListaSimple;
import domain.NodoA;
import domain.NodoInterseccion;
import domain.NodoSimple;

public class LogicaGrafo {

    private Grafo grafo;
    private int[][] matrizIDs;

    public LogicaGrafo() {
        this.grafo = AdministradorInstancias.getGrafo(); 
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
        grafo.limpiar();

        ListaSimple<NodoInterseccion> lista = new ListaSimple<>();
        matrizIDs = new int[size][size];

        int idVisual = 1;

        for (int fila = 0; fila < size; fila++) {
            for (int col = 0; col < size; col++) {
                if (fila % 6 == 0 || col % 6 == 0) {
                    NodoInterseccion nodo = new NodoInterseccion(idVisual, fila, col);
                    matrizIDs[fila][col] = idVisual;
                    insertarNodo(lista, nodo);
                    idVisual++;
                } else {
                    matrizIDs[fila][col] = -1; // no hay nodo en esta posición
                }
            }
        }

        grafo.setListaNodos(lista);

        NodoSimple<NodoInterseccion> actual = lista.getPrimero();
        while (actual != null) {
            NodoInterseccion origen = actual.getDato();
            int fila = origen.getFila();
            int col = origen.getColumna();

            NodoSimple<NodoInterseccion> candidato = lista.getPrimero();
            while (candidato != null) {
                NodoInterseccion destino = candidato.getDato();
                if (origen.getNombre() != destino.getNombre()) {

                	// Avenidas: misma fila
                	if (fila == destino.getFila()) {
                	    if (fila % 2 != 0 && destino.getColumna() == col + 6) { // impar: Oeste → Este
                	        insertarArista(origen, destino);
                	    } else if (fila % 2 == 0 && destino.getColumna() == col - 6) { // par: Este → Oeste
                	        insertarArista(origen, destino);
                	    }
                	}

                	// Calles: misma columna
                	if (col == destino.getColumna()) {
                	    if (col % 2 == 0 && destino.getFila() == fila + 6) { // par: Norte → Sur
                	        insertarArista(origen, destino);
                	    } else if (col % 2 != 0 && destino.getFila() == fila - 6) { // impar: Sur → Norte
                	        insertarArista(origen, destino);
                	    }
                	}

                }
                candidato = candidato.getSiguiente();
            }

            actual = actual.getSiguiente();
        }

        System.out.println("🔍 Verificando conexiones:");
        NodoSimple<NodoInterseccion> verificador = lista.getPrimero();
        while (verificador != null) {
            NodoInterseccion nodo = verificador.getDato();
            System.out.println("Nodo ID: " + nodo.getNombre());
            NodoA calle = nodo.getListaA().getPrimero();
            while (calle != null) {
                System.out.println("  → Conectado a: " + calle.getCalle().getNodoDestino().getNombre());
                calle = calle.getSiguiente();
            }
            verificador = verificador.getSiguiente();
        }
    }

    public int getIdNodo(int fila, int col) {
        return matrizIDs[fila][col];
    }

    private void insertarArista(NodoInterseccion origen, NodoInterseccion destino) {
        int peso = pesoAleatorio();
        Calle calle = new Calle(origen, destino, peso);
        insertarCalleEnListaA(origen, calle);
    }

    private int pesoAleatorio() {
        return (int)(Math.random() * 8) + 3; // entre 3 y 10
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
}
