package kontakty.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kontakty.models.UserAccount;

public class DatabaseUtils {

	/**
	 * Sprawdzanie czy tablica USers jest pusta
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static int uzytkownicy(Connection conn) throws SQLException {

		int ile = 0;

		String sql = "Select count(*) from Users";

		PreparedStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareStatement(sql);

			rs = stm.executeQuery();
			rs.last();
			ile = rs.getRow();

			rs.close();
			stm.close();

			return ile;

		} catch (SQLException e) {
			System.out.println("Błąd podczas wykonywania zapytania");
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception eRs) {
				// nic nie rob
			}

			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}

			throw e;
		}

	}

	/**
	 * Pobranie danych zalogowanego uzytkownika
	 * 
	 * @param conn
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public static UserAccount znajdzUzytkownika(Connection conn, String username, String password) throws SQLException {

		String sql = "Select idusers, user_username, user_password, user_imie, user_nazwisko from Users "
				+ "where user_username = ? and user_password = ? ";

		PreparedStatement stm = null;
		ResultSet rs = null;

		try {

			UserAccount user = null;

			stm = conn.prepareStatement(sql);
			stm.setString(1, username);
			stm.setString(2, password);

			rs = stm.executeQuery();

			if (rs.next()) {
				user = new UserAccount();
				user.setIdUser(Integer.parseInt(rs.getString(1)));
				user.setUsername(username);
				user.setPassword(rs.getString(3));
				user.setUserImie(rs.getString(4));
				user.setUserNazwisko(rs.getString(5));

			}

			rs.close();
			stm.close();

			return user;

		} catch (SQLException e) {
			System.out.println("Błąd podczas wykonywania zapytania");
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception eRs) {
				// nic nie rob
			}

			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}

			throw e;
		}

	}

	/**
	 * Policz uzytkownikow - inna metoda
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static int ileUserow(Connection conn) throws SQLException {

		String sql = "Select idusers, user_username, user_password, user_imie, user_nazwisko from Users";

		PreparedStatement stm = null;
		ResultSet rs = null;

		int ile = 0;

		try {

			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();

			if (rs.next()) {
				ile = 1;
			} else {
				ile = 0;
			}

			rs.close();
			stm.close();

			System.out.println("Zarejestrowanych w bazie " + ile + " użytkowników");
			return ile;

		} catch (SQLException e) {
			System.out.println("Błąd podczas wykonywania zapytania");
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception eRs) {
				// nic nie rob
			}

			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}

			throw e;
		}
	}

	/**
	 * Rejestracja uzytkownika
	 * 
	 * @param conn
	 * @param user
	 * @throws SQLException
	 */
	public static void zarejestrujUzytkownika(Connection conn, UserAccount user) throws SQLException {

		String sql = "Insert into Users (user_username, user_password, user_imie, user_nazwisko)"
				+ " values(?, ?, ?, ?)";

		PreparedStatement stm = null;

		try {

			stm = conn.prepareStatement(sql);
			stm.setString(1, user.getUsername());
			stm.setString(2, user.getPassword());
			stm.setString(3, user.getUserImie());
			stm.setString(4, user.getUserNazwisko());

			stm.execute();

			stm.close();

		} catch (SQLException e) {
			System.out.println("Błąd podczas wykonywania zapytania");
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}

			throw e;
		}
	}

	/**
	 * Pobranie listy uzytkowników
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static List<UserAccount> dajListeUzytkownikow(Connection conn) throws SQLException {

		List<UserAccount> userList = new ArrayList<UserAccount>();

		String sql = "Select * from Users";

		PreparedStatement stm = null;
		ResultSet rs = null;

		try {

			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {
				UserAccount user = new UserAccount();
				user.setIdUser(Integer.parseInt(rs.getString(1)));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setUserImie(rs.getString(4));
				user.setUserNazwisko(rs.getString(5));

				userList.add(user);
			}

			rs.close();
			stm.close();

			return userList;

		} catch (SQLException e) {
			System.out.println("Błąd podczas wykonywania zapytania");
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}

			throw e;
		}

	}

	/**
	 * Pobranie danych użytkownika po ID
	 * 
	 * @param idUser
	 * @return
	 * @throws SQLException
	 */
	public static UserAccount dajDaneUzytkownika(int idUser, Connection conn) throws SQLException {

		UserAccount user = null;
		String sql = "Select * from Users where idusers = ?";

		PreparedStatement stm = null;
		ResultSet rs = null;

		try {

			stm = conn.prepareStatement(sql);
			stm.setInt(1, idUser);
			rs = stm.executeQuery();

			while (rs.next()) {
				user = new UserAccount();
				user.setIdUser(Integer.parseInt(rs.getString(1)));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setUserImie(rs.getString(4));
				user.setUserNazwisko(rs.getString(5));

			}

			rs.close();
			stm.close();

			return user;

		} catch (SQLException e) {
			System.out.println("Błąd podczas wykonywania zapytania");

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception eRs) {
				// nic nie rob
			}
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception eStm) {
				// nic nie rob
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception eConn) {
				// nic nie rob
			}

			throw e;
		}

	}

	/**
	 * Update danych użytkownika
	 * 
	 * @param conn
	 * @param user
	 * @throws SQLException
	 */
	public static void updateDanychUzytkownika(Connection conn, UserAccount user) throws SQLException {
		
		String sql = "Update Users Set " + "user_username = ?, " + "user_password = ?," + "user_imie = ?,"
				+ "user_nazwisko = ? " + "Where idusers = ?";

		PreparedStatement stm = null;

		try {
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, user.getUsername());
			stm.setString(2, user.getPassword());
			stm.setString(3, user.getUserImie());
			stm.setString(4, user.getUserNazwisko());
			stm.setInt(5, user.getIdUser());

			stm.executeUpdate();

			stm.close();

		} finally {
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception e) {
				//nic nie rob
			}
			
		}

	}

}
