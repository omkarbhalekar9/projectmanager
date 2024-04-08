package com.techprime.crud;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.techprime.model.ProjectDetails;
import com.techprime.sql.ProjectSql;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/SaveProjectDetails")
public class SaveProjectDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			ProjectDetails projectDetails = new ProjectDetails();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			projectDetails.setProjectTheme(request.getParameter("theme"));
			projectDetails.setReason(request.getParameter("reason")); // dropdown1
			projectDetails.setCategory(request.getParameter("category")); // dropdown2
			projectDetails.setDepartment(request.getParameter("department")); // dropdown3
			projectDetails.setDivision(request.getParameter("division")); // dropdown4
			projectDetails.setLocation(request.getParameter("location")); // dropdown5
			projectDetails.setPriority(request.getParameter("priority")); // dropdown6
			projectDetails.setType(request.getParameter("type")); // dropdown7

			String sdate = request.getParameter("start_date"); // date1
			String edate = request.getParameter("end_date"); // date2
			projectDetails.setStartDate(new Date(sdf.parse(sdate).getTime()));
			projectDetails.setEndDate(new Date(sdf.parse(edate).getTime()));

			ProjectSql projectSql = new ProjectSql();
			Integer count = projectSql.saveProjectDetails(projectDetails);
			if (count != null && count > 0) {
				int projectId = projectSql.fetchProjectId(projectDetails.getProjectTheme());
				response.sendRedirect("CreateProject.jsp?success=true&projectId=" + projectId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
