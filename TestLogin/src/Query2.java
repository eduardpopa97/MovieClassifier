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

public class Query2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JTable table;
	Connection conexiune = null;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Query2 frame = new Query2();
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
	public Query2() {
		conexiune = Connect.conexiuneBD();		// conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 450, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html>Afisati toate filmele in care a jucat un anumit actor sau care au fost regizate de un anumit regizor</html>");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 393, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblActorNume = new JLabel("Actor nume");
		lblActorNume.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblActorNume.setBounds(20, 60, 66, 14);
		contentPane.add(lblActorNume);
		
		JLabel lblRegizor = new JLabel("Regizor");
		lblRegizor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRegizor.setBounds(20, 135, 46, 14);
		contentPane.add(lblRegizor);
		
		textField = new JTextField();
		textField.setBounds(117, 57, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(117, 132, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSearch = new JButton("Search");		// buton care permite executarea cererii dupa introducerea variabilelor dupa care se face cautarea datelor
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT f.film_titlu FROM Film_actori fa INNER JOIN Film f ON f.film_id = fa.film_id INNER JOIN Actor a ON a.actor_id = fa.actor_id WHERE a.actor_nume = ? AND a.actor_prenume = ? UNION SELECT f.film_titlu FROM Film f INNER JOIN Regizor r ON f.regizor_id = r.regizor_id WHERE r.regizor_nume = ?";	// declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					pst.setString(1, textField.getText());	// cast in String pentru valoarile din campuri
					pst.setString(2, textField_1.getText());
					pst.setString(3, textField_2.getText());
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
		btnSearch.setBounds(267, 67, 121, 23);
		contentPane.add(btnSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 160, 393, 145);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblActorPrenume = new JLabel("Actor prenume");
		lblActorPrenume.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblActorPrenume.setBounds(20, 98, 86, 14);
		contentPane.add(lblActorPrenume);
		
		textField_1 = new JTextField();
		textField_1.setBounds(117, 95, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Back to menu");	// redirectionare catre interfata initiala (posterioara celei de logare)
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Interfata object = new Interfata();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(267, 113, 121, 23);
		contentPane.add(btnNewButton);
	}
}
