package com.Michalski.Mosiolek.Minner.Serwer.Database;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/**
 * 
 * @author wojci
 * <p>
 * Klasa zawieraj�ca wszystkie metody operuj�ce na bazie danych
 */
public class DataBaseManager {
	private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("Serwer");
	private static final Logger logger = Logger.getLogger(DataBaseManager.class);
	
	/**
	 * 
	 * @author wojci
	 * <p>
	 * @see DataBaseManager
	 * <p>
	 * Klasa skupiaj�ca metody operuj�ce na pracowniku w bazie danych
	 */
	public final  static class WorkerManager{

		private WorkerManager() {}
		
		/**
		 * Zwraca pracownika o podanym id
		 * @param idWorker id pracownika
		 * @return zwraca null je�eli nie znaleziono pracownika
		 */
		public static @Nullable Worker getWorker(int idWorker)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			Worker wo = null;
			try {
				wo = em.find(Worker.class, idWorker);
				em.detach(wo);
			}
			catch (Exception e) {
				logger.error("Error!", e);
			}
			finally {
				em.close();
			}
			return wo;
		}
		
		/**
		 * Zwraca pracownika o powi�zanym id i peselu
		 * @param idOffice id biura
		 * @param pesel pesel pracownika
		 * @return zwr�ci null je�eli nie znaleziono
		 */
		public static @Nullable Worker getWorker(int idOffice,@NotNull String pesel)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			TypedQuery<Worker> query = em.createQuery("SELECT c FROM WORKER c WHERE c.pesel LIKE :Pesel AND c.idBiura = :idBiura ", Worker.class);
			query.setParameter("Pesel", pesel);
			query.setParameter("idBiura", idOffice);
			Worker wo = null;
			try {
				wo = query.getSingleResult();
			}
			catch (NoResultException e) {
				
			}
			catch (Exception e) {
				logger.error("Error!", e);
			}
			finally {
				em.close();
			}
			return wo;
		}
		/**
		 * Zwraca pracownika po nicku
		 * @param nick Nick pracownika
		 * @return zwr�ci null je�eli nie znaleziono pracownika
		 */
		public static @Nullable Worker getWorker(@NotNull String nick)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			TypedQuery<Worker> query = em.createQuery("SELECT c FROM WORKER c WHERE c.nick LIKE :Nick", Worker.class);
			query.setParameter("Nick", nick);
			Worker wo = null;
			try {
				wo = query.getSingleResult();
			}
			catch (NoResultException e) {
				
			}
			catch (Exception e) {
				logger.error("Error!", e);
			}
			finally {
				em.close();
			}
			return wo;
			
		}
		/**
		 * Sprawdza po nicku czy pracownika istnieje
		 * @param nick Nick pracownika
		 * @return Zwraca true je�eli pracownik istnieje
		 */
		public static boolean isWorkerExist(@NotNull String nick)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			TypedQuery<Worker> q = em.createQuery("SELECT pr FROM WORKER pr WHERE nick like :Nick", Worker.class);
			q.setParameter("Nick", nick);
			
			try {

				List<Worker> wo =  q.getResultList();
				
				for(Worker buff : wo)
					if(buff.getNick().equals(nick))
						return true;

				
			} catch (Exception e) {
				logger.error("Error!", e);
				return true;
			}finally {
				em.close();
			}
			
			return false;
		}
		/**
		 * Dodaje pracownika do bazy danych
		 * @param wo Obiekt z danymi
		 * @return zwraca true je�eli si� powiod�o
		 */
		public static boolean addWorker(@NotNull Worker wo)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
				em.persist(wo);
				et.commit();
			}catch (Exception e) {
				if(et!=null)
					et.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return true;
		}
		/**
		 * Usuwa pracownika o podanym id z bazy danych
		 * @param id
		 * @return true je�eli usuni�to pracownika
		 */
		public static boolean removeWorker(int id)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			Worker wo = null;
			try {
				et = em.getTransaction();
				et.begin();
				wo = em.find(Worker.class, id);
				if(wo!=null)
					em.remove(em.contains(wo)?wo:em.merge(wo));
				
				et.commit();
			}catch (Exception e) {
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return wo != null;
		}
		/**
		 * Aktualizuje pracownika o podanym id w obiekcie
		 * @param wo Obiekt zawieraj�cy id klienta i dane do aktualizacji
		 * @return true je�eli aktualizacja si� powiod�a
		 */
		public static boolean updateWorker(@NotNull Worker wo)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			Worker worker = null;
			try {
				et = em.getTransaction();
				et.begin();
				worker =em.find(Worker.class, wo.getIdWorker());
				
				if(worker!=null)
				{
					em.detach(worker);
					worker.setName(wo.getName());
					worker.setNick(wo.getNick());
					worker.setPassword(wo.getPassword());
					worker.setPesel(wo.getPesel());
					worker.setSurname(wo.getSurname());
					em.merge(worker);
				}
				
				et.commit();
			}catch (Exception e) {
				if(et!=null)
					et.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return worker != null;
			
		}
	}
	/**
	 * 
	 * @author wojci
	 * <p>
	 * @see DataBaseManager
	 * <p>
	 * Klasa zawieraj�ca wszystkie metody obs�uguj�ce biuro w bazie danych
	 */
	public final static class OfficeManager{
		private OfficeManager() {};
		/**
		 * Dodaje biuro do bazy danych
		 * @param of Obiekt z danymi
		 * @return true je�eli dodano biuro do bazy danych
		 */
		public static boolean addOffice(@NotNull Office of)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction tr = null;
			if(OfficeManager.isExistOffice(of.getNIP()))
				return false;
			try {
				tr = em.getTransaction();
				tr.begin();
				em.persist(of);
				tr.commit();
		
			} catch (Exception e) {
				if(tr!=null)
					tr.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
		
			return true;
		}
		/**
		 * Usuwa biuro o podanym id
		 * @param idOffice id biura
		 * @return zwraca true je�eli usuni�to biuro
		 */
		public static boolean removeOffice(int idOffice)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			Office office = null;
			try {
				et = em.getTransaction();
				et.begin();
				office =em.find(Office.class,idOffice);
				
				if(office!=null)
					em.remove(em.contains(office)?office:em.merge(office));
				
				et.commit();
			}catch (Exception e) {
				if(et!=null)
					et.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return office != null;
		}
		/**
		 * Usuwa biuro o podanym nip
		 * @param nip nip biura
		 * @return zwraca true je�eli usuni�to
		 */
		public static boolean removeOfficeByNip(int nip)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			Office office = null;
			try {
				et = em.getTransaction();
				et.begin();
				TypedQuery<Office> buff = em.createQuery("SELECT c FROM BIURA c WHERE c.nip = :NIP", Office.class);
				buff.setParameter("NIP", nip);
				office = buff.getSingleResult();
		
	
				em.remove(em.contains(office)?office:em.merge(office));
				
				
				et.commit();
			}catch (Exception e) {
				if(et!=null)
					et.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return office != null;
		}
		/**
		 * Aktualizuje biuro o id zawartym w obiekcie 
		 * @param of Obiekt z danymi do aktualizacji 
		 * @return zwraca true je�eli aktualizacja si� powiod�a
		 */
		public static boolean updateOffice(@NotNull Office of)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			Office office = null;
			try {
				et = em.getTransaction();
				et.begin();
				office =em.find(Office.class,of.getIdOffice());
				
				if(office!=null)
				{
					em.detach(office);
					office.setName(of.getName());
					office.setNIP(of.getNIP());
					em.merge(office);
				}
				
				et.commit();
			}catch (Exception e) {
				if(et!=null)
					et.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return office != null;
		}
		/**
		 * Sprawdza czy biuro o podanymi nipie istnieje w bazie danych
		 * @param nip Nip biura
		 * @return true je�eli istnieje w bazie danych
		 */
		public static boolean isExistOffice(int nip)
		{

			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			try {
				TypedQuery<Office> query = em.createQuery("SELECT B FROM BIURA B WHERE B.nip = :NIP",Office.class);
				query.setParameter("NIP", nip);
				query.getSingleResult();		
			}catch (Exception e) {
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return true;
		}
		/**
		 * Zwraca biuro o podanym nip
		 * @param nip Nip biura
		 * @return null je�eli biuro o podanym nip nie istnieje
		 */
		public static @Nullable Office getOffice(int nip)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			Office office = null;
			try {
				
				TypedQuery<Office> query = em.createQuery("SELECT B FROM BIURA B WHERE B.nip = :NIPe",Office.class);
				query.setParameter("NIPe", nip);
				office = query.getSingleResult();		
			}catch (Exception e) {
				logger.error("Error!", e);
				return null;
			}finally {
				em.close();
			}
			return office;
		}
		/**
		 * Zwraca biuro o podanym id biura
		 * @param idoffice id biura
		 * @return zwraca null je�eli biuro o podanym nip nie istnieje
		 */
		public static @Nullable Office getOfficeById(int idoffice)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			Office office = null;
			try {
				office = em.find(Office.class, idoffice);		
				if(office != null)
					em.detach(office);
			}catch (Exception e) {
				logger.error("Error!", e);
				return null;
			}finally {
				em.close();
			}
			return office;
		}
	}
	/**
	 * 
	 * @author wojci
	 * <p>
	 * @see DataBaseManager
	 * <p>
	 * Klasa zawieraj�ce wszystkie metody operuj�ce na adresach w bazie danych
	 */
	public final static class AdresyManager{
		private AdresyManager() {};
		/**
		 * Dodaje adres do bazy danych
		 * @param ad Obiekt z danymi do dodania
		 * @return zwraca true je�eli dodano pomy�lnie
		 */
 		public static boolean addAdresy(@NotNull Adresy ad)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction tr = null;
			try {
				tr = em.getTransaction();
				tr.begin();
				em.persist(ad);
				tr.commit();
			} catch (Exception e) {
				if(tr != null)
					tr.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return true;
		}
		/**
		 * Aktualizuje adres odpowiadaj�cy danym w obiekcie
		 * @param ad Obiekt z danymi
		 * @return zwraca true je�eli aktualizacja si� powiod�a
		 */
		public static boolean updateAdresy(@NotNull Adresy ad)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction tr = null;
			Adresy ad_buff = null;
			
			try {
				tr = em.getTransaction();
				tr.begin();
				
				ad_buff = em.find(Adresy.class, ad.getIdAdresy());
				
				if(ad_buff!=null)
				{
					em.detach(ad_buff);
					ad_buff.setCountry(ad.getCountry());
					ad_buff.setHomenumber(ad.getHomenumber());
					ad_buff.setPostdode(ad.getPostdode());
					ad_buff.setCity(ad.getCity());
					ad_buff.setStreet(ad.getStreet());
					em.merge(ad_buff);
				}
				
				tr.commit();
				
				
			}catch (Exception e) {
				if(tr!=null)
					tr.rollback();
				logger.error("Error!", e);
			}finally {
				em.close();
			}
			
			
			return ad_buff!=null;	
		}
		/**
		 * Zwraca adres o powi�zanym id
		 * @param idAdresy id adresu
		 * @return zwraca null je�eli nie znaleziono adresu o podanym id
		 */
		public static @Nullable Adresy getAdresy(int idAdresy)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			Adresy ad = null;
			try {
				ad = em.find(Adresy.class, idAdresy);
				em.detach(ad);
			}catch (Exception e) {
				logger.error("Error!", e);
			}finally {
				em.close();
			}
			return ad;
		}
		
		/**
		 * Usuwa adres o powi�zanym id
		 * @param idAdresy id adresu 
		 * @return zwraca true je�eli usuni�to adres
		 */
		public static boolean removeAdresy(int idAdresy)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction tr = null;
			Adresy ad = null;
			try {
				tr = em.getTransaction();
				tr.begin();
				
				ad = em.find(Adresy.class, idAdresy);
				if(ad!=null)
					em.remove(ad);
				
				tr.commit();
			}catch (Exception e) {
				if(tr != null)
					tr.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return ad!=null;
		}
		
	}
	/**
	 * 
	 * @author wojci
	 * <p>
	 * @see DataBaseManager
	 * <p>
	 * Przechowuje wszystkie metody powi�zane z obs�ug� wycieczek w bazie danych
	 */
	public final static class TripManager{
		private TripManager() {};
		/**
		 * Dodaje wycieczk� do bazy danych
		 * @param tp Obiekt z danymi
		 * @return zwraca true je�eli dodanie si� powiod�o
		 */
		public static boolean addTrip(@NotNull Trip tp)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			try {
				SimpleDateFormat buff = new SimpleDateFormat("dd/MM/yyyy");
				tp.setDatein(new java.sql.Date(buff.parse(buff.format(tp.getDatein())).getTime()));
				tp.setDateout(new java.sql.Date(buff.parse(buff.format(tp.getDateout())).getTime()));
				et = em.getTransaction();
				et.begin();
				em.persist(tp);
				et.commit();
			}catch (Exception e) {
				if(et!=null)
					et.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return true;
		}
		/**
		 * Usuwa wycieczk� z bazy danych o podanym id
		 * @param idTrip Id wycieczki
		 * @return zwraca true je�eli usuni�to wycieczk�
		 */
		public static boolean removeTrip(int idTrip)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			Trip pay = null;
			try {
				et = em.getTransaction();
				et.begin();
				pay = em.find(Trip.class, idTrip);
				if(pay != null)
					em.remove(pay);
				et.commit();
				
			}catch (Exception e) {
				if(et!=null)
					et.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return pay!=null;
		}
		/**
		 * Zwraca wycieczk� o podanym id
		 * @param idTrip id wycieczki
		 * @return zwraca null je�eli znalezienie wycieczki si� nie powiod�o
		 */
		public static @Nullable Trip getTrip(int idTrip)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			Trip tp = null;
			try {
				tp = em.find(Trip.class, idTrip);
				em.detach(tp);
			}catch (Exception e) {
				logger.error("Error!", e);
			}finally {
				em.close();
			}
			return tp;
		}
		/**
		 * Zwraca wszystkie wycieczki powi�zane z biurem, i spe�niaj�ce wymagania zawarte w Trip
		 * @param idOffice id biura
		 * @param tr Obiekt z danymi
		 * @return zwraca tablice obiekt�w, lub null je�eli wyst�pi� b��d
		 */
		public static @Nullable Trip[] getAllTrips(int idOffice,@NotNull com.Michalski.Mosiolek.Minner.Communication.Trip tr)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			
			TypedQuery<Trip> trips = em.createQuery("SELECT tr FROM TRIP tr WHERE tr.idOffice = :OfficeId AND tr.dateout >= :dateout AND tr.datein <= :datein", Trip.class);
			trips.setParameter("OfficeId", idOffice);
			trips.setParameter("dateout", tr.getDate_out());
			trips.setParameter("datein", tr.getDate_back());
			try {
				List<Trip> list = trips.getResultList();
				return list.toArray(new Trip[0]);
			}catch (Exception e) {
				logger.error("Error!", e);
			}finally {
				em.close();
			}
			return null;
		}
		/**
		 * Aktualizuje wycieczk� danymi zawartymi w obiekcie
		 * @param pay Obiekt z danymi
		 * @return zwraca true je�eli aktualizacja przebieg�a pomy�lnie
		 */
		public static boolean updateTrip(@NotNull Trip pay)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			Trip pat = null;
			try {
				et = em.getTransaction();
				et.begin();
				pat = em.find(Trip.class, pay.getIdTrip());
				if(pat!=null)
				{
					em.detach(pat);
					pat.setDatein(pay.getDatein());
					pat.setDateout(pay.getDateout());
					pat.setMaxguests(pay.getMaxguests());
					em.merge(pat);
				}
				
				et.commit();
			}catch (Exception e) {
				logger.error("Error!", e);
				if(et!=null)
					et.rollback();
				return false;
			}finally {
				em.close();
			}
			return pat!=null;
		}
	}
	/**
	 * 
	 * @author wojci
	 * <p>
	 * @see DataBaseManager
	 * <p>
	 * Posiada wszystkie metody pozwalaj�ce na operowanie na p�atno�ciach w bazie danych
	 */
	public final static class PaymentManager{
		private PaymentManager() {};
		private static Object synch = new Object();
		/**
		 * Dodaje p�atno�� do bazy danych
		 * @param pa Obiekt z danymi
		 * @return zwraca true je�eli dodano p�atno�� prawid�owo
		 */
		public static boolean addPayment(@NotNull Payment pa)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
				em.persist(pa);
				et.commit();
			} catch (Exception e) {
				if(et!=null)
					et.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return true;
		}
		/**
		 * Zwraca p�atno�� o podanym id
		 * @param idPayment id p�atno�ci
		 * @return zwraca null je�eli nieznaleziono
		 */
		public static @Nullable Payment getPayment(int idPayment)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			Payment pay = null;
			try {
				pay = em.find(Payment.class, idPayment);
				em.detach(pay);
			} catch (Exception e) {
				logger.error("Error!", e);
				return null;
			}finally {
				em.close();
			}
			
			return pay;
		}
		/**
		 * Zwraca id p�atno�ci po kluczu obcym umowy
		 * @param id id umowy
		 * @return zwraca null je�eli nie znaleziono 
		 */
		public static @Nullable Payment getPaymentByAggrement(int id)
		{

			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			TypedQuery<Payment> bu = em.createQuery("SELECT C FROM PLATNOSCI C WHERE C.idaggrement = :ID", Payment.class);
			bu.setParameter("ID", id);
			Payment pay = null;
			try {
				pay = bu.getSingleResult();
			} catch (Exception e) {
				logger.error("Error!", e);
				return null;
			}finally {
				em.close();
			}
			
			return pay;
		}
		/**
		 * Usuwa p�atno�� z bazy danych 
		 * @param idPayment id p�atno�ci
		 * @return zwraca true je�eli usuni�cie si� powiod�o
		 */
		public static boolean removePayment(int idPayment)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			Payment pay = null;
			try {
				et = em.getTransaction();
				et.begin();
				pay = em.find(Payment.class, idPayment);
				if(pay != null)
					em.remove(em.contains(pay)?pay:em.merge(pay));
				et.commit();
				
			}catch (Exception e) {
				logger.error("Error!", e);
				if(et!=null)
					et.rollback();
				return false;
			}finally {
				em.close();
			}
			return pay!=null;
		}
		/**
		 * Aktualizuje p�atno�� o podane w obiekcie dane
		 * @param pay Obiekt z danymi
		 * @return zwraca true je�eli aktualizacja si� powiod�a
		 */
		public static boolean updatePayment(@NotNull Payment pay)
		{
			synchronized (synch) {
				EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
				EntityTransaction et = null;
				Payment payment = null;
				try {
					et = em.getTransaction();
					et.begin();
					payment = em.find(Payment.class, pay.getIdPayment());
					if(payment!=null)
					{
						em.detach(payment);
						payment.setState(pay.getState());
						payment.setTotal_cost(pay.getTotal_cost());
						em.merge(payment);
					}
					
					et.commit();
					
				}catch (Exception e) {
					logger.error("Error!", e);
					if(et!=null)
						et.rollback();
					return false;
				}finally {
					em.close();
				}
				return payment!=null;
			}
		}
	}
	/**
	 * 
	 * @author wojci
	 * <p>
	 * @see DataBaseManager
	 * <p>
	 * Zawiera wszystkie metody obs�uguj�ce klienta w bazie danych
	 */
	public final static class ClientManager{
		private ClientManager() {};
		/**
		 * Dodaje klienta do bazy danych
		 * @param cl Obiekt z danymi 
		 * @return zwraca true je�eli dodanie si� powiod�o
		 */
		public static boolean addClient(@NotNull Client cl) {
			

			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			
			EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
				em.persist(cl);
				et.commit();
				
			}catch (Exception e) {
				logger.error("Error!", e);
				if(et!=null)
					et.rollback();
				return false;
			}finally {
				em.close();
			}
			
			
			return true;
		}
		/**
		 * Zwraca klienta o podanym id
		 * @param idClient id klienta 
		 * @return zwraca null je�eli nie znaleziono 
		 */
		public static @Nullable Client getClient(int idClient)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			
			TypedQuery<Client> query = em.createQuery("SELECT kl FROM KLIENCI kl WHERE kl.idClient = :idClient", Client.class);
			query.setParameter("idClient", idClient);
			Client cl = null;
			
			try {
				cl = query.getSingleResult();
				return cl;
			}
			catch (NoResultException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				logger.error("Error!", e);
			}
			finally {
				em.close();
			}
			return null;
		}
		/**
		 * Zwraca klienta powi�zanego z biurem i podanym peselem
		 * @param idOffice id biura
		 * @param pesel Pesel klienta
		 * @return zwraca null je�eli nie znaleziono
		 */
		public static @Nullable Client getClient(int idOffice,@NotNull String pesel)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			
			TypedQuery<Client> query = em.createQuery("SELECT kl FROM KLIENCI kl WHERE kl.pesel LIKE :Pesel AND kl.idBiura = :idBiura", Client.class);
			query.setParameter("Pesel", pesel);
			query.setParameter("idBiura", idOffice);
			Client cl = null;
			
			try {
				cl = query.getSingleResult();
				return cl;
			}
			catch (NoResultException e) {
			}
			catch (Exception e) {
				logger.error("Error!", e);
			}
			finally {
				em.close();
			}
			return null;
		}
		/**
		 * Usuwa klienta o podanym peselu w danym biurze
		 * @param idOffice id biura
		 * @param pesel pesel klienta
		 * @return true je�eli usuni�to klienta pomy�lnie z bazy danych
		 */
		public static boolean removeClient(int idOffice,@NotNull String pesel)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			Client cl = null;
			try {
				et = em.getTransaction();
				et.begin();
				cl = getClient(idOffice, pesel);
				if(cl != null)
					em.remove(em.merge(cl));
				
				et.commit();
			}catch (Exception e) {
				if(et!=null)
					et.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return cl!=null;
		}
		/**
		 * Aktualizuje klienta o zawarte w obiekcie dane
		 * @param cl Obiekt z danymi
		 * @return zwraca true je�eli aktualizacja przebieg�a pomyslnie
		 */
		public static boolean updateClient(@NotNull Client cl)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction tr = null;
			Client client = null;
			try {
				tr = em.getTransaction();
				tr.begin();
				
				client = em.find(Client.class, cl.getIdClient());
				if(client != null)
				{
					em.detach(client);
					client.setName(cl.getName());
					client.setPesel(cl.getPesel());
					client.setPhonenumber(cl.getPhonenumber());
					client.setSurname(cl.getSurname());
					em.merge(client);
				}
				tr.commit();
				
			}catch (Exception e) {
				if(tr!=null)
					tr.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			
			
			
			return client != null;
		}
		/**
		 * Sprawdza czy istnieje klient 
		 * @param idOffice id biura
		 * @param pesel Pesel klienta
		 * @return zwraca true je�eli klient o podanych danych istnieje w bazie danych
		 */
		public static boolean isClientExist(int idOffice,@NotNull String pesel)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			Query query = em.createQuery("SELECT kl FROM KLIENCI kl WHERE kl.idBiura = :idBiura AND kl.pesel LIKE :Pesel");
			query.setParameter("idBiura", idOffice);
			query.setParameter("Pesel", pesel);
			
			try {
				query.getSingleResult();
			} catch (Exception e) {
				logger.error("Error!", e);
				return false;
			}
			finally {
				em.close();
			}
			
			return true;
		}
		
	}
	/**
	 * 
	 * @author wojci
	 * <p>
	 * @see DataBaseManager
	 * <p>
	 * Obs�uguj� logowanie do serwera
	 */
	public static class AdminLoginManager{
		/**
		 * Sprawdza czy has�o i login s� prawid�owe 
		 * @param nick login 
		 * @param password has�o
		 * @return true je�eli login has�o jest prawid�owe
		 */
		public static boolean isPasswordGood(@NotNull String nick,@NotNull String password)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			Query query = em.createQuery("SELECT al FROM AdminLogin al WHERE al.nick LIKE :NICK AND al.password LIKE :PAS");
			query.setParameter("NICK", nick);
			query.setParameter("PAS", password);
			try {
				query.getSingleResult();
			}
			catch (NoResultException e) {
				return false;
			}catch (Exception e) {
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return true;
		}
		/**
		 * Aktualizuje has�o admina
		 * @param nick login
		 * @param password has�o 
		 * @return true je�eli aktualizacja przebieg�a pomy�lnie
		 */
		public static boolean updatePassword(@NotNull String nick,@NotNull String password)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			TypedQuery<AdminLogin> query = em.createQuery("SELECT al FROM AdminLogin al WHERE al.nick LIKE :NICK", AdminLogin.class);
			query.setParameter("NICK", nick);
			try {
				et = em.getTransaction();
				et.begin();
				
				AdminLogin al = query.getSingleResult();
				al.setPassword(password);
				em.persist(al);
				et.commit();
			}catch (Exception e) {
				if(et != null)
					et.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			return true;
		}
	}
	/**
	 * 
	 * @author wojci
	 * <p>
	 * @see DataBaseManager
	 * <p>
	 * Przechowuje wszystkie metody operuj�ce na umowach w bazie danych
	 */
	public static final class AgrrementManager{
		private AgrrementManager() {}
		/**
		 * Dodaje umow� do bazy danych 
		 * @param ag Obiekt zawieraj�cy dane
		 * @return zwraca true je�eli dodano pomyslnie
		 */
		public static boolean addAggrement(@NotNull Aggrement ag)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			try {
				et = em.getTransaction();
				et.begin();
				em.persist(ag);
				et.commit();
				
			}catch (Exception e) {
				if(et!=null)
					et.rollback();
				logger.error("Error!", e);
				return false;
			}finally {
				em.close();
			}
			
			
			return true;
		}
		/**
		 * Zwraca umow� o podanym id 
		 * @param id id umowy
		 * @return zwraca null je�eli nie znaleziono
		 */
		public static @Nullable Aggrement getAggrement(int id)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			Aggrement ag = null;
			try {
				ag = em.find(Aggrement.class, id);
				if(ag != null)
					em.detach(ag);
			}catch (Exception e) {
				logger.error("Error!", e);
			}finally {
				em.close();
			}
			
			
			return ag;
		}
		/**
		 * Zwraca list� um�w z biura, oraz spe�niaj�cy odpowiednie warunki
		 * @param idOffice idbiura
		 * @param ag Obiekt z danymi
		 * @return zwraca true je�eli wyst�pi� b��d
		 */
		public static @Nullable Aggrement[] getAggrements(int idOffice,@NotNull com.Michalski.Mosiolek.Minner.Communication.Aggrement ag)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			
			SimpleDateFormat buff = new SimpleDateFormat("dd/MM/yyyy");
			
			TypedQuery<Aggrement> query = em.createQuery("SELECT al FROM UMOWY al WHERE al.aggrement_date >= :OUT AND al.aggrement_date <= :IN", Aggrement.class);
			try {
				query.setParameter("OUT", new java.sql.Date(buff.parse(buff.format(ag.getDate_out())).getTime()));
				query.setParameter("IN", new java.sql.Date(buff.parse(buff.format(ag.getDate_back())).getTime()));
			} catch (ParseException e1) {
				em.close();
				logger.error("Error!", e1);
				return null;
			}
			
			try {
				List<Aggrement> list = query.getResultList();
				return list.toArray(new Aggrement[0]);
			}catch (Exception e) {
				logger.error("Error!", e);
			}finally {
				em.close();
			}
			return null;
		}
		/**
		 * Usuwa umow� z bazy danych
		 * @param idAggrement id umowy do usuni�cia
		 * @return zwraca true je�eli usuni�to umow� pomyslnie
		 */
		public static boolean removeAggrement(int idAggrement)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			Aggrement ag = null;
			try {
				et = em.getTransaction();
				et.begin();
				ag = em.find(Aggrement.class, idAggrement);
				if(ag!=null)
					em.remove(ag);
				et.commit();
				
			}catch (Exception e) {
				logger.error("Error!", e);
				if(et!=null)
					et.rollback();
				return false;
			}finally {
				em.close();
			}
			return ag!=null;
		}
		/**
		 * Aktualizuje umow� w oparciu o dane zawarte w obiekcie
		 * @param aggrement Obiekt z danymi
		 * @return zwraca true je�eli aktualizowano pomy�lnie
		 */
		public static boolean updateAggrement(@NotNull Aggrement aggrement)
		{
			EntityManager em = DataBaseManager.getEntityManager().createEntityManager();
			EntityTransaction et = null;
			Aggrement ag = null;
			try {
				et = em.getTransaction();
				et.begin();
				ag = em.find(Aggrement.class, aggrement.getIdAggrement());
				em.detach(ag);
				if(ag!=null)
				{
					ag.setAggrement_date(aggrement.getAggrement_date());
					ag.setGuestcount(aggrement.getGuestcount());
					em.merge(ag);
				}
				et.commit();
				
			}catch (Exception e) {
				logger.error("Error!", e);
				if(et!=null)
					et.rollback();
				return false;
			}finally {
				em.close();
			}
			return ag!=null;
		}
	}
	/**
	 * Zwraca EntityManagerFactory dla dla hiberNate
	 * @return
	 */
	static @NotNull EntityManagerFactory getEntityManager()
	{
		return ENTITY_MANAGER_FACTORY;
	}
	/**
	 * Sprzwdza czy baza jest po��czona
	 * @return true je�eli baza jest pod��czona
	 */
	public static boolean isConnected()
	{
		return getEntityManager().isOpen();
	}
	
}
