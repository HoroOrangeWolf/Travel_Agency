package com.Michalski.Mosiolek.Minner.Serwer;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.IllegalFormatException;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.swing.JPasswordField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.Michalski.Mosiolek.Minner.Serwer.Client.ClientOperator;
import com.Michalski.Mosiolek.Minner.Serwer.Database.Adresy;
import com.Michalski.Mosiolek.Minner.Serwer.Database.DataBaseManager;
import com.Michalski.Mosiolek.Minner.Serwer.Database.Office;
import com.Michalski.Mosiolek.Minner.Serwer.Database.Worker;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 
 * @author wojci
 * <p>
 * Podstawowa klasa GUI
 */
public class Window {
	private static final Logger logger = Logger.getLogger(Window.class);
	private JFrame frame;
	private JTextField login;
	private JPasswordField haslo;
	private JPasswordField potwhaslo;
	private JPasswordField potwhaslo1;
	private JLabel status;
	private Thread thread;
	private Serwer ser;
	private JTextField usertodel;
	private JTextField NIPb;
	private JTextField nameb;
	private JTextField streetb;
	private JTextField postcodeb;
	private JTextField housenumberb;
	private JTextField cityb;
	private JTextField NIPbu;
	private JTextField nicku;
	private JPasswordField passwordu;
	private JPasswordField password1u;
	private JTextField countryu;
	private JTextField cityu;
	private JTextField streetu;
	private JTextField postnumberu;
	private JTextField homenumberu;
	private JTextField nameu;
	private JTextField surnameu;
	private JTextField peselu;
	private JTextField NIPu;
	private JTextField countryb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();	
					
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		new DataBaseManager();
		this.prepareSerwer();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardLayout lay = new CardLayout();
		JPanel panel = new JPanel();
		panel.setLayout(lay);
		
		JPanel PanelLogowania = new JPanel();
		PanelLogowania.setBackground(Color.LIGHT_GRAY);
		JButton btnNewButton = new JButton("Login");
	
		JPanel PanelZarzadzania = new JPanel();
		PanelZarzadzania.setForeground(Color.LIGHT_GRAY);
		PanelZarzadzania.setBackground(Color.LIGHT_GRAY);
		
		haslo = new JPasswordField();
		haslo.setHorizontalAlignment(SwingConstants.CENTER);
		
		login = new JTextField();
		login.setHorizontalAlignment(JTextField.CENTER);
		login.setColumns(10);
		
		panel.add(PanelLogowania,"panellogowania");
		
