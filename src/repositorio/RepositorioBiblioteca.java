package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConnection;
import models.Biblioteca;

public class RepositorioBiblioteca {
	
	public void subirBiblioteca(Biblioteca bibl) throws SQLException {
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
	
	public List<Biblioteca> obtenerListaBiblitecas() throws SQLException{
		
		List<Biblioteca> bibl = new ArrayList<Biblioteca>();
		try (
			Connection conexion = DatabaseConnection.getConnection();
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery("SELECT * FROM biblioteca");
				)
			
		{
			Biblioteca bibl1 = new Biblioteca("Repo");
			bibl.add(bibl1);
			/*
			while(rs.next()) {
				Videojuego videojuegoImportado = new Videojuego(
						rs.getInt("idvideojuego"), 
						rs.getString("titulo"), 
						obtenerGenerosid(),  //TODO Checar bien como obtener la lista de generos
						rs.getString("descripcion"),
						rs.getString("direccionArchivo"), 
						rs.getString("imagePath")
						);
				juegos.add(videojuegoImportado);
			}*/
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return bibl;
	}
	
	public boolean eliminar(int id){
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
	/*
	public boolean actualizar(int indice, Videojuego videojuegoActualizado) throws SQLException{
		String sql = "UPDATE videojuego SET nombre = ?, precio = ?, direccionArchivo = ?"
					+ "WHERW idvideojuego = ?";
		
		try(Connection conexion = DatabaseConnection.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);){
			pst.setString(0, videojuegoActualizado.getTitulo());
			pst.setFloat(1, videojuegoActualizado.getPrecio());
			pst.setString(2, videojuegoActualizado.getDireccionURL());
			
			int filaAfectada = pst.executeUpdate();
			if(filaAfectada > 0) {
				System.out.println("Cambios guardados");
				return true;
			}
			
		}
		return false;
		
	}*/
	
	public Biblioteca obtenerBibliotecaAsignada(Biblioteca bibli) throws SQLException{
		Biblioteca bib = new Biblioteca("temporal");
		try(Connection conexion = DatabaseConnection.getConnection();
				Statement stm = conexion.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM biblioteca WHERE nombre = " + bibli.getNombreBiblioteca());)
		{
			bib.setIdbiblioteca(rs.getInt("idbiblioteca"));
			bib.setNombreBiblioteca(rs.getString("nombre"));
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		
		return bib;
	}
}
