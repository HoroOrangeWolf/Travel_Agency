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
 * Reprezentuje pracownika w bazie danych
 */
@Entity(name = "WORKER")
@Table(name = "Pracownicy")
public class Worker {
	
	/**
	 * Trzeba nadaæ entity name!!!! wa¿ne
	 */
	@Id
	@GeneratedValue(generator = "sequence-generator-Worker")
	@GenericGenerator(
				name = "sequence-generator-Worker",
				strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		    	parameters = {
		    			@Parameter(name = "sequence_name", value = "Pracownikseq"),
		    	        @Parameter(name = "initial_value", value = "4"),
		    	        @Parameter(name = "increment_size", value = "1")
				}
			)
	@Column(name = "idPracownicy", unique = true)
	private int idWorker;
	
	@Column(name = "idBiura", nullable = false)
	private int idOffice;
	
	@Column(name = "idAdresy", nullable = false)
	private int idAdres;
	
	@Column(name = "Imie", nullable = false)
	private String name;
	
	@Column(name = "Nazwisko", nullable = false)
	private String surname;
	
	@Column(name = "Pesel", nullable = false)
	private String pesel;
	
	@Column(name = "Nick", nullable = false)
	private String nick;
	
	@Column(name = "Haslo", nullable = false)
	private String password;
	/**
	 * Konstruktor 
	 * @param idOffice id biura gdzie pracuje pracownik
	 * @param idAdres id adres pracownika
	 * @param name imie pracownika
	 * @param surname nazwisko pracownika
	 * @param pesel pesel pracownika
	 * @param nick login pracownika
	 * @param password has³o pracownika
	 */
	public Worker(int idOffice, int idAdres,@NotNull String name,@NotNull String surname,@NotNull String pesel,@NotNull String nick,@NotNull String password) {
		this.idOffice = idOffice;
		this.idAdres = idAdres;
		this.name = name;
		this.surname = surname;
		this.pesel = pesel;
		this.nick = nick;
		this.password = password;
	}
	@SuppressWarnings("unused")
	private Worker() {};
	
	/**
	 * Zwraca has³o pracownika do konta
	 * @return
	 */
	public @NotNull String getPassword() {
		return password;
	}
	/**
	 * Ustawia has³o pracownika do konta
	 * @param password
	 */
	public void setPassword(@NotNull String password) {
		this.password = password;
	}
	/**
	 * Zwraca id pracownika
	 * @return
	 */
	public int getIdWorker() {
		return idWorker;
	}
	/**
	 * Zwraca id biura gdzie pracuje pracownik
	 * @return
	 */
	public int getIdOffice() {
		return idOffice;
	}
	/**
	 * Zwraca id adresu gdzie mieszka pracownik
	 * @return
	 */
	public int getIdAdres() {
		return idAdres;
	}
	/**
	 * Zwraca imie pracownika
	 * @return
	 */
	public @NotNull String getName() {
		return name;
	}
	/**
	 * Zwraca nazwisko pracownika
	 * @return
	 */
	public @NotNull String getSurname() {
		return surname;
	}
	/**
	 * Zwraca pesel pracownika
	 * @return
	 */
	public @NotNull String getPesel() {
		return pesel;
	}
	/**
	 * Zwraca login pracownika
	 * @return
	 */
	public @NotNull String getNick() {
		return nick;
		
	}
	/**
	 * Ustawia id pracownika
	 * @param idWorker
	 */
	public void setIdWorker(int idWorker) {
		this.idWorker = idWorker;
	}
	/**
	 * Ustawia id biura gdzie pracuje pracownik
	 * @param idOffice id biura
	 */
	public void setIdOffice(int idOffice) {
		this.idOffice = idOffice;
	}
	/**
	 * Ustawia id adresu gdzie zamieszkuje pracownik
	 * @param idAdres
	 */
	public void setIdAdres(int idAdres) {
		this.idAdres = idAdres;
	}
	/**
	 * Ustawia imie pracownika
	 * @param name
	 */
	public void setName(@NotNull String name) {
		this.name = name;
	}
	/**
	 * Ustawia nazwisko pracownika
	 * @param surname
	 */
	public void setSurname(@NotNull String surname) {
		this.surname = surname;
	}
	/**
	 * Ustawia pesel pracownika
	 * @param pesel pesel pracownika
	 */
	public void setPesel(@NotNull String pesel) {
		this.pesel = pesel;
	}
	/**
	 * Ustawia login pracownika
	 * @param nick login pracownika
	 */
	public void setNick(@NotNull String nick) {
		this.nick = nick;
	}
	
}
