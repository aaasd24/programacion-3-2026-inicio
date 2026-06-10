package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.BibliotecaPersonal;
import models.Videojuego;

public class RepositorioBiblioteca {
	
	public void subirBiblioteca(BibliotecaPersonal bibl) throws SQLException {
		String sql = "INSERT INTO biblioteca(nombre) VALUE (?)";
		try(Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql);)
		{
			pst.setString(1, bibl.getNombreBiblioteca());
			pst.executeUpdate();
			System.err.println("Se creo nueva biblioteca para usuario");
		}catch(SQLException ex) {
			System.out.println("Error en conexion");
			ex.printStackTrace();
		}
	}
	
	//Se supone que las bibliotecas no se deberan mostrar bajo circunstancias normales
	public List<BibliotecaPersonal> obtenerListaBiblitecas() throws SQLException{
		List<BibliotecaPersonal> bibl = new ArrayList<BibliotecaPersonal>();
		try (
			Connection conexion = DatabaseConnection.getConnection();
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM biblioteca");)
		{
			while(rs.next()) {
				BibliotecaPersonal bibl1 = new BibliotecaPersonal(rs.getString("nombre"));
				bibl.add(bibl1);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return bibl;
	}
	
	public boolean eliminar(int id){
		String sql = "DELETE FROM biblioteca WHERE idbiblioteca = ?";
		try(Connection conexion = DatabaseConnection.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);)
		{
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
	
	//TODO ver como se actualiza la biblitoeca personal de cada usuario
	//Se actualiza nombre si se cambio y los nuevos juegos que se agreguen
	public boolean actualizar(int indice, BibliotecaPersonal bibliotecaActualizada) throws SQLException{
		//TODO Index para que chuchas se usa????
		String sql = "UPDATE biblioteca_has_videojuego SET  WHERE biblioteca_idbiblioteca = ?";
		
		try(Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql);)
		{
			pst.setString(1, bibliotecaActualizada.getNombreBiblioteca());
			pst.setInt(2, bibliotecaActualizada.getIdbiblioteca());
			
			int filaAfectada = pst.executeUpdate();
			if(filaAfectada > 0) {
				System.out.println("Cambios guardados");
				return true;
			}
			
		}
		return false;	
	}
	
	public void juegoComprado(int bibi, int idV) {
		String sql = "INSERT INTO biblioteca_has_videojuego(biblioteca_idbiblioteca, videojuego_idvideojuego) VALUE (?, ?)";
		try(Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql);)
		{
			pst.setInt(1, bibi);
			pst.setInt(1, idV);
			pst.executeUpdate();
			System.err.println("Se guardo el videojuego en la biblioteca del usuario");
		}catch(SQLException ex) {
			System.out.println("Error en conexion");
			ex.printStackTrace();
		}
	}
	public int obteneridVideojuegodeBD(Videojuego videojuego) throws SQLException{
		int id = 0;
		String sql = "SELECT idvideojuego FROM videojuego WHERE UPPER(TRIM(titulo)) = UPPER(TRIM(?))";
		// Abrimos una conexión independiente para que no interfiera con los ciclos externos
		try (Connection conexion = DatabaseConnection.getConnection();
			 PreparedStatement pst = conexion.prepareStatement(sql))
		{
			pst.setString(1, videojuego.getTitulo());
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					id = rs.getInt("idvideojuego");
				}
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return id;
	}
	public List<Videojuego> obtenerJuegosPersonales(int id) throws SQLException{
		List<Videojuego> juegos = new ArrayList<Videojuego>();
		String sql = "SELECT * FROM videojuego v "
				+ "INNER JOIN biblioteca_has_videojuego vhb ON v.idvideojuego = vhb.biblioteca_idbiblioteca "
				+ "INNER JOIN biblioteca b ON vhb.biblioteca_idbiblioteca = b.idbiblioteca WHERE b.idbiblioteca = ";
			
		try (
				Connection conexion = DatabaseConnection.getConnection();
				Statement stm = conexion.createStatement();
				ResultSet rs = stm.executeQuery(sql + id);)
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

	public List<String> obtenerPlatafomaDeVideojuegodBD(int idVideojuego) throws SQLException{
		List<String> plataforma = new ArrayList<String>();
		// Seleccionamos explícitamente p.nombre de la tabla plataforma
		String sql = "SELECT p.nombre FROM plataforma p "
				+ "INNER JOIN videojuego_has_plataforma vhp ON p.idplataforma = vhp.plataforma_idplataforma "
				+ "INNER JOIN videojuego v ON vhp.videojuego_idvideojuego = v.idvideojuego WHERE v.idvideojuego = ?";
		try (
			Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql))
		{
			pst.setInt(1, idVideojuego);
			try (ResultSet rs = pst.executeQuery()) {
				while(rs.next()) {
					plataforma.add(rs.getString("nombre"));
				}
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return plataforma;
	}

	public List<String> obtenerGenerosDdBD(int id) throws SQLException{
		List<String> generos = new ArrayList<String>();
		// Selecciona  gv.nombre para evitar confusiones en el Driver
		String sql = "SELECT gv.nombre FROM generoVideojuego gv "
				+ "INNER JOIN videojuego_has_generoVideojuego vhg ON gv.idgeneroVideojuego = vhg.generoVideojuego_idgeneroVideojuego "
				+ "INNER JOIN videojuego v ON vhg.videojuego_idvideojuego = v.idvideojuego WHERE v.idvideojuego = ?";
		try (
			Connection conexion = DatabaseConnection.getConnection();
			PreparedStatement pst = conexion.prepareStatement(sql))
		{	
			pst.setInt(1, id);
			try (ResultSet rs = pst.executeQuery()) {
				while(rs.next()) {
					generos.add(rs.getString("nombre"));
				}
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
		return generos;
	}

}
