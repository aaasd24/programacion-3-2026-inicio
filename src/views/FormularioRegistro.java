package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
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

@SuppressWarnings("serial")
public class FormularioRegistro extends JFrame {
	// mejor lo cambie a GridBagLayout para que no se estire todo como en el BoxLayout (el boton y demas)
	//Crear Componentes necesarios de la ventana
    JPanel panelComponentes = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    JTextField txtNombre = new JTextField(20);
    JTextField txtEmail = new JTextField(20);
    
    JTextField txtAnioNacimiento = new JTextField(20);
    JPasswordField txtContra = new JPasswordField(20);
    JLabel lblTituloFecha;
    JComboBox<String> comboMeses;
    JComboBox<String> comboDias;
    JPanel panelRadio; 
    JRadioButton rbMujer;
    JRadioButton rbHombre;
    JRadioButton rbExtra; 
    ButtonGroup bgSexo;
    JComboBox<String> comboRegiones; 
    
    JCheckBox chkAceptoCondiciones;
    JButton boton;
    JScrollPane scroll;
    private JLabel lblErrorNombre;
	private JLabel lblErrorEmail;
	private JLabel lblErrorGenero;
	private JLabel lblErrorContrasenia;
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
	    
		lblErrorNombre = createErrorLabel(" ");
		lblErrorEmail = createErrorLabel(" ");
		lblErrorGenero = createErrorLabel(" ");
		lblErrorTerminos = createErrorLabel(" ");
		lblErrorAnio = createErrorLabel(" ");
		lblErrorMes = createErrorLabel(" ");
		lblErrorDia = createErrorLabel(" ");
		lblErrorRegion = createErrorLabel(" ");
		lblErrorContrasenia = createErrorLabel(" ");
		
	    // Configuracion de margenes 
	    c.insets = new java.awt.Insets(2, 2, 2, 2); 
	    c.fill = GridBagConstraints.HORIZONTAL;
	
	    GestorCursor.aplicarATodo(this);

	    // usuario
	    agregarComponente(0, 0, new JLabel("Nombre de usuario: "), lblErrorNombre, txtNombre);
	    
	    // nombre
	    agregarComponente(0, 2, new JLabel("Email: "), lblErrorEmail, txtEmail);

	    // Título de la sección Fecha de nacimiento (Fila propia)
	    c.gridwidth = 2; //acuerdate que son las columnas
	    lblTituloFecha = new JLabel("Fecha de nacimiento:");
	    //lblTituloFecha.setFont(lblTituloFecha.getFont().deriveFont(java.awt.Font.BOLD));
	    agregarComponente(0, 4, lblTituloFecha);

	    
	    // año 
	    c.gridwidth = 1; 
	    agregarComponente(0, 5, new JLabel("Año: "), lblErrorAnio, txtAnioNacimiento);


	    // mes
	    String meses[] = {"Seleccione", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	    comboMeses = new JComboBox<>(meses);
	    agregarComponente(0, 7, new JLabel("Mes:"), lblErrorMes, comboMeses);

	    // dia
	    String dias[] = {"Seleccione", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	    comboDias = new JComboBox<>(dias);
	    agregarComponente(0, 9, new JLabel("Dia:"), lblErrorDia, comboDias);

	    
	    
	    // radiobuttons
	    panelRadio = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
	    rbMujer = new JRadioButton("Mujer");
	    rbHombre = new JRadioButton("Hombre");
	    rbExtra = new JRadioButton("Otro");
	    bgSexo = new ButtonGroup();
	    bgSexo.add(rbHombre); bgSexo.add(rbMujer); bgSexo.add(rbExtra);
	    panelRadio.add(rbMujer); panelRadio.add(rbHombre); panelRadio.add(rbExtra);
	    agregarComponente(0, 11, new JLabel("Genero:"), lblErrorGenero, panelRadio);


	    // region
	    //c.gridx = 1;
	    String regiones[] = {"Seleccione", "MEXICO", "PERU", "MIAMI", "LOS ANGELES", "OCEANIA", "JAPON", "CHINA", "INDIA", "ALASKA", "POLO SUR", "LONDRES", "NIGERIA"};
	    comboRegiones = new JComboBox<>(regiones);
	    comboRegiones.setSelectedIndex(0);
	    agregarComponente(0, 13, new JLabel("Region:"), lblErrorRegion, comboRegiones);
	    
	    // contraseña
	    agregarComponente(0, 15, new JLabel("Contraseña:"), lblErrorContrasenia, txtContra);
	    

	    // terminos
	    c.gridwidth = 2; 
	    chkAceptoCondiciones = new JCheckBox("He leído y aceptado los terminos y condiciones de la plataforma", false);
	    agregarComponente(0, 17, chkAceptoCondiciones, lblErrorTerminos);
	    
	    // boton
	    c.ipady = 15; 
	    JButton boton = new JButton("Crear cuenta");
	    boton.setBackground(assets.Colores.colorear(1));
	    boton.addActionListener(e -> validateForm());
	    agregarComponente(0, 19, boton);
	    assignListeners();
	    
	    // todo pal scroll
	    scroll = new JScrollPane(panelComponentes);
	    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    add(scroll);
	    //con esto hago el mouse jalar en todo//
	    assets.GestorCursor.aplicarATodo(this);
	    
	}

	
	private void agregarComponente(int posColumna, int posFilas, Component nombreComponenteAgregado, Component labelErrorCampo, Component campoAgregado) {
		c.gridx = posColumna;
		c.gridy = posFilas;
		panelComponentes.add(nombreComponenteAgregado, c);
		c.gridx = posColumna + 1;
		panelComponentes.add(campoAgregado, c);
		c.gridy += 1;
		panelComponentes.add(labelErrorCampo, c);
		
	}private void agregarComponente(int posColumna, int posFilas, Component componenteAgregado) {
	
		c.gridx = posColumna;
		c.gridy = posFilas;
		panelComponentes.add(componenteAgregado, c);
		
	}
	private void agregarComponente(int posColumna, int posFilas, Component componenteAgregado, Component labelErrorCampo) {
		c.gridx = posColumna;
		c.gridy = posFilas;
		panelComponentes.add(componenteAgregado, c);
		c.gridx += 1;
		c.gridy += 1;
		panelComponentes.add(labelErrorCampo, c);
		
	}
	private JLabel createErrorLabel(String texto) {
		JLabel label = new JLabel(texto);
		label.setFont(AppFonts.small());
		label.setForeground(Color.RED);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));

