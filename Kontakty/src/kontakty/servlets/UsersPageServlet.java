package kontakty.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
import kontakty.utils.MyUtils;

@WebServlet(urlPatterns = { "/users" })
public class UsersPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1895849805195572937L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		UserAccount user = MyUtils.dajZalogowanegoUzytkownika(session);

		String errorString = null;
		
		Connection conn = null;
		List<UserAccount> usersList = null;

		if (user == null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {

			try {
				conn = MyUtils.nawiazIzwrocPolaczenie(request);

				usersList = DatabaseUtils.dajListeUzytkownikow(conn);

			} catch (ClassNotFoundException | SQLException e) {
				errorString = "Błąd podczas pobierania listy użytkowników";
			}

		}

		MyConnectionUtils.closeMyConnection(conn);
		
		request.setAttribute("errorString", errorString);
		request.setAttribute("userList", usersList);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/users.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
