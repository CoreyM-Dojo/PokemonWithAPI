<!-- Here we have to import the Date class. -->
<!-- You will put the import in the first line of the jsp tag. Use the import attribute -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${poke.get("name")}</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<link rel="stylesheet" href="/css/main.css" />
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body class="bg-dark">
	<div class="card bg-dark text-danger d-flex flex-column ">
		<div class="card-header text-warning bg-danger d-flex justify-content-between align-items-center">
			<h2>Who's that pokemon?</h2>
			<a href="/">Home</a>
		</div>
		<div class="card-body">
			<div class="d-flex flex-column align-items-center justify-content-center">			
				<h1 class="card-title">${poke.get("name").substring(0,1).toUpperCase().concat(poke.get("name").substring(1))}</h1>
				<img width=300 src="${poke.get('sprites').get('front_default') }" alt="" />
			</div>
			<h3>Abilities</h3>
			<div class="col-5 mb-3">			
				<select class="form-control bg-dark text-info">			
					<c:forEach var="ab" items="${abilities }">
						<option>${ab.get("ability").get("name")}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-5 align-self-center bd-highlight">
				<table class="table  table-sm text-success">
					<thead>
						<tr>
							<th scope="col">Stat</th>
							<th scope="col">Value</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="st" items="${stats}">
							<tr>
								<th scope="row">${st.get("stat").get("name")}</th>
								<th>${st.get("base_stat")}</th>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>

		</div>

		<div class="card-footer text-muted">2 days ago</div>
	</div>
</body>
</html>