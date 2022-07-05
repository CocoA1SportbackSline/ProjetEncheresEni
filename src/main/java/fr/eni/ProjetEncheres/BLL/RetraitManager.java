package fr.eni.ProjetEncheres.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.BO.Retrait;
import fr.eni.ProjetEncheres.DAL.DALException;
import fr.eni.ProjetEncheres.DAL.DAOFactory;
import fr.eni.ProjetEncheres.DAL.RetraitDAO;


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
	
	public Retrait selectById(int id) throws BLLException {
		Retrait r = new Retrait();
		
		try {

			r = retraitDao.selectById(id);

		} catch (DALException e) {
			throw new BLLException("Echec selectById");
		}
		return r;
	}

	
	
	
	
	
	public void removeRetrait (Integer noArticle) throws BLLException {
		try {
			retraitDao.delete(noArticle);
		} catch (DALException e) {
			throw new BLLException("removeRetrait failed", e);
		}
	}
	


	public void insertRetrait(Retrait t) throws BLLException{
		boolean verifRetraitExist = false;
		List<Retrait> listeRetrait = new ArrayList<>();


		//Recuperer la liste complete des retrait

		try {
			listeRetrait = retraitDao.selectAll();
		} catch (DALException e1) {
			throw new BLLException("Echec method insertRetrait() a l'appel du DAO selectaLL");

		}

		//tester tous le contenue de la liste pour vérifier si les retrait existe
		for (Retrait retrait : listeRetrait) {
			if(t.getVille().equals(retrait.getVille()) && t.getCodePostal() == retrait.getCodePostal() && t.getRue().equals(retrait.getRue())) {
				//Vérifier si les parametre de t (test le retrait passé en parametre de la methode) sont egaux aux parametre de retrait ( retrait est un element retrait de la liste complete des retrait)
				//si le retrait existe recuperer le retrait dans une variable
				t.setNoRetrait(retrait.getNoRetrait());
				verifRetraitExist = true;
				break;
			}
		
		}

		if (!verifRetraitExist) {
			try {
				retraitDao.insert(t);
			} catch (DALException e) {
				throw new BLLException("Echec method insertRetrait()");
			}
		}
}	
	
}
