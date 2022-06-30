package fr.eni.ProjetEncheres.BLL;

import java.util.List;

import fr.eni.ProjetEncheres.BO.Retrait;
import fr.eni.ProjetEncheres.DAL.DAOFactory;
import fr.eni.ProjetEncheres.DAL.RetraitDAO;
import fr.eni.ProjetEncheres.DAL.JDBC.DALException;

public class RetraitManager {

	private RetraitDAO retraitDao;
	
	private static RetraitManager instance;
	
	public RetraitManager () {
		this.retraitDao = DAOFactory.getRetraitDao();
	}
	
	public static RetraitManager getInstance () {
		if (instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}
	
	public List<Retrait> getRetrait ()throws BLLException{
		List<Retrait> listeRetrait = null;
		try {
			listeRetrait = retraitDao.selectAll();
		} catch (DALException e) {
			throw new BLLException ("getRetrait failed", e);
		}
		return listeRetrait;
	}
	
	public void validerRetrait (Retrait r1) throws BLLException {
		
		StringBuilder message = new StringBuilder();
		if (r1 == null) {
			message.append("Article null\n");
		} else {	
			if (r1.getRue() == null || r1.getRue().isBlank()) {
				message.append("La rue est obligatoire\n");
			} 
			if (r1.getCodePostal() == null || r1.getCodePostal().isBlank()) {
				message.append("Le code postal est obligatoire\n");
			}
			if (r1.getVille() == null || r1.getVille().isBlank()) {
				message.append("La ville est obligatoire\n");
			}
			if(!message.toString().isBlank()) {
				throw new BLLException(message.toString());
			}
		}
	}
	
	public void addRetrait (Retrait newRet) throws BLLException {
        if(newRet != null && newRet.getNoArticle()!= 0) {
        }
        try {
            this.validerRetrait(newRet);
            retraitDao.insert(newRet);
        } catch (DALException e) {
            throw new BLLException ("addRetrait failed", e);
        }
    }
	
	public void removeRetrait (Integer noArticle) throws BLLException {
		try {
			retraitDao.delete(noArticle);
		} catch (DALException e) {
			throw new BLLException("removeRetrait failed", e);
		}
	}
	
	public void updateRetrait (Retrait r1) throws BLLException {
		if(r1 != null && r1.getNoArticle()== 0) {
			throw new BLLException("Retrait inexistant");
		}
		try {
			this.validerRetrait(r1);
			retraitDao.update(r1);
		} catch (DALException e) {
			throw new BLLException("updateRetrait failed", e);
		}
	}
	
}
