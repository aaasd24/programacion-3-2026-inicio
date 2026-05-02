package views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.Usuario;

@SuppressWarnings("serial")
public class FormularioUsuarioDialog extends JDialog{

	private JTextField txtNombre;
    private JTextField txtEmail;
    private JTextField txtAnioNacimiento = new JTextField(20);
    private JComboBox<String> comboMeses;
    private JComboBox<String> comboDias;
    private JComboBox<String> comboRegiones;
    

    private JRadioButton rbtnMale;
    private JRadioButton rbtnFemale;
    private ButtonGroup genderGroup;

    private JButton btnSave;
    private JButton btnCancel;

    private Usuario user;
    private boolean saved = false;
    		
    public FormularioUsuarioDialog(JFrame parent, Usuario user) {
    	super(parent, true);
    	
    	this.user = user;
    	
    	setSize(400, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        add(createTitlePanel(), BorderLayout.NORTH);
        add(createFormPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        loadData();
        btnSave.addActionListener(e -> save());
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
        
        btnSave.addActionListener(e -> save());
        btnCancel.addActionListener(e -> dispose());
        
        return panel;
    }

    private JScrollPane createFormPanel() {

    	JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

		JScrollPane scroll = new JScrollPane(panel);
		scroll.setBorder(null);
		scroll.setHorizontalScrollBar(null);
		scroll.getVerticalScrollBar().setUnitIncrement(14);

		txtNombre = new JTextField();

		txtEmail = new JTextField();
		
		txtAnioNacimiento = new JTextField();
		
		String regiones[] = {"Seleccione", "MEXICO", "PERU", "MIAMI", "LOS ANGELES", "OCEANIA", "JAPON", "CHINA", "INDIA", "ALASKA", "POLO SUR", "LONDRES", "NIGERIA"};
        comboRegiones = new JComboBox<>(regiones);
        comboRegiones.setSelectedIndex(0);

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


		panel.add(createField("Nombre:", txtNombre));
		panel.add(createField("Email:", txtEmail));
		panel.add(createField("Anio", txtAnioNacimiento));
		panel.add(createField("Mes:", comboMeses));
		panel.add(createField("Dia:", comboDias));
		panel.add(createField("Region:", comboRegiones));

		JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderPanel.add(rbtnMale);
		genderPanel.add(rbtnFemale);

		panel.add(createField("Género:", genderPanel));


		return scroll;
    }
    		
    private JPanel createField(String labelText, Component field) {

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAlignmentX(Component.CENTER_ALIGNMENT);

		JLabel label = new JLabel(labelText);
		label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(label);
		panel.add(field);

		return panel;
	}
    private void loadData() {
    	if(user != null) {
    		txtNombre.setText(user.getNombre());
            txtEmail.setText(user.getCorreo());
            comboRegiones.setSelectedItem(user.getRegion());

            if (user.getGenero() == 'M') {
                rbtnMale.setSelected(true);
            } else {
                rbtnFemale.setSelected(true);
            }

            txtAnioNacimiento.setText(user.getAnio());
            comboMeses.setSelectedItem(user.getMes());
            comboDias.setSelectedItem(user.getDia());
            
    	}
    }
    
    private void save() {
    	String nombre = txtNombre.getText();
    	String correo = txtEmail.getText();
        String region = (String) comboRegiones.getSelectedItem();
        String anio = txtAnioNacimiento.getText();
        String mes = (String) comboMeses.getSelectedItem();
        String dia = (String) comboDias.getSelectedItem();
        
        char genero = rbtnMale.isSelected() ? 'M' : 'F';

        
        if(user == null) {
        	this.user = new Usuario(nombre, correo, region, genero, anio, mes, dia);
        }else {
        	this.user.setNombre(nombre);
        	this.user.setCorreo(correo);
        	this.user.setRegion(region);
            this.user.setGenero(genero);
        }
        
        saved = true;
        dispose();
        
    }

    public boolean isSaved() {
    	return saved;
    }
    
    public Usuario getUsuario() {
    	return user;
    }

}