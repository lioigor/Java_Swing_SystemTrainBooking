package connections;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import systemTrainBooking.*;
/**
 * Read-write-delete data in a database table in the train.
 * @author LIO
 *
 */
public class TrainDBDAO implements TrainDAO {

	private ConnectDBMysql con;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
/**
 * Create a connection with the connection class.
 * @param parent
 */
	public TrainDBDAO(ConnectDBMysql parent) {
		con = parent;
	}
/**
 * {@inheritDoc}
 */
	public void write(Train train) throws SQLException {

		boolean result = false;
		try {
			preparedStatement = con
					.getDBConnection()
					.prepareStatement(
							"insert into  systemtrainbookingdb.trains values (?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, train.getTrainNamber());
			preparedStatement.setString(2, train.getTrainName());
			preparedStatement.setString(3, train.getDate());
			preparedStatement.setString(4, train.getDepartureTime());
			preparedStatement.setString(5, train.getArrivalTime());
			preparedStatement.setString(6, train.getDepartureStation());
			preparedStatement.setString(7, train.getStationOfDestination());
			preparedStatement.setInt(8, train.getPrice());
			preparedStatement.executeUpdate();

			preparedStatement.close();
		} catch (MySQLIntegrityConstraintViolationException micve) {
			result = true;
		}
		if (result) {
			train.setTrainNamber(train.getTrainNamber() + 1);
			this.write(train);
		}

	}
/**
 * {@inheritDoc}
 */
	public ArrayList<Train> find(Train train) throws SQLException {

		ArrayList<Train> trainsResult = new ArrayList<Train>();

		statement = con.getDBConnection().createStatement();
		ResultSet qresult1 = statement
				.executeQuery("SELECT * FROM systemtrainbookingdb.trains WHERE dispatchStation = "
						+ "'"
						+ train.getDepartureStation()
						+ "' and "
						+ "stationDestination = "
						+ "'"
						+ train.getStationOfDestination()
						+ "' and "
						+ "date = " + "'" + train.getDate() + "'");
		while (qresult1.next())
			trainsResult.add(new Train(qresult1.getInt("numberTrain"), qresult1
					.getString("trainName"), qresult1.getString("date"),
					qresult1.getString("departureTime"), qresult1
							.getString("arrivalTime"), qresult1
							.getString("dispatchStation"), qresult1
							.getString("stationDestination"), qresult1
							.getInt("price")));

		statement.close();

		return trainsResult;
	}
/**
 * {@inheritDoc}
 */
	public ArrayList<Train> showAll() throws SQLException {

		ArrayList<Train> trainsResult = new ArrayList<Train>();

		statement = con.getDBConnection().createStatement();
		ResultSet qresult1 = statement
				.executeQuery("SELECT * FROM systemtrainbookingdb.trains");
		while (qresult1.next())
			trainsResult.add(new Train(qresult1.getInt("numberTrain"), qresult1
					.getString("trainName"), qresult1.getString("date"),
					qresult1.getString("departureTime"), qresult1
							.getString("arrivalTime"), qresult1
							.getString("dispatchStation"), qresult1
							.getString("stationDestination"), qresult1
							.getInt("price")));

		statement.close();

		return trainsResult;
	}
/**
 * {@inheritDoc}
 */
	public void delete(Train train) throws SQLException {

		statement = con.getDBConnection().createStatement();
		statement
				.executeUpdate("DELETE FROM systemtrainbookingdb.trains WHERE numberTrain = "
						+ "'" + train.getTrainNamber() + "'");

		statement.close();

	}
/**
 * Method for close connect with database in ConnectDBMysql.
 * @throws SQLException
 */
	public void closeConnection() throws SQLException {
		con.closeDBConnection();
	}

}