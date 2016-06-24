package connections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import forAdministrator.*;
/**
 * Write-check data in a database table in the administrator.
 * @author LIO
 *
 */
public class AdminDBDAO implements AdminDAO {

	private ConnectDBMysql con;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
/**
 * Create a connection with the connection class.
 * @param parent
 */
	public AdminDBDAO(ConnectDBMysql parent) {
		con = parent;
	}
/**
 * {@inheritDoc}
 */
	public void write(Admin admin) throws SQLException {

		boolean result = false;
		try {
			preparedStatement = con
					.getDBConnection()
					.prepareStatement(
							"insert into  systemtrainbookingdb.adminaccounts values (?, ?, ?)");
			preparedStatement.setLong(1, admin.getId());
			preparedStatement.setString(2, admin.getLogin());
			preparedStatement.setString(3, admin.getPassword());
			preparedStatement.executeUpdate();

			preparedStatement.close();
		} catch (MySQLIntegrityConstraintViolationException micve) {
			result = true;
		}
		if (result) {
			admin.setId(admin.getId() + 1);
			this.write(admin);
		}

	}
/**
 * {@inheritDoc}
 */
	public boolean checkAdmin(Admin admin) throws SQLException {

		boolean result = false, isLogin = false, isPassword = false;

		statement = con.getDBConnection().createStatement();
		ResultSet qresult = statement
				.executeQuery("SELECT * FROM systemtrainbookingdb.adminaccounts");
		ArrayList<String> logins = new ArrayList<String>();
		while (qresult.next())
			logins.add(qresult.getString("adminLogin"));
		isLogin = logins.contains(admin.getLogin());

		ResultSet qresult2 = statement
				.executeQuery("SELECT * FROM systemtrainbookingdb.adminaccounts");
		ArrayList<String> passwords = new ArrayList<String>();
		while (qresult2.next())
			passwords.add(qresult2.getString("adminPassword"));
		isPassword = passwords.contains(admin.getPassword());

		statement.close();

		if (isLogin && isPassword) {
			result = true;
		} else
			result = false;

		return result;

	}
/**
 * Method for close connect with database in ConnectDBMysql.
 * @throws SQLException
 */
	public void closeConnection() throws SQLException {
		con.closeDBConnection();
	}
}
