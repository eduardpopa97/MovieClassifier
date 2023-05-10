import java.awt.Component;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;



public class ExecuteSQLQueryUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExecuteSQLQueryUser frame = new ExecuteSQLQueryUser();
				    frame.setVisible(true);					// afisare interfata executare cereri   
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	Connection conexiune = null;
	
	public ExecuteSQLQueryUser() {
		conexiune = Connect.conexiuneBD();				// conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 470, 747);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPutYourQuery = new JLabel("Select your query");	// creare eticheta (titlul interfetei)
		lblPutYourQuery.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPutYourQuery.setBounds(152, 11, 165, 22);
		contentPane.add(lblPutYourQuery);
		
		JButton btnNewButton = new JButton("Exit");				// addActionListener este folosit pentru a atribui functionalitate unui buton
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component frmLoginCheck = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginCheck, "Confirm if you want to exit", "Login Check",   // afisare meniu in care aleg daca doresc sau nu sa ies din aplicatie
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
				System.exit(0);									// iesire aplicatie
			}
		}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(88, 664, 112, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back to menu");
		btnNewButton_1.addActionListener(new ActionListener() {		// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				InterfataUser object = new InterfataUser();			// ma intorc la interfata de unde pot sa selectez de la ce tabel sa vizualizez date
				object.setVisible(true);							
				dispose();											// inchidere fereastra anterioara
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(248, 664, 112, 23);
		contentPane.add(btnNewButton_1);
		
		// AVEM 10 BUTOANE DE TIP QUERY. FIECARE BUTON IMI DESCHIDE O INTERFATA DE UNDE POT SA EXECUT O ANUMITA CERERE
		// (ENUNTUL CERINTEI ESTE SCRIS IN STANGA BUTONULUI RESPECTIV, DAR SI IN INTERFATA CARE SE VA DESCHIDE) IN FUNCTIE
		// DE UNA SAU MAI MULTE VARIABILE CARE VOR FI INTRODUSE IN NISTE CAMPURI. CAND APAS BUTONUL QUERY SE VA DESCHIDE
		// INTERFATA RESPECTIVA CERERII, IAR CEA ANTERIOARA SE VA INCHIDE
		
		
		JButton btnNewButton_2 = new JButton("Query 1");			
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Query1User object = new Query1User();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(316, 71, 112, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Query 2");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Query2User object = new Query2User();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(316, 127, 112, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnQuery = new JButton("Query 3");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Query3User object = new Query3User();
				object.setVisible(true);
				dispose();
			}
		});
		btnQuery.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery.setBounds(316, 182, 112, 23);
		contentPane.add(btnQuery);
		
		JButton btnNewButton_4 = new JButton("Query 4");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Query4User object = new Query4User();
			object.setVisible(true);
			dispose();
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBounds(316, 241, 112, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Query 5");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Query5User object = new Query5User();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_5.setBounds(316, 304, 112, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnQuery_1 = new JButton("Query 6");
		btnQuery_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Query6User object = new Query6User();
				object.setVisible(true);
				dispose();
			}
		});
		btnQuery_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery_1.setBounds(316, 359, 112, 23);
		contentPane.add(btnQuery_1);
		
		JButton btnQuery_2 = new JButton("Query 7");
		btnQuery_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Query7User object = new Query7User();
				object.setVisible(true);
				dispose();
			}
		});
		btnQuery_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery_2.setBounds(316, 433, 112, 23);
		contentPane.add(btnQuery_2);
		
		JButton btnQuery_3 = new JButton("Query 8");
		btnQuery_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Query8User object = new Query8User();
				object.setVisible(true);
				dispose();
			}
		});
		btnQuery_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery_3.setBounds(316, 489, 112, 23);
		contentPane.add(btnQuery_3);
		
		JButton btnQuery_4 = new JButton("Query 9");
		btnQuery_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Query9User object = new Query9User();
				object.setVisible(true);
				dispose();
			}
		});
		btnQuery_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery_4.setBounds(316, 543, 112, 23);
		contentPane.add(btnQuery_4);
		
		JButton btnQuery_5 = new JButton("Query 10");
		btnQuery_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Query10User object = new Query10User();
				object.setVisible(true);
				dispose();
			}
		});
		btnQuery_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnQuery_5.setBounds(316, 599, 112, 23);
		contentPane.add(btnQuery_5);
		
		JLabel lblNewLabel = new JLabel("INTEROGARI SIMPLE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 42, 125, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblInterogariComplecse = new JLabel("INTEROGARI COMPLEXE");
		lblInterogariComplecse.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblInterogariComplecse.setBounds(10, 409, 140, 14);
		contentPane.add(lblInterogariComplecse);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Afisati filmele care au primit cel putin o anumita nota la review-uri si user-ul care a acordat nota respectiva alaturi de comentariul sau<html>");
		lblNewLabel_1.setBounds(10, 63, 296, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblafisatiToateFilmele = new JLabel("<html>Afisati toate filmele in care a jucat un anumit actor sau care au fost regizate de un anumit regizor</html>");
		lblafisatiToateFilmele.setBounds(10, 128, 296, 35);
		contentPane.add(lblafisatiToateFilmele);
		
		JLabel lblafisatiNumeleTuturor = new JLabel("<html>Afisati numele tuturor actorilor care au jucat in filme intr-o perioada specificata si numele filmelor</html>");
		lblafisatiNumeleTuturor.setBounds(10, 183, 296, 35);
		contentPane.add(lblafisatiNumeleTuturor);
		
		JLabel lblafisatiNumeleSi = new JLabel("<html>Afisati numele si prenumele regizorilor pentru care suma duratelor filmelor pe care le regizeaza este mai mare decat un anumit numar de minute</html>");
		lblafisatiNumeleSi.setBounds(10, 229, 296, 49);
		contentPane.add(lblafisatiNumeleSi);
		
		JLabel lblafisatiAniiIn = new JLabel("<html>Afisati anii in care s-au regizat cel putin un film si care au primit cel putin o nota mai mare sau egala cu o anumita valoare</html>");
		lblafisatiAniiIn.setBounds(10, 294, 296, 51);
		contentPane.add(lblafisatiAniiIn);
		
		JLabel lblscrietiNumeleUtilizatorilor = new JLabel("<html>Scrieti numele utilizatorilor care au evaluat un anumit film</html>");
		lblscrietiNumeleUtilizatorilor.setBounds(10, 356, 281, 29);
		contentPane.add(lblscrietiNumeleUtilizatorilor);
		
		JLabel lblpentruUnFilm = new JLabel("<html>Pentru un film pe care il selectati determinati numarul de actori care joaca in el</html>");
		lblpentruUnFilm.setBounds(10, 434, 296, 35);
		contentPane.add(lblpentruUnFilm);
		
		JLabel lblgasitiFilmeleCare = new JLabel("<html>Gasiti filmele care au o medie a notelor acordate de utilizatori mai mare decat media tuturor notelor acordate pentru toate filmele<html>");
		lblgasitiFilmeleCare.setBounds(10, 480, 281, 48);
		contentPane.add(lblgasitiFilmeleCare);
		
		JLabel lblgasitiFilmeleCare_1 = new JLabel("<html>Gasiti filmele care au fost evaluate de toti in afara de un anumit utilizator pe care il specificati</html>");
		lblgasitiFilmeleCare_1.setBounds(10, 543, 281, 34);
		contentPane.add(lblgasitiFilmeleCare_1);
		
		JLabel lblscrietiNumeleFilmelor = new JLabel("<html>Scrieti numele filmelor in care unul sau mai multi actori au jucat in cel putin un numar specificat de filme<html>");
		lblscrietiNumeleFilmelor.setBounds(10, 591, 281, 42);
		contentPane.add(lblscrietiNumeleFilmelor);
		
}
}
