package grafo;

public class Videojuego {
	
	String nombre;
	String genero;
	String clasificacion;
	String plataforma;
	String lanzamiento;
	int jugadores;
	float precio;
	
	public Videojuego(String nombre, String genero, String clasificacion, String plataforma, String lanzamiento,
			int jugadores, float precio) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.clasificacion = clasificacion;
		this.plataforma = plataforma;
		this.lanzamiento = lanzamiento;
		this.jugadores = jugadores;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getClasificacion() {
		return clasificacion;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getLanzamiento() {
		return lanzamiento;
	}

	public void setLanzamiento(String lanzamiento) {
		this.lanzamiento = lanzamiento;
	}

	public int getJugadores() {
		return jugadores;
	}

	public void setJugadores(int jugadores) {
		this.jugadores = jugadores;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	

}
