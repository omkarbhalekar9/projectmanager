package com.techprime.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techprime.Constants;
import com.techprime.Utils;
import com.techprime.config.DBConfig;
import com.techprime.model.ProjectDetails;

public class ProjectSql {
	public int saveProjectDetails(ProjectDetails projectDetails) throws SQLException {
		Connection con = null;
    	try {
    		con = (Connection) DBConfig.getConnection();
    	    PreparedStatement ps = null;
    	    ps = con.prepareStatement("insert into t_projectdetails (project_theme, reason, type,"
    	    		+ " division, category, priority, department, location, start_date, end_date, status_id) "
    	    		+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    	    ps.setString(1, projectDetails.getProjectTheme());
    	    ps.setString(2, projectDetails.getReason());
    	    ps.setString(3, projectDetails.getType());
    	    ps.setString(4, projectDetails.getDivision());
    	    ps.setString(5, projectDetails.getCategory());
    	    ps.setString(6, projectDetails.getPriority());
    	    ps.setString(7, projectDetails.getDepartment());
    	    ps.setString(8, projectDetails.getLocation());
    	    ps.setDate(9, projectDetails.getStartDate());
    	    ps.setDate(10, projectDetails.getEndDate());
    	    ps.setInt(11, Constants.STATUS_REGISTERED);
    	    
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

	public Integer fetchProjectId(String theme) throws SQLException {
		Connection con = null;
    	try {
    		con = (Connection) DBConfig.getConnection();
    	    PreparedStatement ps = null;
    	    ResultSet rs = null;
    	    ps = con.prepareStatement("select project_id from t_projectdetails where project_theme = ?");
    	    ps.setString(1, theme);
    	    rs = ps.executeQuery();
    	    
    	    if (rs != null) {
    	    	while (rs.next()) {
        	    	return rs.getInt("project_id");
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

	@SuppressWarnings("unchecked")
	public List<ProjectDetails> getProjectDetails(String searchOption, String sortOption) throws SQLException {
		Connection con = null;
		List<ProjectDetails> list = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd, yyyy");
    	try {
    		con = (Connection) DBConfig.getConnection();
    		String sql = "select * from t_projectdetails tp ";
    		boolean hasSearchOption = !Utils.isNullOrEmpty(searchOption);
            boolean hasSortOption = !Utils.isNullOrEmpty(sortOption);
    		PreparedStatement stmt = null;
    	    ResultSet rs = null;
    	    if (hasSearchOption) {
                sql += "JOIN t_status s ON tp.status_id = s.status_id "
                        + "WHERE LOWER(tp.project_theme) LIKE ? "
                        + "OR LOWER(tp.reason) LIKE ? "
                        + "OR LOWER(tp.type) LIKE ? "
                        + "OR LOWER(tp.division) LIKE ? "
                        + "OR LOWER(tp.category) LIKE ? "
                        + "OR LOWER(tp.priority) LIKE ? "
                        + "OR LOWER(tp.department) LIKE ? "
                        + "OR LOWER(tp.location) LIKE ? "
                        + "OR LOWER(s.status_name) LIKE ? ";
            }
    	    if (hasSortOption && !"projectName".equalsIgnoreCase(sortOption.trim())) {
                String sortBy = getCoulmnForSort(sortOption);
                sql += "ORDER BY tp." + sortBy;
            } else {
                sql += "ORDER BY tp.project_theme";
            }
    	    stmt = con.prepareStatement(sql);
    	    if (hasSearchOption) {
                for (int i = 1; i <= 9; i++) {
                    stmt.setString(i, searchOption.trim().toLowerCase() + "%");
                }
            }
    	    rs = stmt.executeQuery();
    	    
    	    if (rs != null) {
    	    	Map<Integer, String> status = fetchStatusName();
    	    	while (rs.next()) {
    	    		ProjectDetails obj = new ProjectDetails();
    	    		obj.setProjectId(rs.getInt("project_id"));
    	    		obj.setProjectTheme(rs.getString("project_theme"));
    	    		obj.setReason(rs.getString("reason"));
    	    		obj.setType(rs.getString("type"));
    	    		obj.setDivision(rs.getString("division"));
    	    		obj.setCategory(rs.getString("category"));
    	    		obj.setPriority(rs.getString("priority"));
    	    		obj.setDepartment(rs.getString("department"));
    	    		obj.setLocation(rs.getString("location"));
    	    		String start = sdf.format(rs.getDate("start_date"));
    	    		String end = sdf.format(rs.getDate("end_date"));
    	    		obj.setDateStr(Utils.capitalizeFirstLetter(start.substring(0, 3)) + start.substring(3) + " to " + Utils.capitalizeFirstLetter(end.substring(0, 3)) + end.substring(3));
    	    		obj.setStatus(status.get(rs.getInt("status_id")));
    	    		
    	    		list.add(obj);
    	    	}
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	} finally {
    		if (con != null) {
    			con.close();
    		}
    	}
		return list;
	}

	public Integer updateProjectStatus(Integer projectId, String status) throws SQLException {
		Integer statusId = 1;
		Integer i = null;
		if ("Start".equalsIgnoreCase(status)) {
			statusId = Constants.STATUS_RUNNING;
		} else if ("Close".equalsIgnoreCase(status)) {
			statusId = Constants.STATUS_CLOSED;
		} else if ("Cancel".equalsIgnoreCase(status)) {
			statusId = Constants.STATUS_CANCELLED;
		}

		Connection con = null;
		try {
			con = (Connection) DBConfig.getConnection();
			PreparedStatement ps = null;
			ps = con.prepareStatement("update t_projectdetails set status_id = ? where project_id = ?");
			ps.setInt(1, statusId);
			ps.setInt(2, projectId);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return i;
	}

	public Map<String, Integer> getProjectsByStatus() throws SQLException {
		Map<String, Integer> map = new HashMap<>();
		Connection con = null;
		String sql = "select status_id, count(*) as cnt from t_projectdetails group by status_id";
		try {
			con = (Connection) DBConfig.getConnection();
			Statement stmt = null;
    	    ResultSet rs = null;
    	    
    	    stmt = con.createStatement();
    	    rs = stmt.executeQuery(sql);
    	    
    	    if (rs != null) {
    	    	int total = 0;
    	    	while (rs.next()) {
        	    	map.put(rs.getString("status_id"), rs.getInt("cnt"));
        	    	total = total + rs.getInt("cnt");
    	    	}
    	    	map.put("total", total);
    	    }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return map;
	}

	public Integer getClosureDelayProjects() throws SQLException {
		Connection con = null;
		Integer count = null;
    	try {
    		con = (Connection) DBConfig.getConnection();
    		Statement stmt = null;
    	    ResultSet rs = null;
    	    
    	    stmt = con.createStatement();
    	    rs = stmt.executeQuery("SELECT COUNT(*) FROM t_projectdetails WHERE status_id = 2 AND end_date < CURRENT_DATE");
    	    
    	    if (rs != null) {
    	    	while (rs.next()) {
        	    	count = rs.getInt("count");
    	    	}
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	} finally {
    		if (con != null) {
    			con.close();
    		}
    	}
		return count;
	}

	private String getCoulmnForSort(String sortOption) {
		if ("Reason".equalsIgnoreCase(sortOption)) {
			return "reason";
		} else if ("Type".equalsIgnoreCase(sortOption)) {
			return "type";
		} else if ("Division".equalsIgnoreCase(sortOption)) {
			return "division";
		} else if ("Category".equalsIgnoreCase(sortOption)) {
			return "category";
		} else if ("Priority".equalsIgnoreCase(sortOption)) {
			return "priority";
		} else if ("Department".equalsIgnoreCase(sortOption)) {
			return "department";
		} else if ("Location".equalsIgnoreCase(sortOption)) {
			return "location";
		} else if ("Status".equalsIgnoreCase(sortOption)) {
			return "status_id";
		} else {
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public Map<Integer, String> fetchStatusName() throws SQLException {
		Connection con = null;
		Map<Integer, String> map = new HashMap<>();
    	try {
    		con = (Connection) DBConfig.getConnection();
    		Statement stmt = null;
    	    ResultSet rs = null;
    	    
    	    stmt = con.createStatement();
    	    rs = stmt.executeQuery("select * from t_status");
    	    
    	    if (rs != null) {
    	    	while (rs.next()) {
        	    	map.put(rs.getInt("status_id"), rs.getString("status_name"));
    	    	}
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	} finally {
    		if (con != null) {
    			con.close();
    		}
    	}
		return map;
	}
}
