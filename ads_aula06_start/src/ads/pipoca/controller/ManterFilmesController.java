package ads.pipoca.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ads.pipoca.model.entity.Filme;
import ads.pipoca.model.entity.Genero;
import ads.pipoca.model.service.FilmeService;
import ads.pipoca.model.service.GeneroService;

@WebServlet("/manter_filmes.do")
public class ManterFilmesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		Filme filme = null;
		Genero genero = null;
		FilmeService fService = new FilmeService();
		GeneroService gService = new GeneroService();
		String saida = "";
		String id_filme = null;
		int idFilme = -1;
		Enumeration<String> pars = null;
		String par = null;
		ArrayList<Integer> listaIds = null;
		String[] vals = null;
		@SuppressWarnings("unused")
		ArrayList<Filme> filmes = null;
		switch (acao) {
		case "generos":
			ArrayList<Genero> generos = gService.listarGeneros();
			request.setAttribute("generos", generos);
			saida = "InserirFilme.jsp";
			break;
		case "listar":
			ArrayList<Filme> lista = fService.listarFlime();
			request.setAttribute("filmes", lista);
			saida = "ListarFilmes.jsp";
			break;
		case "mostrar":
			id_filme = request.getParameter("id_filme");
			idFilme = Integer.parseInt(id_filme);
			filme = fService.buscarFilme(idFilme);
			System.out.println(filme);
			request.setAttribute("filme", filme);
			saida = "Filme.jsp";
			break;
		case "inserir":
			String titulo = request.getParameter("titulo");
			String descricao = request.getParameter("descricao");
			String diretor = request.getParameter("diretor");
			String idGenero = request.getParameter("genero");
			String data = request.getParameter("data_lancamento");
			String popularidade = request.getParameter("popularidade");
			String posterPath = request.getParameter("poster_path");
			filme = new Filme();
			filme.setTitulo(titulo);
			filme.setDescricao(descricao);
			filme.setDiretor(diretor);
			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dataLanc = null;
			try {
				dataLanc = formater.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filme.setDataLancamento(dataLanc);
			filme.setPopularidade(Double.parseDouble(popularidade));
			filme.setPosterPath(posterPath);
			genero = gService.buscarGenero(Integer.parseInt(idGenero));
			filme.setGenero(genero);
			int id = fService.inserirFilme(filme);
			filme.setId(id);
			System.out.println(filme);
			request.setAttribute("filme", filme);
			saida = "Filme.jsp";
			break;
		case "Editar":
			pars = request.getParameterNames();
			listaIds = new ArrayList<>();
			try {
				
				while ((par  = pars.nextElement()) != null) {
					if (par.startsWith("box")) {
						System.out.println(par +" = "+Arrays.toString(request.getParameterValues(par)));
						vals = request.getParameterValues(par);
						if (vals != null && vals.length > 0 && vals[0].equals("on")) {
							listaIds.add(Integer.parseInt(par.substring(3)));
						}
					}
				}
			} catch(NoSuchElementException nsee) {
			}
			System.out.println("Editar listaIds = "+listaIds);
		case "editar":
			generos = gService.listarGeneros();
			request.setAttribute("generos", generos);
			id_filme = request.getParameter("id_filme");
			if (id_filme != null) {
				idFilme = Integer.parseInt(id_filme);
			} else {
				if (listaIds != null && listaIds.size() > 0) {
					idFilme = listaIds.get(0);
				} else {
					idFilme = -1;
				}
				
			} 
			filme = fService.buscarFilme(idFilme);
			request.setAttribute("filme", filme);
			System.out.println(filme);
			saida = "AtualizarFilme.jsp";
			break;
		case "atualizar":
			id_filme = request.getParameter("id_filme");
			idFilme = Integer.parseInt(id_filme);
			titulo = request.getParameter("titulo");
			descricao = request.getParameter("descricao");
		    diretor = request.getParameter("diretor");
			idGenero = request.getParameter("genero");
			data = request.getParameter("data_lancamento");
			popularidade = request.getParameter("popularidade");
			posterPath = request.getParameter("poster_path");
			filme = new Filme();
			filme.setId(idFilme);
			filme.setTitulo(titulo);
			filme.setDescricao(descricao);
			filme.setDiretor(diretor);
			formater = new SimpleDateFormat("yyyy-MM-dd");
			dataLanc = null;
			try {
				dataLanc = formater.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			filme.setDataLancamento(dataLanc);
			filme.setPopularidade(Double.parseDouble(popularidade));
			filme.setPosterPath(posterPath);
			genero = gService.buscarGenero(Integer.parseInt(idGenero));
			filme.setGenero(genero);
			System.out.println(filme);
			fService.alterarFilme(filme);
			request.setAttribute("filme", filme);
			saida = "Filme.jsp";
			break;
		case "excluir":
			id_filme = request.getParameter("id_filme");
			idFilme = Integer.parseInt(id_filme);
			fService.excluirFilme(idFilme);
			saida = "index.jsp";
			break;
		case "Excluir":
			System.out.println("Excluiu");
			Enumeration<String> pars1 = request.getParameterNames();
			ArrayList<Integer> listaIds1 = new ArrayList<>();
			String[] leitor;
			String variavel;
			try {
				while ((variavel = pars1.nextElement()) != null) {
					if (variavel.startsWith("box")) {
						leitor = request.getParameterValues(variavel);

						if (leitor != null && leitor.length > 0 && leitor[0].equals("on")) {
							listaIds1.add(Integer.parseInt(variavel.substring(3)));
						}
					}
				}
			} catch (NoSuchElementException semElemento) {

			}
			System.out.println("Lista de Ids: "+listaIds1);
			fService.excluirVariosFilmes(listaIds1);
			
		}

		RequestDispatcher view = request.getRequestDispatcher(saida);
		view.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
