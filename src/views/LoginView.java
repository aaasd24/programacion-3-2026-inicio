package views;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridBagLayoutInfo;
import java.awt.Image;
import java.awt.Insets;

import javax.imageio.ImageIO;
import assets.Colores;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import assets.GestorCursor;
import assets.AppFonts;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginView extends JPanel{
	
	 
	
	public LoginView() {
		
		//Definir el fondo principal
		setBackground(Colores.colorear(1));
		setLayout(new BorderLayout());
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//add(Box.createVerticalGlue());
		
		
		//Crear 5 paneles para estructurar la primera faceta
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Colores.colorear(1));
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Colores.colorear(1));
		panelCentral.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Colores.colorear(1));
		
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(Colores.colorear(1));
		
		JPanel panelDerecho = new JPanel();
		panelDerecho.setBackground(Colores.colorear(1));
		
		
		Border emptyBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		setBorder(emptyBorder);
		
		
		add(panelSuperior, BorderLayout.NORTH);
		add(panelDerecho, BorderLayout.LINE_END);
		add(panelIzquierdo, BorderLayout.LINE_START);
		add(panelInferior, BorderLayout.SOUTH);
		add(panelCentral, BorderLayout.CENTER);
		
		
		
		JLabel label1 = new JLabel("Hola");
		JLabel label2 = new JLabel("Hola");
		label1.setForeground(Color.black);
		label1.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JLabel labelIniciarSesion = new JLabel("Ingrese usuario");
		//prueba de texto
		labelIniciarSesion.setFont(AppFonts.normal());
		labelIniciarSesion.setForeground(Color.black);
		//labelIniciarSesion.setFont(new Font("Arial", Font.PLAIN, 20));

		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 1; //En la segunda columna
		c.gridy = 0; //En la primera fila
		c.weighty = 0; //Especifica como distribuir el espacio vertical, 
		c.gridheight = 1; //Es el espacio entre los componentes siguiente, si es 0 se sobreponen, con 1 se separan de acuerdo a su tamaño, pero se quedan juntos
		panelCentral.add(labelIniciarSesion, c);
		
		JTextField textoInicioCuenta = new JTextField();
		textoInicioCuenta.setForeground(Color.BLACK);
		//prueba de texto
		textoInicioCuenta.setFont(AppFonts.normal());
		//textoInicioCuenta.setFont(new Font("Arial", Font.PLAIN, 30));
		textoInicioCuenta.setSize(20, 20);
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(10,0,0,0);
		c.gridx = 1; //En la segunda columna
		c.gridy = 1; //En la segunda fila
		c.weighty = 0;
		c.ipadx = 200;

		panelCentral.add(textoInicioCuenta, c);
		
		JLabel labelContrasenia = new JLabel("Ingrese contraseña");
		labelContrasenia.setForeground(Color.black);
		//prueba de texto
		labelContrasenia.setFont(AppFonts.normal());
		//labelContrasenia.setFont(new Font("Arial", Font.PLAIN, 20));
		c.anchor = GridBagConstraints.PAGE_START;
		c.insets = new Insets(50,0,0,0);
		//c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1; //En la segunda columna
		c.gridy = 2; //En la tercera fila
		c.ipadx = 0;
		c.weighty = 0;  
		panelCentral.add(labelContrasenia, c);
		
		
		JPasswordField contra = new JPasswordField();
		contra.setFont(new Font("Arial", Font.PLAIN, 30));
		c.insets = new Insets(10,0,0,0);
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 1; //En la segunda columna
		c.gridy = 3; //En la cuarta fila
		c.ipadx = 200;
		c.weighty = 1;
		panelCentral.add(contra, c);
		
		//setBackground(azulGlaous);
		JButton boton = new JButton("Iniciar sesión"); // Se crea el objeto boton de la librerias swim
		boton.setBackground(Colores.colorear(2));
		boton.setForeground(Color.WHITE);
		c.anchor = GridBagConstraints.PAGE_START;
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
	}
	/*try {
	Image icono = ImageIO.read(getClass().getResource("../img/icono.png"));
	icono = icono.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	boton.setIcon(new ImageIcon(icono));
}catch(Exception ex) {
	System.out.println("NO se encuentra imagen");
}*/
	
	public static void crearEntradaDeDatos(JPanel panel) {
		JTextField textoInicioCuenta = new JTextField();
		textoInicioCuenta.setForeground(Color.BLACK);
		textoInicioCuenta.setFont(new Font("Arial", Font.PLAIN, 30));

		panel.add(textoInicioCuenta);
	}
	
	public void crearEntradaContrasenia() {
		JPasswordField contra = new JPasswordField();
		contra.setFont(new Font("Arial", Font.PLAIN, 30));
		contra.setBounds(200, 400, 400, 40);
		add(contra);
	}
	
	
}
