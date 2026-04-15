package controllers;

import views.FormularioRegistro;
 import views.MainWindow; 

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.Usuario;
import views.MainWindow;
import repositorio.RepositorioUsuarios;

public class RegistrationController {

    private FormularioRegistro view;
    private RepositorioUsuarios repositorio;
   
    
    
    //el controlador recibe la vista para poder leer y modificar sus componentes
    public RegistrationController(FormularioRegistro view) {
        this.view = view;
        this.repositorio = new RepositorioUsuarios();
        initListeners();
        
    }

    //aqui se asignan todos los eventos que antes estaban en la vista
    //acabo de modficarlo de tal manera que antes de asignar una accion a cada componente, recorremos los que ya existen y los borramos basicamente, 
    //asi evitas que el controlador que se creaba varias veces por error y hacia que el registro se duplicara, se acumulara, osea ahora ya no xd
    public void initListeners() {
        // --- 1. LIMPIAR Y ASIGNAR BOTÓN PRINCIPAL ---
        for (java.awt.event.ActionListener al : view.getBoton().getActionListeners()) {
            view.getBoton().removeActionListener(al);
        }
        view.getBoton().addActionListener(e -> validateForm());

        // --- 2. LIMPIAR Y ASIGNAR COMBOBOXES ---
        // ------Regiones---------
        for (java.awt.event.ActionListener al : view.getComboRegiones().getActionListeners()) {
            view.getComboRegiones().removeActionListener(al);
        }
        view.getComboRegiones().addActionListener(e -> validateComboRegion());

        //----- Meses-------
        for (java.awt.event.ActionListener al : view.getComboMeses().getActionListeners()) {
            view.getComboMeses().removeActionListener(al);
        }
        view.getComboMeses().addActionListener(e -> validateComboMes());

        // ---Días----
        for (java.awt.event.ActionListener al : view.getComboDias().getActionListeners()) {
            view.getComboDias().removeActionListener(al);
        }
        view.getComboDias().addActionListener(e -> validateComboDia());

        // ---------------LIMPIAR Y ASIGNAR CHECKBOX --------------
        for (java.awt.event.ActionListener al : view.getChkAceptoCondiciones().getActionListeners()) {
            view.getChkAceptoCondiciones().removeActionListener(al);
        }
        view.getChkAceptoCondiciones().addActionListener(e -> validateTerms());

        // --------VALIDACIONES EN TIEMPO REAL (TEXTFIELDS) --------
        checarSiCompletoCampo(view.getTxtNombre(), view.getLblErrorNombre());
        checarSiCompletoCampo(view.getTxtEmail(), view.getLblErrorEmail());
        checarSiCompletoCampo(view.getTxtAnioNacimiento(), view.getLblErrorAnio());
        checarSiCompletoCampo(view.getTxtContra(), view.getLblErrorContrasenia());

        // -------LIMPIAR Y ASIGNAR EVENTOS DE VENTANA -------
        for (java.awt.event.WindowListener wl : view.getWindowListeners()) {
            view.removeWindowListener(wl);
        }
        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                view.getComboRegiones().requestFocusInWindow();
            }
        });
    
        
        view.getTxtNombre().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtNombre().selectAll();
            }
        });
        
        view.getTxtEmail().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtEmail().setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                view.getTxtEmail().setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            }
        });
        
    }

    private void guardarNuevoUsuario(Usuario usuarioNuevo) {
    	try {
    		repositorio.guardarUsuario(usuarioNuevo);
    	}catch(IOException ex) {
    		JOptionPane.showMessageDialog(view,"Error al guardar: " + ex.getMessage());
    	}
    }
    
    private void validateForm() {
    	view.getBoton().setEnabled(false); // Deshabilitar temporalmente(prueba para ver si con esto se dejan de duplicar los usuarios)(no era esto)
        boolean valid = true;

        
        if (!validateName()) valid = false;
        if (!validateEmail()) valid = false;
        if (!validateComboRegion()) valid = false;
        if (!validateAnio()) valid = false;
        if (!validateComboMes()) valid = false;
        if (!validateComboDia()) valid = false;
        if (!validateGender()) valid = false;
        if (!validateTerms()) valid = false;
        if (!validateContrasenia()) valid = false;

        if (valid) {
            pasarAMenu();
        } else {
            view.getBoton().setEnabled(true); 
        }
    }

    private void pasarAMenu() {
    	view.getBoton().setEnabled(false);
    	
    	Usuario usuarioNuevo = new Usuario(
    			view.getNombreUsuario(), 
    			view.getEmailUsuario(),
    			view.getPasswordusuario(), 
    			view.getRegion(), view.getAnio(),
    			view.getMes(),
    			view.getDia()
    	);
    	
    	guardarNuevoUsuario(usuarioNuevo);
        JOptionPane.showMessageDialog(
            view,
            "Se inició la sesión",
            "Sesion iniciada",
            JOptionPane.INFORMATION_MESSAGE
        );
        new MainWindow(); 
        view.dispose();
    }

    private boolean validarJTextField(JTextField campoChecar, JLabel labelError) {
        if (campoChecar.getText().trim().isEmpty()) {
            labelError.setText("Es obligatorio llenar este campo");
            return false;
        }
        
        if (campoChecar.getDocument().equals(view.getTxtEmail().getDocument())) {
            if (!campoChecar.getText().contains("@")) {
                labelError.setText("Email inválido");
                return false;
            }
        }
        labelError.setText(" ");
        return true;
    }

    private void checarSiCompletoCampo(JTextField campoARellenar, JLabel labelError) {
        campoARellenar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                validarJTextField(campoARellenar, labelError);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarJTextField(campoARellenar, labelError);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validarJTextField(campoARellenar, labelError);
            }
        });
    }

    // --- METODOS DE VALIDACION ESPECÍFICOS ---

    private boolean validateName() {
        if (view.getTxtNombre().getText().trim().isEmpty()) {
            view.getLblErrorNombre().setText("El nombre es obligatorio");
            return false;
        }
        return true;
    }
    
    private boolean validateAnio() {
        if (view.getTxtAnioNacimiento().getText().trim().isEmpty()) {
            view.getLblErrorAnio().setText("El año es obligatorio");
            return false;
        }
        view.getLblErrorAnio().setText(" ");
        return true;
    }
    
    private boolean validateContrasenia() {
        if (String.valueOf(view.getTxtContra().getPassword()).trim().isEmpty()) {
            view.getLblErrorContrasenia().setText("La contraseña es obligatoria");
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        if (view.getTxtEmail().getText().trim().isEmpty()) {
            view.getLblErrorEmail().setText("El email es obligatorio");
            return false;
        }
        if (!view.getTxtEmail().getText().contains("@")) {
            view.getLblErrorEmail().setText("Email inválido");
            return false;
        }
        return true;
    }

    private boolean validateComboMes() {
        if (view.getComboMeses().getSelectedIndex() == 0) {
            view.getLblErrorMes().setText("Seleccione un mes");
            return false;
        }
        view.getLblErrorMes().setText(" ");
        return true;
    }

    private boolean validateComboDia() {
        if (view.getComboDias().getSelectedIndex() == 0) {
            view.getLblErrorDia().setText("Seleccione un Día");
            return false;
        }
        view.getLblErrorDia().setText(" ");
        return true;
    }

    private boolean validateComboRegion() {
        if (view.getComboRegiones().getSelectedIndex() == 0) {
            view.getLblErrorRegion().setText("Seleccione un país");
            return false;
        }
        view.getLblErrorRegion().setText(" ");
        return true;
    }

    private boolean validateGender() {
        if (!view.getRbHombre().isSelected() && !view.getRbMujer().isSelected()) {
            view.getLblErrorGenero().setText("Seleccione un género");
            return false;
        }
        view.getLblErrorGenero().setText(" ");
        return true;
    }

    private boolean validateTerms() {
        if (!view.getChkAceptoCondiciones().isSelected()) {
            view.getLblErrorTerminos().setText("Debe aceptar los términos");
            return false;
        }
        view.getLblErrorTerminos().setText(" ");
        return true;
    }
}