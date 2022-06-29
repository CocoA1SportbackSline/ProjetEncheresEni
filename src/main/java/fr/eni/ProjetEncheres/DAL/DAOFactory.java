package fr.eni.ProjetEncheres.DAL;

<<<<<<< HEAD

=======
import fr.eni.ProjetEncheres.DAL.JDBC.ArticleVenduDAOImpl;
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
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
<<<<<<< HEAD
=======
	
	public static ArticleVenduDAO getArticleVenduDao() {
		ArticleVenduDAO articleVenduDao = new ArticleVenduDAOImpl();
		return articleVenduDao;
	}


	
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
	
	public static UtilisateurDAO getUtilisateurDAO() {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
		return utilisateurDAO;
	}

}

<<<<<<< HEAD

	
	



=======
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
