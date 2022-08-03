package com.Ali3;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// This servlet invalidates the session if the user logs out.
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Invalidate the session if exists
		HttpSession session = request.getSession(false); 
		if(session != null) // if session is null then invalidate this happens after user press log out
		{
			session.invalidate();
		}
		response.sendRedirect(request.getContextPath() + "/index.jsp"); // redirect to index.jsp
	}
}
