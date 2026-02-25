package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import assets.GestorCursor;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BotonsView extends JPanel{

	public BotonsView() {
		
		//Declaro que la ventana se trabaje de manera que los paneles esten en los bordes. Este se considera como panel general 
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JButton boton = new JButton("Button 1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 100;
		c.gridy = 0;
		this.add(boton, c);
		
		//Declaro un panel para botones, lo pongo de coor de magetna y lo agrego al panel general 
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.MAGENTA);
		
		//Creo botones y los agrego al panel superior
		JButton botonInicio = new JButton("Iniciar sesión");
		JButton botonReinicio = new JButton("Cargar página");
		
		//panelSuperior.add(botonInicio);
		//panelSuperior.add(botonReinicio);
		
		//Agrego panel superior al panel general
		//add(panelSuperior, BorderLayout.NORTH);
		
	}
	
	public void PaintComponents(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Image fondo = null;
		
		try {
			fondo = ImageIO.read(new File("src/assets/icono.png"));
			g2.drawImage(fondo, 0, getWidth() ,null);			
		} catch (IOException ex) {
			System.out.println("la imagen no existe.");
		}
		
	}
	//jcheckBox para marcar casillas en filtros//
	//comboBox para marcar generos o verificacion de edad en juegos para mayores de 18//
	//JRadioButton para encasillar genero del usuario//
	//buttonGroup bgSexo = new sexo()... para encasillar unicamente un boton//
	//JSlider para utilizar barras ya sea para volumen o, en nuestro caso, un rango de precios para los juegos//
	//JtextArea para dejar reseñas, comentarios o errores de sistema//
	//ScrollPane(JTextArea para agreagar un scroll en las reseñas//
	//podemos marcar el AreaDescripcon en falso para simplemente dejarcomentarios de ejemplo o anuncios, e incluso para que no pueda usar el boton si no ha completado los campos//
	//JList que te muestra las opciones d euna, para usarlo en el buscador sencillo que incluiremos para buscar juegos con filtros//
	//JTabbedPane para agregar tablas y ver si podemos darle al usuario la opcoin de separar juegos entre todos y favoritos(o para barras de navegacion)//
	//Flatlight es clave para hacer mas minimalista la pagina y darle "limpieza"//
	
	
}
