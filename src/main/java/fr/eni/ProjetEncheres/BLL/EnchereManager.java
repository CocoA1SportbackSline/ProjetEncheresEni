package fr.eni.ProjetEncheres.BLL;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.BO.ArticleVendu;
import fr.eni.ProjetEncheres.BO.Enchere;
import fr.eni.ProjetEncheres.BO.Utilisateur;
import fr.eni.ProjetEncheres.DAL.ArticleVenduDAO;
import fr.eni.ProjetEncheres.DAL.DALException;
import fr.eni.ProjetEncheres.DAL.DAOFactory;
import fr.eni.ProjetEncheres.DAL.EnchereDAO;
import fr.eni.ProjetEncheres.DAL.UtilisateurDAO;


public class EnchereManager {

	private EnchereDAO enchereDao;
	private UtilisateurDAO utilisateurDao;
	private ArticleVenduDAO articleVenduDao;
	private static List<String> listError;
	private static EnchereManager instance;
	
	public EnchereManager () {
		this.enchereDao = DAOFactory.getEnchereDao();
		this.utilisateurDao = DAOFactory.getUtilisateurDAO();
		this.articleVenduDao = DAOFactory.getArticleVenduDao();
	}
	
	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	public List<String> getListError(){
		return listError;
	}

	public void ajoutEnchere(Enchere enchere, Utilisateur utilisateur)throws BLLException{  
		
        listError = new ArrayList<>();
		
		Enchere enchereMax = null;
		ArticleVendu article = null;
		int prixMin = 0;
		Utilisateur dernierEncherisseur = null;
		
		try {
			enchereMax = enchereDao.recupEnchereMax(enchere.getNoArticle());
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		
		try {
			article = articleVenduDao.selectByID(enchere.getNoArticle());
		} catch (DALException e1) {
			e1.printStackTrace();
		}
		
		if (enchereMax == null) {
			prixMin = article.getPrix_initial();
		} else {
			prixMin = enchereMax.getMontantEnchere();
		}
		
		if(article.getDateFinEncheres().isBefore(LocalDateTime.now())) {
			listError.add("L'enchere est clôturé");
		}
		
		checkEnchere(enchere.getMontantEnchere(), prixMin, listError);
		checkPoints(enchere.getMontantEnchere(), utilisateur.getCredit(), listError);

		if (!listError.isEmpty()) {
			throw new BLLException("Echec ajoutEnchere1");
		}
		
		try {
			enchereDao.insert(enchere);
			utilisateur.setCredit(utilisateur.getCredit() - enchere.getMontantEnchere());
			utilisateurDao.update(utilisateur);
			
			if (enchereMax != null) {
				dernierEncherisseur = utilisateurDao.getselectByID(enchereMax.getNoUtilisateur());
				dernierEncherisseur.setCredit(dernierEncherisseur.getCredit() + enchereMax.getMontantEnchere());
				utilisateurDao.update(dernierEncherisseur);
				
			}
		} catch (DALException e) {
			throw new BLLException("Echec ajoutEnchere2");
		}
		
	}
	
	public Enchere derniereEnchere(ArticleVendu av) throws BLLException {
		Enchere enchereMax = null;
		
		try {
			enchereMax = enchereDao.recupEnchereMax(av.getNoArticle());
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur method derniereEnchere");
		}
		
		return enchereMax;
	}
	
	public void checkEnchere(float enchere, float tarifActuel, List<String> listError){
		if(enchere < tarifActuel) {
			listError.add("Pour encherir, vous devez proposer un prix suprieure a l enchere actuelle");
		}	
	}
	
	public void checkPoints(int enchere, int pointsPerso, List<String> listError){
		if(enchere > pointsPerso) {
			listError.add("Vous ne disposez pas d'assez de credit pour effectuer cette encherire.");
			listError.add("Votre credit est de " + pointsPerso + " points");
		}	
	}
}
