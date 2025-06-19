package data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;




public class JsonUtils<T>  { //Clase generica
	
	private String filePath; //Ruta del Json a procesar
	private final ObjectMapper mapper; 
	//private final ObjectMapper mapper= new ObjectMapper().registerModule(new JavaTimeModule());
	
	
	public JsonUtils(String filePath) {
		this.filePath = filePath;
		this.mapper = new ObjectMapper()
				.registerModule(new JavaTimeModule()) // Soporte para fechas
				.enable(SerializationFeature.INDENT_OUTPUT); // Habilitar formato bonito
	}

	
	
	public void save(T t) throws Exception {
		List<T>list = getElements((Class<T>)t.getClass());
		list.add(t);
		mapper.writeValue(new File(this.filePath), list);
		
	}
	public void remove(T t, int index)throws Exception{
		List<T>list = getElements((Class<T>)t.getClass());
		list.remove(index);
		mapper.writeValue(new File(this.filePath), list);
	}

	public List<T> getElements(Class<T> temp) throws Exception{
		File file= new File(this.filePath);
		
		if(!file.exists()) {
			return new ArrayList<T>();
		}
		return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, temp));		
	}
	public void saveList(List<T> list) throws Exception {
		// Guardar una lista completa (para editar o eliminar)
		mapper.writeValue(new File(this.filePath), list);
	}
	
}