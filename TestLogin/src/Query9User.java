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

public class Query9User extends JFrame {

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
					Query9User frame = new Query9User();
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
	public Query9User() {
		conexiune = Connect.conexiuneBD(); // conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Gasiti filmele care au fost evaluate de toti in afara de un anumit utilizator pe care il specificati</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 399, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblUtilizator = new JLabel("Utilizator");
		lblUtilizator.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUtilizator.setBounds(10, 69, 59, 14);
		contentPane.add(lblUtilizator);
		
		textField = new JTextField();
		textField.setBounds(79, 66, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");		// buton care permite executarea cererii dupa introducerea variabilelor dupa care se face cautarea datelor
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT f.film_titlu\r\n" + 
							"FROM Film f\r\n" + 
							"WHERE f.film_id IN \r\n" + 
							"(SELECT r.film_id \r\n" + 
							"			 FROM Review r\r\n" + 
							"			 WHERE r.user_id NOT IN  \r\n" + 
							"                                                           (SELECT u.user_id\r\n" + 
							"						  FROM [User] u \r\n" + 
							"					           WHERE u.username =?)) \r\n" + 
							"";		// declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
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
		btnSearch.setBounds(175, 65, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnNewButton = new JButton("Back to menu");	// redirectionare catre interfata initiala (posterioara celei de logare)
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				InterfataUser object = new InterfataUser();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(294, 65, 115, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 102, 414, 149);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
