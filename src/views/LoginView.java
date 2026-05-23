package views;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import assets.Colores;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

import assets.AppFonts;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class LoginView extends JPanel{
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------
	//----------------------------------------------------------------- A T R I B U T O S -------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/*	
	 * 
	 * Se declara la ventana del login
	 */
	LoginWindow ventana;
	
	/*
	 * Se declara labeles de Email y contraseña
	 */
	JLabel labelNombreEmail = new JLabel(); //Labels son los rectangulos donde solo van texto* mientras que fields son campos donde se selecciona o agrega informacion
	JLabel labelContrasenia = new JLabel();
	
	/*
	 * Se declara los campos para Email y contraseña
	 */
	JTextField campoEmail = new JTextField();
	JPasswordField campoContrasenia = new JPasswordField();
	
	/*
	 * Se declara los lables de Error de Email y contraseña
	 */
	JLabel labelErrorNombreEmail = new JLabel();
	JLabel labelErrorContrasenia = new JLabel();
	
	/*
	 * SSe declara el Layer en que se trabaja
	 */
	GridBagConstraints c = new GridBagConstraints();
	
	JButton botonLogin = new JButton();
	
	JLabel lblRegister = new JLabel("¿No tienes cuenta? Regístrate aquí");
	

	/**
	 * Contructor de LoginView (Lo que se muestra en el frame
	 * 
	 * @param Loginwindow
	 *  
	 */
	public LoginView(LoginWindow ventana) {
		/*
		 * Se declara la ventana
		 */
		this.ventana = ventana;
		
		/*
		 * Se establese el Layout
		 */
		setLayout(new BorderLayout());
		/*
		 * Se establese el color del fondo
		 */
		setBackground(Colores.colorear(1));
		
		
		/*
		 * Se declara el tamaño maximo de los campos Email y Contraseñia, el ancho el maximo posible y el alto en el tamaño presente
		 */
		campoEmail.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoEmail.getPreferredSize().height));
		campoEmail.setBackground(new Color(255, 255, 255, 200)); // El 200 le da un toque de transparencia
		campoEmail.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,50)));
		campoContrasenia.setMaximumSize(new Dimension(Integer.MAX_VALUE, campoContrasenia.getPreferredSize().height));
		campoContrasenia.setBackground(new Color(255, 255, 255, 200)); // El 200 le da un toque de transparencia
		campoContrasenia.setBorder(BorderFactory.createLineBorder(new Color(255,255,255,50)));
		
		
		
		/*
		 * Se agregan a LoginWiew la imagen del logo en la parte NORTE y el panel del login en el centro
		 */
		
		add(crearImagenLogo(), BorderLayout.NORTH);
		add(crearLoginPanel(), BorderLayout.CENTER);
 }
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------  M E T O D O S -----------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * Metodo para crear un panel con campo, label pprincipal y con label Error
	 * 
	 * @param String texto
	 * @param Component componenteDelLabel
	 * @param JLabel labelTextoDelError
	 * 
	 * @return JPanel
	 */
	private JPanel crearField(String texto, Component componenteDelLabel, JLabel labelTextoDelError) {
		
		/*
		 * Se declara Panel donde se agrega todos los componentes
		 */
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		
		/*
		 * Se declara:
		 * Fondo de panel de color cafe***
		 * Borde de aproximado 5 de grosor en los laterales
		 * Layout de BoxLayout
		 * ALineamiento centrado
		 * Tamaño maximo de 350 de ancho y 70 de alto
		 */
		//panel.setBackground(Colores.colorear(1));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setMaximumSize(new Dimension(350, 70));
		
		/**
		 * Se declara Label de mensaje
		 * 
		 * @param String
		 */
		JLabel label = new JLabel(texto);
		
		/*
		 * Se declara:
		 * Tamaño maximo abchura a su maximo posible y alto del tamaño del label
		 * Alineacion Horizontal de sentido a la izquierda
		 * Alineamiento en X en el centro
		 */
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
		label.setForeground(Color.WHITE);
		label.setFont(AppFonts.normal());
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		/*
		 * Se Declara la alineacion X del Label Error en el centro 
		 */
		labelTextoDelError.setAlignmentX(Component.CENTER_ALIGNMENT);
		/*
		 * Se agerga a panel:
		 * El label del mensaje
		 * El Campo respectivo del Label
		 * El Mensaje Error del Label
		 */
		panel.add(label);
		panel.add(componenteDelLabel);
		panel.add(labelTextoDelError);
		panel.setOpaque(false);
		
		//Devuelve el panel
		return panel;
		
	}
	
	/**
	 * Metodo para crear y definir el label de Error
	 * 
	 * @param devuelve un JLabel
	 */
	private JLabel crearLabelError() {
		/*
		 * Se crea un Jlabel
		 */
		JLabel label = new JLabel(" ");
		/*
		 * Se declara lo siguiente al JLabel:
		 * El tamaño de letra a pequeño
		 * Color de letra a ROJO
		 * Alineacion Horizontal(O del renglon) en sentido hacia la izquierda
		 * Tamaño maximo con el ancho a valor maximo y su altura de acuerdo al tamaño de label normal
		 */
		label.setFont(AppFonts.small());
		label.setForeground(Color.RED);
		label.setBackground(Color.BLUE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

		return label;
	}
	
	/**
	 * Metodo para crear un Boton para el Login
	 * 
	 * @param String texto del mensaje dentro del boton
	 * @param String para indicar el tipo de listener necesario
	 * 
	 * @return JPanel
	 */
	private JPanel crearPanelBoton(String texto1) {

		/*
		 * Se Crea un panel
		 */
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		/**
		 * Se declara la Alineacion en x de manera central
		 * Se declara el tamaño macimo de 350 de ancho y 60 de alto
		 */
		//panel.setBackground(Colores.colorear(1));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setMaximumSize(new Dimension(350, 60));
		
		/**
		 *Se declara el boton 
		 */
	    botonLogin.setText(texto1);
	    botonLogin.setFont(AppFonts.normal()); 
	    botonLogin.setBackground(new Color(45, 45, 45)); // Gris oscuro
	    botonLogin.setForeground(Color.WHITE);
	    botonLogin.setOpaque(false);		
	    botonLogin.setContentAreaFilled(true);
	    
	    // Esto es para que se vea el color pero respete la forma redondeada de FlatLaf
	    botonLogin.putClientProperty("JButton.buttonType", "roundRect");
		
		botonLogin.setBorderPainted(false);
	   // botonLogin.setContentAreaFilled(false);
	    botonLogin.setFocusPainted(false);
	    botonLogin.setPreferredSize(new Dimension(200, 50));
	    botonLogin.setMaximumSize(new Dimension(200, 50));
		
		
		/**
		 * Agrega el boton a panel
		 */
		panel.add(botonLogin);
		return panel;
	}
	/**
	 * Crea la imagen del logo sobre un panel
	 * 
	 * @return JPanel
	 */
	private JPanel crearImagenLogo() {
		/*
		 * Crea un panel y le asigna el color cafe
		 */
		JPanel panel = new JPanel();
		panel.setBackground(Colores.colorear(1));
		/*
		 * Utiliza trycatch, intenta conseguir la imagen del directorio y lo agrga al panel
		 * Sino arroja una excepcion e imprime que no existe imagen
		 */
		/*try {
	        Image img = ImageIO.read(getClass().getResource("../assets/SteakGames.png"));
	        Image imgEscalada = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
	        JLabel labelLogo = new JLabel(new ImageIcon(imgEscalada)); 
	        panel.add(labelLogo);
	        
	    } catch (Exception ex) {
	        System.out.println("No se encuentra la imagen");
	    }*/
		return panel;
	}
	
	/**
	 * Crea el panel completo del Login
	 * 
	 * @Return Panel con todo ajustado
	 */
	private JPanel crearLoginPanel() {
		/**
		 * Crea Panel
		 */
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		
		/*
		 * Asigna al panel:
		 * UN layout tipo box ajustado para que se acomoden respecto a Y
		 * Agrega un borde de ciertas medidas
		 * El color cafe
		 */
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createVerticalStrut(180));
		panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
		//panel.setBackground(Colores.colorear(1));
		/*
		 * Se crean correctamente todos los label de Error
		 */
		labelErrorNombreEmail = crearLabelError();
		labelErrorContrasenia = crearLabelError();
		
		/*
		 * Se agrega todos los elementos por paneles separados, Uno para Usuario, otro para Contraseña, otro para el boton de registro y otro para un label de registro a nuevo usuario
		 */
		panel.add(Box.createVerticalGlue());
		panel.add(crearField("Usuario", campoEmail, labelErrorNombreEmail));
		panel.add(crearField("Constraseña", campoContrasenia, labelErrorContrasenia));
		botonLogin.setPreferredSize(new Dimension(200, 50));
		panel.add(Box.createVerticalStrut(20));
		panel.add(crearPanelBoton("Iniciar Sesión"));
		panel.add(Box.createVerticalGlue());
		panel.add(crearRegistro());
		
		return panel;
	}
	
	/**
	 * Crea el label en la que se puede registrar un nuevo usurio
	 * 
	 * @return devuelve un panel donde aparece el label y que funcionara como boton
	 */
	private JPanel crearRegistro() {
		/*
		 * Se crea un panel
		 */
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		//Define el color del fondeo del label
		//panel.setBackground(Colores.colorear(1));
		/*
		 * Establece al label la capacidad de cambiar el cursor(por el tenerdor con carnte) y que tenga un listener para cambiar de color
		 */
		lblRegister.setForeground(Colores.colorear(4));
		lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblRegister.addMouseListener(new MouseAdapter() {
			/**
			 * Funciones de evento, cuando colisiona el mouse con el label
			 * Si el raton toca el label, cambia a blanco
			 * Sino es negro
			 * 
			 * @param  Requiere del evento de Mouse
			 */
			public void mouseEntered(MouseEvent e) {
				getLblRegister().setForeground(Colores.colorear(0));
			}

			public void mouseExited(MouseEvent e) {
				getLblRegister().setForeground(Color.BLACK);
			}
			
		});
		/*
		 * Agregar el label al panel
		 */
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(lblRegister);
		return panel;
		}

	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------- M E T O D O S     D E      C O N F I G U R A C I O N --------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	
	/*
	 *Funciona para cambiar el color de componente 
	 */
	private void cambiarFondo(JComponent c, Color color) {
		c.setBackground(color);
		c.setForeground(color);
		
	}
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	//--------------------------------------------------------------------- S E T T E R S ---------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
	/**
	 * @return the ventana
	 */
	public Window getVentana() {
		return ventana;
	}


	/**
	 * @return the labelNombreEmail
	 */
	public JLabel getLabelNombreEmail() {
		return labelNombreEmail;
	}


	/**
	 * @return the labelContrasenia
	 */
	public JLabel getLabelContrasenia() {
		return labelContrasenia;
	}


	/**
	 * @return the campoEmail
	 */
	public JTextField getCampoEmail() {
		return campoEmail;
	}


	/**
	 * @return the campoContrasenia
	 */
	public JPasswordField getCampoContrasenia() {
		return campoContrasenia;
	}


	/**
	 * @return the labelErrorNombreEmail
	 */
	public JLabel getLabelErrorNombreEmail() {
		return labelErrorNombreEmail;
	}


	/**
	 * 
	 * @return the labelErrorContrasenia
	 */
	public JLabel getLabelErrorContrasenia() {
		return labelErrorContrasenia;
	}


	/**
	 * @return the lblRegister
	 */
	public JLabel getLblRegister() {
		return lblRegister;
	}
	/**
	 * @return the botonLogin
	 */
	public JButton getBotonLogin() {
		return botonLogin;
	}
	
	public String getContrasenia() {
		return String.valueOf(getCampoContrasenia().getPassword());
	}

	@Override
	protected void paintComponent(java.awt.Graphics g) {
	    super.paintComponent(g); 
	    
	    try {
	    
	        Image fondo = ImageIO.read(getClass().getResource("../assets/photoshop_del_login-version_alt.jpg"));
	        
	        
	        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
	        
	        
	        g.setColor(new Color(0, 0, 0, 100)); 
	        g.fillRect(0, 0, getWidth(), getHeight());
	        
	    } catch (Exception e) {
	       
	        System.out.println("no se encuentra la imagen");
	    }
	}
	

}