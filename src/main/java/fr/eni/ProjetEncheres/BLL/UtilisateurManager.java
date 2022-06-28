package fr.eni.ProjetEncheres.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.BO.Utilisateur;
import fr.eni.ProjetEncheres.DAL.DALException;
import fr.eni.ProjetEncheres.DAL.DAOFactory;
import fr.eni.ProjetEncheres.DAL.UtilisateurDAO;

public class UtilisateurManager {


	private static UtilisateurManager utilisateurManager;
	private UtilisateurDAO utilisateurdao;
	private static List<String> listError;
	
	private UtilisateurManager() {
		this.utilisateurdao = DAOFactory.getUtilisateurDAO();
		
	}
	
	public static UtilisateurManager getInstance() {
		if(utilisateurManager == null) {
			utilisateurManager =new UtilisateurManager();
		}
		return utilisateurManager;
	}
	
	public List<Error> getListError(){
		return listError;
	}
	
	public Utilisateur postUser(int id)throws BLLException{
		Utilisateur user= null;
		try {
			user= utilisateurdao.getselectByID(id);
		}catch (DALException e) {
			throw new BLLException("echec profil");
		}
		return user;
	}
	
	public Utilisateur connexionUser(String pseudo , String motDePasse) throws BLLException {
		Utilisateur user = null;
	
		checkPseudo(pseudo , listError);
		checkEmail(pseudo, listError);
		checkPassword(motDePasse, listError);
		
		if(!listError.isEmpty()) {
			throw new BLLException("echec connexionuser : verifier mot de passe et pseudo");
		}
		
		try {
			user= utilisateurdao.getselectByPseudo(pseudo,motDePasse);
			
		}catch (DALException e) {
			throw new BLLException("echec de la connexion");
		}
		if(user==null) {
			throw new BLLException("echec connexion");
		}
		
		return user;
	}
	
	public void checkPseudo(String pseudo, List<String> listError) {
		if(pseudo.length()>30) {
			listError.add("le pseudo est trop grand");
		}
		if(!pseudo.matches("[a-AZ-Z0-9]*")) {
			listError.add("le pseudo doit composer uniquement de lettre et de chiffre");
		}
	}
	
	public void checkEmail(String Email, List<String>listError) {
		if(Email.length()>30) {
			listError.add("l'adresse mail est trop grande");
			}
		}
	
	public void checkPassword(String Password,List<String>listError) {
		if(Password.length()>30) {
			listError.add("mot de passe est trop grand");
		}
		if(!Password.matches("[a-AZ-Z0-9]*")) {
			listError.add("mot de ne peut correspondre qu'a des lettres et des chiffres");
		}
	}
	
	public void checkNom(String nom, List<String>listError) {
		if(nom.length()>30) {
			listError.add("nom est trop grand");
		}
		if(nom.matches("[a-AZ]*")) {
			listError.add("le pseudo ne peut comprendre que des lettres et des chiffres");
		}
	}
	
	public void checkPrenom(String prenom, List<String>ListError) {
		if(prenom.length()>30) {
			listError.add("le prenom est trop grand ");
		}
	
		if(prenom.matches("[a-AZ]*")) {
			listError.add("le prenom ne peut contenir que des lettre");
		}
	}
	
	public void checkRue(String rue , List<String>ListError) {
		if(rue.length()> 30 ) {
			listError.add("il y a trop de caractere dans le nom rue");
		}
	}
	public void checkVille (String ville, List<String> ListError) {
		if(ville.length()>50) {
			listError.add("il y a trop de caracteres dans le nom ville");
		}
	}
	
	

	public void update (Utilisateur u) throws BLLException{
		listError = new ArrayList<>();
		
		checkPseudo(u.getPseudo(),listError);
		checkNom(u.getNom(),listError);
		checkPrenom(u.getPrenom(),listError);
		checkEmail(u.getEmail(),listError);
		checkRue(u.getRue(),listError);
		checkVille(u.getVille(),listError);
		checkCodePostal(u.getCode_Postal(),listError);
		checkPassword(u.getMotDePasse(),listError);
		
		if(!listError.isEmpty()) {
			throw new BLLException("")
		}
		
	}
}
