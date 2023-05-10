import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Query3 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Query3 frame = new Query3();
					frame.setVisible(true);	// afisare interfata
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conexiune = null;
	/**
	 * Create the frame.
	 */
	public Query3() {
		conexiune = Connect.conexiuneBD();	// conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Afisati numele tuturor actorilor care au jucat in filme intr-o perioada specificata si numele filmelor</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 395, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblDataInceput = new JLabel("Data inceput");
		lblDataInceput.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataInceput.setBounds(10, 64, 81, 14);
		contentPane.add(lblDataInceput);
		
		textField = new JTextField();
		textField.setBounds(133, 61, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDataSfarsit = new JLabel("Data sfarsit");
		lblDataSfarsit.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDataSfarsit.setBounds(10, 101, 81, 14);
		contentPane.add(lblDataSfarsit);
		
		textField_1 = new JTextField();
		textField_1.setBounds(133, 98, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 126, 395, 125);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSearch = new JButton("Search");	// buton care permite executarea cererii dupa introducerea variabilelor dupa care se face cautarea datelor
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT a.actor_nume, a.actor_prenume, f.film_titlu, f.film_an FROM Film_actori fa INNER JOIN Film f ON f.film_id = fa.film_id INNER JOIN Actor a ON a.actor_id = fa.actor_id WHERE f.film_an BETWEEN ? AND ?";	// declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					pst.setString(1, textField.getText());	// cast in String pentru valoarile din campuri
					pst.setString(2, textField_1.getText());	
					ResultSet rs = pst.executeQuery(); 			//  sursa precompilata (cererea) este lansata in executie
					table.setModel(DbUtils.resultSetToTableModel(rs));	// afisare rezultat cerere sub forma de tabel
					pst.close();
					rs.close();

			}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearch.setBounds(293, 60, 112, 23);
		contentPane.add(btnSearch);
		
		JButton btnNewButton = new JButton("Back to menu");		// redirectionare catre interfata initiala (posterioara celei de logare)
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Interfata object = new Interfata();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(293, 97, 112, 23);
		contentPane.add(btnNewButton);
	}

}
