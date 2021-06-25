package com.Michalski.Mosiolek.Minner.Communication;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 
 * @author wojci
 * <p>
 * Klasa u¿ywana jako klasa zwrotna do niemal wszystkich rodzaju operacji
 */

public class WorkerLoginReturn implements Communication {

	
	private static final long serialVersionUID = 2638900549119802556L;
	private final String message;
	private final boolean iserror;
	private WhatToDo what = WhatToDo.ADD;
	
	/**
	 * Konstruktor podstawowy
	 * @param message wiadomoœæ
	 * @param iserror czy wyst¹pi³ b³¹d
	 */
	public WorkerLoginReturn(@Nullable String message,boolean iserror)
	{
		this.message = message;
		this.iserror = iserror;
	}
	/**
	 * Konstruktor rozszerzony
	 * @param message Wiadomoœæ 
	 * @param iserror czy wyst¹pi³ b³¹d
	 * @param what
	 */
	public WorkerLoginReturn(@Nullable String message,boolean iserror,@NotNull WhatToDo what )
	{
		this.message = message;
		this.iserror = iserror;
		this.what = what;
	}
	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public boolean isError() {
		return this.iserror;
	}
	@Override
	public WhatToDo getWhatToDo() {
		return what;
	}
	
}
