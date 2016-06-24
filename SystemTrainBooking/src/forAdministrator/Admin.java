package forAdministrator;
/**
 * Stores information about the Administrator.
 * @author LIO
 *
 */
public class Admin {

	private int id;
	private String login;
	private String password;
/**
 * Default constructor.
 */
	public Admin() {

	}
/**
 * Constructor to initialize the data on Admin.
 * @param id
 * @param login
 * @param password
 */
	public Admin(int id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin other = (Admin) obj;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	public String toString() {
		return "Admin [id=" + id + ", login=" + login + ", password="
				+ password + "]";
	}

}
