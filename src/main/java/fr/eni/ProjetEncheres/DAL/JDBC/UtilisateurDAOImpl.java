package fr.eni.ProjetEncheres.DAL.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ProjetEncheres.BO.Utilisateur;
import fr.eni.ProjetEncheres.DAL.ConnectionProvider;
import fr.eni.ProjetEncheres.DAL.DALException;
import fr.eni.ProjetEncheres.DAL.UtilisateurDAO;


public class UtilisateurDAOImpl implements UtilisateurDAO {



	@Override
	public void insert(Utilisateur u) throws DALException {
		final String INSERT_INFO =
				"INSERT INTO UTILISATEURS (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur)"
						+ " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement pstmt = null;
		Connection con = null;

		try {
			con = ConnectionProvider.getConnection();
			pstmt = con.prepareStatement(INSERT_INFO, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, u.getPseudo());
			pstmt.setString(2, u.getNom());
			pstmt.setString(3, u.getPrenom());
			pstmt.setString(4, u.getEmail());
			pstmt.setString(5, u.getTelephone());   
			pstmt.setString(6, u.getRue());
			pstmt.setInt(7, u.getCodePostal());
			pstmt.setString(8, u.getVille());
			pstmt.setString(9, u.getMotDePasse());
			pstmt.setInt(10, 100);
			pstmt.setByte(11, (byte)0);



			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();

			if(rs.next()) {
				u.setNoUtilisateur(rs.getInt(1));
			}      
		}
		catch(SQLException e){
			throw new DALException ("Erreur methode insert : " + u.toString());
		} finally {
			ConnectionProvider.connectionClosed(con, pstmt);
		}
	}

		
	@Override
	public List<Utilisateur> selectAll() throws DALException {
		List<Utilisateur> list = new ArrayList<>();
		PreparedStatement stmt = null;
		Connection con = null;

		try {

			con = ConnectionProvider.getConnection();

			String sql = "SELECT * FROM Utilisateurs";

			stmt = con.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {
				list.add(new Utilisateur(
						rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), 
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getInt("code_postal"), 
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getByte("administrateur")
						));
			}

		} catch (SQLException e) {
			throw new DALException("Echec method selectAll()");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		return list;
	}
	
	@Override
	public Utilisateur getselectByPseudo(String pseudo, String motDePasse) throws DALException {
		
		Utilisateur user = null;
		PreparedStatement stmt = null;
		Connection con = null;
		
		try {
			con = ConnectionProvider.getConnection();
			
			String sql = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur"
					+ " FROM Utilisateurs WHERE (pseudo=? OR email=?) AND mot_de_passe=?";
			
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, pseudo);
			stmt.setString(2, pseudo);
			stmt.setString(3, motDePasse);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				user = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"), 
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getInt("code_postal"), 
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"), rs.getByte("administrateur"));
			}
			
		} catch (SQLException e) {
			throw new DALException("Couche DAL - ");
		} finally {
			ConnectionProvider.connectionClosed(con, stmt);
		}
		
		return user;
	}

	@Override
	public Utilisateur getselectByID(int id) throws DALException {
		
     	Connection cnx=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Utilisateur utilisateur = null;
	    try {
			cnx = ConnectionProvider.getConnection();
			
			String sql = "select pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit from UTILISATEURS where no_utilisateur = ?;";
			stmt=cnx.prepareStatement(sql);
			stmt.setInt(1, id);
			rs=stmt.executeQuery();
		
			if (rs.next()){
			
				utilisateur = new Utilisateur();
				
				utilisateur.setNoUtilisateur(id);
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(Integer.parseInt(rs.getString("code_postal")));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));

			}
		}catch (SQLException e){
			throw new DALException ("Probleme - rechercherUtilisateur - " + e.getMessage());
		}finally{	
			ConnectionProvider.connectionClosed(cnx, stmt);	
		}
		return utilisateur;
	}
	
	@Override
	public void update(Utilisateur u) throws DALException {
		PreparedStatement stmt = null;
		Connection con = null;

		try {
			con = ConnectionProvider.getConnection();

			String sql = "UPDATE Utilisateurs SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, "
					+ "rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ? WHERE no_utilisateur=?";	

			stmt = con.prepareStatement(sql);

			stmt.setString(1, u.getPseudo());
			stmt.setString(2, u.getNom());
			stmt.setString(3, u.getPrenom());
			stmt.setString(4, u.getEmail());
			stmt.setString(5, u.getTelephone());
			stmt.setString(6, u.getRue());
			stmt.setString(7, String.valueOf(u.getCodePostal()));
			stmt.setString(8, u.getVille());
			stmt.setString(9, u.getMotDePasse());
			stmt.setInt(10, u.getCredit());

			stmt.setInt(11, u.getNoUtilisateur());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
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

				String sql = "DELETE FROM Utilisateurs WHERE no_utilisateur = ?";

				stmt = con.prepareStatement(sql);

				stmt.setInt(1, id);

				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new DALException("Echec method delete()");
			} finally {
				ConnectionProvider.connectionClosed(con, stmt);
			}

		}
	}
	

		
	
	
	
	

	

