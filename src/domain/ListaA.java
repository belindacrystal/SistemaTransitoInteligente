package domain;
public class ListaA {

    private NodoA primero;
    private NodoA ultimo;

    public ListaA() {
        this.primero = null;
        this.ultimo = null;
    }

	public NodoA getPrimero() {
		return primero;
	}

	public void setPrimero(NodoA primero) {
		this.primero = primero;
	}

	public NodoA getUltimo() {
		return ultimo;
	}

	public void setUltimo(NodoA ultimo) {
		this.ultimo = ultimo;
	}
    
    
}