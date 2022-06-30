package fr.eni.ProjetEncheres.DAL.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import fr.eni.ProjetEncheres.BO.Retrait;
import fr.eni.ProjetEncheres.DAL.ConnectionProvider;
import fr.eni.ProjetEncheres.DAL.DALException;
import fr.eni.ProjetEncheres.DAL.RetraitDAO;

public class RetraitDAOImpl implements RetraitDAO {
	
	private static final String INSERT = "INSERT INTO retraits (rue, code_postal, ville) VALUES (?, ?, ?)";
	private static final String DELETE = "DELETE FROM retraits WHERE no_article = ?";
	private static final String UPDATE = "UPDATE retraits SET rue=?, code_postal=?, ville=? WHERE no_article=?";
	private static final String SELECT_ALL = "SELECT * FROM retraits";
	private static final String SELECT_BY_NO_ART = "SELECT rue, code_postal, ville FROM retraits WHERE no_article= ?";
	
	@Override
	public void insert(Retrait r1) throws DALException {
		
		try(Connection conn = ConnectionProvider.getConnection()){  
			PreparedStatement pstmt = conn.prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1,r1.getRue());
			pstmt.setString(2,r1.getCodePostal());
			pstmt.setString(3,r1.getVille());
			pstmt.executeUpdate();
   
			ResultSet rs = pstmt.getGeneratedKeys();
   
			if(rs.next()){
				r1.setNoArticle(rs.getInt(1));
			}
  
		} catch (SQLException e) {
			throw new DALException("Erreur insert",e);
		}
	}

	@Override
	public void delete(Integer noArticle) throws DALException {
		
		try (Connection  conn = ConnectionProvider.getConnection();){	
			PreparedStatement pstmt = conn.prepareStatement(DELETE);
			
			pstmt.setInt(1, noArticle);
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" + noArticle, e);
		} 
	}

	@Override
	public void update(Retrait r1) throws DALException {
		
		try(Connection conn = ConnectionProvider.getConnection();){        
    		PreparedStatement pstmt = conn.prepareStatement(UPDATE);
    		
    		pstmt.setString(1,r1.getRue());
    		pstmt.setString(2,r1.getCodePostal());
    		pstmt.setString(3,r1.getVille());
    		
    		pstmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
            throw new DALException("Erreur update",e);
		}
		
	}

	@Override
	public List<Retrait> selectAll() throws DALException {
		List<Retrait> listeRetrait = new ArrayList<Retrait>();
		// ouverture et fermeture de la connection
		try (Connection conn = ConnectionProvider.getConnection();) {

			// ouverture de requete
			Statement stmt = conn.createStatement();
			// recuperation du tableau
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			Retrait retraitAjout = null;
			while (rs.next()) {
				// utilisation du conscruteur Categorie
				retraitAjout = new Retrait(rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
				listeRetrait.add(retraitAjout);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("erreur select all", e);
		}
		return listeRetrait;
	}

	@Override
	public Retrait selectByNoArt(Integer noArticle) throws DALException {
		try( Connection conn = ConnectionProvider.getConnection();){
			PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_NO_ART);

			pstmt.setInt(1, noArticle);

			//recuperation du tableau
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return new Retrait (rs.getInt("no_article"), rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"));
			} else  {
				throw new DALException("Mauvais ID");
			}
		} catch (SQLException e) {
			throw new DALException("selectById failed - id = " + noArticle, e);
		} 
	}
	
}
