package models;

public class Usuario {

	private String nombre;
	private String correo;
	private String contrasenia;
	private String region;
	private char genero;
	private String anio;
	private String mes;
	private String dia;
	private String ImagenPath;
	
	public Usuario() {
		
	}
	
	public Usuario(String nombre, String correo, String contrasenia) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		
	}
	
	
	public Usuario(String nombre, String correo, String contrasenia, String region, char genero, String anio, String mes, String dia, String ImagenPath) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.region = region;
		this.genero = genero;
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
		this.ImagenPath = ImagenPath;
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
	public String getRegion() {
		return region;
	}
	public char getGenero() {
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
	
	
	
	
	/**
	 * @return the imagenPath
	 */
	public String getImagenPath() {
		return ImagenPath;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public void setGenero(char genero) {
		this.genero = genero;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}

	/**
	 * @param imagenPath the imagenPath to set
	 */
	public void setImagenPath(String imagenPath) {
		ImagenPath = imagenPath;
	}
	public String toCsv() {
		return nombre + "," + correo + "," + region + "," + genero +  "," + anio + "," + mes + "," + dia;
	}

	
}
