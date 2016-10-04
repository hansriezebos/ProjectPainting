package com.projectpainting.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Validate validator = new Validate();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
	
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String msg = "";
		// gegevens van de gebruiker uit de database halen
		User user = validator.findUser(email, password);
		
		// controleren of de gebruiker bestaat
		if(validator.checkUser(user)) {
			// cookies aanmaken
			Cookie loginId = new Cookie("id", String.valueOf(user.getId()));
			Cookie loginName = new Cookie("name", user.getName());
			loginId.setMaxAge(60*60);
			loginName.setMaxAge(60*60);
			response.addCookie(loginId);
			response.addCookie(loginName);
			response.sendRedirect("PaintingController?action=home");
		} else {
			msg = "<span>Username or password incorrect.</span>";
			request.setAttribute("message", msg);
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.include(request, response);
		}
		
	}
}