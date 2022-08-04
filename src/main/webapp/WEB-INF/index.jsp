<!-- Here we have to import the Date class. -->
<!-- You will put the import in the first line of the jsp tag. Use the import attribute -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Demo JSP</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="bg-dark">
	<div class="container bg-dark">
		<div class="card bg-dark">
			<div class="card-header text-warning bg-danger">
				<h2>Whos that pokemon?</h2>
				<form action="/pokemon/search">
					<input class="form-control" type="text" name="pokemon" />
				</form>
			</div>
			<div class="card-body">
				<a href="/pokemon/new" class="btn btn-success">Add Pokemon</a> 
				<a href="/trainer/new" class="btn btn-success">Add Trainer</a>
				<a href="/badge/new" class="btn btn-success">Add Badge</a>
			</div>

			<div class="card-footer text-muted">2 days ago</div>
		</div>
		
	</div>
</body>
</html>