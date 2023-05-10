import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Query4 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	Connection conexiune = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Query4 frame = new Query4();
					frame.setVisible(true);			// afisare interfata
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Query4() {
		conexiune = Connect.conexiuneBD();	// conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 464, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Afisati numele si prenumele regizorilor pentru care suma duratelor filmelor pe care le regizeaza este mai mare decat un anumit numar de minute</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 414, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblDurataFilme = new JLabel("Durata filme");
		lblDurataFilme.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDurataFilme.setBounds(10, 78, 81, 14);
		contentPane.add(lblDurataFilme);
		
		textField = new JTextField();
		textField.setBounds(101, 75, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");	// buton care permite executarea cererii dupa introducerea variabilelor dupa care se face cautarea datelor
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT r.regizor_nume, r.regizor_prenume, SUM(f.film_durata) AS Durata_Filme FROM Film f INNER JOIN Regizor r ON f.regizor_id = r.regizor_id GROUP BY r.regizor_nume, r.regizor_prenume HAVING SUM(f.film_durata) > ? ORDER BY SUM(f.film_durata) DESC";   // declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					pst.setString(1, textField.getText());	// cast in String pentru valoarea din camp
					ResultSet rs = pst.executeQuery(); 	//  sursa precompilata (cererea) este lansata in executie
					table.setModel(DbUtils.resultSetToTableModel(rs));	// afisare rezultat cerere sub forma de tabel
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(217, 74, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnBackToMenu = new JButton("Back to menu");	// redirectionare catre interfata initiala (posterioara celei de logare)
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Interfata object = new Interfata();
				object.setVisible(true);
				dispose();
			}
		});
		btnBackToMenu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBackToMenu.setBounds(316, 74, 118, 23);
		contentPane.add(btnBackToMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 428, 148);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
}
