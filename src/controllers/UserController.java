package controllers;

import repositorio.RepositorioUsuarios;
import tablamodelos.Tablamodelousuario;

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

    public UserController(UsuarioView view) {
        this.view = view;
        this.repo = new RepositorioUsuarios();

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
            int row = view.getSelectedRow();
            if (row != -1) {
                try {
                    repo.delete(row); // Borra del CSV
                    System.out.println("Se borro  un usuario");
                    loadUsers();      // Recarga la tabla
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(view, "Error al eliminar: " + ex.getMessage());
                }
            }
           
        });
        
    }
    
    public void loadUsers() {
    	System.out.println("Se muestra usuarios");
        try {
            List<Usuario> listaFresca = repo.obtenerUsuarios();
            if(model == null) {
                model = new Tablamodelousuario(listaFresca);
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
    	
        // null para el parent, user para saber si es edición o nuevo
    	System.out.println("Creando nuevo usuario");
        FormularioUsuarioDialog dialog = new FormularioUsuarioDialog(null, user);
        dialog.setVisible(true);

        if (dialog.isSaved()) {
            Usuario savedUser = dialog.getUsuario(); 

            try {
                if (user == null) { //usuario nuevo
                	System.out.println("Se crea nuevo usuario");
                    repo.guardarUsuario(savedUser); 
                    
                } else {//actualizar usuario
                	System.out.println("Se edito un usuario");
                    int row = view.getSelectedRow();
                    repo.update(row, savedUser);
                    
                }
                this.loadUsers();
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error al guardar: " + e.getMessage());
            }

        }
    }
}  