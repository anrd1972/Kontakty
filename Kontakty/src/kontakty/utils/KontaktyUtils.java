package kontakty.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KontaktyUtils {

	/**
	 * Sprawdzanie poprawności formatu adresu email
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmailAddress(String email) {

		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\."
				+ "[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);

		return m.matches();
	}

	/**
	 * Sprawdzanie czy String jest w formacjie DD-MM-RRRR
	 * 
	 * @param pStr
	 * @return
	 */
	public static boolean isDateFormatInString(String pStr) {

		String ePattern = "([0-9]{4})-([0-9]{2})-([0-9]{2})";

		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(pStr);

		return m.matches();

	}

	/**
	 * Sprawdzanie czy String nie jest pusty
	 * 
	 * @param pStr
	 * @return
	 */
	public static boolean isBlankOrNull(String pStr) {

		boolean isEmpty = false;

		if (pStr == null || pStr.isEmpty()) {
			isEmpty = true;
		}

		return isEmpty;
	}

	/**
	 * Konwersja <code>String</code> na <code>Date</code>
	 * 
	 * @param <code>String</code>
	 * @return
	 */
	public static Date stringToDate(String pStr) {
		Date sqlData = null;
		sqlData = Date.valueOf(pStr);
		return sqlData;
	}

	/**
	 * Konwersja <code>Date</code> na <code>String</code>
	 * 
	 * @param <code>String</code>
	 * @return
	 */
	public static String dateToString(Date pDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdf.format(pDate);
		return dateStr;
	}

	/**
	 * Sprawdzanie czy dane do logowania sa puste
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean sprawdzDaneLogowanie(String username, String password) {

		boolean isEmpty = false;

		if (KontaktyUtils.isBlankOrNull(username) || KontaktyUtils.isBlankOrNull(password)) {
			isEmpty = true;
		}

		return isEmpty;
	}

	/**
	 * Sprawdzanie czy wymagane dane z formularza nie są puste
	 * 
	 * @param username
	 * @param password
	 * @param userImie
	 * @param userNazwisko
	 * @return
	 */
	public static boolean sprawdzDaneRejestracja(String username, String password, String userImie,
			String userNazwisko) {

		boolean isEmpty = false;

		if (KontaktyUtils.isBlankOrNull(username) || KontaktyUtils.isBlankOrNull(password)
				|| KontaktyUtils.isBlankOrNull(userImie) || KontaktyUtils.isBlankOrNull(userNazwisko)) {
			isEmpty = true;
		}

		return isEmpty;
	}

}
