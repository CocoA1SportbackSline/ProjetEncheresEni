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
}
/*	private UtilisateurManager utilisateurManager;

	private static final long serialVersionUID = 1L;


	public void init() throws ServletException {
		utilisateurManager  = UtilisateurManager.getInstance();
    	super.init();
    }

    public ServletSinscrire() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/sinscrire.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		List<String> listeErreurs = new ArrayList<>();
		
		String pseudo = request.getParameter("pseudo");
		if (pseudo!=null || !request.getParameter(pseudo).isEmpty()) {
			request.setAttribute("pseudoform", pseudo);
		}
		
		String nom = request.getParameter("nom");
		if(nom != null || !request.getParameter("nom").isEmpty());{
			request.setAttribute("nomform", nom);
		}
		
		String prenom = request.getParameter("prenom");
		if(prenom != null || !request.getParameter("prenom").isEmpty());{
			request.setAttribute("prenomform", prenom);
		}
		
		String email = request.getParameter("email");
		if(email != null || !request.getParameter("email").isEmpty());{
			request.setAttribute("emailform", email);
		}
		String telephone = request.getParameter("telephone");
		if(telephone != null || request.getParameter("email").isEmpty()); {
			request.setAttribute("telephoneform",telephone);
		}
		String rue = request.getParameter("rue");
		if(rue != null || request.getParameter("rue").isEmpty()); {
			request.setAttribute("rueform", rue);
		}
		 String Postal = request.getParameter("codePostal");
			 if(Postal != null  || request.getParameter(Postal).isEmpty());
				 try {
					 int codePostal = Integer.parseInt(Postal);
					 request.setAttribute("codePostalform", codePostal);
			 }catch(Exception e) {
				 
			 }
				 String mdp = request.getParameter("mdp");
				 if(mdp !=null || request.getParameter(mdp).isEmpty());
				 request.setAttribute("mdpform", mdp);
				 
				 String ville = request.getParameter("ville");
				 if(ville !=null || request.getParameter("ville").isEmpty());
				 request.setAttribute("villeform", ville);
				 
				 
				 if(!pseudo.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && 
						 !telephone.isEmpty() && rue.isEmpty() && !Postal.isEmpty()){
					 
				 }
	
		 }
		
	}*/

