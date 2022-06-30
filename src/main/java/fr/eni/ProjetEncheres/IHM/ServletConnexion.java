package fr.eni.ProjetEncheres.IHM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetEncheres.BLL.BLLException;
import fr.eni.ProjetEncheres.BLL.UtilisateurManager;
import fr.eni.ProjetEncheres.BO.Utilisateur;
import fr.eni.ProjetEncheres.DAL.DALException;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/Connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UtilisateurManager utilisateurManager;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/pages/Connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String identifiant;
		String mdp;
		Utilisateur user;
		List<String>listError = new ArrayList<>();
		boolean autoriser;
		
		
		
		identifiant = request.getParameter("Pseudo");
		mdp = request.getParameter("mdp");
		
		//try {
			
			try {
			user = utilisateurManager.connexionUser(identifiant, mdp);
			if (user!=null) {
				autoriser = true;
				response.sendRedirect(request.getContextPath() + "/Accueil");
			}else {
				autoriser=false;
				listError.add("pas compte reconnu, veuillez verifier votre identifiant.");
				response.sendRedirect(request.getContextPath() + "/Connexion");
				
			}
			} catch ( BLLException e) {
				// TODO Auto-generated catch block
				
				throw new RuntimeException ("");
			}
			
			if (identifiant.trim().isEmpty() || mdp.trim().isEmpty() ) {
				listError.add ("identifiant ou Mot de passe non renseignés"); 
			}
		
			
			
			
		//}
		
		request.getRequestDispatcher("/WEB-INF/pages/Connexion.jsp").forward(request, response);
	
	
		}
}

