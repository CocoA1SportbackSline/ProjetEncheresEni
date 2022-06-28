package fr.eni.ProjetEncheres.DAL;

import fr.eni.ProjetEncheres.DAL.JDBC.UtilisateurDAOImpl;

public class DAOFactory {
	
	
	public static UtilisateurDAO getUtilisateurDAO() {
		UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
		return utilisateurDAO;
	}

}
