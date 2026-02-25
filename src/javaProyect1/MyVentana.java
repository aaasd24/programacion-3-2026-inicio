package javaProyect1;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

import assets.GestorCursor;
import assets.AppFonts;
import views.BotonsView;
import views.FormularioRegistro;
import views.LoginView;
import views.MyPanel;

public class MyVentana extends JFrame {
	
	public MyVentana() {
		//setSize(100, 100);
		//setLocation(100, 100);
		setBounds(100, 100, 800, 800);
		setResizable(false);
		setTitle("SteakGames");
		setLocationRelativeTo(null);
		//Siempre el set visible al final del constructor

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("../assets/SteakGames.png");
		setIconImage(icono);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		//MyPanel panelito = new MyPanel();
		//add(panelito);
		LoginView miLogin = new LoginView(); 
		FormularioRegistro formulario = new FormularioRegistro();
		BotonsView prueba = new BotonsView();
		GestorCursor.aplicarATodo(this);
		add(miLogin);
		
		setVisible(true);
		
	}
	
}
