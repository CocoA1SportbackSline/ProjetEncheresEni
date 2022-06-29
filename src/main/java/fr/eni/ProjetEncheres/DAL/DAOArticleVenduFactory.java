package fr.eni.ProjetEncheres.DAL;

import fr.eni.ProjetEncheres.DAO.Impl.ArticleVenduDAOImpl;

public class DAOArticleVenduFactory {

	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOImpl();
	}
	
	
	
	
}
