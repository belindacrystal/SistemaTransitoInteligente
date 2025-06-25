package business;

import domain.Calle;
import domain.Grafo;
import domain.ListaSimple;
import domain.NodoA;
import domain.NodoInterseccion;
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
    
    
    // Agregar al inicio de la lista (usado por Dijkstra)
    public void agregarInicio(ListaSimple<NodoInterseccion> lista, NodoInterseccion dato) {
        NodoSimple<NodoInterseccion> nuevo = new NodoSimple<>(dato);
        nuevo.setSiguiente(lista.getPrimero());
        if (lista.getPrimero() == null) {
            lista.setUltimo(nuevo);
        }
        lista.setPrimero(nuevo);
    }
    
    public NodoInterseccion buscarNodoPorId(ListaSimple<NodoInterseccion> lista, int id) {
        NodoSimple<NodoInterseccion> actual = lista.getPrimero();

        while (actual != null) {
            if (actual.getDato().getNombre() == id) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }

        return null;
    }

    // 4. Algoritmo de Dijkstra adaptado
    public ListaSimple<NodoInterseccion> calcularRuta(Grafo grafo, NodoInterseccion origen, NodoInterseccion destino, int tamanio) {
        int totalNodos = tamanio * tamanio;
        int[] distancias = new int[totalNodos];
        boolean[] visitado = new boolean[totalNodos];
        NodoInterseccion[] anteriores = new NodoInterseccion[totalNodos];

        for (int i = 0; i < totalNodos; i++) {
            distancias[i] = -1;
            visitado[i] = false;
        }

        distancias[origen.getNombre()] = 0;

        for (int count = 0; count < totalNodos - 1; count++) {
            int u = encontrarMinimo(distancias, visitado);
            if (u == -1) break;

            visitado[u] = true;
            NodoInterseccion nodoU = buscarNodoPorId(grafo.getListaNodos(), u);
            if (nodoU == null) continue;

            NodoA actualCalle = nodoU.getListaA().getPrimero();
            while (actualCalle != null) {
                Calle calle = actualCalle.getCalle();
                int v = calle.getNodoDestino().getNombre();

                if (!visitado[v] && !calle.isBloqueada()) {
                    int nuevaDistancia = distancias[u] + calle.getPeso();

                    if (distancias[v] == -1 || nuevaDistancia < distancias[v]) {
                        distancias[v] = nuevaDistancia;
                        anteriores[v] = nodoU;
                    }
                }

                actualCalle = actualCalle.getSiguiente();
            }
        }

        return reconstruirRuta(anteriores, origen, destino);
    }

    // Método para reconstruir la ruta desde destino hasta origen
    private ListaSimple<NodoInterseccion> reconstruirRuta(NodoInterseccion[] anteriores, NodoInterseccion origen, NodoInterseccion destino) {
        ListaSimple<NodoInterseccion> ruta = new ListaSimple<>();
        NodoInterseccion actual = destino;

        while (actual != null && !actual.equals(origen)) {
            agregarInicio(ruta, actual);
            actual = anteriores[actual.getNombre()];
        }

        if (actual == null) return null;

        agregarInicio(ruta, origen);
        return ruta;
    }

  

    private int encontrarMinimo(int[] distancias, boolean[] visitado) {
        int indiceMin = -1;

        for (int i = 0; i < distancias.length; i++) {
            if (!visitado[i] && distancias[i] != -1) {
                if (indiceMin == -1 || distancias[i] < distancias[indiceMin]) {
                    indiceMin = i;
                }
            }
        }

        return indiceMin;
    }

  
}
