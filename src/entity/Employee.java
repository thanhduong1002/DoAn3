package entity;

public class Employee extends Customer {
	
	private int old;
	private String address;
	private int gender;
	private String identityNumber;
	private String password;
	private int role;
	
	public Employee() {
		super();
	}
	
	public Employee(int id, String name, String phone,int old, String address, int gender, String identityNumber, String password, int role) {
		super(id,name,phone);
		this.old = old;
		this.address = address;
		this.gender = gender;
		this.identityNumber = identityNumber;
		this.password = password;
		this.role = role;
	}

	public int getOld() {
		return old;
	}

	public void setOld(int old) {
		this.old = old;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	
	
}
