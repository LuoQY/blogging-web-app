<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MicroBlogger</title>
</head>
<body>
<c:import url= "header.jsp" ></c:import>
<div id="wrapper">
	<c:import url = "navigation.jsp"></c:import>
	<div id="content-wrapper">
			<div class="container-fluid">
				<div class="card card-login mx-auto mt-5">
					<div class="card-header">Login</div>
					<div class="card-body"> 
						
						<c:forEach var="error" items="${form.formErrors}">
							<h6 style="color:red"> ${error} </h6>
						</c:forEach>
						
						<form action="Login.do" method="POST">
							<c:forEach var="field" items="${form.hiddenFields}">
		            			<input type="${field.type}" name="${field.name}" value="${field.value}"/>
	            			</c:forEach>
	            			
	            			<c:forEach var="field" items="${form.visibleFields}">
								<div class="form-group">
									<div class="form-label-group">
										<input type="${field.name}" name="${field.name}" id="input${field.label}" class="form-control" placeholder="${field.label}" required="required" autofocus="autofocus" value="${field.value}">
										<label for="input${field.label}">${field.label}</label>
									</div>
								</div>
								<div><h6 style="color:red">${field.error}</h6></div>
							</c:forEach>
							
							<input type="submit" name="button" value="Submit" class="btn btn-primary btn-block">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>