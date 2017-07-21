package kontakty.models;

public class UserAccount {

	private int idUser;
	private String userImie;
	private String userNazwisko;
	private String username;
	private String password;
	
	
	public UserAccount() {}


	public UserAccount(int idUser, String userImie, String userNazwisko, String username, String password) {
		super();
		this.idUser = idUser;
		this.userImie = userImie;
		this.userNazwisko = userNazwisko;
		this.username = username;
		this.password = password;
	}


	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}


	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	/**
	 * @return the userImie
	 */
	public String getUserImie() {
		return userImie;
	}


	/**
	 * @param userImie the userImie to set
	 */
	public void setUserImie(String userImie) {
		this.userImie = userImie;
	}


	/**
	 * @return the userNazwisko
	 */
	public String getUserNazwisko() {
		return userNazwisko;
	}


	/**
	 * @param userNazwisko the userNazwisko to set
	 */
	public void setUserNazwisko(String userNazwisko) {
		this.userNazwisko = userNazwisko;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
