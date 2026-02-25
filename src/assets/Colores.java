package assets;

import java.awt.Color;

public class Colores {
	
		
		static Color maronLeve = new Color(158, 118 , 79);
		static Color blancoIvory = new Color(244, 249 , 233);
		static Color grisGunmetal = new Color(53, 57 , 60);
		static Color rosaNeon = new Color(244, 0 , 118);
		static Color azulGlaous = new Color(119, 133, 172);

	
		public static Color colorear(int color) {
			switch(color) {
			
			case 1: 
				return maronLeve;
			case 2:
				return grisGunmetal;
			case 3:
				return rosaNeon;
			case 4:
				return azulGlaous;
			default :
			return blancoIvory;
			}
		}
}
