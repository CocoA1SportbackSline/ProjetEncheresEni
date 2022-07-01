package fr.eni.ProjetEncheres.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.BO.Utilisateur;
import fr.eni.ProjetEncheres.DAL.DALException;
import fr.eni.ProjetEncheres.DAL.DAOFactory;
import fr.eni.ProjetEncheres.DAL.UtilisateurDAO;

public class UtilisateurManager {
	//private  UtilisateurManager utilisateurManager;
	private UtilisateurDAO utilisateurdao;
	private static List<String> listError;
	private static UtilisateurManager instance;
	
	
	public UtilisateurManager() {
		this.utilisateurdao = DAOFactory.getUtilisateurDAO();
		
	}
	
	public static UtilisateurManager getInstance() {
		if(instance == null) {
			instance =new UtilisateurManager();
		}
		return instance;
	}
	
	public List<String> getListError(){
		return listError;
	}
	
	
	// recupere l utilisateur a partir de l id avec la methode selectby id
	// retourne un utilisateur
	public Utilisateur postUser(int id)throws BLLException{
		Utilisateur user= null;
		try {
			user= utilisateurdao.getselectByID(id);
		}catch (DALException e) {
			throw new BLLException("echec profil");
		}
		return user;
	}
	
	
	
	public void inscriptionUser(Utilisateur u) throws BLLException {
		listError = new ArrayList<>();
		
		checkPseudoUnique(u.getPseudo(), listError);
		checkEmailUnique(u.getEmail(), listError);
		checkPseudo(u.getPseudo(), listError);
		
		if (!listError.isEmpty()) {
			throw new BLLException("Echec inscriptionUser : verification pseudo et email");
		}
		try {
			utilisateurdao.insert(u);
		} catch (DALException e) {
			throw new BLLException("Echec inscriptionUser");
		}
	}
	
	
	
	// permet la connexcion a partir de l'utilisateur
	// Verif si il ny a pas d erreur dans l email ou pseudo , mot de passe 
	// si pas d erreur alors on utilisa la methode select by pseudo utilise le mot de passe et le pseudo 
	//on retourne utilisateur a les infos
	public Utilisateur connexionUser(String pseudo , String motDePasse) throws BLLException {
		Utilisateur user = null;
	
		checkPseudo(pseudo , listError);
		checkEmail(pseudo, listError);
		checkPassword(motDePasse, listError);
		
		if((listError !=null) &&(!listError.isEmpty())) {
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
	
	
	
	// permet la modification des parametre de l utilisateur 
	// avec une verif si il n y a pas d erreur 
	// si non alors la modif est accepte
	public void updateUser(Utilisateur u) throws BLLException {
		listError = new ArrayList<>();
		
		checkPseudo(u.getPseudo(), listError);
		checkNom(u.getNom(), listError);
		checkPrenom(u.getPrenom(), listError);
		checkEmail(u.getEmail(), listError);
		checkTelephone(u.getTelephone(), listError);
		checkRue(u.getRue(), listError);
		checkCodePostal(String.valueOf(u.getCodePostal()), listError);
		checkVille(u.getVille(), listError);
		checkPassword(u.getMotDePasse(), listError);
		
		if(!listError.isEmpty()) {
			throw new BLLException("Echec updateUser : vÃ©rification des attributs");
		}
		
		try {
			utilisateurdao.update(u);
		} catch (DALException e) {
			throw new BLLException("Echec updateUser");
		}
		
	}

	
	// permet la suppression d un utilisateur en fonction de son no_uti attribuÃ© a la creation du compte
	public void deleteUser(Utilisateur u) throws BLLException {
		
		try {
			utilisateurdao.delete(u.getNoUtilisateur());
		} catch (DALException e) {
			throw new BLLException("Echec updateUser");
		}
		
	}
	
	
	public void checkPseudo(String pseudo, List<String> listError) {
		if(pseudo.length()>30) {
			listError.add("le pseudo est trop grand");
		}
		if(!pseudo.matches("[a-zA-Z0-9]*")) {
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
		if(!Password.matches("[a-zA-Z0-9]*")) {
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
	//permet la vérif que le nbr de caractere ne peut pas dépasser 10
	public void checkCodePostal(String codePostal, List<String> listError) {
		if(codePostal.length()>10);
		listError.add("le code postal ne doit pas depasser 10 carac");
	}
	
	//permet le verif que le nbr de caractere ne peut pas depasser une certaine limite
		public void checkTelephone(String telephone, List<String> listError){
			if(telephone.length() > 15) {
				listError.add("Le numero de telephone ne doit pas dÃ©passer 15 caractÃ¨res");
			}	
		}
	
	
		
		// permet la verification du pseudo afin qu il soit unique 
		// on fait apparaitre la liste de tout les utilisateurs afin de faire la verif 
		// si pseudo existe deja alors recree un nouveau pseudo 
		//sinon pseudo valider
		public boolean checkPseudoUnique(String pseudo, List<String> listError) throws BLLException{
			boolean verifPseudo = true;
			List<Utilisateur> listeUtilisateur = new ArrayList<>();
			try {
				listeUtilisateur = utilisateurdao.selectAll();
			} catch (DALException e) {
				throw new BLLException("Echec checkPseudoUnique");
			}
			
			for (Utilisateur utilisateur : listeUtilisateur) {
				if (utilisateur.getPseudo().equals(pseudo)) {
					verifPseudo = false;
					break;
				}	
			}
			if (verifPseudo == false) {
				listError.add("Ce pseudo existe deja ");
			}
			return verifPseudo;	
		}
		
		// verif que l email soit unique a partir de la list utilisateur 
		// si unique alors email = ok sinon taper nouveau email
		public boolean checkEmailUnique(String email, List<String> listError) throws BLLException{
			boolean verifEmail = true;
			List<Utilisateur> listeUtilisateur = new ArrayList<>();
			try {
				listeUtilisateur = utilisateurdao.selectAll();
			} catch (DALException e) {
				throw new BLLException("Echec checkEmailUnique");
			}
			
			for (Utilisateur utilisateur : listeUtilisateur) {
				if (utilisateur.getEmail().equals(email)) {
					verifEmail = false;
					break;
				}	
			}
			if (verifEmail == false) {
				listError.add("Cet email est deja utilisé");

			}
			return verifEmail;	
		}
	}
