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
 * Reprezentujê p³atnoœæ w bazie danych
 */
@Entity(name = "PLATNOSCI")
@Table(name = "Platnosci")
public class Payment {

	@Id
	@Column(name = "idPlatnosci",unique = true)
	@GeneratedValue(generator = "sequence-generator-Platnosci")
	@GenericGenerator(
				name = "sequence-generator-Platnosci",
				strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		    	parameters = {
		    			@Parameter(name = "sequence_name", value = "Platnosciseq"),
		    	        @Parameter(name = "initial_value", value = "4"),
		    	        @Parameter(name = "increment_size", value = "1")
				}
			)
	private int idPayment;
	
	@Column(name = "idUmowy",nullable = false)
	private int idaggrement;
	
	@Column(name = "TypPlatnosci", nullable = false)
	private String type;
	
	@Column(name = "StanPlatnosci", nullable = false)
	private String state;
	
	@Column(name = "CalkowityKoszt", nullable = false)
	private float total_cost;
	/**
	 * Konstruktor p³atnoœci
	 * @param idaggrement id umowy
	 * @param type typ p³atnoœci
	 * @param state stan p³atnoœci
	 * @param total_cost ca³kowity kosz wycieczki
	 */
	public Payment(int idaggrement,@NotNull String type,@NotNull String state, float total_cost) {
		this.idaggrement = idaggrement;
		this.type = type;
		this.state = state;
		this.total_cost = total_cost;
	}
	
	@SuppressWarnings("unused")
	private Payment() {};

	/**
	 * Zwraca id p³atnoœci
	 * @return
	 */
	public int getIdPayment() {
		return idPayment;
	}
	/**
	 * Zwraca id umowy
	 * @return
	 */
	public int getIdaggrement() {
		return idaggrement;
	}
	/**
	 * Zwraca typ p³atnoœci
	 * @return
	 */
	public @NotNull String getType() {
		return type;
	}
	/**
	 * Zwraca stan p³atnoœci
	 * @return
	 */
	public @NotNull String getState() {
		return state;
	}
	/**
	 * Zwraca ca³kowity koszt wycieczki
	 * @return
	 */
	public float getTotal_cost() {
		return total_cost;
	}
	/**
	 * Ustawia id p³atnoœci
	 * @param idPayment
	 */
	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}
	/**
	 * Ustawia id umowy
	 * @param idaggrement
	 */
	public void setIdaggrement(int idaggrement) {
		this.idaggrement = idaggrement;
	}
	/**
	 * Ustawia stan p³tnoœci
	 * @param state stan p³atnoœci
	 */
	public void setState(@NotNull String state) {
		this.state = state;
	}
	/**
	 * Ustawia kosz ca³kowity wycieczki
	 * @param total_cost kosz ca³kowity wycieczki
	 */
	public void setTotal_cost(float total_cost) {
		this.total_cost = total_cost;
	}
}
