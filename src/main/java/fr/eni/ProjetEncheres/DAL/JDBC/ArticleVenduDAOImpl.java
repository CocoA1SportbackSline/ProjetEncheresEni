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
import fr.eni.ProjetEncheres.DAL.ArticleVenduDAO;
import fr.eni.ProjetEncheres.DAL.ConnectionProvider;
import fr.eni.ProjetEncheres.DAL.DALException;




public class ArticleVenduDAOImpl implements ArticleVenduDAO{
	
	//inserer un nouvel article
	private static final String INSERT = "insert into ArticleVendu (nomArticle,description,date_debut_encheres,date_fin_encheres,prix_initale,no_utilisateur,no_categorie) values (?,?,?,?,?,?,?)";
	//select tous les articles
	private static final String SELECT_ALL = "SELECT * from ArticleVendu WHERE ";
	// un article par noArticle
	private static final String SELECT_BY_ID = "SELECT * from ArticleVendu WHERE noArticle = ?";
	// article en cours par nom et catégorie
	private static final String SELECT_EN_COURS_BY_NOM_BY_CATEGORIE = "SELECT * FORM ArticleVendu WHERE (noCategorie = ? && nomArticle = ? && (date_debut_encheres != null) && (date_fin_encheres == null))";
	//toute les ventes en cours et finalisée par un vendeur
	private static final String SELECT_BY_UTILISATEUR = "SELECT * FROM ArticleVendu WHERE ( NoUtilisateur= ? && (date_debut_encheres != null) && (date_fin_encheres != null)) values (?)";
	// toutes les encheres en cours
	private static final String SELECT_ENCHERES_EN_COURS = "SELECT * FROM ArticleVendu WHERE (date_debut_encheres != null) && (date_fin_encheres == null)";
	//ventes en cours pour un vendeur
	private static final String SELECT_ENCHERE_EN_COURS_BY_UTILISATEUR = "SELECT * FROM ArticleVendu WHERE ( NoUtilisateur= ? && (date_debut_encheres != null) && (date_fin_encheres == null)) values (?)";
	//ventes non commencées 
	private static final String SELECT_ENCHERE_NON_COMMENCEE_BY_UTILISATEUR = "SELECT * FROM ArticleVendu WHERE ( NoUtilisateur= ? && (date_debut_encheres == null) && (date_fin_encheres == null)) values (?)";
	//vente terminee pour un vendeur
	private static final String SELECT_ENCHERE_TERMINEE = "SELECT * FROM ArticleVendu WHERE ( NoUtilisateur= ? && (date_debut_encheres != null) && (date_fin_encheres != null)) values (?)";
	
	
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
						throw new DALException("Pizza not found : "+noArticle);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new DALException("erreur select by Id", e);
				}
				return articleVendu;
	}
	
	
	public List<ArticleVendu> selectVenteEnCoursByNomByCategorie() throws DALException{
		
		try (Connection conn = ConnectionProvider.getConnection();){
			
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(SELECT_EN_COURS_BY_NOM_BY_CATEGORIE);
			
			List<ArticleVendu>tousLesArticles = new ArrayList<ArticleVendu>();
			
			while(rs.next()) {
				tousLesArticles.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
				}
		
			return tousLesArticles;	
			
		} catch (SQLException e) {
			
			throw new DALException("erreur Select vente en cours", e);
		}	
		
		
	
	}
	
public List<ArticleVendu> selectByUtilisateur() throws DALException{
		
		try (Connection conn = ConnectionProvider.getConnection();){
			
			Statement stmt = conn.createStatement();
			ResultSet rs =  stmt.executeQuery(SELECT_BY_UTILISATEUR);
			
			List<ArticleVendu>tousLesArticles = new ArrayList<ArticleVendu>();
			
			while(rs.next()) {
				tousLesArticles.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
				}
		
			return tousLesArticles;	
			
		} catch (SQLException e) {
			
			throw new DALException("erreur Select vente par utilisateur", e);
		}	
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

public List<ArticleVendu> selectByUtilisateurEnCours() throws DALException{
	
	try (Connection conn = ConnectionProvider.getConnection();){
		
		Statement stmt = conn.createStatement();
		ResultSet rs =  stmt.executeQuery(SELECT_ENCHERE_EN_COURS_BY_UTILISATEUR);
		
		List<ArticleVendu>tousLesArticles = new ArrayList<ArticleVendu>();
		
		while(rs.next()) {
			tousLesArticles.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
			}
	
		return tousLesArticles;	
		
	} catch (SQLException e) {
		
		throw new DALException("erreur Select vente par utilisateur en cours", e);
	}	
}

public List<ArticleVendu> selectByUtilisateurNonCommencee() throws DALException{
	
	try (Connection conn = ConnectionProvider.getConnection();){
		
		Statement stmt = conn.createStatement();
		ResultSet rs =  stmt.executeQuery(SELECT_ENCHERE_NON_COMMENCEE_BY_UTILISATEUR);
		
		List<ArticleVendu>tousLesArticles = new ArrayList<ArticleVendu>();
		
		while(rs.next()) {
			tousLesArticles.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
			}
	
		return tousLesArticles;	
		
	} catch (SQLException e) {
		
		throw new DALException("erreur Select vente par utilisateur non commencee", e);
	}	
}
public List<ArticleVendu> selectByUtilisateurTerminee() throws DALException{
	
	try (Connection conn = ConnectionProvider.getConnection();){
		
		Statement stmt = conn.createStatement();
		ResultSet rs =  stmt.executeQuery(SELECT_ENCHERE_TERMINEE);
		
		List<ArticleVendu>tousLesArticles = new ArrayList<ArticleVendu>();
		
		while(rs.next()) {
			tousLesArticles.add(new ArticleVendu(rs.getString("nom"),rs.getString("description"),rs.getTimestamp("DateDebutEncheres").toLocalDateTime(),rs.getTimestamp("DateFinEncheres").toLocalDateTime(),rs.getFloat("MiseAPrix"),rs.getInt ("NoUtilisateur"),rs.getInt("noCategorie")));
			}
	
		return tousLesArticles;	
		
	} catch (SQLException e) {
		
		throw new DALException("erreur Select vente par utilisateur terminee", e);
	}	
}

}
