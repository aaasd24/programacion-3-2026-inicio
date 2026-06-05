package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import assets.AppFonts;
import assets.Colores;
import assets.GestorCursor;
import config.Config;
// import controllers.GameRegistrationController; 
import models.Videojuego;
import utils.Genero;

@SuppressWarnings("serial")
public class FormularioJuego extends JFrame {

    // --- CAMPOS DEL JUEGO ---
    private JTextField txtTitulo = new JTextField(20);
    private JTextField txtPrecio = new JTextField(20);
    private JTextField txtLinkDescarga = new JTextField(20);
    
    private JTextArea txtDescripcion; 
    
    private JComboBox<String> comboGeneros;
    private JCheckBox chkMultiplataforma;
    
    // Crossplay 
    private JPanel panelCrossplay; 
    private JRadioButton rbCrossplaySi;
    private JRadioButton rbCrossplayNo;
    
    // Multijugador 
    private JPanel panelMultijugador;
    private JCheckBox chkOnline;
    private JCheckBox chkLocal;
    private JCheckBox chkCoopLocal;
    private JCheckBox chkCoopOnline;
    
    // --- IMAGEN DE PORTADA ---
    private JButton botonSeleccionarPortada;
    private JLabel lblPortadaPrevia;
    private JLabel lblPortadaNombre;
    private String selectedImagePath;
    
    // --- BOTONES INFERIORES ---
    private JButton botonCrear;
    private JButton botonCancelar;
    
    // --- LABELS DE ERROR ---
    private JLabel lblErrorTitulo;
    private JLabel lblErrorPrecio;
    private JLabel lblErrorDesc;
    private JLabel lblErrorLink;
    private JLabel lblErrorPortada;
    private JLabel lblErrorCrossplay;

    private Videojuego videojuego;
    private boolean guardado = false;
    public FormularioJuego() {
        setSize(500, 750); 
        setResizable(false);
        setTitle("Añadir Videojuego a la Parrilla");
        setLocationRelativeTo(null);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        try {
            Image icono = tk.getImage("src/assets/SteakGames.png");
            setIconImage(icono);
        } catch (Exception e) {}
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        GestorCursor.aplicarATodo(this);
        
        add(crearTituloPanel(), BorderLayout.NORTH);
        add(crearPanelFormulario());
        add(crearBotones(), BorderLayout.SOUTH);
        
        // Descomenta esto cuando crees el controlador del juego
        // GameRegistrationController controller = new GameRegistrationController(this);
        // controller.initListeners(); 
        
        setVisible(true);		
    }
	
    public JScrollPane crearPanelFormulario() {
    	JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                   //fondo
                    Image fondo = ImageIO.read(getClass().getResource("../assets/fondo.jpg"));
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                    
                    
                    g.setColor(new Color(0, 0, 0, 160)); 
                    g.fillRect(0, 0, getWidth(), getHeight());
                } catch (Exception e) {
                    System.out.println("Error al cargar el fondo: " + e.getMessage());
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
		scroll.getVerticalScrollBar().setUnitIncrement(14); // Scroll suave
		
		// --- DATOS COMBOBOX ---
		String generos[] = {"Seleccione", "Acción", "Aventura", "RPG", "Shooter", "Deportes", "Estrategia", "Terror", "Indie"};
        comboGeneros = new JComboBox<>(generos);
  
        
        // --- INICIALIZAR ERRORES ---
		lblErrorTitulo = createErrorLabel(" ");
        lblErrorPrecio = createErrorLabel(" ");
        lblErrorDesc = createErrorLabel(" ");
        lblErrorLink = createErrorLabel(" ");
        lblErrorPortada = createErrorLabel(" ");
        
        // --- AÑADIR CAMPOS BÁSICOS ---
        panel.add(crearCampo("Título del Juego", txtTitulo, lblErrorTitulo));
        panel.add(crearCampo("Precio ($)", txtPrecio, lblErrorPrecio));
        panel.add(crearCampo("Género Principal", comboGeneros, createErrorLabel(" ")));
        panel.add(crearCampo("Enlace de Descarga", txtLinkDescarga, lblErrorLink));

        // --- DESCRIPCIÓN (Area de texto más grande) ---
        txtDescripcion = new JTextArea(4, 20); // 4 filas de alto
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion); // Para que tenga scroll si escriben mucho
        panel.add(crearCampo("Descripción", scrollDesc, lblErrorDesc));

