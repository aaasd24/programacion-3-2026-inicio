package tablamodelos;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import models.Videojuego;

@SuppressWarnings("serial")
public class Tablamodelovideojuego extends AbstractTableModel{

	private List<Videojuego> listaVideojuego;
	private final String[] nombreColumnas = {
			"Nombre",
			"Generos",
			"Precio"
	};
	
	public Tablamodelovideojuego(List<Videojuego> videojuegos) {
		this.listaVideojuego = videojuegos;
	}
	
	public String getCoulmName(int columna) {
		return nombreColumnas[columna];
	}
	@Override
	public int getRowCount() {
		return listaVideojuego.size();
	}

	@Override
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Videojuego videojuegoActual = listaVideojuego.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return videojuegoActual.getTitulo();
		case 1:
			return videojuegoActual.getGeneros();
		case 2:
			return videojuegoActual.getPrecio();
		}
		return null;
	}
	public Videojuego getUserAt(int row) {
		return listaVideojuego.get(row);
	}
	
	public void setUsers(List<Videojuego> videojuegos) {
		this.listaVideojuego = videojuegos;
		fireTableDataChanged();
	}
	public void removeRow(int row) {
		listaVideojuego.remove(row);
		fireTableRowsDeleted(row, row);
	}


	public void addRow(Videojuego videojuego) {
		int row = listaVideojuego.size();
		listaVideojuego.add(videojuego);
		fireTableRowsInserted(row, row);
	}

	public void updateRow(int row, Videojuego videojuego) {
		listaVideojuego.set(row, videojuego);
		fireTableRowsUpdated(row, row);
	}
}
