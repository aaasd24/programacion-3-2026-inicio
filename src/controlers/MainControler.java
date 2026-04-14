package controlers;

import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import views.LoginWindow;
import views.MainWindow;

public class MainControler {
	
	private MainWindow view;
	
	public MainControler(MainWindow view) {
		this.view = view;
		
	}

	public void registerListeners( ) {

		view.getSalir().addActionListener(e -> handleClose());

		view.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				handleClose();
			}
		});
		
	}
	private void handleClose() {
		int option = view.confirmExit();
		System.out.println(option);

		if (option == JOptionPane.YES_OPTION) {
			new LoginControler(new LoginWindow().getLoginView());
			view.dispose();
		}
	}
	
}
