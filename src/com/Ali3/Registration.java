package com.Ali3;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/Registration")
public class Registration extends HttpServlet{

	private static final long serialVersionUID = 1;

	private UserRegCon userregistration= new UserRegCon();

			public Registration() {
		super();

	}
			
			protected void doget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				response.getWriter().append("server at: ").append(request.getContextPath());
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String iD = request.getParameter("iD");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		User_Control newUser = new User_Control();
		newUser.setID(iD);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		newUser.setUserName(userName);
		newUser.setPassword(password);
		
		try {
			userregistration.register(newUser);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
			
		


		/*To_Register registering = null;
		try {
			registering = new To_Register(Con_DB_Register.getConnection());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (registering.RegisterUser(design)) {
			response.sendRedirect("index.jsp");
		} else {
			String Message = "User Available";
			HttpSession session = request.getSession();
			session.setAttribute("Error", Message);
			response.sendRedirect("register.jsp");*/
		}

	}

