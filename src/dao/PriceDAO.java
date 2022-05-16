package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import entity.Price;

public class PriceDAO {
	
	private static PriceDAO instance;
	private PriceDAO() {
	}
	public static PriceDAO getInstance() {
		if( instance == null) {
			instance = new PriceDAO();
		}
		return instance;
	}
	
	public  int updatePrice(int idPrice, int price) {
		PreparedStatement ps = null;
		int check = 1;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("update price set "
                 		+ "price = ? "
                		+ "where idTime_CateYard = ? ");

            	ps.setString(1, String.valueOf(price));
            	ps.setString(2, String.valueOf(idPrice));
                int row = ps.executeUpdate();
                if (row < 1) {
                    check = 0;
                }
            } catch (SQLException ex) {
                System.out.println("Update Price fail!");
                ex.printStackTrace();
                check = 0;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return check;	
	}
	public  List<Price> getAllPrice(){
		Price price = null;
		List<Price> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from price");
        		rs = ps.executeQuery();
        		list = new ArrayList<Price>();
        		while(rs.next()) {
        			price = new Price(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
        			list.add(price);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get price fail!");
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return list;
	}
}