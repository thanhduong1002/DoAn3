package entity;

import java.util.Date;

import service.CateYardService;
import service.CustomerService;
import service.EmployeeService;
import service.PriceService;
import service.TimeService;
import service.YardService;

public class Order {
	
	private int idOrder;
	private int idCateYard_Time;
	private int idYard;
	private Date date;
	private int idCustomer;
	
	public Order() {
	}
	
	public Order(int idOrder, int idCateYard_Time, int idYard, Date date, int idCustomer) {
		this.idOrder = idOrder;
		this.idCateYard_Time = idCateYard_Time;
		this.idYard = idYard;
		this.date = date;
		this.idCustomer = idCustomer;
	}
	
	public int getIdOrder() {
		return idOrder;
	}
	
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public int getIdCateYard_Time() {
		return idCateYard_Time;
	}
	
	public void setIdCateYard_Time(int idCateYard_Time) {
		this.idCateYard_Time = idCateYard_Time;
	}
	public int getIdYard() {
		return idYard;
	}
	
	public void setIdYard(int idYard) {
		this.idYard = idYard;
	}
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getIdCustomer() {
		return idCustomer;
	}
	
	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	@Override
	public String toString() {
		return CustomerService.getInstance().checkID(idCustomer).getNameCustomer() + YardService.getInstance().getYardById(idYard).getNameYard()+ TimeService.getInstance().getTimeById(PriceService.getInstance().getPriceByID(idCateYard_Time).getIdTime()).getTime();
	}
}
