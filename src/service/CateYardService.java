package service;

import java.util.List;

import dao.CateYardDAO;
import entity.CateYard;

public class CateYardService {

		private static CateYardService instance;
		private CateYardService() {
			
		}
		public static CateYardService getInstance() {
			if(instance == null) {
				instance = new CateYardService();
			}
			return instance;
		}
		
		public List<CateYard> getAll(){
			return CateYardDAO.getInstance().getAllCateYard();
		}
		public CateYard getCateYard_By_ID(int id) {
			for(CateYard item : getAll()) {
				if(item.getIdCateYard() == id) {
					return item;
				}
			}
			return null;
		}
		
}
