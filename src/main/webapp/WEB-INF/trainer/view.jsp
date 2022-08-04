<!-- Here we have to import the Date class. -->
<!-- You will put the import in the first line of the jsp tag. Use the import attribute -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${poke.getName()}</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="bg-dark">
	<div class="container bg-dark text-danger">
		<a href="/">Home</a>
		<div>
			<h1>${trainer.getName()}</h1>
			<div class="d-flex">
				<a class="btn btn-warning me-4" href="/trainer/edit/${trainer.id}">Edit</a>
				<form action="/trainer/${trainer.id}" method="post">
					<input type="hidden" name="_method" value="delete" />
					<button class="btn btn-danger">Delete</button>
				</form>
			</div>
			<table class="table table-dark table-sm">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Name</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="p" items="${trainer.team}">
						<tr>
							<th scope="row">${trainer.team.indexOf(p) + 1}</th>
							<td>${p.name }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:choose>
				<c:when test="${uncollected.size() > 0 }">
					<form action="/trainer/addBadge/${trainer.id}" method="post">
						<select name="badgeId">
							<c:forEach var="b" items="${uncollected}">
								<option value="${b.id }">${b.name}</option>
							</c:forEach>
						</select> <input type="submit" value="Add" class="btn btn-primary" />
					</form>
				</c:when>
				<c:otherwise>
					<h2 class="text-danger">All badges collected</h2>
				</c:otherwise>
			</c:choose>
			<%-- <form action="/trainer/addBadge/${trainer.id}" method="post">
				<select name="badgeId">
					<c:forEach var="b" items="${uncollected}">					
						<option value="${b.id }">${b.name}</option>
					</c:forEach>
				</select> <input type="submit" value="Add" class="btn btn-primary" />
			</form> --%>

		</div>
		<table class="table table-dark table-sm">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Badge</th>
					<th scope="col">Location</th>
					<th scope="col">Actions</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="b" items="${trainer.badges}">
					<tr>
						<th scope="row">${trainer.badges.indexOf(b) + 1}</th>
						<td>${b.name }</td>
						<td>${b.location}</td>
						<td><a href="/trainer/removeBadge/${trainer.id }/${b.id}"
							class="btn btn-danger">Remove</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>