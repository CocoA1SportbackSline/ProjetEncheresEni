package fr.eni.ProjetEncheres.DAL;

import java.util.List;

import fr.eni.ProjetEncheres.BO.Categorie;

public interface CategorieDAO {
	
	
	void insert (Categorie c1) throws DALException;
	void delete (int noCategorie)throws DALException;
	void update (Categorie c1) throws DALException;
	List <Categorie> selectAll () throws DALException;
	Categorie selectByNo (int noCategorie) throws DALException;
	

}
