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

        // Crear nodos en posiciones válidas (multiplo de 6 en fila o columna)
        for (int fila = 0; fila < size; fila++) {
            for (int col = 0; col < size; col++) {
                if (fila % 6 == 0 || col % 6 == 0) {
                    NodoInterseccion nodo = new NodoInterseccion(idVisual, fila, col);
                    matrizIDs[fila][col] = idVisual;
                    insertarNodo(lista, nodo);
                    idVisual++;
                } else {
                    matrizIDs[fila][col] = -1;
                }
            }
        }

        grafo.setListaNodos(lista);

        NodoSimple<NodoInterseccion> actual = lista.getPrimero();
        while (actual != null) {
            NodoInterseccion origen = actual.getDato();
            int fila = origen.getFila();
            int col = origen.getColumna();

            // Avenidas (horizontales)
            if (fila % 2 != 0) { // Oeste → Este
                for (int i = col + 1; i < size; i++) {
                    int destinoID = matrizIDs[fila][i];
                    if (destinoID != -1 && esConexionValida(fila, col, fila, i)) {
                        insertarArista(origen, buscarNodoPorId(lista, destinoID));
                        break;
                    }
                }
            } else { // Este → Oeste
                for (int i = col - 1; i >= 0; i--) {
                    int destinoID = matrizIDs[fila][i];
                    if (destinoID != -1 && esConexionValida(fila, col, fila, i)) {
                        insertarArista(origen, buscarNodoPorId(lista, destinoID));
                        break;
                    }
                }
            }

            // Calles (verticales)
            if (col % 2 == 0) { // Norte → Sur
                for (int i = fila + 1; i < size; i++) {
                    int destinoID = matrizIDs[i][col];
                    if (destinoID != -1 && esConexionValida(fila, col, i, col)) {
                        insertarArista(origen, buscarNodoPorId(lista, destinoID));
                        break;
                    }
                }
            } else { // Sur → Norte
                for (int i = fila - 1; i >= 0; i--) {
                    int destinoID = matrizIDs[i][col];
                    if (destinoID != -1 && esConexionValida(fila, col, i, col)) {
                        insertarArista(origen, buscarNodoPorId(lista, destinoID));
                        break;
                    }
                }
            }

            actual = actual.getSiguiente();
        }

        // Verificación visual de conexiones
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

    private boolean esConexionValida(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        if (filaOrigen == filaDestino) {
            if (filaOrigen % 2 == 0) {
                return colDestino < colOrigen; // Este → Oeste
            } else {
                return colDestino > colOrigen; // Oeste → Este
            }
        }

        if (colOrigen == colDestino) {
            if (colOrigen % 2 == 0) {
                return filaDestino > filaOrigen; // Norte → Sur
            } else {
                return filaDestino < filaOrigen; // Sur → Norte
            }
        }

        return false; // diagonales no válidas
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
        return (int)(Math.random() * 8) + 3; // peso entre 3 y 10
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

    private NodoInterseccion buscarNodoPorId(ListaSimple<NodoInterseccion> lista, int id) {
        NodoSimple<NodoInterseccion> actual = lista.getPrimero();
        while (actual != null) {
            if (actual.getDato().getNombre() == id) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
}
