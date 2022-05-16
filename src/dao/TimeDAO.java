package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import entity.Beverage;
import entity.Time;

public class TimeDAO {
	private static TimeDAO instance;
	private TimeDAO() {	
	}
	public static TimeDAO getInstance() {
		if( instance == null) {
			instance = new TimeDAO();
		}
		return instance;
	}
	public Time updateTime(Time time) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("update time "
                		+ "set time = ?"
                		+ "where idTime = ? ");
                
                ps.setString(1, time.getTime());
                ps.setString(2, String.valueOf(time.getIdTime()));
                int row = ps.executeUpdate();
                if (row < 1) {
                    time = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update time fail!" + ex.toString());
                time = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return time;
	}
	public List<Time> getAllTime(){
		List<Time> list = null;
		Time time = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from time");
        		rs = ps.executeQuery();
        		list = new ArrayList<Time>();
        		while(rs.next()) {
        			time = new Time(rs.getInt(1),rs.getString(2));
        			list.add(time);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get time fail!");
        		ex.printStackTrace();
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return list;
	}
}
