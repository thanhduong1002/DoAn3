package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import entity.Beverage;
import entity.BeverageBill;

public class BeverageDAO {
	
	private static BeverageDAO instance;
	private BeverageDAO() {	
	}
	public static BeverageDAO getInstance() {
		if( instance == null) {
			instance = new BeverageDAO();
		}
		return instance;
	}
	
	public  Beverage insertBeverage(Beverage beverage) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("insert into beverage values (?,?,?,?,?,?,1)");
                ps.setString(1, String.valueOf(beverage.getIdBeverage()));
                ps.setString(2, beverage.getNameBeverage());
                ps.setString(3, String.valueOf(beverage.getMount()));
                ps.setString(4, String.valueOf(beverage.getOriginalPrice()));
                ps.setString(5, beverage.getMeasure());
                ps.setString(6, String.valueOf(beverage.getPrice()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    beverage = null;
                }
            } catch (SQLException ex) {
                System.out.println("Insert beverage fail!");
                ex.printStackTrace();
                beverage = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return beverage;
	}
	public  Beverage updateBeverage(Beverage beverage) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("update beverage "
                		+ "set namebeverage = ?,"
                		+ "mount = ?,"
                		+ "originalPrice = ?,"
                		+ "measure = ?,"
                		+ "price = ?"
                		+ "where idBeverage = ? ");
                
                ps.setString(1, beverage.getNameBeverage());
                ps.setString(2, String.valueOf(beverage.getMount()));
                ps.setString(3, String.valueOf(beverage.getOriginalPrice()));
                ps.setString(4, beverage.getMeasure());
                ps.setString(5, String.valueOf(beverage.getPrice()));
                ps.setString(6, String.valueOf(beverage.getIdBeverage()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    beverage = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update beverage fail!" + ex.toString());
                ex.printStackTrace();
                beverage = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return beverage;
	}
	public  void deleteBeverage(int idBeverage) {
		PreparedStatement ps = null;
		try {
			if(ConnectDatabase.getInstance().open()) {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("update beverage set status = 0 where idBeverage = ?");
				ps.setString(1, String.valueOf(idBeverage));
				ps.executeUpdate();
				ConnectDatabase.getInstance().close();
			}
		}catch(SQLException e) {
			System.out.println("Delete fail!"+ e.toString());
			e.printStackTrace();
		}
	}
	public  List<Beverage> getAllBeverage(){
		Beverage beverage = null;
		List<Beverage> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from beverage where status = 1");
        		rs = ps.executeQuery();
        		list = new ArrayList<Beverage>();
        		while(rs.next()) {
        			beverage = new Beverage(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getInt(6));
        			list.add(beverage);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get beverage fail!");
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return list;
	}
	public  int Price(int id)
	{
		int price = 0;
		for(Beverage k : getAllBeverage())
			if(id == k.getIdBeverage()) return k.getPrice();
		return price;
	}
	public  String Name(int id)
	{
		String k = "";
		for(Beverage m : getAllBeverage())
			if(id == m.getIdBeverage()) return m.getNameBeverage();
		return k;
	}
	public  int nextId() {
		int value = -1;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select MAX(idBeverage) from qlsb.beverage");
        		rs = ps.executeQuery();
        		if(rs.next()) {
        			value = rs.getInt(1);
        		}else {
        			value = 0;
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get next id beverage fail!");
        		ex.printStackTrace();
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return value + 1 ;
	}
}