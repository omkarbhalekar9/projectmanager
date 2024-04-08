package com.techprime.crud;

import com.techprime.Utils;
import com.techprime.model.UserDetails;
import com.techprime.sql.UserSql;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/LoginUser")
public class LoginUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String userName = request.getParameter("login_username");
			String password = request.getParameter("login_password");
			
			if (!Utils.isNullOrEmpty(userName) && !Utils.isNullOrEmpty(password)) {
				UserSql user = new UserSql();
				UserDetails userDetail = user.fetchUser(userName, password);
				if (userDetail != null) {
					HttpSession session = request.getSession(true);
					session.setAttribute("userDetail", userDetail);
                    session.setMaxInactiveInterval(10);
					response.sendRedirect("CreateProject.jsp?success=true");
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
