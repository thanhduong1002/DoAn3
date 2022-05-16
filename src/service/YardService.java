package service;

import java.util.List;

import dao.YardDAO;
import entity.Yard;

public class YardService {
	
	private static YardService instance;
	private YardService() {
	}
	public static YardService getInstance() {
		if(instance == null) {
			instance = new YardService();
		}
		return instance;
	}
	
	public List<Yard> getAllYard(){
		return YardDAO.getInstance().getAllYard();
	}
	public Yard getYardById(int id) {
		List<Yard> listYard = getAllYard();
		for(int i = 0 ; i < listYard.size() ; i++) {
			if(listYard.get(i).getIdYard() == id) {
				return listYard.get(i);
			}
		}
		return null;
	}
	public void setStatus(int status, int idYard) {
		YardDAO.getInstance().setSatus(status, idYard);
	}
}
