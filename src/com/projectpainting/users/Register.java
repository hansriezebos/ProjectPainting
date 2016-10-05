package com.projectpainting.users;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Validate validator = new Validate();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("namesignup");
		String email = request.getParameter("emailsignup");
		String password = request.getParameter("passwordsignup");
		String passwordConfirm = request.getParameter("passwordsignup_confirm");
		String msg = "";
		
		// eerst controleren of gegevens aan de voorwaarden voldoen
		if(password.equals(passwordConfirm)) {
			User user = validator.signUpUser(name, email, password);
			// inloggen
			user = validator.findUser(email, password);
			
			// cookies aanmaken
			Cookie loginId = new Cookie("id", String.valueOf(user.getId()));
			Cookie loginName = new Cookie("name", user.getName());
			loginId.setMaxAge(60*60);
			loginName.setMaxAge(60*60);
			response.addCookie(loginId);
			response.addCookie(loginName);
			response.sendRedirect("PaintingController?action=home");
		} else {
			msg = "<span>Password does not match.</span>";
			request.setAttribute("messageRegister", msg);
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.include(request, response);
		}
	}	
}
