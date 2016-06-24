package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import connections.*;
import forPassenger.*;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;

import systemTrainBooking.*;
/**
 * Window to display the results on request. Shows the desired train and gives an invoice when choosing...
 * @author LIO
 *
 */
public class FindingRightTrainFrame extends JFrame implements ActionListener {

	static final long serialVersionUID = 2L;
	static int i = 0;
	private Train trainforFind;
	private String currentUser;
	private JButton pay;
	private JButton back;
	private JButton ok;
	private JTextArea display;
	private ArrayList<Train> trains = new ArrayList<Train>();
	private int currentSelectedRow;
/**
 * Drawing window display the results table.
 * Receives as parameters the train to be searched and the current user.
 * @param train
 * @param currentUser
 */
	public FindingRightTrainFrame(Train train, String currentUser) {

		super("Train satisfy your request");
		this.trainforFind = train;
		this.currentUser = currentUser;

		if (!trainforFind.getDepartureStation().equals("all")) {
			try {
				ConnectDBMysql cdb = new ConnectDBMysql();
				TrainDBDAO tdb = new TrainDBDAO(cdb);
				trains = tdb.find(train);
				tdb.closeConnection();
			} catch (SQLException sql) {
				JOptionPane.showConfirmDialog(null,
						"Error connecting to database",
						"Sorry just try again!", JOptionPane.YES_OPTION);
			} catch (ClassNotFoundException cnf) {
				JOptionPane.showConfirmDialog(null,
						"Cannot find db driver classes",
						"Error with database connect", JOptionPane.OK_OPTION);
			}
		} else {
			try {
				ConnectDBMysql cdb = new ConnectDBMysql();
				TrainDBDAO tdb = new TrainDBDAO(cdb);
				trains = tdb.showAll();
				tdb.closeConnection();
			} catch (SQLException sql) {
				JOptionPane.showConfirmDialog(null,
						"Error connecting to database",
						"Sorry just try again!", JOptionPane.YES_OPTION);
			} catch (ClassNotFoundException cnf) {
				JOptionPane.showConfirmDialog(null,
						"Cannot find db driver classes",
						"Error with database connect", JOptionPane.OK_OPTION);
			}
		}
		if (trains.size() == 0) {
			JOptionPane.showConfirmDialog(null, "Your search returns nothing!",
					"Nothing found", JOptionPane.OK_OPTION);
			new MakeApplicationFrame(currentUser);
		} else {
			TableModel model = new TableModelTrain(trains);
			JTable table = new JTable(model);
			table.setSelectionForeground(Color.BLUE);

			ListSelectionModel lm = new DefaultListSelectionModel();
			lm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setSelectionModel(lm);
			ListSelectionModel selModel = table.getSelectionModel();

			selModel.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {

					currentSelectedRow = table.getSelectedRow();
				}
			});

			pay = new JButton("Pay");
			back = new JButton("Back");
			ok = new JButton("Ok");
			JPanel panel1 = new JPanel();
			TitledBorder tb1 = new TitledBorder("Available actions");
			panel1.setBorder(tb1);
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
			panel1.add(Box.createHorizontalGlue());
			pay.setFocusable(false);
			pay.setEnabled(false);
			ok.setFocusable(false);
			pay.setPreferredSize(back.getPreferredSize());
			ok.setPreferredSize(pay.getPreferredSize());
			panel1.add(ok);
			panel1.add(Box.createHorizontalStrut(20));
			panel1.add(pay);
			panel1.add(Box.createHorizontalStrut(20));
			back.setPreferredSize(pay.getPreferredSize());
			back.setFocusable(false);
			panel1.add(back);
			panel1.add(Box.createVerticalStrut(20));

			display = new JTextArea("Hello! This is your display!");
			display.setEditable(false);
			JPanel panel2 = new JPanel();
			TitledBorder tb2 = new TitledBorder("Performed actions");
			panel2.setBorder(tb2);
			panel2.add(display);

			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.add(new JScrollPane(table), BorderLayout.CENTER);
			mainPanel.add(panel1, BorderLayout.SOUTH);
			mainPanel.add(panel2, BorderLayout.EAST);
			add(mainPanel);

			ok.addActionListener(this);
			pay.addActionListener(this);
			back.addActionListener(this);

			setPreferredSize(new Dimension(1100, 400));
			pack();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			setLocationRelativeTo(null);
		}
	}
/**
 * Processing of the user's choice and purchase
 */
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();

		if (src == ok) {
			Passenger passenger = new Passenger();
			try {
				ConnectDBMysql connect = new ConnectDBMysql();
				PassengerDBDAO pdb = new PassengerDBDAO(connect);
				passenger = pdb.retUser(currentUser);
				pdb.closeConnection();
			} catch (SQLException sql) {
				JOptionPane.showConfirmDialog(null,
						"Error connecting to database",
						"Sorry just try again!", JOptionPane.YES_OPTION);
				sql.printStackTrace();
			} catch (ClassNotFoundException cnf) {
				JOptionPane.showConfirmDialog(null,
						"Cannot find db driver classes",
						"Error with database connect", JOptionPane.OK_OPTION);
			}
			Train currentTrain = trains.get(currentSelectedRow);
			display.setText("You choose " + currentTrain.getTrainName()
					+ " which moves\nto "
					+ currentTrain.getStationOfDestination() + " in date "
					+ currentTrain.getDate() + "\nat "
					+ currentTrain.getDepartureTime() + " away.\n"
					+ "Price will be = " + currentTrain.getPrice()
					+ " grn.\n\n" + "Your account registered by:\nName: - "
					+ passenger.getName() + "\nSurname - "
					+ passenger.getSurName() + "\nBank account -  "
					+ passenger.getBankAccount()
					+ "\n\nIf you agree, click pay...");
			pay.setEnabled(true);
		} else if (src == pay) {
			Passenger passenger = new Passenger();
			try {
				ConnectDBMysql connect = new ConnectDBMysql();
				PassengerDBDAO pdb = new PassengerDBDAO(connect);
				passenger = pdb.retUser(currentUser);
				pdb.closeConnection();
			} catch (SQLException sql) {
				JOptionPane.showConfirmDialog(null,
						"Error connecting to database",
						"Sorry just try again!", JOptionPane.YES_OPTION);
				sql.printStackTrace();
			} catch (ClassNotFoundException cnf) {
				JOptionPane.showConfirmDialog(null,
						"Cannot find db driver classes",
						"Error with database connect", JOptionPane.OK_OPTION);
			}
			display.setText("Thanks for your order, it is sure\n to be processed after payment.\n\n"
					+ "To your bank account\n sent an invoice for payment:\n\nuser name -  "
					+ passenger.getLogin()
					+ "\n"
					+ "bank account -  "
					+ passenger.getBankAccount());
			pay.setEnabled(false);
		} else if (src == back) {
			new MakeApplicationFrame(currentUser);
			dispose();
		}
	}

}
