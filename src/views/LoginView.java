package views;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridBagLayoutInfo;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.imageio.ImageIO;
import assets.Colores;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import assets.GestorCursor;
import exception.InvalidPasswordException;
import exception.InvalidUserException;
import assets.AppFonts;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class LoginView extends JPanel{
	
	//Se declara todos los elementos necesarios al panel
	LoginWindow ventana;
	JLabel labelNombreEmail = new JLabel(); //Labels son los rectangulos donde solo van texto* mientras que fields son campos donde se selecciona o agrega informacion
	JLabel labelContrasenia = new JLabel();
	JTextField campoEmail = new JTextField();
	JPasswordField campoContrasenia = new JPasswordField();
	JLabel labelErrorNombreEmail = new JLabel();
	JLabel labelErrorContrasenia = new JLabel();
	GridBagConstraints c = new GridBagConstraints();
	public LoginView(LoginWindow ventana) {
		
		this.ventana = ventana;
		setLayout(new BorderLayout());
		setBackground(Colores.colorear(3));
		
		campoEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoEmail.getPreferredSize().height));
		campoContrasenia.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoContrasenia.getPreferredSize().height));
		add(crearImagenLogo(), BorderLayout.NORTH);
		add(crearLoginPanel(), BorderLayout.CENTER);

	
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------ METODOS DE CONFIGURACION -------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - Metodo para crear un campo con su texto
	
	
	/*
	 * Metodo privado que devuelve un JPanel, requiere de un String(para el label que describre brevemente el campo), un Objeto referencia de "Component" ya sea un textFiel, password field entre otros*,
	 * y por último pide un JLabel de "Error" si no esta lleno el campo*, este label se crea con el metodo "crearLableError" previamente.
	 * Proceso:
	 * = Se crea un panel, y se asigana los siguientes atributos:
	 * 			# Color cyan ---->  cambiar a transparente o cafe designado <-----
	 * 			# Se crea un borde vacio con 5 pixeles de borde en la parte Superior, e inferior, los laterales quedan en 0
	 * 			# Se crea una capa que se ordenara de manera lineal hacia abajo con BoxLayaout, que se colocara dentro del panel y cuya orientación será en el eje Y
	 * 			# El panel se alineará el componente en el centro del eje X
	 * 			# El tamaño del panel será en un maximo de 350 px de largo y 70 de alto
	 *  = Se crea el Label del panel que indica la descripcion breve del campo a llenar
	 *  		# Asiga el tamaño maximo del Label, ocupara el maximo valor posible en lo ancho, y tendra el tamaño de preferencia en la altura respect al panel
	 * 			# La orientacion horizontal del texto que tendra el label será hacia la isquierda con rexpecto al componente ingresado
	 * 			#El aliniamiento del label en el eje X sera con respecto al centro del centro del componente  
	 */
	
	ventana.addWindowListener(new WindowListener() {
		
		@Override
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
	
	private JPanel crearField(String texto, Component componenteDelLabel, JLabel labelTextoDelError) {
		JPanel panel = new JPanel();
		panel.setBackground(Colores.colorear(1));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setMaximumSize(new Dimension(350, 70));
		
		JLabel label = new JLabel(texto);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		labelTextoDelError.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(label);
		panel.add(componenteDelLabel);
		panel.add(labelTextoDelError);
		return panel;
		
	}
	private JLabel crearLabelError() {
		JLabel label = new JLabel();
		label.setFont(AppFonts.small());
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

		return label;
	}
	private JPanel crearPanelBoton(String texto1, String tipoDeListener) {

		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setMaximumSize(new Dimension(350, 60));
		//Se declara el boton
		JButton boton = new JButton(texto1);
		cambiarFondo(boton,Colores.colorear(1));
		
		
		//Depende del boton, tendra un listener propio
		if(tipoDeListener.equals("contrasenia")){
			boton.addActionListener(e -> pasarFormulario());
			panel.setBackground(Color.blue);
		}else if(tipoDeListener.equals("NombreUsuario")) {
			boton.addActionListener(e -> pasarLogin());
			panel.setBackground(Colores.colorear(1));
		}
		panel.add(boton);
		return panel;
	}
	private JPanel crearImagenLogo() {
		JPanel panel = new JPanel();
		panel.setBackground(Colores.colorear(1));
		try {
	        Image img = ImageIO.read(getClass().getResource("../assets/SteakGames.png"));
	        Image imgEscalada = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
	        JLabel labelLogo = new JLabel(new ImageIcon(imgEscalada)); 
	        panel.add(labelLogo);
	        
	    } catch (Exception ex) {
	        System.out.println("No se encuentra la imagen");
	    }
		return panel;
	}
	
	
	
	private JPanel crearLoginPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
		panel.setBackground(Colores.colorear(1));
		labelErrorNombreEmail = crearLabelError();
		labelErrorContrasenia = crearLabelError();
		
		panel.add(crearField("Usuario", campoEmail, labelErrorNombreEmail));
		panel.add(crearField("Constraseña", campoContrasenia, labelErrorContrasenia));
		panel.add(crearPanelBoton("Iniciar sesión", "NombreUsuario"));
		//panel.add(crearPanelBoton("Registrarse", "contrasenia"));
		panel.add(labelRegistro());
		
		return panel;
	}
	
	
	//------------------------------------------------- Validar el login
	private boolean validarNombreUsuario() {
		if(campoEmail.getText().trim().isEmpty()) {
			labelErrorNombreEmail.setText("Nombre de usuario es obligatorio");
			labelErrorNombreEmail.setVisible(true);
			return false;
		}
		return true;
		
	}
	private boolean validarContrasenia() {
		if(String.valueOf(campoContrasenia.getPassword()).trim().isEmpty() ) {
			labelErrorContrasenia.setText("La contraseña es obligatoria");
			labelErrorContrasenia.setVisible(true);
			return false;
		}
		return true;
		
	}
	private void resetearMensajesError() {
		labelErrorNombreEmail.setVisible(false);
		labelErrorContrasenia.setVisible(false);
	}
	private boolean validarLogin() throws InvalidUserException, InvalidPasswordException{
		boolean validado = false;
		resetearMensajesError();
		if(!validarNombreUsuario()) {
			validado = false;
			throw new InvalidUserException("jajajaja");
		}
		if(!validarContrasenia()) {	
			validado = false;
		}
		if(!campoEmail.getText().equals("Juan@gmail.com") && validarNombreUsuario()) {
			throw new InvalidUserException("Usuario laosdfsdakf");
		}
		if(!String.valueOf(campoContrasenia.getPassword()).trim().equals("Banana") && validarContrasenia()) {
			throw new InvalidPasswordException("errorrr");
		}
			
		if(validarContrasenia() && validarNombreUsuario()){
			validado = true;
		}
		return validado;
	}
	
	//---------------- Funcion para ver si se han llenado ambos campos para pasarñ
	private void pasarLogin() {
		try {
			if(validarLogin()) {
				JOptionPane.showMessageDialog(
					this,
					"Se inició la sesión",
					"Sesion iniciada",
					JOptionPane.INFORMATION_MESSAGE
				);
				new MainWindow();
				ventana.dispose();
			}
		} catch (InvalidUserException e) {
			resetearMensajesError();
			labelErrorNombreEmail.setVisible(true);
			labelErrorNombreEmail.setText("Nombre de usuario es obligatorio");
		} catch (InvalidPasswordException e) {
			System.out.println("lñhjkñluñik");
			labelErrorContrasenia.setVisible(true);
		}
	}
	private void pasarFormulario() {
		
			new FormularioRegistro();
			ventana.dispose();
		}
	
	private void cambiarFondo(JComponent c, Color color) {
		setBackground(color);
		setForeground(color);
		
	}
	private JPanel labelRegistro() {
		JPanel panel = new JPanel();
		JLabel lblRegister = new JLabel("¿No tienes cuenta? Regístrate aquí");
		panel.setBackground(Colores.colorear(1));
		lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblRegister.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				pasarFormulario();
			}
			
			public void mouseEntered(MouseEvent e) {
				lblRegister.setForeground(Colores.colorear(0));
			}
			
			public void mouseExited(MouseEvent e) {
				lblRegister.setForeground(Color.BLACK);
			}
		});
		
		
		//funcion para darle enter a iniciar sesion
		campoEmail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						validarLogin();
					} catch (InvalidUserException e1) {
						System.out.println("hola");
					} catch (InvalidPasswordException e1) {
						System.out.println("Adios");
					}
				}
			}
		});
		panel.add(lblRegister);
		return panel;
		}

	private void handleClose() {
		int option = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas regresar? Se perderán todos los datos");
		
		if(option == JOptionPane.YES_OPTION) {
			System.exit(0);
			/*new LoginWindow();
					dispose();*/
		}
		
	}

}

