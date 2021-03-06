package fr.eni.ProjetEncheres.IHM;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ProjetEncheres.BLL.UtilisateurManager;
import fr.eni.ProjetEncheres.BO.Utilisateur;


@WebServlet("/SInscrire")
public class ServletSInscrire extends HttpServlet{


	private UtilisateurManager utilisateurManager;

	private static final long serialVersionUID = 1L;


	public void init() throws ServletException {
		utilisateurManager  = UtilisateurManager.getInstance();
    	super.init();
    }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/SInscrire.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> listeErreurs = new ArrayList<>();
		
		String pseudo = request.getParameter("pseudo");
		if (pseudo!=null || !request.getParameter(pseudo).isEmpty()) {
			request.setAttribute("spseudo", pseudo);
			//debut test
			System.out.println(pseudo);
			//fin test
		}
		String prenom = request.getParameter("prenom");
		if (prenom!=null || !request.getParameter(prenom).isEmpty()) {
			request.setAttribute("prenomForm", prenom);
		}
		String telephone = request.getParameter("telephone");
		if (telephone!=null || !request.getParameter(telephone).isEmpty()) {
			request.setAttribute("telephoneForm", telephone);
		}
		String postal = request.getParameter("postal");
		if (postal!=null || !request.getParameter(postal).isEmpty()) {
			try {
				int code_postal = Integer.parseInt(postal);
				request.setAttribute("postalForm", code_postal);
			} catch (Exception e) {
			}
		}
		String mdp = request.getParameter("mdp");
		String nom = request.getParameter("nom");
		if (nom!=null || !request.getParameter(nom).isEmpty()) {
			request.setAttribute("nomForm", nom);
		}
		String email = request.getParameter("email");
		if (email!=null || !request.getParameter(email).isEmpty()) {
			request.setAttribute("emailForm", email);
		}
		String rue = request.getParameter("rue");
		if (rue!=null || !request.getParameter(rue).isEmpty()) {
			request.setAttribute("rueForm", rue);
		}
		String ville = request.getParameter("ville");
		if (ville!=null || !request.getParameter(ville).isEmpty()) {
			request.setAttribute("villeForm", ville);
		}
		String confirmation = request.getParameter("confirmation");
		
		
		if((pseudo!=null &&!pseudo.isEmpty()) && (prenom!=null &&!prenom.isEmpty()) && (telephone!=null &&!telephone.isEmpty()) && (postal!=null &&!postal.isEmpty()) && 
				(mdp!=null &&!mdp.isEmpty()) && (nom!=null &&!nom.isEmpty()) && (email!=null &&!email.isEmpty()) && (rue!=null &&!rue.isEmpty()) && (ville!=null &&!ville.isEmpty()) && (confirmation!=null &&!confirmation.isEmpty())) {
		
			Utilisateur u = new Utilisateur();

			u.setPseudo(pseudo);
			u.setPrenom(prenom);
			try {
				Integer.parseInt(telephone.trim());
				u.setTelephone(telephone.trim());
			} catch (NumberFormatException e) {
				listeErreurs.add("Le t??l??phone doit ??tre en chiffres");
			}
			try {
				u.setCodePostal(Integer.parseInt(postal));
			} catch (NumberFormatException e) {
				listeErreurs.add("Le code postal doit ??tre en chiffres");
			}
			if (mdp.equals(confirmation)){
				u.setMotDePasse(mdp);
			} else {
				listeErreurs.add("Le mot de passe et la confirmation doivent ??tre identiques");
			}	
			u.setNom(nom);
			//test
			System.out.println(nom);
			//fintest
			u.setEmail(email);
			u.setRue(rue);
			u.setVille(ville);
			//test
			System.out.println(u);
			System.out.println("test");
			//fin test
			if (!listeErreurs.isEmpty()) {
				request.setAttribute("listeDesErreurs", listeErreurs);
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/SInscrire.jsp").forward(request, response);
		} else {
				try {
					utilisateurManager.inscriptionUser(u);
					// test
					System.out.println(u);
					//fin test
					u = utilisateurManager.connexionUser(u.getPseudo(), u.getMotDePasse());
					HttpSession session = request.getSession();
					session.setAttribute("myUser", u);
				} catch (Exception e) {
					listeErreurs = utilisateurManager.getListError();
					request.setAttribute("listeDesErreurs", listeErreurs);
					this.getServletContext().getRequestDispatcher("/WEB-INF/pages/SInscrire.jsp").forward(request, response);
				}
				this.getServletContext().getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);

			}
		} else {
			listeErreurs.add("Tous les champs doivent ??tre remplis");
			request.setAttribute("listeDesErreurs", listeErreurs);
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/SInscrire.jsp").forward(request, response);

		
		}
	}	
}
		
		
	

