package ads.pipoca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.pipoca.model.entity.Filme;
import ads.pipoca.model.service.FilmeService;

@WebServlet("/comprar_filmes.do")
public class ComprarFilmesController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "unchecked", "unused", "null" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String acao = request.getParameter("acao");
		FilmeService FService = new FilmeService();
		String saida="index.jsp";
		HttpSession session = request.getSession();
		ArrayList<Filme> carrinho = null;
		
		//excluir, visualizar
		String id_filme = null;
		int idFilme = -1;
		Filme filme = null;
		String par = null;
		ArrayList<Integer> listaIds = null;
		String[] vals = null;
		Enumeration<String> pars = null;
		
		switch (acao) {
		case "btn/comprar-de-exibirFilmes-jsp":
			ArrayList<Integer> lista = obterIds(request);
			ArrayList<Filme> filmes = FService.listarFilmes(lista);
			//pegar carrinho na seção e concatena
			Object aux = session.getAttribute("filmes");//cast para session retorna objetos(variaveis objetos)
			
			if(aux != null && aux instanceof ArrayList<?>) {
				carrinho = (ArrayList<Filme>)aux;
				if(carrinho.size() > 0) {
					for(Filme f:filmes) {
						carrinho.add(f);
					}
				}else {
					carrinho = filmes;
				}
			}else{
				carrinho = filmes;
			}
			session.setAttribute("filmes", carrinho);
			saida = "Carrinho.jsp";
			break;
		case "menu/comprarFilmes-de-menu-jsp":
			filmes = FService.listarFilmes();
			request.setAttribute("filmes", filmes);
			saida = "ExibirFilmes.jsp";
			break;
			
		//excluir
		case "btn/excluir-de-modal-carrinho-jsp":
			listaIds = new ArrayList<>();
			filmes = new ArrayList<>();
			try {
				while ((filme != null)) {
					if (filme.startsWith("box")) {
						if (vals != null && vals.length > 0 && vals[0].equals("on")) {
							listaIds.add(Integer.parseInt(par.substring(3)));
						}
					}
				}
			} catch(NoSuchElementException nsee) {
			}
			System.out.println("Excluir listaIds = "+ id_filme);
			for(int idDel: listaIds) {
				try {
					if(listaIds.listIterator() != null) {
					  listaIds.remove(idDel);
					System.out.println("Excluir listaIds = "+idDel);
					}
				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();
					filme = null;
				}
			}
			request.setAttribute("filmes", filmes);
			saida = "Carrinho.jsp";
			break;
		case "btn/excluir-de-carrinho-jsp":
			id_filme = request.getParameter("id_filme");
			try {
				idFilme = Integer.parseInt(id_filme);
				System.out.println(id_filme);
			} catch(NumberFormatException nfe) {
				nfe.printStackTrace();
				filme = null;
			}
			filmes = new ArrayList<>();
			filmes.add(filme);
			request.setAttribute("filmes", filmes);
			saida = "Carrinho.jsp";
			break;
			
			//visualizar
		case "btn/visualizar-de-exibirFilmes-jsp":
			pars = request.getParameterNames();
			listaIds = new ArrayList<>();
			try {
				while ((par = pars.nextElement()) != null) {
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
		case "btn/mostrar-de-mostrarFilmeCarrinho-jsp":
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
			filme = FService.buscarFilme(idFilme);
			System.out.println(filme);
			request.setAttribute("filme", filme);
			saida = "Filme.jsp";
			break;
		case "comprar_filmes.do?acao=menu/seuCarrinho-de-menu-jsp":
			filmes = FService.listarFilmes();
			request.setAttribute("filme", filme);
			saida = "Carrinho.jsp";
			break;
			
		//botao finalizar
		case "btn/comprar-de-carrinho-jsp":
			pars = request.getParameterNames();
			listaIds = new ArrayList<>();
			filmes = new ArrayList<>();
			try {
				while ((par = pars.nextElement()) != null) {
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
			System.out.println("Excluir listaIds = "+listaIds);
			for(int idDel: listaIds) {
				try {
					filmes = FService.listarFilmes();
					filmes.add(filme);
					System.out.println(filme);
				} catch(NumberFormatException nfe) {
					nfe.printStackTrace();
					filme = null;
				}
			}
			request.setAttribute("filmes", filmes);
			saida = "FilmeFinalizao.jsp";
			break;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(saida);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private ArrayList<Integer> obterIds(HttpServletRequest request){
		Enumeration<String> pars = request.getParameterNames();
		ArrayList<Integer> listaIds = new ArrayList<>();
		String par = null;
		String[] vals = null;
		try {
			while ((par = pars.nextElement()) != null) {
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
		return listaIds;
	}
	
}
