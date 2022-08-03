package com.Ali3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRegCon {


	public int register(User_Control UsersDetails) throws ClassNotFoundException {
	String dbquerry = "INSERT INTO `group_e_dbs`.`AliMehdi` (`ID`, `First Name`, `Last Name`, `Email`, `UserName`, `Password`) VALUES (?, ?, ?, ?, ?, ?);";

		//
		int output = 0;
		Class.forName("com.mysql.cj.jdbc.Driver");
		String dbname = "group_e_dbs"; //<--Schema name
		String url = "jdbc:mysql://129.151.75.225/" + dbname; //<--Insert IP
		//String url = "jdbc:mysql://129.151.75.225/" + dbname; //<--Insert IP
		String username = "202102_group_e"; 
		String password = "Groupe-64bbb"; //<--Put your groups password here

		try{
			Connection connection = DriverManager.getConnection(url, username, password);

			PreparedStatement preparedStmt = connection.prepareStatement(dbquerry);
			preparedStmt.setString(1, UsersDetails.getID());
			preparedStmt.setString(2, UsersDetails.getFirstName());
			preparedStmt.setString(3, UsersDetails.getLastName());
			preparedStmt.setString(4, UsersDetails.getEmail());
			preparedStmt.setString(5, UsersDetails.getUserName());
			preparedStmt.setString(6, UsersDetails.getPassword());

			System.out.println(preparedStmt);

			output = preparedStmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return output;
	}

}

