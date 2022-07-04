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

@WebServlet("/MonProfil")
public class ServletAfficherMonProfil extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurManager;
       
	
	public void init() throws ServletException {
		utilisateurManager = UtilisateurManager.getInstance();
    	super.init();
    }
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		

		Utilisateur user = (Utilisateur) request.getSession().getAttribute("myUser");
		
		if(user != null && !user.getPseudo().isEmpty()) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/pages/Monprofil.jsp").forward(request, response);
		} else {
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/pages/Accueil.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> listError = new ArrayList<>();
		
		String pseudo = request.getParameter("spseudo");
		String nom = request.getParameter("snom");
		String prenom = request.getParameter("sprenom");
		String email = request.getParameter("semail");
		String telephone = request.getParameter("stelephone");
		String rue = request.getParameter("srue");
		String codePostal = request.getParameter("scodePostal");
		String ville = request.getParameter("sville");
		
		String motDePasseActuel = request.getParameter("spasswordact");
		String motDePasseNouveau = request.getParameter("spasswordnew");
		String motDePasseConfirme = request.getParameter("spasswordconf");
		
		if(request.getParameter("supdate") != null && request.getParameter("supdate").equals("ok")) {
			Utilisateur utilisateurModif = (Utilisateur)request.getSession().getAttribute("myUser");
			
			if(!pseudo.isEmpty()) {
				utilisateurModif.setPseudo(pseudo);
			}
			if(!nom.isEmpty()) {
				utilisateurModif.setNom(nom);
			}
			if(!prenom.isEmpty()) {
				utilisateurModif.setPrenom(prenom);
			}
			if(!email.isEmpty()) {
				utilisateurModif.setEmail(email);
			}
			if(!telephone.isEmpty()) {
				utilisateurModif.setTelephone(telephone);
			}
			if(!rue.isEmpty()) {
				utilisateurModif.setRue(rue);
			}
			if(!codePostal.isEmpty()) {
				try {
					utilisateurModif.setCodePostal(Integer.parseInt(codePostal.trim()));
				} catch(NumberFormatException e){
					listError.add("Le code postal ne pas contenir de caractère alphabéthique");
				}
			}
			if(!ville.isEmpty()) {
				utilisateurModif.setVille(ville);
			}
			
			if(!motDePasseActuel.isEmpty() && !motDePasseNouveau.isEmpty() && !motDePasseConfirme.isEmpty()) {
				if(motDePasseActuel.equals(((Utilisateur)request.getSession().getAttribute("myUser")).getMotDePasse())) {
					if(motDePasseNouveau.equals(motDePasseConfirme)) {
						utilisateurModif.setMotDePasse(motDePasseConfirme);
					} else {
						listError.add("La confirmation de mot de passe est incorrect");
					}
				} else {
					listError.add("Le mot de passe est incorrect");
				}
				
			}
			
			if(!listError.isEmpty()) {
				listError.add("Modification du profil impossible");
			} else {
				try {
					utilisateurManager.updateUser(utilisateurModif);
				} catch (BLLException e) {
					listError = utilisateurManager.getListError();
					listError.add("Impossible de modifier le profil");
				}
			}
			
			request.setAttribute("listError", listError);
					
		}
		if(request.getParameter("sdelete") != null && request.getParameter("sdelete").equals("ok")) {
			this.getServletContext().getRequestDispatcher("/").forward(request, response);
		}
		doGet(request, response);
	}
}