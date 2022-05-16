package model;

import javax.swing.JButton;

public class Button_Yard {
	private int idYard;
	private JButton button;
	
	public Button_Yard() {
		
	}
	
	public Button_Yard(int id, JButton button) {
		idYard = id;
		this.button = button; 
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
	
	
	
}
