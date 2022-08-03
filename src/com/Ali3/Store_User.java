package com.Ali3;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// this servlet is used to store the user details trying to register.
@WebServlet("/Store_User")
public class Store_User extends HttpServlet{
	private static final long serialVersionUID = 1;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName"); //gets first name of whatever user inputed in the form
		String lastName = request.getParameter("lastName"); //gets last name of whatever user inputed in the form
		String userName = request.getParameter("userName"); //gets user name of whatever user inputed in the form
		String password = request.getParameter("password"); //gets password of whatever user inputed in the form

		DButility dbstore = new DButility(); // creating a new object from DButility class to call its methods.

		try{
			dbstore.connectToDB(); // this method connects to the database.
			dbstore.store_user(firstName, lastName, userName, password); // this method prepares the statement and the query to insert registration details into the database. 
			request.getRequestDispatcher("/index.jsp").forward(request, response); // after pressing register it forwards the user to login page (index.jsp)
		}
		// below catches exceptions
		catch (ClassNotFoundException e){
			e.printStackTrace();
		} 
		catch (SQLException e){
			request.setAttribute("prb", dbstore.getprbMessage()); // gets error message if same user name.
			request.getRequestDispatcher("/register.jsp").forward(request, response); // keeps user on the registration page
		}
	}
}
