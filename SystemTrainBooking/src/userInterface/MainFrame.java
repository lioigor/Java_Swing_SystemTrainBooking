package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import forAdministrator.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import systemTrainBooking.Train;
import connections.*;
import forPassenger.*;
/**
 * This frame draws the main window, windows login and registration. 
 * Also draws the admin authorization. At the same time frame connects to the database 
 * through the DAO and makes various kinds of requests for authorization and registration.
 * @author LIO
 *
 */
public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel windowContent = new JPanel();
	private JButton client;
	private JButton administrator;
	private JButton registration;
	private JTextField loginField;
	private JPasswordField passwordField;
	private JButton ok;
	private JButton okReg;
	private JButton okAdmin;
	private JButton cancel;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField bankField;
/**
 * This constructor creates an initial panel applications.
 * @param title
 */
	public MainFrame(String title) {
		super(title);

		String strInfo = "Welcome to the system for train booking! Please log in or register";
		JLabel information = new JLabel(strInfo, SwingConstants.CENTER);
		client = new JButton("log in");
		registration = new JButton("register");
		administrator = new JButton("Admin panel");

		windowContent.setLayout(null);
		information.setBounds(5, 10, 380, 21);
		windowContent.add(information);
		client.setFocusable(false);
		registration.setFocusable(false);
		administrator.setFocusable(false);
		client.setBounds(90, 50, 100, 21);
		windowContent.add(client);
		registration.setBounds(200, 50, 100, 21);
		windowContent.add(registration);
		administrator.setBounds(135, 150, 120, 21);
		windowContent.add(administrator);
		add(windowContent);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setSize(400, 200);
		setResizable(false);
		setLocation(470, 220);
		setVisible(true);

		client.addActionListener(this);
		registration.addActionListener(this);
		administrator.addActionListener(this);
	}
/**
 * Method returns the main panel for its further processing.
 * @return
 */
	public JPanel getMenuFramePanel() {
		String strInfo = "Welcome to the system for train booking! Please log in or register";
		JLabel information = new JLabel(strInfo, SwingConstants.CENTER);
		client = new JButton("log in");
		registration = new JButton("register");
		administrator = new JButton("Admin panel");

		JPanel content = new JPanel();
		content.setLayout(null);
		information.setBounds(5, 10, 380, 21);
		content.add(information);
		client.setFocusable(false);
		registration.setFocusable(false);
		administrator.setFocusable(false);
		client.setBounds(90, 50, 100, 21);
		content.add(client);
		registration.setBounds(200, 50, 100, 21);
		content.add(registration);
		administrator.setBounds(135, 150, 120, 21);
		content.add(administrator);
		client.addActionListener(this);
		registration.addActionListener(this);
		administrator.addActionListener(this);

		return content;
	}
/**
 * Panel for user authentication.
 * @return
 */
	public JPanel getUserAuthorizationPanel() {
		Box box1 = Box.createHorizontalBox();
		JLabel loginLabel = new JLabel("login:");
		loginField = new JTextField(15);
		box1.add(loginLabel);
		box1.add(Box.createHorizontalStrut(6));
		box1.add(loginField);

		Box box2 = Box.createHorizontalBox();
		JLabel passwordLabel = new JLabel("password:");
		passwordField = new JPasswordField(15);
		box2.add(passwordLabel);
		box2.add(Box.createHorizontalStrut(6));
		box2.add(passwordField);

		Box box3 = Box.createHorizontalBox();
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
		box3.add(Box.createHorizontalGlue());
		box3.add(ok);
		box3.add(Box.createHorizontalStrut(12));
		box3.add(cancel);
		loginLabel.setPreferredSize(passwordLabel.getPreferredSize());
		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
		mainBox.add(box1);
		mainBox.add(Box.createVerticalStrut(12));
		mainBox.add(box2);
		mainBox.add(Box.createVerticalStrut(17));
		mainBox.add(box3);
		ok.addActionListener(this);
		cancel.addActionListener(this);

		JPanel p = new JPanel();
		p.add(mainBox);

		return p;
	}
