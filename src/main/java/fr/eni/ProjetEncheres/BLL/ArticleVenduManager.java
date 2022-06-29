package fr.eni.ProjetEncheres.BLL;



import java.util.List;

import fr.eni.ProjetEncheres.BO.ArticleVendu;
import fr.eni.ProjetEncheres.DAL.ArticleVenduDAO;
import fr.eni.ProjetEncheres.DAL.DALException;
import fr.eni.ProjetEncheres.DAL.DAOArticleVenduFactory;

public class ArticleVenduManager {


	private ArticleVenduDAO articleVenduDAO;
	
	public ArticleVenduManager() {

		articleVenduDAO = DAOArticleVenduFactory.getArticleVenduDAO();
		
	}
	
	public void insert(ArticleVendu articleVendu) throws BLLException  {
		
		try {
			articleVenduDAO.insert(articleVendu);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException ("insert",e);
		}
	}
	
	public List<ArticleVendu> selectAll() throws BLLException{
		
		try {
			return articleVenduDAO.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException ("selectAll",e);
		}
		
	}
	
	public ArticleVendu selectByNoArticle (int noArticle)throws BLLException{
		
		try {
			return articleVenduDAO.selectByNoArticle(noArticle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException ("selectByNoArticle",e);
		}
	}
	
	public List<ArticleVendu> selectVenteEnCoursByNomByCategorie() throws BLLException{
		
		try {
			return articleVenduDAO.selectVenteEnCoursByNomByCategorie();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException ("selectVenteEnCoursByNomByCategorie",e);
		}
	}
	
	public List<ArticleVendu> selectByUtilisateur() throws BLLException{
		
		try {
			return articleVenduDAO.selectByUtilisateur();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException ("selectByUtilisateur",e);
		}
	}

	public List<ArticleVendu> selectEnchereEnCours() throws BLLException{
		
		try {
			return articleVenduDAO.selectEnchereEnCours();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException ("selectEnchereEnCours",e);
		}
	}
	
	public List<ArticleVendu> selectByUtilisateurEnCours() throws BLLException{
		
		try {
			return articleVenduDAO.selectByUtilisateurEnCours();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException ("selectByUtilisateurEnCours",e);
		}
	}
	
	public List<ArticleVendu> selectByUtilisateurNonCommencee() throws BLLException{
	
	        try {
				return articleVenduDAO.selectByUtilisateurNonCommencee();
			} catch (DALException e) {
				// TODO Auto-generated catch block
				throw new BLLException ("selectByUtilisateurNonCommencee",e);
			}
	        
	         		
	}    
	
	public 	List<ArticleVendu> selectByUtilisateurTerminee() throws BLLException{
		
		try {
			return articleVenduDAO.selectByUtilisateurNonCommencee();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException ("selectByUtilisateurTerminee",e);
		}
	}
	
}
