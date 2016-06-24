package systemTrainBooking;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
/**
 * Model for storing data in our table with the search result Train.
 * @author LIO
 *
 */
public class TableModelTrain implements TableModel {

	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

	private boolean editable = false;
/**
 * List for storage all selected trains.
 */
	private List<Train> trains;

	public TableModelTrain(List<Train> trains) {
		this.trains = trains;

	}

	public TableModelTrain(List<Train> trains, boolean editable) {
		this.trains = trains;
		this.editable = editable;
	}

	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);
	}
/**
 * JTable needs to know what data it should be displayed in a column.
 */
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}
/**
 * Returns the number of columns to be displayed in the table.
 */
	public int getColumnCount() {
		return 8;
	}
/**
 * Returns the column header to its index.
 */
	public String getColumnName(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return "Train ¹";
		case 1:
			return "Train name";
		case 2:
			return "Date";
		case 3:
			return "Departure time";
		case 4:
			return "Arrival time";
		case 5:
			return "Dispatch station";
		case 6:
			return "Station of destination";
		case 7:
			return "Price";
		}
		return "";
	}
/**
 * Returns the number of rows to be displayed in the table.
 */
	public int getRowCount() {
		return trains.size();
	}
/**
 * Responsible for what data to JTable which cells will be displayed.
 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Train train = trains.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return train.getTrainNamber();
		case 1:
			return train.getTrainName();
		case 2:
			return train.getDate();
		case 3:
			return train.getDepartureTime();
		case 4:
			return train.getArrivalTime();
		case 5:
			return train.getDepartureStation();
		case 6:
			return train.getStationOfDestination();
		case 7:
			return train.getPrice();
		}
		return "";
	}
/**
 * Checks whether the cell is editable JTable row index and column is passed method as a parameter.
 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return editable;
	}

	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);
	}

	public void setValueAt(Object ob, int row, int col) {

	}

}
