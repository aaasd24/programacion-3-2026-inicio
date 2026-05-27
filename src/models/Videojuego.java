package models;

public class Videojuego {
	
	private int id;
	private String titulo;
	private int[] generosId;
	private String descripcion;
	private String portadaPath;
	private boolean plataforma;
	private boolean crossplay;
	private String multijugador;
	private	String direccionURL;
	private	float precio;
	
	
	//Constructor base
	public Videojuego(int id, String nombre, int[] generos,String descripcion, String direccion, String imgaePath) {
		this.id = id;
		this.titulo = nombre;
		this.generosId = generos;
		this.descripcion = descripcion;
		this.direccionURL = direccion;
		this.portadaPath = imgaePath;
	}
	
	//Constructor completo 
	public Videojuego(int id, String nombre, int[] generos, float precio,String descripcion, String direccion, String imgaePath, boolean plataforma, boolean crossplay, String multijugador) {
		this.id = id;
		this.titulo = nombre;
		this.generosId = generos;
		this.precio = precio;
		this.descripcion = descripcion;
		this.direccionURL = direccion;
		this.portadaPath = imgaePath;
		this.plataforma = plataforma;
		this.crossplay = crossplay;
		this.multijugador = multijugador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int[] getGenerosId() {
		return generosId;
	}

	public void setGenerosId(int[] generosId) {
		this.generosId = generosId;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPortadaPath() {
		return portadaPath;
	}

	public void setPortadaPath(String portadaPath) {
		this.portadaPath = portadaPath;
	}

	public boolean isPlataforma() {
		return plataforma;
	}

	public void setPlataforma(boolean plataforma) {
		this.plataforma = plataforma;
	}

	public boolean isCrossplay() {
		return crossplay;
	}

	public void setCrossplay(boolean crossplay) {
		this.crossplay = crossplay;
	}

	public String getMultijugador() {
		return multijugador;
	}

	public void setMultijugador(String multijugador) {
		this.multijugador = multijugador;
	}

	public String getDireccionURL() {
		return direccionURL;
	}

	public void setDireccionURL(String direccionURL) {
		this.direccionURL = direccionURL;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	
	
	
}