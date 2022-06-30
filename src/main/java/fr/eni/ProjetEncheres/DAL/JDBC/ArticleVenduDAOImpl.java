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
			
			stmt.setInt(1, a.getNo_article());
			stmt.setString(2, a.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(a.getDate_debut_encheres()));
			stmt.setTimestamp(4, Timestamp.valueOf(a.getDate_debut_encheres())); 
			if(a.getImage()==null) {
				stmt.setNull(6, Types.VARCHAR);
			} else {
				stmt.setString(6, a.getImage());
			}
			stmt.setInt(7, a.getNo_utilisateur());
			stmt.setInt(8, a.getNo_categorie());
		
			
			stmt.executeUpdate();
			
			 ResultSet rs = stmt.getGeneratedKeys();
		      
		      if(rs.next()) {
		    	  a.setNo_article(rs.getInt(1));
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
			
			stmt.setString(1, a.getNom_article());
			stmt.setString(2, a.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(a.getDate_debut_encheres())); 
			stmt.setTimestamp(4, Timestamp.valueOf(a.getDate_fin_encheres())); 
			stmt.setInt(5, a.getPrix_initial());
			stmt.setInt(6, a.getPrix_vente());
			stmt.setString(7, a.getImage());
			stmt.setInt(8, a.getNo_utilisateur());
			stmt.setInt(9, a.getNo_categorie());
			
			
			stmt.setInt(11, a.getNo_article());
			
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	//inserer un nouvel article
	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_inital,no_utilisateur,no_categorie) values (?,?,?,?,?,?,?)";
	//select tous les articles
	private static final String SELECT_ALL = "SELECT * from ARTICLES_VENDUES ";
	// un article par noArticle
	private static final String SELECT_BY_ID = "SELECT * from ARTICLES_VENDUES WHERE no_article = ?";
	//Select par categorie
	private static final String SELECT_BY_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS INNER JOIN CATEGORIES c"
								+"on ARTICLES_VENDUES.no-categorie = c.no_categorie WHERE no_categorie = ?";
	// article en cours par nom et catégorie
	private static final String SELECT_EN_COURS_BY_NOM_BY_CATEGORIE = "SELECT * FORM ARTICLES_VENDUES WHERE (no_categorie = ? && nomArticle like ? && (date_debut_encheres != null) && (date_fin_encheres == null))";
	//toute les ventes en cours et finalisée par un vendeur
	private static final String SELECT_BY_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUES WHERE ( No_utilisateur= ? && (date_debut_encheres != null) && (date_fin_encheres != null)) values (?)";
	// toutes les encheres en cours
	private static final String SELECT_ENCHERES_EN_COURS = "SELECT * FROM ARTICLES_VENDUES WHERE (date_debut_encheres != null) && (date_fin_encheres == null)";
	//ventes en cours pour un vendeur
	private static final String SELECT_ENCHERE_EN_COURS_BY_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUES WHERE ( No_utilisateur= ? && (date_debut_encheres != null) && (date_fin_encheres == null)) values (?)";
	//ventes non commencées 
	private static final String SELECT_ENCHERE_NON_COMMENCEE_BY_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUES WHERE ( No_utilisateur= ? && (date_debut_encheres == null) && (date_fin_encheres == null)) values (?)";
	//vente terminee pour un vendeur
	private static final String SELECT_ENCHERE_TERMINEE_PAR_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUES WHERE ( No_utilisateur= ? && (date_debut_encheres != null) && (date_fin_encheres != null)) values (?)";
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public void insert(ArticleVendu ArticleVendu) throws DALException{
		
		//Etape 1 : se connecter la BD
		try ( //Try with resources
				Connection conn = ConnectionProvider.getConnection();
			){
			
			//Statement stmt = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			
			//TODO : faire l'insert
			//Valoriser les paramètres
			stmt.setString(1, ArticleVendu.getNomArticle());
			stmt.setString(2, ArticleVendu.getDescription());
			stmt.setTimestamp(3, Timestamp.valueOf(ArticleVendu.getDateDebutEncheres()));
			stmt.setTimestamp(3, Timestamp.valueOf(ArticleVendu.getDateFinEncheres()));
			stmt.setFloat(5, ArticleVendu.getMiseAPrix());
			stmt.setInt(6, ArticleVendu.getNoUtilisateur());
			stmt.setString(7, "a completer categorie");
			
			
			stmt.executeUpdate( );
			
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				ArticleVendu.setNoArticle(rs.getInt(1));
			}
			
			//conn.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			
			throw new DALException("erreur insert", e);
		
	}
	
	}
	
	public List<ArticleVendu> selectAll() throws DALException{
		
	try (Connection conn = ConnectionProvider.getConnection();){
		
		Statement stmt = conn.createStatement();
		ResultSet rs =  stmt.executeQuery(SELECT_ALL);
		
		List<ArticleVendu>tousLesArticles = new ArrayList<ArticleVendu>();
		
		while(rs.next()) {
			tousLesArticles.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
			}
	
		return tousLesArticles;	
		
	} catch (SQLException e) {
		
		throw new DALException("erreur select all", e);
	}	
	}

	
	public ArticleVendu selectByNoArticle (int noArticle)throws DALException{
		ArticleVendu articleVendu = null;
		
		// ouverture et fermeture de la connection
				try (Connection conn = ConnectionProvider.getConnection();) {

					// ouverture de requete
					PreparedStatement requet = conn.prepareStatement(SELECT_BY_ID);
					
					requet.setInt(1, noArticle);
					
					// recuperation du tableau
					ResultSet rs = requet.executeQuery();

					if (rs.next()) {
						articleVendu = new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie"));
					}else {
						throw new DALException("Article not found : "+noArticle);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DALException("erreur select by Id", e);
				}
				return articleVendu;
	}
	
	
	public List<ArticleVendu> selectVenteEnCoursByNomByCategorie(int noCategorie) throws DALException{
		
		List<ArticleVendu>articlesParCategorie = new ArrayList<ArticleVendu>();
		
		
		// ouverture et fermeture de la connection
		try (Connection conn = ConnectionProvider.getConnection();) {

			// ouverture de requete
			PreparedStatement requet = conn.prepareStatement(SELECT_EN_COURS_BY_NOM_BY_CATEGORIE);
			
			requet.setInt(1, noCategorie);
			
			// recuperation du tableau
			ResultSet rs = requet.executeQuery();

			if (rs.next()) {
				articlesParCategorie.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
			}else {
				throw new DALException("categorie not found : "+noCategorie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("erreur select by Id", e);
		}
		return articlesParCategorie;
}
	
public List<ArticleVendu> selectByUtilisateur(int noUtilisateur) throws DALException{
		
	List<ArticleVendu>articlesParUtilisateur = new ArrayList<ArticleVendu>();
	
	
	// ouverture et fermeture de la connection
	try (Connection conn = ConnectionProvider.getConnection();) {

		// ouverture de requete
		PreparedStatement requet = conn.prepareStatement(SELECT_BY_UTILISATEUR);
		
		requet.setInt(1, noUtilisateur);
		
		// recuperation du tableau
		ResultSet rs = requet.executeQuery();

		if (rs.next()) {
			articlesParUtilisateur.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
		}else {
			throw new DALException("categorie not found : "+noUtilisateur);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new DALException("erreur select by Id", e);
	}
	return articlesParUtilisateur;
}

public List<ArticleVendu> selectEnchereEnCours() throws DALException{
	
	try (Connection conn = ConnectionProvider.getConnection();){
		
		Statement stmt = conn.createStatement();
		ResultSet rs =  stmt.executeQuery(SELECT_ENCHERES_EN_COURS);
		
		List<ArticleVendu>tousLesArticles = new ArrayList<ArticleVendu>();
		
		while(rs.next()) {
			tousLesArticles.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
			}
	
		return tousLesArticles;	
		
	} catch (SQLException e) {
		
		throw new DALException("erreur select enchere en cours", e);
	}	
	}

public List<ArticleVendu> selectByUtilisateurEnCours(int noUtilisateur) throws DALException{
	
List<ArticleVendu>articlesParUtilisateur = new ArrayList<ArticleVendu>();
	
	
	// ouverture et fermeture de la connection
	try (Connection conn = ConnectionProvider.getConnection();) {

		// ouverture de requete
		PreparedStatement requet = conn.prepareStatement(SELECT_ENCHERE_EN_COURS_BY_UTILISATEUR);
		
		requet.setInt(1, noUtilisateur);
		
		// recuperation du tableau
		ResultSet rs = requet.executeQuery();

		if (rs.next()) {
			articlesParUtilisateur.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
		}else {
			throw new DALException("categorie not found : "+noUtilisateur);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new DALException("erreur select by utilisateur en cours", e);
	}
	return articlesParUtilisateur;
}

public List<ArticleVendu> selectByUtilisateurNonCommencee(int noUtilisateur) throws DALException{
	
List<ArticleVendu>articlesParUtilisateur = new ArrayList<ArticleVendu>();
	
	
	// ouverture et fermeture de la connection
	try (Connection conn = ConnectionProvider.getConnection();) {

		// ouverture de requete
		PreparedStatement requet = conn.prepareStatement(SELECT_ENCHERE_NON_COMMENCEE_BY_UTILISATEUR);
		
		requet.setInt(1, noUtilisateur);
		
		// recuperation du tableau
		ResultSet rs = requet.executeQuery();

		if (rs.next()) {
			articlesParUtilisateur.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
		}else {
			throw new DALException("categorie not found : "+noUtilisateur);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new DALException("erreur select by utilisateur", e);
	}
	return articlesParUtilisateur;
}
public List<ArticleVendu> selectByUtilisateurTerminee(int noUtilisateur) throws DALException{
	
List<ArticleVendu>articlesParUtilisateur = new ArrayList<ArticleVendu>();
	
	
	// ouverture et fermeture de la connection
	try (Connection conn = ConnectionProvider.getConnection();) {

		// ouverture de requete
		PreparedStatement requet = conn.prepareStatement(SELECT_ENCHERE_TERMINEE_PAR_UTILISATEUR);
		
		requet.setInt(1, noUtilisateur);
		
		// recuperation du tableau
		ResultSet rs = requet.executeQuery();

		if (rs.next()) {
			articlesParUtilisateur.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
		}else {
			throw new DALException("categorie not found : "+noUtilisateur);
		}
	} catch (SQLException e) {
		e.printStackTrace();
		throw new DALException("erreur select par utilisateur terminée", e);
	}
	return articlesParUtilisateur;
}
public List<ArticleVendu> selectByCategorie(Categorie categorie) throws DALException{
	List<ArticleVendu>tousLesArticles = new ArrayList<>();
	// ouverture et fermeture de la connection
	try (Connection conn = ConnectionProvider.getConnection();) {

		// ouverture de requete
		PreparedStatement requet = conn.prepareStatement(SELECT_BY_CATEGORIE);
		
		requet.setInt(1, categorie.getNoCategorie());
		
		// recuperation du tableau
		ResultSet rs = requet.executeQuery();
		
		while(rs.next()) {
			tousLesArticles.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
			}
	
		return tousLesArticles;	
		
	} catch (SQLException e) {
		
		throw new DALException("erreur Select vente par categorie", e);
	}	
}

*/


}
