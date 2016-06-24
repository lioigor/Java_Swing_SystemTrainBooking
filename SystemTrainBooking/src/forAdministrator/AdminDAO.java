package forAdministrator;

import java.sql.SQLException;
/**
 * Interfaces to provide data on admins, regardless of output: a file or database, or anywhere else.
 * @author LIO
 *
 */
public interface AdminDAO {
/**
 * Record administrator in source storage.
 * @param admin
 * @throws SQLException
 * @throws ClassNotFoundException
 */
	void write(Admin admin) throws SQLException, ClassNotFoundException;
/**
 * Verification of the presence of such administrator in some source storage.
 * @param admin
 * @return
 * @throws SQLException
 */
	boolean checkAdmin(Admin admin) throws SQLException;

}
