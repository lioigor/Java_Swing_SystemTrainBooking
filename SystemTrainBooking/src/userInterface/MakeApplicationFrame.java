package userInterface;

import java.awt.event.*;
import javax.swing.*;
import systemTrainBooking.*;
/**
 * Frame in which the user can issue a request for a train or to display all available trains.
 * @author LIO
 *
 */
public class MakeApplicationFrame extends JFrame implements ActionListener {

	private JPanel content = new JPanel();
	private JTextField fromWhere;
	private JTextField where;
	private JTextField date;
	private JButton searchButton;
	private JButton backButton;
	private JButton showAll;
	private String currentUser;
/**
 * Filling the frame.
 */
	public MakeApplicationFrame(String currentUser) {

		super("Make a claim");
		this.currentUser = currentUser;

		fromWhere = new JTextField("");
		where = new JTextField("");
		JLabel fromLabel = new JLabel("Dispatch station");
		JLabel whereLabel = new JLabel("Station of destination");
		JLabel dateLabel = new JLabel("Departure date in format: dd.mm.yyyy");
		date = new JTextField();
		searchButton = new JButton("Search trains");
		backButton = new JButton("Back");
		showAll = new JButton("Show all available trains");

		content.setLayout(null);
		fromLabel.setBounds(20, 50, 100, 15);
		content.add(fromLabel);
		fromWhere.setBounds(20, 70, 150, 20);
		content.add(fromWhere);
		whereLabel.setBounds(250, 50, 120, 15);
		content.add(whereLabel);
		where.setBounds(250, 70, 150, 20);
		content.add(where);
		dateLabel.setBounds(20, 120, 230, 15);
		content.add(dateLabel);
		date.setBounds(20, 140, 150, 20);
		content.add(date);
		searchButton.setBounds(130, 180, 150, 30);
		content.add(searchButton);
		backButton.setBounds(20, 230, 150, 30);
		content.add(backButton);
		showAll.setBounds(220, 230, 180, 30);
		content.add(showAll);
		add(content);

		showAll.addActionListener(this);
		backButton.addActionListener(this);
		searchButton.addActionListener(this);

		pack();
		setSize(430, 300);
		setResizable(false);
		setLocation(470, 220);
		setVisible(true);
	}
/**
 * User selection handler, depending on which is the window displaying the result.
 */
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
		if (src == searchButton) {
			if (fromWhere.getText().equals("") || where.getText().equals("")
					|| date.getText().equals("")) {
				JOptionPane.showConfirmDialog(null,
						"Empty field , enter data please", "Empty field",
						JOptionPane.OK_OPTION);
			} else if (!Train.isValidDate(date.getText(), "dd.MM.yyyy")) {
				JOptionPane.showConfirmDialog(null,
						"Enter date in the format: dd.mm.yyyy",
						"Unknown date format", JOptionPane.OK_OPTION);
			} else {
				Train train = new Train(fromWhere.getText(), where.getText(),
						date.getText());
				new FindingRightTrainFrame(train, currentUser);
				dispose();
			}
		} else if (src == backButton) {
			new MainFrame("System Train Booking");
			dispose();
		} else if (src == showAll) {
			new FindingRightTrainFrame(new Train("all", "all", "all"),
					currentUser);
			dispose();
		}
	}

}
