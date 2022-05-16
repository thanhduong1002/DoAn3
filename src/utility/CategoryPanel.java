package utility;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CategoryPanel {

	private String kind;
	private JLabel jlb;
	private JPanel jpn;
	public CategoryPanel() {
		super();
	}
	public CategoryPanel(String kind, JLabel jlb, JPanel jpn) {
		super();
		this.kind = kind;
		this.jlb = jlb;
		this.jpn = jpn;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public JLabel getJlb() {
		return jlb;
	}
	public void setJlb(JLabel jlb) {
		this.jlb = jlb;
	}
	public JPanel getJpn() {
		return jpn;
	}
	public void setJpn(JPanel jpn) {
		this.jpn = jpn;
	}
	
	
}