/**
 * Panel for user registration.
 * @return
 */
	public JPanel getUserRegistrationPanel() {
		Box box1 = Box.createHorizontalBox();
		JLabel loginLabel = new JLabel("Choose a login:");
		loginField = new JTextField(10);
		box1.add(loginLabel);
		box1.add(Box.createHorizontalStrut(6));
		box1.add(loginField);

		Box box2 = Box.createHorizontalBox();
		JLabel passwordLabel = new JLabel("Choose password:");
		passwordField = new JPasswordField(10);
		box2.add(passwordLabel);
		box2.add(Box.createHorizontalStrut(6));
		box2.add(passwordField);

		Box box3 = Box.createHorizontalBox();
		JLabel nameLabel = new JLabel("Enter your name:");
		nameField = new JTextField(10);
		box3.add(nameLabel);
		box3.add(Box.createHorizontalStrut(6));
		box3.add(nameField);

		Box box4 = Box.createHorizontalBox();
		JLabel surnameLabel = new JLabel("Enter surname:");
		surnameField = new JTextField(10);
		box4.add(surnameLabel);
		box4.add(Box.createHorizontalStrut(6));
		box4.add(surnameField);

		Box box5 = Box.createHorizontalBox();
		JLabel bankLabel = new JLabel("Bank account:");
		bankField = new JTextField(10);
		box5.add(bankLabel);
		box5.add(Box.createHorizontalStrut(6));
		box5.add(bankField);

		Box box6 = Box.createHorizontalBox();
		okReg = new JButton("OK");
		cancel = new JButton("Cancel");
		box6.add(Box.createHorizontalGlue());
		box6.add(okReg);
		box6.add(Box.createHorizontalStrut(12));
		box6.add(cancel);
		loginLabel.setPreferredSize(passwordLabel.getPreferredSize());
		nameLabel.setPreferredSize(loginLabel.getPreferredSize());
		surnameLabel.setPreferredSize(nameLabel.getPreferredSize());
		bankLabel.setPreferredSize(surnameLabel.getPreferredSize());
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
		okReg.addActionListener(this);
		cancel.addActionListener(this);

		JPanel p = new JPanel();
		p.add(mainBox);

		return p;
	}
/**
 * Panel for administrator authorization.
 * @return
 */
	public JPanel getAdminAuthorizationPanel() {
		Box box1 = Box.createHorizontalBox();
		JLabel loginLabel = new JLabel("login:");
		loginField = new JTextField(15);
		box1.add(loginLabel);
		box1.add(Box.createHorizontalStrut(6));
		box1.add(loginField);

		Box box2 = Box.createHorizontalBox();
		JLabel passwordLabel = new JLabel("password:");
		passwordField = new JPasswordField(15);
		box2.add(passwordLabel);
		box2.add(Box.createHorizontalStrut(6));
		box2.add(passwordField);

		Box box3 = Box.createHorizontalBox();
		okAdmin = new JButton("OK");
		cancel = new JButton("Cancel");
		box3.add(Box.createHorizontalGlue());
		box3.add(okAdmin);
		box3.add(Box.createHorizontalStrut(12));
		box3.add(cancel);
		loginLabel.setPreferredSize(passwordLabel.getPreferredSize());
		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(new EmptyBorder(12, 12, 12, 12));
		mainBox.add(box1);
		mainBox.add(Box.createVerticalStrut(12));
		mainBox.add(box2);
		mainBox.add(Box.createVerticalStrut(17));
		mainBox.add(box3);
		okAdmin.addActionListener(this);
		cancel.addActionListener(this);

		JPanel p = new JPanel();
		p.add(mainBox);

		return p;
	}
/**
 * Special method to redraw the frame depending on the desired output content.
 * @param title
 * @param panel
 */
	public void repaint(String title, JPanel panel) {
		this.remove(windowContent);
		this.setTitle(title);
		getContentPane().removeAll();
		this.add(panel);
		revalidate();
	}
