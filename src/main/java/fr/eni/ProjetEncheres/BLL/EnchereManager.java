package fr.eni.ProjetEncheres.BLL;

import fr.eni.ProjetEncheres.BO.Enchere;
import fr.eni.ProjetEncheres.DAL.ArticleVenduDAO;
import fr.eni.ProjetEncheres.DAL.DAOFactory;
import fr.eni.ProjetEncheres.DAL.EnchereDAO;
import fr.eni.ProjetEncheres.DAL.UtilisateurDAO;
import fr.eni.ProjetEncheres.DAL.JDBC.DALException;

public class EnchereManager {

	private EnchereDAO enchereDao;
	private UtilisateurDAO utilisateurDao;
	private ArticleVenduDAO articleVenduDao;
	
	private static EnchereManager instance;
	
	public EnchereManager () {
		this.enchereDao = DAOFactory.getEnchereDao();
		this.utilisateurDao = DAOFactory.getUtilisateurDAO();
		//this.articleVenduDao = DAOFactory.getArticleVenduDAO();
	}
	
	public EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		return instance;
	}
	
	public void validerEnchere (Enchere e1) throws BLLException {
			
			StringBuilder message = new StringBuilder();
			if (e1 == null) {
				message.append("Article null\n");
			} else {	
				if (e1.getDateEnchere() == null) {
					message.append("La date est obligatoire\n");
				} 
				if (e1.getMontantEnchere() == null) {
					message.append("Le montant est obligatoire\n");
				}
				if (e1.getNoArticle() == null) {
					message.append("Le numero d'article est obligatoire\n");
				}
				if (e1.getNoUtilisateur() == null) {
					message.append("Le numero d'utilisateur est obligatoire\n");
				}
				if(!message.toString().isBlank()) {
					throw new BLLException(message.toString());
				}
			}
	}
	
	public void addEnchere (Enchere newEnch) throws BLLException {
		if(newEnch != null && newEnch.getNoEnchere()!= null) {
		}
		try {
			this.validerEnchere(newEnch);
			enchereDao.insert(newEnch);
		} catch (DALException e) {
			throw new BLLException ("addEnchere failed", e);
		}
	}
	
}
