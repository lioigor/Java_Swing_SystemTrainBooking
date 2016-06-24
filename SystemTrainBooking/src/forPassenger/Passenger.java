package forPassenger;
/**
 * Stores information about the Passenger.
 * @author LIO
 *
 */
public class Passenger {

	private int id;
	private String login;
	private String password;
	private String name;
	private String surName;
	private String bankAccount;
/**
 * Default constructor.
 */
	public Passenger() {

	}
/**
 * Constructor to initialize the data on Passenger.
 * @param id
 * @param login
 * @param password
 * @param name
 * @param surName
 * @param bankAccount
 */
	public Passenger(int id, String login, String password, String name,
			String surName, String bankAccount) {
		this.id = id;
		this.password = password;
		this.login = login;
		this.name = name;
		this.surName = surName;
		this.bankAccount = bankAccount;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getSurName() {
		return surName;
	}

	public int getId() {
		return id;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bankAccount == null) ? 0 : bankAccount.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surName == null) ? 0 : surName.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Passenger other = (Passenger) obj;
		if (bankAccount == null) {
			if (other.bankAccount != null)
				return false;
		} else if (!bankAccount.equals(other.bankAccount))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (surName == null) {
			if (other.surName != null)
				return false;
		} else if (!surName.equals(other.surName))
			return false;
		return true;
	}

	public String toString() {
		return "Passenger [id=" + id + ", login=" + login + ", password="
				+ password + ", name=" + name + ", surName=" + surName
				+ ", bankAccount=" + bankAccount + "]";
	}

}
