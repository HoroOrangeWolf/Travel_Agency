package com.Michalski.Mosiolek.Minner.Serwer;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

import com.Michalski.Mosiolek.Minner.Serwer.Client.Client;
import com.Michalski.Mosiolek.Minner.Serwer.Client.ClientOperator;
/**
 * 
 * @author wojci
 * <p>
 * Klasa przyjmuj¹ca nowe po³¹czenia, i tworz¹ca nowe w¹tki
 */
public class Serwer {
	private ServerSocket sock;
	private AtomicBoolean isworking = new AtomicBoolean(true);
	private AtomicBoolean issleeping = new AtomicBoolean(false);
	private static final Logger logger = Logger.getLogger(Serwer.class);

	/**
	 * Usypia akceptowanie nowych po³¹czeñ
	 * @param issleeping true usypia 
	 */
	public void setSleep(boolean issleeping) {
		this.issleeping.set(issleeping);
	}
	/**
	 * Konstruktor
	 * @param port port
	 * @throws IOException
	 */
	public Serwer(int port) throws IOException
	{
		sock = new ServerSocket(port);
	}
	/**
	 * Przyjmuje nowe po³¹czenia
	 * @throws IOException
	 */
	public void activeWaiting() throws IOException 
	{
		System.out.println("Aktywowano serwer, pod adresem " + sock.getInetAddress().getHostName());
		sock.setSoTimeout(50);
		
		while(this.isworking.get())
		{	

			try
			{
				Socket socket = sock.accept();
				
				System.out.println("Odebrano nowe polaczenie z adresu: " + socket.getLocalAddress().getHostName());
				ClientOperator.addOperator(new Client(socket));
			}
			catch(SocketTimeoutException e)
			{
				while(this.issleeping.get());
					
			}
		}
	}
	/**
	 * Wy³¹cza aktywne oczekiwanie
	 */
	public void stopWaiting()
	{
		this.isworking.set(false);
	}
	/**
	 * Oczekuje do zamkniêcia 
	 */
	public void waitToStop()
	{	
		this.isworking.set(false);
	}
	/**
	 * Zamyka wszystkie po³¹czenia
	 */
	public void Close()
	{
		try {
			ClientOperator.closeAll();
			sock.close();
			
		} catch (IOException e) {
			logger.error("Error!", e);
		}
	}
}
