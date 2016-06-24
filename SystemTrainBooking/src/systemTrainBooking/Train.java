package systemTrainBooking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * Stores information about the train.
 * @author LIO
 *
 */
public class Train {
	private int trainNumber;
	private String trainName;
	private String date;
	private String departureTime;
	private String arrivalTime;
	private String departureStation;
	private String stationOfDestination;
	private int price;
/** 
 * Default constructor.
 */
	public Train() {

	}
/**
 * Constructor to initialize the data on train.
 * @param trainNamber
 * @param trainName
 * @param date
 * @param departureTime
 * @param arrivalTime
 * @param departureStation
 * @param stationOfDestination
 * @param price
 */
	public Train(int trainNamber, String trainName, String date,
			String departureTime, String arrivalTime, String departureStation,
			String stationOfDestination, int price) {
		this.trainNumber = trainNamber;
		this.trainName = trainName;
		this.date = date;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.departureStation = departureStation;
		this.stationOfDestination = stationOfDestination;
		this.price = price;
	}
/**
 * Constructor to initialize some of the data that are used to find the right train.
 * @param departureStation
 * @param stationOfDestination
 * @param date
 */
	public Train(String departureStation, String stationOfDestination,
			String date) {
		this.departureStation = departureStation;
		this.stationOfDestination = stationOfDestination;
		this.date = date;
	}

	public void setTrainNamber(int trainNamber) {
		this.trainNumber = trainNamber;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setDepartureStation(String departureStation) {
		this.departureStation = departureStation;
	}

	public void setStationOfDestination(String stationOfDestination) {
		this.stationOfDestination = stationOfDestination;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTrainNamber() {
		return trainNumber;
	}

	public String getTrainName() {
		return trainName;
	}

	public String getDate() {
		return date;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public String getDepartureStation() {
		return departureStation;
	}

	public String getStationOfDestination() {
		return stationOfDestination;
	}

	public int getPrice() {
		return price;
	}
/**
 * Validations introduction date.
 * @param value
 * @param datePattern
 * @return
 */
	public static boolean isValidDate(String value, String datePattern) {

		if (value == null || datePattern == null || datePattern.length() <= 0
				|| value.length() > 10) {
			return false;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
		formatter.setLenient(false);

		try {
			formatter.parse(value);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public String toString() {
		return "Train [trainNumber=" + trainNumber + ", trainName=" + trainName
				+ ", date=" + date + ", departureTime=" + departureTime
				+ ", arrivalTime=" + arrivalTime + ", departureStation="
				+ departureStation + ", stationOfDestination="
				+ stationOfDestination + ", price=" + price + "]";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((arrivalTime == null) ? 0 : arrivalTime.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime
				* result
				+ ((departureStation == null) ? 0 : departureStation.hashCode());
		result = prime * result
				+ ((departureTime == null) ? 0 : departureTime.hashCode());
		result = prime * result + price;
		result = prime
				* result
				+ ((stationOfDestination == null) ? 0 : stationOfDestination
						.hashCode());
		result = prime * result
				+ ((trainName == null) ? 0 : trainName.hashCode());
		result = prime * result + trainNumber;
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Train other = (Train) obj;
		if (arrivalTime == null) {
			if (other.arrivalTime != null)
				return false;
		} else if (!arrivalTime.equals(other.arrivalTime))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (departureStation == null) {
			if (other.departureStation != null)
				return false;
		} else if (!departureStation.equals(other.departureStation))
			return false;
		if (departureTime == null) {
			if (other.departureTime != null)
				return false;
		} else if (!departureTime.equals(other.departureTime))
			return false;
		if (price != other.price)
			return false;
		if (stationOfDestination == null) {
			if (other.stationOfDestination != null)
				return false;
		} else if (!stationOfDestination.equals(other.stationOfDestination))
			return false;
		if (trainName == null) {
			if (other.trainName != null)
				return false;
		} else if (!trainName.equals(other.trainName))
			return false;
		if (trainNumber != other.trainNumber)
			return false;
		return true;
	}

}
