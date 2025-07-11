package business ;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import business.AdministradorInstancias.AppContext;
import domain.AuxIndicente;
import domain.AuxViaCongestionada;
import domain.Grafo;
import domain.NodoA;
import domain.NodoInterseccion;
import domain.NodoSimple;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class UIMainController {
	@FXML
	private GridPane gridPane;
	@FXML
	private ComboBox<Integer> cbSize;
	@FXML
	private TableView<AuxIndicente> tvIncidentesPosicion;
	@FXML
	private TableView<AuxViaCongestionada> tvCongetionVehicular;
	@FXML
	private Button btnGenerarEvento;
	@FXML
	private Button btnSolicitarTaxi;
	@FXML
	private Button btnAumentar;
	@FXML
	private Button btnDisminuir;
	@FXML
	private Button btnIniciar;
	@FXML
	private Button btnReanudar;
	@FXML
	private Button btnPausar;
	@FXML 
	private  TableColumn<AuxIndicente,String> tcPosicion;
	@FXML 
	private  TableColumn<AuxIndicente,String> tcIncidente;
	@FXML
	private TableColumn<AuxViaCongestionada,Integer> tcCantidadVehiculos;
	@FXML
	private TableColumn<AuxViaCongestionada,String> tcCalle;
	
	  private static final int GRID_SIZE = 25;
	  private static final int CELL_SIZE = 25; // puedes ajustar el tamaño visual

	  @FXML
	  private void initialize() {
	      loadTableViewIncidentes();
	      loadTableViewViasCongestionadas();
	      sizeSimulation();
	      
	  }

	    private void loadTableViewViasCongestionadas() {
	    	tcCalle = new TableColumn<AuxViaCongestionada, String>("Calle");
	    	tcCalle.setCellValueFactory(new PropertyValueFactory<>("calle"));
	    	tcCalle.setReorderable(false);
	    	tcCalle.setResizable(false);
			
	    	tcCantidadVehiculos = new TableColumn<AuxViaCongestionada, Integer>("Cantidad de Vehículos");
	    	tcCantidadVehiculos.setCellValueFactory(new PropertyValueFactory<>("cantidadVehiculos"));
	    	tcCantidadVehiculos.setReorderable(false);
	    	//tcCantidadVehiculos.setResizable(false);

			tvCongetionVehicular.getColumns().addAll(tcCalle, tcCantidadVehiculos);
		}
		private void loadTableViewIncidentes() {
			tcPosicion = new TableColumn<AuxIndicente, String>("Posición");
			tcPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));
			tcPosicion.setReorderable(false);
			tcPosicion.setResizable(false);
			
			tcIncidente = new TableColumn<AuxIndicente, String>("Indicentes");
			tcIncidente.setCellValueFactory(new PropertyValueFactory<>("incidente"));
			tcIncidente.setReorderable(false);
			tcIncidente.setResizable(false);
			
			tvIncidentesPosicion.getColumns().addAll(tcPosicion, tcIncidente);
			
		}
		public void generarCiudad(int size) {
	        gridPane.getChildren().clear();
	        gridPane.getColumnConstraints().clear();
	        gridPane.getRowConstraints().clear();

	        // Tamaño visual máximo que usará el GridPane (ajustado al StackPane que lo contiene)
	        double visualAreaSize = 600.0;

	        // Calcula el tamaño de cada celda para que quepa completamente en ese espacio
	        int cellSize = (int) (visualAreaSize / size);

	        // Crear las restricciones de columna y fila según el nuevo tamaño
	        for (int i = 0; i < size; i++) {
	            gridPane.getColumnConstraints().add(new ColumnConstraints(cellSize));
	            gridPane.getRowConstraints().add(new RowConstraints(cellSize));
	        }

	        int contadorNodos = 1;

	        for (int row = 0; row < size; row++) {
	            for (int col = 0; col < size; col++) {
	                Pane celda = new Pane();
	                celda.setPrefSize(cellSize, cellSize);

	                if (row % 6 == 0 || col % 6 == 0) {
	                    celda.getStyleClass().add("calle");

	                    numeroDestinoOrigen(celda, contadorNodos);
	                    contadorNodos++;
	                } else {
	                    celda.getStyleClass().add("bloque");
	                }

	                gridPane.add(celda, col, row);
	            }}
	        

	        // Opcional: eliminar separación visual entre celdas (para bordes uniformes)
	    gridPane.setHgap(0);
	    gridPane.setVgap(0);
	    gridPane.setPadding(new Insets(0));
	    System.out.println("Ciudad generada con " + gridPane.getChildren().size() + " nodos visibles.");
	    

	    }



	    private void sizeSimulation() {
	    	// Agregar opciones del combo: 3 a 7 cuadrantes
	        cbSize.getItems().addAll(3, 4, 5, 6, 7);
	      

	        cbSize.setValue(AppContext.cuadrantesSeleccionados);
	    

	     // Dibujar ciudad inicialmente con ese valor
	        generarCiudad(cbSize.getValue() * 6 + 1); // dibuja ciudad con ese tamaño
	        
	        cbSize.setOnAction(e -> {
	         
	        	 int cuadrantes = cbSize.getValue();
	             AppContext.cuadrantesSeleccionados = cuadrantes;
	             generarCiudad(cuadrantes * 6 + 1);
	        });
	    }

	    private void numeroDestinoOrigen(Pane celda, int numero) {
	    	Label lbl = new Label (String.valueOf(numero));
	    	 lbl.setStyle("-fx-font-size: 9px; -fx-text-fill: white;");

	    	    // Centrar el número en la celda
	    	    celda.setStyle("-fx-alignment: center;");
	    	    celda.getChildren().add(lbl);
	    }


	// Event Listener on Button[#btnGenerarEvento].onAction
	@FXML
	public void generarEvento(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnSolicitarTaxi].onAction
	@FXML
	public void solicitarTaxi(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/presentation/UISolicitud.fxml"));
			Parent root = loader.load();
			UISolicitudController controller = loader.getController();
			
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			
			stage.setOnCloseRequest(
					e->{
						System.out.println("cerrando ventana");
						controller.closeWindows();
					}
					);
		/*	Stage temp = (Stage)this.btnSolicitarTaxi.getScene().getWindow();	
			temp.close();*/
					
		}catch(Exception e) {
			
		}
	}
	// Event Listener on Button[#btnAumentar].onAction
	@FXML
	public void aumentarVehiculo(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnDisminuir].onAction
	@FXML
	public void disminuirVehiculo(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnIniciar].onAction
	@FXML
	public void iniciarSimulation(ActionEvent event) {
		
		    int size = AppContext.cuadrantesSeleccionados * 6 + 1;

		    // Generar el grafo (estructura de nodos y conexiones)
		    AdministradorInstancias.getLogicaGrafo().generarCiudad(size);

		    // Dibujar la ciudad en la interfaz (grid visual)
		    generarCiudad(size);

		    System.out.println("✅ Ciudad inicializada correctamente.");
		}
	

	// Event Listener on Button[#btnReanudar].onAction
	@FXML
	public void reanudarSimulation(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on Button[#btnPausar].onAction
	@FXML
	public void pasuarSimulation(ActionEvent event) {
		// TODO Autogenerated
	}
	
	public GridPane getGridPane() {
	    return gridPane;
	}

}