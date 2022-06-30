package fr.eni.ProjetEncheres.DAL;

import java.util.List;

import fr.eni.ProjetEncheres.BO.ArticleVendu;


public interface ArticleVenduDAO {

	public  void insert(ArticleVendu a) throws DALException;
	
	public List<ArticleVendu> selectAll() throws DALException ;
	
	public void update(ArticleVendu a) throws DALException;
	
	public void delete(int id) throws DALException;
	
	public ArticleVendu selectByID(int id) throws DALException;
	
	public List<ArticleVendu> selectByNoCategorie(int idCategorie) throws DALException;
	
	public List<ArticleVendu> selectByKeyWordAndNoCategorie(String keyWord, int idCategorie) throws DALException;
	
	public List<ArticleVendu> selectByKeyWord(String keyWord) throws DALException;
	
	public List<ArticleVendu> selectAllBeforeDate(int idUtilisateur) throws DALException;
	
	public List<ArticleVendu> selectAllBetweenDate(int idUtilisateur) throws DALException;
	
	public List<ArticleVendu> selectAllSoldOut(int idUtilisateur) throws DALException;
	
	public List<ArticleVendu> selectAllSoldOut() throws DALException;
	
}
