package controllers;

import java.util.List;

import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;


import views.LoginWindow;
import views.MainWindow;

public class MainController {
	
	private MainWindow view;
	public JButton BotonVerUsuarios;
	private UserController usuarioController;
	
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
		view.getBotonHome().addActionListener(e -> {
			view.mostrarVista(MainWindow.HOME);
			updateMenuState(MainWindow.HOME);
		}
				);
		
	}
	private void handleClose() {
		int option = JOptionPane.showConfirmDialog(view, "¿Seguro que deseas regresar? Se perderán todos los datos");
		if (option == JOptionPane.YES_OPTION) {
			//new LoginController(new LoginWindow().getLoginView());
			view.dispose();
		}
	}
	private void mostrarUsuarios() {
		if(usuarioController == null) {
			usuarioController = new UserController(view.panelUsuario);
		}
		usuarioController.loadUsers();
		view.mostrarVista(MainWindow.USERS);
		updateMenuState(MainWindow.USERS);
		
	}
	
	private void updateMenuState(String viewName) {
		view.botonVerUsuario.setEnabled(!viewName.equals(MainWindow.USERS));
		view.botonHome.setEnabled(!viewName.equals(MainWindow.HOME));
	}
	
	
}
