<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Project Painting</title>
	<script src="js/script.js"></script>
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
            <li><a href="PaintingController?action=home">Home</a></li>
            <li class="active"><a href="PaintingController?action=listPainting&userId=<%=userId%>">My Paintings</a></li>
            <li><a href="PaintingController?action=insert">Start Painting</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
          	<form action="Logout" method="post">
				<input class="btn btn-primary btn-lg" type="submit" value="Sign out">
			</form>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
	
	<div class="container">
  		${message}
		<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>Painting ID</th>
				<th>Painting name</th>
				<th>Image</th>
				<th colspan="2">Actions</th>
			</tr>
		</thead>
		<tbody>
			<% int i = 1; %>
			<c:forEach items="${paintings}" var="painting">
				<tr class="table-row">
					<td><c:out value="<%=i++ %>" /></td>
					<td><c:out value="${painting.name}" /></td>
					<td><img src="${painting.imageData}" /></td>
					<td><a
						href="PaintingController?action=edit&id=<c:out value="${painting.id}"/>">Update</a></td>
					<td><a
						href="PaintingController?action=delete&id=<c:out value="${painting.id}"/>&userId=<%=userId%>" onclick="return confirm('Are you sure you want to delete &ldquo;${painting.name}&rdquo;?');">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<hr>

		<footer>
		<p>&copy; 2016 Marc de Vries</p>
		</footer>
	</div>
	<!-- /container -->
</body>
</html>