package controllers;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.Videojuego;
import views.FormularioJuego;

public class FormularioVideojuegoDialogController {

	private FormularioJuego view;
	private Videojuego videojuego;
	
	public FormularioVideojuegoDialogController(FormularioJuego view, Videojuego videojuegoSeleccionado) {

        this.view = view;
        this.videojuego = videojuegoSeleccionado;
        inicializarListeners();
        cargarDatos();
	}
	public void inicializarListeners() {
		//view.getComboPlataformas().addActionListener(e -> );
		//lo mismo para generos
		
		// --- 3. LIMPIAR Y ASIGNAR Listener GENERO ---
		view.getRbCrossplayNo().addActionListener(e -> validarCrossPlay());
		view.getRbCrossplaySi().addActionListener(e -> validarCrossPlay());
		  
		  
		// --------VALIDACIONES EN TIEMPO REAL (TEXTFIELDS) --------
		checarSiCompletoCampo(view.getTxtTitulo(), view.getLblErrorTitulo());
		checarSiCompletoCampo(view.getTxtPrecio(), view.getLblErrorPrecio());
		checarSiCompletoCampo(view.getTxtLinkDescarga(), view.getLblErrorLink());
		
		view.getBotonCrear().addActionListener(e -> save());
		view.getBotonCancelar().addActionListener(e -> view.dispose());
        
	}
    
	public boolean validarFormulario() {
		boolean valid = true;
		
		//TODO editar validaciones
		//JTextField
		if (!validarTitulo()) valid = false;
		if (!validarPrecio()) valid = false;
		if (!validarLink()) valid = false;
		//CheckBotons
		if (!validarCrossPlay()) valid = false;
		//El JtextArea
		if (!validarDescripcion()) valid = false;
		//TODO Generos y plataforma aclarar este show
		//if (!validateComboDia()) valid = false;
		//if (!validateGender()) valid = false;
		
		if (valid) {
			return true;
		}
		return false;
	}
	
	//TODO cambiar validaciones 
	 private boolean validarJTextField(JTextField campoChecar, JLabel labelError) {
        if (campoChecar.getText().trim().isEmpty()) {
            labelError.setText("Es obligatorio llenar este campo");
            return false;
        }
        /*
        if (campoChecar.getDocument().equals(view.getTxtEmail().getDocument())) {
            if (!campoChecar.getText().contains("@")) {
                labelError.setText("Email inválido");
                return false;
            }
        }*/
        
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

    private boolean validarTitulo() {
        if (view.getTxtTitulo().getText().trim().isEmpty()) {
            view.getLblErrorTitulo().setText("El titulo es obligatorio");
            return false;
        }
        return true;
    }
    private boolean validarPrecio() {
        if (view.getTxtPrecio().getText().trim().isEmpty()) {
            view.getLblErrorPrecio().setText("El Precio es obligatorio");
            return false;
        }
        if(Float.valueOf(view.getPrecio()) == null) {
        	view.getLblErrorPrecio().setText("Ingrese solo numeros");
        }
        view.getLblErrorPrecio().setText(" ");
        return true;
    }
    
    private boolean validarLink() {
        if (view.getTxtLinkDescarga().getText().trim().isEmpty()) {
            view.getLblErrorLink().setText("El titulo es obligatorio");
            return false;
        }
        return true;
    }
    private boolean validarCrossPlay() {
    	if (!view.getRbCrossplayNo().isSelected() && !view.getRbCrossplaySi().isSelected()) {   
            return false;
        }
        return true;
    }

    private boolean validarDescripcion() {
        if (view.getDescripcion().isBlank()) {
            view.getLblErrorDesc().setText("Complete este campo");
            return false;
        }
        view.getLblErrorDesc().setText(" ");
        return true;
    }
/*
    private boolean validarplataformas() {
        if (view.getComboDias().getSelectedIndex() == 0) {
            view.getLblErrorDia().setText("Seleccione un Día");
            return false;
        }
        
        view.getLblErrorDia().setText(" ");
        return true;
    }
*/
    
	    
    private void cargarDatos() {
    	view.mostrarDatos(videojuego);
    }
    private void save() {
    	
    	if(!validarFormulario()) {
    		return;
    	}
    	
    	String titulo = view.getTituloJuego();
    	Float precio = Float.valueOf(view.getPrecio());
    	//List<String> genero = view.getGenero();
        String descripcion = view.getDescripcion();
        String link = view.getLinkDescarga();
        boolean crossplay = view.getRbCrossplaySi().isSelected();//Solo sera true si se selecciona si
        String portada = view.getSelectedImagePath();
        String multijugador;
        
        
        if(videojuego == null) { //															NO tiene imagen
        	//this.videojuego = new Videojuego(titulo, link, 0, descripcion, link, portada, crossplay, crossplay, link)
        	
        }else {
        	//Usar SET
            
        }
        
        view.confirmarGuardado();
    }

	public Videojuego getVideojuego() {
		return videojuego;
	}
    
}
