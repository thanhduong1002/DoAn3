package dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import connect.ConnectDatabase;
import entity.Order; 

public class OrderDAO {
	
	private static OrderDAO instance; 
	private OrderDAO() {
	}
	public static OrderDAO getInstance() {
		if(instance == null) {
			instance = new OrderDAO();
		}
		return instance;
	}
	
	public Order insertOrder(Order order) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("insert into ordered values (?,?,?,?,?)");
                ps.setString(1, String.valueOf(order.getIdOrder()));
                ps.setString(2, String.valueOf(order.getIdCateYard_Time()));
                ps.setString(3, String.valueOf(order.getIdYard()));
                ps.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(order.getDate()));
                ps.setString(5, String.valueOf(order.getIdCustomer()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    order = null;
                }
            } catch (SQLException ex) {
            	ex.printStackTrace();
                System.out.println("Insert order fail!");
                order = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
		return order;
	}
	
	public void deleteOrder(int idOrder) {
		PreparedStatement ps = null;
		try {
			if(ConnectDatabase.getInstance().open()) {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("delete from ordered where idOrdered = ?");
				ps.setString(1, String.valueOf(idOrder));
				ps.executeUpdate();
				ConnectDatabase.getInstance().close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Delete fail!");
		}
	}
	
	public List<Order> getOrderByDateTime(Date date, int idTime){
		Order order = null;
		List<Order> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
		if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from ordered inner join qlsb.price on ordered.idTime_CateYard = price.idTime_CateYard inner join qlsb.time on price.idTime = time.idTime where ordered.date = ? and time.idTime = ?");
        		ps.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(date));
        		ps.setString(2,String.valueOf(idTime));
        		rs = ps.executeQuery();
        		list = new ArrayList<Order>();
        		while(rs.next()) {
        			order = new Order(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getInt(5));
        			list.add(order);
        		}
        	}catch (SQLException ex) {
        		ex.printStackTrace();
        		System.out.println("Get order fail!");
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return list;
	}
	public int NextID() {
		int value = -1;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select MAX(idOrdered) from qlsb.ordered");
        		rs = ps.executeQuery();
        		if(rs.next()) {
        			value = rs.getInt(1);
        		}else {
        			value = 0;
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get next id order fail!");
        		ex.printStackTrace();
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return value + 1 ;
	}
	public List<Order> getAllOrder(){
		Order Order = null;
		List<Order> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from ordered");
        		rs = ps.executeQuery();
        		list = new ArrayList<Order>();
        		while(rs.next()) {
        			Order = new Order(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getDate(4), rs.getInt(5));
        			list.add(Order);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get Order fail!" + ex);
        		ex.printStackTrace();
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return list;
	}
}