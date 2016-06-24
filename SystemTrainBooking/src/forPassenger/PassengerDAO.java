package forPassenger;

import java.sql.SQLException;
/**
 * Interfaces to provide data on passengers, regardless of output: a file or database, or anywhere else.
 * @author LIO
 *
 */
public interface PassengerDAO {
/**
 * Record passenger in source storage.
 * @param passenger
 * @throws SQLException
 * @throws ClassNotFoundException
 */
	void write(Passenger passenger) throws SQLException, ClassNotFoundException;
/**
 * Verification of the presence of such user in some source storage.
 * @param passenger
 * @return
 * @throws SQLException
 */
	boolean checkUser(Passenger passenger) throws SQLException;
/**
 * Verification of the presence of such a system login in some source storage.
 * @param login
 * @return
 * @throws SQLException
 */
	boolean readCoincidence(String login) throws SQLException;
/**
 * Finding data about the user for their login in some source storage.
 * @param login
 * @return
 * @throws SQLException
 */
	Passenger retUser(String login) throws SQLException;
}
