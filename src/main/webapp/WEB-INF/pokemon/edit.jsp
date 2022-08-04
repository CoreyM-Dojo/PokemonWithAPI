<!-- Here we have to import the Date class. -->
<!-- You will put the import in the first line of the jsp tag. Use the import attribute -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demo JSP</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/style.css" />
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">

		<h1 class="text-center">Who's that Pokemon?</h1>
		
		<form:form action="/pokemon/${id}" method="POST" modelAttribute="pte">
			<input type="hidden" name="_method" value="put" />
			<div class="row">
				<div class="col-3">
					<form:label path="name" class="form-label">Name</form:label>
				</div>
				<form:errors path="name" />
				<div class="col-6">
					<form:input path="name" class="form-control"/>
				</div>
			</div>
			<div class="row">
				<div class="col-3">
					<form:label path="generation" class="form-label">Generation</form:label>
				</div>
				<form:errors path="generation" />
				<div class="col-6">
					<form:select path="generation" class="form-control">
							<form:options items="${gens}"></form:options>
					</form:select>
				</div>
			</div>
			<div class="row">
				<div class="col-3">
					<form:label path="type1" class="form-label">Type 1</form:label>
				</div>
				<form:errors path="type1" />
				<div class="col-6">
					<form:select path="type1" id="" class="form-control">
							<form:options items="${types}"></form:options>
					</form:select>
				</div>
			</div>
			<div class="row">
				<div class="col-3">
					<form:label path="type2" class="form-label">Type 2</form:label>
				</div>
				<form:errors path="type2" />
				<div class="col-6">
					<form:select path="type2" id="" class="form-control">
						<c:forEach var="type" items="${types}">
							<option value="${type}">${type}</option>
						</c:forEach>
					</form:select> <input type="submit" value="Submit" class="btn btn-primary" />
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>