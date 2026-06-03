package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import assets.GestorCursor;
import config.Config;
import controllers.RegistrationController;
import utils.Genero;
import assets.AppFonts;
import assets.Colores;



@SuppressWarnings("serial")
public class FormularioRegistro extends JFrame {

    JPanel panelComponentes = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    private static String USER = "USUARIO"; 
    private JTextField txtNombre = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JPasswordField txtContra = new JPasswordField(20);
    //private String rol;
    
    private JComboBox<String> comboAnios;
    private JComboBox<String> comboMeses;
    private JComboBox<String> comboDias;
    
    private JPanel panelRadio; 
    private JRadioButton rbMujer;
    private JRadioButton rbHombre;
    
    private JComboBox<String> comboRegiones; 
    
    private JCheckBox chkAceptoCondiciones;
    
    private JButton botonSeleccionarImagen;
	private JLabel lblImagenPrevia;
	private JLabel lblImagenNombre;
	private String selectedImagePath;
	
    private JButton botonCrear;
    private JButton botonCancelar;
    
    //private JScrollPane scroll;
    
    private JLabel lblErrorNombre;
    private JLabel lblErrorEmail;
    private JLabel lblErrorGenero;
    private JLabel lblErrorContrasenia;
    private JLabel lblErrorTerminos;
    private JLabel lblErrorDia;
    private JLabel lblErrorMes;
    private JLabel lblErrorAnio;
    private JLabel lblErrorRegion;
    private JLabel lblErrorImage;

    public FormularioRegistro() {
        setSize(450, 680);
        setResizable(false);
        setTitle("Registrarse");
        setLocationRelativeTo(null);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image icono = tk.getImage("src/assets/SteakGames.png");
        setIconImage(icono);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GestorCursor.aplicarATodo(this);
        //inicializarComponentes();
        
        add(crearTituloPanel(), BorderLayout.NORTH);
        add(crearPanelFormulario());
        add(crearBotones(), BorderLayout.SOUTH);
        // conectamos la vista con el controlador
        RegistrationController controller = new RegistrationController(this);
        controller.initListeners(); 
        
        setVisible(true);		
    }
	
