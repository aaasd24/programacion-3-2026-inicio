package models;

public class Usuario {

	private String nombre;
	private String correo;
	private String contrasenia;
	private String region;
	private String meses;
	private String anio;
	private String dia;
	
	public Usuario(String nombre, String correo, String contrasenia) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		
	}
	public Usuario(String nombre, String correo, String contrasenia, String region, String anio, String meses, String dia) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.region = region;
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
	
	public String toCsv() {
		return nombre + "," + contrasenia + "," + correo + "," + region +  "," + anio + "," + meses + "," + dia;
	}
	public static Usuario fromCsv(String datosUsuario) {
		String[] data = datosUsuario.split(",");
		String nombre = data[0];
		String contrasenia = data[1];
		String correo = data[2];
		String region = data[3];
		String anio = data[4];
		String meses = data[5];
		String dia = data[6];
		return new Usuario(nombre, contrasenia, correo, region, anio, meses, dia); 
		
	}
	
	
}
