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
import javax.servlet.http.HttpSession;

import kontakty.connectionUtils.MyConnectionUtils;
import kontakty.models.UserAccount;
import kontakty.utils.DatabaseUtils;
import kontakty.utils.KontaktyUtils;
import kontakty.utils.MyUtils;

@WebServlet(urlPatterns = { "/doLogin" })
public class DoLoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2593427867795862167L;

	public DoLoginServlet() {
		super();
	}

	/**
	 * Obsluga GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserAccount user = null;
		boolean hasErrors = false;
		String stringError = null;

		Connection conn = null;

		if (KontaktyUtils.sprawdzDaneLogowanie(username, password)) {
			hasErrors = true;
			stringError = "Użytkownik i hasło są wymagane";
		} else {

			try {

				conn = MyUtils.nawiazIzwrocPolaczenie(request);

				user = DatabaseUtils.znajdzUzytkownika(conn, username, password);

				if (user == null) {
					hasErrors = true;
					stringError = "Niepoprawna nazwa użytkownika lub hasło";
				}

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

			request.setAttribute("errorString", stringError);
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");

			dispatcher.forward(request, response);
		} else {
			
			HttpSession session = request.getSession();
			MyUtils.zapamietajZalogowanegoUzytkownika(session, user);
			
			MyConnectionUtils.closeMyConnection(conn);
			
			response.sendRedirect(request.getContextPath() + "/main");
		}

	}

	/**
	 * Obsluga POST z przekierowaniem do GET
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
