package views;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import assets.GestorCursor;
import assets.AppFonts;
import javax.swing.JPanel;

public class MyPanel extends JPanel{

	public MyPanel() {
		setBackground(Color.DARK_GRAY);
		LoginView login = new LoginView();
		add(login);
		setLayout(null); // Indica para que no haya un punto de inicio de colocar el boton
		JButton boton = new JButton("mi botones"); // Se crea el objeto boton de la librerias swim
		boton.setBackground(Color.green);
		boton.setForeground(Color.BLACK);
		boton.setBounds(300, 500, 120, 50); //Ubica y establce tamaño de boton
		
		try {
			//Image icono = ImageIO.read(getClass().getResource("src/assets/icono.png"));
			Image icono = ImageIO.read(getClass().getResource("../assets/SteakGames.png"));
			icono = icono.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			boton.setIcon(new ImageIcon(icono));
		}catch(Exception ex) {
			System.out.println("NO se encuentra imagen");
		}
		
		add(boton); //Agrega el boton
		
		
		
	}
	
}
