package fr.eni.ProjetEncheres.DAL;

import fr.eni.ProjetEncheres.DAL.JDBC.ArticleVenduDAOImpl;
<<<<<<< HEAD

//import fr.eni.ProjetEncheres.DAO.Impl.ArticleVenduDAOImpl;
=======
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git

public class DAOArticleVenduFactory {

	public static ArticleVenduDAO getArticleVenduDAO() {
		return new ArticleVenduDAOImpl();
	}
	
	
	
	
}
