package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import entity.Beverage;
import entity.Customer;
import entity.Employee;

public class CustomerDAO {

	private static CustomerDAO instance;
	
	private CustomerDAO() {
		
	}
	
	public static CustomerDAO getInstance() {
		if( instance == null) {
			instance = new CustomerDAO();
		}
		return instance;
	}
	
	public Customer insertCustomer(Customer customer) {
		PreparedStatement ps = null;//Chỉ định tham số đầu vào khi chạy
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("insert into customer values (?,?,?)");
                ps.setString(1, String.valueOf(customer.getIdCustomer()));
                ps.setString(2, customer.getNameCustomer());
                ps.setString(3, customer.getPhoneCustomer());
                int row = ps.executeUpdate();//row = số hàng được thực thi
                if (row < 1) {
                    customer = null;
                }
            } catch (SQLException ex) {
                System.out.println("Insert customer fail!");
                customer = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return customer;
	}
	public Customer updateCustomer(Customer customer) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("update customer "
                		+ "set namecustomer = ?,"
                		+ "phone = ?"
                		+ "where idCustomer = ? ");

                ps.setString(1, customer.getNameCustomer());
                ps.setString(2, customer.getPhoneCustomer());
                ps.setString(3, String.valueOf(customer.getIdCustomer()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    customer = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update customer fail!" + ex.toString());
                customer = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return customer;
	}

	public void deleteCustomer(int idCustomer) {
		PreparedStatement ps = null;
		try {
			if(ConnectDatabase.getInstance().open()) {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("delete from customer where idCustomer = ?");
				ps.setString(1, String.valueOf(idCustomer));
				ps.executeUpdate();
				ConnectDatabase.getInstance().close();
			}
		}catch(SQLException e) {
			System.out.println("Delete fail!"+ e.toString());
		}
	}
	public int nextId() {
		int value = -1;
		PreparedStatement ps = null;//Chỉ định tham số đầu vào khi chạy
		ResultSet rs = null;//Duy trì 1 con trỏ trỏ đến 1 hàng của 1 bảng
		if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select MAX(idCustomer) from qlsb.customer");
        		rs = ps.executeQuery();
        		if(rs.next()) {
        			value = rs.getInt(1);
        		}else {
        			value = 0;
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get next id customer fail!");
        		ex.printStackTrace();
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return value + 1 ;
	}
	public Customer searchPhone(String phone){
		Customer customer = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from customer where phone = ? ");
        		ps.setString(1, phone);
        		rs = ps.executeQuery();
        		while(rs.next()) {
        			customer = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3));
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get customer by phone fail!");
        		ex.printStackTrace();
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return customer;
	}
	public List<Customer> getAllCustomers(){
		Customer customer = null;
		List<Customer> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from customer");
        		rs = ps.executeQuery();
        		list = new ArrayList<Customer>();
        		while(rs.next()) {
        			customer = new Customer(rs.getInt(1),rs.getString(2),rs.getString(3));
        			list.add(customer);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get customer fail!");
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return list;
	}
}