module SistemaTransitoInteligente_BeliNai {
	requires javafx.controls;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens business to javafx.graphics, javafx.fxml;
}
