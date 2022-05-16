package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDatabase {
	private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/qlsb?allowMultiQueries=true";
    private String user = "root";
    private String pass = "10022001dD@";
    private Connection cnn;
    
    private static ConnectDatabase instance;
    
    private ConnectDatabase(){    	
    }
    public static ConnectDatabase getInstance() {
    	if(instance == null) {
    		instance = new ConnectDatabase();
    	}
    	return instance;
    }
    
    public boolean open() {
        try {
            if (cnn == null || cnn.isClosed()) {
                Class.forName(driver);
                cnn = DriverManager.getConnection(url, user, pass);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public void close() {
        try {
            if (cnn != null) {
                cnn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void close(PreparedStatement ps){
        try {
            if (ps !=null) {
            ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }
    public void close(Statement ps){
        try {
            if (ps !=null) {
            ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        close();
    }
    public void close(Statement ps, ResultSet rs){
        try {
            if (rs !=null) {
            rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        close(ps);
    }
	public Connection getCnn() {
		return cnn;
	}  
}
