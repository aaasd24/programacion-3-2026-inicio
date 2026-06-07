package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import utils.ThemeManager;

import controllers.MainController;
import models.Videojuego;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JMenuItem salir;
	private JMenuBar mb;
	private JMenu archivo;
	private JMenuItem abrir;
	private JMenuItem guardar;

	private JMenu otraOpcion;
	
	private JMenu opcion1;
	private JMenuItem opcion2;
	private JMenuItem opcion3;
	
	public JButton botonVerUsuario;
	public JButton botonHome;
	public JButton botonVerJuego;
	
	private JPanel panelBusqueda;
	private JTextField txtBuscar;
	
	public final static String HOME = "HOME";
	public final static String USERS = "USERS";
	public final static String GAMES = "GAMES";
	
	public UsuarioView panelUsuario;
	public VideojuegoView panelVideojuego;
	
	private CardLayout cardLayout;
	private JPanel contenedor;
	
	//Atributos base para tarjetas
	private String imagenPath;
	private String textoInferior;
	
	public MainWindow() {
		
		setSize(500,500);
		setTitle("Biblioteca");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMenu();
		JPanel panel = new JPanel();
            
		panel.setLayout(new BorderLayout());
		add(panel);
		
		crearBarraNav();
		crearVistas();
		
		MainController mainControl = new MainController(this);
		mainControl.registerListeners();
		setVisible(true);
		
	}
	
	public JMenuItem getSalir() {return salir;}

	public JMenuBar getMb() {return mb;	}


	public JMenu getArchivo() {	return archivo;}


	
	public JMenuItem getAbrir() {return abrir;}

	public JMenuItem getGuardar() {	return guardar;}


	public JMenu getOtraOpcion() {
		return otraOpcion;
	}

	public JMenu getOpcion1() {
		return opcion1;
	}


	public JMenuItem getOpcion2() {
		return opcion2;
	}


	
	public JMenuItem getOpcion3() {
		return opcion3;
	}

	/**
	 * 
	 */
	public JButton getBotonVerUsuario() {
		return botonVerUsuario;
	}
	
	
	public JButton getBotonHome() {
		return botonHome;
	}
	public JButton getBotonVideojuego() {
		return botonVerJuego;
	}
	
	public JPanel getPanelBusqueda() {
		return panelBusqueda;
	}

	public void setPanelBusqueda(JPanel panelBusqueda) {
		this.panelBusqueda = panelBusqueda;
	}

	public JTextField getTxtBuscar() {
		return txtBuscar;
	}

	public void setTxtBuscar(JTextField txtBuscar) {
		this.txtBuscar = txtBuscar;
	}

	public void setMenu() {
		
		mb = new JMenuBar();
		setJMenuBar(mb);
		
		archivo= new JMenu("Archivo");
		archivo.setMnemonic(KeyEvent.VK_A);
		mb.add(archivo);
		
		abrir = new JMenuItem("Abrir");
		abrir.setMnemonic(KeyEvent.VK_B);
		archivo.add(abrir);
		
		guardar = new JMenuItem("Guardar");
		guardar.setMnemonic(KeyEvent.VK_G);
		archivo.add(guardar);
		
		archivo.addSeparator();
		salir = new JMenuItem("Salir");
		salir.setMnemonic(KeyEvent.VK_S);
		archivo.add(salir);
		
		JMenuItem theme = new JMenuItem("Cambiar modo");
	    theme.addActionListener(e -> {
	    	ThemeManager.toggle();
	    });
	    
	    
	    mb.add(theme);
		/*otraOpcion= new JMenu("Otra opción");
		otraOpcion.setMnemonic(KeyEvent.VK_O);
		mb.add(otraOpcion);
		
		opcion1 = new JMenu("Opción 1");
		otraOpcion.add(opcion1);
		
		opcion3 = new JMenuItem("Opción 3");
		opcion1.add(opcion3);
		
		opcion2 = new JMenuItem("Opción 2");
		otraOpcion.add(opcion2);
		*/
		
	}

	public int confirmExit() {
	    return JOptionPane.showConfirmDialog(
	        this,
	        "¿Seguro que deseas regresar? Se perderán todos los datos",
	        "¿Seguro?",
	        JOptionPane.YES_NO_OPTION
	    );
	}
	
	public void crearBarraNav() {
	    JPanel barraNav = new JPanel(new BorderLayout());
	    barraNav.setOpaque(false); 
	    barraNav.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); 

	    JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
	    panelBotones.setOpaque(false);
	    //botones
	    botonHome = new JButton("Inicio");
	    botonVerUsuario = new JButton("Usuarios");
	    botonVerJuego = new JButton("adminPrueba"); 
	   
	    //botones estilo
	    botonHome.setBackground(new Color(60, 60, 60, 200));
	    botonHome.setForeground(Color.WHITE);
	    botonHome.putClientProperty("JButton.buttonType", "roundRect");
	    botonHome.setBorderPainted(false);
	    botonHome.setFocusPainted(false);
	    
	    botonVerUsuario.setBackground(new Color(60, 60, 60, 200));
	    botonVerUsuario.setForeground(Color.WHITE);
	    botonVerUsuario.putClientProperty("JButton.buttonType", "roundRect");
	    botonVerUsuario.setBorderPainted(false);
	    botonVerUsuario.setFocusPainted(false);
	    
	    botonVerJuego.setBackground(new Color(60, 60, 60, 200));
	    botonVerJuego.setForeground(Color.WHITE);
	    botonVerJuego.putClientProperty("JButton.buttonType", "roundRect");
	    botonVerJuego.setBorderPainted(false);
	    botonVerJuego.setFocusPainted(false);
	    
	    //botones en el panel izquierdo
	    panelBotones.add(botonHome);
	    panelBotones.add(botonVerUsuario);
	    panelBotones.add(botonVerJuego);

	    // para la barra de búsqueda maqueta
	    panelBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
	    panelBusqueda.setOpaque(false);

	    txtBuscar = new JTextField();
	    txtBuscar.setPreferredSize(new Dimension(220, 32));
	    txtBuscar.setBackground(new Color(45, 45, 45));     
	    txtBuscar.setForeground(Color.WHITE);              

	    txtBuscar.putClientProperty("JComponent.roundRect", true); // Bordes circulares tipo cápsula
	    txtBuscar.putClientProperty("JTextField.placeholderText", "Buscar juego..."); // Texto de fondo
	    txtBuscar.putClientProperty("JTextField.showClearButton", true); // boton para limpiar

	    panelBusqueda.add(txtBuscar);

	    barraNav.add(panelBotones, BorderLayout.WEST);
	    barraNav.add(panelBusqueda, BorderLayout.EAST);
	    
	    add(barraNav, BorderLayout.NORTH);		
	}
	
	public void crearVistas() {
		cardLayout = new CardLayout();
		contenedor = new JPanel(cardLayout);
		contenedor.setOpaque(false);
		JPanel panelHome = new JPanel() {
			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    // Dibujamos el logo de fondo
                    Image fondo = ImageIO.read(getClass().getResource("../assets/main-1080p.jpg"));
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                    
                    // Capa oscura transparente para el contraste
                    g.setColor(new Color(0, 0, 0, 150)); 
                    g.fillRect(0, 0, getWidth(), getHeight());
                } catch (Exception e) {
                    System.out.println("Error al cargar el fondo del registro: " + e.getMessage());
                }
            }
        };
        
        assets.GestorCursor.aplicarATodo(this);
        
        panelHome.setLayout(new BorderLayout());
        JPanel panelCabecera = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelCabecera.setOpaque(false); // Transparente
        
        JLabel labelBienvenida = new JLabel("Bienvenido a");
        labelBienvenida.setForeground(Color.WHITE);
        labelBienvenida.setFont(assets.AppFonts.title());
        
        JLabel labelLogo = new JLabel();
        try {
            // Cargamos el logo de la parrilla en pequeño
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("../assets/SteakGames.png"));
            Image img = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            labelLogo.setIcon(new javax.swing.ImageIcon(img));
        } catch (Exception e) {
            labelLogo.setText("[LOGO]");
            labelLogo.setForeground(Color.WHITE);
        }
        
        
        panelCabecera.add(labelBienvenida);
        panelCabecera.add(labelLogo);
        
        panelHome.add(panelCabecera, BorderLayout.NORTH);
        
        // --- CATÁLOGO DE JUEGOS ---
        panelHome.add(crearPanelCatalogoJuegos(), BorderLayout.CENTER);
        
        //Panel de CRUD  usuarios
		panelUsuario = new UsuarioView();
		panelUsuario.setOpaque(false);
		
		//Panel de CRUD videojuegos
		panelVideojuego = new VideojuegoView();
		panelVideojuego.setOpaque(false);
		
		contenedor.add(panelHome, HOME);
		contenedor.add(panelUsuario, USERS);
		contenedor.add(panelVideojuego, GAMES);
		
		add(contenedor, BorderLayout.CENTER);
	}
	
	
	public void mostrarVista(String vista) {
		cardLayout.show(contenedor, vista);
		assets.GestorCursor.aplicarATodo(this);
	}
	public void setWindowSize(int width, int height) {
		setSize(width, height);
	}
	
	public void setWindowLocation(int x, int y) {
		setLocation(x, y);
	}
	

	// --- MÉTODOS PARA MAQUETAR LA BIBLIOTECA ---

	private JScrollPane crearPanelCatalogoJuegos() {
        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new javax.swing.BoxLayout(panelContenedor, javax.swing.BoxLayout.Y_AXIS));
        panelContenedor.setOpaque(false);
        panelContenedor.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 30, 20, 30)); 
        
        // CORRECCIÓN AQUÍ: Solo pasamos el título, sin el segundo parámetro viejo ("horas" o "")
        panelContenedor.add(crearCategoria("continuar jugando ->"));
        panelContenedor.add(javax.swing.Box.createVerticalStrut(25)); 
        panelContenedor.add(crearCategoria("biblioteca ->"));
        
        JScrollPane scroll = new JScrollPane(panelContenedor);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16); 
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        return scroll;
    }

    private JPanel crearCategoria(String titulo) { // CORRECCIÓN: Solo recibe (String titulo)
        JPanel panelCat = new JPanel(new BorderLayout());
        panelCat.setOpaque(false);
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(assets.AppFonts.negrita()); 
        lblTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0)); 
        
        JPanel panelJuegos = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelJuegos.setOpaque(false);
        
        for(int i = 0; i < 5; i++) {


            // Nota: Cambiar el 'null' por la ruta de la imagen cuando queramos agregarlas

            // Todas las tarjetas dirán "Jugar"
            panelJuegos.add(crearTarjetaJuego(null, "Jugar")); 

        }
        
        panelCat.add(lblTitulo, BorderLayout.NORTH);
        panelCat.add(panelJuegos, BorderLayout.CENTER);
        
        return panelCat;
    }

    private JPanel crearTarjetaJuego(String rutaImagen, String textoInferior) {
    	//
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setPreferredSize(new Dimension(140, 200)); //tamaño de la imagen aprox
        tarjeta.setOpaque(false);
        
        // 1. La Portada
        JLabel lblPortada = new JLabel();
        lblPortada.setOpaque(true);
        lblPortada.setBackground(new Color(40, 40, 40, 200)); // Fondo gris de prueba por ahora
        lblPortada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        if (rutaImagen != null) {
        	try {
		         javax.swing.ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource(imagenPath));
		         Image img = icon.getImage().getScaledInstance(140, 175, Image.SCALE_SMOOTH);
		         lblPortada.setIcon(new javax.swing.ImageIcon(img));
		     } catch (Exception e) {
		         lblPortada.setText("Imagen no encontrada");
		         lblPortada.setForeground(Color.WHITE);
		     }
		 } else {
		     // Placeholder de texto si mandamos null
			 lblPortada.setText("Portada"); 
		     lblPortada.setForeground(Color.LIGHT_GRAY);
		 }
        
        
       
        
        //borde para jugar
        JLabel lblHoras = new JLabel(textoInferior, javax.swing.SwingConstants.CENTER);
        lblHoras.setForeground(Color.WHITE);
        lblHoras.setFont(assets.AppFonts.small());
        lblHoras.setOpaque(true);
        lblHoras.setBackground(new Color(20, 20, 20, 220)); // Franja casi negra transparente
        lblHoras.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));
        
        // Para que las tarjetas tengan un borde redondeado o limpio usando FlatLaf
        tarjeta.putClientProperty("FlatLaf.style", "arc: 10");
        
        tarjeta.add(lblPortada, BorderLayout.CENTER);
        tarjeta.add(lblHoras, BorderLayout.SOUTH);
        
        return tarjeta;
		
    }
	
	public void mostrarJuego(Videojuego videojuego) {
		if(videojuego != null) {
			imagenPath = videojuego.getPortadaPath();
		}
		
	}
}

