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
}
