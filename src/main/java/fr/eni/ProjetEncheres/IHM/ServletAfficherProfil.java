package fr.eni.ProjetEncheres.IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.ProjetEncheres.BLL.BLLException;
import fr.eni.ProjetEncheres.BLL.UtilisateurManager;
import fr.eni.ProjetEncheres.BO.Utilisateur;

/**
 * Servlet implementation class ServletAfficherProfil
 */
@WebServlet("/ServletAfficherProfil")
public class ServletAfficherProfil extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UtilisateurManager utilisateurMger;

	public void init() throws ServletException {
		utilisateurMger = UtilisateurManager.getInstance();
		super.init();
	}

	public ServletAfficherProfil() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// affiche la page
		request.setAttribute(getServletName(), response);
		// si l'id de la jsp n'est pas vide
		if (!request.getParameter("id").isEmpty()) {
		// alors on fait la conversion du type dde noUtilisateur entre la BO et la BDD
			int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
		// rentrer dans try
			try {
			// d�finition classe utilisateur correspond � la m�thode postUser qui prend en param�tre un int
			// m�thode pr�sente dans la classe utilisateurManager
				Utilisateur utilisateur = utilisateurMger.postUser(noUtilisateur);
			// on modifie le param�tre utilisateur	
				request.setAttribute("utilisateur", utilisateur);
			// initialisation de la session	
				Utilisateur user = (Utilisateur) request.getSession().getAttribute("user");
			// si le pseudo qui est r�cup�r� correspond au pseudo de l'utilisateur
				if (user.getPseudo().equals(utilisateur.getPseudo())) {
					// renvoie la vue de l'autre profil
					this.getServletContext().getRequestDispatcher("/WEB-INF/pages/AutreProfil.jsp").forward(request,
							response);
				}
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // sinon renvoie la vue de mon profil
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/MonUtilisateur.jsp").forward(request,
					response);

		} else {
			request.getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
			

	}
}
