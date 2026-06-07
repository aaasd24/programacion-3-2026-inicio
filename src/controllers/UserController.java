package controllers;

import repositorio.RepositorioUsuarios;
import servicios.PDFExportador;
import tablamodelos.Tablamodelousuario;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import models.Usuario;
import views.UsuarioView;
import views.FormularioUsuarioDialog;

public class UserController {

    private UsuarioView view;
    private RepositorioUsuarios repo;
    private Tablamodelousuario model;
    private PDFExportador expPDF;
    

    public UserController(UsuarioView view) {
        this.view = view;
        this.repo = new RepositorioUsuarios();
        this.expPDF = new PDFExportador();

        // BOTÓN AGREGAR
        view.getBtnAdd().addActionListener(e -> openForm(null));

        // BOTÓN EDITAR (Si tienes el getter en tu view)
        view.getBtnEdit().addActionListener(e -> {
            int row = view.getSelectedRow();
            if (row != -1) {
                Usuario seleccionado = model.getUserAt(row);
                openForm(seleccionado);
            } else {
                JOptionPane.showMessageDialog(view, "Selecciona un usuario para editar");
            }
        });
        
        // BOTÓN ELIMINAR
        view.getBtnDelete().addActionListener(e -> {
			boolean eliminar = repo.delete(model.getUserAt(view.getSelectedRow()).getId());
			if(eliminar) {
				model.removeRow(view.getSelectedRow());
			}
           
        });
        view.getBtnExportarPDF().addActionListener(e -> generarPdf());
        
    }
    
    public void loadUsers() {
    	System.out.println("Se muestra usuarios");
        try {
            List<Usuario> listaFresca = repo.obtenerUsuarios();
            if(model == null) {
                model = new Tablamodelousuario(listaFresca);
                System.out.println(model.getColumnName(0));
                view.setModeloTable(model);
            } else {
                model.setUsers(listaFresca);
            }
            
            view.getTable().revalidate();
            view.getTable().repaint();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void openForm(Usuario user) {
    	
        // null para crear un nuevo usuario, user para actualizar un usuario existente
    	System.out.println("Creando nuevo usuario");
        FormularioUsuarioDialog dialog = new FormularioUsuarioDialog(null);
        FormularioUsuarioDialogController dialogControlador = new FormularioUsuarioDialogController(dialog, user);
        dialog.setVisible(true);
        dialogControlador.inicializarListeners();
        if (dialog.isSaved()) {
            Usuario savedUser = dialogControlador.getUsuario(); 
            try {
                if (user == null) { //usuario nuevo
                	System.out.println("Se crea nuevo usuario");
                    repo.guardarUsuario(savedUser);
                    model.addRow(savedUser);
                    
                } else {//actualizar usuario
                	System.out.println("Se edito un usuario");
                    int row = view.getSelectedRow();
                    boolean actualizar = repo.update(row, savedUser);
                    if(actualizar) {
                    	model.updateRow(row, savedUser);
                    }
                }
                this.loadUsers();
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error al guardar: " + e.getMessage());
            }

        }
    }
    
    public void generarPdf() {
		File file = view.seleccionarPdfFile();
		if(file == null) {
			return;
		}
		try {
			expPDF.exportarUsuarios(repo.obtenerUsuarios(), file); 
			if(Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(file);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Error al exportar no soporta el sistema");
		}
		
		
	}
}  