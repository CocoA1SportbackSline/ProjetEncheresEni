package fr.eni.ProjetEncheres.DAL.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.BO.ArticleVendu;
import fr.eni.ProjetEncheres.BO.Categorie;
import fr.eni.ProjetEncheres.DAL.ArticleVenduDAO;
import fr.eni.ProjetEncheres.DAL.ConnectionProvider;
import fr.eni.ProjetEncheres.DAL.DALException;




public class ArticleVenduDAOImpl implements ArticleVenduDAO{

	@Override
	public void insert(ArticleVendu a) throws DALException {
		

		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, "
					+ "prix_initial, image_article, no_utilisateur, no_categorie, no_retrait) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, a.getNoArticle());
			stmt.setString(2, a.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(a.getDateDebutEncheres()));
			stmt.setTimestamp(4, Timestamp.valueOf(a.getDateDebutEncheres())); 
			if(a.getImage()==null) {
				stmt.setNull(6, Types.VARCHAR);
			} else {
				stmt.setString(6, a.getImage());
			}
			stmt.setInt(7, a.getNoUtilisateur());
			stmt.setInt(8, a.getNoCategorie());
		
			
			stmt.executeUpdate();
			
			 ResultSet rs = stmt.getGeneratedKeys();
		      
		      if(rs.next()) {
		    	  a.setNoArticle(rs.getInt(1));
		      }  
			
		} catch (SQLException e) {
			throw new DALException("methode insert : " + a.toString());
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
	
		
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		
		List<ArticleVendu> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, "
					+ "prix_initial, prix_vente, image_article, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS "
					+ "WHERE prix_vente IS NULL AND date_debut_encheres < CURRENT_TIMESTAMP";
			
			stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new ArticleVendu(
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getTimestamp("date_debut_encheres").toLocalDateTime(), rs.getTimestamp("date_fin_encheres").toLocalDateTime(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("image_article"),
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait")
						));
			}
			
		} catch (SQLException e) {
			throw new DALException("methode selectAll");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
		return list;
	
	}

	@Override
	public void update(ArticleVendu a) throws DALException {
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
					
			String sql = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?,"
					+ " prix_initial = ?, prix_vente = ?, image_article = ?, no_utilisateur = ?, no_categorie = ? WHERE no_article=?";	

			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, a.getNoArticle());
			stmt.setString(2, a.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(a.getDateDebutEncheres())); 
			stmt.setTimestamp(4, Timestamp.valueOf(a.getDateFinEncheres())); 
			stmt.setInt(5, a.getPrixInitial());
			stmt.setInt(6, a.getPrixVente());
			stmt.setString(7, a.getImage());
			stmt.setInt(8, a.getNoUtilisateur());
			stmt.setInt(9, a.getNoCategorie());
			
			
			stmt.setInt(11, a.getNoArticle());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new DALException("Echec method update()");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
	}

	@Override
	public void delete(int id) throws DALException {
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			
			con = ConnectionProvider.getConnection();
			
			String sql = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			stmt.executeUpdate();
		
		} catch (SQLException e) {
			throw new DALException("Echec method delete()");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
	}

	@Override
	public ArticleVendu selectByID(int id) throws DALException {
		
		ArticleVendu article = null;
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				article = new ArticleVendu(
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getTimestamp("date_debut_encheres").toLocalDateTime(), rs.getTimestamp("date_fin_encheres").toLocalDateTime(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("image_article"),
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait")
						);
			}
			
		} catch (SQLException e) {
			throw new DALException("methode selectById");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}

		return article;
		
	}

	@Override
	public List<ArticleVendu> selectByNoCategorie(int idCategorie) throws DALException {
		List<ArticleVendu> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, "
					+ "prix_initial, prix_vente, image_article, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS "
					+ "WHERE no_categorie=? AND prix_vente IS NULL AND date_debut_encheres < CURRENT_TIMESTAMP";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, idCategorie);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new ArticleVendu(
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getTimestamp("date_debut_encheres").toLocalDateTime(), rs.getTimestamp("date_fin_encheres").toLocalDateTime(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("image_article"),
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait")
						));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("methode selectByNoCategorie");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}

		
		return list;
	}

	// Renvoi liste d'article non vendu avec id categorie et mot cle dans nom article et description
	@Override
	public List<ArticleVendu> selectByKeyWordAndNoCategorie(String keyWord, int idCategorie) throws DALException {
		List<ArticleVendu> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, "
					+ "prix_initial, prix_vente, image_article, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS "
					+ "WHERE no_categorie=? AND (nom_article LIKE '%' + ? + '%' OR description LIKE '%' + ? + '%') AND prix_vente IS NULL "
					+ "AND date_debut_encheres < CURRENT_TIMESTAMP";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, idCategorie);
			stmt.setString(2, keyWord);
			stmt.setString(3, keyWord);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new ArticleVendu(
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getTimestamp("date_debut_encheres").toLocalDateTime(), rs.getTimestamp("date_fin_encheres").toLocalDateTime(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("image_article"),
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait")
						));
			}
			
		} catch (SQLException e) {
			throw new DALException("methode selectByKeyWordAndNoCategorie");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
		return list;
	}

	@Override
	public List<ArticleVendu> selectByKeyWord(String keyWord) throws DALException {
		List<ArticleVendu> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, "
					+ "prix_initial, prix_vente, image_article, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS "
					+ "WHERE (nom_article LIKE '%' + ? + '%' OR description LIKE '%' + ? + '%') AND prix_vente IS NULL "
					+ "AND date_debut_encheres < CURRENT_TIMESTAMP";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, keyWord);
			stmt.setString(2, keyWord);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new ArticleVendu(
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getTimestamp("date_debut_encheres").toLocalDateTime(), rs.getTimestamp("date_fin_encheres").toLocalDateTime(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("image_article"),
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait")
						));
			}
			
		} catch (SQLException e) {
			throw new DALException("methode selectByKeyWord");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}

		return list;
		
	}

	@Override
	public List<ArticleVendu> selectAllBeforeDate(int idUtilisateur) throws DALException {
		List<ArticleVendu> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, "
					+ "prix_initial, prix_vente, image_article, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS "
					+ "WHERE date_debut_encheres > CURRENT_TIMESTAMP AND no_utilisateur=?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, idUtilisateur);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new ArticleVendu(
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getTimestamp("date_debut_encheres").toLocalDateTime(), rs.getTimestamp("date_fin_encheres").toLocalDateTime(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("image_article"),
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait")
						));
			}
			
		} catch (SQLException e) {
			throw new DALException("methode selectAllBeforeDate");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}

		return list;
	
	}

	@Override
	public List<ArticleVendu> selectAllBetweenDate(int idUtilisateur) throws DALException {
		List<ArticleVendu> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, "
					+ "prix_initial, prix_vente, image_article, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS "
					+ "WHERE date_debut_encheres < CURRENT_TIMESTAMP AND prix_vente IS NULL AND no_utilisateur=?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, idUtilisateur);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new ArticleVendu(
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getTimestamp("date_debut_encheres").toLocalDateTime(), rs.getTimestamp("date_fin_encheres").toLocalDateTime(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("image_article"),
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait")
						));
			}
			
		} catch (SQLException e) {
			throw new DALException("methode selectAllBetweenDate");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}

		return list;
	}

	@Override
	public List<ArticleVendu> selectAllSoldOut(int idUtilisateur) throws DALException {
		List<ArticleVendu> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, "
					+ "prix_initial, prix_vente, image_article, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS "
					+ "WHERE prix_vente IS NOT NULL AND no_utilisateur=?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, idUtilisateur);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new ArticleVendu(
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getTimestamp("date_debut_encheres").toLocalDateTime(), rs.getTimestamp("date_fin_encheres").toLocalDateTime(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("image_article"),
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait")
						));
			}
			
		} catch (SQLException e) {
			throw new DALException("methode selectAllSoldOut");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}

		return list;
	
	}

	@Override
	public List<ArticleVendu> selectAllSoldOut() throws DALException {
		List<ArticleVendu> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement stmt = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, "
					+ "prix_initial, prix_vente, image_article, no_utilisateur, no_categorie, no_retrait FROM ARTICLES_VENDUS "
					+ "WHERE prix_vente IS NOT NULL";
			
			stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				list.add(new ArticleVendu(
						rs.getInt("no_article"), rs.getString("nom_article"), rs.getString("description"), 
						rs.getTimestamp("date_debut_encheres").toLocalDateTime(), rs.getTimestamp("date_fin_encheres").toLocalDateTime(), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getString("image_article"),
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait")
						));
			}
			
		} catch (SQLException e) {
			throw new DALException("methode selectAllSoldOut");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}

		return list;
	}
}
