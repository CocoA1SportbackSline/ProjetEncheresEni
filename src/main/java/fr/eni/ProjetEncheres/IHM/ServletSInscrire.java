package fr.eni.ProjetEncheres.IHM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetEncheres.BLL.UtilisateurManager;



public class ServletSInscrire extends HttpServlet{
	

	/**
	 * 
	 */
	/**private static final long serialVersionUID = 1L;


	public void init() throws ServletException {
		utilisateurManager  = UtilisateurManager.getInstance();
    	super.init();
    }

    public ServletSinscrire() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response)); 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sinscrire.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.setAttribute("title", getPageName(request, response)); 
		
		List<String> listeErreurs = new ArrayList<>();
		
		String pseudo = request.getParameter("pseudo");
		if (pseudo!=null || !request.getParameter(pseudo).isEmpty()) {
			request.setAttribute("spseudo", pseudo);
		}
		
		String nom = request.getParameter("nom");
		if(nom != null || !request.getParameter("nom").isEmpty());{
			request.setAttribute("snom", nom);
		}
		
		String prenom = request.getParameter("prenom");
		if(prenom != null || !request.getParameter("prenom").isEmpty());{
			request.setAttribute("sprenom", prenom);
		}
		
		String email = request.getParameter("email");
		if(email != null || !request.getParameter("email").isEmpty());{
			request.setAttribute("semail", email);
		}
		String telephone = request.getParameter("telephone");
		if(telephone != null || request.getParameter("email").isEmpty()); {
			request.setAttribute("telephone",telephone);
		}
		String rue = request.getParameter("rue");
		if(rue != null || request.getParameter("rue").isEmpty()); {
			request.setAttribute("srue", rue);
		}
		 int codePostal = request.getParameter("codePostal");
			 if(codePostal.equals  || request.getParameter(codePostal));{
				 
			 }
		 }
		
	}
<<<<<<< HEAD
**/
}
=======


>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
