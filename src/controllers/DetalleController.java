package controllers;

import models.Videojuego;
import views.DetalleJuegoView;

public class DetalleController {

	private DetalleJuegoView view;
	private Videojuego videojuego;

	public DetalleController(DetalleJuegoView view, Videojuego video) {
		this.view = view;
		this.videojuego = video;
	}
	public void inicializarListeners() {
	
	}
	
}
