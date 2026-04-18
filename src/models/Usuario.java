package models;

public class Usuario {

	private String nombre;
	private String correo;
	private String contrasenia;
	private char genero;
	private String region;
	private String meses;
	private String anio;
	private String dia;
	
	public Usuario(String nombre, String correo, String contrasenia) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		
	}
	public Usuario(String nombre, String correo, String contrasenia, String region, char genero, String anio, String meses, String dia) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.region = region;
		this.genero = genero;
		this.anio = anio;
		this.meses = meses;
		this.dia = dia;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}
	/**
	 * @return the contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	/**
	 * @return the pais
	 */
	public String getRegion() {
		return region;
	}
	public char getGenero() {
		return genero;
	}
	
	public String getFechaString() {
		return "Y: " + anio + " M: " + meses + " D: " + dia;
	}
	
	public String toCsv() {
		return nombre + "," + contrasenia + "," + correo + "," + region + "," + genero +  "," + anio + "," + meses + "," + dia;
	}
	public static Usuario fromCsv(String datosUsuario) {
		String[] data = datosUsuario.split(",");
		String nombre = data[0];
		String contrasenia = data[1];
		String correo = data[2];
		String region = data[3];
		String genero = String.valueOf(data[4]);
		String anio = data[5];
		String meses = data[6];
		String dia = data[7];
		return new Usuario(nombre, contrasenia, correo, region, genero.charAt(0), anio, meses, dia); 
		
	}
	
	
}
