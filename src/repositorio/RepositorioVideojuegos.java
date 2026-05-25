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
			
			pst.setString(0, videojuegoNuego.getId());
			pst.setString(1, videojuegoNuego.getNombre());
			pst.setFloat(2, videojuegoNuego.getPrecio());
			pst.setString(3, videojuegoNuego.getDireccionURL());
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
			ResultSet rs = stm.executeQuery("SELECT * FROM videojuego");)
		{
			while(rs.next()) {
				Videojuego videojuegoImportado = new Videojuego(
						rs.getString("idvideojuego"), 
						rs.getString("nombre"), 
						null,  //TODO Checar bien como obtener la lista de generos
						rs.getFloat("precio"), 
						rs.getString("direccionArchivo"));
				juegos.add(videojuegoImportado);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return juegos;
	}
	
	public boolean eliminar(int id) throws SQLException{
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
			pst.setString(0, videojuegoActualizado.getNombre());
			pst.setFloat(1, videojuegoActualizado.getPrecio());
			pst.setString(2, videojuegoActualizado.getDireccionURL());
			
			int filaAfectada = pst.executeUpdate();
			if(filaAfectada > 0) {
				System.out.println("Cambios guardados");
				return true;
			}
			
		}
		return false;
		
	}
	
}
