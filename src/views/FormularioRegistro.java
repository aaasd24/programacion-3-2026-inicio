package views;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import assets.GestorCursor;
import assets.AppFonts;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import assets.GestorCursor;

public class FormularioRegistro extends JFrame {
	
	public FormularioRegistro() {
	
		//atributos del formulario
		//NOmbre usuario y nombre de cuenta, Fecha de nacimiento, genero, datos bancarios y su contraseña y su csv. contraseña de cuenta, ubicacion/region 
		
		
		setSize(400, 400);
		setResizable(false);
		setTitle("SteakGames");
		setLocationRelativeTo(null);
		//Siempre el set visible al final del constructor

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image icono = tk.getImage("src/assets/SteakGames.png");
		setIconImage(icono);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		inicializarComponentes();
		setVisible(true);		
	}
	
	public void inicializarComponentes() {
		JPanel panelComponentes = new JPanel();
		panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
		panelComponentes.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		GestorCursor.aplicarATodo(this);
		//prueba de fuente//
		/*JLabel lblTitulo = new JLabel("Registro");
		lblTitulo.setFont(AppFonts.title());
		add(lblTitulo, BorderLayout.NORTH);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		/*for(int i = 0; i < 7; i++) {
			JLabel lbl = new JLabel("Campo " + i);
			panelComponentes.add(lbl);
			JTextField txt = new JTextField(20);
			panelComponentes.add(txt);
		}*/
		JLabel lblNombreUsuario = new JLabel("Nombre de usuario");
		panelComponentes.add(lblNombreUsuario);
		
		JTextField txtNombre = new JTextField(20);
		panelComponentes.add(txtNombre);
		
		JLabel lblCuenta = new JLabel("Nombre de cuenta");
		panelComponentes.add(lblCuenta);

		JTextField txtNombreCuenta = new JTextField(20);
		panelComponentes.add(txtNombreCuenta);
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento");
		panelComponentes.add(lblFechaNacimiento);
		
		

		JLabel lblAnio = new JLabel("Año:");
		panelComponentes.add(lblAnio);

		JTextField txtAnioNacimiento = new JTextField(20);
		panelComponentes.add(txtAnioNacimiento);
		JLabel lblMes = new JLabel("Mes:");
		panelComponentes.add(lblMes);
		String meses[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio" , "Julio" , "Agosto" , "Septiembre" , "Octubre", "Noviembre" , "Diciembre"};
		JComboBox<String> comboMeses = new JComboBox<String>(meses);
		//comboMeses.setSelectedIndex(0);
		panelComponentes.add(comboMeses);
		
		JLabel lblDia = new JLabel("Dia:");
		panelComponentes.add(lblDia);
		
		
		JLabel lblGenero = new JLabel("Genero");
		panelComponentes.add(lblGenero);
		//JLabel lblNumeroCuentaBanco = new JLabel("Numero de cuenta *");
		//panelComponentes.add(lblNumeroCuentaBanco);
		//JLabel lblNumeroContrasenia = new JLabel("Contraseña de cuenta de Banco *");
		//panelComponentes.add(lblNumeroContrasenia);
		JLabel lblRegion = new JLabel("Región");
		panelComponentes.add(lblRegion);
		JLabel lblContraseniaImportante = new JLabel("Contraseña");
		panelComponentes.add(lblContraseniaImportante);
		JScrollPane scroll = new JScrollPane(panelComponentes);
		scroll.setHorizontalScrollBar(null);
		
		
		
		
		
		add(scroll);
	}

	//add(panelComponentes);
}
