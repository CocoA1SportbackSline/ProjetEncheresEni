package fr.eni.ProjetEncheres.IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletDeconnexion
 */
@WebServlet("/ServletDeconnexion")
public class ServletDeconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDeconnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	         
        // récupère la session en cours
        HttpSession session=request.getSession();  
        // TODO mettre un href vers servlet deconnexion sur le lien clickable "déconnexion" page listEncheresConnecté
        // TODO mettre un href vers servlet deconnexion sur le lien clickable "déconnexion" page listeEncheresMsVentes
       
        // supprime la session
        session.invalidate();  
        // redirige vers la page de connexion
        response.sendRedirect("/WEB-INF/pages/Connexion.jsp")  ;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
