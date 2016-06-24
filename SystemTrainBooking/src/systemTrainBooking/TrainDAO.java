package systemTrainBooking;

import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Interfaces to provide data on trains, regardless of output: a file or database, or anywhere else.
 * @author LIO
 *
 */
public interface TrainDAO {
/**
 * Record train in source storage.
 * @param train
 * @throws SQLException
 * @throws ClassNotFoundException
 */
	void write(Train train) throws SQLException, ClassNotFoundException;
/**
 * Find train in some source storage.
 * @param train
 * @return
 * @throws SQLException
 */
	ArrayList<Train> find(Train train) throws SQLException;
/**
 * Show all trains that contains in some source storage.
 * @return
 * @throws SQLException
 */
	ArrayList<Train> showAll() throws SQLException;
/**
 * Delete train in some source storage.
 * @param train
 * @throws SQLException
 */
	void delete(Train train) throws SQLException;
}
