package kontakty.utils;

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
	 * @param <code>Connection</code> conn
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

}
