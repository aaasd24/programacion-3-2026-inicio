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

	public void subirVideojuego(Videojuego videojuegoNuevo) throws SQLException {
		//pasar todos los datos excepto los generos
		String sql = "INSERT INTO videojuego(titulo, "
				+ "portadaPath,"
				+ "disponibilidad,"
				+ "descripcion,"
				+ "crossplay,"
				+ "multijugador,"
				+ "precio, "
				+ "direccionArchivo"
				+ ")" + 
					"VALUE (?, ?, ?, ?, ?, ?, ?, ?)";
		try(Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql);)
		{
			
			pst.setString(1, videojuegoNuevo.getTitulo());
			pst.setString(2, videojuegoNuevo.getPortadaPath());
			pst.setBoolean(3, videojuegoNuevo.getDisponibilidad());
			pst.setString(4, videojuegoNuevo.getDescripcion());
			pst.setBoolean(5, videojuegoNuevo.getCrossplay());
			pst.setString(6, videojuegoNuevo.getMultijugador());
			pst.setFloat(7, videojuegoNuevo.getPrecio());
			pst.setString(8, videojuegoNuevo.getDireccionArchivo());
			// El ID del juego se agrega automaticamente, los generos se agregar aparte
			pst.executeUpdate();
			System.err.println("Se subio nuevo juego");
		}catch(SQLException ex) {
			System.out.println("Error en conexion");
			ex.printStackTrace();
		}
		conectarVideojuegosGeneros(videojuegoNuevo);
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
						rs.getInt("idvideojuego"), 
						rs.getString("titulo"), 
						obtenerGenerosDdBD(rs.getInt("idvideojuego")),
						rs.getString("descripcion"),
						rs.getString("direccionArchivo"), 
						rs.getString("imagePath"));
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
			PreparedStatement pst = conexion.prepareStatement(sql);)
		{
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
	public int obteneridVideojuegodeBD(Videojuego videojuego) throws SQLException{
		int id = 0;
		try (
			Connection conexion = DatabaseConnection.getConnection();
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM videojuego"))
		{
			id = rs.getInt("idvideojuego");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return id;
	}
	
	public void conectarVideojuegosGeneros(Videojuego videojuego) throws SQLException {
		String sql = "INSERT INTO videojuego_has_generoVideojuego("
				+ "videojuego_idvideojuego, "
				+ "generoVideojuego_idgeneroVideojuego) "
				+ "VALUE (?, ?)";
		try(Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql);)
		{
			for(int i = 0; i < videojuego.getGeneros().size(); i++) {
				pst.setInt(1, obteneridVideojuegodeBD(videojuego));
				pst.setInt(2, obtenerIDConStringGeneroideojuego(videojuego.getGeneros().get(i)));
				pst.executeUpdate();
			}

			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public int obtenerIDConStringGeneroideojuego(String nombre) throws SQLException{
		int id = 0;
		try(Connection conexion = DatabaseConnection.getConnection();
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM generoVideojuego WHERE nombre = " + nombre);)
		{
			rs.next();
			id = rs.getInt("idgeneroVideojuego");
		}
		return id;
	}
	
	
	public List<String> obtenerGenerosDdBD(int idVideojuego) throws SQLException{
		List<String> generos = new ArrayList<String>();
		try (
				Connection conexion = DatabaseConnection.getConnection();
				Statement stm = conexion.createStatement();
				ResultSet rs = stm.executeQuery("SELECT nombre, idgeneroVideojuego FROM generoVideojuego gv "
						+ "INNER JOIN videojuego_has_generoVideojuego vhg ON gv.idgeneroVideojuego = vhg.generoVideojuego_idgeneroVideojuego "
						+ "INNER JOIN videojuego v ON vhg.videojuego_idvideojuego = v.idvideojuego WHERE v.idvideojuego = " 
						+ idVideojuego);)
			{	
			while(rs.next()) {
				String temporal = rs.getString("nombre");
				System.out.println(temporal);
				generos.add(temporal);
			}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		
		return generos;
	}
}
