package controllers;

import repositorio.RepositorioUsuarios;
import tablamodelos.Tablamodelousuario;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

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
	public void loadUsers() {	
		System.out.println("Carga usuarios");
		try {
			List<Usuario> users = repo.obtenerUsuarios();
			
			if(model == null) {
				model = new Tablamodelousuario(users);
				view.setModeloTable(model);
			}else {
				model.setUsers(users);
			}
			
		}catch (IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
	}
	
	private void openForm(Usuario user) {
		
		FormularioUsuarioDialog dialog = new FormularioUsuarioDialog(null, user);
		dialog.setVisible(true);
		
		if(dialog.isSaved()) {
			Usuario savedUser = dialog.
			
			try {
				if(user == null) {
					repo.save(savedUser);
				}else {
					int row = view.getSelectedRow();
					repo.update(row, savedUser);
				}
				
				loadUsers();
			}catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, e.getMessage());
			}
			
		}
		
	}
	
}