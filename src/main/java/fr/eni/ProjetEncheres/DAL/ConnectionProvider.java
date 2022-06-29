package fr.eni.ProjetEncheres.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {


private static DataSource dataSource;
	
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");

		} catch (NamingException exc ) {
			exc.printStackTrace();
			throw new RuntimeException("Connexion � la BD impossible");
		}		
	}
	
	public static Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
	
	public static void connectionClosed(Connection con, PreparedStatement stmt) throws DALException {
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
}

