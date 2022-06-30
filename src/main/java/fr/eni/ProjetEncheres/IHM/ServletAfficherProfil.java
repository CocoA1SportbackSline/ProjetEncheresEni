package fr.eni.ProjetEncheres.IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetEncheres.BLL.UtilisateurManager;
import fr.eni.ProjetEncheres.BO.Utilisateur;

/**
 * Servlet implementation class ServletAfficherProfil
 */
@WebServlet("/ServletAfficherProfil")
public class ServletAfficherProfil extends HttpServlet {

	private static final long serialVersionUID = 1L;
   
	private UtilisateurManager utilisateurMger;
	
	public void init () throws ServletException {
		utilisateurMger = UtilisateurManager.getInstance();
		super.init();
	}
	
    public ServletAfficherProfil() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//affiche la page 
		request.setAttribute(getServletName(), response);
	/*	String pseudoUtil = request.getParameter("pseudo");
		try {
			Utilisateur u = utilisateurMger.postUser(u.getNoUtilisateur());

			request.setAttribute("pizza", p);
			System.out.println("Pizza: " + p);

			request.setAttribute("piutilisateurs", utilisateurMger.getAllPizzas());
		} catch (BLLException e) {
			req.setAttribute("erreur", "Une erreur inattendue est survenue !!");
			e.printStackTrace();
		}*/

		request.getRequestDispatcher("/WEB-INF/pages/AfficherProfil.jsp").forward(request, response);
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/AfficherProfil.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");*/
		
	
		 
		
	}
}

