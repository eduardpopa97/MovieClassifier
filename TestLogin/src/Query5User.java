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

public class Query5User extends JFrame {

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
					Query5User frame = new Query5User();
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
	public Query5User() {
		conexiune = Connect.conexiuneBD(); // conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 459, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Afisati anii in care s-au regizat cel putin un film si care au primit cel putin o nota mai mare sau egala cu o anumita valoare</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 402, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNotaAcordata = new JLabel("Nota acordata");
		lblNotaAcordata.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNotaAcordata.setBounds(10, 72, 85, 14);
		contentPane.add(lblNotaAcordata);
		
		textField = new JTextField();
		textField.setBounds(108, 69, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");		// buton care permite executarea cererii dupa introducerea variabilelor dupa care se face cautarea datelor
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT DISTINCT f.film_an FROM Film f INNER JOIN Review r ON f.film_id = r.film_id GROUP BY f.film_an, r.review_nota HAVING COUNT(*)>=1 AND r.review_nota >=?"; // declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);		// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					pst.setString(1, textField.getText());	// cast in String pentru valoarea din camp
					ResultSet rs = pst.executeQuery(); 		//  sursa precompilata (cererea) este lansata in executie
					table.setModel(DbUtils.resultSetToTableModel(rs));	// afisare rezultat cerere sub forma de tabel
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(216, 68, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnBackToMenu = new JButton("Back to menu");		// redirectionare catre interfata initiala (posterioara celei de logare)
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				InterfataUser object = new InterfataUser();
				object.setVisible(true);
				dispose();
			}
		});
		btnBackToMenu.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBackToMenu.setBounds(315, 68, 118, 23);
		contentPane.add(btnBackToMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 423, 154);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
	}

}
