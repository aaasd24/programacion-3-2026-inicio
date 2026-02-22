package views;
import java.awt.BorderLayout;
import java.awt.event.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class LoginView extends JPanel{
	public Color maronLeve = new Color(158, 118 , 79);
	public Color blancoIvory = new Color(244, 249 , 233);
	public Color grisGunmetal = new Color(53, 57 , 60);
	public Color rosaNeon = new Color(244, 0 , 118);
	public Color azulGlaous = new Color(119, 133, 172);
	 
	
	public LoginView() {
		
		//Definir el fondo principal
		setBackground(azulGlaous);
		setLayout(new BorderLayout());
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		//add(Box.createVerticalGlue());
		
		
		//Crear 5 paneles para estructurar la primera faceta
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.GREEN);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.BLUE);
		panelCentral.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panelInferior = new JPanel();
		panelInferior.setBackground(Color.RED);
		
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBackground(Color.PINK);
		
		JPanel panelDerecho = new JPanel();
		panelDerecho.setBackground(Color.ORANGE);
		
		//Botones al panel superior
		for (int i = 0; i < 10; i++) {
			JButton b = new JButton(i + "");
			panelInferior.add(b);
			panelInferior.add(Box.createVerticalGlue());
		}
		
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
		panelIzquierdo.add(label1);
		panelDerecho.add(label2);

		JTextField textoInicioCuenta = new JTextField();
		textoInicioCuenta.setForeground(Color.BLACK);
		textoInicioCuenta.setFont(new Font("Arial", Font.PLAIN, 30));
		textoInicioCuenta.setSize(20, 20);
		
		c.gridheight = 3;
		c.gridwidth = 3;
		c.weightx = 4;
		c.weighty = 2;
		c.gridx = 2;
		c.gridy = 0;
		panelCentral.add(textoInicioCuenta, c);
		
		
		try {
	        Image img = ImageIO.read(getClass().getResource("../img/SteakGames.png"));
	        Image imgEscalada = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
	        JLabel labelLogo = new JLabel(new ImageIcon(imgEscalada)); 
	        panelSuperior.add(labelLogo);
	        
	    } catch (Exception ex) {
	        System.out.println("No se encuentra la imagen");
	    }
	}
	
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
	public void Boton() {
			setBackground(maronLeve);
			JButton boton = new JButton("Iniciar sesion"); // Se crea el objeto boton de la librerias swim
			boton.setBackground(azulGlaous);
			boton.setForeground(Color.WHITE);
			boton.setBounds(270, 500, 250, 50); //Ubica y establce tamaño de boton
			
			/*try {
				Image icono = ImageIO.read(getClass().getResource("../img/icono.png"));
				icono = icono.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				boton.setIcon(new ImageIcon(icono));
			}catch(Exception ex) {
				System.out.println("NO se encuentra imagen");
			}*/
			
			add(boton); //Agrega el boton
		
	}
	
}