		return label;
	}
	private void pasarAMenu() {
		JOptionPane.showMessageDialog(
			this,
			"Se inició la sesión",
			"Sesion iniciada",
			JOptionPane.INFORMATION_MESSAGE
		);
		new MainWindow();
		dispose();
	}
	private void validateForm() {
		boolean valid = true;

		if (!validateName()) {
			valid = false;
		}
		if (!validateEmail()) {
			valid = false;
		}
		if(!validateComboRegion()) {
			valid = false;
		}
		if(!validateAnio()) 
        	valid = false;
		
		if(!validateComboMes()) 
        	valid = false;
		
		if(!validateComboDia()) 
        	valid = false;
		
        if(!validateGender()) 
        	valid = false;
        
        if(!validateTerms()) 
        	valid = false;
        
        if(!validateContrasenia()) 
        	valid = false;

		if (valid) {
			//JOptionPane.showMessageDialog(this, "Registro exitoso");
			pasarAMenu();
		}
		

	}
	private boolean validarJTextField(JTextField campoChecar, JLabel labelError) {
		if (campoChecar.getText().trim().isEmpty()) {
			labelError.setText("Es obligatorio llenar este campo");
			return false;
		}
		//Checa si el Jtextfied agregado es especifico para el JTextfield de Email, si es así debera ingresar un @ c
		//System.out.println(campoChecar.getDocument().equals(txtEmail.getDocument()));
		if(campoChecar.getDocument().equals(txtEmail.getDocument())) {
			if (!campoChecar.getText().contains("@")) {
				labelError.setText("Email inválido");
				return false;
			}
		}
		labelError.setText(" ");
		return true;
	}
	private boolean validateName() {
		if (txtNombre.getText().trim().isEmpty()) {
			lblErrorNombre.setText("El nombre es obligatorio");
			return false;
		}

		return true;
	}
	
	private boolean validateAnio() {
		if (txtAnioNacimiento.getText().trim().isEmpty()) {
			lblErrorAnio.setText("El año es obligatorio");
			return false;
		}
		lblErrorAnio.setText(" ");
		return true;
	}
	
	private boolean validateContrasenia() {

		if (String.valueOf(txtContra.getPassword()).trim().isEmpty()) {
			lblErrorContrasenia.setText("La contraseña es obligatoria");
			return false;
		}

		return true;
	}
	private boolean validateEmail() {

		if (txtEmail.getText().trim().isEmpty()) {
			lblErrorEmail.setText("El email es obligatorio");
			return false;
		}

		if (!txtEmail.getText().contains("@")) {
			lblErrorEmail.setText("Email inválido");
			return false;
		}

		return true;
	}
	private boolean validateComboMes() {
		if (comboMeses.getSelectedIndex() == 0) {
			lblErrorMes.setText("Seleccione un mes");
			return false;
		}
		lblErrorMes.setText(" ");
		return true;
	}
	private boolean validateComboDia() {

		if (comboDias.getSelectedIndex() == 0) {
			lblErrorDia.setText("Seleccione un Día");
			return false;
		}
		lblErrorDia.setText(" ");
		return true;
	}
	private boolean validateComboRegion() {

		if (comboRegiones.getSelectedIndex() == 0) {
			lblErrorRegion.setText("Seleccione un país");
			return false;
		}
		lblErrorRegion.setText(" ");
		return true;
	}

	private boolean validateGender() {

		if (!rbHombre.isSelected() && !rbMujer.isSelected()) {
			lblErrorGenero.setText("Seleccione un género");
			return false;
		}
		lblErrorGenero.setText(" ");
		return true;
	}

	private boolean validateTerms() {

		if (! chkAceptoCondiciones.isSelected()) {
			lblErrorTerminos.setText("Debe aceptar los términos");
			return false;
		}
		lblErrorTerminos.setText(" ");
		return true;
	}
	
	private void assignListeners() {
		comboRegiones.addActionListener(e -> {
			validateComboRegion();
		});
		comboMeses.addActionListener(e -> {
			validateComboMes();
		});
		comboDias.addActionListener(e -> {
			validateComboDia();
		});
		
		chkAceptoCondiciones.addActionListener(e -> validateTerms());
		
		checarSiCompletoCampo(txtNombre, lblErrorNombre);
		checarSiCompletoCampo(txtEmail, lblErrorEmail);
		checarSiCompletoCampo(txtAnioNacimiento, lblErrorAnio);
		checarSiCompletoCampo(txtContra, lblErrorContrasenia);
	}
	private void checarSiCompletoCampo(JTextField campoARellenar, JLabel labelError) {
		campoARellenar.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				validarJTextField(campoARellenar, labelError);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				validarJTextField(campoARellenar, labelError);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				validarJTextField(campoARellenar, labelError);
			}
		});
	}
}
