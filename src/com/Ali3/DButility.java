package com.Ali3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpServlet;

//This Class consists of several methods doing different process authentication, connection, reading from database as well as insertion into the database.
public class DButility  extends HttpServlet{
	private static final long serialVersionUID = 1L;

	//Below are Variables
	public String stored_username = null;
	public String stored_password = null;
	public Connection con = null; // it represents unique session with a data source in client/server database system,
	public ResultSet rs = null; //an object that represents a set of data returned from a data source, usually as the result of a query works by pointing cursor to the row.

	//The Below Method connects to MySql Database
	private Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver"); // this fixes the error of no suitable driver found however this is not required but due to some bugs we have to keep this.
		String dbname = "group_e_dbs"; // name of the schema
		String url = "jdbc:mysql://129.151.75.225/" + dbname; // the url or the IP address of the database
		String username = "202102_group_e"; // user name of the database
		String password = "Groupe-64bbb"; // password of the database
		return DriverManager.getConnection(url, username, password); // gets connection.
	}

	//The below method checks credential of user whether the user trying to login is inputting the correct user name and password as registered.
	public boolean checkCredentials(String uname , String pwd) throws ClassNotFoundException, SQLException // this method takes uname and pwd as parameter
	{
		boolean x = false; //initially boolean is set to false
		Statement stmt = con.createStatement(); // creation of statement
		ResultSet rs = stmt.executeQuery("SELECT * FROM AliMehdi_UserDetails;"); // execution of Mysql statement.

		while (rs.next()) 
		{ 
			this.stored_username = rs.getString("UserName"); // gets the username and stores it.
			this.stored_password = rs.getString("Password"); // gets the password and stores it.

			if(uname.equals(this.stored_username) && pwd.equals(this.stored_password)) // compares if the user name and password of the person trying to login whether he is registered in the database or not
			{
				x = true; // returns true if the user trying to log in is registered in the database
			}
		}
		return x; // returns boolean
	}

	public String getAuthenticationMessage() /// this has the error message if the user name or password is incorrect.
	{
		return "Username or password is incorrect";
	}

	// This is for registering new user and storing their details in the database
	public boolean store_user(String FirstName, String LastName, String UserName, String Password ) throws SQLException 
	{	
		boolean success= false; // initially boolean set to false
		String dbquerry = "INSERT INTO `group_e_dbs`.`AliMehdi_UserDetails` (`First Name`, `Last Name`, `UserName`, `Password`) VALUES (?, ?, ?, ?);"; // this is the query
		PreparedStatement preparedStmt = con.prepareStatement(dbquerry); // this is for preparing the statement
		preparedStmt.setString(1, FirstName); 
		preparedStmt.setString(2, LastName);
		preparedStmt.setString(3, UserName);
		preparedStmt.setString(4, Password);
		preparedStmt.execute(); // executes the query for inserting into the database
		success = true; // boolean is set to true
		{
			return success; // returns boolean  output
		}	
	}
	public String getprbMessage() // this has the message if user name already exists
	{
		return "SORRY! UserName Already Exists!";
	}


	//this is for storing user history of what the user converted
	public boolean store_history(String UserName, String InputtedNumber, String ToDecimal, String ToHexaDecimal) throws SQLException
	{
		boolean success = false; // boolean initially set to false
		String dbquerry = "INSERT INTO `group_e_dbs`.`AliMehdi_UserHistory` (`UserName`, `Inputted Number`, `Converted To Decimal`, `Converted To HexaDecimal`) VALUES (?, ?, ?, ?);"; // this is the query
		PreparedStatement preparedStmt = con.prepareStatement(dbquerry); // this is for preparing the statement
		preparedStmt.setString(1, UserName);
		preparedStmt.setString(2, InputtedNumber);
		preparedStmt.setString(3, ToDecimal);
		preparedStmt.setString(4, ToHexaDecimal);
		preparedStmt.execute(); //executes the query for inserting into the database
		success = true; // boolean is set to true
		{
			return success; // returns boolean
		}
	}

	// this method is used to get the information to display users history in UserHistory.jsp
	public ResultSet DBuser_history(String uname) throws SQLException  // As all users history are in one table thats why this take uname as parameter so that it displays history according to the user logged in.
	{
		Statement statement = con.createStatement(); // this is for preparing the statement
		ResultSet result = statement.executeQuery("SELECT * FROM group_e_dbs.AliMehdi_UserHistory where UserName like '" + uname + "'"); // this is the query whereby uname will be according to the user logged in.
		return result; // returns query
	}

	//this is the user defined method used for connecting to database.
	public void connectToDB() throws ClassNotFoundException, SQLException 
	{
		con = getConnection();
		System.out.println("Successfully Connected To MySql Database");
	}
}
