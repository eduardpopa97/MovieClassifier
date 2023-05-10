import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Query7 extends JFrame {

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
					Query7 frame = new Query7();
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
	public Query7() {
		conexiune = Connect.conexiuneBD();		// conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Pentru un film pe care il selectati determinati numarul de actori care joaca in el</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 402, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNumeFilm = new JLabel("Nume film ");
		lblNumeFilm.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumeFilm.setBounds(10, 71, 59, 14);
		contentPane.add(lblNumeFilm);
		
		textField = new JTextField();
		textField.setBounds(78, 68, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSearc = new JButton("Search");		// buton care permite executarea cererii dupa introducerea variabilelor dupa care se face cautarea datelor
		btnSearc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT f.film_titlu, f.film_id, COUNT(*) AS NrActori\r\n" + 
							"FROM Film_actori fa INNER JOIN Film f\r\n" + 
							"						ON f.film_id = fa.film_id\r\n" + 
							"					INNER JOIN Actor a\r\n" + 
							"						ON a.actor_id = fa.actor_id\r\n" + 
							"WHERE f.film_id IN (SELECT fa.film_id FROM Film_actori fa WHERE a.actor_id \r\n" + 
							"			IN (SELECT a.actor_id FROM Actor a WHERE EXISTS (SELECT a.actor_id FROM Actor a WHERE a.actor_id = fa.actor_id))) AND f.film_titlu = ?\r\n" + 
							"GROUP BY f.film_titlu, f.film_id\r\n" + 
							"";		// declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					pst.setString(1, textField.getText());		// cast in String pentru valoarea din camp
					ResultSet rs = pst.executeQuery(); 	//  sursa precompilata (cererea) este lansata in executie
					table.setModel(DbUtils.resultSetToTableModel(rs));	//  sursa precompilata (cererea) este lansata in executie
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnSearc.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSearc.setBounds(184, 67, 89, 23);
		contentPane.add(btnSearc);
		
		JButton btnNewButton = new JButton("Back to menu");   // redirectionare catre interfata initiala (posterioara celei de logare)
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Interfata object = new Interfata();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(299, 67, 113, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 103, 414, 148);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
