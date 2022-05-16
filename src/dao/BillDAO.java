package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import connect.ConnectDatabase;
import entity.Bill;

public class BillDAO {
	
	private static BillDAO instance;
	private BillDAO() {	
	}
	public static BillDAO getInstance() {
		if( instance == null) {
			instance = new BillDAO();
		}
		return instance;
	}
	
	public Bill insertBill(Bill bill) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("insert into Bill values (?,?,?,?,?,?)");
                ps.setString(1, String.valueOf(bill.getIdBill()));
                ps.setString(2, String.valueOf(bill.getCreateDate()));
                ps.setString(3, String.valueOf(bill.getCreateTime()));
                ps.setString(4, String.valueOf(bill.getTotal()));
                ps.setString(5, String.valueOf(bill.getIdEmployee()));
                if(bill.getIdOrder() != 0)
                	ps.setString(6, String.valueOf(bill.getIdOrder()));
                else 
                	ps.setString(6, null);
                int row = ps.executeUpdate();
                if (row < 1) {
                	bill = null;
                }
            } catch (SQLException ex) {
                System.out.println("Insert Bill fail!" + ex);
                ex.printStackTrace();
                bill = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return bill;
	}
	
	public List<Bill> getAllBill(){
		Bill Bill = null;
		List<Bill> list = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select * from bill");
        		rs = ps.executeQuery();
        		list = new ArrayList<Bill>();
        		while(rs.next()) {
        			Bill = new Bill(rs.getInt(1),rs.getDate(2),Time.valueOf(rs.getString(3)),rs.getInt(4),rs.getInt(5),rs.getInt(6));
        			list.add(Bill);
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get Bill fail!" + ex);
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return list;
	}
	
	public Bill updateBill(Bill bill) {
		PreparedStatement ps = null;
		if (ConnectDatabase.getInstance().open()) {
            try {
                ps = ConnectDatabase.getInstance().getCnn().prepareStatement("update bill "
                		+ "set create_Date = ?,"
                		+ "create_Time = ?,"
                		+ "total = ?,"
                		+ "idEmployee = ?,"
                		+ "idOrdered = ? "
                		+ "where idBill = ? ");

                ps.setString(1, String.valueOf(bill.getCreateDate()));
                ps.setString(2, String.valueOf(bill.getCreateTime()));
                ps.setString(3, String.valueOf(bill.getTotal()));
                ps.setString(4, String.valueOf(bill.getIdEmployee()));           
                if(bill.getIdOrder() != 0) {
                	ps.setString(5, String.valueOf(bill.getIdOrder()));
                }else {
                	ps.setNull(5, Types.NULL);
                }
                ps.setString(6, String.valueOf(bill.getIdBill()));  
                int row = ps.executeUpdate();
                if (row < 1) {
                    bill = null;
                }
            } catch (SQLException ex) {
                System.out.println("Update Bill fail!" + ex.toString());
                ex.printStackTrace();
                bill = null;
            } finally {
            	ConnectDatabase.getInstance().close(ps);
            }
        }
        return bill;
	}
	public void deleteBill(int idBill) {
		PreparedStatement ps = null;
		try {
			if(ConnectDatabase.getInstance().open()) {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("delete from bill where idBill = ?");
				ps.setString(1, String.valueOf(idBill));
				ps.executeUpdate();
				ConnectDatabase.getInstance().close();
			}
		}catch(SQLException e) {
			System.out.println("Delete fail!"+ e.toString());
			e.printStackTrace();
		}
	}
	public void deleteBillByDate(String date1, String date2) {
		PreparedStatement ps = null;
		try {
			if(ConnectDatabase.getInstance().open()) {
				ps = ConnectDatabase.getInstance().getCnn().prepareStatement("delete from bill where create_Date BETWEEN ? AND ?");
				ps.setString(1, date1);
				ps.setString(2, date2);
				ps.executeUpdate();
				ConnectDatabase.getInstance().close();
			}
		}catch(SQLException e) {
			System.out.println("Delete fail!"+ e.toString());
			e.printStackTrace();
		}
	}
	public int nextId() {
		int value = -1;
		PreparedStatement ps = null;
		ResultSet rs = null;
		if(ConnectDatabase.getInstance().open()) {
        	try {
        		ps = ConnectDatabase.getInstance().getCnn().prepareStatement("select MAX(idBill) from qlsb.bill");
        		rs = ps.executeQuery();
        		if(rs.next()) {
        			value = rs.getInt(1);
        		}else {
        			value = 0;
        		}
        	}catch (SQLException ex) {
        		System.out.println("Get next id bill fail!");
        		ex.printStackTrace();
            } finally {
            	ConnectDatabase.getInstance().close(ps, rs);
            }
        }
		return value + 1 ;
	}
}