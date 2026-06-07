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
import assets.Colores;
import config.Config;
import tablamodelos.Tablamodelovideojuego;

@SuppressWarnings("serial")
public class VideojuegoView extends JPanel {

    private JButton btnEdit;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnExportarVideojuego;
    private JTable tabla;
    
    // Panel superior para los botones
    private JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
    
    public VideojuegoView() {
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
       
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
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
        tabla.setGridColor(new Color(80, 80, 80)); 
        
        // Colores base de la tabla (Modo oscuro)
        tabla.setBackground(new Color(40, 40, 40, 150));
        tabla.setForeground(Color.WHITE); 
        tabla.setFont(AppFonts.normal());
        
        // Color al seleccionar una fila 
        tabla.setSelectionBackground(new Color(211, 84, 0)); // Naranja intenso
        tabla.setSelectionForeground(Color.WHITE);
        
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Estilo de la Cabecera
        JTableHeader header = tabla.getTableHeader();
        header.setBackground(new Color(25, 25, 25)); // Gris casi negro
        header.setForeground(Color.WHITE);
        header.setFont(AppFonts.negrita());
        header.setPreferredSize(new Dimension(0, 45)); // Cabecera alta
        header.setReorderingAllowed(false);
        
        // Renderizador personalizado para las celdas
        tabla.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Efecto cebra para las filas (Tonos oscuros)
                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(new Color(45, 45, 45, 200)); // Par
                    } else {
                        c.setBackground(new Color(35, 35, 35, 200)); // Impar
                    }
                    c.setForeground(Color.WHITE);
                }
                
                // Resaltar la columna 1(Título del juego)
                if (column == 1) {
                    c.setFont(AppFonts.negrita());
                    // Si no está seleccionada, le damos un tono ligeramente gris claro o blanco humo
                    if (!isSelected) {
                        c.setForeground(new Color(220, 220, 220)); 
                    }
                } else {
                    c.setFont(AppFonts.normal());
                }
                
                return c;
            }
        });
    }
    
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
    
    public int getSelectedRow() {
        return tabla.getSelectedRow();
    }
}