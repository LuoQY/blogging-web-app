<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
<c:import url= "header.jsp" ></c:import>
<div id="wrapper">
	<c:import url = "navigation.jsp"></c:import>
	<div id="content-wrapper">
			<div class="container-fluid">
				<div class="card card-login mx-auto mt-5">
					<div class="card-header">Register</div>
					<div class="card-body">
						<c:forEach var="error" items="${form.formErrors}">
							<h6 style="color:red"> ${error} </h6>
						</c:forEach>
							
						<form action="Register.do" method="POST">
							<c:forEach var="field" items="${form.hiddenFields}">
					            <input type="${field.type}" name="${field.name}" value="${field.value}"/>
				            </c:forEach>
				            <c:forEach var="field" items="${form.visibleFields}">
								<div class="form-group">
									<div class="form-label-group">
										<c:if test="${field.name == 'confirmPassword' }">
											<input type="${field.type}" name="${field.name}" id="${field.name}" class="form-control" placeholder="${field.label}" required="required" autofocus="autofocus">
											<label for="${field.name}">${field.label}</label>
										</c:if>
										<c:if test="${field.name != 'confirmPassword' }">
											<input type="${fn:toLowerCase(field.name)}" name="${field.name}" id="${field.type}" class="form-control" placeholder="${field.label}" required="required" autofocus="autofocus" value="${field.value}">
											<label for="${field.type}">${field.label}</label>
										</c:if>
										<div><h6 style="color:red">${field.error}</h6></div>
									</div>
								</div>
							</c:forEach>
							
							<input type="submit" name="button" class="btn btn-primary btn-block" value="Submit">
						</form>
					</div>
				</div>
			</div>
		</div>
</div>

</body>
</html>