package service;

import java.util.*;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import dao.BeverageDAO;
import entity.Beverage;
import dao.BeverageDAO;

public class BeverageService {
	private List<Beverage> listBeverage = null;
	
	private static BeverageService instance;
	private BeverageService() {
		
	}
	public static BeverageService getInstance() {
		if(instance == null) {
			instance = new BeverageService();
		}
		return instance;
	}
	public List<Beverage> getAllBeverage() {
		return BeverageDAO.getInstance().getAllBeverage();
	}
	
	public static boolean Compare(String s1, String s2)
	{
		String Cd = "", Cn = "";
		if(s1 == ""  || s2 == "") return true;
		if(s1.length() < s2.length()) {
			Cd = s2; Cn = s1;
		}
		else {
			Cd = s1; Cn = s2;
		}
		for(int i = 0; i < Cd.length(); ++i)
		{
			boolean kt = true;
			if(Cd.charAt(i) == Cn.charAt(0))
			{
				for(int j = 0; j < Cn.length(); ++j)
				{
					if(i + j >= Cd.length()) return false;
					if(Cd.charAt(j + i) != Cn.charAt(j)) 
					{
						kt = false;
						break;
					}
				}
				if(kt) return true;
			}
		}
		return false;
	}
	
	public Beverage UpdateOrAdd(Beverage Beverage)
	{
		if(Check(Beverage.getIdBeverage())) //nếu có id
			return BeverageDAO.getInstance().updateBeverage(Beverage);
		else 
			return BeverageDAO.getInstance().insertBeverage(Beverage);
	}
	
	public Object[][]  Sort(String name, int m)
	{
		List<Beverage> l = BeverageDAO.getInstance().getAllBeverage();
		Collections.sort(l,  new Comparator<Beverage>() {
            @Override
            public int compare(Beverage o1, Beverage o2) {
            	switch(m)
            	{
            		case 1:
            			return o1.getMount() - o2.getMount();
            		case 2:
            			return o1.getOriginalPrice() - o2.getOriginalPrice();
            		case 3:
            			return o1.getPrice() - o2.getPrice();
            		default:
            			return o1.getIdBeverage() - o2.getIdBeverage();
            	}
            }
        });
		return showBeverages(name, l); 
	}
	
	public Object[][] showBeverages(String name, List<Beverage> l){
		Object[][] result = new Object[l.size()][6];
		int dem = 0;
		for(int i = 0 ; i < l.size() ; i++) {
			if(Compare(l.get(i).getNameBeverage().toString(), name))
			{
				result[dem][0] = l.get(i).getIdBeverage();
				result[dem][1] = l.get(i).getNameBeverage().toString();
				result[dem][2] = l.get(i).getMeasure().toString();
				result[dem][3] = l.get(i).getOriginalPrice();
				result[dem][4] = l.get(i).getPrice();
				result[dem][5] = l.get(i).getMount();
				dem++;
			}
		}
		return result;
	}
	
	public Object[][] showBeverages(String name){
		listBeverage = BeverageDAO.getInstance().getAllBeverage();
		return showBeverages(name, listBeverage);
	}
	public void deleteBeverage(int id) {
		BeverageDAO.getInstance().deleteBeverage(id);
	}
	public int getNextIdBeverage() {
		return BeverageDAO.getInstance().nextId();
	}
	public Beverage checkID(int Id) {
		listBeverage = BeverageDAO.getInstance().getAllBeverage();
		for(Beverage item : listBeverage) {	
			if(item.getIdBeverage() == Id) 	return item;
		}
		return null;
	}
	public boolean Check(int ID)
	{
		listBeverage = BeverageDAO.getInstance().getAllBeverage();
		for(Beverage item : listBeverage)
			if(item.getIdBeverage() == ID) return true;
		return false;
	}
}