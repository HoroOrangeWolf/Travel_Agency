package com.Michalski.Mosiolek.Minner.Serwer.Client;

import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.Michalski.Mosiolek.Minner.Communication.Aggrement;
import com.Michalski.Mosiolek.Minner.Communication.Communication;
import com.Michalski.Mosiolek.Minner.Communication.Trip;
import com.Michalski.Mosiolek.Minner.Communication.WhatToDo;
import com.Michalski.Mosiolek.Minner.Communication.WorkerLogin;
import com.Michalski.Mosiolek.Minner.Communication.WorkerLoginReturn;
import com.Michalski.Mosiolek.Minner.Serwer.Database.Adresy;
import com.Michalski.Mosiolek.Minner.Serwer.Database.DataBaseManager;
import com.Michalski.Mosiolek.Minner.Serwer.Database.Payment;
import com.Michalski.Mosiolek.Minner.Serwer.Database.Worker;
/**
 * 
 * @author wojci
 * <p>
 * Obrazuje pojedyncze połaczenie z serwerem
 */
public class Client{
	private Socket sock;
	private boolean isworking;
	private boolean stoper;
	private Worker wo = null;
	private ObjectOutputStream stream;
	private ObjectInputStream streamin;
	private static final Logger logger = Logger.getLogger(Client.class);

	/**
	 * Konstruktor
	 * @param sock to co zwraca accept
	 */
	public Client(@NotNull Socket sock)
	{
		this.sock = sock;
		try {
			stream = new ObjectOutputStream(sock.getOutputStream());
			streamin = new ObjectInputStream(sock.getInputStream());
		} catch (IOException e) {
			logger.error("Error!", e);
		}
	}
	/**
	 * Obsługuje połączenie z klientem
	 */
	public void activeWaiting()
	{
		isworking = true;
		stoper = true;
		main_loop:while(stoper)
		{
			try {
		    	
				try {
					Object obj = streamin.readObject();
					if(!(obj instanceof Communication))
						continue;
					Communication com = (Communication)obj;
					if(obj instanceof WorkerLogin)
					{
						if(wo==null)
						{
							String buff = ((WorkerLogin)obj).getNick();
							if(buff.length()<4)
								stream.writeObject(new WorkerLoginReturn("Nick musi mieć conajmniej 3 znaki!!!", true));
							if(ClientOperator.isExist(buff))
								stream.writeObject(new WorkerLoginReturn("Użytkownik o takim nicku jest już zalogowany do systemu!", true));
							else
								workerLogin((WorkerLogin)obj);
						}
						else
						{
							stream.writeObject(new WorkerLoginReturn("Wylogowano pomyślnie", false));
							wo = null;
						}
						continue;
					}
					if(wo==null)
						continue;
					
					if(obj instanceof com.Michalski.Mosiolek.Minner.Communication.Client)
					{
						com.Michalski.Mosiolek.Minner.Communication.Client cl = (com.Michalski.Mosiolek.Minner.Communication.Client)obj;
						if(com.getWhatToDo()==null)
						{
							stream.writeObject(new WorkerLoginReturn("WhatToDo NULL", true));
							return;
						}
						switch(com.getWhatToDo())
						{
						case ADD:
							ClientOperatorAdd(cl);
							break;
						case GET:
							ClientOperatorGet(cl);
							break;
						case UPDATE:
							ClientOperatorUpdate(cl);
							break;
						case REMOVE:
							ClientOperatorRemove(cl);
							break;
						default:
							continue main_loop;
						
						}
					}
					else if(obj instanceof com.Michalski.Mosiolek.Minner.Communication.Trip && !(obj  instanceof com.Michalski.Mosiolek.Minner.Communication.Aggrement))
					{
						switch (com.getWhatToDo()) {
						case ADD:
							TripOperatorAdd((Trip)obj);
							break;
						case GET_TRIPS:
							TripOperatorGetTrips((Trip)obj);
						default:
							break;
						}
					}
					else if(obj instanceof com.Michalski.Mosiolek.Minner.Communication.Aggrement)
					{
						switch (com.getWhatToDo()) {
						case ADD:
							AggrementOperatoAdd((Aggrement)obj);
							break;
						case GET_TRIPS:
							AggrementOperatorGet((Aggrement)obj);
							break;
						case UPDATE:
							AggrementOperatorUpdate((Aggrement)obj);
						default:
							break;
						}
						
					}

				} catch (ClassNotFoundException e) {
					logger.error("Error class not found!", e);
				}
				
		
			} catch (IOException e) {
				logger.error("Error: " + wo==null?"null":wo.getName(), e);
				stoper = false;
			}
		}
	    
	    isworking = false;    
	}
	/**
	 * Sprawdza czy nick się zgadza
	 * @param nick Nick
	 * @return true jeżeli tak
	 */
	boolean isNickCorrect(@NotNull String nick)
	{
		return  wo!=null?wo.getNick().equals(nick):false;
	}
	/**
	 * Aktualizuje umowe
	 * @param obj Klasa z danymi
	 * @throws IOException
	 */
	private void AggrementOperatorUpdate(@NotNull Aggrement obj) throws IOException
	{
		
		if(!obj.getPayment_type().equalsIgnoreCase("Przelew"))
		{
			stream.writeObject(new WorkerLoginReturn("Można zmienic tylko stan płatności w przelewu", true));
			return;
		}
		com.Michalski.Mosiolek.Minner.Serwer.Database.Payment pay = DataBaseManager.PaymentManager.getPaymentByAggrement(obj.getIdaggrement());
		
		if(pay == null || !(obj.getPayment_status().equals("Zapłacono") || obj.getPayment_status().equals("Niezapłacono")))
		{
			stream.writeObject(new WorkerLoginReturn("Wystąpił błąd!", true));
			return;
		}
		
		pay.setState(obj.getPayment_status());
		if(!DataBaseManager.PaymentManager.updatePayment(pay))
		{
			stream.writeObject(new WorkerLoginReturn("Wystąpił błąd!", true));
			return;
		}
		
		stream.writeObject(new WorkerLoginReturn("Zmieniono stan płatności!", false));
		
	}
	/**
	 * Zatrzymuje czekanie
	 * @param value
	 */
	public void setStop(boolean value)
	{
		stoper = value;
	}
	/**
	 * Czeka na zatrzymanie
	 */
	public void waitToStop()
	{
		while(isworking);
	}
	/**
	 * Wymusza zamknięcie, lubie shreka
	 */
	
