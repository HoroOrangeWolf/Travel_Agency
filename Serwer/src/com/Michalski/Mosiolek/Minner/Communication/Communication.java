package com.Michalski.Mosiolek.Minner.Communication;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;
/**
 * 
 * @author wojci
 *<p>
 *Ogólny interfejs u¿ywany przez klasy komunikacyjne
 */
public interface Communication extends Serializable{
	/**
	 * Zwraca wiadomoœæ b³êdu
	 * @return
	 */
	public String getMessage();
	/**
	 * Zwraca czy wyst¹pi³ b³¹d
	 * @return
	 */
	public boolean isError();
	/**
	 * Zwraca czy wyst¹pi³ error
	 * @return Zwraca true je¿eli wyst¹pi³ b³¹d
	 */
	public @NotNull WhatToDo getWhatToDo();
}
