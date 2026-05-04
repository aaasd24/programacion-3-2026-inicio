package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import assets.AppFonts;
import assets.Colores;
import tablamodelos.Tablamodelousuario;


@SuppressWarnings("serial")
public class UsuarioView extends JPanel{

	private JButton btnEdit;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnExportarPDF;
	private JTable tabla;
	JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	public UsuarioView() {
		setLayout(new BorderLayout());
		tabla = new JTable();

		add(new JScrollPane(tabla), BorderLayout.CENTER);
		


        btnAdd = new JButton("Agregar");
        btnEdit = new JButton("Editar");
        btnDelete = new JButton("Eliminar");
        btnExportarPDF = new JButton("Exportar a PDF");
        panelButtons.add(btnAdd);
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        panelButtons.add(btnExportarPDF);
        estilizarTabla();
        add(panelButtons, BorderLayout.NORTH);
	}
	

	
	/**
	 * Esta funcion le da el detallismo a la tabla de usuarios. Mas abajo hay indicaciones para que puedas editarla aca chido.
	 * (con madre we, voy a intentar ver como poner los bordes de fuego en los botones)
	 */
	public void estilizarTabla() {
		//Altura de las celdas y configuraciones por defecto.
		tabla.setRowHeight(35);
		
		tabla.setShowGrid(true);
		tabla.setGridColor(new Color(230, 230, 230));
		tabla.setBackground(Colores.colorear(1));
		tabla.setForeground(Color.BLACK);
		tabla.setFont(AppFonts.normal());
		
		//Colores que se asignan cuando es seleccionado una celda. TODO cambia colores a tu gusto
		tabla.setSelectionBackground(new Color(52, 152, 219));
		tabla.setSelectionForeground(Color.WHITE);
		
		//Establece que solo uno se puede seleccionar
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Configuracion por defecto de la cabeza de la tabla. TODO cambia color, tamaño y estilo de letra al que más convenga
		JTableHeader header = tabla.getTableHeader();
		header.setBackground(Colores.colorear(2)); //Color de fondo
		header.setForeground(Color.WHITE);			//Color de letra
		header.setFont(AppFonts.negrita());			//Estilo de letra
		header.setPreferredSize(new Dimension(0, 40));	//Tamaño de la celda
		header.setReorderingAllowed(false);				//Nose
		
		//Funcion que incia que todo vaya bien supongo.
		tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //Aqui se cambia los colores de fondo para que no todo se vea blanco
                
                /*
                 * Por defecnto si no es seleccionado, las filas pares son de color blanco y las impares de un blanco mas oscuro
                 * La letra siempre de color negro
                 */
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Colores.colorear(1));
                    } else {
                        c.setBackground(Colores.colorear(1));
                    }

                    c.setForeground(Color.BLACK);
                }
				
                /*
                 * Si es la columna 1, esta letra sera negrita y con color azul siempre y cuando no sea SELECCIONADA
                 * 
                 * Por defecto todo estara en normal.
                 */
				if(column == 1) {
					c.setFont(AppFonts.negrita());
					if(!isSelected) {
						c.setForeground(Color.BLACK);
					}
				} else {
					c.setFont(AppFonts.normal());
				}
			
				
				return c;
				
			}
			
		});
		
	}
	
	
	public File seleccionarPdfFile() {
		
		String path = System.getProperty("user.home");
		JFileChooser chooser = new JFileChooser(path);
		
		chooser.setSelectedFile(new File("reporte-usuarios.pdf"));
		
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos PDF",  "pdf");
		chooser.addChoosableFileFilter(filter);
		chooser.setFileFilter(filter);
		
		int option = chooser.showDialog(this, "Exportar PDF de usuarios");
		
		if(option != JFileChooser.APPROVE_OPTION) {
			return null;
		}
		
		File file = chooser.getSelectedFile();
		
		if(!file.getName().toLowerCase().endsWith(".pdf")) {
			file = new File(file.getAbsolutePath() + ".pdf");
		}
		
		return file;
	}


	public void setModeloTable(Tablamodelousuario modelo) {
		this.tabla.setModel(modelo);
		//establace los tamaños de cada columna, Solo usamos 3 filas 
	    if(tabla.getColumnCount() >= 1) {
			tabla.getColumnModel().getColumn(0).setPreferredWidth(80);
		}
		
		if(tabla.getColumnCount() >= 2) {
			tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
		}
		
		if(tabla.getColumnCount() >= 3) {
			tabla.getColumnModel().getColumn(2).setPreferredWidth(50);
		}
		
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		
		if(tabla.getColumnCount() >= 1) {
			tabla.getColumnModel().getColumn(0).setCellRenderer(center);
		}
		this.tabla.revalidate();
	    this.tabla.repaint();
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
    }public JButton getBtnExportarPDF() {
        return btnExportarPDF;
    }
	
    public int getSelectedRow() {
    	return tabla.getSelectedRow();
    }
}
