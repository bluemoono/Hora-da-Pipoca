<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

	<!-- Modal -->
	<div class="modal fade" id="delete-modal" tabindex="-1" role="dialog">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">Excluir Filme</h4>
      </div>
      <div class="modal-body">
        <p>Você realmente deseja continuar a exclusão do filme <strong>${filme.titulo}</strong>? &hellip;</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
        <a href="manter_filmes.do?acao=excluir&id_filme=${filme.id}" class="btn btn-danger">Excluir</a>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

	<header>
		<c:import url="Menu2.tag" />
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
					<fmt:setLocale value="pt_BR" />
					<h1>Filme id: ${filme.id}</h1>
					<p>
						<strong>Título: </strong>${filme.titulo}</p>
					<p>
						<strong>Descrição: </strong>${filme.descricao}</p>
					<p>
						<strong>Diretor: </strong>${filme.diretor}</p>
					<p>
						<strong>Gênero: </strong>${filme.genero.nome}</p>
					<p>
						<strong>Lançamento: </strong>
						<fmt:formatDate value="${filme.dataLancamento}" dateStyle="FULL" />
					</p>
					<p>
						<strong>Popularidade: </strong>
						<fmt:formatNumber value="${filme.popularidade}"
							minFractionDigits="2" maxFractionDigits="2" />
					</p>
				</div>
		</div>
		<hr>
		<div id="action" class="row">
			<div class="col-md-12">
				<a href="manter_filmes.do?acao=editar&id_filme=${filme.id}" class="btn btn-primary">Editar</a> 
				<a href="#" class="btn btn-danger" data-toggle="modal" data-target="#delete-modal">Excluir</a> 
				<a href="index.jsp" class="btn btn-default">Voltar</a>
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