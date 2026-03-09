package javaProyect1;

import views.FormularioRegistro;
import views.LoginWindow;
import assets.GestorCursor;

public class Main {
    public static void main(String[] args) {
    	abrirFormulario();
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