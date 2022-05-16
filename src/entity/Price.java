package entity;

public class Price {
	private int idCateYard_Time;
	private int idCateYard;
	private int idTime;
	private int price;
	public Price() {
	}
	
	public Price(int idCateYard_Time, int idCateYard, int idTime, int price) {
		this.idCateYard_Time = idCateYard_Time;
		this.idCateYard = idCateYard;
		this.idTime = idTime;
		this.price = price;
	}

	public int getIdCateYard_Time() {
		return idCateYard_Time;
	}
	public void setIdCateYard_Time(int idCateYard_Time) {
		this.idCateYard_Time = idCateYard_Time;
	}
	public int getIdCateYard() {
		return idCateYard;
	}
	public void setIdCateYard(int idCateYard) {
		this.idCateYard = idCateYard;
	}
	public int getIdTime() {
		return idTime;
	}
	public void setIdTime(int idTime) {
		this.idTime = idTime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Price [idCateYard_Time=" + idCateYard_Time + ", idCateYard=" + idCateYard + ", idTime=" + idTime
				+ ", price=" + price + "]";
	}
	
	
	
}
