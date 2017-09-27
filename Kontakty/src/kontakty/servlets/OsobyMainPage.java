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

import kontakty.models.Osoby;
import kontakty.models.UserAccount;
import kontakty.utils.DatabaseOsoby;
import kontakty.utils.MyUtils;

@WebServlet(urlPatterns = { "/contacts" })
public class OsobyMainPage extends HttpServlet {

	private static final long serialVersionUID = -5183674312426160883L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();

		UserAccount user = MyUtils.dajZalogowanegoUzytkownika(session);

		String errorString = null;

		Connection conn = null;

		List<Osoby> listaOsob = null;

		int ileOsob = 0;

		if (user == null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {

			try {
				conn = MyUtils.nawiazIzwrocPolaczenie(request);

				listaOsob = DatabaseOsoby.dajListeOsob(conn);

				ileOsob = listaOsob.size();

			} catch (ClassNotFoundException | SQLException e) {
				errorString = "Błąd podczas pobierania listy użytkowników";
			}

			if (ileOsob == 0) {
				errorString = "Lista jest pusta.";
			}
			
			request.setAttribute("ile", ileOsob);
			request.setAttribute("errorString", errorString);
			request.setAttribute("listaOsob", listaOsob);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/contacts.jsp");
			dispatcher.forward(request, response);

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
