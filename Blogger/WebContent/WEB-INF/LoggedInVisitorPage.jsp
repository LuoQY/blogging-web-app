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
			<c:if test="${!(empty error)}">
				<script>
					alert("${error}")
				</script>
			</c:if>
			<div class="container-fluid">
				<h1>${sessionScope.blogger.firstName} ${sessionScope.blogger.lastName}'s Blog</h1>
				<hr>
				
				<c:forEach var="post" items="${posts}">
					<c:if test="${post.email eq sessionScope.blogger.email}">
						<div class="card mb-3">
							<div class="card-header">
								<div class="row">
									<div class="col align-self-center">
										<div class="small text-muted">
											Updated <fmt:formatDate type = "both" value = "${post.time}" />
										</div>
									</div>
								</div>
							</div>
							<div class="card-body">
								<p>${post.content}</p>
							</div>
							<div class="card-footer small text-muted">
								<c:forEach var="comment" items="${comments}">
									<c:if test="${comment.postId eq post.id}">
							
										<div class="row">
											<c:if test="${comment.email eq user.email }">
												<form action="Deletecomment.do" method="POST">
													<input name="commentid" type="hidden" value="${comment.id}" />
													<div class="col-1">
														<button class="btn btn-outline-dark btn-sm" type="submit"> X </button>
													</div>
												</form>
											</c:if>
											<div class="col align-self-center">
												Comment by <a class="text-dark" href="#">${comment.firstName} ${comment.lastName}</a> :  ${comment.content}
												<span class="font-italic">&nbsp;&nbsp;&nbsp;--<fmt:formatDate type = "both" value = "${comment.time}" /></span>
											</div>
										</div>
										<hr>
									</c:if>
								</c:forEach>
								<form action="Newcomment.do" method="POST">
									<div class="row">
											<input name="postid" type="hidden" value="${post.id}" />
											<div class="col-10">
												<textarea rows="1" name="comment" class="form-control"></textarea>
											</div>
											<div class="col align-self-end">
												<button type="submit" class="btn btn-primary">Comment</button>
											</div>
									</div>
								</form>
							</div>
		
						</div>
					</c:if>
				</c:forEach>

			</div>
		</div>
</div>

</body>
</html>