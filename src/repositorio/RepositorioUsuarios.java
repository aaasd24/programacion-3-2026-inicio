package repositorio;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
// 28/04/26: voy a implementar el ejemplo de la maestra aunque al parecer necesitamos descargar algo aparte para poder usar estas librerias, asi que por ahora como no esta nada actualizado en la guia para instalar, lo voy a dejar implementado pero como tal "no funcionando"(mañana vemos que pedo)//
public class RepositorioUsuarios {

	
	private final String FILE = "src/assets/files/usuarios.json";
	
	
	private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	
	
	public void guardarUsuario(Usuario usuarioNuevo) throws IOException{
		
		List<Usuario> usuariosLista = obtenerUsuarios();
		usuariosLista.add(usuarioNuevo); //eclipse me pidio cambiar de add() to addAll()//
		updateAll(usuariosLista);
		
		
	}
	
	
	
	
	public List<Usuario> obtenerUsuarios() throws IOException{
		
		//List<Usuario> usuarios = new ArrayList<Usuario>();
		File file = new File(FILE);
		
		if(!file.exists() || file.length() == 0) {
			return new ArrayList<>();
			
		}
	

		return mapper.readValue(file,new TypeReference<List<Usuario>>() {});
		
	}
	public void updateAll(List<Usuario> listaUsuarios) throws IOException {
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
