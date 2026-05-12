package javaProyect1;

import views.FormularioRegistro;
import views.LoginWindow;
import views.MainWindow;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

import controllers.LoginController;
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
    	MainWindow mainWind = new MainWindow();
    	mainWind.setVisible(true);
    }
}

//TODO idea para modificar boton: investigar como podemos modificar el boton para que se encienda en llamas cuando presionas o pasas el cursor por encima//
//TODO CAMBIAR PALETA DE COLORES(URGE)//
//TODO modificar y agregar que en el formulario solo se pueda agregar numeros en la edad y letras en el nombre//
//TODO no logre entender porque no me dejo apuntar la libreria hash (ni la de sql) desde el proyecto asi que lo hice en mi pc, pero igualmente la agregue para el repositorio para que luego que tengas tiempo, waches si te pasa lo mismo










