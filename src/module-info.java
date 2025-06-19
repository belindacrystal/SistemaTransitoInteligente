module SistemaTransitoInteligente_BeliNai {
	requires javafx.controls;
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;
	
	opens application to javafx.graphics, javafx.fxml;
}
