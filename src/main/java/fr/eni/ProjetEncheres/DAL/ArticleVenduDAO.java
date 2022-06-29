package fr.eni.ProjetEncheres.DAL;

import java.util.List;

import fr.eni.ProjetEncheres.BO.ArticleVendu;
import fr.eni.ProjetEncheres.BO.Categorie;

public interface ArticleVenduDAO {

	void insert(ArticleVendu articleVendu) throws DALException;
	
	List<ArticleVendu> selectAll() throws DALException;
	
	ArticleVendu selectByNoArticle (int noArticle)throws DALException;
	
	List<ArticleVendu> selectVenteEnCoursByNomByCategorie() throws DALException;
	
	List<ArticleVendu> selectByUtilisateur() throws DALException;
	
	List<ArticleVendu> selectEnchereEnCours() throws DALException;
	
	List<ArticleVendu> selectByUtilisateurEnCours() throws DALException;
	
	List<ArticleVendu> selectByUtilisateurNonCommencee() throws DALException;
	
	List<ArticleVendu> selectByUtilisateurTerminee(int noUtilisateur) throws DALException;
	
	List<ArticleVendu> selectByCategorie(Categorie categorie) throws DALException;
}
