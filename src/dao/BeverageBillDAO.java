package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import entity.BeverageBill;
import entity.Bill;

public class BeverageBillDAO {
	
	private static BeverageBillDAO instance;
	private BeverageBillDAO() {	
	}
	public static BeverageBillDAO getInstance() {
		if( instance == null) {
			instance = new BeverageBillDAO();
		}
		return instance;
	}
	
	public  BeverageBill insertBeverageBill(BeverageBill beverageBill) {
		if(Check(beverageBill))
		{
			PreparedStatement ps = null;
			if (ConnectDatabase.getInstance().open()) {
	            try {
	            	ps = ConnectDatabase.getInstance().getCnn().prepareStatement("insert into beveragebill values (?,?,?)");
	                ps.setString(1, String.valueOf(beverageBill.getIdBeveBill()));
	                ps.setString(2, String.valueOf(beverageBill.getIdBeve()));
	                ps.setString(3, String.valueOf(beverageBill.getMountBeve()));
	                int row = ps.executeUpdate();
	                if (row < 1) beverageBill = null;
	            } catch (SQLException ex) {
	                System.out.println("Insert beveragebill fail!" + ex);
	                beverageBill = null;
	            } finally {
	            	ConnectDatabase.getInstance().close(ps);
	            }
	        }
		} else updateBeverageBill(beverageBill);
		return beverageBill;
	}
	
	public  void deleteBeverageBill(int idBeveBill, int idBeve) {
		PreparedStatement ps = null;
		try {
			if(ConnectDatabase.getInstance().open()) {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("delete from beveragebill "
						+ "where id_beverage_Bill = ? and idBeverage = ?");
				ps.setString(1, String.valueOf(idBeveBill));
				ps.setString(2, String.valueOf(idBeve));
				ps.executeUpdate();
				ConnectDatabase.getInstance().close(ps);
			}
		}catch(SQLException e) {
			System.out.println("Delete fail!"+ e.toString());
		}
	}
	
	public  BeverageBill updateBeverageBill(BeverageBill beverageBill) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("update beveragebill "
                		+ "set mount = ?"
                		+ "where id_beverage_Bill = ? and idBeverage = ?");
                ps.setString(1, String.valueOf(beverageBill.getMountBeve()));
                ps.setString(2, String.valueOf(beverageBill.getIdBeveBill()));
                ps.setString(3, String.valueOf(beverageBill.getIdBeve()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    beverageBill = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update beverageBill fail!" + ex.toString());
                beverageBill = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return beverageBill;
	}
	public  List<BeverageBill> getAllBeveBill(){
		BeverageBill BeverageBill = null;
		List<BeverageBill> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from beveragebill");
        		rs = ps.executeQuery();
        		list = new ArrayList<>();
        		while(rs.next()) {
        			BeverageBill = new BeverageBill(rs.getInt(1),rs.getInt(2),rs.getInt(3));
        			list.add(BeverageBill);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get Bill fail!");
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return list;
	}

	public  boolean Check(BeverageBill b)
	{
		for(BeverageBill i : getAllBeveBill())
			if(b.getIdBeve() == i.getIdBeve() && b.getIdBeveBill() == i.getIdBeveBill())
			{
				b.setMountBeve(b.getMountBeve() + i.getMountBeve());
				return false;
			}
		return true;
	}
}