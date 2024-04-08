package com.techprime.crud;

import com.techprime.Utils;
import com.techprime.sql.UserSql;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userName = request.getParameter("username");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			if (!Utils.isNullOrEmpty(userName) && !Utils.isNullOrEmpty(email) && !Utils.isNullOrEmpty(password)) {
				UserSql user = new UserSql();
				int count = user.insertUser(userName, email, password);
				if (count > 0) {
					response.sendRedirect("register.jsp?success=true");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
