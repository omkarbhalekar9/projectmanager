package com.techprime.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.techprime.config.DBConfig;
import com.techprime.model.UserDetails;

public class UserSql {

	public int insertUser(String userName, String email, String password) throws SQLException {
    	Connection con = null;
    	try {
    		con = (Connection) DBConfig.getConnection();
    	    PreparedStatement ps = null;
    	    ps = con.prepareStatement("insert into t_user (user_name, email, password) values (?, ?, ?)");
    	    ps.setString(1, userName);
    	    ps.setString(2, email);
    	    ps.setString(3, password);
    	    
    	    int count = ps.executeUpdate();
    	    return count;
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	} finally {
    		if (con != null) {
    			con.close();
    		}
    	}
		return 0;
    }

	public UserDetails fetchUser(String userName, String password) throws SQLException {
		Connection con = null;
    	try {
    		con = (Connection) DBConfig.getConnection();
    	    PreparedStatement ps = null;
    	    ResultSet rs = null;
    	    ps = con.prepareStatement("select * from t_user where user_name = ? and password = ?");
    	    ps.setString(1, userName);
    	    ps.setString(2, password);
    	    rs = ps.executeQuery();
    	    
    	    if (rs != null) {
    	    	while (rs.next()) {
    	    		UserDetails userDetail = new UserDetails();
        	    	userDetail.setUserName(rs.getString("user_name"));
        	    	userDetail.setUserPassword(rs.getString("password"));
        	    	return userDetail;
    	    	}
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	} finally {
    		if (con != null) {
    			con.close();
    		}
    	}
		return null;
	}
}
