package models;

import java.util.List;

public class BibliotecaPersonal {

	private List<Videojuego> biblioteca;
	private int idbiblioteca = 0;
	private String nombreBiblioteca;
	
	public BibliotecaPersonal(String nombreUsuario) {
		this.nombreBiblioteca = "Biblioteca de " +  nombreUsuario;
	}
	
	public void agregaJuego(Videojuego juego) {
		biblioteca.add(juego);
	}
	public void eliminarJuego(Videojuego juego) {
		biblioteca.remove(juego);
	}

	public int getIdbiblioteca() {
		return idbiblioteca;
	}

	public void setIdbiblioteca(int idbiblioteca) {
		this.idbiblioteca = idbiblioteca;
	}

	/**
	 * @return the nombreBiblioteca
	 */
	public String getNombreBiblioteca() {
		return nombreBiblioteca;
	}

	/**
	 * @param nombreBiblioteca the nombreBiblioteca to set
	 */
	public void setNombreBiblioteca(String nombreBiblioteca) {
		this.nombreBiblioteca = nombreBiblioteca;
	}
	
}
