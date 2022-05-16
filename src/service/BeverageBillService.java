package service;

import java.util.*;

import dao.BeverageBillDAO;
import dao.BeverageDAO;
import entity.Beverage;
import entity.BeverageBill;

public class BeverageBillService {
	private ArrayList<BeverageBill> listBeverageBill = null;
	
	private static BeverageBillService instance;
	private BeverageBillService() {
	}
	public static BeverageBillService getInstance() {
		if(instance == null) {
			instance = new BeverageBillService();
		}
		return instance;
	}
	
	public void delet(int idBeveBill, int idBeve){
		BeverageBillDAO.getInstance().deleteBeverageBill(idBeveBill, idBeve);
	}
	public void deleteBillBeveOfBill(List<BeverageBill> l)
	{
		for(BeverageBill i : l)
			BeverageBillDAO.getInstance().deleteBeverageBill(i.getIdBeveBill(), i.getIdBeve());
	}
	public void deleteList(int idBeveBill, int idBeve, List<BeverageBill> b) {
		for (int i = 0; i < b.size(); ++i)
			if (b.get(i).getIdBeveBill() == idBeveBill && b.get(i).getIdBeve() == idBeve)
				b.remove(i);
	}
	public List<BeverageBill> getAllBeverageBill(int ID) {
		List<BeverageBill> l = new ArrayList<>();
		for(BeverageBill i : BeverageBillDAO.getInstance().getAllBeveBill())
			if(ID == i.getIdBeveBill())
				l.add(i);
		return l;
	}
	
	public BeverageBill Add(BeverageBill BeverageBill){
			return BeverageBillDAO.getInstance().insertBeverageBill(BeverageBill);
	}
	
	public void AddList(BeverageBill b, List<BeverageBill> l){
		boolean k = true;
		for(BeverageBill i : l)
			if(i.getIdBeveBill() == b.getIdBeveBill() && i.getIdBeve() == b.getIdBeve())
			{
				i.setMountBeve(i.getMountBeve() + b.getMountBeve());
				k = false;
			}
		if(k) l.add(b);
	}
	public  int demID(List<BeverageBill> l, int ID) {
		int dem = 0;
		for(int i = 0 ; i < l.size() ; i++) {
			if(ID == l.get(i).getIdBeveBill())
				dem++;
		}
		return dem;
	}
	public Object[][] showBeverageBills(int ID, List<BeverageBill> l){
		Object[][] result = new Object[demID(l, ID)][10];
		int dem = 0;
		for(int i = 0 ; i < l.size() ; i++) {
			if(ID == l.get(i).getIdBeveBill())
			{
				result[dem][0] = dem + 1;
				result[dem][1] = BeverageDAO.getInstance().Name(l.get(i).getIdBeve());
				result[dem][2] = String.valueOf(l.get(i).getMountBeve());
				result[dem][3] = String.valueOf(BeverageDAO.getInstance().Price(l.get(i).getIdBeve()));
				result[dem][4] = String.valueOf(BeverageDAO.getInstance().Price(l.get(i).getIdBeve())
						*l.get(i).getMountBeve());
				dem++;
			}
		}
		return result;
	}
	
	public int TinhTien(List<BeverageBill> l){
		int tien = 0;
		for(int i = 0 ; i < l.size() ; i++) {
			tien += BeverageDAO.getInstance().Price(l.get(i).getIdBeve())
					*l.get(i).getMountBeve();
		}
		return tien;
	}
	
	public int ReID(String name)
	{
		for(Beverage i : BeverageDAO.getInstance().getAllBeverage())
			if(i.getNameBeverage().contains(name))
				return i.getIdBeverage();
		return 0;
	}
	
	public Object[][] showBeverageBills(int ID){
		listBeverageBill = (ArrayList<BeverageBill>) BeverageBillDAO.getInstance().getAllBeveBill();
		return showBeverageBills(ID, listBeverageBill);
	}
	public BeverageBill checkID(int IdBeveBill, int IDBeve) {
		listBeverageBill = (ArrayList<BeverageBill>) BeverageBillDAO.getInstance().getAllBeveBill();
		for(BeverageBill item : listBeverageBill) {	
			if(item.getIdBeveBill() == IdBeveBill && item.getIdBeve() == IDBeve) 
				return item;
		}
		return null;
	}
}