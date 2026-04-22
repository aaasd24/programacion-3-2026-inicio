package controllers;

import repositorio.RepositorioUsuarios;
import tablamodelos.Tablamodelousuario;
import models.Usuario;
import views.UsuarioView;
import views.FormularioRegistro;
import views.FormularioUsuarioDialog;

public class UserController {

	private UsuarioView view;
	private RepositorioUsuarios repo;
	private Tablamodelousuario model;
	
	public UserController(UsuarioView view) {
		this.view = view;
		repo = new RepositorioUsuarios();
		
		view.getBtnAdd().addActionListener(e -> {
			FormularioUsuarioDialog form = new FormularioUsuarioDialog(null, null);
			form.setVisible(true);
		});
		
	}
	
}