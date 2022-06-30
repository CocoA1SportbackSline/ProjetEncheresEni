package fr.eni.ProjetEncheres.DAL.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import fr.eni.ProjetEncheres.BO.Enchere;

import fr.eni.ProjetEncheres.DAL.ConnectionProvider;
import fr.eni.ProjetEncheres.DAL.EnchereDAO;

public class EnchereDAOImpl implements EnchereDAO{
	
	private static final String INSERT = "INSERT INTO encheres (date_enchere, montant_enchere, no_article, no_utilisateur) VALUES (?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM encheres WHERE no_enchere = ?";
	private static final String UPDATE = "UPDATE encheres SET date_enchere=?, montant_enchere=?, no_article=?, no_utilisateur WHERE no_enchere=?";
	private static final String SELECT_ALL = "SELECT * FROM encheres";
	private static final String SELECT_BY_NO_ENCH = "SELECT rue, code_postal, ville FROM retraits WHERE no_article= ?";
	private static final String RECUP_ENCHERE_MAX ="SELECT TOP 1 * FROM ENCHERES WHERE no_article = ? ORDER BY montant_enchere DESC";
	
	@Override
	public void insert(Enchere e1) throws DALException {
		
		
		try(Connection conn = ConnectionProvider.getConnection()){  
			PreparedStatement pstmt = conn.prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
			
			//pstmt.set(1,e1.getDateEnchere());
			pstmt.setInt(2,e1.getMontantEnchere());
			pstmt.setInt(3,e1.getNoArticle());
			pstmt.setInt(4,e1.getNoArticle());
			pstmt.executeUpdate();
   
			ResultSet rs = pstmt.getGeneratedKeys();
   
			if(rs.next()){
				e1.setNoEnchere(rs.getInt(1));
			}
  
		} catch (SQLException e) {
			throw new DALException("Erreur insert",e);
		}
		
		
		
	}
	@Override
	public void delete(int noEnchere) throws DALException {

		try (Connection  conn = ConnectionProvider.getConnection();){	
			PreparedStatement pstmt = conn.prepareStatement(DELETE);
			
			pstmt.setInt(1, noEnchere);
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			throw new DALException("Delete ench�re failed - id=" + noEnchere, e);
		} 
		
	}
	@Override
	public void update(Enchere e1) throws DALException {
		
		try(Connection conn = ConnectionProvider.getConnection();){        
    		PreparedStatement pstmt = conn.prepareStatement(UPDATE);
    	
    		pstmt.setTimestamp(1,Timestamp.valueOf(e1.getDateEnchere()));
    		pstmt.setInt(2,e1.getMontantEnchere());
			pstmt.setInt(3,e1.getNoArticle());
			pstmt.setInt(4,e1.getNoArticle());
			pstmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
            throw new DALException("Erreur update",e);
		}
		
		
	}
	@Override
	public List<Enchere> selectAll() throws DALException {
		
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		// ouverture et fermeture de la connection
		try (Connection conn = ConnectionProvider.getConnection();) {

			// ouverture de requete
			Statement stmt = conn.createStatement();
			// recuperation du tableau
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			Enchere enchereAjout = null;
			while (rs.next()) {


		
			enchereAjout = new Enchere(rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur"));

				listeEnchere.add(enchereAjout);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("erreur select all", e);
		}
		return listeEnchere;
	
	}
	@Override
	public Enchere selectByNoEnch(int noEnchere) throws DALException {
		
		try( Connection conn = ConnectionProvider.getConnection();){
			PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_NO_ENCH);

			pstmt.setInt(1, noEnchere);

			//recuperation du tableau
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return new Enchere (rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur"));
			} else  {
				throw new DALException("Mauvais ID");
			}
		} catch (SQLException e) {
			throw new DALException("selectByNoEnch failed - id = " + noEnchere, e);
		} 
	}
	@Override
	public Enchere recupEnchereMax(int noEchere) throws DALException {

		Enchere enchere = null;

		
		try( Connection conn = ConnectionProvider.getConnection();){
			PreparedStatement pstmt = conn.prepareStatement(RECUP_ENCHERE_MAX);

			pstmt.setInt(1, noEchere);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				enchere = new Enchere(rs.getInt("no_enchere"), rs.getTimestamp("date_enchere").toLocalDateTime(), 
						rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur"));
			}

		} catch (SQLException e) {
			throw new DALException("echec methode recupEnchereMax");
		
		
	}
		return enchere;
	
	}
	
	
	
	
	
	
	
	
	
}
/*
	@Override
	public void insert(Enchere e1) throws DALException {
		
		try(Connection conn = ConnectionProvider.getConnection()){  
			PreparedStatement pstmt = conn.prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
			
			//pstmt.set(1,e1.getDateEnchere());
			pstmt.setInt(2,e1.getMontantEnchere());
			pstmt.setInt(3,e1.getNoArticle());
			pstmt.setInt(4,e1.getNoArticle());
			pstmt.executeUpdate();
   
			ResultSet rs = pstmt.getGeneratedKeys();
   
			if(rs.next()){
				e1.setNoEnchere(rs.getInt(1));
			}
  
		} catch (SQLException e) {
			throw new DALException("Erreur insert",e);
		}
		
	}

	@Override
	public void delete(Integer noEnchere) throws DALException {
		
		try (Connection  conn = ConnectionProvider.getConnection();){	
			PreparedStatement pstmt = conn.prepareStatement(DELETE);
			
			pstmt.setInt(1, noEnchere);
			pstmt.executeUpdate();
				
		} catch (SQLException e) {
			throw new DALException("Delete ench�re failed - id=" + noEnchere, e);
		} 
		
	}

	@Override
	public void update(Enchere e1) throws DALException {
		
		try(Connection conn = ConnectionProvider.getConnection();){        
    		PreparedStatement pstmt = conn.prepareStatement(UPDATE);
    	
    		pstmt.setTimestamp(1,Timestamp.valueOf(e1.getDateEnchere()));
    		pstmt.setInt(2,e1.getMontantEnchere());
			pstmt.setInt(3,e1.getNoArticle());
			pstmt.setInt(4,e1.getNoArticle());
			pstmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
            throw new DALException("Erreur update",e);
		}
		
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		List<Enchere> listeEnchere = new ArrayList<Enchere>();
		// ouverture et fermeture de la connection
		try (Connection conn = ConnectionProvider.getConnection();) {

			// ouverture de requete
			Statement stmt = conn.createStatement();
			// recuperation du tableau
			ResultSet rs = stmt.executeQuery(SELECT_ALL);

			Enchere enchereAjout = null;
			while (rs.next()) {

			enchereAjout = new Enchere(rs.getDate("date_enchere"), rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur"));
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git

		
			enchereAjout = new Enchere(rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur"));

				listeEnchere.add(enchereAjout);

>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("erreur select all", e);
		}
		return listeEnchere;
	}

	@Override
	public Enchere selectByNoEnch(Integer noEnchere) throws DALException {
		
		try( Connection conn = ConnectionProvider.getConnection();){
			PreparedStatement pstmt = conn.prepareStatement(SELECT_BY_NO_ENCH);

			pstmt.setInt(1, noEnchere);

			//recuperation du tableau
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return new Enchere (rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur"));
			} else  {
				throw new DALException("Mauvais ID");
			}
		} catch (SQLException e) {
			throw new DALException("selectByNoEnch failed - id = " + noEnchere, e);
		} 
	}
	@Override
	public Enchere recupEnchereMax(int id_article) throws DALException {
		PreparedStatement stmt = null;
		Connection con = null;
		Enchere enchere = null;

		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT TOP 1 * FROM ENCHERES WHERE no_article = ? ORDER BY montant_enchere DESC";
			
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id_article);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				enchere = new Enchere(rs.getInt("no_enchere"), rs.getTimestamp("date_enchere").toLocalDateTime(), 
						rs.getInt("montant_enchere"), rs.getInt("no_article"), rs.getInt("no_utilisateur"));
			}

		} catch (SQLException e) {
			throw new DALException("echec methode recupEnchereMax");
		} finally {
			try {
				ConnectionProvider.connectionClosed(con, stmt);
			} catch (fr.eni.ProjetEncheres.DAL.DALException e) {
				
				e.printStackTrace();
			}
		} 
		return enchere;
		
	}
	

}*/
