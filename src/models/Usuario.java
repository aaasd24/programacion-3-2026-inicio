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
	
	public Usuario() {
		
	}
	
	public Usuario(String nombre, String correo, String contrasenia) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		
	}
	public Usuario(String nombre, String correo, String region, char genero, String anio, String mes, String dia) {
		this.nombre = nombre;
		this.correo = correo;
		this.region = region;
		this.genero = genero;
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
	}
	
	
	public Usuario(String nombre, String correo, String contrasenia, String region, char genero, String anio, String mes, String dia) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.region = region;
		this.genero = genero;
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
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
	public String toCsv() {
		return nombre + "," + correo + "," + region + "," + genero +  "," + anio + "," + mes + "," + dia;
	}
	public static Usuario fromCsv(String datosUsuario) {
		String[] data = datosUsuario.split(",");
		String nombre = data[0];
		//String contrasenia = data[1];
		String correo = data[1];
		String region = data[2];
		String genero = String.valueOf(data[3]);
		String anio = data[4];
		String meses = data[5];
		String dia = data[6];
		
		return new Usuario(
				nombre,
				correo, region,
				genero.charAt(0),
				anio,
				meses,
				dia
				); 
	}

	
}
