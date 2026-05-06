package views;



import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

import assets.GestorCursor;
import assets.AppFonts;

@SuppressWarnings("serial")
public class LoginWindow extends JFrame {
	private LoginView loginView;
	
	public LoginWindow() {
		//setSize(100, 100);
		//setLocation(100, 100);
		setBounds(100, 100, 650, 680);
		setResizable(false);
		setTitle("SteakGames");
		//FlatLightLaf.setup(); 
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/assets/SteakGames.png");
		setIconImage(icono);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		UIManager.put("Label.font", AppFonts.normal());
		UIManager.put("Button.font", AppFonts.title());
		UIManager.put("TextField.font", AppFonts.small());		

		loginView = new LoginView(this); 
		add(loginView);
		GestorCursor.aplicarATodo(this);
		
	}
	public LoginView getLoginView() {
		return loginView;
	}
	
}