        // --- CROSSPLAY (Radio Buttons) ---
        panelCrossplay = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCrossplay.setOpaque(false);
        rbCrossplaySi = new JRadioButton("Sí");
        rbCrossplayNo = new JRadioButton("No");
        configurarRadio(rbCrossplaySi);
        configurarRadio(rbCrossplayNo);
        // Seleccionamos "No" por defecto
        rbCrossplayNo.setSelected(true); 
        panelCrossplay.add(rbCrossplaySi); 
        panelCrossplay.add(rbCrossplayNo);
        panel.add(crearCampo("¿Tiene Crossplay?", panelCrossplay, createErrorLabel(" ")));
        
        // --- MULTIJUGADOR Y PLATAFORMA (Checkboxes) ---
        panelMultijugador = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelMultijugador.setOpaque(false);
        chkOnline = new JCheckBox("Online");
        chkLocal = new JCheckBox("Local");
        chkCoopLocal = new JCheckBox("Coop Local");
        chkCoopOnline = new JCheckBox("Coop Online");
        configurarCheck(chkOnline);
        configurarCheck(chkLocal);
        configurarCheck(chkCoopLocal);
        configurarCheck(chkCoopOnline);
        panelMultijugador.add(chkOnline);
        panelMultijugador.add(chkLocal);
        panelMultijugador.add(chkCoopLocal);
        panelMultijugador.add(chkCoopOnline);
        JPanel panelPlataforma = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelPlataforma.setOpaque(false);
        chkMultiplataforma = new JCheckBox("Disponible en múltiples plataformas");
        configurarCheck(chkMultiplataforma); 

        panelPlataforma.add(chkMultiplataforma);
        panel.add(crearCampo("Opciones Multijugador", panelMultijugador, createErrorLabel(" ")));
        panel.add(crearCampo("Plataforma", panelPlataforma, createErrorLabel(" ")));
        
        // --- PORTADA DEL JUEGO (Imagen) ---
        botonSeleccionarPortada = new JButton("Seleccionar Portada");
		lblPortadaNombre = new JLabel("Ninguna imagen seleccionada");
		lblPortadaNombre.setForeground(Colores.colorear(5)); 

		lblPortadaPrevia = new JLabel();
		lblPortadaPrevia.setPreferredSize(new Dimension(150, 200)); 
		lblPortadaPrevia.setBorder(null);
		lblPortadaPrevia.setVisible(false); 
		
		JPanel imagePanel = new JPanel();
		imagePanel.setOpaque(false);
		imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
		botonSeleccionarPortada.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPortadaPrevia.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPortadaNombre.setAlignmentX(Component.CENTER_ALIGNMENT);

		imagePanel.add(lblPortadaPrevia);
		imagePanel.add(botonSeleccionarPortada);
		imagePanel.add(lblPortadaNombre);

		panel.add(crearCampo("Imagen de Portada:", imagePanel, lblErrorPortada));
        
