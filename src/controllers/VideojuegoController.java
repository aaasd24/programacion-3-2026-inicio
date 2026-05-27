package controllers;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import models.Videojuego;
import repositorio.RepositorioVideojuegos;
import servicios.PDFExportador;
import tablamodelos.Tablamodelovideojuego;
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
		//view.getBtnAdd().addActionListener(e -> cargarJuegoNuevo(null));
		
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
		System.out.println("Se muestrn juegos");
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
	
}
