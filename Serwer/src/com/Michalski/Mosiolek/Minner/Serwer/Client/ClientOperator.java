package com.Michalski.Mosiolek.Minner.Serwer.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.jetbrains.annotations.NotNull;

/**
 * @author wojci
 * <p>
 * Klasa obs�uguj�ca po cz�ci po��czenia
 */
public class ClientOperator extends Thread{
	private static List<ClientOperator> op = new ArrayList<ClientOperator>();
	private Client client;
	
	/**
	 * Konstruktor
	 * @param client Klient serwera
	 */
	public synchronized static void addOperator(@NotNull Client client)
	{
		ClientOperator cop = new ClientOperator();
		cop.client = client;
		op.add(cop);
		cop.start();
	}
	
	@Override
	public void run()
	{
		client.activeWaiting();
		op.remove(this);
	}
	/**
	 * Zamyka wszystkie po�aczenia
	 */
	public synchronized static void closeAll()
	{
		ListIterator<ClientOperator> begin = op.listIterator();
		while(begin.hasNext()) {
			ClientOperator buff = begin.next();
			buff.client.setStop(true);
			buff.client.forceclose();
			begin.remove();
		}
	}
	/**
	 * Sprawdza czy mo�e usun�� pracownika o podanym nicku
	 * @param nick Nick
	 * @return True je�eli mo�na usun�� pracownika
	 */
	public synchronized static boolean isCanDelete(@NotNull String nick)
	{
		for(ClientOperator cl : op)
			if(cl.client.isNickCorrect(nick))
				return false;
		return true;
	}
	/**
	 * Sprawdza czy pracownik jest teraz pod��czony
	 * @param nick Nick pracownika
	 * @return Zwraca true je�eli pracownik jest pod��czony
	 */
	public synchronized static boolean isExist(@NotNull String nick)
	{
		return !isCanDelete(nick);
	}
	/**
	 * Usuwa pracownika z listy po��cze�
	 * @param nick Nick pracownika
	 */
	public synchronized static void removeWorker(@NotNull String nick)
	{
		for(ClientOperator cl : op)
		   if(cl.client.isNickCorrect(nick))
		   {
			   cl.client.setStop(true);
			   cl.client.forceclose();
			   op.remove(cl);
			   return;
		   }			   
	}
	
}
