package service;

import java.util.List;
import dao.PriceDAO;
import entity.Price;

public class PriceService {
	
	private static PriceService instance;
	private PriceService() {
	}
	public static PriceService getInstance() {
		if(instance == null) {
			instance = new PriceService();
		}
		return instance;
	}
	
	public List<Price> getAllPrice() {
		return PriceDAO.getInstance().getAllPrice();
	}
	public Object[][] showPrice(){
		List<Price> listPrice = getAllPrice();
		Object[][] result = new Object[listPrice.size()][4];
		for(int i = 0 ; i < listPrice.size() ; i++) {
			result[i][0] = listPrice.get(i).getIdCateYard_Time();
			result[i][1] = CateYardService.getInstance().getCateYard_By_ID(listPrice.get(i).getIdCateYard()).toString() ;
			result[i][2] = TimeService.getInstance().getTimeById(listPrice.get(i).getIdTime()).toString();
			result[i][3] = listPrice.get(i).getPrice();
		}
		return result;
	}	
	public Price getPriceByTime_Yard(int idTime, int idYard) {
		if(YardService.getInstance().getYardById(idYard) != null) {
			for(Price item : getAllPrice()) {
				if(item.getIdTime() == idTime && item.getIdCateYard() == YardService.getInstance().getYardById(idYard).getIdCateyard()) {
					return item;
				}
			}
		}
		return null;
	}
	public Price getPriceByID(int id) {
		for(Price item : getAllPrice()) {
			if(item.getIdCateYard_Time() == id) {
				return item;
			}
		}
		return null;
	}
	public int updatePrice(int idPrice, int price) {
		return PriceDAO.getInstance().updatePrice(idPrice,price);
	}
}
