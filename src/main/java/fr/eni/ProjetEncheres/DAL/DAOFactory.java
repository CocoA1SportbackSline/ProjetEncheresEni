package fr.eni.ProjetEncheres.DAL;

<<<<<<< HEAD
import fr.eni.ProjetEncheres.DAL.JDBC.CategorieDAOImpl;
import fr.eni.ProjetEncheres.DAL.JDBC.EnchereDAOImpl;
import fr.eni.ProjetEncheres.DAL.JDBC.RetraitDAOImpl;

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
=======
import fr.eni.ProjetEncheres.DAL.JDBC.UtilisateurDAOImpl;

public class DAOFactory {
	
	
	public static UtilisateurDAO getUtilisateurDAO() {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
		return utilisateurDAO;
	}

>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
}
