package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import models.Videojuego;
import assets.AppFonts;
import assets.GestorCursor;

@SuppressWarnings("serial")
public class DetalleJuegoView extends JDialog {

    private JLabel lblPortadaGrande;
    private JLabel lblTituloJuego;
    private JTextArea txtDescripcion;
    private JButton btnJugarGrande;
    private Videojuego videojuegoSeleccionado;

    public DetalleJuegoView(JFrame parent, Videojuego videojuego) {
        super(parent, true); // Modal: bloquea el fondo hasta cerrar
        this.videojuegoSeleccionado = videojuego;
        
        setSize(800, 600); 
        setResizable(false);
        setUndecorated(true); 
        setLocationRelativeTo(parent);
        
        JPanel panelPrincipal = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(15, 15, 15, 250)); // RGBA, el ultimo es la opacidad w, 0 siendo transparente y 250 el mas opaco y solido
                g.fillRect(0, 0, getWidth(), getHeight());
                
                g.setColor(new Color(211, 84, 0));
                g.fillRect(0, 0, getWidth(), 4);
            }
        };
        panelPrincipal.setOpaque(false);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        panelPrincipal.add(crearPanelCabecera(), BorderLayout.NORTH);
        
        panelPrincipal.add(crearPanelCuerpoCentral(), BorderLayout.CENTER);
        
        panelPrincipal.add(crearPanelBotonJugar(), BorderLayout.SOUTH);
        
        add(panelPrincipal);
        
        llenarDatosJuego();
        
        GestorCursor.aplicarATodo(this);
    }

    private JPanel crearPanelCabecera() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        lblTituloJuego = new JLabel("[TÍTULO JUEGO]");
        lblTituloJuego.setFont(AppFonts.negrita()); 
        lblTituloJuego.setForeground(Color.WHITE);
        
        // Botón de cerrar estilizado
        JButton btnCerrar = new JButton("X");
        btnCerrar.setFont(AppFonts.small());
        btnCerrar.setForeground(Color.GRAY);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.addActionListener(e -> dispose()); 
        
        panel.add(lblTituloJuego, BorderLayout.CENTER);
        panel.add(btnCerrar, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        return panel;
    }

    private JPanel crearPanelCuerpoCentral() {
        JPanel panelCuerpo = new JPanel(new BorderLayout());
        panelCuerpo.setOpaque(false);
        panelCuerpo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        // --- IZQUERDA: PORTADA DEL JUEGO (300x450 aprox) ---
        lblPortadaGrande = new JLabel();
        lblPortadaGrande.setOpaque(true);
        lblPortadaGrande.setBackground(new Color(30, 30, 30));
        lblPortadaGrande.setHorizontalAlignment(SwingConstants.CENTER);
        lblPortadaGrande.setPreferredSize(new Dimension(300, 450));
        lblPortadaGrande.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60)));
        
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setOpaque(false);
        panelIzquierdo.add(lblPortadaGrande);
        
        // --- DERECHA: DESCRIPCIÓN ---
        JPanel panelDerecho = new JPanel();
        panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
        panelDerecho.setOpaque(false);
        panelDerecho.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        
        JLabel lblTituloDesc = new JLabel("DESCRIPCIÓN DE LA PARRILLA");
        lblTituloDesc.setFont(AppFonts.negrita());
        lblTituloDesc.setForeground(new Color(230, 230, 250)); 
        lblTituloDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        txtDescripcion = new JTextArea();
        txtDescripcion.setOpaque(false);
        txtDescripcion.setEditable(false);
        txtDescripcion.setFocusable(false);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setFont(AppFonts.normal());
        txtDescripcion.setForeground(Color.WHITE); 
        
        JScrollPane scrollDesc = new JScrollPane(txtDescripcion);
        scrollDesc.setOpaque(false);
        scrollDesc.getViewport().setOpaque(false);
        scrollDesc.setBorder(null);
        scrollDesc.getVerticalScrollBar().setUnitIncrement(14); 
        
        panelDerecho.add(lblTituloDesc);
        panelDerecho.add(scrollDesc);
        
        panelCuerpo.add(panelIzquierdo, BorderLayout.WEST);
        panelCuerpo.add(panelDerecho, BorderLayout.CENTER);
        
        return panelCuerpo;
    }

    private JPanel crearPanelBotonJugar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        btnJugarGrande = new JButton("JUGAR AHORA");
        btnJugarGrande.setFont(AppFonts.negrita());
        btnJugarGrande.setForeground(Color.WHITE);
        btnJugarGrande.setBackground(new Color(211, 84, 0)); // Naranja 
        btnJugarGrande.putClientProperty("JButton.buttonType", "roundRect"); 
        btnJugarGrande.setBorderPainted(false);
        btnJugarGrande.setFocusPainted(false);
        btnJugarGrande.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
        
        panel.add(btnJugarGrande, BorderLayout.CENTER);
        
        return panel;
    }

    private void llenarDatosJuego() {
        if (videojuegoSeleccionado != null) {
            lblTituloJuego.setText(videojuegoSeleccionado.getTitulo());
            txtDescripcion.setText(videojuegoSeleccionado.getDescripcion());
            
            // Carga y escalado síncrono de la portada del juego
            String pathImg = videojuegoSeleccionado.getPortadaPath();
            if (pathImg != null && !pathImg.trim().isEmpty()) {
                try {
                    ImageIcon icon = new ImageIcon(pathImg);
                    Image img = icon.getImage().getScaledInstance(300, 450, Image.SCALE_SMOOTH);
                    lblPortadaGrande.setIcon(new ImageIcon(img));
                
                } catch (Exception e) {
                    lblPortadaGrande.setText("Error Arte");
                    lblPortadaGrande.setForeground(Color.RED);
                }
            } else {
                lblPortadaGrande.setText("Sin Arte");
                lblPortadaGrande.setForeground(Color.GRAY);
            }
        }
    }
    //public 
}