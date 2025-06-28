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

     // Conectar nodos con sentido del tránsito
        NodoSimple<NodoInterseccion> origen = lista.getPrimero();
        while (origen != null) {
            NodoInterseccion nodoOrigen = origen.getDato();
            int fila = nodoOrigen.getFila();
            int col = nodoOrigen.getColumna();
            int idOrigen = nodoOrigen.getNombre();

            NodoSimple<NodoInterseccion> destino = lista.getPrimero();
            while (destino != null) {
                NodoInterseccion nodoDestino = destino.getDato();
                int filaDest = nodoDestino.getFila();
                int colDest = nodoDestino.getColumna();
                int idDestino = nodoDestino.getNombre();

                // Horizontal: Avenidas
                if (fila == filaDest) {
                    if (fila % 2 == 0 && colDest == col - 6) {
                        // Fila par → de Este a Oeste (→ conectar a la izquierda)
                    	insertarArista(nodoOrigen, nodoDestino);

                    } else if (fila % 2 != 0 && colDest == col + 6) {
                        // Fila impar → de Oeste a Este (← conectar a la derecha)
                    	insertarArista(nodoOrigen, nodoDestino);

                    }
                }

                // Vertical: Calles
                if (col == colDest) {
                    if (col % 2 == 0 && filaDest == fila + 6) {
                        // Col par → de Norte a Sur (↑ conectar hacia abajo)
                    	insertarArista(nodoOrigen, nodoDestino);

                    } else if (col % 2 != 0 && filaDest == fila - 6) {
                        // Col impar → de Sur a Norte (↓ conectar hacia arriba)
                    	insertarArista(nodoOrigen, nodoDestino);

                    }
                }

                destino = destino.getSiguiente();
            }

            origen = origen.getSiguiente();
        }
        
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
    private void conectarNodos(ListaSimple<NodoInterseccion> lista) {
        NodoSimple<NodoInterseccion> actual = lista.getPrimero();

        while (actual != null) {
            NodoSimple<NodoInterseccion> siguiente = lista.getPrimero();

            while (siguiente != null) {
                if (sonAdyacentes(actual.getDato(), siguiente.getDato())) {
                	int peso = (int) (Math.random() * 8) + 3; // número entre 3 y 10
                	Calle calle = new Calle(actual.getDato(), siguiente.getDato(), peso);
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

    private boolean esVecinoVertical(NodoInterseccion origen, NodoInterseccion destino) {
    	 // Misma columna
        if (origen.getColumna() != destino.getColumna()) return false;

        int diff = destino.getFila() - origen.getFila();

        // Columna impar: permite de SUR a NORTE (hacia arriba)
        if (origen.getColumna() % 2 != 0) {
            return diff == -6;
        }
        // Columna par: permite de NORTE a SUR (hacia abajo)
        else {
            return diff == 6;
        }
    }

    private boolean esVecinoHorizontal(NodoInterseccion origen, NodoInterseccion destino) {
    	  // Misma fila
        if (origen.getFila() != destino.getFila()) return false;

        int diff = destino.getColumna() - origen.getColumna();

        // Fila impar: permite de OESTE a ESTE (derecha)
        if (origen.getFila() % 2 != 0) {
            return diff == 6;
        }
        // Fila par: permite de ESTE a OESTE (izquierda)
        else {
            return diff == -6;
        }
    }
}
