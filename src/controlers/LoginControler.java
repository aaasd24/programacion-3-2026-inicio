package controlers;

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
import views.FormularioRegistro;
//import exceptions.InvalidPasswordException;
//import exceptions.InvalidUserException;
import views.LoginView;
import views.MainWindow;


public class LoginControler {
	private LoginView view;
	
	public LoginControler(LoginView view) {
		this.view = view;
		registrerListener();
	}
	
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
		 * TODO requerrimos de esto forzosamente?    v
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

	private void pasarFormulario() {
			new FormularioRegistro();
			view.getVentana().dispose();
	}
	
	
	private void pasarLogin() {
		
		Usuario usuarioPresente = new Usuario("Juan", "tu@gmail.com", "Contra123");
		
		try {
			if(validarLogin(usuarioPresente)) {
				JOptionPane.showMessageDialog(
					view.getVentana(),
					"Se inició la sesión",
					"Sesion iniciada",
					JOptionPane.INFORMATION_MESSAGE
				);
				new MainWindow();
				view.getVentana().dispose();
			}
		} catch (InvalidUserException | InvalidPasswordException e) {
			mostrarErrorEmail("Credenciales incorrectas");
			mostrarErrorContrasenia("Credenciales incorrectas");
		}
	}
	/*
	 * Metodo para confirmar cerrar ventana
	 */
	private void handleClose() {
		int option = JOptionPane.showConfirmDialog(view, "¿Seguro que deseas regresar? Se perderán todos los datos");
		
		if(option == JOptionPane.YES_OPTION) {
			System.exit(0);
			/*new LoginWindow();
					dispose();*/
		}else {
			//System.exit(0);
		}
		
	}
	private void mostrarErrorEmail(String texto) {
		view.getLabelErrorNombreEmail().setText(texto);
		view.getLabelErrorNombreEmail().setVisible(true);
	}
	private void mostrarErrorContrasenia(String texto) {
		view.getLabelErrorContrasenia().setText(texto);
		view.getLabelErrorContrasenia().setVisible(true);
	}
	
	public void resetearMensajesError() {
		view.getLabelErrorNombreEmail().setVisible(false);
		view.getLabelErrorContrasenia().setVisible(false);
	}


	private boolean validarLLenadoEmail() {
		if(view.getCampoEmail().getText().trim().isEmpty()) {
			mostrarErrorEmail("Correo incorrecto");
			return false;
		}
		return true;
		
	}
	private boolean validarLLenadoContrasenia() {
	if(String.valueOf(view.getCampoContrasenia().getPassword()).trim().isEmpty() ) {
		mostrarErrorContrasenia("Contraseña invalida");
		return false;
	}
	return true;
	
}
	
	
	
	private boolean validarLogin(Usuario usuario) throws InvalidUserException, InvalidPasswordException{
		boolean validado = false;
		resetearMensajesError();
		if(!validarLLenadoEmail()) {
			validado = false;
		}
		if(!validarLLenadoContrasenia()) {	
			validado = false;
		}
		if(!view.getCampoEmail().getText().equals(usuario.getCorreo()) && validarLLenadoEmail()) {
			throw new InvalidUserException("Correo de usuario incompleto");
		}
		if(!String.valueOf(view.getCampoContrasenia().getPassword()).trim().equals(usuario.getContrasenia()) && validarLLenadoContrasenia()) {
			throw new InvalidPasswordException("Contrasenia erronea");
		}
			
		if(validarLLenadoContrasenia() && validarLLenadoEmail()){
			validado = true;
		}
		return validado;
	}
	
}

