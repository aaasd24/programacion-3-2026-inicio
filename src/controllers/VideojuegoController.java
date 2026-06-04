package controllers;


import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import models.Usuario;
import models.Videojuego;
import repositorio.RepositorioVideojuegos;
import tablamodelos.Tablamodelovideojuego;
import views.FormularioJuego;
import views.FormularioUsuarioDialog;
import views.VideojuegoView;

public class VideojuegoController{

	private VideojuegoView view;
	private Tablamodelovideojuego tabla; //TODO irving te toca editar esta seccion pa que se vea chido
	//private PDFExportador expo;
	private RepositorioVideojuegos repo;
	
	
	public VideojuegoController(VideojuegoView view) {
		this.view = view;
		this.repo = new RepositorioVideojuegos();
		//this.expPDF
		
		//Listener boton [  AGRREGAR JUEGO  ]
		view.getBtnAdd().addActionListener(e -> abrirFormulario(null));
		
		//LIstener boton [  EDITAR Juego ]
		view.getBtnEdit().addActionListener(e -> {
			int row = view.getSelectedRow();
			if(row != -1 ) {
				Videojuego seleccionado = tabla.getUserAt(row);
			}else {
				JOptionPane.showMessageDialog(view, "Seleccione un juego a editar");
			}
		});
		
		//Listener boton [ eLIMINAR ] 
		view.getBtnDelete().addActionListener(e ->{
			boolean eliminar = repo.eliminar(tabla.getUserAt(view.getSelectedRow()).getId());
			if(eliminar) {
				tabla.removeRow(view.getSelectedRow());
			}
		});
		
		//Listener boton [ CREAR PDF JUEGO  ]
		//view.getBtnExportarVideojuego().addActionListener(e -> generrarPDF());
	}
	public void cargarJuegos() {
		System.out.println("Se muestran juegos");
		try {
			List<Videojuego> lista = repo.obtenerListaVideojuegos();
			if(tabla == null) {
				tabla = new Tablamodelovideojuego(lista);
				view.setModeloTable(tabla);
			}else {
				tabla.setUsers(lista);
			}
			view.getTable().revalidate();
			view.getTable().repaint();
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	 private void abrirFormulario(Videojuego videojuego) {
	    	
	        // null para crear un nuevo usuario, user para actualizar un usuario existente
	        FormularioJuego dialog = new FormularioJuego();
	        FormularioVideojuegoDialogController dialogControlador = new FormularioVideojuegoDialogController(dialog, videojuego);
	        dialog.setVisible(true);
	        dialogControlador.inicializarListeners();
	        if (dialog.estaGuardado()) {
	            Videojuego videojuegoActual = dialogControlador.getVideojuego(); 
	            try {
	                if (videojuegoActual == null) { //usuario nuevo
	                	System.out.println("Se crea nuevo usuario");
	                    repo.subirVideojuego(videojuegoActual);
	                    //model.addRow(savedUser);
	                    
	                } else {//actualizar usuario
	                	System.out.println("Se edito un usuario");
	                    int row = view.getSelectedRow();
	                    boolean actualizar = repo.actualizar(row, videojuegoActual);
	                    if(actualizar) {
	                    	//model.updateRow(row, savedUser);
	                    }
	                }
	                this.cargarJuegos();
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	                JOptionPane.showMessageDialog(view, "Error al guardar: " + e.getMessage());
	            }

	        }
	    }
	
	
}
