package model;

public class Time {
	private int idTime;
	private String time;
	
	
	public Time() {
	}
	
	public Time(int idTime, String time) {
		this.idTime = idTime;
		this.time = time;
	}

	public int getIdTime() {
		return idTime;
	}
	public void setIdTime(int idTime) {
		this.idTime = idTime;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return time;
	}
	
	
}
