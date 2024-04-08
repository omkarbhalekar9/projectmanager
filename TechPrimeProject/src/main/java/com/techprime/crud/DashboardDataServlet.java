package com.techprime.crud;

import java.io.PrintWriter;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techprime.Constants;
import com.techprime.model.DashboardData;
import com.techprime.sql.ProjectSql;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DashboardDataServlet")
public class DashboardDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
            // Fetch data from database or any other source
            ProjectSql projectSql = new ProjectSql();
            Map<String, Integer> map = projectSql.getProjectsByStatus();
            // Create Java object to hold data
            Integer totalProjects = map.containsKey("total") ? map.get("total") : 0;
            Integer closedProjects = map.containsKey(String.valueOf(Constants.STATUS_CLOSED)) ? map.get(String.valueOf(Constants.STATUS_CLOSED)) : 0;
            Integer runningProjects = map.containsKey(String.valueOf(Constants.STATUS_RUNNING)) ? map.get(String.valueOf(Constants.STATUS_RUNNING)) : 0;
            Integer closureDelay = projectSql.getClosureDelayProjects();
            Integer cancelledProjects = map.containsKey(String.valueOf(Constants.STATUS_CANCELLED)) ? map.get(String.valueOf(Constants.STATUS_CANCELLED)) : 0;
            DashboardData dashboardData = new DashboardData(totalProjects, closedProjects, runningProjects, closureDelay == null ? 0 : closureDelay, cancelledProjects);

            // Convert Java object to JSON
            ObjectMapper mapper = new ObjectMapper();
            String jsonData = mapper.writeValueAsString(dashboardData);

            // Set response content type to JSON
            response.setContentType("application/json");

            // Write JSON data to response
            PrintWriter out = response.getWriter();
            out.print(jsonData);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
