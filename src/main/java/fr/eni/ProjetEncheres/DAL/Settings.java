package fr.eni.ProjetEncheres.DAL;

import java.io.IOException;
import java.util.Properties;

public class Settings {
	
private static Properties properties;
	
	//bloc initialisation
	static {
		properties = new Properties();
		try {
			properties.load(Settings.class.getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProperty (String key) {
		return properties.getProperty(key);
	}

}
