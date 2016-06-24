package connections;

import java.sql.*;
/**
 * Class to connect to the Mysql database.
 * @author LIO
 *
 */
public class ConnectDBMysql {
	/**
	 * Field specifically for return connection to other classes.
	 */
	private Connection con = null;
/**
 * Creating a database connection with specific parameters.
 * @throws ClassNotFoundException
 * @throws SQLException
 */
	public ConnectDBMysql() throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/?user=igor";
		String login = "igor";
		String pass = "2204";
		Class.forName(driver);
		con = DriverManager.getConnection(url, login, pass);

	}
/**
 * Creating a database connection with specific parameters for a special, different connection.
 * @param driver
 * @param url
 * @param login
 * @param pass
 * @throws ClassNotFoundException
 * @throws SQLException
 */
	public ConnectDBMysql(String driver, String url, String login, String pass)
			throws ClassNotFoundException, SQLException {

		Class.forName(driver);
		con = DriverManager.getConnection(url, login, pass);

	}
/**
 * Close connection with mysql database.
 * @throws SQLException
 */
	public void closeDBConnection() throws SQLException {

		con.close();

	}
/**
 * This method return connection for other classes for manipulation with database.
 * @return
 */
	public Connection getDBConnection() {
		return con;
	}
}
