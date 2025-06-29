package business;

import domain.Calle;
import domain.Grafo;
import domain.ListaSimple;
import domain.NodoA;
import domain.NodoInterseccion;
import domain.NodoSimple;

public class LogicListaSimple<T> {

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

    public ListaSimple<NodoInterseccion> calcularRuta(Grafo grafo, NodoInterseccion origen, NodoInterseccion destino, int tamanio) {
        ListaSimple<NodoInterseccion> nodos = grafo.getListaNodos();
        int totalNodos = contarNodos(nodos);

        int[] distancias = new int[totalNodos];
        boolean[] visitado = new boolean[totalNodos];
        NodoInterseccion[] anteriores = new NodoInterseccion[totalNodos];
        NodoInterseccion[] indexados = new NodoInterseccion[totalNodos];

        // Indexar nodos
        NodoSimple<NodoInterseccion> actual = nodos.getPrimero();
        int i = 0;
        while (actual != null) {
            indexados[i] = actual.getDato();
            distancias[i] = Integer.MAX_VALUE;
            visitado[i] = false;
            anteriores[i] = null;
            i++;
            actual = actual.getSiguiente();
        }

        int idxOrigen = buscarIndiceNodo(indexados, origen);
        int idxDestino = buscarIndiceNodo(indexados, destino);

        if (idxOrigen == -1 || idxDestino == -1) return null;

        distancias[idxOrigen] = 0;

        for (int count = 0; count < totalNodos - 1; count++) {
            int u = encontrarMinimo(distancias, visitado);
            if (u == -1) break;

            visitado[u] = true;
            NodoInterseccion nodoU = indexados[u];
            NodoA calle = nodoU.getListaA().getPrimero();

            while (calle != null) {
                Calle c = calle.getCalle();
                NodoInterseccion vecino = c.getNodoDestino();
                int v = buscarIndiceNodo(indexados, vecino);
                if (!visitado[v] && !c.isBloqueada()) {
                    int nuevaDistancia = distancias[u] + c.getPeso();
                    if (nuevaDistancia < distancias[v]) {
                        distancias[v] = nuevaDistancia;
                        anteriores[v] = nodoU;
                    }
                }
                calle = calle.getSiguiente();
            }
        }

        return reconstruirRuta(anteriores, idxOrigen, idxDestino, indexados);
    }



    private int buscarIndiceNodo(NodoInterseccion[] nodos, NodoInterseccion buscado) {
        for (int i = 0; i < nodos.length; i++) {
            if (nodos[i].getNombre() == buscado.getNombre()) {
                return i;
            }
        }
        return -1;
    }

    private ListaSimple<NodoInterseccion> reconstruirRuta(NodoInterseccion[] anteriores, int idxOrigen, int idxDestino, NodoInterseccion[] indexados) {
        ListaSimple<NodoInterseccion> ruta = new ListaSimple<>();
        NodoInterseccion actual = indexados[idxDestino];

        while (actual != null && actual.getNombre() != indexados[idxOrigen].getNombre()) {
            agregarInicio(ruta, actual);
            int i = buscarIndiceNodo(indexados, actual);
            actual = anteriores[i];
        }

        if (actual == null) return null;

        agregarInicio(ruta, indexados[idxOrigen]);

        System.out.print("🛣 Ruta reconstruida: ");
        NodoSimple<NodoInterseccion> paso = ruta.getPrimero();
        paso = ruta.getPrimero();
        while (paso != null) {
            System.out.print(paso.getDato().getNombre());
            paso = paso.getSiguiente();
            if (paso != null) {
                System.out.print(" → ");
            }
        }
        System.out.println(" → DESTINO ✅");
        return ruta;
    }

    private int contarNodos(ListaSimple<NodoInterseccion> lista) {
        int count = 0;
        NodoSimple<NodoInterseccion> actual = lista.getPrimero();
        while (actual != null) {
            count++;
            actual = actual.getSiguiente();
        }
        return count;
    }

    private int encontrarMinimo(int[] distancias, boolean[] visitado) {
        int indiceMin = -1;
        for (int i = 0; i < distancias.length; i++) {
            if (!visitado[i] && distancias[i] != Integer.MAX_VALUE) {
                if (indiceMin == -1 || distancias[i] < distancias[indiceMin]) {
                    indiceMin = i;
                }
            }
        }
        return indiceMin;
    }
}