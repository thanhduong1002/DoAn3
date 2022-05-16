package utility;

import javax.swing.JButton;

public class Button_Yard {
	private int idYard;
	private JButton button;
	private int status;
	
	public Button_Yard() {
		
	}
	
	public Button_Yard(int idYard, JButton button, int status) {
		this.idYard = idYard;
		this.button = button; 
		this.status = status;
	}
	
	public int getIdYard() {
		return idYard;
	}
	public void setIdYard(int idYard) {
		this.idYard = idYard;
	}
	public JButton getButton() {
		return button;
	}
	public void setButton(JButton button) {
		this.button = button;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}	
}
