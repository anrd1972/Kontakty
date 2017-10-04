package kontakty.models;

import java.util.Date;

public class Osoby {
	
	private int idOsoby;			//not null
	private String osobaImie; 		//not null
	private String osobaNazwisko;
	private String osobaEmail;		//not null
	private String osobaTelefonDom;
	private String osobaTelefonPraca;
	private String osobaAdresUlica;
	private String osobaAdresNrDomu;
	private String osobaAdresNrMieszkania;
	private String osobaAdresMiasto;
	private String osobaAdresKodPocztowy;
	private Date osobaUrodziny;
	
	private String operacja;
	
	public Osoby() {}


	public Osoby(int idOsoby, String osobaImie, String osobaNazwisko, String osobaEmail, String osobaTelefonDom,
			String osobaTelefonPraca, String osobaAdresUlica, String osobaAdresNrDomu, String osobaAdresNrMieszkania,
			String osobaAdresMiasto, String osobaAdresKodPocztowy, Date osobaUrodziny, String operacja) {
		super();
		this.idOsoby = idOsoby;
		this.osobaImie = osobaImie;
		this.osobaNazwisko = osobaNazwisko;
		this.osobaEmail = osobaEmail;
		this.osobaTelefonDom = osobaTelefonDom;
		this.osobaTelefonPraca = osobaTelefonPraca;
		this.osobaAdresUlica = osobaAdresUlica;
		this.osobaAdresNrDomu = osobaAdresNrDomu;
		this.osobaAdresNrMieszkania = osobaAdresNrMieszkania;
		this.osobaAdresMiasto = osobaAdresMiasto;
		this.osobaAdresKodPocztowy = osobaAdresKodPocztowy;
		this.osobaUrodziny = osobaUrodziny;
		this.operacja = operacja;
	}


	/**
	 * @return the idOsoby
	 */
	public int getIdOsoby() {
		return idOsoby;
	}


	/**
	 * @param idOsoby the idOsoby to set
	 */
	public void setIdOsoby(int idOsoby) {
		this.idOsoby = idOsoby;
	}


	/**
	 * @return the osobaImie
	 */
	public String getOsobaImie() {
		return osobaImie;
	}


	/**
	 * @param osobaImie the osobaImie to set
	 */
	public void setOsobaImie(String osobaImie) {
		this.osobaImie = osobaImie;
	}


	/**
	 * @return the osobaNazwisko
	 */
	public String getOsobaNazwisko() {
		return osobaNazwisko;
	}


	/**
	 * @param osobaNazwisko the osobaNazwisko to set
	 */
	public void setOsobaNazwisko(String osobaNazwisko) {
		this.osobaNazwisko = osobaNazwisko;
	}


	/**
	 * @return the osobaEmail
	 */
	public String getOsobaEmail() {
		return osobaEmail;
	}


	/**
	 * @param osobaEmail the osobaEmail to set
	 */
	public void setOsobaEmail(String osobaEmail) {
		this.osobaEmail = osobaEmail;
	}


	/**
	 * @return the osobaTelefonDom
	 */
	public String getOsobaTelefonDom() {
		return osobaTelefonDom;
	}


	/**
	 * @param osobaTelefonDom the osobaTelefonDom to set
	 */
	public void setOsobaTelefonDom(String osobaTelefonDom) {
		this.osobaTelefonDom = osobaTelefonDom;
	}


	/**
	 * @return the osobaTelefonPraca
	 */
	public String getOsobaTelefonPraca() {
		return osobaTelefonPraca;
	}


	/**
	 * @param osobaTelefonPraca the osobaTelefonPraca to set
	 */
	public void setOsobaTelefonPraca(String osobaTelefonPraca) {
		this.osobaTelefonPraca = osobaTelefonPraca;
	}


	/**
	 * @return the osobaAdresUlica
	 */
	public String getOsobaAdresUlica() {
		return osobaAdresUlica;
	}


	/**
	 * @param osobaAdresUlica the osobaAdresUlica to set
	 */
	public void setOsobaAdresUlica(String osobaAdresUlica) {
		this.osobaAdresUlica = osobaAdresUlica;
	}


	/**
	 * @return the osobaAdresNrDomu
	 */
	public String getOsobaAdresNrDomu() {
		return osobaAdresNrDomu;
	}


	/**
	 * @param osobaAdresNrDomu the osobaAdresNrDomu to set
	 */
	public void setOsobaAdresNrDomu(String osobaAdresNrDomu) {
		this.osobaAdresNrDomu = osobaAdresNrDomu;
	}


	/**
	 * @return the osobaAdresNrMieszkania
	 */
	public String getOsobaAdresNrMieszkania() {
		return osobaAdresNrMieszkania;
	}


	/**
	 * @param osobaAdresNrMieszkania the osobaAdresNrMieszkania to set
	 */
	public void setOsobaAdresNrMieszkania(String osobaAdresNrMieszkania) {
		this.osobaAdresNrMieszkania = osobaAdresNrMieszkania;
	}


	/**
	 * @return the osobaAdresMiasto
	 */
	public String getOsobaAdresMiasto() {
		return osobaAdresMiasto;
	}


	/**
	 * @param osobaAdresMiasto the osobaAdresMiasto to set
	 */
	public void setOsobaAdresMiasto(String osobaAdresMiasto) {
		this.osobaAdresMiasto = osobaAdresMiasto;
	}


	/**
	 * @return the osobaAdresKodPocztowy
	 */
	public String getOsobaAdresKodPocztowy() {
		return osobaAdresKodPocztowy;
	}


	/**
	 * @param osobaAdresKodPocztowy the osobaAdresKodPocztowy to set
	 */
	public void setOsobaAdresKodPocztowy(String osobaAdresKodPocztowy) {
		this.osobaAdresKodPocztowy = osobaAdresKodPocztowy;
	}


	/**
	 * @return the osobaUrodziny
	 */
	public Date getOsobaUrodziny() {
		return osobaUrodziny;
	}


	/**
	 * @param osobaUrodziny the osobaUrodziny to set
	 */
	public void setOsobaUrodziny(Date osobaUrodziny) {
		this.osobaUrodziny = osobaUrodziny;
	}


	/**
	 * @return the operacja
	 */
	public String getOperacja() {
		return operacja;
	}


	/**
	 * @param operacja the operacja to set
	 */
	public void setOperacja(String operacja) {
		this.operacja = operacja;
	}
	
	

}
