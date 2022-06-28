package fr.eni.ProjetEncheres.DAL.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.BO.Categorie;
import fr.eni.ProjetEncheres.DAL.CategorieDAO;
import fr.eni.ProjetEncheres.DAL.ConnectionProvider;

public class CategorieDAOImpl implements CategorieDAO{
	
	private static final String INSERT = "INSERT INTO categories (libelle) values (?)";
	private static final String DELETE = "DELETE FROM categories WHERE no_categorie = ?";
	
	//a revoir car pas certaine de la requete
	private static final String UPDATE = "UPDATE categories SET (libelle) WHERE no_categorie = ?";
	
	private static final String SELECT_ALL = "SELECT * FROM categories";
	private static final String SELECT_BY_ID = "SELECT no_categorie, libelle FROM categories WHERE no_categorie = ?";
	
	@Override
	public void insert(Categorie c1) throws DALException {
		
		try(Connection conn = ConnectionProvider.getConnection()){  
			PreparedStatement pstmt = conn.prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1,c1.getLibelle());
			pstmt.executeUpdate();
   
			ResultSet rs = pstmt.getGeneratedKeys();
   
			if(rs.next()){
				c1.setNoCategorie(rs.getInt(1));
			}
  
		} catch (SQLException e) {
			throw new DALException("Erreur insert",e);
		}
		
	}

	@Override
	public void delete(Integer noCategorie) throws DALException {
		
		try (Connection  conn = ConnectionProvider.getConnection();){	
			PreparedStatement pstmt = conn.prepareStatement(DELETE);
			
			pstmt.setInt(1, noCategorie);
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" + noCategorie, e);
		} 
	}

	@Override
	public void update(Categorie c1) throws DALException {
		
		try(Connection conn = ConnectionProvider.getConnection();){        
    		PreparedStatement pstmt = conn.prepareStatement(UPDATE);
    		
    		pstmt.setString(1,c1.getLibelle());
    		
    		pstmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
            throw new DALException("Erreur update",e);
		}
	}

	@Override
	public List<Categorie> selectAll() throws DALException {
		List<Categorie> listeCategorie = new ArrayList<Categorie>();
		// ouverture et fermeture de la connection
		try (Connection conn = ConnectionProvider.getConnection();) {

			// ouverture de requete
			Statement stmt = conn.createStatement();
			// recuperation du tableau
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			Categorie cateAjout = null;
			while (rs.next()) {
				// utilisation du conscruteur Categorie
				cateAjout = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				listeCategorie.add(cateAjout);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("erreur select all", e);
		}
		return listeCategorie;
	}

	@Override
	public Categorie selectByNo(Integer noCategorie) throws DALException {
		
		try( Connection conn = ConnectionProvider.getConnection();){
			PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_ID);

			pstmt.setInt(1, noCategorie);

			//recuperation du tableau
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return new Categorie (rs.getInt("id"), rs.getString("libelle"));
			} else  {
				throw new DALException("Mauvais ID");
			}
		} catch (SQLException e) {
			throw new DALException("selectByNo failed - id = " + noCategorie, e);
		} 
	}
}
