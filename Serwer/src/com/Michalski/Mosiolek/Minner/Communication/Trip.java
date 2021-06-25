package com.Michalski.Mosiolek.Minner.Communication;

import java.sql.Date;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/**
 * 
 * @author wojci
 * <p>
 * Klasa Komunikacyjna
 */
public class Trip implements Communication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3707264398926554289L;
	private int idTrip, maxguest, standard;
	private java.sql.Date date_out, date_back;
	private String name,phonenumber,message;
	private float price;
	private WhatToDo what;
	private boolean isError =false;
	/**
	 * Konstruktor podstawowy
	 * @param idTrip id wycieczki
	 * @param maxguest maksymalna iloœæ goœci
	 * @param standard standard
	 * @param date_out data wyjazdu
	 * @param date_back data powrotu
	 * @param name nazwa
	 * @param phonenumber numer telefonu
	 * @param price cena
	 */
	public Trip(int idTrip, int maxguest, int standard,@Nullable Date date_out,@Nullable Date date_back,@Nullable String name,@Nullable String phonenumber,
			float price) {
		super();
		this.idTrip = idTrip;
		this.maxguest = maxguest;
		this.standard = standard;
		this.date_out = date_out;
		this.date_back = date_back;
		this.name = name;
		this.phonenumber = phonenumber;
		this.price = price;
	}
	/**
	 * Konstruktor rozszerzony
	 * @param idTrip id wycieczki
	 * @param maxguest maksymalna iloœæ goœci
	 * @param standard standard
	 * @param date_out data wyjazdu
	 * @param date_back data powrotu
	 * @param name nazwa
	 * @param phonenumber numer telefonu
	 * @param price cena
	 * @param what okreœla operacje serwera
	 */
	public Trip(int idTrip, int maxguest, int standard,@Nullable Date date_out,@Nullable Date date_back,@Nullable String name,@Nullable String phonenumber,
			float price,@NotNull WhatToDo what) {
		super();
		this.idTrip = idTrip;
		this.maxguest = maxguest;
		this.standard = standard;
		this.date_out = date_out;
		this.date_back = date_back;
		this.name = name;
		this.phonenumber = phonenumber;
		this.price = price;
		this.what = what;
	}
	/**
	 * Konstruktor "zwrotny"
	 * @param isError czy wyst¹pi³ b³¹d
	 * @param message wiadomoœæ b³êdu
	 */
	public Trip(boolean isError,@Nullable String message)
	{
		this.isError = isError;
		this.message = message;
	}
	/**
	 * Zwraca id wycieczki
	 * @return
	 */
	public int getIdTrip() {
		return idTrip;
	}
	/**
	 * Zwraca maksymaln¹ iloœæ goœci
	 * @return
	 */
	public int getMaxguest() {
		return maxguest;
	}
	/**
	 * Zwraca standard
	 * @return
	 */
	public int getStandard() {
		return standard;
	}
	/**
	 * Zwraca datê wyjazdu
	 * @return
	 */
	public @Nullable java.sql.Date getDate_out() {
		return date_out;
	}
	/**
	 * Zwraca data przyjazdu
	 * @return
	 */
	public @Nullable java.sql.Date getDate_back() {
		return date_back;
	}
	/**
	 * Zwraca nazwê wycieczki
	 * @return
	 */
	public @Nullable String getName() {
		return name;
	}
	/**
	 * Zwraca numer telefonu
	 * @return
	 */
	public @Nullable String getPhonenumber() {
		return phonenumber;
	}

	public float getPrice() {
		return price;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public boolean isError() {
		return this.isError;
	}

	@Override
	public WhatToDo getWhatToDo() {
		return this.what;
	}
	public void setWhatToDo(@NotNull WhatToDo what)
	{
		this.what = what;
	}

}
