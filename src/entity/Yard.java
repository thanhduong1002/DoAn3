package entity;

public class Yard {
	private int idYard;
	private int idCateyard;
	private String nameYard;
	private int status;
	
	public Yard() {
	}

	public Yard(int idYard, int idCateyard, String nameYard, int status) {
		this.idYard = idYard;
		this.idCateyard = idCateyard;
		this.nameYard = nameYard;
		this.status = status;
	}

	public int getIdYard() {
		return idYard;
	}

	public void setIdYard(int idYard) {
		this.idYard = idYard;
	}

	public int getIdCateyard() {
		return idCateyard;
	}

	public void setIdCateyard(int idCateyard) {
		this.idCateyard = idCateyard;
	}

	public String getNameYard() {
		return nameYard;
	}

	public void setNameYard(String nameYard) {
		this.nameYard = nameYard;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
