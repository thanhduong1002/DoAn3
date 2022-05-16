package entity;

import java.sql.Date;
import java.sql.Time;

public class Bill {
	private int idBill;
	private Date createDate;
	private Time createTime;
	private int total;
	private int idEmployee;
	private int idOrder;
	public Bill() {
	}
	public Bill(int idBill, Date createDate, Time createTime, int total, int idEmployee, int idOrder) {
		this.idBill = idBill;
		this.createDate = createDate;
		this.createTime = createTime;
		this.total = total;
		this.idEmployee = idEmployee;
		this.idOrder = idOrder;
	}
	public int getIdBill() {
		return idBill;
	}
	public void setIdBill(int idBill) {
		this.idBill = idBill;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Time getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Time createTime) {
		this.createTime = createTime;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	
	
}
