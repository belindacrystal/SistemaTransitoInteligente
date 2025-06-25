package domain;
public class ListaV {

    private NodoV primero;
    private NodoV ultimo;

    public ListaV() {
        this.primero = null;
        this.ultimo = null;
    }

	public NodoV getPrimero() {
		return primero;
	}

	public void setPrimero(NodoV primero) {
		this.primero = primero;
	}

	public NodoV getUltimo() {
		return ultimo;
	}

	public void setUltimo(NodoV ultimo) {
		this.ultimo = ultimo;
	}
    
    
}