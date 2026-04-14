package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import assets.GestorCursor;
import assets.AppFonts;
import assets.Colores; 
import assets.ErrorLabel; 

import controlers.RegistrationController;

@SuppressWarnings("serial")
public class FormularioRegistro extends JFrame {

    JPanel panelComponentes = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    private JTextField txtNombre = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtAnioNacimiento = new JTextField(20);
    private JPasswordField txtContra = new JPasswordField(20);
    private JLabel lblTituloFecha;
    private JComboBox<String> comboMeses;
    private JComboBox<String> comboDias;
    private JPanel panelRadio; 
    private JRadioButton rbMujer;
    private JRadioButton rbHombre;
    private JRadioButton rbExtra; 
    private ButtonGroup bgSexo;
    private JComboBox<String> comboRegiones; 
    private JCheckBox chkAceptoCondiciones;
    private JButton boton;
    private JScrollPane scroll;
    
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
        setSize(600, 680);
        setResizable(false);
        setTitle("Registrarse");
        setLocationRelativeTo(null);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image icono = tk.getImage("src/assets/SteakGames.png");
        setIconImage(icono);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        inicializarComponentes();
        
        // conectamos la vista con el controlador
        RegistrationController controller = new RegistrationController(this);
        controller.initListeners(); 
        
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
        
        c.insets = new java.awt.Insets(2, 2, 2, 2); 
        c.fill = GridBagConstraints.HORIZONTAL;
    
        GestorCursor.aplicarATodo(this);

        agregarComponente(0, 0, new JLabel("Nombre de usuario: "), lblErrorNombre, txtNombre);
        agregarComponente(0, 2, new JLabel("Email: "), lblErrorEmail, txtEmail);

        c.gridwidth = 2;
        lblTituloFecha = new JLabel("Fecha de nacimiento:");
        agregarComponente(0, 4, lblTituloFecha);

        c.gridwidth = 1; 
        agregarComponente(0, 5, new JLabel("Año: "), lblErrorAnio, txtAnioNacimiento);

        String meses[] = {"Seleccione", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        comboMeses = new JComboBox<>(meses);
        agregarComponente(0, 7, new JLabel("Mes:"), lblErrorMes, comboMeses);

        String dias[] = {"Seleccione", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        comboDias = new JComboBox<>(dias);
        agregarComponente(0, 9, new JLabel("Dia:"), lblErrorDia, comboDias);

        panelRadio = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        rbMujer = new JRadioButton("Mujer");
        rbHombre = new JRadioButton("Hombre");
        rbExtra = new JRadioButton("Otro");
        bgSexo = new ButtonGroup();
        bgSexo.add(rbHombre); bgSexo.add(rbMujer); bgSexo.add(rbExtra);
        panelRadio.add(rbMujer); panelRadio.add(rbHombre); panelRadio.add(rbExtra);
        agregarComponente(0, 11, new JLabel("Genero:"), lblErrorGenero, panelRadio);

        String regiones[] = {"Seleccione", "MEXICO", "PERU", "MIAMI", "LOS ANGELES", "OCEANIA", "JAPON", "CHINA", "INDIA", "ALASKA", "POLO SUR", "LONDRES", "NIGERIA"};
        comboRegiones = new JComboBox<>(regiones);
        comboRegiones.setSelectedIndex(0);
        agregarComponente(0, 13, new JLabel("Region:"), lblErrorRegion, comboRegiones);
        
        agregarComponente(0, 15, new JLabel("Contraseña:"), lblErrorContrasenia, txtContra);
        
        c.gridwidth = 2; 
        chkAceptoCondiciones = new JCheckBox("He leído y aceptado los terminos y condiciones de la plataforma", false);
        agregarComponente(0, 17, chkAceptoCondiciones, lblErrorTerminos);
        
        c.ipady = 15; 
        boton = new JButton("Crear cuenta");
        boton.setBackground(assets.Colores.colorear(1));
        agregarComponente(0, 19, boton);
        
        scroll = new JScrollPane(panelComponentes);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll);
        
        GestorCursor.aplicarATodo(this);
    }

    private void agregarComponente(int posColumna, int posFilas, Component nombreComponenteAgregado, Component labelErrorCampo, Component campoAgregado) {
        c.gridx = posColumna;
        c.gridy = posFilas;
        panelComponentes.add(nombreComponenteAgregado, c);
        c.gridx = posColumna + 1;
        panelComponentes.add(campoAgregado, c);
        c.gridy += 1;
        panelComponentes.add(labelErrorCampo, c);
    }

    private void agregarComponente(int posColumna, int posFilas, Component componenteAgregado) {
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

    // --- GETTERS PARA EL CONTROLADOR ---
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JTextField getTxtAnioNacimiento() { return txtAnioNacimiento; }
    public JPasswordField getTxtContra() { return txtContra; }
    public JComboBox<String> getComboMeses() { return comboMeses; }
    public JComboBox<String> getComboDias() { return comboDias; }
    public JComboBox<String> getComboRegiones() { return comboRegiones; }
    public JRadioButton getRbMujer() { return rbMujer; }
    public JRadioButton getRbHombre() { return rbHombre; }
    public JCheckBox getChkAceptoCondiciones() { return chkAceptoCondiciones; }
    public JButton getBoton() { return boton; }
    
    public JLabel getLblErrorNombre() { return lblErrorNombre; }
    public JLabel getLblErrorEmail() { return lblErrorEmail; }
    public JLabel getLblErrorGenero() { return lblErrorGenero; }
    public JLabel getLblErrorContrasenia() { return lblErrorContrasenia; }
    public JLabel getLblErrorTerminos() { return lblErrorTerminos; }
    public JLabel getLblErrorDia() { return lblErrorDia; }
    public JLabel getLblErrorMes() { return lblErrorMes; }
    public JLabel getLblErrorAnio() { return lblErrorAnio; }
    public JLabel getLblErrorRegion() { return lblErrorRegion; }

    public String getNombreUsuario() { return txtNombre.getText(); }
    
    public String getEmailUsuario() { return txtEmail.getText();}
    public String getPasswordusuario() { return  String.valueOf(txtEmail.getText()); }
    public String getRegion() { return String.valueOf(comboRegiones.getSelectedItem());}
    public String getAnio() { return txtAnioNacimiento.getText();}
    public String getMes() { return String.valueOf(comboMeses.getSelectedItem());}
    public String getDia() { return String.valueOf(comboDias.getSelectedItem()); }
    
}