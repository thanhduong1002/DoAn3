package model;

public class Customer {

	private int idCustomer;
	private String nameCustomer;
	private String phoneCustomer;
	
	public Customer() {
	}
	
	public Customer(int idCustomer, String nameCustomer, String phoneCustomer) {
		this.idCustomer = idCustomer;
		this.nameCustomer = nameCustomer;
		this.phoneCustomer = phoneCustomer;
	}
	@Override
	public String toString() {
		return nameCustomer +" "+ phoneCustomer;
	}
	public int getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
	public String getNameCustomer() {
		return nameCustomer;
	}
	public void setNameCustomer(String nameCustomer) {
		this.nameCustomer = nameCustomer;
	}
	public String getPhoneCustomer() {
		return phoneCustomer;
	}
	public void setPhoneCustomer(String phoneCustomer) {
		this.phoneCustomer = phoneCustomer;
	}
	
	
}
