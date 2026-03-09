package views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;

import assets.GestorCursor;
import assets.AppFonts;
import assets.Colores;
import assets.ErrorLabel;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import assets.GestorCursor;

public class FormularioRegistro extends JFrame {
	// mejor lo cambie a GridBagLayout para que no se estire todo como en el BoxLayout (el boton y demas)
	//Crear Componentes necesarios de la ventana
    JPanel panelComponentes = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    JTextField txtNombre = new JTextField(20);
    JLabel labelErrorNombreEmail = new JLabel();
	JLabel labelErrorContrasenia = new JLabel();
    JLabel labelErrorNombreUsuario = new JLabel();
    JTextField txtNombreCuenta;
    JLabel lblTituloFecha;
    JTextField txtAnioNacimiento;
    JComboBox<String> comboMeses;
    JComboBox<String> comboDias;
    JPanel panelRadio; 
    JRadioButton rbMujer;
    JRadioButton rbHombre;
    JRadioButton rbExtra; 
    ButtonGroup bgSexo;
    JComboBox<String> comboRegiones; 
    JPasswordField txtContra;
    JCheckBox chkAceptoCondiciones;
    JButton boton;
    JScrollPane scroll;
    private JLabel lblErrorNombre;
    private JLabel lblErrorCuenta;
	private JLabel lblErrorEmail;
	private JLabel lblErrorCombo;
	private JLabel lblErrorGenero;
	private JLabel lblErrorTerminos;
	private JLabel lblErrorDia;
	private JLabel lblErrorMes;
	private JLabel lblErrorAnio;
	private JLabel lblErrorRegion;
	public FormularioRegistro() {
	
		//atributos del formulario
		//NOmbre usuario y nombre de cuenta, Fecha de nacimiento, genero, datos bancarios y su contraseña y su csv. contraseña de cuenta, ubicacion/region 
		
		
		setSize(600, 680);
		setResizable(false);
		setTitle("Registrarse");
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
	    
		lblErrorNombre = createErrorLabel("a");
		lblErrorEmail = createErrorLabel("b");
		labelErrorContrasenia = createErrorLabel("c");
		lblErrorGenero = createErrorLabel("s");
		lblErrorTerminos = createErrorLabel("s");
		lblErrorAnio = createErrorLabel("s");
		lblErrorMes = createErrorLabel("s");
		lblErrorDia = createErrorLabel("s");
		lblErrorRegion = createErrorLabel("s");
	    // Configuracion de margenes 
	    c.insets = new java.awt.Insets(8, 8, 8, 8); 
	    c.fill = GridBagConstraints.HORIZONTAL;
	
	    GestorCursor.aplicarATodo(this);

	    // usuario
	    agregarComponente(0, 0, new JLabel("Nombre de usuario: "));
	    agregarComponente(0, 0, lblErrorNombre);
	    agregarComponente(1, 0, txtNombre);
	    /*agregarComponente(0, 3, new JLabel("Email: "));
	    agregarComponente(1, 3, txtNombreCuenta);
	    agregarComponente(0, 4, lblErrorEmail);
	    agregarComponente(1, 0, new JLabel("Nombre de usuario: "));*/

	   // agregarComponente(1, 2, crearErrorLabel("La cuenta es obligatoria", labelErrorNombreUsuario));
	    // nombre
	    c.gridx = 0; c.gridy = 1;
	    panelComponentes.add(new JLabel("Nombre de cuenta: "), c);
	    c.gridx = 1;
	    JTextField txtNombreCuenta = new JTextField(20);
	    panelComponentes.add(txtNombreCuenta, c);

	    // Título de la sección Fecha de nacimiento (Fila propia)
	    c.gridx = 0; c.gridy = 2;
	    c.gridwidth = 2; //acuerdate que son las columnas
	    lblTituloFecha = new JLabel("Fecha de nacimiento:");
	    lblTituloFecha.setFont(lblTituloFecha.getFont().deriveFont(java.awt.Font.BOLD));
	    panelComponentes.add(lblTituloFecha, c);
	    
	    // año 
	    c.gridwidth = 1; 
	    c.gridx = 0; c.gridy = 3;
	    panelComponentes.add(new JLabel("Año:"), c);
	    c.gridx = 1;
	     txtAnioNacimiento = new JTextField(20);
	    panelComponentes.add(txtAnioNacimiento, c);

	    // mes
	    c.gridx = 0; c.gridy = 4;
	    panelComponentes.add(new JLabel("Mes:"), c);
	    c.gridx = 1;
	    String meses[] = {"Seleccione", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	    comboMeses = new JComboBox<>(meses);
	    panelComponentes.add(comboMeses, c);

	    // dia
	    c.gridx = 0; c.gridy = 5;
	    panelComponentes.add(new JLabel("Dia:"), c);
	    c.gridx = 1;
	    String dias[] = {"Seleccione", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	    comboDias = new JComboBox<>(dias);
	    panelComponentes.add(comboDias, c);

	    // radiobuttons
	    c.gridx = 0; c.gridy = 6;
	    panelComponentes.add(new JLabel("Sexo"), c);
	    
	    panelRadio = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
	    rbMujer = new JRadioButton("Mujer");
	    rbHombre = new JRadioButton("Hombre");
	    rbExtra = new JRadioButton("Otro");
	    bgSexo = new ButtonGroup();
	    bgSexo.add(rbHombre); bgSexo.add(rbMujer); bgSexo.add(rbExtra);
	    panelRadio.add(rbMujer); panelRadio.add(rbHombre); panelRadio.add(rbExtra);
	    
	    c.gridx = 1;
	    panelComponentes.add(panelRadio, c);

	    // region
	    c.gridx = 0; c.gridy = 7;
	    panelComponentes.add(new JLabel("Región"), c);
	    c.gridx = 1;
	    String regiones[] = {"Seleccione", "MEXICO", "PERU", "MIAMI", "LOS ANGELES", "OCEANIA", "JAPON", "CHINA", "INDIA", "ALASKA", "POLO SUR", "LONDRES", "NIGERIA"};
	    comboRegiones = new JComboBox<>(regiones);
	    comboRegiones.setSelectedIndex(0);
	    panelComponentes.add(comboRegiones, c);

	    // contraseña
	    c.gridx = 0; c.gridy = 8;
	    panelComponentes.add(new JLabel("Contraseña:"), c);
	    c.gridx = 1;
	    txtContra = new JPasswordField();
	    panelComponentes.add(txtContra, c);

	    // terminos
	    c.gridx = 0; c.gridy = 9;
	    c.gridwidth = 2; 
	    chkAceptoCondiciones = new JCheckBox("He leído y aceptado los terminos y condiciones de la plataforma", true);
	    panelComponentes.add(chkAceptoCondiciones, c);

	    // boton
	    c.gridx = 0; c.gridy = 10;
	    c.gridwidth = 2;
	    c.ipady = 15; 
	    JButton boton = new JButton("Crear cuenta");
	    boton.setBackground(assets.Colores.colorear(1));
	    boton.addActionListener(e -> validateForm());
	    panelComponentes.add(boton, c);

	    // todo pal scroll
	    scroll = new JScrollPane(panelComponentes);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    add(scroll);
	    //con esto hago el mouse jalar en todo//
	    assets.GestorCursor.aplicarATodo(this);
	    assignListeners();
	}
	//add(panelComponentes);

	
	private void agregarComponente(int posColumna, int posFilas, Component componenteAgregado) {
		c.gridx = posColumna;
		c.gridy = posFilas;
		panelComponentes.add(componenteAgregado, c);
		
	}
	private JLabel crearErrorLabel(String texto, JLabel labelError) {
		labelError.setText(texto);
		labelError.setBackground(Color.red);
		labelError.setVisible(true);
		return labelError;
	}
	private JLabel createErrorLabel(String texto) {
		JLabel label = new JLabel(texto);
		label.setFont(AppFonts.small());
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

		return label;
	}
	private void validateForm() {
		resetErrorLabels();

		boolean valid = true;

		if (!validateName()) {
			valid = false;
		}

		if (!validateEmail()) {
			valid = false;
		}

		if(!validateComboBox()) 
        	valid = false;
        
        if(!validateGender()) 
        	valid = false;
        
        if(!validateTerms()) 
        	valid = false;
       

		if (valid) {
			JOptionPane.showMessageDialog(this, "Registro exitoso");
		}
		

	}
	private void resetErrorLabels() {
		labelErrorNombreEmail.setText("");
		lblErrorEmail.setText("");
		lblErrorCombo.setText("");
		lblErrorGenero.setText("");
		lblErrorTerminos.setText("");
		lblErrorRegion.setText("");
		lblErrorDia.setText("");
		lblErrorMes.setText("");
		lblErrorAnio.setText("");
	}
	private boolean validateName() {

		if (txtNombre.getText().trim().isEmpty()) {
			lblErrorNombre.setText("El nombre es obligatorio");
			return false;
		}

		return true;
	}

	private boolean validateEmail() {

		if (labelErrorNombreEmail.getText().trim().isEmpty()) {
			lblErrorEmail.setText("El email es obligatorio");
			return false;
		}

		if (!labelErrorNombreEmail.getText().contains("@")) {
			lblErrorEmail.setText("Email inválido");
			return false;
		}

		return true;
	}

	private boolean validateComboBox() {

		if (comboRegiones.getSelectedIndex() == 0) {
			lblErrorCombo.setText("Seleccione un país");
			return false;
		}

		return true;
	}

	private boolean validateGender() {

		if (!rbHombre.isSelected() && !rbMujer.isSelected()) {
			lblErrorGenero.setText("Seleccione un género");
			return false;
		}

		return true;
	}

	private boolean validateTerms() {

		if (! chkAceptoCondiciones.isSelected()) {
			lblErrorTerminos.setText("Debe aceptar los términos");
			return false;
		}

		return true;
	}
	private void assignListeners() {
		comboRegiones.addActionListener(e -> {
			validateComboBox();
		});
		
		chkAceptoCondiciones.addActionListener(e -> validateTerms());
		
		txtNombre.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validateName();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validateName();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validateName();
			}

			
			
		});
		
		
	}
}
