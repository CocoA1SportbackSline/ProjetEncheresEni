package fr.eni.ProjetEncheres.DAL.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.BO.ArticleVendu;
import fr.eni.ProjetEncheres.BO.Categorie;
import fr.eni.ProjetEncheres.DAL.ArticleVenduDAO;
import fr.eni.ProjetEncheres.DAL.ConnectionProvider;
import fr.eni.ProjetEncheres.DAL.DALException;




public class ArticleVenduDAOImpl implements ArticleVenduDAO{
	
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


}
