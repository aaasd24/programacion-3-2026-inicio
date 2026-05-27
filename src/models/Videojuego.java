package models;

public class Videojuego {
	
	private int id;
	private String nombre;
	private int[] generosId;
	private	String direccionURL;
	private	float precio;
	
	public Videojuego(int id, String nombre, int[] generos, float precio, String direccion) {
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
	public void setId(int id) {
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
	public void setGeneros(int[] generos) {this.generosId = generos; }
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
	public int[] getGenerosid() { return generosId;}

	
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
	public int getId() {
		return id;
	}
	
}