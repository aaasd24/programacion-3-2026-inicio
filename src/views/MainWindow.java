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
import java.util.List;

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
	    botonVerJuego = new JButton("Videojuegos"); 
	   
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
        try {
            // Creamos una instancia rápida del repositorio de tu compañero
            repositorio.RepositorioVideojuegos repoInicial = new repositorio.RepositorioVideojuegos();
            
            // Le pasamos la lista real de la base de datos entre los paréntesis
            panelHome.add(crearPanelCatalogoJuegos(repoInicial.obtenerListaVideojuegos()), BorderLayout.CENTER);
        } catch(Exception e) {
            // Si la base de datos llega a estar apagada, le pasamos una lista vacía 
            // para que tu Front-End cargue limpio y no se congele la aplicación
            panelHome.add(crearPanelCatalogoJuegos(new java.util.ArrayList<>()), BorderLayout.CENTER);
        }
        
        //Panel de CRUD  usuarios
		panelUsuario = new UsuarioView();
		panelUsuario.setOpaque(false);
		
		//Panel de CRUD videojuegos
		panelVideojuego = new VideojuegoView(this);
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

	public JScrollPane crearPanelCatalogoJuegos(List<Videojuego> listaJuegos) {
        JPanel panelContenedor = new JPanel();
        panelContenedor.setLayout(new javax.swing.BoxLayout(panelContenedor, javax.swing.BoxLayout.Y_AXIS));
        panelContenedor.setOpaque(false);
        panelContenedor.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 30, 20, 30)); 
        
        // Categoria 1: Continuar jugando (Simulamos los primeros 2 juegos de la lista)
        List<Videojuego> recientes = new java.util.ArrayList<>();
        if (listaJuegos != null && !listaJuegos.isEmpty()) {
            recientes.add(listaJuegos.get(0));
            if (listaJuegos.size() > 1) recientes.add(listaJuegos.get(1));
        }
        panelContenedor.add(crearCategoria("continuar jugando ->", recientes));
        panelContenedor.add(javax.swing.Box.createVerticalStrut(25)); 
        
        // Categoria 2: Toda tu biblioteca de la Parrilla
        panelContenedor.add(crearCategoria("biblioteca ->", listaJuegos));
        
        JScrollPane scroll = new JScrollPane(panelContenedor);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16); 
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        return scroll;
    }

	private JPanel crearCategoria(String titulo, List<Videojuego> juegos) { 
        JPanel panelCat = new JPanel(new BorderLayout());
        panelCat.setOpaque(false);
        
        // Título de la categoría
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(assets.AppFonts.negrita()); 
        lblTitulo.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0)); 
        
        // Fila que contiene las tarjetas de los juegos
        JPanel panelJuegos = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelJuegos.setOpaque(false);
        
        if (juegos != null && !juegos.isEmpty()) {
            for (Videojuego juego : juegos) {
                panelJuegos.add(crearTarjetaJuego(juego.getPortadaPath(), "Jugar", juego.getTitulo())); 
            }
        } else {
            JLabel vacío = new JLabel("La parrilla está vacía. Añade un juego en Videojuegos.");
            vacío.setForeground(Color.GRAY);
            vacío.setFont(assets.AppFonts.small());
            panelJuegos.add(vacío);
        }
        
      
        // JScrollPane que solo se mueva de izquierda a derecha en panelJuegos
        JScrollPane scrollFila = new JScrollPane(panelJuegos);
        scrollFila.setOpaque(false);
        scrollFila.getViewport().setOpaque(false);
        scrollFila.setBorder(null); // Sin bordes feos de Windows clásico
        
        // Configuración de barras: Activamos la horizontal sólo si es necesaria, apagamos la vertical
        scrollFila.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollFila.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        
        // Scroll supersuave para la fila horizontal
        scrollFila.getHorizontalScrollBar().setUnitIncrement(16);
        
        scrollFila.setPreferredSize(new Dimension(400, 250));
        scrollFila.setMinimumSize(new Dimension(100, 250));
        scrollFila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        
        scrollFila.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10)); 
        
        panelCat.add(lblTitulo, BorderLayout.NORTH);
        panelCat.add(scrollFila, BorderLayout.CENTER); 
        
        return panelCat;
    }

	private JPanel crearTarjetaJuego(String rutaImagen, String textoInferior, String tituloJuego) {
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setPreferredSize(new Dimension(140, 215)); // Incrementamos un poquito el alto para el título
        tarjeta.setOpaque(false);
        
        // Panel interno para agrupar portada + franja de jugar
        JPanel contenedorArte = new JPanel(new BorderLayout());
        contenedorArte.setPreferredSize(new Dimension(140, 190));
        contenedorArte.setOpaque(false);

        JLabel lblPortada = new JLabel();
        lblPortada.setOpaque(true);
        lblPortada.setBackground(new Color(45, 45, 45)); 
        lblPortada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        
        if (rutaImagen != null && !rutaImagen.trim().isEmpty()) {
        	try {
		         java.net.URL urlImg = getClass().getResource(rutaImagen);
		         if (urlImg != null) {
		             ImageIcon icon = new ImageIcon(urlImg);
		             Image img = icon.getImage().getScaledInstance(140, 165, Image.SCALE_SMOOTH);
		             lblPortada.setIcon(new ImageIcon(img));
		         } else {
		             lblPortada.setText("Insertar Portada"); 
		             lblPortada.setForeground(Color.LIGHT_GRAY);
		         }
		     } catch (Exception e) {
		         lblPortada.setText("Error");
		         lblPortada.setForeground(Color.RED);
		     }
		 } else {
			 lblPortada.setText("Sin Portada"); 
		     lblPortada.setForeground(Color.GRAY);
		 }
        
        // Franja inferior interactiva ("Jugar")
        JLabel lblJugar = new JLabel(textoInferior, javax.swing.SwingConstants.CENTER);
        lblJugar.setForeground(Color.WHITE);
        lblJugar.setFont(assets.AppFonts.small());
        lblJugar.setOpaque(true);
        lblJugar.setBackground(new Color(211, 84, 0, 230)); 
        lblJugar.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 4, 0));
        
        contenedorArte.add(lblPortada, BorderLayout.CENTER);
        contenedorArte.add(lblJugar, BorderLayout.SOUTH);
        
        // Texto con el nombre real del videojuego abajo de la tarjeta
        JLabel lblNombreJuego = new JLabel(tituloJuego, javax.swing.SwingConstants.CENTER);
        lblNombreJuego.setForeground(Color.WHITE);
        lblNombreJuego.setFont(assets.AppFonts.small());
        lblNombreJuego.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
        
        tarjeta.add(contenedorArte, BorderLayout.CENTER);
        tarjeta.add(lblNombreJuego, BorderLayout.SOUTH);
        
        return tarjeta;
    }
	
	public void refrescarCatalogoDesdeBD(java.util.List<Videojuego> listaActualizada) {
        // 1. Buscamos el JScrollPane que está actualmente en el centro del Home
        BorderLayout layout = (BorderLayout) ((JPanel) contenedor.getComponent(0)).getLayout();
        Component viejoScroll = layout.getLayoutComponent(BorderLayout.CENTER);
        
        if (viejoScroll != null) {
            ((JPanel) contenedor.getComponent(0)).remove(viejoScroll);
        }
        
        // 2. Generamos el nuevo catálogo con la lista fresca que nos manden
        JScrollPane nuevoScroll = crearPanelCatalogoJuegos(listaActualizada);
        ((JPanel) contenedor.getComponent(0)).add(nuevoScroll, BorderLayout.CENTER);
        
        // 3. Forzamos a Java Swing a re-renderizar los pixeles y aplicar cursores
        ((JPanel) contenedor.getComponent(0)).revalidate();
        ((JPanel) contenedor.getComponent(0)).repaint();
        assets.GestorCursor.aplicarATodo(this);
    }
}

