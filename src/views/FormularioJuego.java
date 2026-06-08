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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import assets.AppFonts;
import assets.Colores;
import assets.GestorCursor;
import config.Config;
 
import models.Videojuego;

@SuppressWarnings("serial")
public class FormularioJuego extends JDialog{

    // --- CAMPOS DEL JUEGO ---
    private JTextField txtTitulo = new JTextField(20);
    private JTextField txtPrecio = new JTextField(20);
    private JTextField txtLinkDescarga = new JTextField(20);
    
    private JTextArea txtDescripcion; 
    
    private JList<String> listaGeneros;
    private JList<String> listaPlataformas;
    
    // Crossplay 
    private ButtonGroup crossGrupo;
    private JPanel panelCrossplay; 
    private JRadioButton rbCrossplaySi;
    private JRadioButton rbCrossplayNo;
    
    // Multijugador 
    private ButtonGroup multiGrupo;
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
    private JLabel lblErrorPlataforma;
    private JLabel lblErrorGeneros;
    private JLabel lblErrorMultijugador;

    private Videojuego videojuego;
    private boolean guardado = false;
    public FormularioJuego(JFrame parent) {
    	super(parent, true);
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
		listaGeneros = new JList<String>(new String[] {"Acción", "Aventura", "RPG", "Shooter", "Deportes", "Estrategia", "Terror", "Indie"});
		listaPlataformas = new JList<String>(new String[] {"NINTENDO", "PS4", "XBOX", "PC"});
		listaGeneros.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listaPlataformas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		
		// --- INICIALIZAR ERRORES ---
		lblErrorTitulo = createErrorLabel(" ");
		lblErrorPrecio = createErrorLabel(" ");
		lblErrorDesc = createErrorLabel(" ");
		lblErrorLink = createErrorLabel(" ");
		lblErrorPortada = createErrorLabel(" ");
		lblErrorGeneros = createErrorLabel(" ");
		lblErrorCrossplay = createErrorLabel(" ");
		lblErrorPlataforma = createErrorLabel(" ");
		lblErrorMultijugador = createErrorLabel(" ");
		
		// --- AÑADIR CAMPOS BÁSICOS ---
		panel.add(crearCampo("Título del Juego", txtTitulo, lblErrorTitulo));
		panel.add(crearCampo("Precio ($)", txtPrecio, lblErrorPrecio));
		panel.add(crearCampo("Géneros", listaGeneros, lblErrorGeneros));
		panel.add(crearCampo("Enlace de Descarga", txtLinkDescarga, lblErrorLink));
		
		// --- DESCRIPCIÓN (Area de texto más grande) ---
		txtDescripcion = new JTextArea(4, 20); // 4 filas de alto
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		JScrollPane scrollDesc = new JScrollPane(txtDescripcion); // Para que tenga scroll si escriben mucho
		panel.add(crearCampo("Descripción", scrollDesc, lblErrorDesc));
		
		// --- CROSSPLAY (Radio Buttons) ---
		crossGrupo = new ButtonGroup();
		panelCrossplay = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelCrossplay.setOpaque(false);
		rbCrossplaySi = new JRadioButton("Sí");
		rbCrossplayNo = new JRadioButton("No");
		configurarRadio(rbCrossplaySi);
		configurarRadio(rbCrossplayNo);
		// Seleccionamos "No" por defecto
		rbCrossplayNo.setSelected(true);
		crossGrupo.add(rbCrossplayNo);
		crossGrupo.add(rbCrossplaySi);
		panelCrossplay.add(rbCrossplaySi); 
		panelCrossplay.add(rbCrossplayNo);
		panel.add(crearCampo("¿Tiene Crossplay?", panelCrossplay, lblErrorCrossplay));
		
		// --- MULTIJUGADOR Y PLATAFORMA (Checkboxes) ---
		multiGrupo = new ButtonGroup();
		panelMultijugador = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		panelMultijugador.setOpaque(false);
		chkOnline = new JCheckBox("Online");
		chkLocal = new JCheckBox("Local");
		chkCoopLocal = new JCheckBox("Coop Local");
		chkCoopOnline = new JCheckBox("Coop Online");
		//Configurar los Check buttons
		configurarCheck(chkOnline);
		configurarCheck(chkLocal);
		configurarCheck(chkCoopLocal);
		configurarCheck(chkCoopOnline);
		//Agregarlo al grupo y que solo se pueda seleccionar uno
		multiGrupo.add(chkCoopLocal);
		multiGrupo.add(chkCoopOnline);
		multiGrupo.add(chkLocal);
		multiGrupo.add(chkOnline);
		//Agregarlo al panel principal
		panelMultijugador.add(chkOnline);
		panelMultijugador.add(chkLocal);
		panelMultijugador.add(chkCoopLocal);
		panelMultijugador.add(chkCoopOnline);
		JPanel panelPlataforma = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelPlataforma.setOpaque(false);
		panelPlataforma.add(listaPlataformas);
		
		panel.add(crearCampo("Plataformas " , panelPlataforma, lblErrorPlataforma));
		panel.add(crearCampo("Opciones Multijugador", panelMultijugador, lblErrorMultijugador));
		
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
    //Lo hiciste bien 
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
			txtPrecio.setText(videojuegoSeleccionado.getPrecio() + "");
			txtLinkDescarga.setText(videojuegoSeleccionado.getDireccionArchivo());
			txtDescripcion.setText(videojuegoSeleccionado.getDescripcion());
			if(videojuegoSeleccionado.getCrossplay()) rbCrossplaySi.setSelected(true); else rbCrossplayNo.setSelected(true);
			
			
			//Indicar los generos seleccionados
			List<String> generos = videojuegoSeleccionado.getGeneros();
			int[] indices = new int[generos.size()];
			int i = 0;
			for(String genero: generos) {
				if(genero.equals("ACCION")) indices[i++] = 0;
				else if(genero.equals("AVENTURA")) indices[i++] = 1;
				else if(genero.equals("RPG")) indices[i++] = 2;
				else if(genero.equals("SHOOTER")) indices[i++] = 3;
				else if(genero.equals("DEPORTES")) indices[i++] = 4;
				else if(genero.equals("ESTRATEGIA")) indices[i++] = 5;
				else if(genero.equals("TERROR")) indices[i++] = 6;
				else if(genero.equals("INDIE")) indices[i++] = 7;
			}
			listaGeneros.setSelectedIndices(indices);
			
			mostrarMultiSleccionado(videojuegoSeleccionado).setSelected(true);
			
			List<String> plataformas = videojuegoSeleccionado.getPlataformasDisponibles();
			int[] indicesP = new int[plataformas.size()];
			int j = 0;
			for(String plataforma: plataformas) {
				if(plataforma.equals("NINTENDO")) indicesP[j++] = 0;
				else if(plataforma.equals("PS4")) indicesP[j++] = 1;
				else if(plataforma.equals("XBOX")) indicesP[j++] = 2;
				else if(plataforma.equals("PC")) indicesP[j++] = 3;
			}
			listaPlataformas.setSelectedIndices(indicesP);
            
    	}
    }
    
    
    public void confirmarGuardado() {
    	System.out.println("Guardado confirmado");
    	guardado = true;
        this.dispose();  
    }
    public boolean estaGuardado() {
    	return guardado;
    }

	public Videojuego getVideojuego() {
		return videojuego;
	}
	public JCheckBox mostrarMultiSleccionado(Videojuego videojuego) {
		String multiS = videojuego.getMultijugador();
		if(multiS.equals("Online")) {
			return chkOnline;
		}else if(multiS.equals("Local")) {
			return chkLocal;
		}else if(multiS.equals("Coop Online")) {
			return chkCoopOnline;
		}else if(multiS.equals("Coop Local")) {
			return chkCoopLocal;
		}
		System.out.println("ninguno");
		return null;
		
		
	}
	
    // --- GETTERS ---
    public String getTituloJuego() { return txtTitulo.getText(); }
    public String getPrecio() { return txtPrecio.getText(); }
    public String getLinkDescarga() { return txtLinkDescarga.getText(); }
    public String getDescripcion() { return txtDescripcion.getText(); }
    public ButtonModel btnCrossActivo() { return crossGrupo.getSelection();}
    public boolean tieneCrossplay() { return rbCrossplaySi.isSelected(); }
    
    // getters para saber que casillas marcan
    public ButtonModel chkSeleccionado() { return multiGrupo.getSelection();}

    public boolean isOnline() { return chkOnline.isSelected(); }
    public boolean isLocal() { return chkLocal.isSelected(); }
    public boolean isCoopLocal() { return chkCoopLocal.isSelected(); }
    public boolean isCoopOnline() { return chkCoopOnline.isSelected(); }
    
    
    public String getSelectedImagePath() { return selectedImagePath; }
    
    public JButton getBotonCrear() { return botonCrear; }
    public JButton getBotonCancelar() { return botonCancelar; }
    public JButton getBotonSeleccionarPortada() { return botonSeleccionarPortada; }

	// GETTERS Y SETTER DE LOS LabelError
	public JLabel getLblErrorTitulo() {	return lblErrorTitulo;}
	public JLabel getLblErrorPrecio() {	return lblErrorPrecio;	}
	public JLabel getLblErrorDesc() {return lblErrorDesc;}
	public JLabel getLblErrorLink() {	return lblErrorLink;}
	public JLabel getLblErrorPortada() {return lblErrorPortada;}
	public JLabel getLblErrorMulijugador() {return lblErrorMultijugador;}
	public JLabel getLblErrorPlataforma() {return lblErrorPlataforma;}
	public JLabel getLblErrorGeneros() {return lblErrorGeneros;}
	public JLabel getLblErrorCrossplay() {return lblErrorCrossplay;}
	
	public void setLblErrorTitulo(JLabel lblErrorTitulo) {this.lblErrorTitulo = lblErrorTitulo;	}
	public void setLblErrorPrecio(JLabel lblErrorPrecio) {this.lblErrorPrecio = lblErrorPrecio;	}
	public void setLblErrorDesc(JLabel lblErrorDesc) {	this.lblErrorDesc = lblErrorDesc;}
	public void setLblErrorLink(JLabel lblErrorLink) {this.lblErrorLink = lblErrorLink;}
	public void setLblErrorPortada(JLabel lblErrorPortada) {this.lblErrorPortada = lblErrorPortada;}

	//Getters de los componentes
    public JTextField getTxtTitulo() { return txtTitulo;}
	public JTextField getTxtPrecio() {return txtPrecio;}
	public JTextField getTxtLinkDescarga() {return txtLinkDescarga;}
	public JTextArea getTxtDescripcion() {return txtDescripcion;}	
	public JList<String> getJListaGeneros() {return listaGeneros;}
	public JList<String> getJListaPlataformas() { return listaPlataformas;}
	public JPanel getPanelCrossplay() {	return panelCrossplay;}
	public JRadioButton getRbCrossplaySi() {return rbCrossplaySi;}
	public JRadioButton getRbCrossplayNo() {return rbCrossplayNo;}
	public JPanel getPanelMultijugador() {return panelMultijugador;}
	public JCheckBox getChkOnline() {return chkOnline;}
	public JLabel getLblPortadaNombre() {return lblPortadaNombre;}
	public JCheckBox getChkLocal() {return chkLocal;}
	public JCheckBox getChkCoopLocal() {return chkCoopLocal;}
	public JCheckBox getChkCoopOnline() {return chkCoopOnline;}
	public JLabel getLblPortadaPrevia() {return lblPortadaPrevia;}
	
	//Setters de los componentes
	public void setTxtPrecio(JTextField txtPrecio) {this.txtPrecio = txtPrecio;}
	public void setTxtLinkDescarga(JTextField txtLinkDescarga) {this.txtLinkDescarga = txtLinkDescarga;}
	public void setTxtDescripcion(JTextArea txtDescripcion) {this.txtDescripcion = txtDescripcion;}
	public void setPanelCrossplay(JPanel panelCrossplay) {this.panelCrossplay = panelCrossplay;}
	public void setRbCrossplaySi(JRadioButton rbCrossplaySi) {this.rbCrossplaySi = rbCrossplaySi;}
	public void setRbCrossplayNo(JRadioButton rbCrossplayNo) {this.rbCrossplayNo = rbCrossplayNo;}
	public void setPanelMultijugador(JPanel panelMultijugador) {this.panelMultijugador = panelMultijugador;}
	public void setChkOnline(JCheckBox chkOnline) {this.chkOnline = chkOnline;}
	public void setChkLocal(JCheckBox chkLocal) {this.chkLocal = chkLocal;}
	public void setChkCoopLocal(JCheckBox chkCoopLocal) {this.chkCoopLocal = chkCoopLocal;}
	public void setChkCoopOnline(JCheckBox chkCoopOnline) {	this.chkCoopOnline = chkCoopOnline;}
	public void setLblPortadaPrevia(JLabel lblPortadaPrevia) {this.lblPortadaPrevia = lblPortadaPrevia;	}
	public void setLblPortadaNombre(JLabel lblPortadaNombre) {this.lblPortadaNombre = lblPortadaNombre;}
	public void setTxtTitulo(JTextField txtTitulo) {this.txtTitulo = txtTitulo;}
	public void setBotonSeleccionarPortada(JButton botonSeleccionarPortada) {this.botonSeleccionarPortada = botonSeleccionarPortada;}
	public void setSelectedImagePath(String selectedImagePath) {this.selectedImagePath = selectedImagePath;}
	public void setBotonCrear(JButton botonCrear) {this.botonCrear = botonCrear;}
	public void setBotonCancelar(JButton botonCancelar) {this.botonCancelar = botonCancelar;}

}