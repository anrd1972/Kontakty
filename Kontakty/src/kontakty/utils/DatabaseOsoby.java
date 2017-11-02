package kontakty.utils;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kontakty.models.Osoby;

public class DatabaseOsoby {

	/**
	 * Pobranie listy zarejestrowanych osob
	 * 
	 * @param <code>Connection</code>
	 *            conn
	 * @return <code>List</code>
	 * @throws <code>SQLException</code>
	 */
	public static List<Osoby> dajListeOsob(Connection conn) throws SQLException {

		List<Osoby> listaOsob = new ArrayList<Osoby>();

		String sql = "Select * from Osoby";

		PreparedStatement stm = null;
		ResultSet rs = null;

		try {

			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {
				Osoby osoba = new Osoby();
				osoba.setIdOsoby(Integer.parseInt(rs.getString(1)));
				osoba.setOsobaImie(rs.getString(2));
				osoba.setOsobaNazwisko(rs.getString(3));
				osoba.setOsobaEmail(rs.getString(4));
				osoba.setOsobaTelefonDom(rs.getString(5));
				osoba.setOsobaTelefonPraca(rs.getString(6));
				osoba.setOsobaAdresUlica(rs.getString(7));
				osoba.setOsobaAdresNrDomu(rs.getString(8));
				osoba.setOsobaAdresNrMieszkania(rs.getString(9));
				osoba.setOsobaAdresMiasto(rs.getString(10));
				osoba.setOsobaAdresKodPocztowy(rs.getString(11));
				osoba.setOsobaUrodziny(rs.getDate(12));

				listaOsob.add(osoba);
			}

			rs.close();
			stm.close();
			conn.close();

			return listaOsob;

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
	 * Zapis nowej osoby do bazy
	 * 
	 * @param <code>Connection</code>
	 * @param <code>Osoby</code>
	 * @throws <code>SQLException</code>
	 */
	public static void zapiszOsobe(Connection conn, Osoby osoba) throws SQLException {

		if (osoba.getOsobaImie().equals(null)) {
			throw new InvalidParameterException("Imię puste");
		}
		
		if (osoba.getOsobaEmail().equals(null)) {
			throw new InvalidParameterException("Email pusty");
		}
		
		String insertSQL = "INSERT INTO Osoby " + "(osoba_imie, osoba_nazwisko, osoba_email, "
				+ "osoba_telefon_dom, osoba_telefon_praca, osoba_adres_ulica, osoba_adres_nr_domu, "
				+ "osoba_adres_nr_mieszkania, osoba_adres_miasto, osoba_adres_kod_pocztowy, osoba_urodziny) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement stm = null;

		try {
			stm = conn.prepareStatement(insertSQL);
			stm.setString(1, osoba.getOsobaImie());
			stm.setString(2, osoba.getOsobaNazwisko());
			stm.setString(3, osoba.getOsobaEmail());
			stm.setString(4, osoba.getOsobaTelefonDom());
			stm.setString(5, osoba.getOsobaTelefonPraca());
			stm.setString(6, osoba.getOsobaAdresUlica());
			stm.setString(7, osoba.getOsobaAdresNrDomu());
			stm.setString(8, osoba.getOsobaAdresNrMieszkania());
			stm.setString(9, osoba.getOsobaAdresMiasto());
			stm.setString(10, osoba.getOsobaAdresKodPocztowy());
			stm.setDate(11, osoba.getOsobaUrodziny());

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
	 * Zwraca dane osoby o konkretnym id
	 * @param conn
	 * @param idOsoba
	 * @return
	 * @throws SQLException
	 */
	public static Osoby dajDaneOsoby(Connection conn, int idOsoba) throws SQLException {
		
		Osoby osoba = null;
		String sql = "Select * from Osoby where idOsoby = ?";
		
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {

			stm = conn.prepareStatement(sql);
			stm.setInt(1, idOsoba);
			rs = stm.executeQuery();

			while (rs.next()) {
				osoba = new Osoby();
				osoba.setIdOsoby(idOsoba);
				osoba.setOsobaImie(rs.getString(2));
				osoba.setOsobaNazwisko(rs.getString(3));
				osoba.setOsobaEmail(rs.getString(4));
				osoba.setOsobaTelefonDom(rs.getString(5));
				osoba.setOsobaTelefonPraca(rs.getString(6));
				osoba.setOsobaAdresUlica(rs.getString(7));
				osoba.setOsobaAdresNrDomu(rs.getString(8));
				osoba.setOsobaAdresNrMieszkania(rs.getString(9));
				osoba.setOsobaAdresMiasto(rs.getString(10));
				osoba.setOsobaAdresKodPocztowy(rs.getString(11));
				osoba.setOsobaUrodziny(rs.getDate(12));

			}

			rs.close();
			stm.close();

			return osoba;

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
	 * Update danych osoby kontaktowej
	 * @param conn
	 * @param osoba
	 * @throws SQLException
	 */
	public static void uspdateDanychOsoby(Connection conn, Osoby osoba) throws SQLException {
		
		String sqlUpdate = "UPDATE Osoby SET "
		+ "osoba_imie = ?, "
		+ "osoba_nazwisko = ?, "
		+ "osoba_email = ?, "
		+ "osoba_telefon_dom = ?, "
		+ "osoba_telefon_praca = ? ,"
		+ "osoba_adres_ulica = ?, "
		+ "osoba_adres_nr_domu = ?, "
		+ "osoba_adres_nr_mieszkania = ?, "
		+ "osoba_adres_miasto = ?, "
		+ "osoba_adres_kod_pocztowy = ?, "
		+ "osoba_urodziny = ? "
		+ "WHERE idosoby = ?";
		
		PreparedStatement stm = null;
		
		try {
			stm = conn.prepareStatement(sqlUpdate);
			
			stm.setString(1, osoba.getOsobaImie());
			stm.setString(2, osoba.getOsobaNazwisko());
			stm.setString(3, osoba.getOsobaEmail());
			stm.setString(4, osoba.getOsobaTelefonDom());
			stm.setString(5, osoba.getOsobaTelefonPraca());
			stm.setString(6, osoba.getOsobaAdresUlica());
			stm.setString(7, osoba.getOsobaAdresNrDomu());
			stm.setString(8, osoba.getOsobaAdresNrMieszkania());
			stm.setString(9, osoba.getOsobaAdresMiasto());
			stm.setString(10, osoba.getOsobaAdresKodPocztowy());
			stm.setDate(11, osoba.getOsobaUrodziny());
			stm.setInt(12, osoba.getIdOsoby());
			
			stm.executeUpdate();
			
			stm.close();
			conn.close();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception e) {
				// nic nie rob
			}
			
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				//nic nie rob
			}
		}

	}
	
	/**
	 * Usuwanie osoby kontaktowej z bazy
	 * 
	 * @param conn
	 * @param id
	 * @throws SQLException
	 */
	public static void usunOsobe(Connection conn, int id) throws SQLException {

		String sql = "DELETE FROM Osoby WHERE idosoby = " + id;

		PreparedStatement stm = null;

		try {
			stm = conn.prepareStatement(sql);

			stm.executeUpdate(sql);
			
			stm.close();
			conn.close();

		} finally {
			try {
				if (stm != null) {
					stm.close();
				}
			} catch (Exception e) {
				// nic nie rob
			}
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (Exception exc) {
				//nic nie rob
			}

		}

	}
	
	/**
	 * Zwraca dane szukanej osoby
	 * @param conn
	 * @param idOsoba
	 * @return
	 * @throws SQLException
	 */
	public static List<Osoby> dajDaneSzukanejOsoby(Connection conn, String pStr) throws SQLException {

		List<Osoby> listaOsob = new ArrayList<Osoby>();
		String sql = "select * from osoby where osoba_imie like '%"+ pStr + "%' "
				+ "or osoba_nazwisko like '%"+ pStr + "%' "
				+ "or osoba_adres_miasto like '%" + pStr + "%'";
		
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {

			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();

			while (rs.next()) {
				Osoby osoba = new Osoby();
				osoba.setIdOsoby(rs.getInt(1));
				osoba.setOsobaImie(rs.getString(2));
				osoba.setOsobaNazwisko(rs.getString(3));
				osoba.setOsobaEmail(rs.getString(4));
				osoba.setOsobaTelefonDom(rs.getString(5));
				osoba.setOsobaTelefonPraca(rs.getString(6));
				osoba.setOsobaAdresUlica(rs.getString(7));
				osoba.setOsobaAdresNrDomu(rs.getString(8));
				osoba.setOsobaAdresNrMieszkania(rs.getString(9));
				osoba.setOsobaAdresMiasto(rs.getString(10));
				osoba.setOsobaAdresKodPocztowy(rs.getString(11));
				osoba.setOsobaUrodziny(rs.getDate(12));

				listaOsob.add(osoba);
			}

			rs.close();
			stm.close();

			return listaOsob;

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

}
