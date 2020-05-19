<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mostrar Filme</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<header>
		<c:import url="Menu2.tag"/>
	</header>
	<div class="container">
		<h1>Filme</h1>
		<p class="lead"> Digite o código do filme para mostrar seus dados.</p>
		<div class="form-inline">
			<form class="form-inline" action="manter_filmes.do" method="GET">
				<label>Id do Filme:</label>
				<input type="number" class="form-control" name="id_filme" required/>
				<input type="submit" name="acao" value="mostrar" class="btn btn-default">
			</form>
		</div>
	</div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min (2).js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>