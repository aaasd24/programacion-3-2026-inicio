package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import assets.AppFonts;
import controllers.FormularioUsuarioDialogController;
import models.Usuario;

@SuppressWarnings("serial")
public class FormularioUsuarioDialog extends JDialog{

	private JTextField txtNombre;
    private JTextField txtEmail;
    private static String ADMIN = "ADMIN";
    private JComboBox<String> comboAnios;
    private JComboBox<String> comboMeses;
    private JComboBox<String> comboDias;
    private JComboBox<String> comboRegiones;
    
    private JRadioButton rbtnMale;
    private JRadioButton rbtnFemale;
    private ButtonGroup genderGroup;

    private JLabel lblErrorNombre;
    private JLabel lblErrorEmail;
    private JLabel lblErrorGenero;
    private JLabel lblErrorDia;
    private JLabel lblErrorMes;
    private JLabel lblErrorAnio;
    private JLabel lblErrorRegion;
    
    private JButton btnSave;
    private JButton btnCancel;

    //private FormularioUsuarioDialogController controlador;
    private Usuario user;
    private boolean saved = false;
    		
    public FormularioUsuarioDialog(JFrame parent) {
    	super(parent, true);
    	
    	setSize(400, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        add(createTitlePanel(), BorderLayout.NORTH);
        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        //loadData();
        //controlador = new FormularioUsuarioDialogController(this );
        //controlador.inicializarListeners();
        //btnSave.addActionListener(e -> save());
    }
    
    private JPanel createTitlePanel() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Formulario de Usuario"));
        return panel;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();

        btnSave = new JButton("Guardar");
        btnCancel = new JButton("Cancelar");

        panel.add(btnSave);
        panel.add(btnCancel);
        
        return panel;
    }

    private JScrollPane createFormPanel() {

    	lblErrorNombre = createErrorLabel(" ");
        lblErrorEmail = createErrorLabel(" ");
        lblErrorGenero = createErrorLabel(" ");
        //lblErrorTerminos = createErrorLabel(" ");
        lblErrorAnio = createErrorLabel(" ");
        lblErrorMes = createErrorLabel(" ");
        lblErrorDia = createErrorLabel(" ");
        lblErrorRegion = createErrorLabel(" ");

        
    	JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

		JScrollPane scroll = new JScrollPane(panel);
		scroll.setBorder(null);
		scroll.setHorizontalScrollBar(null);
		scroll.getVerticalScrollBar().setUnitIncrement(14);

		txtNombre = new JTextField();

		txtEmail = new JTextField();
		
		
		
		String regiones[] = {"Seleccione", "MEXICO", "PERU", "MIAMI", "LOS ANGELES", "OCEANIA", "JAPON", "CHINA", "INDIA", "ALASKA", "POLO SUR", "LONDRES", "NIGERIA"};
        comboRegiones = new JComboBox<>(regiones);
        comboRegiones.setSelectedIndex(0);
        
        String anios[] = {"Seleccione", "2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026",};
        comboAnios = new JComboBox<>(anios);

        String meses[] = {"Seleccione", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        comboMeses = new JComboBox<>(meses);

        String dias[] = {"Seleccione", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        comboDias = new JComboBox<>(dias);

		rbtnMale = new JRadioButton("Masculino");
		rbtnMale.setActionCommand("M");

		rbtnFemale = new JRadioButton("Femenino");
		rbtnFemale.setActionCommand("F");

		genderGroup = new ButtonGroup();
		genderGroup.add(rbtnMale);
		genderGroup.add(rbtnFemale);
		
		panel.add(createField("Nombre:", txtNombre, lblErrorNombre));
		panel.add(createField("Email:", txtEmail, lblErrorEmail));
		panel.add(createField("Anio", comboAnios, lblErrorAnio));
		panel.add(createField("Mes:", comboMeses, lblErrorMes));
		panel.add(createField("Dia:", comboDias, lblErrorDia));
		panel.add(createField("Region:", comboRegiones, lblErrorRegion));

		JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderPanel.add(rbtnMale);
		genderPanel.add(rbtnFemale);

		panel.add(createField("Género:", genderPanel, lblErrorGenero));


		return scroll;
    }
    		
    private JPanel createField(String labelText, Component field, Component errorLabel) {

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(labelText);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		errorLabel.setForeground(Color.RED);
		errorLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, errorLabel.getPreferredSize().height));

		panel.add(label);
		panel.add(field);
		panel.add(errorLabel);

		return panel;
	}
    
    private JLabel createErrorLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(AppFonts.small());
        label.setForeground(Color.RED);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
        return label;
    }
    
    public void mostrarDatos(Usuario usuarioSeleccionado) {
    	if(usuarioSeleccionado != null) {
    		txtNombre.setText(usuarioSeleccionado.getNombre());
            txtEmail.setText(usuarioSeleccionado.getCorreo());
            comboRegiones.setSelectedItem(usuarioSeleccionado.getRegionString());

            if (usuarioSeleccionado.getGenero() == 'M') {
                rbtnMale.setSelected(true);
            } else {
                rbtnFemale.setSelected(true);
            }

            comboAnios.setSelectedItem(usuarioSeleccionado.getAnio());
            comboMeses.setSelectedItem(usuarioSeleccionado.getMes());
            comboDias.setSelectedItem(usuarioSeleccionado.getDia());
            
    	}
    }
    
    public void confirmarGuardado() {
        saved = true;
        dispose();
        
    }

    public boolean isSaved() {
    	return saved;
    }
    
    public Usuario getUsuario() {
    	return user;
    }
    
    
    
 // --- GETTERS PARA EL CONTROLADOR ---
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JComboBox<String> getComboAnios()	{return comboAnios; }
    public JComboBox<String> getComboMeses() { return comboMeses; }
    public JComboBox<String> getComboDias() { return comboDias; }
    public JComboBox<String> getComboRegiones() { return comboRegiones; }
    public JRadioButton getRbMujer() { return rbtnFemale; }
    public JRadioButton getRbHombre() { return rbtnMale; }
    public JButton getBotonGuardar() { return btnSave; }
    public JButton getBotonCancelar() { return btnCancel; }
    
    public JLabel getLblErrorNombre() { return lblErrorNombre; }
    public JLabel getLblErrorEmail() { return lblErrorEmail; }
    public JLabel getLblErrorGenero() { return lblErrorGenero; }
    public JLabel getLblErrorDia() { return lblErrorDia; }
    public JLabel getLblErrorMes() { return lblErrorMes; }
    public JLabel getLblErrorAnio() { return lblErrorAnio; }
    public JLabel getLblErrorRegion() { return lblErrorRegion; }
    
    public String getEmailUsuario() { return txtEmail.getText();}
    public String getRegion() { return String.valueOf(comboRegiones.getSelectedItem());}
    public String getAnio() { return String.valueOf(comboAnios.getSelectedItem());}
    public int getRegionID() { return comboRegiones.getSelectedIndex(); }
    public String getMes() { return String.valueOf(comboMeses.getSelectedItem());}
    public String getDia() { return String.valueOf(comboDias.getSelectedItem()); }
    public char getGenero() { 
    	if(rbtnMale.isSelected()) {
    		return 'H';
    	}
    	if(rbtnFemale.isSelected()) {
    		return 'M';
    	}
    	return 'n'; 
    }
	public String getRol() {return ADMIN;}


}