package com.Michalski.Mosiolek.Minner.Communication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 
 * @author wojci
 * <p>
 * Klasa s³u¿¹ca do przesy³ania danych zwi¹zanych z klientem
 */
public class Client implements Communication {
	/**
	 * 
	 */
	private static final long serialVersionUID = 272484732295243662L;
	private String name,surname, phoneNumber, pesel,
	country, city, street, homenumber, postcode,message;
	private WhatToDo what;
	private int idClient;
	private boolean isError = false;
	/**
	 * Konstruktor
	 * @param idClient idClienta w bazie
	 * @param name Imie klienta
	 * @param surname Nazwisko Klienta
	 * @param phoneNumber Numertelefonu
	 * @param pesel Pesel klienta
	 * @param country Kraj klienta
	 * @param city Miasto 
	 * @param street Ulica
	 * @param homenumber Numerdomu
	 * @param postcode Kod pocztowy
	 */
	public Client(int idClient,@Nullable String name,@Nullable String surname,@Nullable String phoneNumber,@Nullable String pesel,@Nullable String country,@Nullable String city,
			@Nullable String street,@Nullable String homenumber,@Nullable String postcode) {
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.pesel = pesel;
		this.country = country;
		this.city = city;
		this.street = street;
		this.homenumber = homenumber;
		this.postcode = postcode;
		this.idClient = idClient;
	}
	/**
	 * Konstruktor
	 * @param idClient idClienta w bazie
	 * @param name Imie klienta
	 * @param surname Nazwisko Klienta
	 * @param phoneNumber Numertelefonu
	 * @param pesel Pesel klienta
	 * @param country Kraj klienta
	 * @param city Miasto 
	 * @param street Ulica
	 * @param homenumber Numerdomu
	 * @param postcode Kod pocztowy
	 * @param what okreœla jak¹ operacje ma wykonaæ serwer na obiekcie
	 */
	public Client(int idClient,@Nullable String name,@Nullable String surname,@Nullable String phoneNumber,@NotNull String pesel,@Nullable String country,@Nullable String city,
			@Nullable String street,@Nullable String homenumber,@Nullable String postcode,@NotNull WhatToDo what) {
		this.name = name;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.pesel = pesel;
		this.country = country;
		this.city = city;
		this.street = street;
		this.homenumber = homenumber;
		this.postcode = postcode;
		this.what = what;
		this.idClient = idClient;
	}
	/**
	 * Konstruktor, g³ównie u¿ywany do zwracania obiektu do klienta
	 * @param isError Czy wyst¹pi³ b³¹d
	 * @param message Wiadomoœæ b³êdu
	 */
	public Client(boolean isError,@Nullable String message) {
		this.message = message;
		this.isError = isError;
	}
	/**
	 * Zwraca imie klienta
	 * @return imie klienta
	 */
	public @Nullable String getName() {
		return name;
	}
	/**
	 * Zwraca nazwisko klienta
	 * @return
	 */
	public @Nullable String getSurname() {
		return surname;
	}
	/**
	 * Zwraca numer telefonu
	 * @return
	 */
	public @Nullable String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Zwraca Pesel
	 * @return
	 */
	public @Nullable String getPesel() {
		return pesel;
	}
	/**
	 * Zwraca kraj klienta
	 * @return
	 */
	public @Nullable String getCountry() {
		return country;
	}
	/**
	 * Zwraca miasto
	 * @return
	 */
	public @Nullable String getCity() {
		return city;
	}
	/**
	 * Zwraca ulice
	 * @return
	 */
	public @Nullable String getStreet() {
		return street;
	}
	/**
	 * Zwraca numer domu
	 * @return
	 */
	public @Nullable String getHomenumber() {
		return homenumber;
	}
	/**
	 * Zwraca kod pocztowy
	 * @return
	 */
	public @Nullable String getPostcode() {
		return postcode;
	}
	
	@Override
	public @Nullable String getMessage() {
		return this.message;
	}
	/**
	 * Zwraca id klienta
	 * @return
	 */
	public int getIdClient() {
		return idClient;
	}
	/**
	 * Ustawia wiadomoœæ b³êdu
	 * @param message Wiadomoœæ b³êdu
	 */
	public void setMessage(@Nullable String message) {
		this.message = message;
	}
	
	@Override
	public boolean isError() {
		return this.isError;
	}

	@Override
	public @NotNull WhatToDo getWhatToDo() {
		// TODO Auto-generated method stub
		return this.what;
	}
	/**
	 * Ustawia operacje serwera
	 * @param what Operacja serwera
	 */
	public void setWhatToDo(@NotNull WhatToDo what)
	{
		this.what = what;
	}

}
