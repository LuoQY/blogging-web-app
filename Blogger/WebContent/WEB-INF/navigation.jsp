<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="customize.css">

<title>MicroBlogger</title>
</head>
<body>
<div id="wrapper">
	<ul class="sidebar navbar-nav">
		<li class="nav-item">
			<c:if test="${(empty sessionScope.user)}">
				<form action="Login.do" method="GET">
					<button type="submit" class="nav-link btn">Login</button>
				</form>
			</c:if>
			<c:if test="${!(empty sessionScope.user)}">
				<form action="Homepage.do" method="GET">
					<button type="submit" class="nav-link btn">Home</button>
				</form>
			</c:if>
		</li>
		<li class="nav-item">
			<c:if test="${(empty sessionScope.user)}">
				<form action="Register.do" method="GET">
					<button type="submit" class="nav-link btn" >Register</button>
				</form>
			</c:if>
			<c:if test="${!(empty sessionScope.user)}">
			    <form method="POST" action="Logout.do">
					<button type="submit" class="nav-link btn">Logout</button>
				</form>
			</c:if>
		</li>
		<div class="dropdown-divider"></div>
		<c:forEach var="item" items="${sessionScope.allusers}">
			<c:if test="${item.email eq sessionScope.user.email}">
		 		<li class="nav-item">
		 			<form action="Homepage.do" method="GET">
						<input type="submit" name="userButton" class="nav-link btn" value="${item.firstName} ${item.lastName}">
					</form>
				</li>
			</c:if>
			<c:if test="${(item.email ne sessionScope.user.email) && !(empty sessionScope.user)}">
				 <li class="nav-item">
				 	<form action="Loggedin.do" method="POST">
				 		<input type="hidden" name="email" value="${item.email }" />
						<input type="submit" name="userButton" class="nav-link btn" value="${item.firstName} ${item.lastName}">
					</form>
				</li>
			</c:if>
			<c:if test="${(item.email ne sessionScope.user.email) && (empty sessionScope.user)}">
				<li class="nav-item">
				 	<form action="Notloggedin.do" method="POST">
				 	<input type="hidden" name="email" value="${item.email }" />
						<input type="submit" name="userButton" class="nav-link btn" value="${item.firstName} ${item.lastName}">
					</form>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</div>

</body>
</html>