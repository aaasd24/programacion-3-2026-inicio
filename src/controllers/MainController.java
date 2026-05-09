package controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import utils.Config;
import views.MainWindow;

public class MainController {
	
	private MainWindow view;
	public JButton BotonVerUsuarios;
	private UserController usuarioController;
	
	public MainController(MainWindow view) {	
		this.view = view;
		loadWindowPreferences();
		
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
		//int option = JOptionPane.showConfirmDialog(view, "¿Seguro que deseas regresar? Se perderán todos los datos");
		//if (option == JOptionPane.YES_OPTION) {
			//new LoginController(new LoginWindow().getLoginView());
		saveWindowPreferences();
		view.dispose();
		//}
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
	private void saveWindowPreferences() {
		Dimension size = view.getSize();
		Point point = view.getLocation();
		
		Config.set("registration.window.width", 
				String.valueOf(size.width));
		
		Config.set("registration.window.height", 
				String.valueOf(size.height));
		
		Config.set("registration.window.x", 
				String.valueOf(point.x));
		
		Config.set("registration.window.y", 
				String.valueOf(point.y));
		
	}
	
	private void loadWindowPreferences()
	{
		int width = Integer.parseInt(
				Config.get("registration.window.width"
						, "500"));
		
		int height = Integer.parseInt(
				Config.get("registration.window.height"
						, "500"));
		
		String xValue = Config.get("registration.window.x"
						, "");
		
		String yValue = Config.get("registration.window.y"
				, "");
		
		if(!xValue.isBlank() && !yValue.isBlank()) {
			view.setWindowLocation(Integer.parseInt(xValue), Integer.parseInt(yValue));
		}else {
			view.setLocationRelativeTo(null);
		}
		
		view.setWindowSize(width, height);
	}
	
}
