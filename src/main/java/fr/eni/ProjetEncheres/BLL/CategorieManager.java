package fr.eni.ProjetEncheres.BLL;

import java.util.List;

import fr.eni.ProjetEncheres.BO.Categorie;
import fr.eni.ProjetEncheres.DAL.CategorieDAO;
import fr.eni.ProjetEncheres.DAL.DAOFactory;
import fr.eni.ProjetEncheres.DAL.JDBC.DALException;

public class CategorieManager {

	private CategorieDAO categorieDao;
	
	private static CategorieManager instance;
	
	public CategorieManager () {
		//instancie le DAO
		this.categorieDao = DAOFactory.getCategorieDao();
	}
	
	public static CategorieManager getInstance () {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	public List<Categorie> getCategorie ()throws BLLException{
		List<Categorie> listeCategorie = null;
		try {
			listeCategorie = categorieDao.selectAll();
		} catch (DALException e) {
			throw new BLLException ("getCategorie failed", e);
		}
		return listeCategorie;
	}
	
	public void validerCategorie (Categorie c1) throws BLLException {
		
		StringBuilder message = new StringBuilder();
		if (c1 == null) {
			message.append("Article null\n");
		} else {	
			if (c1.getLibelle() == null || c1.getLibelle().isBlank()) {
				message.append("Le libellé est obligatoire\n");
			} 
		
			if(!message.toString().isBlank()) {
				throw new BLLException(message.toString());
			}
		}
	}
	
	public void addCategorie (Categorie newCate) throws BLLException {
		if(newCate != null && newCate.getNoCategorie()!= null) {
		}
		try {
			this.validerCategorie(newCate);
			categorieDao.insert(newCate);
		} catch (DALException e) {
			throw new BLLException ("addCategorie failed", e);
		}
	}
	
	public void removeCategorie (Integer noCategorie) throws BLLException {
		try {
			categorieDao.delete(noCategorie);
		} catch (DALException e) {
			throw new BLLException("removeCategorie failed", e);
		}
	}
	
	public void updateCategorie (Categorie c1) throws BLLException {
		if(c1 != null && c1.getNoCategorie()== null) {
			throw new BLLException("Article inexistant");
		}
		try {
			this.validerCategorie(c1);
			categorieDao.update(c1);
		} catch (DALException e) {
			throw new BLLException("updateCategorie failed", e);
		}
	}
	
}
