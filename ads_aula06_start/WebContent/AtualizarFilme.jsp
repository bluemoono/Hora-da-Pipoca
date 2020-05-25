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
<h1>Atualizar Filme</h1>
<p>Atualize os dados do filme ${filme.id}</p>
	
	<form action="manter_filmes.do" method="POST">
  		<div class="form-group col-md-5">
    		<label>Título do Filme</label>
    		<input type="text" class="form-control" name="titulo" value="${filme.titulo}" required>
  		</div>

  		<div class="form-group col-md-5">
    		<label>Diretor:</label>
    		<input type="text" class="form-control" name="diretor" value="${filme.diretor}">
  		</div>
  		
  		<div class="form-group col-md-2">
    		<label>Gênero: </label>
    		<select class="form-control" name="genero" required>
				<c:forEach var="genero" items="${generos}">
				<c:if test="${genero.id eq filme.genero.id }">
					<option value="${genero.id}">${genero.nome}</option>
				</c:if>
				</c:forEach>
			</select>
  		</div>
  		
  		<div class="form-group col-md-8">
    		<label>Pôster:</label>
    		<input type="text" class="form-control" name="poster_path" value="${filme.posterPath}">
  		</div>
  		<div class="form-group col-md-2">
    		<label>Lançamento:</label>
    		<input type="date" class="form-control" name="data_lancamento" value="${filme.dataLancamento}">
  		</div>
  		<div class="form-group col-md-2">
    		<label>Popularidade:</label>
    		<input type="number" class="form-control" name="popularidade" value="${filme.popularidade}">
  		</div>
  		<div class="form-group col-md-6">
    		<label>Descrição:</label>
    		<textarea class="form-control" name="descricao">${filme.descricao}</textarea>
  		</div>
  		<div class="form-group col-md-12">
    		<button type="submit" class="btn btn-info"  name="acao" value="atualizar">Alterar</button>
  		</div>
	</form>
	</div>
	
	
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min (2).js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	
</body>
</html>