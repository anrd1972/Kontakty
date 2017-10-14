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

@WebServlet(urlPatterns = { "/new" })
public class DodajOsobeServlet extends HttpServlet {

	private static final long serialVersionUID = 5997832212510780932L;

	public DodajOsobeServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		UserAccount loggedUser = MyUtils.dajZalogowanegoUzytkownika(session);

		Connection conn = null;
		Osoby osoba = null;

		boolean hasErrors = false;

		String errorString = null;
		int idOsoby = 0;
		
		String operacja = request.getParameter("mode");

		if (loggedUser == null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {
			
			if (operacja.equals("M")) {

				idOsoby = Integer.parseInt(request.getParameter("id"));

				try {
					conn = MyUtils.nawiazIzwrocPolaczenie(request);

					osoba = DatabaseOsoby.dajDaneOsoby(conn, idOsoby);
					osoba.setOperacja(operacja);
					osoba.setIdOsoby(idOsoby);

				} catch (ClassNotFoundException | SQLException e) {
					hasErrors = true;
					errorString = "Błąd podczas pobierania danych";
				}

			} else {
				osoba = new Osoby();
				osoba.setOperacja(operacja);
			}

			if (hasErrors) {
				request.setAttribute("errorString", errorString);
				response.sendRedirect(request.getServletPath() + "/contacts");
			} else {
				request.setAttribute("osoba", osoba);

				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addnew.jsp");
				dispatcher.forward(request, response);
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
