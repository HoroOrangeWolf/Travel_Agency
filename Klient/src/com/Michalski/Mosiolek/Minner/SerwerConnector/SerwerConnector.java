package com.Michalski.Mosiolek.Minner.SerwerConnector;

import java.io.IOException;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.Michalski.Mosiolek.Minner.Communication.Aggrement;
import com.Michalski.Mosiolek.Minner.Communication.Client;
import com.Michalski.Mosiolek.Minner.Communication.Communication;
import com.Michalski.Mosiolek.Minner.Communication.Trip;
import com.Michalski.Mosiolek.Minner.Communication.WhatToDo;
import com.Michalski.Mosiolek.Minner.Communication.WorkerLogin;
import com.Michalski.Mosiolek.Minner.Communication.WorkerLoginReturn;

import static com.Michalski.Mosiolek.Minner.Klient.Window.showErrorMessage;
import static com.Michalski.Mosiolek.Minner.Klient.Window.showInfoMessage;

/**
 * 
 * @author wojci
 * Klasa zawieraj¹ca komunikacji pomiêdzy u¿ytkownikiem, a serwerem 
 */
public class SerwerConnector {
	private static final Logger logger = Logger.getLogger(SerwerConnector.class);
	private Socket sock;
	private ObjectOutputStream stream;
	private ObjectInputStream streamin;
	public SerwerConnector() throws UnknownHostException, IOException
	{
		sock = new Socket("127.0.0.1", 4021);	
		stream = new ObjectOutputStream(sock.getOutputStream());
		streamin = new ObjectInputStream(sock.getInputStream());
	}
	/**
	 * Wysy³a ¿¹danie o zalogowanie siê klienta
	 * @param nick Nick pracownika
	 * @param password Has³o pracownika
	 * @return zwraca true je¿eli uda³o siê zalogowaæ, false je¿eli nie
	 */
	public boolean login(@NotNull String nick,@NotNull String password)
	{
		try {
			
			stream.writeObject(new WorkerLogin(nick,password));
			Object obj = streamin.readObject();
			if(obj instanceof Communication)
			{
				if(((Communication)obj).isError())
				{
					showErrorMessage(((Communication)obj).getMessage());
					return false;
				}
				else
				{	
					showInfoMessage(((Communication)obj).getMessage());
					return true;
				}
			}
			else
				return false;
		} catch (IOException e) {
			logger.error("Error!", e);
			return false;
		} catch (ClassNotFoundException e) {
			logger.error("Error!", e);
			return false;
		} 
	}
	/**
	 * Aktualizuje p³atnoœæ klienta naszego biura
	 * @param ag Przyjmuje klase z danymi wymaganymi do aktualizacji 
	 * @return Zwraca true je¿eli powiod³o siê
	 */
	public boolean updatePayment(@NotNull Aggrement ag)
	{
		ag.setWhatToDo(WhatToDo.UPDATE);
		try {
			stream.writeObject(ag);
			Object obj = streamin.readObject();
			if(!(obj instanceof Communication))
			{
				showErrorMessage("Nieprawid³owy obiekt!");
				return false;
			}
			Communication com = (Communication)obj;
			if(com.isError())
			{
				showErrorMessage(com.getMessage());
				return false;
			}
			showInfoMessage(com.getMessage());
			return true;
		} catch (IOException | ClassNotFoundException e) {
			logger.error("Error!", e);
		}
		
		return false;
	}
	/**
	 * Dodaje wycieczkê do bazy danych przez serwer
	 * @param of Przyjmuje klase z danymi
	 * @return zwraca true je¿eli siê powiod³o
	 */
	public boolean addTrip(@NotNull Trip of)
	{
		
		try {
			of.setWhatToDo(WhatToDo.ADD);
			stream.writeObject(of);
			Object obj = streamin.readObject();
			if(obj instanceof Communication)
			{
				Communication com = (Communication)obj;
				if(com.isError())
				{
					showErrorMessage(com.getMessage());
					return false;
				}
				else 
				{
					showInfoMessage(com.getMessage());
					return true;
				}
				
			}
		} catch (IOException | ClassNotFoundException e) {
			logger.error("Error!", e);
		}
		
		return false;
	}
	/**
	 * Zwraca wycieczki o podanych argumentach
	 * @param tr Klasa z danymi
	 * @return Zwraca tablicê wycieczek, mo¿e byæ null w przypadku b³êdu
	 */
	public @Nullable Trip[] getTrips(@NotNull Trip tr)
	{
		tr.setWhatToDo(WhatToDo.GET_TRIPS);
		List<Trip> triplist = new ArrayList<Trip>();
		try {
			stream.writeObject(tr);
			do {
		
				Object obj = streamin.readObject();
				if(!(obj instanceof Trip))
					return null;
				Trip trip = (Trip)obj;
				if(trip.isError())
					break;
				triplist.add(trip);
				if(trip.getWhatToDo()==WhatToDo.TRIPS_ERROR)
					break;
				
			}while(true);
		} catch (IOException | ClassNotFoundException e) {
			logger.error("Error!", e);
		}
		return triplist.toArray(new Trip[0]);
	}
	/**
	 * Dodaje klienta do bazy danych przez serwer
	 * @param cl Klasa z danymi
	 * @return zwraca true je¿eli siê powiod³o
	 */
	public boolean addClient(@NotNull Client cl)
	{
		try {
			cl.setWhatToDo(WhatToDo.ADD);
			stream.writeObject(cl);
			Object obj = streamin.readObject();
			if(obj instanceof Communication)
			{
				Communication com = (Communication) obj;
				if(com.isError())
				{
					showErrorMessage(com.getMessage());
					return false;
				}
				else {
					showInfoMessage(com.getMessage());
					return true;
				}
			}
			
		} catch (IOException | ClassNotFoundException e) {
			logger.error("Error!", e);
		}
		
		return false;
	}
	/**
	 * Updatuje klienta o podanych argumentach
	 * @param cl Klasa z danymi
	 * @return Zwraca true je¿eli siê powiod³o
	 */
	public boolean updateClient(@NotNull Client cl)
	{
		try {
			cl.setWhatToDo(WhatToDo.UPDATE);
			stream.writeObject(cl);
			Object obj = streamin.readObject();
			Communication com = null;
			if(obj instanceof Communication)
			{
				com = (Communication)obj;
				if(com.isError())
				{
					showErrorMessage(com.getMessage());
					return false;
				}
				showInfoMessage(com.getMessage());
				return true;
			}
		} catch (IOException | ClassNotFoundException e) {
			logger.error("Error!", e);
		}
		return false;
	}
	/**
	 * Usuwa klienta o podanym peselu
	 * @param pesel Pesel klienta
	 * @return zwraca true je¿eli usuniêto  klienta
	 */
	public boolean removeClient(@NotNull String pesel)
	{
		Client cl = new Client(-1,null, null, null, pesel, null, null, null, null, null, WhatToDo.REMOVE);
		try {
			stream.writeObject(cl);
			Object obj = streamin.readObject();
			Communication com = null;
			if(obj instanceof Communication)
			{
				com = (Communication)obj;
				if(com.isError())
				{
					showErrorMessage(com.getMessage());
					return false;
				}
				showInfoMessage(com.getMessage());
				return true;
			}
		} catch (IOException | ClassNotFoundException e) {
			logger.error("Error!", e);
		}
		return false;
	}
	/**
	 * Zwraca klienta o podanym peselu
	 * @param pesel Pesel klienta
	 * @return Zwraca klienta o podanym peselu, zwraca null je¿eli wyst¹pi³ b³¹d
	 */
	public @Nullable Client getClient(@NotNull String pesel)
	{
		try {
			stream.writeObject(new Client(-1,null, null, null, pesel, null, null, null, null, null, WhatToDo.GET));
			Object obj = streamin.readObject();
			if(obj instanceof Client &&  !((Communication)obj).isError())
				return (Client)obj;
			showErrorMessage(((Communication)obj).getMessage());
			return null;
		}catch (Exception e) {
			logger.error("Error!", e);
		}
		return null;
	}
	/**
	 * Wylogowuje pracownika
	 * @return Zwraca true je¿eli pomyslnie
	 */
	public boolean logout()
	{
		try {
			stream.writeObject(new WorkerLogin(null, null));
			Object obj = streamin.readObject();
			if(!(obj instanceof WorkerLoginReturn))
				return false;
			showInfoMessage(((Communication)obj).getMessage());
			return true;
		}catch (Exception e) {
		}
		return false;
	}
	/**
	 * Zwraca umowy klienta
	 * @param ag Klasa z danymi
	 * @return Mo¿e byæ false je¿eli wyst¹pi³ b³¹d
	 */
	public @Nullable Aggrement[] getAggrements(@NotNull Aggrement ag)
	{
		ArrayList<Aggrement> list = new ArrayList<Aggrement>();
		
		try {
			ag.setWhatToDo(WhatToDo.GET_TRIPS);
			stream.writeObject(ag);
			do {
				Object obj = streamin.readObject();
				if(!(obj instanceof Aggrement))
					return null;
				Communication com = (Communication) obj;
				if(com.isError() && com.getWhatToDo() == WhatToDo.TRIPS_ERROR)
				{
					list.add((Aggrement)obj);
					return list.toArray(new Aggrement[0]);
				}
				else if(com.isError())
					return list.toArray(new Aggrement[0]);
				list.add((Aggrement)obj);
				
			}while(true);
			
			
		}catch (Exception e) {
			logger.error("Error!", e);
		}
		return null;
	}
	/**
	 * Dodaje umowê do bazy danych przez serwer
	 * @param ag Klasa z danymi
	 * @return Zwraca true je¿eli dodano prawid³owo
	 */
	public boolean addAggrement(@NotNull Aggrement ag)
	{

		try {
			ag.setWhatToDo(WhatToDo.ADD);
			stream.writeObject(ag);
			
			Object obj = streamin.readObject();
			if(!(obj instanceof Communication))
			{
				showErrorMessage("Nieznany b³¹d!");
				return false;
			}
			Communication com = (Communication)obj;
			if(com.isError())
				showErrorMessage(com.getMessage());
			else
				showInfoMessage("Dodano umowê klientowi!!!");
			
			
			
		}catch (Exception e) {
			logger.error("Error!", e);
			return false;
		}
		return true;
		
	}
	/**
	 * Zamyka po³¹czenie
	 * @return zwraca true je¿eli zamkniêto
	 */
	public boolean close()
	{
		try {
			this.sock.close();
		} catch (IOException e) {
			logger.error("Error!", e);
			return false;
		}
		return true;
	}
	/**
	 * Initializuje po³¹czenie
	 * @return zwraca true je¿eli uda³o siê po³¹czyæ 
	 */
	public boolean open()
	{
		try {
			this.sock = new Socket("127.0.0.1", 4021);
		} catch (IOException e) {
			logger.error("Error!", e); 
			return false;
		};
		return true;
	}
	
}
