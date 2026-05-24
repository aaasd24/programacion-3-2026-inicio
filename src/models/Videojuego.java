package models;

public class Videojuego {
	
	private String id;
	private String nombre;
	private String[] generosId;
	private	String direccionURL;
	private	float precio;
	
	public Videojuego(String id, String nombre, String[] generos, float precio, String direccion) {
		this.nombre = nombre;
		this.generosId = generos;
		this.precio = precio;
		this.direccionURL = direccion;
	}

	//Setters
	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {this.nombre = nombre;}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @param precio
	 */
	public void setPrecio(float precio) {this.precio = precio;}
	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(String[] generos) {this.generosId = generos; }
	/**
	 * 
	 * @param direccionURL
	 */
	public void setDireccionURL(String direccionURL) { this.direccionURL = direccionURL;}
	
	//Getters
	/**
	 * 
	 * @return nombre
	 */
	public String getNombre() {return nombre;}
	/**
	 * @return the generos
	 */
	public String[] getGeneros() { return generosId;}

	
	/**
	 * @return the precio
	 */
	public float getPrecio() { return precio; }

	/**
	 * 
	 * @return direccion de archivo
	 */
	public String getDireccionURL() { return direccionURL; }

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
}