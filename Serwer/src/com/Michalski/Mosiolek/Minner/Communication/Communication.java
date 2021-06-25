package com.Michalski.Mosiolek.Minner.Communication;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;
/**
 * 
 * @author wojci
 *<p>
 *Og�lny interfejs u�ywany przez klasy komunikacyjne
 */
public interface Communication extends Serializable{
	/**
	 * Zwraca wiadomo�� b��du
	 * @return
	 */
	public String getMessage();
	/**
	 * Zwraca czy wyst�pi� b��d
	 * @return
	 */
	public boolean isError();
	/**
	 * Zwraca czy wyst�pi� error
	 * @return Zwraca true je�eli wyst�pi� b��d
	 */
	public @NotNull WhatToDo getWhatToDo();
}
