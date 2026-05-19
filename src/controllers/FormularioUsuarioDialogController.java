package controllers;

import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.Usuario;
import repositorio.RepositorioUsuarios;
import views.FormularioUsuarioDialog;

public class FormularioUsuarioDialogController {

	private FormularioUsuarioDialog view;
	private Usuario usuario;
	
	public FormularioUsuarioDialogController(FormularioUsuarioDialog view, Usuario usuarioSeleccionado) {

        this.view = view;
        this.usuario = usuarioSeleccionado;
        inicializarListeners();
        cargarDatos();
	}
	public void inicializarListeners() {
        view.getBotonGuardar().addActionListener(e -> validarFormulario());

        view.getComboRegiones().addActionListener(e -> validateComboRegion());

        
        // --- 3. LIMPIAR Y ASIGNAR Listener GENERO ---
        // ------Regiones---------
        view.getRbHombre().addActionListener(e -> validateGender());
        view.getRbMujer().addActionListener(e -> validateGender());

        view.getComboAnios().addActionListener(e -> validateAnio());
        view.getComboMeses().addActionListener(e -> validateComboMes());
        view.getComboDias().addActionListener(e -> validateComboDia());

        

        // --------VALIDACIONES EN TIEMPO REAL (TEXTFIELDS) --------
        checarSiCompletoCampo(view.getTxtNombre(), view.getLblErrorNombre());
        checarSiCompletoCampo(view.getTxtEmail(), view.getLblErrorEmail());
        
        view.getBotonGuardar().addActionListener(e -> save());
        view.getBotonCancelar().addActionListener(e -> view.dispose());
	}
    
	public boolean validarFormulario() {
        boolean valid = true;

        
        if (!validateName()) valid = false;
        if (!validateEmail()) valid = false;
        if (!validateComboRegion()) valid = false;
        if (!validateAnio()) valid = false;
        if (!validateComboMes()) valid = false;
        if (!validateComboDia()) valid = false;
        if (!validateGender()) valid = false;

        if (valid) {
        	return true;
        }
        return false;
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
    	if (view.getComboAnios().getSelectedIndex() == 0) {
            view.getLblErrorAnio().setText("Seleccione un año");
            return false;
        }
        view.getLblErrorAnio().setText(" ");
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
	    
    private void cargarDatos() {
    	view.mostrarDatos(usuario);
    }
    private void save() {
    	
    	if(!validarFormulario()) {
    		return;
    	}
    	
    	String nombre = view.getTxtNombre().getText();
    	String correo = view.getEmailUsuario();
        int region = (int) view.getRegionID();
        String anio = view.getAnio();
        String mes =  view.getMes();
        String dia = view.getDia();
        String rol = "ADMIN";
        
        
        char genero = view.getGenero(); 
        
        if(usuario == null) { //															NO tiene imagen
        	this.usuario = new Usuario(nombre, correo, region, genero, anio, mes, dia, null, rol);
        }else {
        	this.usuario.setNombre(nombre);
        	this.usuario.setCorreo(correo);
        	this.usuario.setRegionID(region);
            this.usuario.setGenero(genero);
            this.usuario.setAnio(anio);
            this.usuario.setMes(mes);
            this.usuario.setDia(dia);
            this.usuario.setRol(rol);
            
        }
        
        view.confirmarGuardado();
    }
	public Usuario getUsuario() {
		return usuario;
	}
    
    
    
}
