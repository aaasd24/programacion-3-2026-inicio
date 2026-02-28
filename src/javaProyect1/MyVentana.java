package javaProyect1;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

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
		setBounds(100, 100, 650, 680);
		setResizable(false);
		setTitle("SteakGames");
		setLocationRelativeTo(null);
		//Siempre el set visible al final del constructor
		FlatLightLaf.setup(); 
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/assets/SteakGames.png");
		setIconImage(icono);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		UIManager.put("Label.font", AppFonts.normal());
		UIManager.put("Button.font", AppFonts.title());
		UIManager.put("TextField.font", AppFonts.small());
		
		
		
		//MyPanel panelito = new MyPanel();
		//add(panelito);
		LoginView miLogin = new LoginView(); 
		 FormularioRegistro formulario = new FormularioRegistro();
		add(miLogin);
		GestorCursor.aplicarATodo(this);
		
	}
	
}
