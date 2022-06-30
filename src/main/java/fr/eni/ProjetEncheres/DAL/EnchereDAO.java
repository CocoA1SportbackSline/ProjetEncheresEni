package fr.eni.ProjetEncheres.DAL;

import java.util.List;

import fr.eni.ProjetEncheres.BO.Enchere;
import fr.eni.ProjetEncheres.DAL.JDBC.DALException;

public interface EnchereDAO {

	void insert (Enchere e1) throws DALException;
	void delete (int noEnchere)throws DALException;
	void update (Enchere e1) throws DALException;
	List <Enchere> selectAll () throws DALException;
	Enchere selectByNoEnch (int noEnchere) throws DALException;
	public Enchere recupEnchereMax(int noEchere) throws DALException;


}
