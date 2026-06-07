package controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import models.Videojuego;
import views.FormularioJuego;

public class FormularioVideojuegoController {

	private FormularioJuego view;
	private Videojuego videojuego;
	
	public FormularioVideojuegoController(FormularioJuego view, Videojuego videojuegoSeleccionado) {

        this.view = view;
        this.videojuego = videojuegoSeleccionado;
        inicializarListeners();
        cargarDatos();
	}
	public void inicializarListeners() {
		view.getBotonCrear().addActionListener(e -> validarFormulario());
		// --------VALIDACIONES EN TIEMPO REAL (TEXTFIELDS) --------
		checarSiCompletoCampo(view.getTxtTitulo(), view.getLblErrorTitulo());
		checarSiCompletoCampo(view.getTxtPrecio(), view.getLblErrorPrecio());
		checarSiCompletoCampo(view.getTxtLinkDescarga(), view.getLblErrorLink());
		checarSiCompletoCampoA(view.getTxtDescripcion(), view.getLblErrorDesc());
		
		view.getTxtPrecio().addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				txtCelularKeyTyped(e);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				txtCelularKeyTyped(e);
			}
			@Override
			public void keyPressed(KeyEvent e) {
				txtCelularKeyTyped(e);
			}
		});
		// --- 2 Asignar listeners a demas campos
		view.getJListaGeneros().addListSelectionListener(e -> validarGeneros());
		view.getJListaPlataformas().addListSelectionListener(e -> validarplataformas());
		// --- 3. LIMPIAR Y ASIGNAR Listener GENERO ---
		view.getRbCrossplayNo().addActionListener(e -> validarCrossPlay());
		view.getRbCrossplaySi().addActionListener(e -> validarCrossPlay());
		
		view.getChkCoopLocal().addActionListener(e -> validarMulti());
		view.getChkCoopOnline().addActionListener(e -> validarMulti());
		view.getChkLocal().addActionListener(e -> validarMulti());
		view.getChkOnline().addActionListener(e -> validarMulti());
		
		view.getBotonSeleccionarPortada().addActionListener(e -> view.elegirImagen());
		
		view.getBotonCrear().addActionListener(e -> save());
		view.getBotonCancelar().addActionListener(e -> view.dispose());
        
	}
    
	public boolean validarFormulario() {
		boolean valid = true;
		
		//JTextField
		if (!validarTitulo()) valid = false;
		if (!validarPrecio()) valid = false;
		if (!validarLink()) valid = false;
		//CheckBotons
		if (!validarCrossPlay()) valid = false;
		if(!validarMulti()) valid = false;
		//El JtextArea
		if (!validarDescripcion()) valid = false;
		if (!validarplataformas()) valid = false;
		if (!validarGeneros()) valid = false;
		if (!validarImagen()) valid = false;
		
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
        
        labelError.setText(" ");
        return true;
    }
	private boolean validarJTextArea(JTextArea campoChecar, JLabel labelError) {
        if (campoChecar.getText().trim().isEmpty()) {
            labelError.setText("Es obligatorio llenar este campo");
            return false;
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
    private void checarSiCompletoCampoA(JTextArea campoARellenar, JLabel labelError) {
        campoARellenar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                validarJTextArea(campoARellenar, labelError);
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                validarJTextArea(campoARellenar, labelError);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                validarJTextArea(campoARellenar, labelError);
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

    private boolean validarplataformas() {
        if (view.getJListaPlataformas().isSelectionEmpty()) {
            view.getLblErrorPlataforma().setText("Seleccione minimo una plataforma");
            return false;
        }
        
        view.getLblErrorPlataforma().setText(" ");
        return true;
    }    
    
    private boolean validarGeneros() {
    	if(view.getJListaGeneros().isSelectionEmpty()) {
    		view.getLblErrorGeneros().setText("Seleccione minimo un genero");
    		return false;
    	}
    	view.getLblErrorGeneros().setText(" ");
    	return true;
    }
    
    private boolean validarMulti() {
    	if(!(view.isCoopLocal() == true || view.isCoopOnline() == true || view.isLocal() == true || view.isOnline() == true)) {
    		view.getLblErrorMulijugador().setText("Indique un modo de multijugador");
    		return false;
    	}
    	view.getLblErrorMulijugador().setText(" ");
    	return true;
    }
    private boolean validarImagen(){
	    if(view.getSelectedImagePath() == null){
	        view.getLblErrorPortada().setText("Selecciona una imagen");
	        return false;
	    }

	    view.getLblErrorPlataforma().setText(" ");
	    return true;
	}
    private void cargarDatos() {
    	view.mostrarDatos(videojuego);
    }
    private void save() {
    	
		if(!validarFormulario()) {
			return;
		}
		
		String titulo = view.getTituloJuego();
		Float precio = Float.valueOf(view.getPrecio());
		List<String> genero = view.getJListaGeneros().getSelectedValuesList();
		String descripcion = view.getDescripcion();
		String link = view.getLinkDescarga();
		boolean crossplay = view.getRbCrossplaySi().isSelected();//Solo sera true si se selecciona si
		String portada = view.getSelectedImagePath();
		List<String> plataformas = view.getJListaPlataformas().getSelectedValuesList();
		String multi = " ";
		if(view.getChkCoopLocal().isSelected()) {
			multi = "Coop Local";
		}
		else if(view.getChkCoopOnline().isSelected()) {
			multi = "Coop Online";
		}
		else if(view.getChkLocal().isSelected()) {
			multi = "Local";
		}
		else if(view.getChkOnline().isSelected()) {
			multi = "Online";
		}
		
		
        if(videojuego == null) { //															NO tiene imagen
        	this.videojuego = new Videojuego(titulo, genero, precio, descripcion, link, portada, plataformas, crossplay, multi);
        }else {
        	this.videojuego.setTitulo(titulo);
        	this.videojuego.setPrecio(precio);
        	this.videojuego.setGeneros(genero);
        	this.videojuego.setDescripcion(descripcion);
        	this.videojuego.setDireccionArchivo(link);
        	this.videojuego.setPortadaPath(portada);
        	this.videojuego.setPlataforma(plataformas);
        	this.videojuego.setCrossplay(crossplay);
        	this.videojuego.setMultijugador(multi);
        }
        view.confirmarGuardado();
    }

	public Videojuego getVideojuego() {
		return videojuego;
	}
	private void txtCelularKeyTyped(KeyEvent evt) {
		int tecla = evt.getKeyChar();

		boolean caracteresValidos = tecla > 47 && tecla < 58 || tecla == 46 || tecla == 8 || tecla == 127 || tecla == 65535;
	            
	     if (!caracteresValidos )
	    {
	    	 evt.consume();
	    }
	}
}
