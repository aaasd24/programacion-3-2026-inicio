package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controllers.MainController;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JMenuItem salir;
	private JMenuBar mb;
	private JMenu archivo;
	private JMenuItem abrir;
	private JMenuItem guardar;

	private JMenu otraOpcion;
	
	private JMenu opcion1;
	private JMenuItem opcion2;
	private JMenuItem opcion3;
	
	public JButton botonVerUsuario;
	public JButton botonHome;
	
	public final static String HOME = "HOME";
	public final static String USERS = "USERS";
	
	public UsuarioView panelUsuario;
	
	private CardLayout cardLayout;
	private JPanel contenedor;
	
	public MainWindow() {
		
		setSize(500,500);
		setTitle("Biblioteca");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMenu();
		JPanel panel = new JPanel();
		JPanel panelBoton = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
		
		crearBarraNav();
		crearVistas();
		//botonVerUsuario = new JButton("Ver usuarios");
		//panelBoton.add(botonVerUsuario, BorderLayout.CENTER);
		//panel.add(panelBoton);
		
		MainController mainControl = new MainController(this);
		mainControl.registerListeners();
		setVisible(true);
		
	}
	
	
	/**
	 * @return the salir
	 */
	public JMenuItem getSalir() {
		return salir;
	}


	/**
	 * @return the mb
	 */
	public JMenuBar getMb() {
		return mb;
	}


	/**
	 * @return the archivo
	 */
	public JMenu getArchivo() {
		return archivo;
	}


	/**
	 * @return the abrir
	 */
	public JMenuItem getAbrir() {
		return abrir;
	}


	/**
	 * @return the guardar
	 */
	public JMenuItem getGuardar() {
		return guardar;
	}


	/**
	 * @return the otraOpcion
	 */
	public JMenu getOtraOpcion() {
		return otraOpcion;
	}


	/**
	 * @return the opcion1
	 */
	public JMenu getOpcion1() {
		return opcion1;
	}


	/**
	 * @return the opcion2
	 */
	public JMenuItem getOpcion2() {
		return opcion2;
	}


	/**
	 * @return the opcion3
	 */
	public JMenuItem getOpcion3() {
		return opcion3;
	}

	/**
	 * 
	 */
	public JButton getBotonVerUsuario() {
		return botonVerUsuario;
	}
	
	/**
	 * 
	 * @return
	 */
	public JButton getBotonHome() {
		return botonHome;
	}
	
	public void setMenu() {
		
		mb = new JMenuBar();
		setJMenuBar(mb);
		
		archivo= new JMenu("Archivo");
		archivo.setMnemonic(KeyEvent.VK_A);
		mb.add(archivo);
		
		abrir = new JMenuItem("Abrir");
		abrir.setMnemonic(KeyEvent.VK_B);
		archivo.add(abrir);
		
		guardar = new JMenuItem("Guardar");
		guardar.setMnemonic(KeyEvent.VK_G);
		archivo.add(guardar);
		
		archivo.addSeparator();
		salir = new JMenuItem("Salir");
		salir.setMnemonic(KeyEvent.VK_S);
		archivo.add(salir);
		
		otraOpcion= new JMenu("Otra opción");
		otraOpcion.setMnemonic(KeyEvent.VK_O);
		mb.add(otraOpcion);
		
		opcion1 = new JMenu("Opción 1");
		otraOpcion.add(opcion1);
		
		opcion3 = new JMenuItem("Opción 3");
		opcion1.add(opcion3);
		
		opcion2 = new JMenuItem("Opción 2");
		otraOpcion.add(opcion2);
		
		
	}

	public int confirmExit() {
	    return JOptionPane.showConfirmDialog(
	        this,
	        "¿Seguro que deseas regresar? Se perderán todos los datos",
	        "¿Seguro?",
	        JOptionPane.YES_NO_OPTION
	    );
	}
	
	public void crearBarraNav() {
		JPanel barraNav = new JPanel(new FlowLayout(FlowLayout.LEFT));
		botonHome = new JButton("Inicio");
		botonVerUsuario = new JButton("Usuarios");
		
		barraNav.add(botonHome);
		barraNav.add(botonVerUsuario);
		
		add(barraNav, BorderLayout.NORTH);		
		
	}
	
	public void crearVistas() {
		cardLayout = new CardLayout();
		contenedor = new JPanel(cardLayout);
		
		JPanel panelHome = new JPanel();
		panelHome.add(new JLabel("BIenvenido al sistema"));
		panelUsuario = new UsuarioView();
		
		contenedor.add(panelHome, HOME);
		contenedor.add(panelUsuario, USERS);
		
		add(contenedor, BorderLayout.CENTER);
	}
	public void mostrarVista(String vista) {
		cardLayout.show(contenedor, vista);
	}
	
}

