package service;

import java.util.List;

import dao.EmployeeDAO;
import entity.Employee;

public class EmployeeService {
	
	private static Employee storeUser = null;
	private List<Employee> listEmployee = null;
	
	private static EmployeeService instance;
	private EmployeeService() {
	}
	public static EmployeeService getInstance() {
		if(instance == null) {
			instance = new EmployeeService();
		}
		return instance;
	}
	public List<Employee> getAllEmployee() {
		return EmployeeDAO.getInstance().getAllEmployees();
	}
	public Employee updateEmployee(Employee employee) {
		return EmployeeDAO.getInstance().updateEmployee(employee);
	}
	public Employee insertEmployee(Employee employee) {
		return EmployeeDAO.getInstance().insertEmployee(employee);
	}
	public Object[][] showEmployees(){
		listEmployee = EmployeeDAO.getInstance().getAllEmployees();
		Object[][] result = new Object[listEmployee.size()][9];
		for(int i = 0 ; i < listEmployee.size() ; i++) {
			result[i][0] = listEmployee.get(i).getIdCustomer();
			result[i][1] = listEmployee.get(i).getNameCustomer().toString();
			result[i][2] = listEmployee.get(i).getOld();
			if(listEmployee.get(i).getGender() == 1) {
				result[i][3] = "Nam";
			}else {
				result[i][3] = "Nữ";
			}
			result[i][4] = listEmployee.get(i).getAddress().toString();
			result[i][5] = listEmployee.get(i).getPhoneCustomer().toString();
			result[i][6] = listEmployee.get(i).getIdentityNumber().toString();
			result[i][7] = listEmployee.get(i).getPassword().toString();
			if(listEmployee.get(i).getRole() == 1) {
				result[i][8] = "Admin";
			}else {
				result[i][8] = "Nhân viên";
			}
		}
		return result;
	}
	public void deleteEmployee(int idEmmployee) {
		EmployeeDAO.getInstance().deleteEmployee(idEmmployee);
	}
	public int getNextIdEmployee() {
		return EmployeeDAO.getInstance().nextId();
	}
	public Employee checkIDEmployee(int IdEmployee) {
		listEmployee = EmployeeDAO.getInstance().getAllEmployees();
		for(Employee item : listEmployee) {
			if(item.getIdCustomer() == IdEmployee) return item;
		}
		return null;
	}
	public Employee checkEmployee(String password, int id) {
		List<Employee> list = getAllEmployee();
		for(Employee item : list) {
			if(item.getPassword().equals(password) && item.getIdCustomer() == id) {
				return item;
			}
		}
		return null;
	}
	public static Employee getStoreUser() {
		return storeUser;
	}
	public static void setStoreUser(Employee storeUser) {
		EmployeeService.storeUser = storeUser;
	}
	
}
