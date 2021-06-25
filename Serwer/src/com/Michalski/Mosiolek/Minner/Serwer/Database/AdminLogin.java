package com.Michalski.Mosiolek.Minner.Serwer.Database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jetbrains.annotations.NotNull;

/**
 * 
 * @author wojci
 * Klasa do hibernate wymagana do logowania admina
 */
@Entity
@Table(name = "panel")
public class AdminLogin {
	@Id
	@Column(name = "nick")
	private String nick;
	@Column(name = "password")
	private String password;
	/**
	 * Podstawowy konstruktor
	 * @param nick login
	 * @param password kas這
	 */
	public AdminLogin(@NotNull String nick,@NotNull String password) {
		super();
		this.nick = nick;
		this.password = password;
	}
	/**
	 * Zablokowany konstruktor
	 */
	@SuppressWarnings("unused")
	private AdminLogin() {}
	
	
	/**
	 * Zwraca nick admina
	 * @return
	 */
	public @NotNull String getNick() {
		return nick;
	}
	/**
	 * Zwraca has這 admina
	 * @return
	 */
	public @NotNull String getPassword() {
		return password;
	}
	/**
	 * Ustawia has這 admina
	 * @param password has這 admina
	 */
	public void setPassword(@NotNull String password) {
		this.password = password;
	}
	
}
