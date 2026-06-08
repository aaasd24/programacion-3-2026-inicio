package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import config.Config;
import tablamodelos.Tablamodelovideojuego;

@SuppressWarnings("serial")
public class VideojuegoView extends JPanel {

    private JButton btnEdit;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnExportarVideojuego;
    private JTable tabla;
    private MainWindow mainWindow;
    
    // Panel superior para los botones
    private JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
    
    public VideojuegoView(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setLayout(new BorderLayout());
        setOpaque(false); //panel base lo hago transparente

        //Configuración de la Tabla y el Scroll
        tabla = new JTable();
        
        btnAdd = crearBotonEstilizado("Agregar Juego");
        btnEdit = crearBotonEstilizado("Editar");
        btnDelete = crearBotonEstilizado("Eliminar");
        btnExportarVideojuego = crearBotonEstilizado("Exportar PDF");
        panelButtons.add(btnAdd);
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        panelButtons.add(btnExportarVideojuego);
        estilizarTabla();
        
        JScrollPane scrollPane = new JScrollPane(tabla);
       
        Color fondoSolidoOscuro = new Color(30, 30, 30); // Gris muy oscuro sólido
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.setBackground(fondoSolidoOscuro);
        scrollPane.getViewport().setBackground(fondoSolidoOscuro);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); 

        add(scrollPane, BorderLayout.CENTER);
        
        //Configuración de los Botones
        panelButtons.setOpaque(false);
        add(panelButtons, BorderLayout.NORTH);
    }
    
    /**
     * Sobrescribimos paintComponent para dibujar el fondo de Steak Games en esta vista
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            //fondo
            Image fondo = ImageIO.read(getClass().getResource("/assets/fondo.jpg"));
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            
            // Filtro oscuro para que los datos de la tabla contrasten y se lean bien
            g.setColor(new Color(0, 0, 0, 160)); 
            g.fillRect(0, 0, getWidth(), getHeight());
        } catch (Exception e) {
            System.out.println("Error al cargar fondo de vista de juegos: " + e.getMessage());
        }
    }
    
    /**
     * Método auxiliar para estilizar los botones con el tema oscuro/cápsula
     */
    private JButton crearBotonEstilizado(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(60, 60, 60, 200)); 
        boton.setForeground(Color.WHITE);
        boton.setFont(AppFonts.normal());
        boton.putClientProperty("JButton.buttonType", "roundRect"); // Estilo flatlaf
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        return boton;
    }

    /**
     * Estiliza la tabla para adaptarla al diseño oscuro y premium.
     */
    public void estilizarTabla() {
        tabla.setRowHeight(35);
        tabla.setShowGrid(true);
        tabla.setGridColor(new Color(60, 60, 60)); 
        
        tabla.setOpaque(true); 
        tabla.setBackground(new Color(30, 30, 30)); // Gris oscuro
        tabla.setForeground(Color.WHITE); 
        tabla.setFont(AppFonts.normal());
        
        // Color al seleccionar una fila 
        tabla.setSelectionBackground(new Color(211, 84, 0)); 
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Estilo de la Cabecera (Totalmente solida)
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(20, 20, 20)); // Gris casi negro
        header.setForeground(Color.WHITE);
        header.setFont(AppFonts.negrita());
        header.setPreferredSize(new Dimension(0, 45)); 
        header.setReorderingAllowed(false);
        
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                ((DefaultTableCellRenderer) c).setOpaque(true);
                
                // Efecto cebra con colores sólidos 
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(40, 40, 40)); // Filas pares (gris intermedio)
                    } else {
                        c.setBackground(new Color(30, 30, 30)); // Filas impares (gris oscuro)
                    }
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(new Color(211, 84, 0)); // Fila seleccionada (naranja)
                    c.setForeground(Color.WHITE);
                }
                
                // Estilo para resaltar la columna del Titulo (Columna 1)
                if (column == 1) { 
                    c.setFont(AppFonts.negrita());
                } else {
                    c.setFont(AppFonts.normal());
                }
                
                return c;
            }
        });

        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Forzamos la opacidad absoluta en la celda
                setOpaque(true);
                
                // Asignamos el color de fondo correspondiente (Cebra oscuro)
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(45, 45, 45)); // Gris carbón
                    } else {
                        c.setBackground(new Color(30, 30, 30)); // Gris oscuro
                    }
                    c.setForeground(Color.WHITE);
                } else {
                    c.setBackground(new Color(211, 84, 0)); // Naranja 
                    c.setForeground(Color.WHITE);
                }

                if (column == 0) {
                    c.setFont(AppFonts.negrita());
                    if (!isSelected) {
                        c.setForeground(new Color(230, 230, 230));
                    }
                } else {
                    c.setFont(AppFonts.normal());
                }

                return c;
            }

            // Inyección del limpiador de graficos directamente en la celda
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        });
    }
    
    //metodo PDFexporter
    public File seleccionarPdfFile() {
        String path = Config.get("videojuego.export.pdf", System.getProperty("user.home"));
        JFileChooser chooser = new JFileChooser(path);
        
        chooser.setSelectedFile(new File("descripción-juego.pdf"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Documentos PDF",  "pdf");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);
        
        int option = chooser.showDialog(this, "Exportar PDF de videojuego");
        
        if (option != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        
        File file = chooser.getSelectedFile();
        Config.set("videojuego.export.pdf", file.getParent());
        if (!file.getName().toLowerCase().endsWith(".pdf")) {
            file = new File(file.getAbsolutePath() + ".pdf");
        }
        
        return file;
    }

    public void setModeloTable(Tablamodelovideojuego modelo) {
        this.tabla.setModel(modelo);
        
        // Ajustamos los anchos de columna según los datos de los videojuegos
        if (tabla.getColumnCount() >= 1) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
        }
        if (tabla.getColumnCount() >= 2) {
            tabla.getColumnModel().getColumn(1).setPreferredWidth(250); // Título
        }
        if (tabla.getColumnCount() >= 3) {
            tabla.getColumnModel().getColumn(2).setPreferredWidth(100); // Género
        }
        if (tabla.getColumnCount() >= 4) {
            tabla.getColumnModel().getColumn(3).setPreferredWidth(80);  // Precio
        }
        
        // centra datos
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);
        
        if (tabla.getColumnCount() >= 1) {
            tabla.getColumnModel().getColumn(0).setCellRenderer(center);
        }
        // columna 3(precios)
        if (tabla.getColumnCount() >= 3) {
            tabla.getColumnModel().getColumn(3).setCellRenderer(center);
        }
        
        this.tabla.revalidate();
        this.tabla.repaint();
    }
    
    // --- GETTERS ---
    public JTable getTable() { return tabla; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnEdit() { return btnEdit; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnExportarVideojuego() { return btnExportarVideojuego; }
    public MainWindow getMainWindow() { return this.mainWindow; }

    public int getSelectedRow() {
        return tabla.getSelectedRow();
    }
}