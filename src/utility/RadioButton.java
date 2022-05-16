package utility;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class RadioButton {
	private JRadioButton jrb1;
	private JRadioButton jrb2;
	
	public RadioButton(JRadioButton jrb1, JRadioButton jrb2) {
		this.jrb1 = jrb1;
		this.jrb2 = jrb2;
		
		ButtonGroup group = new ButtonGroup();
		group.add(jrb1);
	    group.add(jrb2);
	}
	

}
