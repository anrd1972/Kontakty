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

import kontakty.connectionUtils.MyConnectionUtils;
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

		request.setCharacterEncoding("utf-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userImie = request.getParameter("userImie");
		String userNazwisko = request.getParameter("userNazwisko");

		String operacja = request.getParameter("operacja");
		String id = request.getParameter("idUser");

		UserAccount user = null;
		boolean hasErrors = false;
		String stringError = null;

		Connection conn = null;

		if (KontaktyUtils.sprawdzDaneRejestracja(username, password, userImie, userNazwisko)) {
			hasErrors = true;
			stringError = "Pola oznaczone gwiazdką są wymagane!";
		} else {

			try {

				user = new UserAccount();

				if (operacja.equals("M")) {
					user.setIdUser(Integer.parseInt(id));
				}

				user.setUsername(username);
				user.setPassword(password);
				user.setUserImie(userImie);
				user.setUserNazwisko(userNazwisko);
				user.setOperacja(operacja);

				conn = MyUtils.nawiazIzwrocPolaczenie(request);

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

			user.setOperacja(operacja);

			if (operacja.equals("M")) {
				user.setIdUser(Integer.parseInt(id));
			}

			request.setAttribute("errorString", stringError);
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
		} else {
			try {
				if (operacja.equals("N")) {
					DatabaseUtils.zarejestrujUzytkownika(conn, user);
					MyConnectionUtils.closeMyConnection(conn);
					response.sendRedirect(request.getContextPath() + "/");
				} else if (operacja.equals("M")) {
					DatabaseUtils.updateDanychUzytkownika(conn, user);
					MyConnectionUtils.closeMyConnection(conn);
					response.sendRedirect(request.getContextPath() + "/users");
				} else if (operacja.equals("D")) {
					DatabaseUtils.zarejestrujUzytkownika(conn, user);
					MyConnectionUtils.closeMyConnection(conn);
					response.sendRedirect(request.getContextPath() + "/users");
				} else {
					stringError = "Nierozpoznana operacja!";
					response.sendRedirect(request.getContextPath() + "/users");
				}

			} catch (SQLException e) {
				System.out.println("Błąd podczas zapisu do bazy danych!");
			}

		}

	}

	/**
	 * Obsługa POST z przekierowaniem do GET
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
