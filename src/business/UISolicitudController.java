package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import business.AdministradorInstancias.AppContext;
import domain.Calle;
import domain.Grafo;
import domain.ListaSimple;
import domain.NodoA;
import domain.NodoInterseccion;
import domain.NodoSimple;
import javafx.event.ActionEvent;

public class UISolicitudController {
	@FXML
	private Button btnSolicitar;
	@FXML
	private Button btnCerrar;
	@FXML
	private TextField tfDestino;
	@FXML
	private TextField tfOrigen;
	@FXML
	private TextField tfCantidadCarros; 

	// Event Listener on Button[#btnSolicitar].onAction
	@FXML
	public void solicitar(ActionEvent event) {
		  try {
		        int origenId = Integer.parseInt(tfOrigen.getText());
		        int destinoId = Integer.parseInt(tfDestino.getText());

		        Grafo grafo = AdministradorInstancias.getGrafo();
		        LogicListaSimple<NodoInterseccion> logic = new LogicListaSimple<>();

		        // 🔍 DEBUG: Imprimir nodos y sus conexiones
		        System.out.println("========== NODOS EN EL GRAFO ==========");
		        NodoSimple<NodoInterseccion> actual = grafo.getListaNodos().getPrimero();
		        while (actual != null) {
		            NodoInterseccion nodo = actual.getDato();
		            System.out.println("ID: " + nodo.getNombre() + " → fila: " + nodo.getFila() + ", col: " + nodo.getColumna());

		            NodoA conexiones = nodo.getListaA().getPrimero();
		            while (conexiones != null) {
		                Calle calle = conexiones.getCalle();
		                System.out.println("   ↳ Conectado a: " + calle.getNodoDestino().getNombre() + " (peso: " + calle.getPeso() + ")");
		                conexiones = conexiones.getSiguiente();
		            }

		            actual = actual.getSiguiente();
		        }

		        // Buscar nodos de origen y destino
		        NodoInterseccion origen = logic.buscarNodoPorId(grafo.getListaNodos(), origenId);
		        NodoInterseccion destino = logic.buscarNodoPorId(grafo.getListaNodos(), destinoId);

		        if (origen == null || destino == null) {
		            System.out.println("❌ Origen o Destino no válidos.");
		            return;
		        }

		        System.out.println("✅ Origen encontrado: " + origen.getNombre());
		        System.out.println("✅ Destino encontrado: " + destino.getNombre());

		        ListaSimple<NodoInterseccion> ruta = logic.calcularRuta(grafo, origen, destino, AppContext.cuadrantesSeleccionados * 6 + 1);

		        if (ruta == null) {
		            System.out.println("❌ No se encontró ruta.");
		        } else {
		            System.out.print("🛣 Ruta calculada: ");
		            NodoSimple<NodoInterseccion> paso = ruta.getPrimero();
		            while (paso != null) {
		                System.out.print(paso.getDato().getNombre() + " → ");
		                paso = paso.getSiguiente();
		            }
		            System.out.println("DESTINO");
		        }

		    } catch (NumberFormatException e) {
		        System.out.println("⚠ Entrada inválida en los campos.");
		    }
		}
	@FXML
	public void cerrarVentana(ActionEvent event) {
		closeWindows();
	}

	public void closeWindows() {
		 Stage stage = (Stage) btnCerrar.getScene().getWindow();
		    stage.close();
		/*try {
			FXMLLoader loader = new FXMLLoader(
				getClass().getResource("/presentation/UIMain.fxml"));
		    Parent root = loader.load();
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("/business/application.css").toExternalForm());


		    Stage stage = new Stage();
		    stage.setScene(scene);
		   UIMainController controller = loader.getController(); 
		

		 
		    
		    stage.show();
		    Stage temp = (Stage)this.btnCerrar.getScene().getWindow();
		    temp.close();
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		
	}
}