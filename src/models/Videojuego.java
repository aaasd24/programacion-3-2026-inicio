package models;

public class Videojuego {
	
	private int id;
	private String titulo;
	private String[] generosId;
	private String descripcion;
	private String portadaPath;
	private boolean disponibilidadEnEstaplataforma;
	private boolean crossplay;
	private String multijugador;
	private	String direccionArchivo;
	private	float precio;
	
	
	//Constructor base
	public Videojuego(int id, String nombre, String[] generos,String descripcion, String direccion, String imgaePath) {
		this.id = id;
		this.titulo = nombre;
		this.generosId = generos;
		this.descripcion = descripcion;
		this.direccionArchivo = direccion;
		this.portadaPath = imgaePath;
	}
	
	//Constructor completo 
	public Videojuego(int id, String nombre, String[] generos, float precio,String descripcion, String direccion, String imgaePath, boolean plataforma, boolean crossplay, String multijugador) {
		this.id = id;
		this.titulo = nombre;
		this.generosId = generos;
		this.precio = precio;
		this.descripcion = descripcion;
		this.direccionArchivo = direccion;
		this.portadaPath = imgaePath;
		this.disponibilidadEnEstaplataforma = plataforma;
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

	public String[] getGeneros() {
		return generosId;
	}

	public void setGeneros(String[] generosId) {
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

	public boolean isPlataformadisponible() {
		return disponibilidadEnEstaplataforma;
	}

	public void setPlataforma(boolean plataforma) {
		this.disponibilidadEnEstaplataforma = plataforma;
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

	public String getDireccionArchivo() {
		return direccionArchivo;
	}

	public void setDireccionArchivo(String direccionURL) {
		this.direccionArchivo = direccionURL;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	
	
	
}