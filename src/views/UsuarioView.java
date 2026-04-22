package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import tablamodelos.Tablamodelousuario;

public class UsuarioView extends JPanel{

	private JButton btnEdit;
	private JButton btnAdd;
	private JButton btnDelete;
	private JTable tabla;
	JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	public UsuarioView() {
		setLayout(new BorderLayout());
		tabla = new JTable();

		add(new JScrollPane(tabla), BorderLayout.CENTER);
		


        btnAdd = new JButton("Agregar");
        btnEdit = new JButton("Editar");
        btnDelete = new JButton("Eliminar");

        panelButtons.add(btnAdd);
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        
        add(panelButtons, BorderLayout.NORTH);
	}
	
	public void setModeloTable(Tablamodelousuario modelo) {
		tabla.setModel(modelo);
		
	}
	
	public JTable getTable() {
		return tabla;
	}

	public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }
	
    public int getSelectedRow() {
    	return tabla.getSelectedRow();
    }
}
