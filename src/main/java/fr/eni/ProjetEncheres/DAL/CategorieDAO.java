package fr.eni.ProjetEncheres.DAL;

import java.util.List;

import fr.eni.ProjetEncheres.BO.Categorie;
import fr.eni.ProjetEncheres.DAL.JDBC.DALException;

public interface CategorieDAO {

	void insert (Categorie c1) throws DALException;
	void delete (Integer noCategorie)throws DALException;
	void update (Categorie c1) throws DALException;
	List <Categorie> selectAll () throws DALException;
	Categorie selectByNo (Integer noCategorie) throws DALException;
	
}
