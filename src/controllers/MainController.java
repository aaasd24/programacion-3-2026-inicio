package controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import config.Config;
import models.Videojuego;
import repositorio.RepositorioVideojuegos;
import utils.Session;
import views.LoginWindow;
import views.MainWindow;

public class MainController {
	
	private MainWindow view;
	public JButton BotonVerUsuarios;
	private UserController usuarioController;
	private VideojuegoController videojuegoController;
	private RepositorioVideojuegos repo;
	
	public MainController(MainWindow view) {	
		this.view = view;
		this.repo = new RepositorioVideojuegos();
		loadWindowPreferences();
		
	}

	public void registerListeners( ) {

		for (ActionListener al : view.getSalir().getActionListeners()) {
            view.getSalir().removeActionListener(al);
        }
		view.getSalir().addActionListener(e -> {
			saveWindowPreferences();
			int option = view.confirmarSalida();
			if(option == JOptionPane.YES_OPTION){
				Session.logout();
				 LoginWindow ventana = new LoginWindow();
			     new LoginController(ventana.getLoginView());
			     ventana.setVisible(true);
			     view.dispose();
			     
			}	    	
		});

		for (WindowListener al : view.getWindowListeners()) {
            view.removeWindowListener(al);
        }
		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				{
					saveWindowPreferences();
		        	 int option = view.confirmarSalida();

		             if(option == JOptionPane.YES_OPTION){
		            	 LoginWindow ventana = new LoginWindow();
		                 new LoginController(ventana.getLoginView());
		                 ventana.setVisible(true);
		                 view.dispose();
		                 
		             }
		        	
		        };
			}
		});
		view.getBotonVerUsuario().addActionListener(e -> mostrarUsuarios());
		view.getBotonVideojuego().addActionListener(e -> mostrarVideojuegos());
		view.getBotonHome().addActionListener(e -> {
			view.mostrarVista(MainWindow.HOME);
			updateMenuState(MainWindow.HOME);
		});
		
		KeyAdapter teclaPasar = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscar();
				}
			}
		};
		
		view.getTxtBuscar().addKeyListener(teclaPasar);
		
	}
	private void mostrarUsuarios() {
		if(usuarioController == null) {
			usuarioController = new UserController(view.panelUsuario);
		}
		usuarioController.loadUsers();
		view.mostrarVista(MainWindow.USERS);
		updateMenuState(MainWindow.USERS);
		
	}
	
	private void mostrarVideojuegos() {
		if(videojuegoController == null) {
			videojuegoController = new VideojuegoController(view.panelVideojuego);
		}
		videojuegoController.cargarJuegos();
		view.mostrarVista(MainWindow.GAMES);
		updateMenuState(MainWindow.GAMES);
		
	}
	
	private void updateMenuState(String viewName) {
		view.botonVerUsuario.setEnabled(!viewName.equals(MainWindow.USERS));
		view.botonVerJuego.setEnabled(!viewName.equals(MainWindow.GAMES));
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
	
	public List<Videojuego> buscar() {
		List<Videojuego> juegosRelacionados = new ArrayList<Videojuego>();
		String SBusqueda = view.getTxtBuscar().getSelectedText();
		try {
			repo.buscarJuego(SBusqueda);
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return juegosRelacionados;
		
	}
	
}