	public void forceclose()
	{
		try {
			sock.getInputStream().close();
			sock.getOutputStream().close();
			sock.close();
		} catch (IOException e) {
		}
	}
	/**
	 * Wysyła do klienta klasę z danymi
	 * @param ag Klasa z danymi 
	 * @throws IOException
	 */
	public void AggrementOperatorGet(@NotNull com.Michalski.Mosiolek.Minner.Communication.Aggrement ag) throws IOException
	{
		com.Michalski.Mosiolek.Minner.Serwer.Database.Aggrement[] aggrements = DataBaseManager.AgrrementManager.getAggrements(wo.getIdOffice(), ag);
		
		if(aggrements == null)
		{
			Aggrement buff1 = new Aggrement(true, "Error!!");
			buff1.setWhatToDo(WhatToDo.TRIPS_ERROR);
			stream.writeObject(buff1);
			return;
		}
		for(com.Michalski.Mosiolek.Minner.Serwer.Database.Aggrement buff : aggrements)
		{
			Payment pay = DataBaseManager.PaymentManager.getPaymentByAggrement(buff.getIdAggrement());
			com.Michalski.Mosiolek.Minner.Serwer.Database.Trip tr = DataBaseManager.TripManager.getTrip(buff.getIdTrip());
			if(pay == null || tr == null)
			{
				Aggrement buff1 = new Aggrement(true, "Error!!");
				buff1.setWhatToDo(WhatToDo.TRIPS_ERROR);
				stream.writeObject(buff1);
				return;
			}
			
			Aggrement buffer = new Aggrement(buff.getIdAggrement(),buff.getIdTrip(), 0, 0, 0, buff.getGuestcount(), 0, null, null, buff.getAggrement_date(), tr.getName(), null, pay.getTotal_cost(), pay.getType(), pay.getState());
			stream.writeObject(buffer);
		}
		
		stream.writeObject(new Aggrement(true, null));
	}
	/**
	 * Dodaje umowę do bazy danych
	 * @param ag Klasa komunikacyjna
	 * @throws IOException
	 */
	public void AggrementOperatoAdd(@NotNull com.Michalski.Mosiolek.Minner.Communication.Aggrement ag) throws IOException
	{
		if(ag.getPrice()<= 0)
		{
			stream.writeObject(new WorkerLoginReturn("Koszt nie może być mniejszy lub równy zeru!", true));
			return;
		}
		if(ag.getGuestcount()<=0)
		{
			stream.writeObject(new WorkerLoginReturn("Liczba gości nie może być mniejsza bądz równa zeru!", true));
			return;
		}
		com.Michalski.Mosiolek.Minner.Serwer.Database.Aggrement aggrement = new com.Michalski.Mosiolek.Minner.Serwer.Database.Aggrement(ag.getIdClient(), ag.getIdTrip(), ag.getGuestcount(), ag.getAggrementdate());
		com.Michalski.Mosiolek.Minner.Serwer.Database.Trip tr = DataBaseManager.TripManager.getTrip(aggrement.getIdTrip());

		if(tr == null)
		{
			stream.writeObject(new WorkerLoginReturn("Wystąpił błąd!!!", true));
			return;
		}
		if(tr.getMaxguests()<aggrement.getGuestcount())
		{
			stream.writeObject(new WorkerLoginReturn("Brak miejsc!!!", true));
			return;
		}
		int max = tr.getMaxguests();
		tr.setMaxguests(max - aggrement.getGuestcount());
		if(!DataBaseManager.TripManager.updateTrip(tr))
		{
			stream.writeObject(new WorkerLoginReturn("Wystąpił błąd1", true));
			return;
		}
		if(!DataBaseManager.AgrrementManager.addAggrement(aggrement))
		{
			tr.setMaxguests(max);
			DataBaseManager.TripManager.updateTrip(tr);
			stream.writeObject(new WorkerLoginReturn("Wystąpił błąd2", true));
			return;
		}
		if(!DataBaseManager.PaymentManager.addPayment(new Payment(aggrement.getIdAggrement(), ag.getPayment_type(), ag.getPayment_status(), ag.getPrice())))
		{
			tr.setMaxguests(max);
			DataBaseManager.TripManager.updateTrip(tr);
			DataBaseManager.AgrrementManager.removeAggrement(aggrement.getIdAggrement());
			stream.writeObject(new WorkerLoginReturn("Wystąpił błąd3", true));
			return;
		}
		
		stream.writeObject(new WorkerLoginReturn("Dodano umowę", false));	
	}

	
	/**
	 * Obsługuje logowanie
	 * @param l Klasa komunikacyjna
	 * @throws IOException
	 */
	public void workerLogin(@NotNull WorkerLogin l) throws IOException
	{
		wo = DataBaseManager.WorkerManager.getWorker(l.getNick());
		if(wo == null)
		{
			stream.writeObject(new WorkerLoginReturn("Nick nie istnieje w bazie danych!",true));
			return;
		}
		
		if(l.getPassword().equals(wo.getPassword()))
			stream.writeObject(new WorkerLoginReturn("Zalogowano pomyślnie!", false));
		else
			stream.writeObject(new WorkerLoginReturn("Nie poprawne hasło", true));
	}
	/**
	 * Obsługuje dodawanie klienta
	 * @param cl klasa komunikacyjna
	 * @throws IOException
	 */
	public void ClientOperatorAdd(@NotNull com.Michalski.Mosiolek.Minner.Communication.Client cl) throws IOException
	{
		String buff = checkClientBeforeAdd(cl);
		if(buff!=null)
		{
			stream.writeObject(new WorkerLoginReturn(buff, true));
			return;
		}

		if(DataBaseManager.ClientManager.isClientExist(wo.getIdOffice(), cl.getPesel()))
		{
			stream.writeObject(new WorkerLoginReturn("Klient o podanym peselu istnieje w bazie danych ", true));
			return;
		}
		Adresy ad = new Adresy(cl.getCountry(), cl.getCity(), cl.getHomenumber(), cl.getPostcode(), cl.getStreet());
		
		com.Michalski.Mosiolek.Minner.Serwer.Database.Client client = new com.Michalski.Mosiolek.Minner.Serwer.Database.Client(cl.getName(), cl.getSurname(), cl.getPesel(), cl.getPhoneNumber());
		
		client.setIdBiura(wo.getIdOffice());
		
		if(DataBaseManager.AdresyManager.addAdresy(ad))
		{
			client.setIdAdresy(ad.getIdAdresy());
			if(DataBaseManager.ClientManager.addClient(client))
			{
				stream.writeObject(new WorkerLoginReturn("Klient zostal dodany do bazy danych", false));
				return;
			}
		}
		stream.writeObject(new WorkerLoginReturn("Klient o podanym peselu istnieje w bazie danych ", true));	
	}
	/**
	 * Obsługuję wysyłanie klienta do "Klienta serwera"
	 * @param cl Klasa komunikacyjna
	 * @throws IOException
	 */
	public void ClientOperatorGet(@NotNull com.Michalski.Mosiolek.Minner.Communication.Client cl) throws IOException
	{
		com.Michalski.Mosiolek.Minner.Serwer.Database.Client client = DataBaseManager.ClientManager.getClient(wo.getIdOffice(), cl.getPesel());
		if(client == null)
		{
			stream.writeObject(new WorkerLoginReturn("Klient o podanym peselu nie istnieje!", true));
			return;
		}
		Adresy ad = DataBaseManager.AdresyManager.getAdresy(client.getIdAdresy());
		if(ad == null)
		{
			stream.writeObject(new WorkerLoginReturn("Błąd odczytu danych!", true));
			return;
		}
		stream.writeObject(new com.Michalski.Mosiolek.Minner.Communication.Client(client.getIdClient(),client.getName(), client.getSurname(), client.getPhonenumber(), client.getPesel(),
				ad.getCountry(), ad.getCity(), ad.getStreet(), ad.getHomenumber(), ad.getPostdode()));
	}
	/**
	 * Obsługuję aktualizację klienta
	 * @param cl Klasa komunikacyjna
	 * @throws IOException
	 */
	public void ClientOperatorUpdate(@NotNull com.Michalski.Mosiolek.Minner.Communication.Client cl) throws IOException
	{
		String buff = checkClientBeforeAdd(cl);
		if(buff!=null)
		{
			stream.writeObject(new WorkerLoginReturn(buff, true));
			return;
		}
		
		com.Michalski.Mosiolek.Minner.Serwer.Database.Client client = DataBaseManager.ClientManager.getClient(wo.getIdOffice(), cl.getPesel());
		if(client == null)
		{
			stream.writeObject(new WorkerLoginReturn("Client o podanym peselu nie istnieje w bazie danych", true));
			return;
		}
		Adresy ad = DataBaseManager.AdresyManager.getAdresy(client.getIdAdresy());
		if(ad == null)
		{
			stream.writeObject(new WorkerLoginReturn("Wystąpił błąd!", false));
			return;
		}
		ad.setCity(cl.getCity());
		ad.setCountry(cl.getCountry());
		ad.setHomenumber(cl.getHomenumber());
		ad.setPostdode(cl.getPostcode());
		ad.setStreet(cl.getStreet());
		
		client.setName(cl.getName());
		client.setSurname(cl.getSurname());
		client.setPhonenumber(cl.getPhoneNumber());
		if(DataBaseManager.AdresyManager.updateAdresy(ad) && DataBaseManager.ClientManager.updateClient(client))
		{
			stream.writeObject(new WorkerLoginReturn("Zmodyfikowano pomyślnie!", false));
			return;
		}
		stream.writeObject(new WorkerLoginReturn("Nie udało się zmodyfikować klienta", true));
	}
	/**
	 * Sprawdza klienta przed dodaniem 
	 * @param cl Klasa komunikacyjna
	 * @return zwraca null jeżeli wszystko jest prawidłowe 
	 */
	private @Nullable String checkClientBeforeAdd(@NotNull com.Michalski.Mosiolek.Minner.Communication.Client cl)
	{
		if(cl.getName() == null)
			return "Pole imie nie może być puste!!!";
		if(!(cl.getName().length()>1 && cl.getName().length() < 31))
			return "Długość imienia od 2 do 30";
		
		if(cl.getCountry() == null)
			return "Pole kraj nie może być puste";
		if(!(cl.getCountry().length()>1 && cl.getCountry().length()<31))
			return "Pole kraj ma mieć długość od 2 do 30 znaków";
		
		if(cl.getCity() == null)
			return "Pole miasto nie może być puste";
		if(!(cl.getCountry().length()>1 && cl.getCountry().length()<31))
			return "Pole miasto ma mieć długość od 2 do 30 znaków";
		
		
		if(cl.getHomenumber() == null)
			return "Pole numer domu nie może być puste";
		if(!(cl.getHomenumber().length()>0 && cl.getHomenumber().length()<20))
			return "Pole numer domu powinno mieć długość od 1 do 19 znaków";
		
		if(cl.getPesel() == null)
			return "Pole pesel nie może być puste";
		if(!(cl.getPesel().length()>5 && cl.getPesel().length()<20))
			return "Pole pesel powinno mieć długość od 5 do 19 znaków";
		 
		if(cl.getPhoneNumber() == null)
			return "Pole numer telefonu nie może być puste";
		if(!(cl.getPhoneNumber().length()>5 && cl.getPhoneNumber().length()<20))
			return "Pole numer telefonu powinno mieć długość od 5 do 19 znaków";
		 
		if(cl.getPostcode() == null)
			return "Pole kod pocztowy nie może być puste";
		if(!(cl.getPostcode().length()>3 && cl.getPostcode().length()<7))
			return "Pole kod pocztowy powinno mieć długość od 4 do 6 znaków";
		
		if(cl.getStreet() == null)
			return "Pole ulica nie może być puste";
		if(!(cl.getStreet().length()>1 && cl.getStreet().length()<21))
			return "Pole ulica powinno mieć długość od 2 do 20 znaków";
		
		if(cl.getSurname() == null)
			return "Pole nazwisko nie może być puste";
		if(!(cl.getSurname().length()>3 && cl.getSurname().length()<7))
			return "Pole kod nazwisko powinno mieć długość od 4 do 6 znaków";
		return null;
	}
	/**
	 * Sprawdza wycieczkę przed dodaniem
	 * @param tr Klasa komunikacyjna
	 * @return null, jeżeli wszystko ok
	 */
	private @Nullable String checkTripBeforeAdd(@NotNull Trip tr)
	{
		if(tr.getDate_back() == null || tr.getDate_out() == null || tr.getDate_out().getTime() < new Date().getTime() || tr.getDate_back().getTime() < tr.getDate_out().getTime())
			return "Nie poprawna data";
		if(tr.getMaxguest()<=0)
			return "Nie poprawna ilość maksymalna gości";
		
		if(tr.getName()==null)
			return "Pole nazwa nie może być puste";
		if(!(tr.getName().length()>3 && tr.getName().length()<21))
			return "Pole kod pocztowy powinno mieć długość od 4 do 20 znaków";
		
		if(tr.getPhonenumber()==null)
			return "Pole numertelefonu nie może być puste";
		if(!(tr.getPhonenumber().length()>3 && tr.getPhonenumber().length()<21))
			return "Numer telefonu powinien mieć mieć długość od 4 do 20 znaków";
		if(tr.getPrice()<=0)
			return "Pole cena nie może mieć wartości mniejszej bądz równej zeru";
		if(tr.getStandard()<=0 || tr.getStandard()>=6)
			return "Nie prawidłowy standard";
		return null;
	}
	/**
	 * Obsługuję dodawanie wycieczki
	 * @param tr Klasa komunikacyjna
	 * @throws IOException
	 */
	public void TripOperatorAdd(@NotNull Trip tr) throws IOException
	{
		String buff = checkTripBeforeAdd(tr);
		if(buff!=null)
			stream.writeObject(new WorkerLoginReturn(buff, true));
		if(DataBaseManager.TripManager.addTrip(new com.Michalski.Mosiolek.Minner.Serwer.Database.Trip(wo.getIdOffice(), tr.getPrice(),
				tr.getDate_out(), tr.getDate_back(), tr.getName(), tr.getPhonenumber(), tr.getMaxguest(), tr.getStandard())))
			stream.writeObject(new WorkerLoginReturn("Dodano  wycieczke!", false));
		else
			stream.writeObject(new WorkerLoginReturn("Nie udało się dodać wycieczki!", true));		
		
	}
	/**
	 * Obsługuję zwracanie listy wycieczek
	 * @param tr Klasa komunikacyjna
	 * @throws IOException
	 */
	public void TripOperatorGetTrips(@NotNull Trip tr) throws IOException
	{
		com.Michalski.Mosiolek.Minner.Serwer.Database.Trip[] trip = DataBaseManager.TripManager.getAllTrips(wo.getIdOffice(), tr);
		if(trip == null)
		{
			Trip trop = new Trip(true, "Error!");
			trop.setWhatToDo(WhatToDo.TRIPS_ERROR);
			stream.writeObject(trop);
			return;
		}
		for(com.Michalski.Mosiolek.Minner.Serwer.Database.Trip buff : trip)
			stream.writeObject(new Trip(buff.getIdTrip(), buff.getMaxguests(), buff.getStandard(), buff.getDateout(), buff.getDatein(), buff.getName(), buff.getPhonenumber(), buff.getPrice()));
		
		stream.writeObject(new Trip(true, null));
	}
	
	/**
	 * Obsługuję usuwanie klienta
	 * @param cl Klasa komunikacyjna
	 * @throws IOException
	 */
	public void ClientOperatorRemove(@NotNull com.Michalski.Mosiolek.Minner.Communication.Client cl) throws IOException
	{
		if(DataBaseManager.ClientManager.removeClient(wo.getIdOffice(), cl.getPesel()))
			stream.writeObject(new WorkerLoginReturn("Usunięto klienta!", false));
		else
			stream.writeObject(new WorkerLoginReturn("Nie udało się usunąć klienta!", true));
	}
	
	
	
}
