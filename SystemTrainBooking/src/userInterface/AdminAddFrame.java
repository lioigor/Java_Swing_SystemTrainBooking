package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import systemTrainBooking.*;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import connections.ConnectDBMysql;
import connections.TrainDBDAO;
/**
 * Auxiliary window to add a new train to the database.
 * @author LIO
 *
 */
public class AdminAddFrame extends JFrame implements ActionListener {

	private JTextField numTrain;
	private JTextField trainName;
	private JTextField date;
	private JTextField departureTime;
	private JTextField arrTime;
	private JTextField depStation;
	private JTextField destStation;
	private JTextField price;
	private JButton ok;
	private JButton cancel;
/**
 * Form with fields for entering data.
 */
	public AdminAddFrame() {

		super("Input new train");

		Box box1 = Box.createHorizontalBox();
		JLabel numLabel = new JLabel("Input train #:");
		numTrain = new JTextField(10);
		box1.add(numLabel);
		box1.add(Box.createHorizontalStrut(6));
		box1.add(numTrain);

		Box box2 = Box.createHorizontalBox();
		JLabel trainNameLabel = new JLabel("Train name:");
		trainName = new JPasswordField(10);
		box2.add(trainNameLabel);
		box2.add(Box.createHorizontalStrut(6));
		box2.add(trainName);

		Box box3 = Box.createHorizontalBox();
		JLabel dateLabel = new JLabel("Date:");
		date = new JTextField(10);
		box3.add(dateLabel);
		box3.add(Box.createHorizontalStrut(6));
		box3.add(date);

		Box box4 = Box.createHorizontalBox();
		JLabel depTimeLabel = new JLabel("Departure time:");
		departureTime = new JTextField(10);
		box4.add(depTimeLabel);
		box4.add(Box.createHorizontalStrut(6));
		box4.add(departureTime);

		Box box5 = Box.createHorizontalBox();
		JLabel arrTimeLabel = new JLabel("Arrival time:");
		arrTime = new JTextField(10);
		box5.add(arrTimeLabel);
		box5.add(Box.createHorizontalStrut(6));
		box5.add(arrTime);

		Box box6 = Box.createHorizontalBox();
		JLabel depStationLabel = new JLabel("Departure station:");
		depStation = new JTextField(10);
		box6.add(depStationLabel);
		box6.add(Box.createHorizontalStrut(6));
		box6.add(depStation);

		Box box7 = Box.createHorizontalBox();
		JLabel destStationLabel = new JLabel("Destination station:");
		destStation = new JTextField(10);
		box7.add(destStationLabel);
		box7.add(Box.createHorizontalStrut(6));
		box7.add(destStation);

		Box box8 = Box.createHorizontalBox();
		JLabel priceLabel = new JLabel("Price:");
		price = new JTextField(10);
		box8.add(priceLabel);
		box8.add(Box.createHorizontalStrut(6));
		box8.add(price);

		Box box9 = Box.createHorizontalBox();
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
		box9.add(ok);
		box9.add(Box.createHorizontalStrut(12));
		box9.add(cancel);

		depTimeLabel.setPreferredSize(destStationLabel.getPreferredSize());
		trainNameLabel.setPreferredSize(depTimeLabel.getPreferredSize());
		numLabel.setPreferredSize(depTimeLabel.getPreferredSize());
		dateLabel.setPreferredSize(depTimeLabel.getPreferredSize());
		depTimeLabel.setPreferredSize(depTimeLabel.getPreferredSize());
		arrTimeLabel.setPreferredSize(depTimeLabel.getPreferredSize());
		depStationLabel.setPreferredSize(depTimeLabel.getPreferredSize());
		priceLabel.setPreferredSize(depTimeLabel.getPreferredSize());

		Box mainBox = Box.createVerticalBox();
		mainBox.add(box1);
		mainBox.add(Box.createVerticalStrut(8));
		mainBox.add(box2);
		mainBox.add(Box.createVerticalStrut(8));
		mainBox.add(box3);
		mainBox.add(Box.createVerticalStrut(8));
		mainBox.add(box4);
		mainBox.add(Box.createVerticalStrut(8));
		mainBox.add(box5);
		mainBox.add(Box.createVerticalStrut(8));
		mainBox.add(box6);
		mainBox.add(Box.createVerticalStrut(8));
		mainBox.add(box7);
		mainBox.add(Box.createVerticalStrut(8));
		mainBox.add(box8);
		mainBox.add(Box.createVerticalStrut(8));
		mainBox.add(box9);

		add(mainBox);

		ok.addActionListener(this);
		cancel.addActionListener(this);

		pack();
		setSize(300, 300);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();

		if (src == ok) {
			if (numTrain.getText().equals("") || trainName.getText().equals("")
					|| date.getText().equals("")
					|| departureTime.getText().equals("")
					|| arrTime.getText().equals("")
					|| depStation.getText().equals("")
					|| destStation.getText().equals("")
					|| price.getText().equals("")) {
				JOptionPane.showConfirmDialog(null,
						"Empty field , enter data please", "Empty field",
						JOptionPane.OK_OPTION);
			} else {
				Train train = new Train();
				boolean error = false;
				try {
					train = new Train(Integer.parseInt(numTrain.getText()),
							trainName.getText(), date.getText(),
							departureTime.getText(), arrTime.getText(),
							depStation.getText(), destStation.getText(),
							Integer.parseInt(price.getText()));
				} catch (NumberFormatException nfe) {
					JOptionPane.showConfirmDialog(null,
							"Fields number and price must be numeric",
							"Error number format", JOptionPane.OK_OPTION);
					error = true;
				}
				if (!error) {
					boolean check = true;
					try {
						ConnectDBMysql cdb = new ConnectDBMysql();
						TrainDBDAO tdb = new TrainDBDAO(cdb);
						tdb.write(train);
						tdb.closeConnection();
					} catch (SQLException sql) {
						JOptionPane
								.showConfirmDialog(null,
										"Error connecting to database",
										"Sorry just try again!",
										JOptionPane.YES_OPTION);
						check = false;
					} catch (ClassNotFoundException cnf) {
						JOptionPane.showConfirmDialog(null,
								"Cannot find db driver classes",
								"Error with database connect",
								JOptionPane.OK_OPTION);
						check = false;
					}
					if (check) {
						JOptionPane.showConfirmDialog(null,
								"This train has been add to database",
								"Successful record", JOptionPane.OK_OPTION);
					} else
						JOptionPane.showConfirmDialog(null,
								"This train don't add to database",
								"Not successful record", JOptionPane.OK_OPTION);
				}
			}
		} else if (src == cancel) {
			dispose();
		}
	}
}
