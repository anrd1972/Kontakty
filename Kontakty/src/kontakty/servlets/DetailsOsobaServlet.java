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

import kontakty.models.Osoby;
import kontakty.models.UserAccount;
import kontakty.utils.DatabaseOsoby;
import kontakty.utils.MyUtils;

@WebServlet(urlPatterns = { "/detail" })
public class DetailsOsobaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1980480584358251576L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		HttpSession session = req.getSession();
		UserAccount loggedUser = MyUtils.dajZalogowanegoUzytkownika(session);

		Connection conn = null;
		Osoby osoba = null;

		boolean hasErrors = false;

		String errorString = null;
		int idOsoby = 0;

		String operacja = req.getParameter("mode");

		if (loggedUser == null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(req, resp);
		} else {

			idOsoby = Integer.parseInt(req.getParameter("id"));

			try {
				conn = MyUtils.nawiazIzwrocPolaczenie(req);

				osoba = DatabaseOsoby.dajDaneOsoby(conn, idOsoby);
				osoba.setOperacja(operacja);
				osoba.setIdOsoby(idOsoby);

			} catch (ClassNotFoundException | SQLException e) {
				hasErrors = true;
				errorString = "Błąd podczas pobierania danych";
			}

		}

		if (hasErrors) {
			req.setAttribute("errorString", errorString);
			resp.sendRedirect(req.getServletPath() + "/contacts");
		} else {
			req.setAttribute("osoba", osoba);

			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/details.jsp");
			dispatcher.forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
