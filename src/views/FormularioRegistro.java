package views;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import assets.GestorCursor;
import assets.AppFonts;
import assets.Colores;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import assets.GestorCursor;

public class FormularioRegistro extends JFrame {
	
	public FormularioRegistro() {
	
		//atributos del formulario
		//NOmbre usuario y nombre de cuenta, Fecha de nacimiento, genero, datos bancarios y su contraseña y su csv. contraseña de cuenta, ubicacion/region 
		
		
		setSize(600, 680);
		setResizable(false);
		setTitle("SteakGames");
		setLocationRelativeTo(null);
		//Siempre el set visible al final del constructor

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/assets/SteakGames.png");
		setIconImage(icono);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializarComponentes();
		setVisible(true);		
	}
	
	public void inicializarComponentes() {
	    // mejor lo cambie a GridBagLayout para que no se estire todo como en el BoxLayout (el boton y demas)
	    JPanel panelComponentes = new JPanel(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    
	    // Configuracion de margenes 
	    c.insets = new java.awt.Insets(8, 8, 8, 8); 
	    c.fill = GridBagConstraints.HORIZONTAL;
	
	    GestorCursor.aplicarATodo(this);

	    // usuario
	    c.gridx = 0; c.gridy = 0;
	    panelComponentes.add(new JLabel("Nombre de usuario: "), c);
	    c.gridx = 1;
	    JTextField txtNombre = new JTextField(20);
	    panelComponentes.add(txtNombre, c);

	    // nombre
	    c.gridx = 0; c.gridy = 1;
	    panelComponentes.add(new JLabel("Nombre de cuenta: "), c);
	    c.gridx = 1;
	    JTextField txtNombreCuenta = new JTextField(20);
	    panelComponentes.add(txtNombreCuenta, c);

	    // Título de la sección Fecha de nacimiento (Fila propia)
	    c.gridx = 0; c.gridy = 2;
	    c.gridwidth = 2; //acuerdate que son las columnas
	    JLabel lblTituloFecha = new JLabel("Fecha de nacimiento:");
	    lblTituloFecha.setFont(lblTituloFecha.getFont().deriveFont(java.awt.Font.BOLD));
	    panelComponentes.add(lblTituloFecha, c);
	    
	    // año 
	    c.gridwidth = 1; 
	    c.gridx = 0; c.gridy = 3;
	    panelComponentes.add(new JLabel("Año:"), c);
	    c.gridx = 1;
	    JTextField txtAnioNacimiento = new JTextField(20);
	    panelComponentes.add(txtAnioNacimiento, c);

	    // mes
	    c.gridx = 0; c.gridy = 4;
	    panelComponentes.add(new JLabel("Mes:"), c);
	    c.gridx = 1;
	    String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	    JComboBox<String> comboMeses = new JComboBox<>(meses);
	    panelComponentes.add(comboMeses, c);

	    // dia
	    c.gridx = 0; c.gridy = 5;
	    panelComponentes.add(new JLabel("Dia:"), c);
	    c.gridx = 1;
	    String dias[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	    JComboBox<String> comboDias = new JComboBox<>(dias);
	    panelComponentes.add(comboDias, c);

	    // radiobuttons
	    c.gridx = 0; c.gridy = 6;
	    panelComponentes.add(new JLabel("Sexo"), c);
	    
	    JPanel panelRadio = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
	    JRadioButton rbMujer = new JRadioButton("Mujer");
	    JRadioButton rbHombre = new JRadioButton("Hombre");
	    JRadioButton rbExtra = new JRadioButton("Si");
	    ButtonGroup bgSexo = new ButtonGroup();
	    bgSexo.add(rbHombre); bgSexo.add(rbMujer); bgSexo.add(rbExtra);
	    panelRadio.add(rbMujer); panelRadio.add(rbHombre); panelRadio.add(rbExtra);
	    
	    c.gridx = 1;
	    panelComponentes.add(panelRadio, c);

	    // region
	    c.gridx = 0; c.gridy = 7;
	    panelComponentes.add(new JLabel("Región"), c);
	    c.gridx = 1;
	    String regiones[] = {"MEXICO", "PERU", "MIAMI", "LOS ANGELES", "OCEANIA", "JAPON", "CHINA", "INDIA", "ALASKA", "POLO SUR", "LONDRES", "NIGERIA"};
	    JComboBox<String> comboRegiones = new JComboBox<>(regiones);
	    comboRegiones.setSelectedIndex(0);
	    panelComponentes.add(comboRegiones, c);

	    // contraseña
	    c.gridx = 0; c.gridy = 8;
	    panelComponentes.add(new JLabel("Contraseña:"), c);
	    c.gridx = 1;
	    JPasswordField txtContra = new JPasswordField();
	    panelComponentes.add(txtContra, c);

	    // terminos
	    c.gridx = 0; c.gridy = 9;
	    c.gridwidth = 2; 
	    JCheckBox chkAceptoCondiciones = new JCheckBox("He leído y aceptado los terminos y condiciones de la plataforma", true);
	    panelComponentes.add(chkAceptoCondiciones, c);

	    // boton
	    c.gridx = 0; c.gridy = 10;
	    c.gridwidth = 2;
	    c.ipady = 15; 
	    JButton boton = new JButton("Crear cuenta");
	    boton.setBackground(assets.Colores.colorear(1));
	    panelComponentes.add(boton, c);

	    // todo pal scroll
	    JScrollPane scroll = new JScrollPane(panelComponentes);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    add(scroll);
	    //con esto hago el mouse jalar en todo//
	    assets.GestorCursor.aplicarATodo(this);
	}
	//add(panelComponentes);
}
