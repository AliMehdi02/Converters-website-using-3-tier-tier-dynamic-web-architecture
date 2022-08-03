package com.Ali3;
import java.sql.Connection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authenticator{
	private String uname = null;
	private String pwd = null;
	private String stored_username = null;
	private String stored_password = null;

	//Constructor
	Authenticator (String uname, String pwd) throws IOException{
		this.uname = uname;
		this.pwd = pwd;
	}

	// TODO: In assignment 2.2 discuss authentication tokens with the idea that
	// plain text comparison is not secure.
	public boolean authenticateUser() throws IOException, ClassNotFoundException, SQLException{
		return checkCredentials() ? true : false;
	}

	/**
	 * This method only checks if a user exists within the MySQL Database.
	 * It is not efficient and uses plain text password.
	 * @throws ClassNotFoundException 
	 */

	/* 
	 * TODO: Replace plain text passwords with authentication tokens
	 * 
	 * TODO: This method needs to be split as it's doing too much. Split into:
	 * TODO:	 1) Acquiring the MySQL Connection only
	 * 			-- The connection object then needs to be attached to a cookie
	 * 			-- The cookie + SQL connection is then invalidated when the user closes the session / times out
	 * TODO:	 2) Executing different types of SQL statements
	 * 			-- Inserting into different tables
	 * 			-- Reading from different tables
	 */
	public boolean checkCredentials() throws ClassNotFoundException{
		boolean status = false;
		Class.forName("com.mysql.cj.jdbc.Driver");
		String dbname = "group_e_dbs"; //<--Schema name
		String url = "jdbc:mysql://129.151.75.225/" + dbname; //<--Insert IP
		//String url = "jdbc:mysql://129.151.75.225/" + dbname; //<--Insert IP
		String username = "202102_group_e"; 
		String password = "Groupe-64bbb"; //<--Put your groups password here

		System.out.println("Connecting database...");


		try{
			Connection connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");

			//Create SQL Statement
			Statement stmt = connection.createStatement();

			//Execute SQL Statement
			ResultSet rs = stmt.executeQuery("SELECT * FROM AliMehdi;");

			//Get all the rows from this table
			while (rs.next()) {
				System.out.println(rs.getString("ID"));

				//Store this rows details
				this.stored_username = rs.getString("UserName");
				this.stored_password = rs.getString("Password");

				//Check if it matches the user that is requesting to log in
				if(uname.equals(this.stored_username) && pwd.equals(this.stored_password)){
					status = true;
				}
			}
			connection.close();
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
		return status;
	}

	public String getAuthenticationMessage(){
		return "Username or password is incorrect";
	}

	public String getUsername(){
		return this.stored_username;
	}
}
