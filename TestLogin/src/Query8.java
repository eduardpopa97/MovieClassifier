import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Query8 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	Connection conexiune = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Query8 frame = new Query8();
					frame.setVisible(true);		// afisare interfata
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Query8() {
		conexiune = Connect.conexiuneBD();		// conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Gasiti filmele care au o medie a notelor acordate de utilizatori mai mare decat media tuturor notelor acordate pentru toate filmele<html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 401, 28);
		contentPane.add(lblNewLabel);
		
		JButton btnSearch = new JButton("Search");		// buton care permite executarea cererii dupa introducerea variabilelor dupa care se face cautarea datelor
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT f.film_titlu, AVG(r.review_nota) AS MedieReview, (SELECT AVG(r.review_nota) FROM Review r) AS MedieTotal FROM Film f INNER JOIN Review r ON f.film_id = r.film_id GROUP BY f.film_titlu  HAVING AVG(r.review_nota) > (SELECT AVG(r.review_nota) FROM Review r)";	// declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea 
					PreparedStatement pst = conexiune.prepareStatement(query); // creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					ResultSet rs = pst.executeQuery();  //  sursa precompilata (cererea) este lansata in executie
					table.setModel(DbUtils.resultSetToTableModel(rs)); // afisare rezultat cerere sub forma de tabel
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
					}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(56, 50, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnNewButton = new JButton("Back to menu");  // redirectionare catre interfata initiala (posterioara celei de logare)
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Interfata object = new Interfata();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(236, 50, 116, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 414, 163);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
