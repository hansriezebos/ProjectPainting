<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Project Painting</title>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="js/sketch.js"></script>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/jumbotron.css" rel="stylesheet">
	<link href="css/canvas_style.css" rel="stylesheet">
	<link href="css/navbar-static-top.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/canvas_style.css">
	<link href="images/YT.png" rel="icon">
</head>
<body onload="showImg()">
	<%
		String userId = null;
		String userName = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("id"))
					userId = cookie.getValue();
				if (cookie.getName().equals("name"))
					userName = cookie.getValue();
			}
		}
		if (userId == null)
			response.sendRedirect("index.jsp");
	%>
	<nav class="navbar navbar-default navbar-static-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="index.jsp">Project Painting</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="PaintingController?action=home">Home</a></li>
				<li><a href="PaintingController?action=listPainting&userId=<%=userId%>">My Paintings</a></li>
				<li class="active"><a href="PaintingController?action=insert">Start Painting</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<form action="Logout" method="post">
					<input class="btn btn-primary btn-lg" type="submit"
						value="Sign out">
				</form>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	</nav>
	<div class="container">
	
		<div class="col-md-1">
			<div id="SketchTools">
				<!-- Advanced colors -->
				<a href="#SketchPad" data-color="#e74c3c" title="Alizarin"><img src="images/alizarin_icon.png" alt="Alizarin"/></a>
				<a href="#SketchPad" data-color="#c0392b" title="Pomegrante"><img src="images/pomegrante_icon.png" alt="Pomegrante"/></a>
				<a href="#SketchPad" data-color="#2ecc71" title="Emerald"><img src="images/emerald_icon.png" alt="Emerald"/></a>
				<a href="#SketchPad" data-color="#1abc9c" title="Torquoise"><img src="images/torquoise_icon.png" alt="Torquoise"/></a>
				<a href="#SketchPad" data-color="#3498db" title="Peter River"><img src="images/peterriver_icon.png" alt="Peter River"/></a>
				<a href="#SketchPad" data-color="#9b59b6" title="Amethyst"><img src="images/amethyst_icon.png" alt="Amethyst"/></a>
				<a href="#SketchPad" data-color="#f1c40f" title="Sun Flower"><img src="images/sunflower_icon.png" alt="Sun Flower"/></a>
				<a href="#SketchPad" data-color="#f39c12" title="Orange"><img src="images/orange_icon.png" alt="Orange"/></a>
			</div>	
		</div>
		<div class="col-md-1">
			<div id="SketchTools">
				<!-- Advanced colors -->		
				<a href="#SketchPad" data-color="#ecf0f1" title="Clouds"><img src="images/clouds_icon.png" alt="Clouds"/></a>
				<a href="#SketchPad" data-color="#bdc3c7" title="Silver"><img src="images/silver_icon.png" alt="Silver"/></a>
				<a href="#SketchPad" data-color="#7f8c8d" title="Asbestos"><img src="images/asbestos_icon.png" alt="Asbestos"/></a>
				<a href="#SketchPad" data-color="#34495e" title="Wet Asphalt"><img src="images/wetasphalt_icon.png" alt="Wet Asphalt"/></a>
				<a href="#SketchPad" data-color="#ffffff" title="Eraser"><img src="images/eraser_icon.png" alt="Eraser"/></a>
			</div>	
		</div>
		<div class="col-md-1">
			<div id="SketchTools">
				<!-- Basic tools -->
				<a href="#SketchPad" data-color="#000000" title="Black"><img src="images/black_icon.png" alt="Black"/></a>
				<a href="#SketchPad" data-color="#ff0000" title="Red"><img src="images/red_icon.png" alt="Red"/></a>
				<a href="#SketchPad" data-color="#00ff00" title="Green"><img src="images/green_icon.png" alt="Green"/></a>
				<a href="#SketchPad" data-color="#0000ff" title="Blue"><img src="images/blue_icon.png" alt="Blue"/></a>
				<a href="#SketchPad" data-color="#ffff00" title="Yellow"><img src="images/yellow_icon.png" alt="Yellow"/></a>
				<a href="#SketchPad" data-color="#00ffff" title="Cyan"><img src="images/cyan_icon.png" alt="Cyan"/></a>
			</div>	
		</div>
		<div class="col-md-8">
        	<!-- <canvas id="SketchPad" width="700" height="500" onclick="imgToData()" > -->
			<canvas id="SketchPad" width="700" height="500">
			<script type="text/javascript">
	  			$(function() {
	    			$('#SketchPad').sketch();
	    			//sketch.onclick = imgToData();
	  			});
			</script>
		</div>
		<div class="col-md-1">
			<div id="SketchTools">
				<!-- Size options -->
				<a href="#SketchPad" data-size="1"><img src="images/pencil_icon.png" alt="Pencil"/></a>
				<a href="#SketchPad" data-size="3"><img src="images/pen_icon.png" alt="Pen"/></a>
				<a href="#SketchPad" data-size="5"><img src="images/stick_icon.png" alt="Stick"/></a>
				<a href="#SketchPad" data-size="9"><img src="images/smallbrush_icon.png" alt="Small brush"/></a>
				<a href="#SketchPad" data-size="15"><img src="images/mediumbrush_icon.png" alt="Medium brush"/></a>
				<a href="#SketchPad" data-size="30"><img src="images/bigbrush_icon.png" alt="Big brush"/></a>
				<a href="#SketchPad" data-size="60"><img src="images/bucket_icon.png" alt="Huge bucket"/></a>
		
				<a href="#SketchPad" data-download='png' id="DownloadPng">Download .PNG</a>
			</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form method="post" action="paintingcontroller" autocomplete="on">
					<p>
						<label for="paintingname"> Painting name </label> 
						<input id="paintingname" name="paintingName" required="required" type="text" placeholder="" value="${painting.name}" /> 
						<input id="paintingId" name="paintingId" type="hidden" value="${painting.id}" /> 
						<input id="image_data" name="canvasData" required="required" type="hidden" placeholder="" value="${painting.imageData}" /> 
						<input id="userId" name="userId" required="required" type="hidden" placeholder="" value="<%=userId%>" />
						<input type="submit" value="Save painting" />
					</p>
				</form>

			</div>
		</div>
		<hr>

		<footer>
		<p>&copy; 2016 Marc de Vries</p>
		</footer>
	</div>
	<!-- /container -->
</body>
</html>