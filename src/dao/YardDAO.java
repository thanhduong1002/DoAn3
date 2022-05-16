package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import entity.Beverage;
import entity.Yard;

public class YardDAO {
	private static YardDAO instance;
	private YardDAO() {	
	}
	public static YardDAO getInstance() {
		if( instance == null) {
			instance = new YardDAO();
		}
		return instance;
	}
	public Yard updateYard(Yard yard) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("update yard "
                		+ "set idCategory_Yard = ?,"
                		+ "nameYard = ?,"
                		+ "status = ?"
                		+ "where idYard = ? ");
                
                ps.setString(1, String.valueOf(yard.getIdCateyard()));
                ps.setString(2, yard.getNameYard());
                ps.setString(3, String.valueOf(yard.getStatus()));
                ps.setString(4, String.valueOf(yard.getIdYard()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    yard = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update yard fail!" + ex.toString());
                yard = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return yard;
	}
	public List<Yard> getAllYard(){
		List<Yard> list = null;
		Yard yard = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from yard");
        		rs = ps.executeQuery();
        		list = new ArrayList<Yard>();
        		while(rs.next()) {
        			yard = new Yard(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4));
        			list.add(yard);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get yard fail!");
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return list;
	}
	public void setSatus(int status, int idYard) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("update yard set status = ? where idYard = ?");
         
                
                ps.setString(1, String.valueOf(status));
                ps.setString(2, String.valueOf(idYard));

                int row = ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Update yard fail!" );
                ex.printStackTrace();
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
	}
	
}
