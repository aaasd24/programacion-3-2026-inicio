package assets;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestorCursor {
    private static Cursor cursorNormal;
    private static Cursor cursorClick;

    static {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image imgNormal = tk.getImage(GestorCursor.class.getResource("../assets/cursor-tenedor.png"));
        Image imgClick = tk.getImage(GestorCursor.class.getResource("../assets/cursor-tenedor-click.png"));

        cursorNormal = tk.createCustomCursor(imgNormal, new Point(0, 0), "Normal");
        cursorClick = tk.createCustomCursor(imgClick, new Point(0, 0), "Click");
    }

    public static void aplicarATodo(Component c) {
        c.setCursor(cursorNormal);
        
        c.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                c.setCursor(cursorClick);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                c.setCursor(cursorNormal);
            }
        });

        // Si el componente es un contenedor (como un JPanel o el JFrame mismo), 
        // buscamos a sus "hijos" (botones, etc.) y repetimos el proceso (hacemos la recursividad que vimos en estructura de datos pues)//
        if (c instanceof Container) {
            Component[] hijos = ((Container) c).getComponents();
            for (Component hijo : hijos) {
                aplicarATodo(hijo); 
            }
        }
    }
    }