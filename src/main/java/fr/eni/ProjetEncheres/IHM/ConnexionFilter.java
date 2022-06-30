package fr.eni.ProjetEncheres.IHM;

import java.io.IOException;

	import javax.servlet.DispatcherType;
	import javax.servlet.Filter;
	import javax.servlet.FilterChain;
	import javax.servlet.FilterConfig;
	import javax.servlet.ServletException;
	import javax.servlet.ServletRequest;
	import javax.servlet.ServletResponse;
	import javax.servlet.annotation.WebFilter;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	@WebFilter(
	        urlPatterns="/ServletConnexion",
	        dispatcherTypes= {
	                DispatcherType.REQUEST,
	                DispatcherType.INCLUDE,
	                DispatcherType.FORWARD,
	                DispatcherType.ERROR
	        }
	)
	public class ConnexionFilter implements Filter{

	    @Override
	    public void init(FilterConfig filterConfig) throws ServletException {
	    }

	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	        if(!httpRequest.getServletPath().toLowerCase().contains("interdit"))
	        {
	            //Laissons passer la requête vers la ressource qui est autorisée
	            chain.doFilter(request, response);
	        }
	        else
	        {
	            //renvoyons une 403 à l'utilisateur
	            HttpServletResponse httpResponse = (HttpServletResponse) response;
	            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
	        }


	    }

	    @Override
	    public void destroy() {
	    }

	
}
