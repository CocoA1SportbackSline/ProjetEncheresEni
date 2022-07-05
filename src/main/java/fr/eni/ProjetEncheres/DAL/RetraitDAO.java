package fr.eni.ProjetEncheres.DAL;

import java.util.List;

import fr.eni.ProjetEncheres.BO.Retrait;


public interface RetraitDAO {

	void insert (Retrait r1) throws DALException;
	void delete (int id)throws DALException;
	void update (Retrait r1) throws DALException;
	List <Retrait> selectAll () throws DALException;
	Retrait selectById (int noRetrait) throws DALException;
	
}
