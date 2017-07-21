package kontakty.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	 * @param pStr
	 * @return
	 */
	public static boolean isDateFormatInString(String pStr) {
		
		String ePattern = "([0-9]{2})-([0-9]{2})-([0-9]{4})";
		
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(pStr);

		return m.matches();
		
	}

	/**
	 * Konwersja String na Date
	 * 
	 * @param pData
	 * @return
	 */
	public static Date stringToDate(String pData) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date data = null;

		try {

			data = formatter.parse(pData);

		} catch (ParseException e) {
			System.out.println("Problem z konwersją String na Date");
		}

		return data;
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
	
}
