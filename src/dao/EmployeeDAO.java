package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import entity.Employee;

public class EmployeeDAO {
	private static EmployeeDAO instance;
	
	private EmployeeDAO() {
		
	}
	
	public static EmployeeDAO getInstance() {
		if(instance == null) {
			instance = new EmployeeDAO();
		}
		return instance;
	}
	
	public Employee insertEmployee(Employee employee) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("insert into employee values (?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, String.valueOf(employee.getIdCustomer()));
                ps.setString(2, employee.getNameCustomer());
                ps.setString(3, String.valueOf(employee.getOld()));
                ps.setString(4, employee.getAddress());
                ps.setString(5, String.valueOf(employee.getGender()));
                ps.setString(6, employee.getPhoneCustomer());
                ps.setString(7, employee.getIdentityNumber());
                ps.setString(8, String.valueOf(employee.getPassword()));
                ps.setString(9, String.valueOf(employee.getRole()));
                ps.setString(10,"1");
                int row = ps.executeUpdate();
                if (row < 1) {
                    employee = null;
                }
            } catch (SQLException ex) {
                System.out.println("Insert beverage fail!");
                employee = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return employee;
	}
	public Employee updateEmployee(Employee employee) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("update employee "
                		+ "set name = ?,"
                		+ "old = ?,"
                		+ "address = ?,"
                		+ "gender = ?,"
                		+ "phone = ?,"
                		+ "identity_Number = ?,"
                		+ "password = ?,"
                		+ "role = ?"
                		+ "where idEmployee = ? ");
                
                ps.setString(1, employee.getNameCustomer());
                ps.setString(2, String.valueOf(employee.getOld()));
                ps.setString(3, employee.getAddress());
                ps.setString(4, String.valueOf(employee.getGender()));
                ps.setString(5, employee.getPhoneCustomer());
                ps.setString(7, employee.getPassword());
                ps.setString(8, String.valueOf(employee.getRole()));
                ps.setString(9, String.valueOf(employee.getIdCustomer()));
                ps.setString(6, employee.getIdentityNumber());
                int row = ps.executeUpdate();
                if (row < 1) {
                    employee = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update enployee fail!" + ex.toString());
                employee = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return employee;
	}
	public void deleteEmployee(int idEmployee) {
		PreparedStatement ps = null;
		try {
            if (ConnectDatabase.getInstance().open()) {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("update employee set status = 0 where idEmployee = ?");
                ps.setString(1, String.valueOf(idEmployee));
                ps.executeUpdate();
                ConnectDatabase.getInstance().close();
            }
        } catch (SQLException e) {
            System.out.println("Delete employee fail!");
            e.printStackTrace();
        }
	}
	public List<Employee> getAllEmployees(){
		Employee employee = null;
		List<Employee> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from employee where status = 1");
        		rs = ps.executeQuery();
        		list = new ArrayList<Employee>();
        		while(rs.next()) {
        			employee = new Employee(rs.getInt(1),rs.getString(2),rs.getString(6),rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(7),rs.getString(8),rs.getInt(9));
        			list.add(employee);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get beverage fail!");
        		ex.printStackTrace();
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return list;
	}
	public int nextId() {
		int value = -1;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select MAX(idEmployee) from qlsb.employee");
        		rs = ps.executeQuery();
        		if(rs.next()) {
        			value = rs.getInt(1);
        		}else {
        			value = 0;
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get next id employee fail!");
        		ex.printStackTrace();
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return value + 1 ;
	}
}
