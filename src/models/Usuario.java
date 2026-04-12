package models;

public class Usuario {

	private String nombre;
	private String correo;
	private String contrasenia;
	private String pais;
	
	public Usuario() {
		
	}
	public Usuario(String nombre, String correo, String contrasenia) {
		this.nombre = nombre;
		this.correo = correo;
		this.contrasenia = contrasenia;
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
	public String getPais() {
		return pais;
	}
	
}
