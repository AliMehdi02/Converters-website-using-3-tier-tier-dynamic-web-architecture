package com.Ali3;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//This servlet is responsible for converting the binary number to decimal and hexadecimal and as well as getting the data required to be stored in history database.
@WebServlet("/Converter")
public class Convertor extends HttpServlet{
	private static final long serialVersionUID = 1;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try{  //in order to catch null pointer exception and others such as sql and class exception
			String result = ""; //Variable declaration

			System.out.println("Some binary data has been submitted and its being calculated");

			String user = request.getSession().getAttribute("CURRENT_USER").toString(); // Gets the user name of person logged in

			String session_id = request.getSession().getId(); //gets the session ID of the session which invalidates after logging out

			System.out.println("Session data: " + user + " Session ID: " + session_id); // Printing session ID and user name of person

			String input = request.getParameter("bin-number"); // this bin-number is the name of the input in user-welcome JSP where user will input binary number

			System.out.println(input); //this line prints the binary number that the user typed

			String option = request.getParameter("DecHex"); // this line of code gets the option that the user selected (either he/she wants to covert to decimal or hexadecimal)

			Algorithm algo = new Algorithm(); //creating a new object from the Algorithm class which contains my converter codes in order to call the user-defined methods 

			algo.BinaryToDecimal(Integer.parseInt(input)); // this method converts from binary to decimal  
			algo.BinaryToHex(Integer.parseInt(input)); // this method converts from binary to Hexadecimal  

			if(option.equals("Decimal")) // if user selected to convert to decimal                                                                          
			{
				result = String.valueOf(algo.BinaryToDecimal(Integer.parseInt(input))); // if user selects to convert to decimal then this method will be executed
			}
			else if(option.equals("HexaDecimal")) // if user selected to convert to hexadecimal
			{
				result = algo.BinaryToHex(Integer.parseInt(input)); //if user selects to convert to hexadecimal then this method will be executed
			}
			request.getSession().setAttribute("Ans", result); // This is setting the attribute "Ans" to have the result output

			// The Below codes is for storing the user history into the database.
			DButility dbH = new DButility(); // creating object from DButility class to call the method to connect to database which is in DButility class.

			String UserName = user;
			String InputtedNumber = input;
			String ToDecimal = null;
			String ToHexaDecimal = null;

			if (option.equals("Decimal")) // if the user selected to convert to decimal den input decimal number and leave HexaDecimal as null.
			{
				ToDecimal = result ;
			}
			else if (option.equals("HexaDecimal")) // if the user selected to convert to decimal den input decimal number and leave HexaDecimal as null.
			{
				ToHexaDecimal = result ;
			}

			dbH.connectToDB();
			dbH.store_history(UserName, InputtedNumber, ToDecimal, ToHexaDecimal); // this method is for executing and preparing the statement and the query for inserting the required details into the database.
			request.getRequestDispatcher("/user-welcome.jsp").forward(request, response); // redirect to the same page.
		}
		//Catching Exceptions Details
		catch (SQLException e){
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e){
			request.setAttribute("errorMsg", "Error! Your session has expired"); // if the user exceeds the time limit of session den he/she is redirected to index.jsp and this message is shown.
			request.getRequestDispatcher("/index.jsp").forward(request, response); // redirects to login page in case null pointer exception occurs
		} 
	}
}


