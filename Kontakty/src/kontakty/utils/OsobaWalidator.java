package kontakty.utils;

public class OsobaWalidator {
	
	public static boolean sprawdzOsobaImie(String osobaImie) {
		
		boolean komunikat = false;
		
		if (osobaImie.isEmpty()) {
			komunikat = true;
		}
		return komunikat;
	}
	
	
	
	public static boolean sprawdzOsobaEmail(String osobaEmail) {
		
		boolean komunikat = false;
		
		if (osobaEmail.equals(null)) {
			komunikat = true;
		} else {
			if(!KontaktyUtils.isValidEmailAddress(osobaEmail)) {
				komunikat = true;
			}
		}
				
		return komunikat;
	}

}
