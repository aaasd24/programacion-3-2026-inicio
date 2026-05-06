package javaProyect1;

import views.FormularioRegistro;
import views.LoginWindow;
import views.MainWindow;
import controllers.LoginController;

public class Main {
    public static void main(String[] args) {
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











