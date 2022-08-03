package com.Ali3;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// this servlet is used to check user name and password to authenticate user.
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		String uname = request.getParameter("uname"); // this gets the user name of person trying to login
		String pwd = request.getParameter("pwd"); // this gets the password of person trying to login

		DButility db = new DButility(); // this creates an object db from DButility class to call the methods of that class

		try{
			db.connectToDB(); // method for connecting to the database
			if (db.checkCredentials(uname, pwd)) 
			{
				request.getSession().setAttribute("CURRENT_USER", uname); // set attribute "CURRENT_USER" to have the name of person logged in.
				request.getSession().setMaxInactiveInterval(40); // if inactive for 5 minutes session will expire
				// the below method will get the history data to be displayed
				ResultSet resultt =  db.DBuser_history(uname); // this method prepares the statement and the query to get the data from the database
				String output = ""; // variable which will have the history columns combined.

				for (int i = 0; i < 5 && resultt.next(); i++)
				{
					String number, dec, hexdec; // Variables declaration
					number = resultt.getString("Inputted Number"); // number variable will have the column data of "Inputted Number"
					dec = resultt.getString("Converted To Decimal"); // dec variable will have the column data of "Converted To Decimal"
					hexdec = resultt.getString("Converted To HexaDecimal"); // hexdec variable will have the column data of "Converted To HexaDecimal"

					if(hexdec == null) // will not print null value of hexadecimal
					{
						output += "<h4>" + "You converted "+ number+" which is a binary number to Decimal number and the result was: "+dec+"</h4>";
					}
					else if (dec==null) // will not print null value of decimal
					{
						output += "<h4>"+" You converted "+number+" which is a binary number to HexaDecimal number and the result was: "+hexdec+"</h4>";
					}
				}
				request.setAttribute("output", output);	 // setting attribute of variable output so we can get this attribute in UserHistory.jsp to display history of user.
				request.getRequestDispatcher("/user-welcome.jsp").forward(request, response);
			}
			else 
			{
				request.setAttribute("errorMsg", db.getAuthenticationMessage()); // if user name and password does not match it displays error message
				request.getRequestDispatcher("/index.jsp").forward(request, response); // keep the user on the same index.jsp page
			}
		}
		//below catches exceptions
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
} 
