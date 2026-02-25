package assets;

import java.awt.Font;

public class AppFonts {

	private static Font base;
	
	static {
		try {
			base = Font.createFont(
					Font.TRUETYPE_FONT, 
					AppFonts.class.getResourceAsStream("../assets/inkfree.ttf"));
			
		}catch(Exception e) {
			base = new Font("Times New Roman", Font.PLAIN, 14);
		}
	}
	
	public static Font normal() {
		return base.deriveFont(20f);
	}
	
	public static Font small() {
		return base.deriveFont(14f);
	}
	
	public static Font title() {
		return base.deriveFont(Font.BOLD, 35f);
	}
	
}
