package fr.eni.ProjetEncheres.DAL;


import fr.eni.ProjetEncheres.DAL.JDBC.ArticleVenduDAOImpl;
import fr.eni.ProjetEncheres.DAL.JDBC.CategorieDAOImpl;
import fr.eni.ProjetEncheres.DAL.JDBC.EnchereDAOImpl;
import fr.eni.ProjetEncheres.DAL.JDBC.RetraitDAOImpl;
import fr.eni.ProjetEncheres.DAL.JDBC.UtilisateurDAOImpl;

public class DAOFactory {

	public static CategorieDAO getCategorieDao () {
		CategorieDAO categorieDao = new CategorieDAOImpl();
		return categorieDao;
	}
	
	public static RetraitDAO getRetraitDao () {
		RetraitDAO retraitDao = new RetraitDAOImpl();
		return retraitDao;
	}

	public static EnchereDAO getEnchereDao() {
		EnchereDAO enchereDao = new EnchereDAOImpl ();
		return enchereDao;
	}

	
	public static ArticleVenduDAO getArticleVenduDao() {
		ArticleVenduDAO articleVenduDao = new ArticleVenduDAOImpl();
		return articleVenduDao;
	}


	public static UtilisateurDAO getUtilisateurDAO() {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
		return utilisateurDAO;
	}

}

