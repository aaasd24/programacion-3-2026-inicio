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
				+ "descripcion,"
				+ "crossplay,"
				+ "multijugador,"
				+ "precio, "
				+ "direccionArchivo"
				+ ")" + 
					"VALUE (?, ?, ?, ?, ?, ?, ?)";
		try(Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql);)
		{
			
			pst.setString(1, videojuegoNuevo.getTitulo());
			pst.setString(2, videojuegoNuevo.getPortadaPath());
			pst.setString(3, videojuegoNuevo.getDescripcion());
			pst.setBoolean(4, videojuegoNuevo.getCrossplay());
			pst.setString(5, videojuegoNuevo.getMultijugador());
			pst.setFloat(6, videojuegoNuevo.getPrecio());
			pst.setString(7, videojuegoNuevo.getDireccionArchivo());
			// El ID del juego se agrega automaticamente, los generos se agregar aparte
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
						rs.getInt("idvideojuego"), 
						rs.getString("titulo"), 
						rs.getFloat("precio"),
						rs.getString("descripcion"),
						rs.getString("direccionArchivo"), 
						rs.getString("portadaPath"),
						rs.getBoolean("crossplay"),
						rs.getString("multijugador")
						);
				juegos.add(videojuegoImportado);
				
			}
			Videojuego jg;
			for(int i = 0; i < juegos.size(); i++) {
				jg = juegos.get(i);
				jg.setGeneros(obtenerGenerosDdBD(jg.getId()));
				jg.setPlataforma(obtenerPlatafomaDeVideojuegodBD(jg.getId()));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return juegos;
	}
	
	public boolean eliminar(int id){
		//Primero se debe eliminar de la tabla has
		eliminarVideojuegoEnGeneroHas(id);
		eliminarVideojuegoEnPlataformaHas(id);
		// y luego ahora si el juego
		String sql = "DELETE FROM videojuego WHERE idvideojuego = ?";
		try(Connection conexion = DatabaseConnection.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);
				){
			pst.setInt(1, id);
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
	public boolean eliminarVideojuegoEnGeneroHas(int idVideojuego) {
		String sql = "DELETE FROM videojuego_has_generoVideojuego WHERE videojuego_idvideojuego = ?";
		try(Connection conexion = DatabaseConnection.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);
				){
			pst.setInt(1, idVideojuego);
			int filaAfectada = pst.executeUpdate();
			if(filaAfectada > 0) {
				return true;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	public boolean eliminarVideojuegoEnPlataformaHas(int idVideojuego) {
		String sql = "DELETE FROM videojuego_has_plataforma WHERE videojuego_idvideojuego = ?";
		try(Connection conexion = DatabaseConnection.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);
				){
			System.out.println(idVideojuego);
			pst.setInt(1, idVideojuego);
			int filaAfectada = pst.executeUpdate();
			if(filaAfectada > 0) {
				return true;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public int obteneridVideojuegodeBD(Videojuego videojuego) throws SQLException{
		int id = 0;
		try (
			Connection conexion = DatabaseConnection.getConnection();
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM videojuego WHERE titulo = '" + videojuego.getTitulo() + "'"))
		{
			rs.next();
			id = rs.getInt("idvideojuego");
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return id;
	}
	
	public void conectarVideojuegosGeneros(Videojuego videojuego) throws SQLException {
		int idVideojuego = obteneridVideojuegodeBD(videojuego);
		int[] lista = new int[videojuego.getGeneros().size()];
		for(int i = 0; i < videojuego.getGeneros().size(); i++) {
			lista[i] = obtenerIDConStringGeneroVideojuego(videojuego.getGeneros().get(i));
		}
		String sql = "INSERT INTO videojuego_has_generoVideojuego("
				+ "videojuego_idvideojuego, "
				+ "generoVideojuego_idgeneroVideojuego) "
				+ "VALUE (?, ?)";
		try(Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql);)
		{
			for(int i = 0; i < videojuego.getGeneros().size(); i++) {
				pst.setInt(1, idVideojuego);
				pst.setInt(2, lista[i]);
				pst.executeUpdate();
			}

			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public void conectarVideojuegoPlataforma(Videojuego videojuego) throws SQLException{
		int idVideojuego = obteneridVideojuegodeBD(videojuego);
		int[] lista = new int[videojuego.getPlataformasDisponibles().size()];
		for(int i = 0; i < videojuego.getPlataformasDisponibles().size(); i++) {
			lista[i] = obtenerIDSConStringPlataformaVideojuego(videojuego.getPlataformasDisponibles().get(i));
		}
		String sql = "INSERT INTO videojuego_has_plataforma("
				+ "videojuego_idvideojuego, "
				+ "plataforma_idplataforma) "
				+ "VALUE (?, ?)";
		try(Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql);)
		{
			for(int i = 0; i < videojuego.getPlataformasDisponibles().size(); i++) {
				pst.setInt(1, idVideojuego);
				pst.setInt(2, lista[i]);
				pst.executeUpdate();
			}

			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	public int obtenerIDConStringGeneroVideojuego(String nombre) throws SQLException{
		int id = 0;
		try(Connection conexion = DatabaseConnection.getConnection();
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM generoVideojuego WHERE nombre = '" + nombre + "'");)
		{
			rs.next();
			id = rs.getInt("idgeneroVideojuego");
		}
		return id;
	}
	
	public int obtenerIDSConStringPlataformaVideojuego(String nombre) throws SQLException{
		int id = 0;
		try(Connection conexion = DatabaseConnection.getConnection();
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM plataforma WHERE nombre = '" + nombre + "'");)
		{
			rs.next();
			id = rs.getInt("idplataforma");
		}
		return id;
	}
	
	public List<String> obtenerGenerosDdBD(int id) throws SQLException{
		List<String> generos = new ArrayList<String>();
		try (
				Connection conexion = DatabaseConnection.getConnection();
				Statement stm = conexion.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM generoVideojuego gv "
						+ "INNER JOIN videojuego_has_generoVideojuego vhg ON gv.idgeneroVideojuego = vhg.generoVideojuego_idgeneroVideojuego "
						+ "INNER JOIN videojuego v ON vhg.videojuego_idvideojuego = v.idvideojuego WHERE v.idvideojuego = " 
						+ id);)
			{	
			while(rs.next()) {
				String temporal = rs.getNString("nombre");
				generos.add(temporal);
			}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		
		return generos;
	}
	
	public List<String> obtenerPlatafomaDeVideojuegodBD(int idVideojuego) throws SQLException{
		List<String> plataforma = new ArrayList<String>();
		try (
				Connection conexion = DatabaseConnection.getConnection();
				Statement stm = conexion.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM plataforma p "
						+ "INNER JOIN videojuego_has_plataforma vhp ON p.idplataforma = vhp.plataforma_idplataforma "
						+ "INNER JOIN videojuego v ON vhp.videojuego_idvideojuego = v.idvideojuego WHERE v.idvideojuego = " 
						+ idVideojuego);)
			{
			while(rs.next()) {
				String temporal = rs.getNString("nombre");
				plataforma.add(temporal);
			}
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		
		return plataforma;
	}
	
	// Ver que hacer con este comando 
	public boolean actualizar(int indice, Videojuego videojuegoActualizado) throws SQLException{
		String sql = "UPDATE videojuego SET titulo = ?, "
				+ "precio = ?, "
				+ "direccionArchivo = ?, "
				+ "descripcion = ?, "
				+ "portadaPath = ?, "
				+ "crossplay = ?, "
				+ "multijugador = ? "
				+ "WHERE idvideojuego = ?";
		
		try(Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql);)
		{
			pst.setString(1, videojuegoActualizado.getTitulo());
			pst.setFloat(2, videojuegoActualizado.getPrecio());
			pst.setString(3, videojuegoActualizado.getDireccionArchivo());
			pst.setString(4, videojuegoActualizado.getDescripcion());
			pst.setString(5, videojuegoActualizado.getPortadaPath());
			pst.setBoolean(6, videojuegoActualizado.getCrossplay());
			pst.setString(7, videojuegoActualizado.getMultijugador());
			pst.setInt(8, videojuegoActualizado.getId());
			int filaAfectada = pst.executeUpdate();
			if(filaAfectada > 0) {
				System.out.println("Cambios guardados");
				return true;
			}
		}
		return false;	
	}
	public boolean actualizarPlataformas(Videojuego videojuegoActualizado) throws SQLException {
		conectarVideojuegoPlataforma(videojuegoActualizado);
		return true;
	}
	public boolean actualizarGeneros(Videojuego videojuegoActualizado) throws SQLException {
		try	{
			conectarVideojuegosGeneros(videojuegoActualizado);
			return true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}return false;
	}
	
	public List<Videojuego> buscarJuego(String entrada){
		List<Videojuego> juegos = new ArrayList<Videojuego>();
		try (
				Connection conexion = DatabaseConnection.getConnection();
				Statement stm = conexion.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM videojuego WHERE titulo LIKE '%" + entrada + "%'");)
		{
			while(rs.next()) {
				Videojuego videojuegoImportado = new Videojuego(
						rs.getInt("idvideojuego"), 
						rs.getString("titulo"), 
						rs.getFloat("precio"),
						rs.getString("descripcion"),
						rs.getString("direccionArchivo"), 
						rs.getString("portadaPath"),
						rs.getBoolean("crossplay"),
						rs.getString("multijugador")
						);
				juegos.add(videojuegoImportado);
				
			}
			Videojuego jg;
			for(int i = 0; i < juegos.size(); i++) {
				jg = juegos.get(i);
				jg.setGeneros(obtenerGenerosDdBD(jg.getId()));
				jg.setPlataforma(obtenerPlatafomaDeVideojuegodBD(jg.getId()));
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
		return juegos;
	}
	
}
