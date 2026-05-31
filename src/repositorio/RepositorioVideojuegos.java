package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Videojuego;

public class RepositorioVideojuegos {

	public void subirVideojuego(Videojuego videojuegoNuego) throws SQLException {
		String sql = "INSERT INTO videojuego(idvideojuego, nombre, precio, direccionArchivo)" + 
					"VALUE (?, ?, ?, ?)";
		try(Connection conexion = DatabaseConnection.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);){
			
			pst.setInt(0, videojuegoNuego.getId());
			pst.setString(1, videojuegoNuego.getTitulo());
			pst.setFloat(2, videojuegoNuego.getPrecio());
			pst.setString(3, videojuegoNuego.getDireccionArchivo());
			pst.executeUpdate();
			System.err.println("Se subio nuevo juego");
		}catch(SQLException ex) {
			System.out.println("Error en conexion");
			ex.printStackTrace();
		}
	}
	
	public List<Videojuego> obtenerListaVideojuegos() throws SQLException{
		
		List<Videojuego> juegos = new ArrayList<Videojuego>();
		try (
			Connection conexion = DatabaseConnection.getConnection();
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM videojuego");
				//ResultSet rs2 = stm.executeQuery("");
				)
			
		{
			while(rs.next()) {
				Videojuego videojuegoImportado = new Videojuego(
						rs.getInt("idvideojuego"), 
						rs.getString("titulo"), 
						null,  //TODO Checar bien como obtener la lista de generos
						rs.getString("descripcion"),
						rs.getString("direccionArchivo"), 
						rs.getString("imagePath")
						);
				juegos.add(videojuegoImportado);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return juegos;
	}
	
	public boolean eliminar(int id){
		String sql = "DELETE FROM videojuego WHERE idvideojuego = ?";
		try(Connection conexion = DatabaseConnection.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);
				){
			pst.setInt(0, id);
			int filaAfectada = pst.executeUpdate();
			if(filaAfectada > 0) {
				System.out.println("Se elimino");
				return true;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public boolean actualizar(int indice, Videojuego videojuegoActualizado) throws SQLException{
		String sql = "UPDATE videojuego SET nombre = ?, precio = ?, direccionArchivo = ?"
					+ "WHERW idvideojuego = ?";
		
		try(Connection conexion = DatabaseConnection.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);){
			pst.setString(0, videojuegoActualizado.getTitulo());
			pst.setFloat(1, videojuegoActualizado.getPrecio());
			pst.setString(2, videojuegoActualizado.getDireccionArchivo());
			
			int filaAfectada = pst.executeUpdate();
			if(filaAfectada > 0) {
				System.out.println("Cambios guardados");
				return true;
			}
			
		}
		return false;
		
	}
	/**
	 * 
	 * @param idVideojuego
	 * @return lista de enteros de los idgenero del videojuego
	 * @throws SQLException
	 */
	public int[] obtenerGenerosid() throws SQLException {
		String sql = "SELECT generoVideojuego_idgeneroVideojuego FROM videojuego_has_generoVideojuego WHERE videojuego_idvideojuego = ?";
		try(Connection conexion = DatabaseConnection.getConnection();
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery(sql)){
			int[] idgenerosTemporal = null;
			int i = 0;
			while(rs.next()) {
				 idgenerosTemporal[i] = rs.getInt("generoVideojuego_idgeneroVideojuego");
				 i++;
			}
			return idgenerosTemporal;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}
}
