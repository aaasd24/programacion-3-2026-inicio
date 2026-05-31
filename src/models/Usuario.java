package models;

import utils.Genero;

public class Usuario {

	private int id;
	private String nombre;
	private String correo;
	private String contrasenia;
	private int idregion;
	//private EnumGenero genero = EnumGenero;
	Genero genero;
	private String anio;
	private String mes;
	private String dia;
	private String ImagenPath;
	private String rol;
	private int idBiblioteca;
	
	public Usuario() {
		
	}
	public Usuario(int id, String correo, String contrasenia) {
		this.id = id;
		this.correo = correo;
		this.contrasenia = contrasenia;
	}
	public Usuario(String correo, String contrasenia) {
		this.correo = correo;
		this.contrasenia = contrasenia;
	}

	
	
	public Usuario(String nombre, String correo, int idregion, Genero genero, String anio, String mes, String dia, String ImagenPath, String rol) {
		this.nombre = nombre;
		this.correo = correo;
		this.idregion = idregion;
		this.genero = genero;
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
		this.ImagenPath = ImagenPath;
		this.rol = rol;
	}
	
	
	public Usuario(int id, String nombre, String correo, int idregion, Genero genero, String anio, String mes, String dia, String ImagenPath, String rol) {
		this.id = id;
		this.nombre = nombre;
		this.correo = correo;
		this.idregion = idregion;
		this.genero = genero;
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
		this.ImagenPath = ImagenPath;
		this.rol = rol;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return el correo
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @return la contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	/**
	 * @return el pais
	 */
	public int getRegionID() {
		return idregion;
	}
	public String getRegionString() {
		switch(idregion) {
		case 1:
			return "MEXICO";
		case 2:
			return "PERO";
		case 3:
			return "MAIMI";
		case 4:
			return "LOSANGELES";
		case 5:
			return "OCEANIA";
		case 6:
			return "JAPON";
		case 7:
			return "CHINA";
		case 8:
			return "INDIA";
		case 9:
			return "ALASKA";
		case 10:
			return "POLO SUR";
		case 11:
			return "LONDRES";
		case 12:
			return "NIGERIA";
		}
		return null;
				
	}
	public Genero getGenero() {
		return genero;
	}
	/*
	public String getFechaString() {
		return "Y: " + anio + " M: " + meses + " D: " + dia;
	}*/
	public String getAnio() {
		return anio;
	}
	public String getMes() {
		return mes;
	}
	public String getDia() {
		return dia;
	}
	public String getRol() {
		return rol;
	}
	
	/**
	 * @return the idBiblioteca ???????????????????? 
	 */
	public int getIdBiblioteca() {
		return idBiblioteca;
	}
	
	/**
	 * @return the imagenPath
	 */
	public String getImagenPath() {
		return ImagenPath;
	}


	public void setNombre(String nombre) {this.nombre = nombre;}
	public void setCorreo(String correo) {this.correo = correo;}
	public void setContrasenia(String contrasenia) {this.contrasenia = contrasenia;}
	public void setGenero(Genero genero) {this.genero = genero;}
	public void setRegionID(int region) {this.idregion = region;}
	public void setAnio(String anio) {this.anio = anio;}
	public void setMes(String mes) {this.mes = mes;}
	public void setDia(String dia) {this.dia = dia;}
	public void setRol(String rol) {this.rol = rol;}
	public void setImagenPath(String imagenPath) {ImagenPath = imagenPath;}
	public void setIdBiblioteca(int idBiblioteca) {	this.idBiblioteca = idBiblioteca;}
	
	
	
	public String toCsv() {
		return nombre + "," + correo + "," + idregion + "," + genero +  "," + anio + "," + mes + "," + dia;
	}

	
}
