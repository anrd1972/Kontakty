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
import kontakty.utils.DatabaseOsoby;
import kontakty.utils.MyUtils;

@WebServlet(urlPatterns = { "/rem" })
public class DoDeleteOsoba extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7920513294010432930L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");

		HttpSession session = req.getSession();
		UserAccount loggedUser = MyUtils.dajZalogowanegoUzytkownika(session);

		Connection conn = null;

		boolean hasErrors = false;
		String errorString = null;

		if (loggedUser == null) {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(req, resp);
		} else {
			
			int id = Integer.parseInt(req.getParameter("id"));
			
			try {
				conn = MyUtils.nawiazIzwrocPolaczenie(req);

				DatabaseOsoby.usunOsobe(conn, id);

			} catch (ClassNotFoundException | SQLException e) {
				hasErrors = true;
				errorString = "Błąd podczas usuwania osoby kontaktowej";
				System.out.println(e.getMessage());
			}
		}

		if (hasErrors) {
			req.setAttribute("errorString", errorString);
		}

		MyConnectionUtils.closeMyConnection(conn);
		
		resp.sendRedirect(req.getContextPath() + "/contacts");
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
