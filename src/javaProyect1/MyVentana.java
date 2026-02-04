package javaProyect1;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MyVentana extends JFrame {
	
	public MyVentana() {
		//setSize(100, 100);
		//setLocation(100, 100);
		setBounds(100, 100, 500, 500);
		setResizable(false);
		setTitle("Hola");
		setLocationRelativeTo(null);
		//Siempre el set visible al final del constructor

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/img/icono.png");
		setIconImage(icono);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		MyPanel panelito = new MyPanel();
		add(panelito);
		
		setVisible(true);
		
	}
	
}
