package fr.eni.ProjetEncheres.DAL;

import java.util.List;

import fr.eni.ProjetEncheres.BO.Retrait;
import fr.eni.ProjetEncheres.DAL.JDBC.DALException;

public interface RetraitDAO {

	void insert (Retrait r1) throws DALException;
	void delete (Integer noArticle)throws DALException;
	void update (Retrait r1) throws DALException;
	List <Retrait> selectAll () throws DALException;
	Retrait selectByNoArt (Integer noArticle) throws DALException;
	
}
