package domain;

public class AuxViaCongestionada {
	  private String calle;
	    private int cantidadVehiculos;

	    public AuxViaCongestionada(String calle, int cantidadVehiculos) {
	        this.calle = calle;
	        this.cantidadVehiculos = cantidadVehiculos;
	    }

	    public String getCalle() {
	        return calle;
	    }

	    public void setCalle(String calle) {
	        this.calle = calle;
	    }

	    public int getCantidadVehiculos() {
	        return cantidadVehiculos;
	    }

	    public void setCantidadVehiculos(int cantidadVehiculos) {
	        this.cantidadVehiculos = cantidadVehiculos;
	    }

		@Override
		public String toString() {
			return "AuxViaCongestionada [calle=" + calle + ", cantidadVehiculos=" + cantidadVehiculos + "]";
		}
	    
	}
