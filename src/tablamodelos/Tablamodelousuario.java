package tablamodelos;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.Usuario;


@SuppressWarnings("serial")
public class Tablamodelousuario extends AbstractTableModel{
	
	private List<Usuario> listaUsuarios;
	private final String[] nombreColumnas = {
			"Nombre",
			"Correo",
			"Region"			
	};
	
	public Tablamodelousuario(List<Usuario> usuarios) {
		this.listaUsuarios = usuarios;
	}
	
	@Override
	public String getColumnName(int column) {
		return nombreColumnas[column];
	}
	
	
	@Override
	public int getRowCount() {
		return listaUsuarios.size();
	}

	@Override
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Usuario usuarioActual = listaUsuarios.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return usuarioActual.getNombre();
		case 1:
			return usuarioActual.getCorreo();
		case 2:
			return usuarioActual.getRegion();

		}
		
		return null;
	}
	public Usuario getUserAt(int row) {
		return listaUsuarios.get(row);
	}
	
	public void setUsers(List<Usuario> usuarios) {
		this.listaUsuarios = usuarios;
		fireTableDataChanged();
	}
	

}
