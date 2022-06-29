package fr.eni.ProjetEncheres.DAL;

import java.util.List;

import fr.eni.ProjetEncheres.BO.Utilisateur;

public interface UtilisateurDAO {
	
	
	public Utilisateur getselectByPseudo(String pseudo , String motDePasse) throws DALException;
	
	public void delete(int id) throws DALException;
	
	public void update(Utilisateur u) throws DALException;
	
	public List<Utilisateur> selectAll() throws DALException ;
	
	public void insert(Utilisateur u) throws DALException; 

	public Utilisateur getselectByID(int id)throws DALException;

	
}
