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
import kontakty.utils.MyUtils;

@WebServlet(urlPatterns = { "/del" })
public class DoDeleteUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5320496132492837075L;

	/**
	 * Obsługa GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		UserAccount loggedUser = MyUtils.dajZalogowanegoUzytkownika(session);

		Connection conn = null;

		boolean hasErrors = false;
		String errorString = null;

		if (loggedUser == null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			
			int id = Integer.parseInt(request.getParameter("id"));
			
			try {
				conn = MyUtils.nawiazIzwrocPolaczenie(request);

				DatabaseUtils.usunUzytkownika(conn, id);

			} catch (ClassNotFoundException | SQLException e) {
				hasErrors = true;
				errorString = "Błąd podczas usuwania użytkownika";
				System.out.println(e.getMessage());
			}
		}

		if (hasErrors) {
			request.setAttribute("errorString", errorString);
		}

		MyConnectionUtils.closeMyConnection(conn);
		
		response.sendRedirect(request.getContextPath() + "/users");

	}

	/**
	 * Obsługa POST z przekierowaniem do GET
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
