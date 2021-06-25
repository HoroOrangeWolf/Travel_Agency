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
 * Reprezentuje wycieczkê w bazie danych
 */

@Entity(name = "TRIP")
@Table(name = "Wycieczki")
public class Trip {

	@Id
	@GeneratedValue(generator = "sequence-generator-Trip")
	@GenericGenerator(
				name = "sequence-generator-Trip",
				strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		    	parameters = {
		    			@Parameter(name = "sequence_name", value = "Wycieczkiseq"),
		    	        @Parameter(name = "initial_value", value = "4"),
		    	        @Parameter(name = "increment_size", value = "1")
				}
			)
	@Column(name = "idWycieczki", unique = true)
	private int idTrip;
	
	@Column(name = "idBiura", nullable = false)
	private int idOffice;
	
	@Column(name = "Cena", nullable = false)
	private float price;
	
	@Column(name = "DataWyjazdu", nullable = false)
	private java.sql.Date dateout;
	
	@Column(name = "DataPowrotu", nullable = false)
	private java.sql.Date datein;
	
	@Column(name = "Nazwa", nullable = false)
	private String name;
	
	@Column(name = "Telefon", nullable = false)
	private String phonenumber;
	
	@Column(name = "MaxOsob", nullable = false)
	private int maxguests;
	
	@Column(name = "Standard", nullable = false)
	private int standard;
	
	/**
	 * Konstruktor wycieczki
	 * @param idOffice id biura
	 * @param price cena 
	 * @param dateout data wyjazdu
	 * @param datein data przyjazdu
	 * @param name nazwa
	 * @param phonenumber numer telefonu
	 * @param maxguests maksymalna iloœæ goœci
	 * @param standard standard
	 */
	public Trip(int idOffice, float price,@NotNull Date dateout,@NotNull Date datein,@NotNull String name,@NotNull String phonenumber, int maxguests,
			int standard) {
		this.idOffice = idOffice;
		this.price = price;
		this.dateout = dateout;
		this.datein = datein;
		this.name = name;
		this.phonenumber = phonenumber;
		this.maxguests = maxguests;
		this.standard = standard;
	}
	@SuppressWarnings("unused")
	private Trip() {};
	/**
	 * Zwraca id wycieczki
	 * @return
	 */
	public int getIdTrip() {
		return idTrip;
	}
	/**
	 * Zwraca id biura
	 * @return
	 */
	public int getIdOffice() {
		return idOffice;
	}
	/**
	 * Zwraca cenne 
	 * @return
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * Zwraca datê wyjazdu
	 * @return
	 */
	public @NotNull java.sql.Date getDateout() {
		return dateout;
	}
	/**
	 * Zwraca datê przyjazdu
	 * @return
	 */
	public @NotNull java.sql.Date getDatein() {
		return datein;
	}
	/**
	 * Zwraca nazwê wycieczki
	 * @return
	 */
	public @NotNull String getName() {
		return name;
	}
	/** 
	 * Zwraca numer telefonu
	 * @return
	 */
	public String getPhonenumber() {
		return phonenumber;
	}
	/**
	 * Zwraca maksymaln¹ iloœæ uczestników wycieczki
	 * @return
	 */
	public int getMaxguests() {
		return maxguests;
	}
	/**
	 * Zwraca standard wycieczki
	 * @return
	 */
	public int getStandard() {
		return standard;
	}
	/**
	 * Ustawia id wycieczki
	 * @param idTrip id wycieczki
	 */
	public void setIdTrip(int idTrip) {
		this.idTrip = idTrip;
	}
	/**
	 * Ustawia id biura wycieczki
	 * @param idOffice id biura
	 */
	public void setIdOffice(int idOffice) {
		this.idOffice = idOffice;
	}
	/**
	 * Ustawia cenne wycieczki
	 * @param price cenna wycieczki
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * Ustawia datê wyjazdu
	 * @param dateout data wyjazdu
	 */
	public void setDateout(@NotNull java.sql.Date dateout) {
		this.dateout = dateout;
	}
	/**
	 * Ustawia datê powrotu z wycieczki
	 * @param datein data powrotu
	 */
	public void setDatein(@NotNull java.sql.Date datein) {
		this.datein = datein;
	}
	/**
	 * Ustawia nazwê wycieczki
	 * @param name nazwa wycieczki
	 */
	public void setName(@NotNull String name) {
		this.name = name;
	}
    /**
     * Ustawia numer telefonu do wycieczki
     * @param phonenumber
     */
	public void setPhonenumber(@NotNull String phonenumber) {
		this.phonenumber = phonenumber;
	}
	/**
	 * Ustawia maksymln¹ iloœæ goœci jaka mo¿e byæ na wycieczce
	 * @param maxguests
	 */
	public void setMaxguests(int maxguests) {
		this.maxguests = maxguests;
	}
	/**
	 * Ustawia standard wycieczki
	 * @param standard
	 */
	public void setStandard(int standard) {
		this.standard = standard;
	}	
}
