package fr.eni.ProjetEncheres.BLL;

import fr.eni.ProjetEncheres.DAL.DAOFactory;
import fr.eni.ProjetEncheres.DAL.EnchereDAO;

public class EnchereManager {

	private EnchereDAO enchereDao;
	
	private static EnchereManager instance;
	
	public EnchereManager getInstance() {
		//instancie le DAO
		this.enchereDao = DAOFactory.getEnchereDao();
	}
	
}
