package model;

public class BeverageBill {
	private int idBeveBill;
	private int idBeve;
	private int mountBeve;
	public BeverageBill() {

	}
	public BeverageBill(int idBeveBill, int idBeve, int mountBeve) {

		this.idBeveBill = idBeveBill;
		this.idBeve = idBeve;
		this.mountBeve = mountBeve;
	}
	public int getIdBeveBill() {
		return idBeveBill;
	}
	public void setIdBeveBill(int idBeveBill) {
		this.idBeveBill = idBeveBill;
	}
	public int getIdBeve() {
		return idBeve;
	}
	public void setIdBeve(int idBeve) {
		this.idBeve = idBeve;
	}
	public int getMountBeve() {
		return mountBeve;
	}
	public void setMountBeve(int mountBeve) {
		this.mountBeve = mountBeve;
	}
	
	
}
