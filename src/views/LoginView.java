package views;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridBagLayoutInfo;
import java.awt.Image;
import java.awt.Insets;

import javax.imageio.ImageIO;
import assets.Colores;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import assets.GestorCursor;
import javaProyect1.MyVentana;
import assets.AppFonts;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class LoginView extends JPanel{
	MyVentana ventana;
	JLabel labelNombreEmail = new JLabel(); //Labels son los rectangulos donde solo van texto* mientras que fields son campos donde se selecciona o agrega informacion
	JLabel labelContrasenia = new JLabel();
	JTextField campoEmail = new JTextField();
	JPasswordField campoContrasenia = new JPasswordField();
	JLabel labelErrorNombreEmail = new JLabel();
	JLabel labelErrorContrasenia = new JLabel();
	GridBagConstraints c = new GridBagConstraints();
	public LoginView(MyVentana ventana) {
		this.ventana = ventana;
		setLayout(new BorderLayout());
		setBackground(Colores.colorear(3));
		
		add(crearImagenLogo(), BorderLayout.NORTH);
		add(crearLoginPanel(), BorderLayout.CENTER);
		//add(crearPanelBoton(), BorderLayout.SOUTH);
		
		//Definir el fondo principal
		//setBackground(Colores.colorear(1));
		//setLayout(new BorderLayout());		
		
		//Crear 5 paneles para estructurar la primera faceta
		/*	
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Colores.colorear(1));
		
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(Colores.colorear(1));
		
		JPanel panelDerecho = new JPanel();
		panelDerecho.setBackground(Colores.colorear(1));
		*/
		/*
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Colores.colorear(1));
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Colores.colorear(1));
		panelCentral.setLayout(new GridBagLayout());
		
		
		Border emptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		setBorder(emptyBorder);*/
		
		/*
		add(panelSuperior, BorderLayout.NORTH);
		
		add(panelDerecho, BorderLayout.LINE_END);
		add(panelIzquierdo, BorderLayout.LINE_START);
		add(panelInferior, BorderLayout.SOUTH);
		add(panelCentral, BorderLayout.CENTER);
		
		
		labelIniciarSesion = new JLabel("Ingrese usuario");
		labelIniciarSesion.setFont(AppFonts.normal());
		labelIniciarSesion.setForeground(Color.black);
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 1; //En la segunda columna
		c.gridy = 0; //En la primera fila
		c.weighty = 0; //Especifica como distribuir el espacio vertical, 
		c.gridheight = 1; //Es el espacio entre los componentes siguiente, si es 0 se sobreponen, con 1 se separan de acuerdo a su tamaño, pero se quedan juntos
		panelCentral.add(labelIniciarSesion, c);
		
		textoInicioCuenta = new JTextField();
		textoInicioCuenta.setForeground(Color.BLACK);
		textoInicioCuenta.setFont(AppFonts.normal());
		textoInicioCuenta.setSize(20, 20);
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(10,0,0,0);
		c.gridx = 1; //En la segunda columna
		c.gridy = 1; //En la segunda fila
		c.weighty = 0;
		c.ipadx = 200;

		panelCentral.add(textoInicioCuenta, c);
		
		labelErrorUsuario = new JLabel("");
		labelErrorUsuario.setFont(AppFonts.small());
		labelErrorUsuario.setForeground(Color.red);
		labelErrorUsuario.setVisible(true);
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(00,0,0,0);
		c.gridx = 1; //En la segunda columna
		c.gridy = 2; //En la tercera fila
		c.ipadx = 0;
		c.weighty = 0;  
		panelCentral.add(labelErrorUsuario, c);
		
		JLabel labelContrasenia = new JLabel("Ingrese contraseña");
		labelContrasenia.setForeground(Color.black);
		labelContrasenia.setFont(AppFonts.normal());
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(50,0,0,0);
		c.gridx = 1; //En la segunda columna
		c.gridy = 2; //En la tercera fila
		c.ipadx = 0;
		c.weighty = 0;  
		panelCentral.add(labelContrasenia, c);
		
		
		contrasenia = new JPasswordField();
		contrasenia.setFont(AppFonts.normal());
		c.insets = new Insets(10,0,0,0);
		c.anchor = GridBagConstraints.PAGE_START;
		textoInicioCuenta.setSize(20, 20);
		c.gridx = 1; //En la segunda columna
		c.gridy = 3; //En la cuarta fila
		c.ipadx = 200;
		c.weighty = 1;
		panelCentral.add(contrasenia, c);
		
		labelErrorContrasenia = new JLabel("");
		labelErrorContrasenia.setFont(AppFonts.small());
		labelErrorContrasenia.setForeground(Color.red);
		labelErrorContrasenia.setVisible(true);
		//labelContrasenia.setFont(new Font("Arial", Font.PLAIN, 20));
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(55,0,0,0);
		c.gridx = 1; //En la segunda columna
		c.gridy = 3; //En la Cuarta fila
		c.ipadx = 0;
		c.weighty = 0;  
		panelCentral.add(labelErrorContrasenia, c);
		
		//setBackground(azulGlaous);
		JButton boton = new JButton("Iniciar sesión"); // Se crea el objeto boton de la librerias swim
		boton.setBackground(Colores.colorear(2));
		boton.setForeground(Color.WHITE);
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(0,0,0,0);
		c.gridx = 1; //En la segunda columna
		c.gridy = 4; //En la quinta fila
		c.ipadx = 0; //Vuelve al tamaño original
		c.weighty = 5;
		panelCentral.add(boton, c);
		
		try {
	        Image img = ImageIO.read(getClass().getResource("../assets/SteakGames.png"));
	        Image imgEscalada = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
	        JLabel labelLogo = new JLabel(new ImageIcon(imgEscalada)); 
	        panelSuperior.add(labelLogo);
	        
	    } catch (Exception ex) {
	        System.out.println("No se encuentra la imagen");
	    }
		
		boton.addActionListener(e -> pasarLogin());*/
	}
	//---------------------------------- metodos de configuracion
	private JPanel crearField(String texto, Component componenteDelLabel, JLabel labelTextoDelError) {
		JPanel panel = new JPanel();
		panel.setBackground(Colores.colorear(1));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.setMaximumSize(new Dimension(350, 60));
		
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
	private JPanel crearPanelBoton(String texto1, String texto2) {

		JPanel panel = new JPanel();

		JButton btnValidate = new JButton(texto1);
		btnValidate.addActionListener(e -> pasarLogin());
		JButton btnRegistrar = new JButton(texto2);
		btnRegistrar.addActionListener(e -> pasarFormulario());

		panel.add(btnValidate);
		panel.add(btnRegistrar);
		return panel;
	}
	private JPanel crearImagenLogo() {
		JPanel panel = new JPanel();
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
		labelErrorNombreEmail = crearLabelError();
		labelErrorContrasenia = crearLabelError();
		
		panel.add(crearField("Usuario", campoEmail, labelErrorNombreEmail));
		panel.add(crearField("Constraseña", campoContrasenia, labelErrorContrasenia));
		panel.add(crearPanelBoton("Iniciar sesión", "Registrarse"));

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
		if(String.valueOf(campoContrasenia.getPassword()).trim().isEmpty()) {
			labelErrorContrasenia.setText("La contraseña es obligatoria");
			labelErrorContrasenia.setVisible(true);
			return false;
		}
		return true;
		
	}
	private void resetearMensajesError() {
		//labelErrorContrasenia.setText("");
		//labelErrorNombreUsuario.setText("");
		labelErrorNombreEmail.setVisible(false);
		labelErrorContrasenia.setVisible(false);
	}
	private boolean validarLogin() {
		resetearMensajesError();
		if(!validarNombreUsuario()) {
			return false;
		}
		if(!validarContrasenia()) {
			return false;
		}
		return true;
	}
	
	//---------------- Funcion para ver si se han llenado ambos campos para pasarñ
	private void pasarLogin() {
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
	}
	private void pasarFormulario() {
		
			new FormularioRegistro();
			ventana.dispose();
		}
	
}
