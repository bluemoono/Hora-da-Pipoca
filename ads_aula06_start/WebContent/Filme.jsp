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
<title>Filme</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<header>
		<c:import url="Menu2.tag"/>
	</header>
	<div class="container">
		<c:if test="${empty filme}">
			<div class="row">
				<h3 class="bg-warning text-center">Filme não encontrado</h3>
			</div>
		</c:if>
		<div class="row">
			<div class="col-sm-3 xs-6">
				<img src="${filme.posterPath}" class="img-responsive">
			</div>
			<c:if test="${not empty filme}">
				<div class="col-xs-9">
					<fmt:setLocale value="pt_BR"/>
					<h1>Filme id: ${filme.id}</h1>
					<p><strong>Título: </strong>${filme.titulo}</p>
					<p><strong>Descrição: </strong>${filme.descricao}</p>
					<p><strong>Diretor: </strong>${filme.diretor}</p>
					<p><strong>Gênero: </strong>${filme.genero.nome}</p>
					<p><strong>Lançamento: </strong>
					<fmt:formatDate value="${filme.dataLancamento}" dateStyle="FULL"/></p>
					<p><strong>Popularidade: </strong>
					<fmt:formatNumber value="${filme.popularidade}" minFractionDigits="2"
					maxFractionDigits="2"/>
					</p>
				</div>
			</div>
		</c:if>
	</div>
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min (2).js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>