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
 * Reprezentujê Biuro w bazie danych
 */

@Entity(name = "BIURA")
@Table(name = "Biura")
public class Office {

	@Id
	@GeneratedValue(generator = "sequence-generator-Office")
	@GenericGenerator(
				name = "sequence-generator-Office",
				strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		    	parameters = {
		    			@Parameter(name = "sequence_name", value = "BiuraSeq"),
		    	        @Parameter(name = "initial_value", value = "4"),
		    	        @Parameter(name = "increment_size", value = "1")
				}
			)
	@Column(name = "idBiura")
	private int idOffice;
	
	@Column(name = "idAdresy", nullable = false)
	private int idAddres;
	
	@Column(name = "Nazwa", nullable = false)
	private String name;
	
	@Column(name = "NIP", nullable = false)
	private int nip;
	/**
	 * Konstruktor 
	 * @param idAddres id adresu
	 * @param name Nazwa biura
	 * @param nip Nip biura
	 */
	public Office(int idAddres,@NotNull String name, int nip) {
		this.idAddres = idAddres;
		this.name = name;
		this.nip = nip;
	}
	@SuppressWarnings("unused")
	private Office() {};
	/**
	 * Zwraca id biura
	 * @return
	 */
	public int getIdOffice() {
		return idOffice;
	}
	/**
	 * Zwraca id adresu
	 * @return
	 */
	public int getIdAddres() {
		return idAddres;
	}
	/**
	 * Zwraca nazwê biura
	 * @return
	 */
	public @NotNull String getName() {
		return name;
	}
	/**
	 * Zwraca nip biura
	 * @return
	 */
	public int getNIP() {
		return this.nip;
	}
	/**
	 * Ustawia id biura
	 * @param idOffice id biura
	 */
	public void setIdOffice(int idOffice) {
		this.idOffice = idOffice;
	}
	/**
	 * Ustawia id adresu
	 * @param idAddres id adresu
	 */
	public void setIdAddres(int idAddres) {
		this.idAddres = idAddres;
	}
	/**
	 * Ustawia nazwê biura
	 * @param name nazwa biura
	 */
	public void setName(@NotNull String name) {
		this.name = name;
	}
	/**
	 * Ustawia nip biura
	 * @param nIP nip biura
	 */
	public void setNIP(int nIP) {
		this.nip = nIP;
	}
}
