package models;

import java.util.List;

public class Videojuego {
	
	private int id;
	private String titulo;
	private List<String> generosId;
	private String descripcion;
	private String portadaPath;
	private List<String> plataformasDisponible;
	private boolean crossplay;
	private String multijugador;
	private	String direccionArchivo;
	private	float precio;
	
	
	//Constructor base
	public Videojuego(int id, String nombre,  float precio, String descripcion, String direccion, String imgaePath, boolean crossplay, String multijugador) {
		this.id = id;
		this.titulo = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.direccionArchivo = direccion;
		this.portadaPath = imgaePath;
		this.crossplay = crossplay;
		this.multijugador = multijugador;
	}
	
	//Constructor completo 
	public Videojuego(String nombre, List<String> generos, float precio,String descripcion, String direccion, String imgaePath, List<String> plataforma, boolean crossplay, String multijugador) {
		this.titulo = nombre;
		this.generosId = generos;
		this.precio = precio;
		this.descripcion = descripcion;
		this.direccionArchivo = direccion;
		this.portadaPath = imgaePath;
		this.plataformasDisponible = plataforma;
		this.crossplay = crossplay;
		this.multijugador = multijugador;
		
		//no los habias inicializado y por eso arrojaba error we
		this.generosId = new java.util.ArrayList<>();
		this.plataformasDisponible = new java.util.ArrayList<>();
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

	public List<String> getGeneros() {
		return generosId;
	}

	public void setGeneros(List<String> generosId) {
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

	public List<String> getPlataformasDisponibles() {
		return plataformasDisponible;
	}

	public void setPlataforma(List<String> plataforma) {
		this.plataformasDisponible = plataforma;
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
	
	public boolean getCrossplay() {
		return crossplay;
	}
	public void setCrossplat(boolean crossplay) {
		this.crossplay = crossplay;
	}
	
}