package fr.eni.ProjetEncheres.DAL;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
<<<<<<< HEAD

private static DataSource dataSource;
	
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");

		} catch (NamingException exc ) {
			exc.printStackTrace();
			throw new RuntimeException("Connexion à la BD impossible");
		}		
	}
	
	public static Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}
	
	
}
=======
	
private static DataSource dataSource;
	
	static {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");

		} catch (NamingException exc ) {
			exc.printStackTrace();
			throw new RuntimeException("Connexion Ã  la BD impossible");
		}		
	}
	
	public static Connection getConnection() throws SQLException
	{
		return dataSource.getConnection();
	}


}
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
