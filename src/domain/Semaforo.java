package domain;

public class Semaforo {

private boolean enVerde;
private int duracionVerde; // en milisegundos
private int duracionRojo;  // en milisegundos

public Semaforo(int duracionVerde, int duracionRojo) {
    this.enVerde = true; // inicia en verde por defecto
    this.duracionVerde = duracionVerde;
    this.duracionRojo = duracionRojo;
}

public boolean isEnVerde() {
    return enVerde;
}

public void cambiarEstado() {
    enVerde = !enVerde;
}

public int getDuracionVerde() {
    return duracionVerde;
}

public void setDuracionVerde(int duracionVerde) {
    this.duracionVerde = duracionVerde;
}

public int getDuracionRojo() {
    return duracionRojo;
}

public void setDuracionRojo(int duracionRojo) {
    this.duracionRojo = duracionRojo;
}

@Override
public String toString() {
    if (enVerde) {
        return "Semáforo en VERDE";
    } else {
        return "Semáforo en ROJO";
    }
}
}