    public JScrollPane crearPanelFormulario() {
    	JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Dibujamos la parrillada de fondo
                    Image fondo = ImageIO.read(getClass().getResource("../assets/fondo.jpg"));
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                    
                    // Capa oscura transparente para el contraste
                    g.setColor(new Color(0, 0, 0, 150)); 
                    g.fillRect(0, 0, getWidth(), getHeight());
                } catch (Exception e) {
                    System.out.println("Error al cargar el fondo del registro: " + e.getMessage());
                }
            }
        };
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
		JScrollPane scroll = new JScrollPane(panel);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setHorizontalScrollBar(null);
		scroll.setBorder(null);
		scroll.getVerticalScrollBar().setUnitIncrement(14);
		
		
		String anios[] = {"Seleccione", "2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2023","2024","2025","2026",};
        comboAnios = new JComboBox<>(anios);
        String meses[] = {"Seleccione", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        comboMeses = new JComboBox<>(meses);
        String dias[] = {"Seleccione", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
        comboDias = new JComboBox<>(dias);
        String regiones[] = {"Seleccione", "MEXICO", "PERU", "MIAMI", "LOS ANGELES", "OCEANIA", "JAPON", "CHINA", "INDIA", "ALASKA", "POLO SUR", "LONDRES", "NIGERIA"};
        comboRegiones = new JComboBox<>(regiones);
        
		lblErrorNombre = createErrorLabel(" ");
        lblErrorEmail = createErrorLabel(" ");
        lblErrorGenero = createErrorLabel(" ");
        lblErrorTerminos = createErrorLabel(" ");
        lblErrorAnio = createErrorLabel(" ");
        lblErrorMes = createErrorLabel(" ");
        lblErrorDia = createErrorLabel(" ");
        lblErrorRegion = createErrorLabel(" ");
        lblErrorContrasenia = createErrorLabel(" ");
        lblErrorImage = createErrorLabel(" ");
        
        panel.add(crearCampo("Nombre de usuario", txtNombre, lblErrorNombre));
        panel.add(crearCampo("Email", txtEmail, lblErrorEmail));
        panel.add(crearCampo("Contraseña", txtContra, lblErrorContrasenia));
        panel.add(crearCampo("Año", comboAnios, lblErrorAnio));
        panel.add(crearCampo("Mes", comboMeses, lblErrorMes));
        panel.add(crearCampo("Dia", comboDias, lblErrorDia));

        panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelRadio.setOpaque(false);
        rbMujer = new JRadioButton("Mujer");
        rbMujer.setFont(AppFonts.small());
        rbMujer.setForeground(Colores.colorear(5));
        rbHombre = new JRadioButton("Hombre");
        rbHombre.setFont(AppFonts.small());
        rbHombre.setForeground(Colores.colorear(5));
        rbMujer.setOpaque(false);
        rbHombre.setOpaque(false);
        panelRadio.add(rbMujer); panelRadio.add(rbHombre);
        panel.add(crearCampo("Genero", panelRadio, lblErrorGenero));
        panel.add(crearCampo("Region", comboRegiones, lblErrorRegion));
        
        //Panel de  seleccionar imagen
        botonSeleccionarImagen = new JButton("Seleccionar imagen");

		lblImagenNombre = new JLabel("Ninguna imagen seleccionada");
		lblImagenNombre.setForeground(Colores.colorear(5));

		lblImagenPrevia = new JLabel();
		lblImagenPrevia.setPreferredSize(new Dimension(120,120));
		lblImagenPrevia.setBorder(null);
		lblImagenPrevia.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		lblImagenPrevia.setVisible(false);
		
		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
		botonSeleccionarImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblImagenPrevia.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblImagenNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

		imagePanel.add(lblImagenPrevia);
		imagePanel.add(botonSeleccionarImagen);
		imagePanel.add(lblImagenNombre);

		panel.add(crearCampo("Foto:", imagePanel, lblErrorImage));
		
		//TODO Panel de los botones, Cehcar los tamaños de los botones
		
		JPanel termsPanel = new JPanel();
		termsPanel.setOpaque(false);
		chkAceptoCondiciones = new JCheckBox("He leído los terminos y condiciones");
		chkAceptoCondiciones.setForeground(Colores.colorear(5));
		chkAceptoCondiciones.setOpaque(false);
		termsPanel.add(chkAceptoCondiciones);
        panel.add(crearCampo("", termsPanel, lblErrorTerminos));
        
        
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
       
        GestorCursor.aplicarATodo(this);
        return scroll;
    }
    private JPanel crearTituloPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 45, 45));

		JLabel titulo = new JLabel("Nueva cuenta");
		titulo.setFont(AppFonts.title());
		titulo.setForeground(Color.WHITE);

		panel.add(titulo);

		return panel;
	}
    private JPanel crearCampo(String nombre, Component campo, JLabel error) {
    	JPanel panelCampo = new JPanel();
    	panelCampo.setOpaque(false);
    	panelCampo.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
		panelCampo.setLayout(new BoxLayout(panelCampo, BoxLayout.Y_AXIS));
		panelCampo.setAlignmentX(Component.CENTER_ALIGNMENT);
    	
    	
    	JLabel labelNombre = new JLabel(nombre);
    	labelNombre.setForeground(Color.WHITE);
    	labelNombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, labelNombre.getPreferredSize().height));
		labelNombre.setHorizontalAlignment(SwingConstants.LEFT);
		labelNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		error.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panelCampo.add(labelNombre);
		panelCampo.add(campo);
		panelCampo.add(error);
		
		return panelCampo;
    	
    }

    private JLabel createErrorLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(AppFonts.small());
        label.setForeground(Color.RED);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setMaximumSize(new Dimension(Integer.MAX_VALUE, label.getPreferredSize().height));
        return label;
    }
    
    public JPanel crearBotones() {
    	JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	
    	botonCancelar = new JButton("Volver");
    	botonCrear = new JButton("Crear");
    	

    	panelBoton.add(botonCrear);
    	panelBoton.add(botonCancelar);
    
    	
    	
    	return panelBoton;
    }
    
    public int confirmarRegreso() {
	    return JOptionPane.showConfirmDialog(
	        this,
	        "¿Seguro que deseas regresar? Se perderán todos los datos",
	        "¿Seguro?",
	        JOptionPane.YES_NO_OPTION
	    );
	}
    
    public void  elegirImagen() {
		
		String lastDirectory = Config.get("registration.image.last.directory", System.getProperty("user.home"));
		
		JFileChooser chooser = new JFileChooser(lastDirectory);
		chooser.setDialogTitle("Seleccionar imagen");
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png");
		chooser.setFileFilter(filter);
		
		int option = chooser.showOpenDialog(this);
		
		if(option == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			
			selectedImagePath = file.getAbsolutePath();
			lastDirectory = file.getParent();
			
			Config.set("registration.image.last.directory", lastDirectory);
			
			lblImagenNombre.setText(file.getName());
			
			ImageIcon icon = new ImageIcon(selectedImagePath);
			Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
			
			lblImagenPrevia.setIcon(new ImageIcon(img));
	        lblImagenPrevia.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Volvemos a poner el borde estético
	        lblImagenPrevia.setVisible(true);
	        
	        lblImagenPrevia.revalidate();
	        lblImagenPrevia.repaint();
	        this.getContentPane().revalidate();
	        this.getContentPane().repaint();
			
			lblImagenPrevia.setIcon(new ImageIcon(img));
		}
		
	}

    // --- GETTERS PARA EL CONTROLADOR ---
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JPasswordField getTxtContra() { return txtContra; }
    public JComboBox<String> getComboAnios()	{return comboAnios; }
    public JComboBox<String> getComboMeses() { return comboMeses; }
    public JComboBox<String> getComboDias() { return comboDias; }
    public JComboBox<String> getComboRegiones() { return comboRegiones; }
    public JRadioButton getRbMujer() { return rbMujer; }
    public JRadioButton getRbHombre() { return rbHombre; }
    public JCheckBox getChkAceptoCondiciones() { return chkAceptoCondiciones; }
    public JButton getBotonCrear() { return botonCrear; }
    public JButton getBotonCancelar() { return botonCancelar; }
    public JButton getBotonSeleccionarImagen() { return botonSeleccionarImagen; }
    
    public JLabel getLblErrorNombre() { return lblErrorNombre; }
    public JLabel getLblErrorEmail() { return lblErrorEmail; }
    public JLabel getLblErrorGenero() { return lblErrorGenero; }
    public JLabel getLblErrorContrasenia() { return lblErrorContrasenia; }
    public JLabel getLblErrorTerminos() { return lblErrorTerminos; }
    public JLabel getLblErrorDia() { return lblErrorDia; }
    public JLabel getLblErrorMes() { return lblErrorMes; }
    public JLabel getLblErrorAnio() { return lblErrorAnio; }
    public JLabel getLblErrorRegion() { return lblErrorRegion; }
    public JLabel getLblErrorImagen() { return lblErrorImage; }

    public String getNombreUsuario() { return txtNombre.getText(); }
    
    public String getEmailUsuario() { return txtEmail.getText();}
    public String getPasswordusuario() { return  String.valueOf(txtContra.getPassword()); }
    public String getRegion() { return String.valueOf(comboRegiones.getSelectedItem());}
    public int getRegionID() { return  comboRegiones.getSelectedIndex(); }
    public String getAnio() { return String.valueOf(comboAnios.getSelectedItem());}
    public String getMes() { return String.valueOf(comboMeses.getSelectedItem());}
    public String getDia() { return String.valueOf(comboDias.getSelectedItem()); }
    public Genero getGenero() { 
    	if(rbHombre.isSelected()) {
    		return Genero.HOMBRE;
    	}
    	if(rbMujer.isSelected()) {
    		return Genero.MUJER;
    	}
    	return Genero.HOMBRE;
    }
    public String getSelectedImagePath() { return selectedImagePath;    }

	public String getRol() {return USER;}
	
    
}