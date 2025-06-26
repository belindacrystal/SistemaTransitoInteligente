package domain;

public class AuxIndicente {
	private String posicion;
    private String incidente;

    public AuxIndicente(String posicion, String incidente) {
        this.posicion = posicion;
        this.incidente = incidente;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getIncidente() {
        return incidente;
    }

    public void setIncidente(String incidente) {
        this.incidente = incidente;
    }

	@Override
	public String toString() {
		return "AuxIndicente [posicion=" + posicion + ", incidente=" + incidente + "]";
	}
    

}
