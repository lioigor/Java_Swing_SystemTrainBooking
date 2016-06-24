package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import connections.*;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.table.*;

import systemTrainBooking.*;
/**
 * Administrator window, in which he has the ability to view, add, delete, trains.
 * @author LIO
 *
 */
public class AdminPanelFrame extends JFrame implements ActionListener {

	static final long serialVersionUID = 2L;
	private JButton show;
	private JButton back;
	private JButton apply;
	private JButton delete;
	private JButton add;
	private JTextArea display;
	private ArrayList<Train> trains = new ArrayList<Train>();
	private int currentSelectedRow;
/**
 * Window with a table and display the desired action.
 * Parameter trains use for view trains in table with table-model.
 * @param trains
 */
	public AdminPanelFrame(ArrayList<Train> trains) {

		super("Admin panel");
		this.trains = trains;

		TableModel model = new TableModelTrain(trains, true);
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

		show = new JButton("Show trains");
		back = new JButton("Back");
		apply = new JButton("Apply changes");
		delete = new JButton("Delete");
		add = new JButton("Add");
		JPanel panel1 = new JPanel();
		TitledBorder tb1 = new TitledBorder("Available actions");
		panel1.setBorder(tb1);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(Box.createHorizontalGlue());
		show.setFocusable(false);
		apply.setEnabled(false);
		apply.setFocusable(false);
		delete.setFocusable(false);
		add.setFocusable(false);

		show.setPreferredSize(apply.getPreferredSize());
		delete.setPreferredSize(apply.getPreferredSize());
		add.setPreferredSize(apply.getPreferredSize());
		panel1.add(Box.createHorizontalStrut(20));
		panel1.add(show);
		panel1.add(Box.createHorizontalStrut(20));
		panel1.add(add);
		panel1.add(Box.createHorizontalStrut(20));
		panel1.add(Box.createHorizontalStrut(20));
		panel1.add(delete);

		back.setPreferredSize(show.getPreferredSize());
		back.setFocusable(false);
		panel1.add(Box.createHorizontalStrut(20));
		panel1.add(apply);
		panel1.add(Box.createHorizontalStrut(20));
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

		apply.addActionListener(this);
		add.addActionListener(this);
		delete.addActionListener(this);
		show.addActionListener(this);
		back.addActionListener(this);

		setPreferredSize(new Dimension(1100, 400));
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}
/**
 * Processing operations administrator.
 */
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();

		if (src == show) {
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

			new AdminPanelFrame(trains);
			dispose();
		} else if (src == add) {
			new AdminAddFrame();
			display.setText("To apply the changes,\n click -Apply Changes-");
			apply.setEnabled(true);
		} else if (src == delete) {
			Train currentTrain = trains.get(currentSelectedRow);

			try {
				ConnectDBMysql cdb = new ConnectDBMysql();
				TrainDBDAO tdb = new TrainDBDAO(cdb);
				tdb.delete(currentTrain);
				tdb.closeConnection();
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
			display.setText("To apply the changes,\n click -Apply Changes-");
			apply.setEnabled(true);
		} else if (src == apply) {
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

			new AdminPanelFrame(trains);
			dispose();
		} else if (src == back) {
			new MainFrame("System Train Booking");
			dispose();

		}
	}
}
