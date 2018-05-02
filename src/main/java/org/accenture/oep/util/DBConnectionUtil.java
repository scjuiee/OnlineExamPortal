package org.accenture.oep.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DBConnectionUtil {
	
	private static final Logger logger = LogManager.getLogger(DBConnectionUtil.class);
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getDBConnection() throws SQLException {
		// variables
		Connection connection = null;		

		// Step 1: Loading or registering Oracle JDBC driver class
		try {

			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			// Step 2: Opening database connection

			//String msAccDB = "C:/Satyasree/pwm1.accdb";
			String currentDirectory = new File("").getAbsolutePath();
			String msAccDB=currentDirectory +"/oep.accdb";
			logger.debug("DB Access Path:.."+msAccDB);
			String dbURL = "jdbc:ucanaccess://" + msAccDB;

			// Step 2.A: Create and get connection using DriverManager class
			connection = DriverManager.getConnection(dbURL+";jackcessOpener=org.accenture.oep.util.CryptCodecOpener","sa","fc411#");
			
			
			} catch (ClassNotFoundException ex) {
				logger.error("Problem in loading or " + "registering MS Access JDBC driver");
				
			}
		catch (Exception cnfex) {

			logger.error("Exception occurred while reading labels master table");
			cnfex.printStackTrace();
		}

		return connection;

	}
	
	
}
