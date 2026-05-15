package controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import exception.InvalidPasswordException;
import exception.InvalidUserException;
import models.Usuario;
import repositorio.LoginRepository;
import views.FormularioRegistro;
//import exceptions.InvalidPasswordException;
//import exceptions.InvalidUserException;
import views.LoginView;
import views.MainWindow;


public class LoginController {
	
	//Atributos
	private LoginRepository repositorio;
	
	
	private LoginView view;
	/**
	 * Constructor de View
	 * @param view
	 */
	public LoginController(LoginView view) {
		repositorio = new LoginRepository();
		this.view = view;
		registrerListener();
	}
	
	/**
	 * funcion para agregar las listener a todos los campos y botones
	 * Y asigna la funcion que realizaran
	 */
	private void registrerListener() {
		view.getLblRegister().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pasarFormulario();
			}
			
		});
		view.getBotonLogin().addActionListener(e -> pasarLogin());
		KeyAdapter teclaPasar = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					pasarLogin();
				}
			}
		};
		view.getCampoEmail().addKeyListener(teclaPasar);
		view.getCampoContrasenia().addKeyListener(teclaPasar);
		
		/**
		 * Agrega al LoginWindow un listener
		 * 
		 * @param windowListener                     |
		 * 
		 */
		view.getVentana().addWindowListener(new WindowListener() {
			
			@Override
			//Metodo para indicar en consola que se abrio en consola
			//@param windowEvent
			public void windowOpened(WindowEvent e) {
				System.out.println("Se abrió la ventana");
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				System.out.println("Se minimizó");
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				System.out.println("Se volvió a abrir");
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				System.out.println("Perdió el focus");
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				System.out.println("Se cerró");
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				System.out.println("Obtuvo el focus");			
			}
		});
	}
	
	/**
	 * Funcion que inmediatamente abre la otra ventana de formulario
	 */
	private void pasarFormulario() {
	    FormularioRegistro registroVista = new FormularioRegistro();
	    // otra modificacion para ver si esto era lo que duplicaba los usuarios(se crea el controlador una sola vez)
	    new RegistrationController(registroVista); 
	    view.getVentana().dispose();
	}
	
	
	/**
	 * Crea usuario temporal para el ejercicio Usa try catch
	 * intenta- Conseguir un usuario(El temporal) y comprueba que existe.
	 * Si es asi pasa a la ventana principal
	 * Sino Lanza un erro de excepcion 
	 */
	private void pasarLogin() {	
		
		if(!validarLogin(new Usuario(view.getCampoEmail(), view.getCampoContrasenia()))){
			return;
		}
	
	 Usuario usuarios = repositorio.login(view.getCampoEmail().getText(), String.valueOf(view.getCampoContrasenia().getPassword()));
	 if(usuarios == null) {
			mostrarErrorEmail("Credenciales invalidas");
			mostrarErrorContrasenia("Credenciales invalidas");
			return;
		}
		
		JOptionPane.showMessageDialog(view.getVentana(),  "Se inició la sesión", "Sesión iniciada", JOptionPane.INFORMATION_MESSAGE);
		new MainController(new MainWindow());
		
		view.getVentana().dispose();
		
	}
	 
	
	/**
	 * Metodo para confirmar cerrar ventana, Si presiona la opcion si, se cierra
	 */
	private void handleClose() {
		int option = JOptionPane.showConfirmDialog(view, "¿Seguro que deseas regresar? Se perderán todos los datos");
		if(option == JOptionPane.YES_OPTION) {
			System.exit(0);
		}else {
			;
		}
		
	}
	
	/**
	 * Obtiene el label de errod el campo correo y reescribe con el nuevo String
	 * @param texto
	 */
	private void mostrarErrorEmail(String texto) {
		view.getLabelErrorNombreEmail().setText(texto);
		view.getLabelErrorNombreEmail().setVisible(true);
	}
	
	/**
	 * Obtiene el label de error del campo contraseñoa, y reescribe un nuevo String
	 * @param texto
	 */
	private void mostrarErrorContrasenia(String texto) {
		view.getLabelErrorContrasenia().setText(texto);
		view.getLabelErrorContrasenia().setVisible(true);
	}
	
	/**
	 * Obtiene los label de Error de cada campo y los vuelve invisibles
	 */
	public void resetearMensajesError() {
		view.getLabelErrorNombreEmail().setVisible(false);
		view.getLabelErrorContrasenia().setVisible(false);
	}

	/**
	 * Si el campo indicado esta vacio, DEvuelve false caso contrario devuelve true
	 * @return
	 */
	private boolean validarLLenadoEmail() {
		if(view.getCampoEmail().getText().trim().isEmpty()) {
			mostrarErrorEmail("Correo incorrecto");
			return false;
		}
		return true;	
	}
	
	/**
	 * Si el campo indicado esta vacio devuelve false
	 * @return booleano
	 */
	private boolean validarLLenadoContrasenia() {
	if(String.valueOf(view.getCampoContrasenia().getPassword()).trim().isEmpty() ) {
		mostrarErrorContrasenia("Contraseña invalida");
		return false;
	}
	return true;
	
}
	/**
	 * Recibe un usuario y comprueba si primero tiene todos los campos estan llenos llamando una funcion
	 * Luego Comprueba si el correo de usuario es correcto, y tmabien comprueba si la contraseña es igual a la constraseña del usuario
	 * Si cumple con lo anterior devuelve true
	 * @param usuario
	 * @return booleano
	 * @throws InvalidUserException
	 * @throws InvalidPasswordException
	 */
	private boolean validarLogin(Usuario usuario){
		boolean validado = false;
		resetearMensajesError();
		if(!validarLLenadoEmail()) {
			validado = false;
		}
		if(!validarLLenadoContrasenia()) {	
			validado = false;
		}
			
		if(validarLLenadoContrasenia() && validarLLenadoEmail()){
			validado = true;
		}
		return validado;
	}
	
}

