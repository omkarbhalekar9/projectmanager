package com.techprime.crud;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techprime.model.ProjectDetails;
import com.techprime.sql.ProjectSql;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ProjectList")
public class ProjectList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String searchName = request.getParameter("searchName");
			String sortOption = request.getParameter("sortOption");
			ProjectSql projectSql = new ProjectSql();
			List<ProjectDetails> list = projectSql.getProjectDetails(searchName, sortOption);
			if (list != null) {
				ObjectMapper mapper = new ObjectMapper();
				try {
                    String jsonList = mapper.writeValueAsString(list);
                    request.setAttribute("jsonList", jsonList);
                    // Forward the request to the JSP page
                    request.getRequestDispatcher("ShowProjects.jsp").forward(request, response);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    // Handle the exception as needed
                }
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

