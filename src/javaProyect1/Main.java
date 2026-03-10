package javaProyect1;

import views.FormularioRegistro;
import views.LoginWindow;
import assets.GestorCursor;

public class Main {
    public static void main(String[] args) {
    	abrirLogin();
    }
    
    public static void abrirLogin() {
        LoginWindow ventana = new LoginWindow();
        ventana.setVisible(true);
    }
    public static void abrirFormulario() {
        FormularioRegistro formularioVentana = new FormularioRegistro();
        formularioVentana.setVisible(true);
    }
    
    public static void irAlRegistro(javax.swing.JFrame ventanaActual) {
        ventanaActual.dispose(); //para cerrar el Login
        
        FormularioRegistro formulario = new FormularioRegistro();
        GestorCursor.aplicarATodo(formulario); 
        formulario.setVisible(true);
    }
}

//idea para modificar boton: investigar como podemos modificar el boton para que se encienda en llamas cuando presionas o pasas el cursor por encima//
//modificar boton de registro para que en lugar de boton sea texto clicleable/
//TODO CAMBIAR PALETA DE COLORES(URGE)//
