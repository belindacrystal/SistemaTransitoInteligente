package domain;

public class Evento {
    private String tipo; // "choque", "congestion", "reparacion"
    private Calle calleAfectada;
    private int tiempoInicio;
    private int duracion;

    public Evento(String tipo, Calle calleAfectada, int tiempoInicio, int duracion) {
        this.tipo = tipo;
        this.calleAfectada = calleAfectada;
        this.tiempoInicio = tiempoInicio;
        this.duracion = duracion;
    }

    public String getTipo() {
        return tipo;
    }

    public Calle getCalleAfectada() {
        return calleAfectada;
    }

    public int getTiempoInicio() {
        return tiempoInicio;
    }

    public int getDuracion() {
        return duracion;
    }

    public boolean estaActivo(int tiempoActual) {
        return tiempoActual >= tiempoInicio && tiempoActual <= (tiempoInicio + duracion);
    }
}