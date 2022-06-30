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
		
		String identifiant=null;
		String mdp=null;
		List<String>listError = new ArrayList<>();
		
		
		if (identifiant.trim().isEmpty() || mdp.trim().isEmpty() ) {
			listError.add ("identifiant ou Mot de passe non renseignés"); 
		}
		
		identifiant = request.getParameter("Pseudo");
		mdp = request.getParameter("mdp");
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		//try {
			
			//utilisateurManager.connexionUser(identifiant, mdp);
			
		//}else {
			
			listError.add("pas compte sur cet identifiant, veuillez verifier votre identifiant.");
			getServletContext().getRequestDispatcher("/WEB-INF/pages/Connexion.jsp");
		//}
		
		request.getRequestDispatcher("/WEB-INF/pages/Connexion.jsp").forward(request, response);
	//} catch (DALException e) {
		
	//	throw new BLLException ("");
	//}
		}
}

