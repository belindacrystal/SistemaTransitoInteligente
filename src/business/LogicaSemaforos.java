package business;

import domain.ListaSimple;
import domain.NodoInterseccion;
import domain.Semaforo;


public class LogicaSemaforos {
    // Lista de todos los nodos con semáforos
    private ListaSimple<NodoInterseccion> interseccionesControladas;
    private LogicListaSimple<NodoInterseccion> listaHelper;

    public LogicaSemaforos() {
        this.interseccionesControladas = new ListaSimple<>();
        this.listaHelper = new LogicListaSimple<>();
    }

    public void registrarInterseccion(NodoInterseccion interseccion) {
        listaHelper.agregar(interseccion, interseccionesControladas);
    }

    public void iniciarControlSemaforos() {
        // Lanza hilos o timers para cambiar semáforos periódicamente
    }

    public void cambiarEstadoSemaforos() {
        // Recorre todos los semáforos y cambia su estado
    }

    public void sincronizarSegunDireccion() {
        // Aplica lógica: pares vs impares (norte-sur, este-oeste)
    }

    public void notificarVehiculos(NodoInterseccion interseccion) {
        // Permite avanzar a los vehículos en espera si el semáforo cambia a verde
    }

    public Semaforo getSemaforo(NodoInterseccion interseccion) {
        return interseccion.getSemaforo();
    }
}
