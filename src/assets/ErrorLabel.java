package assets;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import assets.AppFonts;

public class ErrorLabel extends JLabel{

	public ErrorLabel() {
		setLabelStyle();
	}
	
	public ErrorLabel(String text) {
		super(text);
		setLabelStyle();
	}
	
	private void setLabelStyle() {
		setFont(AppFonts.small());
		setForeground(Color.RED);
		setHorizontalAlignment(SwingConstants.LEFT);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, getPreferredSize().height));
	}
	
	
}