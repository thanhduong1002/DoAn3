package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import entity.CateYard;
import entity.Time;

public class CateYardDAO {
	
	private static CateYardDAO instance;
	
	private CateYardDAO() {
	}
	public static CateYardDAO getInstance(){
		if(instance == null) {
			instance = new CateYardDAO();
		}
		return instance;
	}
	
	public CateYard updateCateYard(CateYard cateYard) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("update category_yard "
                 		+ "set nameCategory_Yard = ? "
                		+ "where idCategory_Yard = ? ");
            	ps.setString(1, String.valueOf(cateYard.getNameCateYard()));
            	ps.setString(2, String.valueOf(cateYard.getIdCateYard()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    cateYard = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update CateYard fail!");
                cateYard = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return cateYard;	
	}
	public List<CateYard> getAllCateYard(){
		CateYard cateYard = null;
		List<CateYard> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from category_yard");
        		rs = ps.executeQuery();
        		list = new ArrayList<CateYard>();
        		while(rs.next()) {
        			cateYard = new CateYard(rs.getInt(1),rs.getString(2));
        			list.add(cateYard);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get CateYard fail!");
        		ex.printStackTrace();
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return list;
	}
}