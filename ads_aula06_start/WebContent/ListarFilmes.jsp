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
<title>Lista de Filmes</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<header>
		<c:import url="Menu2.tag" />
	</header>
	<div class="container">
		<div class="row">
			<h1>Lista de Filmes</h1>
			<hr>
		</div>

		<div class="row">
			<form action="manter_filmes.do" method="POST">

				<!-- Modal -->
				<div class="modal fade" id="delete-modal" tabindex="-1"
					role="dialog">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title">Excluir Filme</h4>
							</div>
							<div class="modal-body">
								<p>Você realmente deseja continuar a exclusão do
									filme?&hellip;</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Cancelar</button>
								<input type="submit" name="acao" value="Excluir"
									class="btn btn-danger">
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->

				<table class="table table-striped">
					<thead>
						<tr>
							<th>&nbsp;</th>
							<th>Pôster</th>
							<th>Id</th>
							<th>Título</th>
							<th>Genero</th>
							<th>Diretor</th>
							<th>Lançamento</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="filme" items="${filmes}">
							<tr>
								<td>
									<div class="checkbox">
										<label> 
											<input type="checkbox" name="box${filme.id}" onClick="tratarBotoes(this)">
										</label>
									</div>
								</td>
								<td><img src="${filme.posterPath}" class="img-thumbnail"
									width="70"></td>
								<td>${filme.id}</td>
								<td>${filme.titulo}</td>
								<td>${filme.genero.nome}</td>
								<td>${filme.diretor}</td>
								<td><fmt:formatDate value="${filme.dataLancamento}"
										dateStyle="SHORT" /></td>
								<td>
								<div class="row">
									<div class="col-md-4">
										<label> 
											<a href="manter_filmes.do?acao=mostrar&id_filme=${filme.id}" class="btn btn-info" onClick="tratarBotoes(this)">Visualizar</a>
										</label>
									</div>
									<div class="col-md-2">
										<label> 
											<a href="manter_filmes.do?acao=editar&id_filme=${filme.id}" class="btn btn-primary" onClick="tratarBotoes(this)">Editar</a>
										</label>
									</div>
									
								</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
		<hr>
		<div class="row">
			<div class="col-d-12">
				<input type="submit" id="btn-editar" name="acao" value="Editar"class="btn btn-primary" disabled> 
				<input type="submit" id="btn-visualizar" name="acao" value="Visualizar" class="btn btn-info" disabled> 
				<input type="button" id="btn-excluir" name="acao" data-toggle="modal" data-target="#delete-modal" class="btn btn-danger" value="Excluir"disabled> 
				<a href="index.jsp" class="btn btn-default">Voltar</a>
			</div>
		</div>
		</form>
		<br> <br> <br>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.min (2).js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
	
	<!-- Javascript dos botões -->
	<script>
		var ckboxes = [];
		function tratarBotoes(element){
			if(element.checked){
				ckboxes.push(element);
			} else {
				ckboxes.pop();
			}
			
			if (ckboxes.length == 0){
				document.getElementById("btn-editar").disabled = true;
				document.getElementById("btn-visualizar").disabled = true;
				document.getElementById("btn-excluir").disabled = true;
			} else if(ckboxes.length == 1){
				document.getElementById("btn-editar").disabled = false;
				document.getElementById("btn-visualizar").disabled = false;
				document.getElementById("btn-excluir").disabled = false;
			} else{
				document.getElementById("btn-editar").disabled = true;
				document.getElementById("btn-visualizar").disabled = true;
				document.getElementById("btn-excluir").disabled = false;
			}
		}
	</script>
</body>
</html>