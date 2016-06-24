package connections;

import java.sql.*;
import java.util.*;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import forPassenger.Passenger;
import forPassenger.PassengerDAO;
/**
 * Read-write-check data in a database table in the passenger.
 * @author LIO
 *
 */
public class PassengerDBDAO implements PassengerDAO {

	private ConnectDBMysql con;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
/**
 * Create a connection with the connection class.	
 * @param parent
 */
	public PassengerDBDAO(ConnectDBMysql parent){
		con = parent;
	}
/**
 * {@inheritDoc}
 */
     public void write(Passenger passenger) throws SQLException{

			boolean result = false;
			try {
				preparedStatement = con.getDBConnection()
						.prepareStatement("insert into  systemtrainbookingdb.usersaccounts values (?, ?, ?, ?, ?, ?)");
				preparedStatement.setLong(1, passenger.getId());
				preparedStatement.setString(2, passenger.getLogin());
				preparedStatement.setString(3, passenger.getPassword());
				preparedStatement.setString(4, passenger.getName());
				preparedStatement.setString(5, passenger.getSurName());
				preparedStatement.setString(6, passenger.getBankAccount());
				preparedStatement.executeUpdate();

				preparedStatement.close();
			} catch(MySQLIntegrityConstraintViolationException micve){
				result = true;
			}
			if(result){
				passenger.setId(passenger.getId() + 1);
				this.write(passenger);
			}
			
    }
/**
 * {@inheritDoc}
 */
    public boolean checkUser(Passenger passenger) throws SQLException{
    	 
    	boolean result = false, isLogin = false, isPassword = false;
    	
    	 statement = con.getDBConnection().createStatement();
			ResultSet qresult = statement
					.executeQuery("SELECT * FROM systemtrainbookingdb.usersaccounts ");
			ArrayList<String> logins = new ArrayList<String>();
			while (qresult.next())
				logins.add(qresult.getString("userLogin"));
				isLogin = logins.contains(passenger.getLogin());
			
			ResultSet qresult2 = statement
						.executeQuery("SELECT * FROM systemtrainbookingdb.usersaccounts");
			ArrayList<String> passwords = new ArrayList<String>();
			while(qresult2.next())
				passwords.add(qresult2.getString("userPassword"));
				isPassword = passwords.contains(passenger.getPassword());
			
			statement.close();
			
			if(isLogin && isPassword){
				result = true;
			}
			else
				result = false;
			
			return result;
    	 
    }
/**
 * {@inheritDoc}
 */
	public boolean readCoincidence(String login) throws SQLException{
		boolean result = false;
			statement = con.getDBConnection().createStatement();
			ResultSet qresult = statement
					.executeQuery("SELECT * FROM systemtrainbookingdb.usersaccounts");
			ArrayList<String> logins = new ArrayList<String>();
			while (qresult.next())
				logins.add(qresult.getString("userLogin"));

			result = logins.contains(login);

			statement.close();
			
		return result;
	}
/**
 * {@inheritDoc}
 */
	 public Passenger retUser(String login) throws SQLException{
	    	
	    	 statement = con.getDBConnection().createStatement();
				ResultSet qresult = statement
						.executeQuery("SELECT * FROM systemtrainbookingdb.usersaccounts WHERE userLogin = " + "'" + login + "'");
			ArrayList<Passenger> result = new ArrayList<Passenger>();
				while (qresult.next())
					result.add(new Passenger(qresult.getInt("userID"), qresult.getString("userLogin"),
							qresult.getString("userPassword"), qresult.getString("userName"), qresult.getString("userSurName"),
							qresult.getString("bankAccount")));
				
				statement.close();
				
			return result.get(0);
	    	 
	    }
/**
 * Method for close connect with database in ConnectDBMysql.	 
 * @throws SQLException
 */
	public void closeConnection() throws SQLException{
		con.closeDBConnection();
	}
	 
}
