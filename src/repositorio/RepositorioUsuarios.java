package repositorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;

public class RepositorioUsuarios {

	
	private final String FILE = "src/assets/files/usuarios.csv";
	
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
	}
	public List<Usuario> obtenerUsuarios() throws IOException{
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try(BufferedReader reader = new BufferedReader(new FileReader(FILE))){
			String linea;
			while((linea = reader.readLine()) != null) {
				Usuario usuario = Usuario.fromCsv(linea);
				usuarios.add(usuario);
				
			}
			
		}
		return usuarios;
		
	}
	public void updateAll(List<Usuario> listaUsuarios) throws IOException {
	    try (BufferedWriter writer = new BufferedWriter(
	            new OutputStreamWriter(new FileOutputStream(FILE), StandardCharsets.UTF_8))) {

	        for (Usuario usuarioActual : listaUsuarios) {
	            writer.write(usuarioActual.toCsv());
	            writer.newLine();
	        }
	    }
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
