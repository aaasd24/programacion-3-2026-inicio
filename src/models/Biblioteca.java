package models;

import java.util.List;

public class Biblioteca {

	private List<Videojuego> biblioteca;
	private int idbiblioteca;
	
	public Biblioteca(int idusuario) {
		idbiblioteca = idusuario;
		
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
	
}
