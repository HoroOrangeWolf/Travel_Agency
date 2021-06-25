package com.Michalski.Mosiolek.Minner.Communication;

import java.sql.Date;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 
 * @author wojci
 * <p>
 * @see Trip
 * <p>
 * Klasa komunikacyjna
 */

public class Aggrement extends Trip {
	private static final long serialVersionUID = 4369543172961302359L;
	private int idUmowy, idClient, guestcount,idaggrement;
	private java.sql.Date aggrementdate;
	private String payment_type, payment_status;
	
	/**
	 * Konstruktor
	 * @param idAggrement id umoway 
	 * @param idTrip id wycieczki
	 * @param maxguest maksymalna ilo�� ko�ci
	 * @param standard standard wycieczki
	 * @param idUmowy id umowy
	 * @param guestcount ilo�� go�ci
	 * @param idClient idclienta
	 * @param date_out data wyjazdu
	 * @param date_back data powrotu
	 * @param aggrementdate data umowy
	 * @param name nazwa wycieczki
	 * @param phonenumber numer telefonu
	 * @param price cena
	 * @param payment_type rodzaj p�atno�ci
	 * @param payment_status status p�atno�ci
	 * @param what operacja jak� ma wykona� serwer
	 */
	public Aggrement(int idAggrement, int idTrip, int maxguest, int standard, int idUmowy, int guestcount, int idClient,@Nullable Date date_out,@Nullable Date date_back,@Nullable Date aggrementdate,@Nullable String name,
			@Nullable String phonenumber, float price,@Nullable String payment_type,@Nullable String payment_status,@NotNull WhatToDo what) {
		super(idTrip, maxguest, standard, date_out, date_back, name, phonenumber, price, what);
		this.aggrementdate = aggrementdate;
		this.idUmowy = idUmowy;
		this.idClient = idClient;
		this.guestcount = guestcount;
		this.payment_status = payment_status;
		this.payment_type = payment_type;
		this.idaggrement = idAggrement;
	}
	/**
	 * Konstruktor
	 * @param idAggrement id umoway 
	 * @param idTrip id wycieczki
	 * @param maxguest maksymalna ilo�� ko�ci
	 * @param standard standard wycieczki
	 * @param idUmowy id umowy
	 * @param guestcount ilo�� go�ci
	 * @param idClient idclienta
	 * @param date_out data wyjazdu
	 * @param date_back data powrotu
	 * @param aggrementdate data umowy
	 * @param name nazwa wycieczki
	 * @param phonenumber numer telefonu
	 * @param price cena
	 * @param payment_type rodzaj p�atno�ci
	 * @param payment_status status p�atno�ci
	 */
	public Aggrement(int idAggrement,int idTrip, int maxguest, int standard, int idUmowy, int guestcount, int idClient,@Nullable Date date_out,@Nullable Date date_back,@Nullable Date aggrementdate,@Nullable String name,
			@Nullable String phonenumber, float price,@Nullable String payment_type,@Nullable String payment_status) {
		super(idTrip, maxguest, standard, date_out, date_back, name, phonenumber, price);
		this.aggrementdate = aggrementdate;
		this.idUmowy = idUmowy;
		this.idClient = idClient;
		this.guestcount = guestcount;
		this.payment_status = payment_status;
		this.payment_type = payment_type;
		this.idaggrement = idAggrement;
	}
	/**
	 * Konstruktor 
	 * @param isError czy wyst�pi� b��d
	 * @param message wiadomo�� 
	 */
	public Aggrement(boolean isError,@Nullable String message)
	{
		super(isError, message);
	}
	/**
	 * Zwraca id umowy og�lne
	 * @return id umowy
	 */
	public int getIdaggrement() {
		return idaggrement;
	}
	/**
	 * Zwraca id umowy p�atno�ci
	 * @return id umowy
	 */
	public int getIdUmowy() {
		return idUmowy;
	}
	/**
	 * zwraca Id klienta
	 * @return id client
	 */
	public int getIdClient() {
		return idClient;
	}
	/**
	 * Zwraca ilo�� go�ci
	 * @return ilo�� go�ci
	 */
	public int getGuestcount() {
		return guestcount;
	}
	/**
	 * Zwraca date umowy
	 * @return data umowy
	 */
	public @Nullable java.sql.Date getAggrementdate() {
		return aggrementdate;
	}
	/**
	 * Zwraca typ p�atno�ci
	 * @return typ p�atno�ci
	 */
	public @Nullable String getPayment_type() {
		return payment_type;
	}
	/**
	 * Zwraca status p�atno�ci 
	 * @return status p�atno�ci
	 */
	public @Nullable String getPayment_status() {
		return payment_status;
	}
	
}
