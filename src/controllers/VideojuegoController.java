package controllers;


import java.awt.Desktop;
import java.io.File;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import models.Videojuego;
import repositorio.RepositorioVideojuegos;
import servicios.PDFExportadorVideojuego;
import tablamodelos.Tablamodelovideojuego;
import views.FormularioJuego;
import views.VideojuegoView;

public class VideojuegoController{

	private VideojuegoView view;
	private Tablamodelovideojuego tabla;
	private RepositorioVideojuegos repo;
	private PDFExportadorVideojuego expo;
	
	
	public VideojuegoController(VideojuegoView view) {
		this.view = view;
		this.repo = new RepositorioVideojuegos();
		this.expo = new PDFExportadorVideojuego();
		
		//Listener boton [  AGRREGAR JUEGO  ]
		view.getBtnAdd().addActionListener(e -> abrirFormulario(null));
		
		//LIstener boton [  EDITAR Juego ]
		view.getBtnEdit().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row != -1 ) {
				Videojuego seleccionado = tabla.getVideojuegorAt(row);
				abrirFormulario(seleccionado);
			}else {
				JOptionPane.showMessageDialog(view, "Seleccione un juego a editar");
			}
		});
		
		//Listener boton [ eLIMINAR ] 
		view.getBtnDelete().addActionListener(e ->{
			boolean eliminar = repo.eliminar(tabla.getVideojuegorAt(view.getSelectedRow()).getId());
			if(eliminar) {
				tabla.removeRow(view.getSelectedRow());
			}
			
		});
		//Listener boton [ CREAR PDF JUEGO  ]
		view.getBtnExportarVideojuego().addActionListener(e -> generrarPDF());
	}
	
	public void cargarJuegos() {
		System.out.println("Se muestran juegos");
		try {
			List<Videojuego> lista = repo.obtenerListaVideojuegos();
			if(tabla == null) {
				tabla = new Tablamodelovideojuego(lista);
				view.setModeloTable(tabla);
			}else {
				tabla.setJuegos(lista);
			}
			view.getTable().revalidate();
			view.getTable().repaint();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	private void abrirFormulario(Videojuego videojuego) {
        System.out.println("Formulario abierto");
        FormularioJuego dialog = new FormularioJuego(null);
        FormularioVideojuegoController dialogControlador = new FormularioVideojuegoController(dialog, videojuego);
        
        // Si estamos editando, mostramos los datos existentes
        if (videojuego != null) {
            dialog.mostrarDatos(videojuego);
        }
        
        dialogControlador.inicializarListeners();
        dialog.setVisible(true);
        System.out.println("Se prendio el cerro");
        
        if (dialog.estaGuardado()) {
            Videojuego videojuegoActual = dialogControlador.getVideojuego(); 
            try {
                if (videojuego == null) { // Videojuego nuevo
                    System.out.println("Se crea nuevo Juego");
                    
                    //Guarda el registro base en la tabla videojuego
                    repo.subirVideojuego(videojuegoActual);
                    
                    //Inserto las relaciones usando UPPER y TRIM
                    repo.conectarVideojuegosGeneros(videojuegoActual);
                    repo.conectarVideojuegoPlataforma(videojuegoActual);
                    
                } else { // Actualizar videojuego
                    System.out.println("Se edito un Juego");
                    int row = view.getSelectedRow();
                    boolean actualizar = repo.actualizar(row, videojuegoActual);
                    if (actualizar) {
                        repo.actualizarGeneros(videojuegoActual);
                        repo.actualizarPlataformas(videojuegoActual);
                        
                        this.cargarJuegos(); 
                        System.out.println("Catálogo actualizado en la interfaz con éxito.");
                    } else {
                        JOptionPane.showMessageDialog(view, "No se pudieron guardar los cambios en la base de datos.", "Error de actualización", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (Exception e) { 
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error al guardar en la base de datos: " + e.getMessage());
            } 
        } 
    } 
	
	 private void generrarPDF() {
		 File file = view.seleccionarPdfFile();
			if(file == null) {
				return;
			}
			try {
				expo.exportarVideojuegos(repo.obtenerListaVideojuegos(), file); 
				if(Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(file);
				}
			}catch(Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(view, "Error al exportar no soporta el sistema");
			}
		}
}
