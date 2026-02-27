package javaProyect1;

import views.FormularioRegistro;
import assets.GestorCursor;

public class Main {
    public static void main(String[] args) {
       
        abrirLogin();
    }

    public static void abrirLogin() {
        MyVentana ventana = new MyVentana();
        ventana.setVisible(true);
    }

    public static void irAlRegistro(javax.swing.JFrame ventanaActual) {
        ventanaActual.dispose(); //para cerrar el Login
        
        FormularioRegistro formulario = new FormularioRegistro();
        GestorCursor.aplicarATodo(formulario); 
        formulario.setVisible(true);
    }
}