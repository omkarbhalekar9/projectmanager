package com.techprime.crud;

import com.techprime.sql.ProjectSql;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateProjectStatus")
public class UpdateProjectStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String projectId = request.getParameter("projectId");
			String status = request.getParameter("action");
			ProjectSql projectSql = new ProjectSql();
			Integer count = projectSql.updateProjectStatus(Integer.parseInt(projectId), status);
			if (count != null && count > 0) {
				response.sendRedirect("ShowProjects.jsp?success=true");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
