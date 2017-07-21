package kontakty.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kontakty.connectionUtils.MyConnectionUtils;
import kontakty.models.UserAccount;

public class MyUtils {

	public static final String POLACZENIE = "POLACZENIE";

	/**
	 * Zapamietywanie polaczenia
	 * 
	 * @param request
	 * @param conn
	 */
	public static void zapamietajPolaczenie(ServletRequest request, Connection conn) {

		request.setAttribute(POLACZENIE, conn);
	}

	/**
	 * Zwracanie zapamietanego polaczenia
	 * 
	 * @param request
	 * @return
	 */
	public static Connection dajZapamietanePolaczene(ServletRequest request) {

		Connection conn = (Connection) request.getAttribute(POLACZENIE);
		return conn;
	}

	/**
	 * Zapamietywanie zalogowanego uzytkownika
	 * 
	 * @param session
	 * @param user
	 */
	public static void zapamietajZalogowanegoUzytkownika(HttpSession session, UserAccount user) {

		session.setAttribute("zalogowany", user);
	}

	/**
	 * Zwaranie zalogowanego uzytkownika
	 * 
	 * @param session
	 * @return
	 */
	public static UserAccount dajZalogowanegoUzytkownika(HttpSession session) {

		UserAccount zalogowany = (UserAccount) session.getAttribute("zalogowany");
		return zalogowany;
	}

	/**
	 * Nazwiazywanie, zapamietywanie i zwracanie polaczenia
	 * @param request
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection nawiazIzwrocPolaczenie(HttpServletRequest request)
			throws ClassNotFoundException, SQLException {

		Connection conn = MyConnectionUtils.getMyConnection();
		MyUtils.zapamietajPolaczenie(request, conn);

		return MyUtils.dajZapamietanePolaczene(request);
	}

}
