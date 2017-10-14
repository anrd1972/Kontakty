package kontakty.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kontakty.connectionUtils.MyConnectionUtils;
import kontakty.models.Osoby;
import kontakty.utils.DatabaseOsoby;
import kontakty.utils.KontaktyUtils;
import kontakty.utils.MyUtils;
import kontakty.utils.OsobaWalidator;

@WebServlet(urlPatterns = { "/doAdd" })
public class DoAddOsobaServlet extends HttpServlet {

	private static final long serialVersionUID = -9178736686143235128L;

	public DoAddOsobaServlet() {
		super();
	}

	Osoby osoba = null;

	String osobaImie;
	String osobaNazwisko;
	String osobaEmail;
	String osobaTelefonDom;
	String osobaTelefonPraca;
	String osobaAdresUlica;
	String osobaAdresNrDomu;
	String osobaAdresNrMieszkania;
	String osobaAdresMiasto;
	String osobaAdresKodPocztowy;
	String osobaUrodziny;
	String operacja;
	String id;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		// nie puste
		osobaImie = request.getParameter("osobaImie");

		osobaNazwisko = request.getParameter("osobaNazwisko");

		// nie puste
		osobaEmail = request.getParameter("osobaEmail");

		osobaTelefonDom = request.getParameter("osobaTelefonDom");
		osobaTelefonPraca = request.getParameter("osobaTelefonPraca");
		osobaAdresUlica = request.getParameter("osobaAdresUlica");
		osobaAdresNrDomu = request.getParameter("osobaAdresNrDomu");
		osobaAdresNrMieszkania = request.getParameter("osobaAdresNrMieszkania");
		osobaAdresMiasto = request.getParameter("osobaAdresMiasto");
		osobaAdresKodPocztowy = request.getParameter("osobaAdresKodPocztowy");

		// musi byc w formacie yyyy-mm-dd
		osobaUrodziny = request.getParameter("osobaUrodziny");

		operacja = request.getParameter("operacja");
		id = request.getParameter("idOsoby");

		String komunikat = null;

		Connection conn = null;

		if (OsobaWalidator.sprawdzOsobaImie(osobaImie)) {

			komunikat = "Imie jest wymagane";

			osoba = odtworzObiekt();

			request.setAttribute("errorString", komunikat);
			request.setAttribute("osoba", osoba);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addnew.jsp");
			dispatcher.forward(request, response);
		} else if (OsobaWalidator.sprawdzOsobaEmail(osobaEmail)) {

			komunikat = "Pusty adres email lub niepoprawny format";

			osoba = odtworzObiekt();

			request.setAttribute("errorString", komunikat);
			request.setAttribute("osoba", osoba);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addnew.jsp");
			dispatcher.forward(request, response);
		} else if (OsobaWalidator.sprawdzDate(osobaUrodziny)) {

			komunikat = "Data mysi być w formacie RRRR-MM-DD";

			osobaUrodziny = "1999-01-01";
			osoba = odtworzObiekt();

			request.setAttribute("errorString", komunikat);
			request.setAttribute("osoba", osoba);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/addnew.jsp");
			dispatcher.forward(request, response);
		} else {

			try {
				conn = MyUtils.nawiazIzwrocPolaczenie(request);

				if (operacja.equals("D")) {

					DatabaseOsoby.zapiszOsobe(conn, odtworzObiekt());

				} else {
					DatabaseOsoby.uspdateDanychOsoby(conn, odtworzObiekt());
				}

				MyConnectionUtils.closeMyConnection(conn);

				request.setAttribute("errorString", "Zapis zakończony powodzeniem");
				response.sendRedirect(request.getContextPath() + "/contacts");

			} catch (ClassNotFoundException | SQLException e) {

				System.out.println("Błąd podczas zapisu do bazy danych!");
				request.setAttribute("errorString", "Błąd podczas zapisu do bazy danych!");
				response.sendRedirect(request.getContextPath() + "/contacts");
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// zwraca obiekt Osoby
	public Osoby odtworzObiekt() {
		osoba = new Osoby();

		if (operacja.equals("M")) {
			osoba.setIdOsoby(Integer.parseInt(id));
		}

		osoba.setOsobaImie(osobaImie);
		osoba.setOsobaNazwisko(osobaNazwisko);
		osoba.setOsobaEmail(osobaEmail);
		osoba.setOsobaTelefonDom(osobaTelefonDom);
		osoba.setOsobaTelefonPraca(osobaTelefonPraca);
		osoba.setOsobaAdresUlica(osobaAdresUlica);
		osoba.setOsobaAdresNrDomu(osobaAdresNrDomu);
		osoba.setOsobaAdresNrMieszkania(osobaAdresNrMieszkania);
		osoba.setOsobaAdresMiasto(osobaAdresMiasto);
		osoba.setOsobaAdresKodPocztowy(osobaAdresKodPocztowy);

		if (osobaUrodziny.equals(null) || osobaUrodziny.equals("")) {
			osoba.setOsobaUrodziny(Date.valueOf("1900-01-01"));
		} else {
			osoba.setOsobaUrodziny(KontaktyUtils.stringToDate(osobaUrodziny));
		}

		return osoba;
	}

}
