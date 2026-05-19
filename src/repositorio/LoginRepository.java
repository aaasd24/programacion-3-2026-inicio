package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DatabaseConnection;
import models.Usuario;

public class LoginRepository {

	public Usuario login(String correo, String contrasenia) {
		
		/*String sql = "SELECT id, email, password FROM users WHERE email = '" 
				+ email + "' AND password = '" + password + "'";*/
		//Se cambio el comando dado que la maesta tiene los datos en ingles
		//String sql = "SELECT id, email, password FROM users WHERE email = ? AND password = ?";
		
		String sql = "SELECT idusuario, correo, contrasenia FROM usuario WHERE correo = ? AND contrasenia = ?";
		try (
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			
			stmt.setString(1, correo);
			stmt.setString(2, contrasenia);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				Usuario usuarios = new Usuario();
				usuarios.setId(rs.getInt("id"));
				usuarios.setCorreo(rs.getString("correo"));
				
				return usuarios;
			}
			
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
	
}