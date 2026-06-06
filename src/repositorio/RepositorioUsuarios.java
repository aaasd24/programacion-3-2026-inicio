package repositorio;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import models.Usuario;
import utils.ContraseniaUtils;
import utils.Genero;
import config.DatabaseConnection;

public class RepositorioUsuarios {
	
	public void guardarUsuario(Usuario usuarioNuevo) throws SQLException{	
		String sql = "INSERT INTO usuario (nombre, correo, contrasenia, genero, anio, mes, dia, rol, imagePath, region_idregion)"
				+ "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql);)
		{	
			pst.setString(1, usuarioNuevo.getNombre());
			pst.setString(2, usuarioNuevo.getCorreo());
			pst.setString(3, ContraseniaUtils.hashContrasenia(usuarioNuevo.getContrasenia()));
			pst.setString(4, String.valueOf(usuarioNuevo.getGenero()));
			pst.setString(5, usuarioNuevo.getAnio());
			pst.setString(6, usuarioNuevo.getMes());
			pst.setString(7, usuarioNuevo.getDia());
			pst.setString(8, usuarioNuevo.getRol());
			pst.setString(9, usuarioNuevo.getImagenPath());
			pst.setInt(10, usuarioNuevo.getRegionID());
			pst.executeUpdate();
			System.out.println("Se guardo nuevo usuario");
		}catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public List<Usuario> obtenerUsuarios() throws IOException{
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try(
				//Intenta conectarse a la base de datos, crea un estado y ejecuta un comando
				Connection conexion = DatabaseConnection.getConnection();
				Statement stm = conexion.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM usuario");
				)
		{
			while(rs.next()) {
				Usuario usuarioImportado = new Usuario(
						rs.getInt("idusuario"),
						rs.getString("nombre"), 
						rs.getString("correo"), 
						rs.getInt("region_idregion"),
						obtenerGenerodeRS(rs),
						rs.getString("anio"), 
						rs.getString("mes"), 
						rs.getString("dia"), 
						rs.getString("imagePath"), 
						rs.getString("rol"));
				usuarios.add(usuarioImportado);
			}
			
			
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		
		return usuarios;
	}
	//Metodo especial, para leer los datos de la base de datos y se use el enum del java
	public static Genero obtenerGenerodeRS(ResultSet rs) {
		try {
			if(rs.getString("genero") == "HOMBRE") {
				return Genero.HOMBRE;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Genero.MUJER;
	}
	
	public boolean delete(int id){
		
		String sql = "DELETE FROM usuario WHERE idusuario = ?";
		try(Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql))
		{
			pst.setInt(1, id);
			int affectedRows = pst.executeUpdate();
			if(affectedRows > 0) {
				System.out.println("Se elimino");
				return true;
			}	
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
		
	}
	
	public boolean update(int index, Usuario updatedUser) throws IOException {
		String sql = "UPDATE usuario SET nombre = ?, correo = ?, region_idregion = ?, " +
					" genero = ?, anio = ?, mes = ?, dia = ?, rol = ? WHERE idusuario = ?";
		
		try(Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql)) 
		{
			pst.setString(1, updatedUser.getNombre());
			pst.setString(2, updatedUser.getCorreo());
			pst.setInt(3, updatedUser.getRegionID());
			pst.setString(4, String.valueOf(updatedUser.getGenero()));
			pst.setString(5, updatedUser.getAnio());
			pst.setString(6, updatedUser.getMes());
			pst.setString(7, updatedUser.getDia());
			pst.setString(8, updatedUser.getRol());
			pst.setInt(9, updatedUser.getId());
			
			int affectedRows = pst.executeUpdate();
			
			if(affectedRows > 0) {
				System.out.println("Cambios guardados");
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	public void conectarBiblioteca(Usuario usuario) throws SQLException{
		String sql = "UPDATE usuario SET biblioteca_idbiblioteca = ? WHERE nombre = ?";
		int id = obtenerIDBiblioteca(usuario);
		try(Connection conexion = DatabaseConnection.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);)
		{
			pst.setInt(1, id);
			pst.setString(2, usuario.getNombre());
			pst.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int obtenerIDBiblioteca(Usuario usuario) throws SQLException{
		int id = 0;
		try(Connection conexion = DatabaseConnection.getConnection();
				Statement stm = conexion.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM biblioteca WHERE nombre = 'Biblioteca de " + usuario.getNombre() + "'");)
		{
			rs.next();
			id = rs.getInt("idbiblioteca");
		
		}
		return id;
	}
	
	
}