        GestorCursor.aplicarATodo(this);
        return scroll;
    }
    
    // Método auxiliar para estilar RadioButtons
    private void configurarRadio(JRadioButton rb) {
        rb.setFont(AppFonts.small());
        rb.setForeground(Colores.colorear(5));
        rb.setOpaque(false);
    }
    
    // Método auxiliar para estilar CheckBoxes
    private void configurarCheck(JCheckBox chk) {
        chk.setFont(AppFonts.small());
        chk.setForeground(Colores.colorear(5));
        chk.setOpaque(false);
    }

    private JPanel crearTituloPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(45, 45, 45)); 

		JLabel titulo = new JLabel("Registrar Nuevo Videojuego");
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
    	labelNombre.setForeground(Color.WHITE); // Texto en blanco para el fondo oscuro
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
    	panelBoton.setBackground(new Color(45, 45, 45)); 
    	
    	botonCancelar = new JButton("Cancelar");
    	botonCrear = new JButton("Guardar Juego");
    	
    	panelBoton.add(botonCrear);
    	panelBoton.add(botonCancelar);
    	
    	return panelBoton;
    }
    
    // El método para cargar la imagen (con la lógica de visibilidad arreglada)
    //si no lo hice bien, pues me dices 
    public void elegirImagen() {
		String lastDirectory = Config.get("registration.image.last.directory", System.getProperty("user.home"));
		JFileChooser chooser = new JFileChooser(lastDirectory);
		chooser.setDialogTitle("Seleccionar Portada");
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png");
		chooser.setFileFilter(filter);
		
		int option = chooser.showOpenDialog(this);
		
		if(option == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			selectedImagePath = file.getAbsolutePath();
			lastDirectory = file.getParent();
			Config.set("registration.image.last.directory", lastDirectory);
			
			lblPortadaNombre.setText(file.getName());
			
			ImageIcon icon = new ImageIcon(selectedImagePath);
			Image img = icon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
			
			lblPortadaPrevia.setIcon(new ImageIcon(img));
	        lblPortadaPrevia.setBorder(BorderFactory.createLineBorder(Color.GRAY)); 
	        lblPortadaPrevia.setVisible(true);
	        
	        lblPortadaPrevia.revalidate();
	        lblPortadaPrevia.repaint();
	        this.getContentPane().revalidate();
	        this.getContentPane().repaint();
		}
	}

    public void mostrarDatos(Videojuego videojuegoSeleccionado) {
    	if(videojuegoSeleccionado != null) {
    		txtTitulo.setText(videojuegoSeleccionado.getTitulo());
            //txtPrecio.set(videojuegoSeleccionado.getPrecio());
            txtLinkDescarga.setText(videojuegoSeleccionado.getDireccionArchivo());
            //comboPlataformas.setSelectedItem(videojuegoSeleccionado.get);
            //TODO Prblemas con PRECIO, combo plataforma y generos, descripcion y multijugador4
            

            //chkCoopLocal.setSelectedIcon(videojuegoSeleccionado.get);
    	}
    }
    
    
    public void confirmarGuardado() {
    	guardado = true;
        dispose();  
    }
    public boolean estaGuardado() {
    	return guardado;
    }

	public Videojuego getVideojuego() {
		return videojuego;
	}
    
    // --- GETTERS ---
    public String getTituloJuego() { return txtTitulo.getText(); }
    public String getPrecio() { return txtPrecio.getText(); }
    public String getLinkDescarga() { return txtLinkDescarga.getText(); }
    public String getDescripcion() { return txtDescripcion.getText(); }
    public String getGenero() { return String.valueOf(comboGeneros.getSelectedItem()); }
    
    public boolean tieneCrossplay() { return rbCrossplaySi.isSelected(); }
    
    // getters para saber que casillas marcan
    public boolean isOnline() { return chkOnline.isSelected(); }
    public boolean isLocal() { return chkLocal.isSelected(); }
    public boolean isCoopLocal() { return chkCoopLocal.isSelected(); }
    public boolean isCoopOnline() { return chkCoopOnline.isSelected(); }
    public boolean isMultiplataforma() { return chkMultiplataforma.isSelected(); }
    
    public String getSelectedImagePath() { return selectedImagePath; }
    
    public JButton getBotonCrear() { return botonCrear; }
    public JButton getBotonCancelar() { return botonCancelar; }
    public JButton getBotonSeleccionarPortada() { return botonSeleccionarPortada; }

	
	public JLabel getLblErrorTitulo() {	return lblErrorTitulo;}
	public void setLblErrorTitulo(JLabel lblErrorTitulo) {this.lblErrorTitulo = lblErrorTitulo;	}
	public JLabel getLblErrorPrecio() {	return lblErrorPrecio;	}
	public void setLblErrorPrecio(JLabel lblErrorPrecio) {this.lblErrorPrecio = lblErrorPrecio;	}
	public JLabel getLblErrorDesc() {return lblErrorDesc;}
	public void setLblErrorDesc(JLabel lblErrorDesc) {	this.lblErrorDesc = lblErrorDesc;}
	public JLabel getLblErrorLink() {	return lblErrorLink;}
	public void setLblErrorLink(JLabel lblErrorLink) {this.lblErrorLink = lblErrorLink;}
	public JLabel getLblErrorPortada() {return lblErrorPortada;}
	public void setLblErrorPortada(JLabel lblErrorPortada) {this.lblErrorPortada = lblErrorPortada;}
	
	//Getters de los componentes
    public JTextField getTxtTitulo() { return txtTitulo;}
	public JTextField getTxtPrecio() {return txtPrecio;}

	public JTextField getTxtLinkDescarga() {return txtLinkDescarga;}
	
	public JTextArea getTxtDescripcion() {return txtDescripcion;}
	public void setTxtDescripcion(JTextArea txtDescripcion) {this.txtDescripcion = txtDescripcion;}
	public JComboBox<String> getComboGeneros() {return comboGeneros;}
	public void setComboGeneros(JComboBox<String> comboGeneros) {
		this.comboGeneros = comboGeneros;
	}
	public void setTxtPrecio(JTextField txtPrecio) {this.txtPrecio = txtPrecio;}
	public void setTxtLinkDescarga(JTextField txtLinkDescarga) {this.txtLinkDescarga = txtLinkDescarga;}

	public JPanel getPanelCrossplay() {
		return panelCrossplay;
	}
	public void setPanelCrossplay(JPanel panelCrossplay) {
		this.panelCrossplay = panelCrossplay;
	}
	public JRadioButton getRbCrossplaySi() {
		return rbCrossplaySi;
	}
	public void setRbCrossplaySi(JRadioButton rbCrossplaySi) {
		this.rbCrossplaySi = rbCrossplaySi;
	}
	public JRadioButton getRbCrossplayNo() {
		return rbCrossplayNo;
	}
	public void setRbCrossplayNo(JRadioButton rbCrossplayNo) {
		this.rbCrossplayNo = rbCrossplayNo;
	}
	public JPanel getPanelMultijugador() {
		return panelMultijugador;
	}
	public void setPanelMultijugador(JPanel panelMultijugador) {
		this.panelMultijugador = panelMultijugador;
	}
	public JCheckBox getChkOnline() {
		return chkOnline;
	}
	public void setChkOnline(JCheckBox chkOnline) {
		this.chkOnline = chkOnline;
	}
	public JCheckBox getChkLocal() {
		return chkLocal;
	}
	public void setChkLocal(JCheckBox chkLocal) {
		this.chkLocal = chkLocal;
	}
	public JCheckBox getChkCoopLocal() {
		return chkCoopLocal;
	}
	public void setChkCoopLocal(JCheckBox chkCoopLocal) {
		this.chkCoopLocal = chkCoopLocal;
	}
	public JCheckBox getChkCoopOnline() {
		return chkCoopOnline;
	}
	public void setChkCoopOnline(JCheckBox chkCoopOnline) {
		this.chkCoopOnline = chkCoopOnline;
	}
	public JLabel getLblPortadaPrevia() {
		return lblPortadaPrevia;
	}
	public void setLblPortadaPrevia(JLabel lblPortadaPrevia) {
		this.lblPortadaPrevia = lblPortadaPrevia;
	}
	public JLabel getLblPortadaNombre() {
		return lblPortadaNombre;
	}
	public void setLblPortadaNombre(JLabel lblPortadaNombre) {
		this.lblPortadaNombre = lblPortadaNombre;
	}
	public void setTxtTitulo(JTextField txtTitulo) {
		this.txtTitulo = txtTitulo;
	}
	public void setBotonSeleccionarPortada(JButton botonSeleccionarPortada) {
		this.botonSeleccionarPortada = botonSeleccionarPortada;
	}
	public void setSelectedImagePath(String selectedImagePath) {
		this.selectedImagePath = selectedImagePath;
	}
	public void setBotonCrear(JButton botonCrear) {this.botonCrear = botonCrear;}
	public void setBotonCancelar(JButton botonCancelar) {this.botonCancelar = botonCancelar;
	}

    
}