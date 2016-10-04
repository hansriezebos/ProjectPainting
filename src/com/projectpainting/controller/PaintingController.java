package com.projectpainting.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projectpainting.model.Painting;
import com.projectpainting.dao.PaintingDAO;
import com.projectpainting.dao.PaintingDAOImplementation;

@WebServlet("/PaintingController")
public class PaintingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PaintingDAO dao;
	public static final String LIST_PAINTING = "/listPainting.jsp";
	public static final String INSERT_OR_EDIT = "/painting.jsp";
	public static final String HOME = "/home.jsp";
       
    public PaintingController() {
    	dao = new PaintingDAOImplementation();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter( "action" );
		String msg = "";
		
		if( action.equalsIgnoreCase( "home" ) ) {
			forward = HOME;
			request.setAttribute("statistics", dao.getAllStatistics());
		}
		else if( action.equalsIgnoreCase( "delete" ) ) {
			forward = LIST_PAINTING;
			int userId = Integer.parseInt( request.getParameter("userId") );
			int paintingId = Integer.parseInt( request.getParameter("id") );
			dao.deletePainting(paintingId);
			msg = "<div class='alert alert-warning' role='alert'>Painting is verwijderd.</div>";
			request.setAttribute("message", msg);
			request.setAttribute("paintings", dao.getAllPaintings(userId) );
		}
		else if( action.equalsIgnoreCase( "edit" ) ) {
			forward = INSERT_OR_EDIT;
			int paintingId = Integer.parseInt( request.getParameter("id") );
			Painting painting = dao.getPaintingById(paintingId);
			request.setAttribute("painting", painting);
		}
		else if( action.equalsIgnoreCase( "insert" ) ) {
			forward = INSERT_OR_EDIT;
		}
		else {
			forward = LIST_PAINTING;
			int userId = Integer.parseInt( request.getParameter("userId") );
			request.setAttribute("paintings", dao.getAllPaintings(userId) );
		}
		RequestDispatcher view = request.getRequestDispatcher( forward );
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Painting painting = new Painting();
		PrintWriter out = response.getWriter();
		String msg = "";
		
		painting.setUserId(Integer.parseInt(request.getParameter("userId")));
		painting.setName(request.getParameter("paintingName"));
		painting.setImageData( request.getParameter("canvasData"));
		String paintingId = request.getParameter("paintingId");
		if (paintingId == null || paintingId.isEmpty()) {
			dao.addPainting(painting);
			msg = "<div class='alert alert-success' role='alert'>Painting is added to your collection.</div>";
		}
		else {
			painting.setId(Integer.parseInt(paintingId));
			dao.updatePainting(painting);
			msg = "<div class='alert alert-info' role='alert'>Painting has been edited.</div>";
		}
		
		RequestDispatcher view = request.getRequestDispatcher(LIST_PAINTING);
		request.setAttribute("message", msg);
		request.setAttribute("paintings", dao.getAllPaintings(Integer.parseInt(request.getParameter("userId"))));
		view.forward(request, response);
	}
}
	
