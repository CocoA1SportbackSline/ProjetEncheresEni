package fr.eni.ProjetEncheres.DAL;

import fr.eni.ProjetEncheres.DAL.JDBC.ArticleVenduDAOImpl;


public class DAOArticleVenduFactory {

	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOImpl();
	}
	
	
	
	
}
