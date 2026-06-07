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
	        // null para crear un nuevo usuario, user para actualizar un usuario existente
	        FormularioJuego dialog = new FormularioJuego(null);
	        FormularioVideojuegoController dialogControlador = new FormularioVideojuegoController(dialog, videojuego);
	        dialog.setVisible(true);
	        dialogControlador.inicializarListeners();
	        System.out.println("Cerro formulario");
	        if (dialog.estaGuardado()) {
	            Videojuego videojuegoActual = dialogControlador.getVideojuego(); 
	            try {
	                if (videojuego == null) { //Videojuego nuevo
	                	System.out.println("Se crea nuevo Juevo");
	                    repo.subirVideojuego(videojuegoActual);
	                    repo.conectarVideojuegosGeneros(videojuegoActual);
	                    repo.conectarVideojuegoPlataforma(videojuegoActual);
	                    
	                    tabla.addRow(videojuegoActual);
	                    
	                } else {//actualizar videojuego
	                	System.out.println("Se edito un Juego");
	                    int row = view.getSelectedRow();
	                    boolean actualizar = repo.actualizar(row, videojuegoActual);
	                    repo.actualizarGeneros(videojuegoActual);
	                    repo.actualizarPlataformas(videojuegoActual);
	                    if(actualizar) {
	                    	tabla.updateRow(row, videojuegoActual);
	                    }
	                }
	                this.cargarJuegos();
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(view, "Error al guardar: " + e.getMessage());
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
