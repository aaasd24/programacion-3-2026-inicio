package controllers;

import repositorio.RepositorioUsuarios;
import tablamodelos.Tablamodelousuario;
import models.Usuario;
import views.UsuarioView;
import views.FormularioRegistro;

public class UserController {

	private Usuario view;
	private RepositorioUsuarios repo;
	private Tablamodelousuario model;
	
	public UserController(UsuarioView view) {
		this.view = view;
		repo = new RepositorioUsuarios();
		
		view.getBtnAdd().addActionListener(e -> {
			UserFormDialog form = new UserFormDialog(null, null);
			form.setVisible(true);
		});
	}
	
}