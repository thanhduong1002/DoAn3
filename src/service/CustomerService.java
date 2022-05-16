package service;

import java.util.List;

import dao.CustomerDAO;
import entity.Customer;

public class CustomerService {

	private List<Customer> listCustomer = null;
	
	private static CustomerService instance;
	private CustomerService() {
	}
	public static CustomerService getInstance() {
		if(instance == null) {
			instance = new CustomerService();
		}
		return instance;
	}
	public List<Customer> getAllCustomer(){
		return CustomerDAO.getInstance().getAllCustomers();
	}
	public Customer updateCustomer(Customer customer) {
		return CustomerDAO.getInstance().updateCustomer(customer);
	}
	public Customer insertCustomer(Customer customer) {
		return CustomerDAO.getInstance().insertCustomer(customer);
	}
	public Object[][] showCustomers(){
		listCustomer = CustomerDAO.getInstance().getAllCustomers();
		Object[][] result = new Object[listCustomer.size()][3];
		for(int i = 0 ; i < listCustomer.size() ; i++) {
			result[i][0] = listCustomer.get(i).getIdCustomer();
			result[i][1] = listCustomer.get(i).getNameCustomer().toString();
			result[i][2] = listCustomer.get(i).getPhoneCustomer().toString();
		}
		return result;
	}	
	public void deleteCustomer(int idCustomer) {
		CustomerDAO.getInstance().deleteCustomer(idCustomer);
	}
	public int getNextIdCustomer() {
		return CustomerDAO.getInstance().nextId();
	}
	public Customer checkID(int Id) {
		listCustomer = getAllCustomer();
		for(Customer item : listCustomer) {
			if(item.getIdCustomer() == Id) return item;
		}
		return null;
	}
	public Customer searchPhone(String phone) {
		return CustomerDAO.getInstance().searchPhone(phone);
	}
}