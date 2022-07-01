package fr.eni.ProjetEncheres.IHM;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.ProjetEncheres.BLL.UtilisateurManager;
import fr.eni.ProjetEncheres.BO.Utilisateur;



public class ServletSInscrire extends HttpServlet{


	private UtilisateurManager utilisateurManager;

	private static final long serialVersionUID = 1L;


	public void init() throws ServletException {
		utilisateurManager  = UtilisateurManager.getInstance();
    	super.init();
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
				 
				 String confirmation = request.getParameter("confirmation");
				 
				  if(!pseudo.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && !email.isEmpty() && 
						 !telephone.isEmpty() && rue.isEmpty() && !Postal.isEmpty() && !ville.isEmpty() ){
					 
				 }
				  
				 Utilisateur u = new Utilisateur();
				 
				 u.setPseudo(pseudo);
				 u.setPrenom(prenom);
				 try {
					 Integer.parseInt(telephone.trim());
					 u.setTelephone(telephone);
					 
				 }catch(NumberFormatException e){
					 	listeErreurs.add("le telephone doit etre que des chiffres");
					 
				 }
				 try {
					 u.setCodePostal(Integer.parseInt(Postal));
				 }catch(NumberFormatException e) {
					 
				 }
				 if(mdp.equals(confirmation)) {
					 u.setMotDePasse(mdp);
				 }else {
					 listeErreurs.add("le mot de passe et le mot de confirmation ne sont pas les memes");
					 
				 }
				 u.setNom(nom);
				 u.setEmail(email);
				 u.setRue(rue);
				 u.setVille(ville);
				 
				 if(!listeErreurs.isEmpty()) {
					 request.setAttribute("listedesErreurs", listeErreurs);
					 this.getServletContext().getRequestDispatcher("WEB-INF/").forward(request, response);
				 }else {
					
					 try {
						 utilisateurManager.inscriptionUser(u);
						 u = utilisateurManager.connexionUser(u.getPseudo(),u.getMotDePasse());
						 HttpSession session = request.getSession();
						 session.setAttribute("myuser", u);
					 }catch (Exception e) {
						 listeErreurs = utilisateurManager.getListError();
							request.setAttribute("listeDesErreurs", listeErreurs);
							this.getServletContext().getRequestDispatcher("/WEB-INF/sinscrire.jsp").forward(request, response);
						 
					 }
					 
					 this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
				 }
				/*} else {
					listeErreurs.add("Tous les champs doivent être remplis");
					request.setAttribute("listeDesErreurs", listeErreurs);
					this.getServletContext().getRequestDispatcher("/WEB-INF/sinscrire.jsp").forward(request, response);
				 }*/
			}
			
}
		
		
	

