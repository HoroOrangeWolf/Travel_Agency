package com.Michalski.Mosiolek.Minner.Communication;

import org.jetbrains.annotations.NotNull;

/**
 * 
 * @author wojci
 * <p>
 * Klasa przeznaczona do logowania klienta
 */
public class WorkerLogin implements Communication{

	private static final long serialVersionUID = 107332579837710941L;
	private final String nick, password;
	/**
	 * Konstruktor podstawowy
	 * @param nick Login
	 * @param password has³o
	 */
	public WorkerLogin(@NotNull String nick,@NotNull String password)
	{
		this.nick = nick;
		this.password = password;
	}
	/**
	 * Zwraca login pracownika
	 * @return
	 */
	
	public @NotNull String getNick() {
		return nick;
	}
	/**
	 * Zwraca has³o pracownika
	 * @return
	 */

	public @NotNull String getPassword() {
		return password;
	}
	@Override
	public String getMessage() {
		return "";
	}
	@Override
	public boolean isError() {
		return false;
	}
	@Override
	public WhatToDo getWhatToDo() {
		return WhatToDo.ADD;
	}
}