		JLabel lblNewLabel = new JLabel("Panel Logowania", SwingConstants.CENTER);
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(25.0f));
		lblNewLabel.setForeground(Color.BLACK);
		
		JLabel lblNewLabel_1 = new JLabel("Has\u0142o");
		
		JLabel lblNewLabel_2 = new JLabel("Login");

		status = new JLabel("Status Bazy Danych: ");
		GroupLayout gl_PanelLogowania = new GroupLayout(PanelLogowania);
		gl_PanelLogowania.setHorizontalGroup(
			gl_PanelLogowania.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelLogowania.createSequentialGroup()
					.addGap(218)
					.addGroup(gl_PanelLogowania.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
						.addGroup(gl_PanelLogowania.createSequentialGroup()
							.addGap(95)
							.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
							.addGap(112)))
					.addGap(213))
				.addGroup(gl_PanelLogowania.createSequentialGroup()
					.addGap(253)
					.addComponent(login, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
					.addGap(246))
				.addGroup(gl_PanelLogowania.createSequentialGroup()
					.addGap(313)
					.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
					.addGap(325))
				.addGroup(gl_PanelLogowania.createSequentialGroup()
					.addGap(253)
					.addGroup(gl_PanelLogowania.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(haslo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
					.addGap(246))
				.addGroup(gl_PanelLogowania.createSequentialGroup()
					.addContainerGap()
					.addComponent(status, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(439, Short.MAX_VALUE))
		);
		gl_PanelLogowania.setVerticalGroup(
			gl_PanelLogowania.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelLogowania.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_PanelLogowania.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_PanelLogowania.createSequentialGroup()
							.addGap(54)
							.addComponent(lblNewLabel_2)))
					.addGap(4)
					.addComponent(login, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblNewLabel_1)
					.addGap(11)
					.addComponent(haslo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
					.addComponent(status, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		PanelLogowania.setLayout(gl_PanelLogowania);
		panel.add(PanelZarzadzania,"PanelZarzadzania");
		
		JButton btnNewButton_1 = new JButton("Obs\u0142uga U\u017Cytkownika");

		
		JButton btnNewButton_3 = new JButton("W\u0142\u0105cz Serwer");
		
		JButton btnNewButton_4 = new JButton("Wy\u0142\u0105cz Serwer");
		
		JButton btnNewButton_5 = new JButton("Wyloguj");
		
		JButton btnNewButton_8 = new JButton("Obs\u0142uga Biura");
		
		
		JLabel statusserwera = new JLabel("<html>Status Serwera: <font color = 'red'>Nieaktywny</font></html>");
		GroupLayout gl_PanelZarzadzania = new GroupLayout(PanelZarzadzania);
		gl_PanelZarzadzania.setHorizontalGroup(
			gl_PanelZarzadzania.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelZarzadzania.createSequentialGroup()
					.addGap(10)
					.addComponent(statusserwera, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(463))
				.addGroup(Alignment.TRAILING, gl_PanelZarzadzania.createSequentialGroup()
					.addGap(260)
					.addGroup(gl_PanelZarzadzania.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnNewButton_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(btnNewButton_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(btnNewButton_5, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(btnNewButton_8, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
					.addGap(278))
		);
		gl_PanelZarzadzania.setVerticalGroup(
			gl_PanelZarzadzania.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PanelZarzadzania.createSequentialGroup()
					.addGap(74)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_8)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton_4)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton_5)
					.addGap(118)
					.addComponent(statusserwera))
		);
		PanelZarzadzania.setLayout(gl_PanelZarzadzania);
		lay.show(panel, "panellogowania");
		
		
		
		
	
	
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		JPanel Uzytkownik = new JPanel();
		Uzytkownik.setBackground(Color.LIGHT_GRAY);
		panel.add(Uzytkownik, "Uzytkownik");
		
		JLabel lblNewLabel_4 = new JLabel("Menu");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNewButton_6 = new JButton("Dodaj lub Modyfikuj U\u017Cytkownika");
		
		JButton btnNewButton_12 = new JButton("Usu\u0144");

		
		JButton btnNewButton_15 = new JButton("Powr\u00F3t");
		
		GroupLayout gl_Uzytkownik = new GroupLayout(Uzytkownik);
		gl_Uzytkownik.setHorizontalGroup(
			gl_Uzytkownik.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Uzytkownik.createSequentialGroup()
					.addGap(302)
					.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addGap(334))
				.addGroup(gl_Uzytkownik.createSequentialGroup()
					.addGap(228)
					.addComponent(btnNewButton_6, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addGap(257))
				.addGroup(gl_Uzytkownik.createSequentialGroup()
					.addGap(228)
					.addComponent(btnNewButton_12, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addGap(257))
				.addGroup(gl_Uzytkownik.createSequentialGroup()
					.addGap(228)
					.addComponent(btnNewButton_15, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addGap(257))
		);
		gl_Uzytkownik.setVerticalGroup(
			gl_Uzytkownik.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Uzytkownik.createSequentialGroup()
					.addGap(42)
					.addComponent(lblNewLabel_4)
					.addGap(18)
					.addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton_12, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(btnNewButton_15, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
		);
		Uzytkownik.setLayout(gl_Uzytkownik);
		
		JPanel ZmianaHasla = new JPanel();
		ZmianaHasla.setBackground(Color.LIGHT_GRAY);
		panel.add(ZmianaHasla, "ZmianaHasla");
		
		potwhaslo = new JPasswordField();
		potwhaslo.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel lblNewLabel_14 = new JLabel("Podaj Has\u0142o");
		
		JButton zatwhaslo = new JButton("Zatwierdz");
		
		potwhaslo1 = new JPasswordField();
		potwhaslo1.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel lblNewLabel_15 = new JLabel("Powt\u00F3rz has\u0142o");
		GroupLayout gl_ZmianaHasla = new GroupLayout(ZmianaHasla);
		gl_ZmianaHasla.setHorizontalGroup(
			gl_ZmianaHasla.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ZmianaHasla.createSequentialGroup()
					.addGap(285)
					.addComponent(lblNewLabel_14, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
					.addGap(311))
				.addGroup(gl_ZmianaHasla.createSequentialGroup()
					.addGap(232)
					.addComponent(potwhaslo, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
					.addGap(280))
				.addGroup(gl_ZmianaHasla.createSequentialGroup()
					.addGap(285)
					.addComponent(lblNewLabel_15, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
					.addGap(311))
				.addGroup(gl_ZmianaHasla.createSequentialGroup()
					.addGap(232)
					.addComponent(potwhaslo1, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
					.addGap(280))
				.addGroup(gl_ZmianaHasla.createSequentialGroup()
					.addGap(259)
					.addComponent(zatwhaslo, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
					.addGap(311))
		);
		gl_ZmianaHasla.setVerticalGroup(
			gl_ZmianaHasla.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ZmianaHasla.createSequentialGroup()
					.addGap(113)
					.addComponent(lblNewLabel_14)
					.addGap(11)
					.addComponent(potwhaslo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(lblNewLabel_15)
					.addGap(8)
					.addComponent(potwhaslo1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(zatwhaslo, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
		);
		ZmianaHasla.setLayout(gl_ZmianaHasla);
		
		JPanel UsunUzytkownik = new JPanel();
		UsunUzytkownik.setBackground(Color.LIGHT_GRAY);
		panel.add(UsunUzytkownik, "UsunUzytkownik");
		
		JLabel lblNewLabel_7 = new JLabel("Usu\u0144");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		
		usertodel = new JTextField();
		usertodel.setColumns(10);
		
		JButton deletesUser = new JButton("Usu\u0144");
	
		
		JButton retfromuserdel = new JButton("Powr\u00F3t");
	
		
		JLabel lblNewLabel_8 = new JLabel("Nick");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_UsunUzytkownik = new GroupLayout(UsunUzytkownik);
		gl_UsunUzytkownik.setHorizontalGroup(
			gl_UsunUzytkownik.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_UsunUzytkownik.createSequentialGroup()
					.addGroup(gl_UsunUzytkownik.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_UsunUzytkownik.createSequentialGroup()
							.addGap(233)
							.addGroup(gl_UsunUzytkownik.createParallelGroup(Alignment.LEADING)
								.addComponent(deletesUser, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
								.addComponent(retfromuserdel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
								.addComponent(usertodel, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
						.addGroup(Alignment.LEADING, gl_UsunUzytkownik.createSequentialGroup()
							.addGap(283)
							.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
							.addGap(55)))
					.addGap(278))
				.addGroup(gl_UsunUzytkownik.createSequentialGroup()
					.addGap(261)
					.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
					.addGap(310))
		);
		gl_UsunUzytkownik.setVerticalGroup(
			gl_UsunUzytkownik.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_UsunUzytkownik.createSequentialGroup()
					.addGap(46)
					.addComponent(lblNewLabel_7)
					.addGap(5)
					.addComponent(lblNewLabel_8)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(usertodel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(deletesUser, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(retfromuserdel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(136, Short.MAX_VALUE))
		);
		UsunUzytkownik.setLayout(gl_UsunUzytkownik);
		
		JPanel Biuro = new JPanel();
		Biuro.setBackground(Color.LIGHT_GRAY);
		panel.add(Biuro, "Biuro");
		
		JLabel var = new JLabel("Menu");
		var.setBounds(299, 11, 79, 14);
		
		JButton btnNewButton_18 = new JButton("Dodaj lub Modyfikuj Biuro");
		
		btnNewButton_18.setBounds(236, 43, 172, 50);
		
		JButton btnNewButton_19 = new JButton("Usu\u0144 Biuro");
		
		btnNewButton_19.setBounds(236, 104, 172, 48);
		Biuro.add(btnNewButton_19);
		
		JButton btnNewButton_21 = new JButton("Powr\u00F3t");
		
		btnNewButton_21.setBounds(236, 163, 172, 48);
		Biuro.setLayout(null);
		Biuro.add(btnNewButton_18);
		Biuro.add(btnNewButton_21);
		Biuro.add(var);
		
		JPanel DodajModyfikujBiuro = new JPanel();
		DodajModyfikujBiuro.setBackground(Color.LIGHT_GRAY);
		panel.add(DodajModyfikujBiuro, "DodajModyfikujBiuro");
		
		NIPb = new JTextField();
		NIPb.setBounds(10, 31, 140, 20);
		NIPb.setText("");
		NIPb.setColumns(10);
		
		JLabel var1 = new JLabel("NIP");
		var1.setBounds(66, 19, 46, 14);
		
		JLabel lblNewLabel_9 = new JLabel("Nazwa");
		lblNewLabel_9.setBounds(54, 62, 46, 14);
		
		nameb = new JTextField();
		nameb.setBounds(10, 75, 140, 20);
		nameb.setColumns(10);
		
		JButton btnNewButton_9 = new JButton("Dodaj");
	
		btnNewButton_9.setBounds(178, 75, 199, 47);
		
		JButton btnNewButton_10 = new JButton("Powr\u00F3t");
		
		btnNewButton_10.setBounds(178, 303, 199, 42);
		
		JLabel lblNewLabel_10 = new JLabel("Ulica");
		lblNewLabel_10.setBounds(66, 106, 46, 14);
		
		streetb = new JTextField();
		streetb.setBounds(10, 118, 140, 20);
		streetb.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Kod pocztowy");
		lblNewLabel_11.setBounds(42, 188, 84, 14);
		
		postcodeb = new JTextField();
		postcodeb.setBounds(10, 200, 140, 20);
		postcodeb.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Numer Domu");
		lblNewLabel_12.setBounds(42, 231, 81, 14);
		
		housenumberb = new JTextField();
		housenumberb.setBounds(10, 245, 140, 20);
		housenumberb.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Miejscowo\u015B\u0107");
		lblNewLabel_13.setBounds(42, 276, 101, 14);
		
		cityb = new JTextField();
		cityb.setBounds(10, 289, 140, 20);
		cityb.setColumns(10);
		
		JButton btnNewButton_23 = new JButton("Modyfikuj");
		
		btnNewButton_23.setBounds(178, 247, 199, 45);
		DodajModyfikujBiuro.setLayout(null);
		DodajModyfikujBiuro.add(var1);
		DodajModyfikujBiuro.add(NIPb);
		DodajModyfikujBiuro.add(lblNewLabel_9);
		DodajModyfikujBiuro.add(nameb);
		DodajModyfikujBiuro.add(lblNewLabel_10);
		DodajModyfikujBiuro.add(lblNewLabel_13);
		DodajModyfikujBiuro.add(cityb);
		DodajModyfikujBiuro.add(btnNewButton_10);
		DodajModyfikujBiuro.add(streetb);
		DodajModyfikujBiuro.add(lblNewLabel_11);
		DodajModyfikujBiuro.add(postcodeb);
		DodajModyfikujBiuro.add(lblNewLabel_12);
		DodajModyfikujBiuro.add(housenumberb);
		DodajModyfikujBiuro.add(btnNewButton_9);
		DodajModyfikujBiuro.add(btnNewButton_23);
		
		JButton btnNewButton_14 = new JButton("Pobierz Informacje");
		btnNewButton_14.setBounds(178, 133, 199, 45);
		DodajModyfikujBiuro.add(btnNewButton_14);
		
		JLabel lblNewLabel_28 = new JLabel("Kraj");
		lblNewLabel_28.setBounds(54, 149, 46, 14);
		DodajModyfikujBiuro.add(lblNewLabel_28);
		
		countryb = new JTextField();
		countryb.setBounds(10, 162, 140, 20);
		DodajModyfikujBiuro.add(countryb);
		countryb.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Wyczy\u015B\u0107 Pola");
		
		btnNewButton_2.setBounds(178, 188, 199, 48);
		DodajModyfikujBiuro.add(btnNewButton_2);
		
		JPanel UsunBiuro = new JPanel();
		UsunBiuro.setBackground(Color.LIGHT_GRAY);
		panel.add(UsunBiuro, "UsunBiuro");
		UsunBiuro.setLayout(null);
		
		JLabel lblNewLabel_16 = new JLabel("Usu\u0144 Biuro");
		lblNewLabel_16.setBounds(262, 30, 85, 14);
		UsunBiuro.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("NIP");
		lblNewLabel_17.setBounds(279, 55, 46, 14);
		UsunBiuro.add(lblNewLabel_17);
		
		NIPbu = new JTextField();
		NIPbu.setBounds(227, 80, 165, 20);
		UsunBiuro.add(NIPbu);
		NIPbu.setColumns(10);
		
		JButton btnNewButton_11 = new JButton("Usu\u0144");
		
		btnNewButton_11.setBounds(227, 111, 165, 40);
		UsunBiuro.add(btnNewButton_11);
		
		JButton btnNewButton_22 = new JButton("Powr\u00F3t");
		
		btnNewButton_22.setBounds(227, 162, 165, 40);
		UsunBiuro.add(btnNewButton_22);
		
		JPanel DodajModyfikujUzytkownika = new JPanel();
		DodajModyfikujUzytkownika.setBackground(Color.LIGHT_GRAY);
		panel.add(DodajModyfikujUzytkownika, "DodajModyfikujUzytkownika");
		DodajModyfikujUzytkownika.setLayout(null);
		
		JButton adduser = new JButton("Dodaj");
		adduser.setBounds(364, 11, 139, 47);
		DodajModyfikujUzytkownika.add(adduser);
		
		JButton btnNewButton_26 = new JButton("Powr\u00F3t");
		
		btnNewButton_26.setBounds(364, 239, 139, 47);
		DodajModyfikujUzytkownika.add(btnNewButton_26);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setBounds(0, 0, 358, 361);
		DodajModyfikujUzytkownika.add(tabbedPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setForeground(Color.WHITE);
		tabbedPane.addTab("Informacje Logowania", null, layeredPane, null);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(113, 49, 1, 1);
		layeredPane.add(layeredPane_1);
		
		JLayeredPane layeredPane_2 = new JLayeredPane();
		layeredPane_2.setBounds(113, 81, 1, 1);
		layeredPane.add(layeredPane_2);
		
		JLabel lblNewLabel_19 = new JLabel("Nick");
		lblNewLabel_19.setBounds(10, 11, 46, 14);
		layeredPane.add(lblNewLabel_19);
		
		nicku = new JTextField();
		nicku.setBackground(Color.WHITE);
		nicku.setBounds(10, 36, 132, 20);
		layeredPane.add(nicku);
		nicku.setColumns(10);
		
		JLabel lblNewLabel_20 = new JLabel("Has\u0142o");
		lblNewLabel_20.setBounds(10, 68, 46, 14);
		layeredPane.add(lblNewLabel_20);
		
		passwordu = new JPasswordField();
		passwordu.setBounds(10, 93, 133, 20);
		layeredPane.add(passwordu);
		
		JLabel lblNewLabel_21 = new JLabel("Potwierdz Has\u0142o");
		lblNewLabel_21.setBounds(10, 124, 90, 14);
		layeredPane.add(lblNewLabel_21);
		
		password1u = new JPasswordField();
		password1u.setBounds(10, 149, 132, 20);
		layeredPane.add(password1u);
		
		JLabel lblNewLabel_27 = new JLabel("NIP");
		lblNewLabel_27.setBounds(10, 180, 46, 14);
		layeredPane.add(lblNewLabel_27);
		
		NIPu = new JTextField();
		NIPu.setBounds(10, 205, 132, 20);
		layeredPane.add(NIPu);
		NIPu.setColumns(10);
		
		JLayeredPane layeredPane_3 = new JLayeredPane();
		tabbedPane.addTab("Adres Zamieszkania", null, layeredPane_3, null);
		
		JLabel lblNewLabel_5 = new JLabel("Kraj");
		lblNewLabel_5.setBounds(10, 11, 46, 14);
		layeredPane_3.add(lblNewLabel_5);
		
		countryu = new JTextField();
		countryu.setBounds(10, 36, 119, 20);
		layeredPane_3.add(countryu);
		countryu.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Miejscowo\u015B\u0107");
		lblNewLabel_6.setBounds(10, 67, 95, 14);
		layeredPane_3.add(lblNewLabel_6);
		
		cityu = new JTextField();
		cityu.setBounds(10, 92, 119, 20);
		layeredPane_3.add(cityu);
		cityu.setColumns(10);
		
		JLabel lblNewLabel_18 = new JLabel("Ulica");
		lblNewLabel_18.setBounds(10, 123, 46, 14);
		layeredPane_3.add(lblNewLabel_18);
		
		streetu = new JTextField();
		streetu.setBounds(10, 148, 119, 20);
		layeredPane_3.add(streetu);
		streetu.setColumns(10);
		
		JLabel lblNewLabel_22 = new JLabel("Kod Pocztowy");
		lblNewLabel_22.setBounds(10, 179, 95, 14);
		layeredPane_3.add(lblNewLabel_22);
		
		postnumberu = new JTextField();
		postnumberu.setBounds(10, 204, 119, 20);
		layeredPane_3.add(postnumberu);
		postnumberu.setColumns(10);
		
		JLabel lblNewLabel_23 = new JLabel("Numer Domu");
		lblNewLabel_23.setBounds(10, 235, 77, 14);
		layeredPane_3.add(lblNewLabel_23);
		
		homenumberu = new JTextField();
		homenumberu.setBounds(10, 260, 119, 20);
		layeredPane_3.add(homenumberu);
		homenumberu.setColumns(10);
		
		JLayeredPane layeredPane_4 = new JLayeredPane();
		tabbedPane.addTab("Informacje Osobowe", null, layeredPane_4, null);
		
		JLabel lblNewLabel_24 = new JLabel("Imie");
		lblNewLabel_24.setBounds(0, 0, 46, 14);
		layeredPane_4.add(lblNewLabel_24);
		
		nameu = new JTextField();
		nameu.setBounds(0, 11, 116, 20);
		layeredPane_4.add(nameu);
		nameu.setColumns(10);
		
		JLabel lblNewLabel_25 = new JLabel("Nazwisko");
		lblNewLabel_25.setBounds(0, 42, 66, 14);
		layeredPane_4.add(lblNewLabel_25);
		
		surnameu = new JTextField();
		surnameu.setBounds(0, 55, 116, 20);
		layeredPane_4.add(surnameu);
		surnameu.setColumns(10);
		
		JLabel lblNewLabel_26 = new JLabel("Pesel");
		lblNewLabel_26.setBounds(0, 86, 46, 14);
		layeredPane_4.add(lblNewLabel_26);
		
		peselu = new JTextField();
		peselu.setBounds(0, 99, 116, 20);
		layeredPane_4.add(peselu);
		peselu.setColumns(10);
		
		JButton btnNewButton_27 = new JButton("Modyfikuj");
		
		btnNewButton_27.setBounds(364, 69, 139, 47);
		DodajModyfikujUzytkownika.add(btnNewButton_27);
		
		JButton btnNewButton_13 = new JButton("Pobierz Informacje");
		
		btnNewButton_13.setBounds(364, 127, 139, 49);
		DodajModyfikujUzytkownika.add(btnNewButton_13);
		
		JButton btnNewButton_7 = new JButton("Wyczy\u015B\u0107 Pola");
		
		btnNewButton_7.setBounds(364, 187, 139, 41);
		DodajModyfikujUzytkownika.add(btnNewButton_7);
		
		zatwhaslo.addActionListener((e)->{
				if(!String.copyValueOf(potwhaslo.getPassword()).equals(String.copyValueOf(potwhaslo.getPassword())))
					JOptionPane.showMessageDialog(frame, "Hasla musz� by� takie same!!!", "B��d aktualizacji has�a!", JOptionPane.ERROR_MESSAGE);
				else if(potwhaslo.getPassword().length>2 && potwhaslo.getPassword().length < 51)
				{
					if(!DataBaseManager.AdminLoginManager.updatePassword(login.getText(), String.copyValueOf(potwhaslo.getPassword())))
					{
						showErrorMessage(frame, "Cos poszlo nie tak!!");
						return;
					}
					potwhaslo.setText("");
					potwhaslo1.setText("");
					login.setText("");
					haslo.setText("");
					lay.show(panel, "panellogowania");
				}
				else
					JOptionPane.showMessageDialog(frame, "Nie prawid�owa d�ugo�� has�a lub loginu", "B��d aktualizacji has�a!", JOptionPane.ERROR_MESSAGE);
					
		});
		
		btnNewButton.addActionListener((e)->{
				if(login.getText().length()>2 && haslo.getPassword().length>2 && login.getText().length()<51 && haslo.getPassword().length < 51 && DataBaseManager.AdminLoginManager.isPasswordGood(login.getText(),String.copyValueOf(haslo.getPassword())))
				{
					if(login.getText().equals("admin") && String.copyValueOf(haslo.getPassword()).equals("admin"))
					{
						JOptionPane.showMessageDialog(frame, "Zmie� Domy�lne has�o!", "Uwaga!", JOptionPane.WARNING_MESSAGE);
						lay.show(panel, "ZmianaHasla");
						return;
					}
					lay.show(panel, "PanelZarzadzania");
				}
				else
					JOptionPane.showMessageDialog(frame, "Nie prawid�owe has�o lub login!!!", "B��d logowania", JOptionPane.ERROR_MESSAGE);
		});
		
		btnNewButton_3.addActionListener((e)->{
				this.prepareSerwer();
				if(thread.isAlive())
					ser.setSleep(false);
				else
					thread.start();
				statusserwera.setText("<html>Status Serwera: <font color = 'green'>Aktywny</font></html>");
		});
		
		btnNewButton_4.addActionListener((e)->{
			    this.prepareSerwer();
				ser.setSleep(true);
				statusserwera.setText("<html>Status Serwera: <font color = 'red'>Nieaktywny</font></html>");
		});
		
		btnNewButton_5.addActionListener((e) ->
		{
			this.prepareSerwer();
			ser.setSleep(false);
			ser.stopWaiting();
			ser.waitToStop();
			ser.Close();
			thread = null;
			ser = null;
			statusserwera.setText("<html>Status Serwera: <font color = 'red'>Nieaktywny</font></html>");
			lay.show(panel, "panellogowania");
		});
		adduser.addActionListener((e)->
		{
			
			if(!simpleValidationUser())
				return;
			int NIP;
			try
			{
				NIP = Integer.parseInt(NIPu.getText());
			}
			catch(IllegalFormatException e1)
			{
				showErrorMessage(frame, "Nieprawid�owy format NIP'u");
				return;
			}
			if(nicku.getText().length() <3 || nicku.getText().length()>30)
			{
				showErrorMessage(frame, "Nick powinien mie� od 3 do 30 znak�w");
				return;
			}
			if(DataBaseManager.WorkerManager.isWorkerExist(nicku.getText()))
			{
				showErrorMessage(frame, "Podany nick istnieje w bazie danych!");
				return;
			}
			
			Office office = DataBaseManager.OfficeManager.getOffice(NIP);
			if(office == null)
			{
				showErrorMessage(frame, "Biuro podrozy o podanym nr nip nie istnieje!");
				return;
			}
			
			Adresy ad = new Adresy(countryu.getText(), cityu.getText(), homenumberu.getText(), postnumberu.getText(), streetu.getText());
			if(!DataBaseManager.AdresyManager.addAdresy(ad))
			{
				showErrorMessage(frame, "Co� posz�o nie tak!");
				return;
			}
			
			Worker wo = new Worker(office.getIdOffice(), ad.getIdAdresy(), nameu.getText(), surnameu.getText(), peselu.getText(), nicku.getText(), String.valueOf(passwordu.getPassword()));
			if(DataBaseManager.WorkerManager.addWorker(wo))
				showInfoMessage(frame, "Dodano poprawnie");
			else 
				showErrorMessage(frame, "Nie udalo sie dodac pracownika");
		});
		btnNewButton_9.addActionListener((e)->{
			if(!simpleValidationOffice())
				return;
			
			int NIP;
			try
			{
				NIP = Integer.parseInt(NIPb.getText());
			}
			catch(IllegalFormatException e1)
			{
				showErrorMessage(frame, "Nieprawid�owy format NIP'u");
				return;
			}
			
			if(DataBaseManager.OfficeManager.isExistOffice(NIP))
			{
				showErrorMessage(frame, "Taki nip juz istnieje w bazie danych!");	
				return;
			}
			
			Adresy ad = new Adresy(countryb.getText(), cityb.getText(), housenumberb.getText(), postcodeb.getText(), streetb.getText());
			if(!DataBaseManager.AdresyManager.addAdresy(ad))
			{
				showErrorMessage(frame, "Co� posz�o nie tak!");
				return;
			}
			
			Office of = new Office(ad.getIdAdresy(), nameb.getText(), NIP);
			if(DataBaseManager.OfficeManager.addOffice(of))
				showInfoMessage(frame, "Dodano poprawnie");
			else
				showErrorMessage(frame, "Co� posz�o nie tak!");
			
		});
		btnNewButton_23.addActionListener((e)->{
			if(!simpleValidationOffice())
				return;
			int NIP;
			try
			{
				NIP = Integer.parseInt(NIPb.getText());
			}
			catch(IllegalFormatException e1)
			{
				showErrorMessage(frame, "Nieprawid�owy format NIP'u");
				return;
			}
			Office of = DataBaseManager.OfficeManager.getOffice(NIP);
			if(of==null)
			{
				showErrorMessage(frame, "Biuro o podanym nip nie istnieje");
				return;
			}
			Adresy ad = DataBaseManager.AdresyManager.getAdresy(of.getIdAddres());
			if(ad == null)
			{
				showErrorMessage(frame, "Wyst�pi� b��d ");
				return;
			}
			of.setName(nameb.getText());
			ad.setCity(cityb.getText());
			ad.setCountry(countryb.getText());
			ad.setHomenumber(housenumberb.getText());
			ad.setPostdode(postcodeb.getText());
			ad.setStreet(streetb.getText());
			if(DataBaseManager.OfficeManager.updateOffice(of) && DataBaseManager.AdresyManager.updateAdresy(ad))
				showInfoMessage(frame, "Dane zosta�y poprawnie zmodyfikowane");
			else
				showErrorMessage(frame, "Wyst�pi� b��d!!!");
		});
		
		btnNewButton_14.addActionListener((e)->{
			int liczba;
			try {
				
				liczba = Integer.parseInt(NIPb.getText());
			}
			catch(NumberFormatException en)
			{
				showErrorMessage(frame, "Nie poprawny format NIP");
				return;
			}
			Office office = DataBaseManager.OfficeManager.getOffice(liczba);
			Adresy ad = null;
			if(office == null)
			{
				showErrorMessage(frame, "Wyst�pi� b��d pobierania informacji");
				return;
			}
			ad = DataBaseManager.AdresyManager.getAdresy(office.getIdAddres());
			if(ad==null)
			{
				showErrorMessage(frame, "Wyst�pi� b��d pobierania informacji");
				return;
			}

			cityb.setText(ad.getCity());
			countryb.setText(ad.getCountry());
			nameb.setText(office.getName());
			housenumberb.setText(ad.getHomenumber());
			postcodeb.setText(ad.getPostdode());
			streetb.setText(ad.getStreet());
			showInfoMessage(frame, "Pobrano dane pomy�lnie!");
		});
		btnNewButton_13.addActionListener((e)->{
			if(nicku.getText().length() <3 || nicku.getText().length()>30)
			{
				showErrorMessage(frame, "Nick powinien mie� od 3 do 30 znak�w");
				return;
			}
			
			Worker user = DataBaseManager.WorkerManager.getWorker(nicku.getText());
			if(user==null)
			{
				showInfoMessage(frame, "Could not find user!");
				return;
			}
			Office of = DataBaseManager.OfficeManager.getOfficeById(user.getIdOffice());
			if(of == null)
			{
				showInfoMessage(frame, "Could not find user office!");
				return;
			}
			Adresy ad = DataBaseManager.AdresyManager.getAdresy(user.getIdAdres());
			if(ad == null)
			{
				showInfoMessage(frame, "Could not find user adres!");
				return;
			}
			clearUserFields();
			NIPu.setText(Integer.toString(of.getNIP()));
			nicku.setText(user.getNick());
			nameu.setText(user.getName());
			surnameu.setText(user.getSurname());
			cityu.setText(ad.getCity());
			streetu.setText(ad.getStreet());
			postnumberu.setText(ad.getPostdode());
			homenumberu.setText(ad.getHomenumber());
			peselu.setText(user.getPesel());
			countryu.setText(ad.getCountry());
		});
		btnNewButton_1.addActionListener((e)->
		{
			clearUserFields();
			lay.show(panel, "Uzytkownik");
		});
		btnNewButton_6.addActionListener((e)->{
			lay.show(panel, "DodajModyfikujUzytkownika");
		});
		btnNewButton_8.addActionListener((e)->
		{
			lay.show(panel, "Biuro");
		});
		
		btnNewButton_18.addActionListener((e)->{
			lay.show(panel, "DodajModyfikujBiuro");
		});
		
		btnNewButton_21.addActionListener((e)->{
			lay.show(panel, "PanelZarzadzania");
	    });
		
		btnNewButton_15.addActionListener((e)->{
			lay.show(panel, "PanelZarzadzania");
		});
		
		btnNewButton_10.addActionListener((e)->{
			clearOfficeFields();
			lay.show(panel, "Biuro");
		});
		btnNewButton_26.addActionListener((e)->{
			clearUserFields();
			lay.show(panel, "Uzytkownik");
		});
		
		btnNewButton_19.addActionListener((e)->{
			lay.show(panel,"UsunBiuro");
		});
		btnNewButton_22.addActionListener((e)->{
			lay.show(panel, "Biuro");
		});
		btnNewButton_2.addActionListener((e)->{
			clearOfficeFields();
		});
		btnNewButton_7.addActionListener((e)->{
			clearUserFields();
		});
		btnNewButton_11.addActionListener((e)->{
			int liczba;
			try {
				
				liczba = Integer.parseInt(NIPbu.getText());
			}
			catch(NumberFormatException en)
			{
				showErrorMessage(frame, "Nie poprawny format NIP");
				return;
			}

			if(DataBaseManager.OfficeManager.removeOfficeByNip(liczba))
				showInfoMessage(frame, "Usuni�to biuro pomy�lnie!");
			else
				showErrorMessage(frame, "Podany NIP nie istnieje w bazie danych!");
		});
		btnNewButton_27.addActionListener((e)->{
			if(!simpleValidationUser())
				return;
			int NIP;
			try
			{
				NIP = Integer.parseInt(NIPu.getText());
			}
			catch(IllegalFormatException e1)
			{
				showErrorMessage(frame, "Nieprawid�owy format NIP'u");
				return;
			}
			Worker wo = DataBaseManager.WorkerManager.getWorker(nicku.getText());
			if(wo == null)
			{
				showErrorMessage(frame, "B��d!");
				return;
			}
			Office of = DataBaseManager.OfficeManager.getOffice(NIP);
			Adresy ad = null;
			if(of != null)
			{
				of.setName(nameu.getText());
				ad = DataBaseManager.AdresyManager.getAdresy(of.getIdAddres());
				if(ad != null)
				{
					ad.setCity(cityu.getText());
					ad.setCountry(countryu.getText());
					ad.setHomenumber(homenumberu.getText());
					ad.setPostdode(postnumberu.getText());
					ad.setStreet(streetu.getText());
				}
			}
			else
				showErrorMessage(frame, "Biuro o podanym adresie NIP nie istnieje");
			
			wo.setIdOffice(of.getIdOffice());
			wo.setName(nameu.getText());
			wo.setNick(nicku.getText());
			wo.setPassword(String.valueOf(passwordu.getPassword()));
			wo.setPesel(peselu.getText());
			wo.setSurname(surnameu.getText());
			
			if(of != null && ad != null && DataBaseManager.AdresyManager.updateAdresy(ad) && DataBaseManager.WorkerManager.updateWorker(wo))
				showInfoMessage(frame, "Zmodyfikowano pomy�lnie!");
			else
				showErrorMessage(frame, "Co� pocz�o nie tak!");
		});
		retfromuserdel.addActionListener((e)->{
			usertodel.setText("");
			lay.show(panel, "Uzytkownik");
		});
		deletesUser.addActionListener((e)->{
			
			String string = usertodel.getText();
			if(string==null ||  string.length() < 4 && string.length() > 30)
			{
				showErrorMessage(frame, "Nick powinien mie� od 3 do 30 znak�w");
				return;
			}
			
			if(!this.checkStringForSql(string))
			{
				showErrorMessage(frame, "You can not use % _");
				return;
			}
		
			if(!ClientOperator.isCanDelete(string) && JOptionPane.showConfirmDialog(frame, "Czy chcesz usun�� pracownika, kt�ry wci�� u�ywa systemu?")==JOptionPane.NO_OPTION)
				return;
			
			
			Worker wo = DataBaseManager.WorkerManager.getWorker(string);
			if(wo==null)
			{
				showErrorMessage(frame, "Podany pracownika nie istnieje!");
				return;
			}
			ClientOperator.removeWorker(string);
			if(DataBaseManager.WorkerManager.removeWorker(wo.getIdWorker()))
				showInfoMessage(frame, "Usunieto pracownika pomyslnie");
			else
				showErrorMessage(frame, "Nie udalo sie usunac pracownika");
	
		});
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lay.show(panel, "UsunUzytkownik");
			}
		});
		if(DataBaseManager.isConnected())
			status.setText("<html> Status bazy danych: <font color = 'green'>Po��czono</font></html> ");
		else
			status.setText("<html> Status bazy danych: <font color = 'red'>Nie po��czono!</font></html> ");
	}
	/**
	 * Podstawowa walidacja u�ytkownika
	 * @return zwraca true je�eli wszystko jest dobrze
	 */
	private boolean simpleValidationUser()
	{
		if(nicku.getText().length()<4 || nicku.getText().length()>30)
		{
			showErrorMessage(frame,"Nick musi mie� d�ugo�� od 4 do 30 znak�w!");
			return false;
		}
		if(passwordu.getPassword().length < 4 || passwordu.getPassword().length > 30)
		{
			showErrorMessage(frame, "Has�o musi mie� od 4 do 30 znak�w!");
			return false;
		}
		if(!String.valueOf(passwordu.getPassword()).equals(String.valueOf(password1u.getPassword())))
		{
			showErrorMessage(frame,"Has�o musi by� takie same!");
			return false;
		}
		if(countryu.getText().length() < 2 || countryu.getText().length()>30)
		{
			showErrorMessage(frame, "Nazwa kraju musi mie� d�ugo�� od 2 do 30 znak�w");
			return false;
		}
		if(cityu.getText().length() < 2 || cityu.getText().length()>30)
		{
			showErrorMessage(frame,"Nazwa miasta musi mie� d�ugo�� od 2 do 30 znak�w");
			return false;
		}
		if(streetu.getText().length() < 2 || streetu.getText().length() > 30)
		{
			showErrorMessage(frame, "Nazwa ulicy musi mie� d�ugo�� od 2 do 30 znak�w");
			return false;
		}
		if(homenumberu.getText().length() < 1 || streetu.getText().length() > 30)
		{
			showErrorMessage(frame, "Nieprawid�owy numer domu!");
			return false;
		}
		if(postnumberu.getText().length() < 1 || streetu.getText().length() > 30)
		{
			showErrorMessage(frame, "Nieprawid�owy kod pocztowy!");
			return false;
		}
		return true;
	}
	/**
	 * Podstawowa walidacja biura
	 * @return zwraca true je�eli wszystko jest dobrze
	 */
	private boolean simpleValidationOffice()
	{
		if(nameb.getText().length()<3 || nameb.getText().length()>30)
		{
			showErrorMessage(frame, "Nazwa powinna mie� od 3 do 30 znak�w");
			return false;
		}
		if(NIPb.getText().length()<3 || NIPb.getText().length()>30)
		{
			showErrorMessage(frame, "NIP powinien mie� od 3 do 30 znak�w");
			return false;
		}
		if(streetb.getText().length()<3 || streetb.getText().length()>30)
		{
			showErrorMessage(frame, "Nazwa ulicy powinna mie� od 3 do 30 znak�w");
			return false;
		}
		if(countryb.getText().length()<3 || countryb.getText().length()>30)
		{
			showErrorMessage(frame, "Nazwa pa�stwa powinna mie� od 3 do 30 znak�w");
			return false;
		}
		if(cityb.getText().length()<3 || cityb.getText().length()>30)
		{
			showErrorMessage(frame, "Nazwa miasta powinna mie� od 3 do 30 znak�w");
			return false;
		}
		if(housenumberb.getText().length()<3 || housenumberb.getText().length()>30)
		{
			showErrorMessage(frame, "Numer domu powinien mie� od 3 do 30 znak�w");
			return false;
		}
		if(postcodeb.getText().length() < 3 || postcodeb.getText().length() > 30)
		{
			showErrorMessage(frame, "Kod pocztowy powinien mie� od 3 do 30 znak�w");
			return false;
		}
		return true;
	}
	/**
	 * Sprawdza czy mo�na u�y� tekstu w zapytaniui
	 * @param buff s�owo
	 * @return zwraca true je�eli mo�na
	 */

	private boolean checkStringForSql(@NotNull String buff)
	{
		int length = buff.length();
		for(int i = 0; i < length; ++i)
			if(buff.charAt(i) == '%' || buff.charAt(i) == '_')
				return false;
			
		return length!=0;
	}
	/**
	 * Czy�ci wszystkie pola biura
	 */
	private void clearOfficeFields()
	{
		nameb.setText("");
		NIPb.setText("");
		streetb.setText("");
		countryb.setText("");
		cityb.setText("");
		housenumberb.setText("");
		postcodeb.setText("");	
	}
	/**
	 * Czy�ci wszystkie pola pracownika
	 */
	private void clearUserFields()
	{
		nicku.setText("");
		passwordu.setText("");
		password1u.setText("");
		nameu.setText("");
		NIPu.setText("");
		streetu.setText("");
		countryu.setText("");
		cityu.setText("");
		homenumberu.setText("");
		postnumberu.setText("");
		nameu.setText("");
		surnameu.setText("");
		peselu.setText("");
	}
	/**
	 * Przygotowuje serwer
	 */
	private void prepareSerwer()
	{
	
		if(thread == null)
		{
			try {
				ser = new Serwer(4021);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			thread = new Thread(()->
				{
					try {
						ser.activeWaiting();
					} catch (IOException e1) {
						logger.error("ERROR!", e1);
					}
				}
			);
		}
	}
	/**
	 * Pokazuje wiadomo�� b��du
	 * @param frame 
	 * @param text tekst b��du
	 */
	public static void showErrorMessage(@NotNull JFrame frame,@NotNull String text)
	{
		JOptionPane.showMessageDialog(frame, text,"B��d", JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Pokazuje informacje
	 * @param frame
	 * @param text informacja
	 */
	private static void showInfoMessage(@NotNull JFrame frame,@NotNull String text)
	{
		JOptionPane.showMessageDialog(frame, text,"Komunikat", JOptionPane.INFORMATION_MESSAGE);
	}
}
