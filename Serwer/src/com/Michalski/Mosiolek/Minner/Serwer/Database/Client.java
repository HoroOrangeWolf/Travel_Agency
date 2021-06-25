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
 * Klasa reprezentuj¹ca klienta w bazie danych
 */

@Entity(name = "KLIENCI")
@Table(name = "Klienci")
public class Client {
	
	@Id
	@GeneratedValue(generator = "sequence-generator-Klienci")
	@GenericGenerator(
				name = "sequence-generator-Klienci",
				strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		    	parameters = {
		    			@Parameter(name = "sequence_name", value = "KlienciSeq"),
		    	        @Parameter(name = "initial_value", value = "4"),
		    	        @Parameter(name = "increment_size", value = "1")
				}
			)
	@Column(name = "idKlienci", unique = false)
	private int idClient;
	
	@Column(name = "Imie", nullable = false)
	private String name;
	
	@Column(name = "Nazwisko", nullable = false)
	private String surname;
	
	@Column(name = "Pesel", nullable = false)
	private String pesel;
	
	@Column(name = "Telefon", nullable = false)
	private String phonenumber;
	
	@Column(name = "idAdresy", nullable = false)
	private int idAdresy;
	
	@Column(name = "idBiura", nullable = false)
	private int idBiura;
	/**
	 * Konstruktor
	 * @param name imie
	 * @param surname naziwsko
	 * @param pesel pesel
 	 * @param phonenumber numer telefonu
	 */
	public Client(@NotNull String name,@NotNull String surname,@NotNull String pesel,@NotNull String phonenumber) {
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
		this.phonenumber = phonenumber;
	}
	
	@SuppressWarnings("unused")
	private Client()
	{}
	/**
	 * Zwraca imie
	 * @return
	 */
	public @NotNull String getName() {
		return name;
	}
	/**
	 * Zwraca nazwisko
	 * @return
	 */
	public @NotNull String getSurname() {
		return surname;
	}
	/**
	 * Zwraca pesel
	 * @return
	 */
	public @NotNull String getPesel() {
		return pesel;
	}
	/**
	 * Zwraca numer telefonu
	 * @return
	 */
	public @NotNull String getPhonenumber() {
		return phonenumber;
	}
	/**
	 * Ustawia id klienta
	 * @param idClient
	 */
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	/**
	 * Ustawia imie klienta u¿ywany do aktualizacji
	 * @param name
	 */
	public void setName(@NotNull String name) {
		this.name = name;
	}
	/**
	 * Ustawia nazwisko klienta u¿ywany do aktualizacji
	 * @param surname
	 */
	public void setSurname(@NotNull String surname) {
		this.surname = surname;
	}
	/**
	 * Ustawia pesel klienta u¿ywany do aktualizacji
	 * @param pesel
	 */
	public void setPesel(@NotNull String pesel) {
		this.pesel = pesel;
	}
	/**
	 * Ustawia numer telefonu u¿ywany do aktualizacji
	 * @param phonenumber
	 */
	public void setPhonenumber(@NotNull String phonenumber) {
		this.phonenumber = phonenumber;
	}
	/**
	 * Zwraca id adresu
	 * @return
	 */
	public int getIdAdresy() {
		return idAdresy;
	}
	/**
	 * Ustawia id adresu
	 * @param idAdresy
	 */
	public void setIdAdresy(int idAdresy) {
		this.idAdresy = idAdresy;
	}
	/**
	 * Zwraca id biura
	 * @return
	 */
	public int getIdBiura() {
		return idBiura;
	}
	/**
	 * Ustawia id biura
	 * @param idBiura
	 */
	public void setIdBiura(int idBiura) {
		this.idBiura = idBiura;
	}
	/**
	 * Zwraca id klienta
	 * @return
	 */
	public int getIdClient() {
		return idClient;
	}


	
}
