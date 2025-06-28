package business;

import domain.Grafo;

public class AdministradorInstancias {

    private static Grafo grafo = null;
    private static LogicaGrafo logicaGrafo = null;

    public static Grafo getGrafo() {
        if (grafo == null) {
            grafo = new Grafo();
        }
        return grafo;
    }

    public static void reiniciarGrafo() {
        grafo = new Grafo();
    }

    public static LogicaGrafo getLogicaGrafo() {
        if (logicaGrafo == null) {
            logicaGrafo = new LogicaGrafo();
        }
        return logicaGrafo;
    }

    public static void reiniciarTodo() {
        grafo = new Grafo();
        logicaGrafo = new LogicaGrafo();
    }
    public class AppContext {
        public static int cuadrantesSeleccionados = 5; // valor por defecto
    }
}
