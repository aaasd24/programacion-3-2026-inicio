package views;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tablamodelos.Tablamodelousuario;

public class UsuarioView extends JPanel{

	
	private JTable tabla;
	
	public UsuarioView() {
		setLayout(new BorderLayout());
		tabla = new JTable();
		
		add(new JScrollPane(tabla), BorderLayout.CENTER);
		
	}
	
	public void setModeloTable(Tablamodelousuario modelo) {
		tabla.setModel(modelo);
		
	}
	
	public JTable getTable() {
		return tabla;
	}
}
