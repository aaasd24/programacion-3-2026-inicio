package repositorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
// 28/04/26: voy a implementar el ejemplo de la maestra aunque al parecer necesitamos descargar algo aparte para poder usar estas librerias, asi que por ahora como no esta nada actualizado en la guia para instalar, lo voy a dejar implementado pero como tal "no funcionando"(mañana vemos que pedo)//
public class RepositorioUsuarios {

	
	private final String FILE = "src/assets/files/usuarios.json";
	
	
	private final ObjectMapper mapper = 
			new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	
	
	
	public void guardarUsuario(Usuario usuarioNuevo) throws IOException{
		List<Usuario> actuales = obtenerUsuarios();
		if (actuales.stream().anyMatch(u -> u.getCorreo().equalsIgnoreCase(usuarioNuevo.getCorreo()))) {
	        System.out.println("Error: ya existe ese usuario");//se supone que ya con esto no deberia de poder repetirse el registro de usuario, pero aun se duplican a la hora de crearlos pipipi
	        return; 
	    }
		try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE, true), StandardCharsets.UTF_8))) {
			writer.append(usuarioNuevo.toCsv());
			writer.newLine();
			
		} 
		
		List<Usuario> users = obtenerUsuarios();
		users.addAll(actuales); //eclipse me pidio cambiar de add() to addAll()//
		updateAll(actuales);
		
		
	}
	
	
	
	
	public List<Usuario> obtenerUsuarios() throws IOException{
		
		//List<Usuario> usuarios = new ArrayList<Usuario>();
		File file = new File(FILE);
		
		if(!file.exists() || file.length() == 0) {
			return new ArrayList<>();
		
		/*try(BufferedReader reader = new BufferedReader(new FileReader(FILE))){
			String linea;
			while((linea = reader.readLine()) != null) {
				Usuario usuario = Usuario.fromCsv(linea);
				usuarios.add(usuario);
				
			}*/
			
		}
	
	
		//return usuarios;
		
		return mapper.readValue(
				file, 
				new TypeReference<List<Usuario>>() {}
			);
		
	}
	public void updateAll(List<Usuario> listaUsuarios) throws IOException {
	   /* try (BufferedWriter writer = new BufferedWriter(
	            new OutputStreamWriter(new FileOutputStream(FILE), StandardCharsets.UTF_8))) {

	        for (Usuario usuarioActual : listaUsuarios) {
	            writer.write(usuarioActual.toCsv());
	            writer.newLine();
	        }
	    }*/
		 mapper.writeValue(new File(FILE), listaUsuarios);
	}
	
	public void delete(int indice) throws IOException {
		List<Usuario> lista = obtenerUsuarios();
		lista.remove(indice);
		updateAll(lista);
	}
	
	public void update(int index, Usuario updatedUser) throws IOException {
		List<Usuario> usuarios = obtenerUsuarios();
		usuarios.set(index, updatedUser);
		updateAll(usuarios);
	}
}
