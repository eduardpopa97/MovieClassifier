import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class ModifReview extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboBoxName;

	public void refreshTable() {		// functia refreshTable are rolul de a da refresh la tabela imediat dupa ce are loc o modificare (update, insert, delete) ca sa nu mai fiu nevoit sa apas LoadReview
		try {
			String query = "SELECT * FROM Review";	// afisare tabela Review
			PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
			ResultSet rs = pst.executeQuery(); 							// sursa precompilata (cererea) este lansata in executie
			table.setModel(DbUtils.resultSetToTableModel(rs));			 // afisare rezultate cerere sub forma de tabela
			pst.close();												 // eliberare spatiu memorie
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fillComboBox() {	// din butonul JcomboBox pot sa selectez id-ul pentru review
		try {						// cand am selectat o valoare => campurile (dupa care doresc sa fac un update/delete) sunt setate la valorile corespunzatoare inregistrarii in care se gaseste id-ul pentru review	
			String query = "SELECT * FROM Review";		
			PreparedStatement pst = conexiune.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				comboBoxName.addItem(rs.getString("review_id"));
			}
		    }
		catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModifReview frame = new ModifReview();
					frame.setVisible(true);				// afisare interfata ModifReview
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conexiune = null;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;			// definire campuri 
	
	/**
	 * Create the frame.
	 */
	public ModifReview() {
		conexiune = Connect.conexiuneBD();	// conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 924, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BtnLoad = new JButton("LoadReview");
		BtnLoad.setFont(new Font("Tahoma", Font.BOLD, 11));
		BtnLoad.addActionListener(new ActionListener() {		// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT * FROM Review";		// afisez tot continutul tabelei Review
					PreparedStatement pst = conexiune.prepareStatement(query);  // creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					ResultSet rs = pst.executeQuery(); 							//  sursa precompilata (cererea) este lansata in executie
					table.setModel(DbUtils.resultSetToTableModel(rs));			// afisare rezultat cerere in tabela tabel
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		BtnLoad.setBounds(223, 11, 108, 23);
		contentPane.add(BtnLoad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(201, 84, 697, 247);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("ReviewID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 20, 70, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(105, 17, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFilmId = new JLabel("FilmID");
		lblFilmId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFilmId.setBounds(10, 57, 70, 14);
		contentPane.add(lblFilmId);
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 54, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblComentariu = new JLabel("Comentariu");
		lblComentariu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblComentariu.setBounds(10, 94, 86, 14);
		contentPane.add(lblComentariu);
		
		textField_2 = new JTextField();
		textField_2.setBounds(105, 91, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblUserid = new JLabel("UserID");
		lblUserid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserid.setBounds(10, 137, 59, 14);
		contentPane.add(lblUserid);
		
		textField_3 = new JTextField();
		textField_3.setBounds(105, 134, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ReviewNota");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 179, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(105, 176, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblOracomentariu = new JLabel("OraComentariu");
		lblOracomentariu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOracomentariu.setBounds(10, 221, 97, 14);
		contentPane.add(lblOracomentariu);
		
		textField_5 = new JTextField();
		textField_5.setBounds(105, 218, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");			// buton pentru insert
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "INSERT INTO Review (review_id, film_id, comentariu, user_id, review_nota, ora_comentariu) VALUES (?,?,?,?,?,?)";	// declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					pst.setString(1,textField.getText());			// cast in String pentru valorile din cele 6 campuri din interfata 
					pst.setString(2,textField_1.getText());
					pst.setString(3,textField_2.getText());
					pst.setString(4,textField_3.getText());
					pst.setString(5,textField_4.getText());
					pst.setString(6,textField_5.getText());
					
					pst.execute(); 			//  sursa precompilata (cererea) este lansata in executie
					
					JOptionPane.showMessageDialog(null, "Data Saved");		 // afisare mesaj care valideaza inserarea inregistrarii
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable(); // refresh la tabela
			}
		});
		btnNewButton.setBounds(223, 50, 108, 23);
		contentPane.add(btnNewButton);
			
		JButton btnNewButton_1 = new JButton("Update");		// buton pentru update
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "UPDATE Review SET review_id = '"+textField.getText()+"', film_id = '"+textField_1.getText()+"', comentariu = '"+textField_2.getText()+"', user_id = '"+textField_3.getText()+"', review_nota = '"+textField_4.getText()+"', ora_comentariu = '"+textField_5.getText()+"' WHERE review_id = '"+textField.getText()+"' "; // declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					
					pst.execute(); 		 //  sursa precompilata (cererea) este lansata in executie
					
					JOptionPane.showMessageDialog(null, "Data Updated");		// afisare mesaj care valideaza modificarea inregistrarii
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();   // refresh la tabela
			}
		});
		btnNewButton_1.setBounds(390, 11, 108, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");   // buton pentru delete
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action = JOptionPane.showConfirmDialog(null, "Do you really want to delete?", "Delete", JOptionPane.YES_NO_OPTION); // mesaj care intreaba daca se doreste sau nu stergerea inregistrarii
				if(action == 0) { // aleg YES
				try {
					String query = "DELETE FROM Review WHERE review_id = '"+textField.getText()+"' "; // declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					
					pst.execute(); 	//  sursa precompilata (cererea) este lansata in executie
					
					JOptionPane.showMessageDialog(null, "Data Deleted");	// afisare mesaj care valideaza stergerea inregistrarii
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();	// refresh la tabela
				}
			}
		});
		btnNewButton_2.setBounds(390, 50, 108, 23);
		contentPane.add(btnNewButton_2);
		
		comboBoxName = new JComboBox<String>();			// declarare buton tip JcomboBox
		comboBoxName.addActionListener(new ActionListener() {	// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT * FROM Review WHERE review_id = ?";	// declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					pst.setString(1, (String)comboBoxName.getSelectedItem());	// cast in String pentru valoarea din butonul JcomboBox
					ResultSet rs = pst.executeQuery();	// sursa precompilata (cererea) este lansata in executie
						
					while(rs.next()) {			// cand am selectat o valoare => campurile (dupa care doresc sa fac un update/delete) sunt setate la valorile corespunzatoare inregistrarii in care se gaseste id-ul pentru review
						textField.setText(rs.getString("review_id"));
						textField_1.setText(rs.getString("film_id"));
						textField_2.setText(rs.getString("comentariu"));
						textField_3.setText(rs.getString("user_id"));
						textField_4.setText(rs.getString("review_nota"));
						textField_5.setText(rs.getString("ora_comentariu"));
					}
					
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();			// refresh la tabela
			}
		});
		comboBoxName.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBoxName.setBounds(10, 255, 181, 76);
		contentPane.add(comboBoxName);
		
		JButton btnNewButton_3 = new JButton("Go to Actor");	// redirectionare catre tabela cu actori (vizualizare + modificare inregistrari)
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ModifActor object = new ModifActor();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(559, 11, 108, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Go to Film");			// redirectionare catre tabela cu filme (vizualizare + modificare inregistrari)
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ModifFilm object = new ModifFilm();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(559, 50, 108, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Execute SQL");	// redirectionare catre interfata cu cereri
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ExecuteSQLQuery object = new ExecuteSQLQuery();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_5.setBounds(725, 11, 108, 23);
		contentPane.add(btnNewButton_5);
		
		refreshTable();
		fillComboBox();
	}

}
