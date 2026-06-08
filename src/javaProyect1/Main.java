package javaProyect1;

import views.FormularioRegistro;
import views.LoginWindow;
import views.MainWindow;


import controllers.LoginController;
import models.Usuario;
import utils.Session;
import utils.ThemeManager;

public class Main {
    public static void main(String[] args) {
    	ThemeManager.applySavedTheme();
    	//abrirLogin();
    	abrirMain();
    	//abrirFormulario();
    	System.out.println("jalando");
    	
    }
    
    public static void abrirLogin() {
    	LoginWindow ventana = new LoginWindow();
        new LoginController(ventana.getLoginView());
        ventana.setVisible(true);
    }
    public static void abrirFormulario() {
        FormularioRegistro formularioVentana = new FormularioRegistro();
        formularioVentana.setVisible(true);
    }
    public static void abrirMain() {
    	Session.login(new Usuario());
    	MainWindow mainWind = new MainWindow();
    	mainWind.setVisible(true);
    }
}










