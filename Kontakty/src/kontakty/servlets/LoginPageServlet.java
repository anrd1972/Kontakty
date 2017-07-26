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

@WebServlet(urlPatterns = { "/login" })
public class LoginPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6310197733882090154L;

	public LoginPageServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		session.invalidate();

		int userAccounts = 0;
		Connection conn = null;

		try {

			conn = MyUtils.nawiazIzwrocPolaczenie(req);

			userAccounts = DatabaseUtils.ileUserow(conn);
			
			MyConnectionUtils.closeMyConnection(conn);

		} catch (SQLException | ClassNotFoundException eSql) {
			eSql.printStackTrace();
		}

		if (userAccounts > 0) {
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			disp.forward(req, resp);
		} else {
			UserAccount user = new UserAccount();
			user.setOperacja("N");
			req.setAttribute("user", user);
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/register.jsp");
			disp.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
