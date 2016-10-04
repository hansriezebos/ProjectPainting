<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Project Painting</title>
	<script src="js/script.js"></script>
	<script src="js/bootstrap.js"></script>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/jumbotron.css" rel="stylesheet">
	<link href="css/navbar-static-top.css" rel="stylesheet">
	<link href="images/YT.png" rel="icon">
</head>
<body>
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
            <li class="active"><a href="PaintingController?action=home">Home</a></li>
            <li><a href="PaintingController?action=listPainting&userId=<%=userId%>">My Paintings</a></li>
            <li><a href="PaintingController?action=insert">Start Painting</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          	<form action="Logout" method="post">
				<input class="btn btn-primary btn-lg" type="submit" value="Sign out">
			</form>
			<!--
            <li><p><a class="btn btn-primary btn-lg" href="#" role="button">Sign out</a></p></li>
            -->
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Hello, <%=userName%> </h1>
			<p>Welcome to Project Painting. We take the pain out of painting.</p>
			<p>
				<a class="btn btn-primary btn-lg" href="PaintingController?action=insert" role="button">Start Painting &raquo;</a>
			</p>
		</div>
	</div>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-md-6">
				<h2>Project Painting in numbers</h2>
				<p>Right now, there are ${statistics[1]} registered painters out there who discovered Project Painting 
				and created a total of ${statistics[0]} paintings.</p>
			</div>
			<div class="col-md-6">
				<div class="col-md-6-wrapper">
				<h2>Project Painting is enabled by</h2>
				<p class="enabled">
					<a href="http://www.youngcapital.nl" target="_blank"><img src="images/yt-logo.png" alt="YoungCapital"></a>
					<a href="http://www.enable-u.nl" target="_blank"><img src="images/enableu-logo.png" alt="Enable-U"></a>
					<a href="http://www.itph-academy.nl" target="_blank"><img src="images/itph-logo.png" alt="ITPH Academy"></a>
					<span class="stretch"></span>
				</p>
				</div>
			</div>
		</div>

		<hr>

		<footer>
		<p>&copy; 2016 Marc de Vries</p>
		</footer>
	</div>	<!-- /container -->

</body>
</html>