/**
 * Event handler responds to the button and depending on the press fills the form 
 * and content necessary to calculate the appropriate mode of action.
 */
	public void actionPerformed(ActionEvent e) {

		Object src = e.getSource();
		if (src == client) {
			repaint("Login", getUserAuthorizationPanel());
		} else if (src == registration) {
			repaint("Register", getUserRegistrationPanel());
		} else if (src == administrator) {
			repaint("Admin", getAdminAuthorizationPanel());
		} else if (src == ok) {
			String pass = new String(passwordField.getPassword());

			if (loginField.getText().equals("") || pass.equals("")) {
				JOptionPane.showConfirmDialog(null,
						"Empty field , enter data please", "Empty field",
						JOptionPane.OK_OPTION);
			} else {
				Passenger user = new Passenger();
				user.setLogin(loginField.getText());
				user.setPassword(pass);

				boolean check = false;
				try {
					ConnectDBMysql connect = new ConnectDBMysql();
					PassengerDBDAO pdb = new PassengerDBDAO(connect);
					check = pdb.checkUser(user);
					pdb.closeConnection();
				} catch (SQLException sql) {
					JOptionPane.showConfirmDialog(null,
							"Error connecting to database",
							"Sorry just try again!", JOptionPane.YES_OPTION);
				} catch (ClassNotFoundException cnf) {
					JOptionPane.showConfirmDialog(null,
							"Cannot find db driver classes",
							"Error with database connect",
							JOptionPane.OK_OPTION);
				}
				if (check) {
					JOptionPane.showConfirmDialog(null,
							"Authentication is successful",
							"Successful operation", JOptionPane.OK_OPTION);
					new MakeApplicationFrame(user.getLogin());
					dispose();
				} else  {
					JOptionPane.showConfirmDialog(null,
							"This user name and password is not found",
							"Not successful", JOptionPane.OK_OPTION);
				}
			}

		} else if (src == okReg) {
			if (loginField.getText().equals("")
					|| passwordField.getPassword().equals("")
					|| nameField.getText().equals("")
					|| surnameField.getText().equals("")
					|| bankField.getText().equals("")) {
				JOptionPane.showConfirmDialog(null,
						"Empty field , enter data please", "Empty field",
						JOptionPane.YES_OPTION);
			} else {
				String pass = new String(passwordField.getPassword());
				boolean check = false;
				try {
					ConnectDBMysql connect = new ConnectDBMysql();
					PassengerDBDAO pdb = new PassengerDBDAO(connect);
					check = pdb.readCoincidence(loginField.getText());
					pdb.closeConnection();
				} catch (SQLException sql) {
					JOptionPane.showConfirmDialog(null,
							"Error connecting to database",
							"Sorry just try again!", JOptionPane.YES_OPTION);
				} catch (ClassNotFoundException cnf) {
					JOptionPane.showConfirmDialog(null,
							"Cannot find db driver classes",
							"Error with database connect",
							JOptionPane.OK_OPTION);
				}
				if (check) {
					JOptionPane.showConfirmDialog(null,
							"This username already exists",
							"Retype login, please", JOptionPane.OK_OPTION);
				} else {
					Passenger user = new Passenger(1, loginField.getText(),
							pass, nameField.getText(), surnameField.getText(),
							bankField.getText());
					boolean checkException = false;
					try {
						ConnectDBMysql connect = new ConnectDBMysql();
						PassengerDBDAO pdb = new PassengerDBDAO(connect);
						pdb.write(user);
						pdb.closeConnection();
					} catch (SQLException sql) {
						checkException = true;
						JOptionPane
								.showConfirmDialog(null,
										"Error connecting to database",
										"Sorry just try again!",
										JOptionPane.YES_OPTION);
					} catch (ClassNotFoundException cnf) {
						checkException = true;
						JOptionPane.showConfirmDialog(null,
								"Cannot find db driver classes",
								"Error with database connect",
								JOptionPane.OK_OPTION);
					}
					if (!checkException) {
						JOptionPane.showConfirmDialog(null,
								"Registration is successful",
								"successful action", JOptionPane.OK_OPTION);
						repaint("System Train Booking", getMenuFramePanel());
					} else {
						JOptionPane.showConfirmDialog(null,
								"Registration not successful",
								"Error record in database",
								JOptionPane.OK_OPTION);
						repaint("System Train Booking", getMenuFramePanel());
					}
				}

			}
		} else if (src == okAdmin) {
			String pass = new String(passwordField.getPassword());

			if (loginField.getText().equals("") || pass.equals("")) {
				JOptionPane.showConfirmDialog(null,
						"Empty field , enter data please", "Empty field",
						JOptionPane.OK_OPTION);
			} else {
				Admin admin = new Admin();
				admin.setLogin(loginField.getText());
				admin.setPassword(pass);

				boolean check = false;
				try {
					ConnectDBMysql connect = new ConnectDBMysql();
					AdminDBDAO pdb = new AdminDBDAO(connect);
					check = pdb.checkAdmin(admin);
					pdb.closeConnection();
				} catch (SQLException sql) {
					JOptionPane.showConfirmDialog(null,
							"Error connecting to database",
							"Sorry just try again!", JOptionPane.YES_OPTION);
					System.out.println(sql.getMessage());
				} catch (ClassNotFoundException cnf) {
					JOptionPane.showConfirmDialog(null,
							"Cannot find db driver classes",
							"Error with database connect",
							JOptionPane.OK_OPTION);
					System.out.println(cnf.getMessage());
				}
				if (check) {
					JOptionPane.showConfirmDialog(null,
							"Authentication is successful",
							"Successful operation", JOptionPane.OK_OPTION);
					new AdminPanelFrame(new ArrayList<Train>());
					dispose();
				} else  {
					JOptionPane.showConfirmDialog(null,
							"This admin name and password is not found",
							"Not successful", JOptionPane.OK_OPTION);
				}
			}
		} else if (src == cancel) {
			repaint("System Train Booking", getMenuFramePanel());
		}

	}

}
