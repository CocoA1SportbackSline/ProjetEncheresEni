package fr.eni.ProjetEncheres.BLL;



import java.util.List;

import fr.eni.ProjetEncheres.BO.ArticleVendu;
import fr.eni.ProjetEncheres.DAL.ArticleVenduDAO;
import fr.eni.ProjetEncheres.DAL.DALException;

import fr.eni.ProjetEncheres.DAL.DAOFactory;
import fr.eni.ProjetEncheres.DAL.EnchereDAO;
import fr.eni.ProjetEncheres.DAL.UtilisateurDAO;

public class ArticleVenduManager {

	private static ArticleVenduManager articleVenduManager;
	private ArticleVenduDAO articleVenduDAO;
	private EnchereDAO enchereDAO;
	private UtilisateurDAO utilisateurDAO;
	
	private static List<String> listError;
	
	public ArticleVenduManager() {
		this.articleVenduDAO = DAOFactory.getArticleVenduDao();
		this.enchereDAO = DAOFactory.getEnchereDao();
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public static ArticleVenduManager getInstance() {
		if(articleVenduManager == null) {
			articleVenduManager = new ArticleVenduManager();
		}
		return articleVenduManager;
	}
	
	public List<String> getListError() {
		return listError;
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
	
	public List<ArticleVendu> selectVenteEnCoursByNomByCategorie(int noUtilisateur) throws BLLException{
		
		try {
			return articleVenduDAO.selectVenteEnCoursByNomByCategorie(noUtilisateur);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException ("selectVenteEnCoursByNomByCategorie",e);
		}
	}
	
	public List<ArticleVendu> selectByUtilisateur(int noUtilisateur) throws BLLException{
		
		try {
			return articleVenduDAO.selectByUtilisateur(noUtilisateur);
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
	
	public List<ArticleVendu> selectByUtilisateurEnCours(int noUtilisateur) throws BLLException{
		
		try {
			return articleVenduDAO.selectByUtilisateurEnCours( noUtilisateur);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException ("selectByUtilisateurEnCours",e);
		}
	}
	
	public List<ArticleVendu> selectByUtilisateurNonCommencee(int noUtilisateur) throws BLLException{
	
	        try {
				return articleVenduDAO.selectByUtilisateurNonCommencee(noUtilisateur);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				throw new BLLException ("selectByUtilisateurNonCommencee",e);
			}
	        
	         		
	}    
	
	public 	List<ArticleVendu> selectByUtilisateurTerminee(int noUtilisateur) throws BLLException{
		
		try {
			return articleVenduDAO.selectByUtilisateurNonCommencee(noUtilisateur);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			throw new BLLException ("selectByUtilisateurTerminee",e);
		}
	}
	
}
