package com.Michalski.Mosiolek.Minner.Serwer.Database;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.jetbrains.annotations.NotNull;

/**
 * 
 * @author wojci
 * <p>
 * Klasa reprezentuj¹ca umowy w bazie danych
 */

@Entity(name = "UMOWY")
@Table(name = "Umowy")
public class Aggrement  {


	@Id
	@GeneratedValue(generator = "sequence-generator-Umowy")
	@GenericGenerator(
				name = "sequence-generator-Umowy",
				strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		    	parameters = {
		    			@Parameter(name = "sequence_name", value = "Umowyseq"),
		    	        @Parameter(name = "initial_value", value = "4"),
		    	        @Parameter(name = "increment_size", value = "1")
				}
			)
	@Column(name = "idUmowy", unique = true)
	private int idAggrement;
	@Column(name = "idKlienci", nullable = false)
	private int idClient;
	@Column(name = "idWycieczki", nullable =  false)
	private int idTrip;
	@Column(name = "DataUmowy", nullable = false)
	private java.sql.Date aggrement_date;
	@Column(name = "LiczbaOsob", nullable = false)
	private int guestcount;
	

	/**
	 * Konstruktor
	 * @param idclient
	 * @param idTrip
	 * @param guestcount 
	 * @param aggrement_date
	 */
	public Aggrement(int idclient,int idTrip,int guestcount,@NotNull Date aggrement_date) {
		this.idClient = idclient;
		this.idTrip = idTrip;
		this.guestcount = guestcount;
		this.aggrement_date = aggrement_date;
	}
	
	@SuppressWarnings("unused")
	private Aggrement() {};
	protected int getIdClient() {
		return idClient;
	}
	/**
	 * Zwraca id umoway
	 * @return
	 */
	public int getIdAggrement() {
		return idAggrement;
	}
	
	/**
	 * Ustawia id umowy
	 * @param idAggrement id umowy
	 */
	protected void setIdAggrement(int idAggrement) {
		this.idAggrement = idAggrement;
	}
	/**
	 * Iloœæ goœci
	 * @return zwraca iloœæ goœci
	 */
	public int getGuestcount() {
		return guestcount;
	}
	/**
	 * Zwraca date umowy
	 * @return
	 */
	public @NotNull java.sql.Date getAggrement_date() {
		return aggrement_date;
	}
	/**
	 * Ustawia date umowy
	 * @param aggrement_date
	 */
	public void setAggrement_date(@NotNull java.sql.Date aggrement_date) {
		this.aggrement_date = aggrement_date;
	}
	/**
	 * Ustawia iloœæ goœci
	 * @param guestcount ilosæ goœci
	 */
	public void setGuestcount(int guestcount) {
		this.guestcount = guestcount;
	}
	/**
	 * Ustawia id klienta
	 * @param idClient
	 */
	protected void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	/**
	 * Ustawia id klienta
	 * @param client przechowuje id klienta
	 */
	public void setIdClient(@NotNull Client client)
	{
		this.idClient = client.getIdClient();
	}
	/**
	 * Zwraca id wycieczki
	 * @return
	 */
	public int getIdTrip() {
		return idTrip;
	}
	/**
	 * Ustawia id wycieczki
	 * @param idTrip
	 */
	protected void setIdTrip(int idTrip) {
		this.idTrip = idTrip;
	}
	/**
	 * Ustawia id wycieczki
	 * @param of przechowuje id wycieczki
	 */
	public void setIdTrip(@NotNull Trip of)
	{
		this.idTrip = of.getIdTrip();
	}
}
