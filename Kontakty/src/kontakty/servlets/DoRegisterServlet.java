package kontakty.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kontakty.models.UserAccount;
import kontakty.utils.DatabaseUtils;
import kontakty.utils.KontaktyUtils;
import kontakty.utils.MyUtils;

@WebServlet(urlPatterns = { "/doRegister" })
public class DoRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = -3833484000231352457L;

	public DoRegisterServlet() {
		super();
	}

	/**
	 * Obsługa GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userImie = request.getParameter("userImie");
		String userNazwisko = request.getParameter("userNazwisko");

		UserAccount user = null;
		boolean hasErrors = false;
		String stringError = null;

		Connection conn = null;

		if (sprawdzDaneRejestracja(username, password, userImie, userNazwisko)) {
			hasErrors = true;
			stringError = "Pola oznaczone gwiazdką są wymagane!";
		} else {

			try {

				user = new UserAccount();

				user.setUsername(username);
				user.setPassword(password);
				user.setUserImie(userImie);
				user.setUserNazwisko(userNazwisko);

				conn = MyUtils.nawiazIzwrocPolaczenie(request);

				DatabaseUtils.zarejestrujUzytkownika(conn, user);

			} catch (SQLException | ClassNotFoundException eSql) {
				eSql.printStackTrace();
				hasErrors = true;
				stringError = eSql.getMessage();
			}
		}

		if (hasErrors) {
			user = new UserAccount();
			user.setUsername(username);
			user.setPassword(password);
			user.setUserImie(userImie);
			user.setUserNazwisko(userNazwisko);

			request.setAttribute("errorString", stringError);
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp");

			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");

			dispatcher.forward(request, response);
		}

	}

	/**
	 * Obsługa POST z przekierowaniem do GET
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Sprawdzanie czy wymagane dane z formularza nie są puste
	 * @param username
	 * @param password
	 * @param userImie
	 * @param userNazwisko
	 * @return
	 */
	public boolean sprawdzDaneRejestracja(String username, String password, String userImie, String userNazwisko) {

		boolean isEmpty = false;

		if (KontaktyUtils.isBlankOrNull(username) || KontaktyUtils.isBlankOrNull(password)
				|| KontaktyUtils.isBlankOrNull(userImie) || KontaktyUtils.isBlankOrNull(userNazwisko)) {
			isEmpty = true;
		}

		return isEmpty;
	}

}
