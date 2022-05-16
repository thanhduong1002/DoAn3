package entity;

public class Beverage {
	private int idBeverage;
	private String nameBeverage;
	private String measure;
	private int originalPrice;
	private int price;
	private int mount;
	public Beverage() {

	}
	public Beverage(int idBeverage, String nameBeverage, int mount, int originalPrice, String measure, int price) {

		this.idBeverage = idBeverage;
		this.nameBeverage = nameBeverage;
		this.measure = measure;
		this.originalPrice = originalPrice;
		this.price = price;
		this.mount = mount;
	}
	public int getIdBeverage() {
		return idBeverage;
	}
	public void setIdBeverage(int idBeverage) {
		this.idBeverage = idBeverage;
	}
	public String getNameBeverage() {
		return nameBeverage;
	}
	public void setNameBeverage(String nameBeverage) {
		this.nameBeverage = nameBeverage;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public int getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(int originalPrice) {
		this.originalPrice = originalPrice;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMount() {
		return mount;
	}
	public void setMount(int mount) {
		this.mount = mount;
	}
	@Override
	public String toString() {
		return nameBeverage;
	}
	
	
	
}
