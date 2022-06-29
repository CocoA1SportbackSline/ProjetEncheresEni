package fr.eni.ProjetEncheres.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git


>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git

private static DataSource dataSource;
	
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");

		} catch (NamingException exc ) {
			exc.printStackTrace();
<<<<<<< HEAD
			throw new RuntimeException("Connexion � la BD impossible");
		}		
	}
	
	public static Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
	
	public static void connectionClosed(Connection con, PreparedStatement stmt) throws DALException {
=======
			throw new RuntimeException("Connexion la BD impossible");
		}		
	}
	
	public static Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
<<<<<<< HEAD
	}	


	
=======
	}
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
	
	
public static void connectionClosed(Connection con, PreparedStatement stmt) throws DALException {
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
		try {
			if(stmt != null) {
				stmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			throw new DALException("Erreur fermeture connexion");
		}
	}
<<<<<<< HEAD
}

<<<<<<< HEAD
=======

=======
}
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
