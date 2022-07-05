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
	private static final String DELETE = "DELETE FROM retraits WHERE no_retrait = ?";
	private static final String UPDATE = "UPDATE retraits SET rue=?, code_postal=?, ville=? WHERE no_retrait=?";
	private static final String SELECT_ALL = "SELECT * FROM retraits";
	private static final String SELECT_BY_NO_ART = "SELECT no_retrait,rue, code_postal, ville FROM retraits WHERE no_retrait= ?";
	
	
	@Override
	public void insert(Retrait r1) throws DALException {
		
		try(Connection conn = ConnectionProvider.getConnection()){  
			PreparedStatement pstmt = conn.prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstmt.setString(1,r1.getRue());
			pstmt.setInt(2,r1.getCodePostal());
			pstmt.setString(3,r1.getVille());
			pstmt.executeUpdate();
   
			ResultSet rs = pstmt.getGeneratedKeys();
   
			if(rs.next()){
				r1.setNoRetrait(rs.getInt(1));
			}
  
		} catch (SQLException e) {
			throw new DALException("Erreur insert",e);
		}
	}
		
	
	@Override
	public void delete(int id) throws DALException {
		
		try (Connection  conn = ConnectionProvider.getConnection();){	
			PreparedStatement pstmt = conn.prepareStatement(DELETE);
			
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" );
		} 
	}
		
	
	@Override
	public void update(Retrait r1) throws DALException {
		
		try(Connection conn = ConnectionProvider.getConnection();){        
    		PreparedStatement pstmt = conn.prepareStatement(UPDATE);
    		
    		pstmt.setString(1,r1.getRue());
    		pstmt.setInt(2,r1.getCodePostal());
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

			while (rs.next()) {
				listeRetrait.add(new Retrait(
						rs.getInt("no_retrait")	,rs.getString("rue"),rs.getInt("code_postal"), rs.getString("ville"))
						);
			}		

		} catch (SQLException e) {
			throw new DALException("Echec method selectAll()");
		
		}
		
		return listeRetrait;
			
	}
	@Override
	public Retrait selectById(int id) throws DALException {
		Retrait retrait = null;
		try( Connection conn = ConnectionProvider.getConnection();){
			PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_NO_ART);

			pstmt.setInt(1, id);

			//recuperation du tableau
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()){

				retrait = new Retrait();
				
				retrait.setNoRetrait(rs.getInt("no_retrait"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(Integer.parseInt(rs.getString("code_postal")));
				retrait.setVille(rs.getString("ville"));

			}

		} catch (SQLException e){

			throw new DALException ("Probleme - retrait - " + e.getMessage());
		}	
		
		return retrait;
	}
}
