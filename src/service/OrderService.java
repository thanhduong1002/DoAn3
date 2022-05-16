package service;

import java.util.Date;
import java.util.List;

import dao.OrderDAO;
import dao.PriceDAO;
import entity.Order;
import entity.Price;
import entity.Yard;

public class OrderService {

	private static OrderService instance;
	private OrderService() {
	}
	public static OrderService getInstance() {
		if(instance == null) {
			instance = new OrderService();
		}
		return instance;
	}
	
	public Order insertOrder(Order order) {
		return OrderDAO.getInstance().insertOrder(order);
	}
	public void deleteOrder(int idOrder) {
		OrderDAO.getInstance().deleteOrder(idOrder);
	}
	public List<Order> getOrderByDate_Time(Date date, int idTime){
		return OrderDAO.getInstance().getOrderByDateTime(date, idTime);
	}
	public int getNextID() {
		return OrderDAO.getInstance().NextID();
	}
	public Price getPriceByTime_Yard(int idTime, int idYard) {
		return PriceService.getInstance().getPriceByTime_Yard(idTime, idYard);
	}
	public Order getOrderByTime_Date_Yard(int idTime, int idYard, Date date) {
		List<Order> list = getOrderByDate_Time(date, idTime);
		for(Order item : list) {
			if(item.getIdYard() == idYard) {
				return item;
			}
		}
		return null;
	}
}
