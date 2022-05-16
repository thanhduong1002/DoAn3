package model;

public class CateYard {
	private int idCateYard;
	private String nameCateYard;
	public CateYard() {

	}
	public CateYard(int idCateYard, String nameCateYard) {

		this.idCateYard = idCateYard;
		this.nameCateYard = nameCateYard;
	}
	public int getIdCateYard() {
		return idCateYard;
	}
	public void setIdCateYard(int idCateYard) {
		this.idCateYard = idCateYard;
	}
	public String getNameCateYard() {
		return nameCateYard;
	}
	public void setNameCateYard(String nameCateYard) {
		this.nameCateYard = nameCateYard;
	}
	@Override
	public String toString() {
		return nameCateYard;
	}
	
	
}
