package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BotonsView extends JPanel{

	public BotonsView() {
		
		//Declaro que la ventana se trabaje de manera que los paneles esten en los bordes. Este se considera como panel general 
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JButton boton = new JButton("Button 1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 100;
		c.gridy = 0;
		this.add(boton, c);
		
		//Declaro un panel para botones, lo pongo de coor de magetna y lo agrego al panel general 
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBackground(Color.MAGENTA);
		
		//Creo botones y los agrego al panel superior
		JButton botonInicio = new JButton("Iniciar sesión");
		JButton botonReinicio = new JButton("Cargar página");
		
		//panelSuperior.add(botonInicio);
		//panelSuperior.add(botonReinicio);
		
		//Agrego panel superior al panel general
		//add(panelSuperior, BorderLayout.NORTH);
		
	}
	
}
