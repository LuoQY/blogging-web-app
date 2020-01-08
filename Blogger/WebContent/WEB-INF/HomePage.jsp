<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
				<c:forEach var="error" items="${form.formErrors}">
					<p class="error"> ${error} </p>
			    </c:forEach>
			
				<h1>Welcome to ${user.firstName} ${user.lastName}'s Home Page</h1>
				<hr>
				
				<c:forEach var="post" items="${posts}">
					<c:if test="${post.email eq user.email}">
						<div class="card mb-3">
							<div class="card-header">
								<div class="row">
									<div class="col align-self-center">
										<div class="small text-muted">
											Updated <fmt:formatDate type = "both" value = "${post.time}" />
										</div>
									</div>
									<form action="Deletepost.do" method="POST">
										<div class="col d-flex">
											<div class="ml-auto">
												<input type="hidden" name="postid" value="${post.id }" />
												<button class="btn btn-dark btn-sm" type="submit"> Delete </button>
											</div>
										</div>
									</form>
								</div>
							</div>
							<div class="card-body">
								<p>${post.content}</p>
							</div>
							<div class="card-footer small text-muted">
								<c:forEach var="comment" items="${comments}">
									<c:if test="${comment.postId eq post.id}">
										<div class="row">
											<div class="col-1">
												<form action="Deletecomment.do" method="POST">
													<input name="commentid" type="hidden" value="${comment.id}" />
													<button class="btn btn-outline-dark btn-sm" type="submit"> X </button>
												</form>
											</div>
											<div class="col align-self-center">
												Comment by <a class="text-dark" href="#">${comment.firstName} ${comment.lastName}</a> :  ${comment.content} 
												<span class="font-italic">&nbsp;&nbsp;&nbsp;--<fmt:formatDate type = "both" value = "${comment.time}" /></span>
											</div>
										</div>
										<hr>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</c:if>
				</c:forEach>

				
				<hr>
				<form method="POST" action="Newpost.do">
					<c:forEach var="field" items="${form.visibleFields}">
						<div class="row">
							<div class="col">
								<label>${field.label}</label>
							</div>
							<div class="col-9">
								<textarea rows="4" name="${field.name}" class="form-control">${field.value}</textarea>
							</div>
							<div><h6 style="color:red">${field.error}</h6></div>
							<div class="col align-self-end">
								<button type="submit" name="submit" class="btn btn-primary">Submit</button>
							</div>
						</div>
					</c:forEach>
				</form>
			</div>
		</div>
</div>

</body>
</html>