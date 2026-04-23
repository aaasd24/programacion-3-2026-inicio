package controllers;

import java.util.List;

import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import models.Usuario;
import repositorio.RepositorioUsuarios;
import tablamodelos.Tablamodelousuario;
import views.LoginWindow;
import views.MainWindow;

public class MainController {
	
	private MainWindow view;
	public JButton BotonVerUsuarios;
	private UserController usuarioControler;
	
	public MainController(MainWindow view) {	
		this.view = view;
		
	}

	public void registerListeners( ) {

		view.getSalir().addActionListener(e -> handleClose());

		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
			}
		});
		view.getBotonVerUsuario().addActionListener(e -> mostrarUsuarios());
		view.getBotonHome().addActionListener(e -> view.mostrarVista(view.HOME));
		
	}
	private void handleClose() {
		//int option = view.confirmExit();
		//System.out.println(option);
		int option = JOptionPane.showConfirmDialog(view, "¿Seguro que deseas regresar? Se perderán todos los datos");
		if (option == JOptionPane.YES_OPTION) {
			new LoginController(new LoginWindow().getLoginView());
			view.dispose();
		}
	}
	private void mostrarUsuarios() {
		if(usuarioControler == null) {
			usuarioControler = new UserController(view.panelUsuario);
		}
		usuarioControler.loadUsers();
		RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
		try {
			List<Usuario> listaUsuarios = repositorioUsuarios.obtenerUsuarios();
			
			Tablamodelousuario  tablaUsuarios = new Tablamodelousuario(listaUsuarios);
			
			view.panelUsuario.setModeloTable(tablaUsuarios);
			
			view.mostrarVista(view.USERS);
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage());
		}
	}
	
	private void updateMenuState(String viewName) {
		view.botonVerUsuario.setEnabled(!viewName.equals(MainWindow.USERS));
		view.botonHome.setEnabled(!viewName.equals(MainWindow.HOME));
	}
	
	
}
