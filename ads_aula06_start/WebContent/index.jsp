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
<title>Hora da Pipoca</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<header>
		<c:import url="Menu2.tag"/>
	</header>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h2 class="text-center">POP * Time</h2>
				<h4 class="text-center">Sua hora de diversão</h4>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12" align="center">
				<img src="img/hora_pipoca.jpg" class="img-responsive">
			</div>
		</div>
	</div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min (2).js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>