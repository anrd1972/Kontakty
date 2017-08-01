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

@WebServlet(urlPatterns = { "/reg" })
public class RegisterPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4020836409372219932L;

	public RegisterPageServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		UserAccount loggedUser = MyUtils.dajZalogowanegoUzytkownika(session);

		Connection conn = null;
		UserAccount user = null;

		boolean hasErrors = false;
		String errorString = null;
		int idUser = 0;

		if (loggedUser == null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		} else {

			String operacja = request.getParameter("mode");

			if (operacja.equals("M")) {
				idUser = Integer.parseInt(request.getParameter("id"));

				try {
					conn = MyUtils.nawiazIzwrocPolaczenie(request);

					user = DatabaseUtils.dajDaneUzytkownika(idUser, conn);

					user.setOperacja(operacja);
					user.setIdUser(idUser);

					MyConnectionUtils.closeMyConnection(conn);

				} catch (ClassNotFoundException | SQLException e) {
					hasErrors = true;
					errorString = "Błąd podczas pobierania listy użytkowników";
					System.out.println(e.getMessage());
				}
			} else {
				user = new UserAccount();
				user.setOperacja(operacja);
			} 

			if (hasErrors) {
				response.sendRedirect(request.getServletPath() + "/users");
			}

			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);

			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			dispatcher.forward(request, response);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
