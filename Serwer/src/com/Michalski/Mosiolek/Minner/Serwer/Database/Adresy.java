package com.Michalski.Mosiolek.Minner.Serwer.Database;

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
 * Klasa do hibernate, przechowuje adresy
 */
@Entity(name = "ADRESY")
@Table(name = "ADRESY")
public class Adresy {
	@Id
	@GeneratedValue(generator = "sequence-generator-Adresy")
	@GenericGenerator(
				name = "sequence-generator-Adresy",
				strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		    	parameters = {
		    			@Parameter(name = "sequence_name", value = "AdresySeq"),
		    	        @Parameter(name = "initial_value", value = "4"),
		    	        @Parameter(name = "increment_size", value = "1")
				}
			)
	@Column(name = "idAdresy")
	private int idAdresy;
	
	@Column(name = "Kraj", nullable = false)
	private String country;
	
	@Column(name = "Miejscowosc", nullable = false)
	private String city;
	
	@Column(name = "NumerDomu", nullable = false)
	private String homenumber;
	
	@Column(name = "KodPocztowy", nullable = false)
	private String postdode;
	
	@Column(name = "Ulica", nullable = false)
	private String street;
	/**
	 * Konstruktor
	 * @param country pañstwo
	 * @param city miasto
	 * @param homenumber numerdomu
	 * @param postdode kod pocztowy
	 * @param street ulica
	 */
	public Adresy(@NotNull String country,@NotNull String city,@NotNull String homenumber,@NotNull String postdode,@NotNull String street) {
		this.country = country;
		this.city = city;
		this.homenumber = homenumber;
		this.postdode = postdode;
		this.street = street;
	}
	
	@SuppressWarnings("unused")
	private Adresy(){}
	
	/**
	 * Zwraca id adresu
	 * @return
	 */
	public int getIdAdresy() {
		return idAdresy;
	}
	/**
	 * Zwraca kraj adresu
	 * @return
	 */
	public @NotNull String getCountry() {
		return country;
	}
	/**
	 * Zwraca miasto adresu
	 * @return
	 */
	public @NotNull String getCity() {
		return city;
	}
	/**
	 * Zwraca numer domu adresu
	 * @return
	 */
	public @NotNull String getHomenumber() {
		return homenumber;
	}
	/**
	 * Zwraca kod pocztowy adresu
	 * @return
	 */
	public @NotNull String getPostdode() {
		return postdode;
	}
	/**
	 * Zwraca ulice adresu
	 * @return
	 */
	public @NotNull String getStreet() {
		return street;
	}
	/**
	 * Ustawia id adresu
	 * @param idAdresy
	 */
	public void setIdAdresy(int idAdresy) {
		this.idAdresy = idAdresy;
	}
	/**
	 * Ustawia kraj adresu
	 * @param country 
	 */
	public void setCountry(@NotNull String country) {
		this.country = country;
	}
	/**
	 * Ustawia numer domu adresu
	 * @param homenumber
	 */
	public void setHomenumber(@NotNull String homenumber) {
		this.homenumber = homenumber;
	}
	/**
	 * Ustawia kod pocztowy adresu
	 * @param postdode
	 */
	public void setPostdode(@NotNull String postdode) {
		this.postdode = postdode;
	}
	/**
	 * Ustawia ulicê adresu
	 * @param street
	 */
	public void setStreet(@NotNull String street) {
		this.street = street;
	}
	/**
	 * Ustawia miasto adresu
	 * @param city
	 */
	public void setCity(@NotNull String city) {
		this.city = city;
	}
}
