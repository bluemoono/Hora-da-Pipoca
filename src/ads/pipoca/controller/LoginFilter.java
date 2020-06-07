package ads.pipoca.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ads.pipoca.model.entity.Usuario;

@WebFilter("/*")
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		Usuario logado = (Usuario)session.getAttribute("logado");
		String path = req.getContextPath();
		String uri = req.getRequestURI();
		String acao = req.getParameter("acao");
		System.out.println("Filter: Path: "+path);
		System.out.println("Filter: URI: "+uri);
		
		
		if(acao == null) {
			acao = "";
		}
		if(logado != null || uri.equals(path+"/Login.jsp") || acao.equals("btn/login-de-login-jsp")
				|| uri.endsWith(".js") || uri.endsWith(".css") || uri.contains("glyphicons")) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(path+"/Login.jsp");
		}
		
	}

}
