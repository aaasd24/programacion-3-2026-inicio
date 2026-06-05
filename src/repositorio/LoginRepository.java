package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DatabaseConnection;
import models.Usuario;
import utils.ContraseniaUtils;

public class LoginRepository {

	public Usuario login(String correo, String contrasenia) {
		
		String sql = "SELECT idusuario, contrasenia, nombre, correo, rol  FROM usuario WHERE correo = ?";
		try (
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			
			stmt.setString(1, correo);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				String hashedPassword = rs.getString("contrasenia");
				boolean contraseniaCorrecta = ContraseniaUtils.checkContrasenia(contrasenia, hashedPassword);
				
				if(!contraseniaCorrecta) {
					System.out.println("Error xd");
					return null;
				}
				Usuario usuarios = new Usuario();
				usuarios.setId(rs.getInt("idusuario"));
				usuarios.setNombre(rs.getString("nombre"));
				usuarios.setCorreo(rs.getString("correo"));
				usuarios.setRol(rs.getString("rol"));
				
				return usuarios;
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
}