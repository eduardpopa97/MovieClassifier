import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class ModifFilm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboBoxName;

	public void refreshTable() {			// functia refreshTable are rolul de a da refresh la tabela imediat dupa ce are loc o modificare (update, insert, delete) ca sa nu mai fiu nevoit sa apas LoadFilm
		try {
			String query = "SELECT * FROM Film";	// afisare tabela Film
			PreparedStatement pst = conexiune.prepareStatement(query); // creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
			ResultSet rs = pst.executeQuery(); 							// sursa precompilata (cererea) este lansata in executie
			table.setModel(DbUtils.resultSetToTableModel(rs));			// afisare rezultate cerere sub forma de tabela
			pst.close();												 // eliberare spatiu memorie
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void fillComboBox() {			// din butonul JcomboBox pot sa selectez titlul filmului
		try {								// cand am selectat o valoare => campurile (dupa care doresc sa fac un update/delete) sunt setate la valorile corespunzatoare inregistrarii in care se gaseste titlul filmului	
			String query = "SELECT * FROM Film";
			PreparedStatement pst = conexiune.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				comboBoxName.addItem(rs.getString("film_titlu"));
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
					ModifFilm frame = new ModifFilm();    // afisare interfata ModifFilm
					frame.setVisible(true);
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
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;		// definire campuri 
	/**
	 * Create the frame.
	 */
	public ModifFilm() {
		conexiune = Connect.conexiuneBD();	// conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 967, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BtnLoad = new JButton("LoadFilm");
		BtnLoad.setFont(new Font("Tahoma", Font.BOLD, 11));
		BtnLoad.addActionListener(new ActionListener() {	// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT * FROM Film";		// afisez tot continutul tabelei Film
					PreparedStatement pst = conexiune.prepareStatement(query);   // creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					ResultSet rs = pst.executeQuery(); 							//  sursa precompilata (cererea) este lansata in executie
					table.setModel(DbUtils.resultSetToTableModel(rs));			// afisare rezultat cerere in tabela tabel
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		BtnLoad.setBounds(217, 11, 108, 23);
		contentPane.add(BtnLoad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(201, 102, 728, 229);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblFilmId = new JLabel("FilmID");
		lblFilmId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFilmId.setBounds(10, 11, 46, 14);
		contentPane.add(lblFilmId);
		
		textField = new JTextField();
		textField.setBounds(105, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFilmTitlu = new JLabel("FilmTitlu");
		lblFilmTitlu.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFilmTitlu.setBounds(10, 42, 70, 14);
		contentPane.add(lblFilmTitlu);
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 39, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblFilmAn = new JLabel("FilmAn");
		lblFilmAn.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFilmAn.setBounds(10, 74, 86, 14);
		contentPane.add(lblFilmAn);
		
		textField_2 = new JTextField();
		textField_2.setBounds(105, 71, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblFilmDurata = new JLabel("FilmDurata");
		lblFilmDurata.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFilmDurata.setBounds(10, 109, 70, 14);
		contentPane.add(lblFilmDurata);
		
		textField_3 = new JTextField();
		textField_3.setBounds(105, 106, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblFilmLimba = new JLabel("FilmLimba");
		lblFilmLimba.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFilmLimba.setBounds(10, 144, 70, 14);
		contentPane.add(lblFilmLimba);
		
		textField_4 = new JTextField();
		textField_4.setBounds(105, 141, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblFilmTara = new JLabel("FilmTara");
		lblFilmTara.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFilmTara.setBounds(10, 180, 70, 14);
		contentPane.add(lblFilmTara);
		
		textField_5 = new JTextField();
		textField_5.setBounds(105, 177, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblRegizorId = new JLabel("RegizorId");
		lblRegizorId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRegizorId.setBounds(10, 214, 70, 14);
		contentPane.add(lblRegizorId);
		
		textField_6 = new JTextField();
		textField_6.setBounds(105, 211, 86, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblDescriere = new JLabel("Descriere");
		lblDescriere.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescriere.setBounds(10, 249, 70, 14);
		contentPane.add(lblDescriere);
		
		textField_7 = new JTextField();
		textField_7.setBounds(105, 246, 86, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");      // buton pentru insert
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "INSERT INTO Film (film_id, film_titlu, film_an, film_durata, film_limba, film_tara, regizor_id, descriere) VALUES (?,?,?,?,?,?,?,?)";	// declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);		// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					pst.setString(1,textField.getText());						// cast in String pentru valorile din cele 8 campuri din interfata
					pst.setString(2,textField_1.getText());
					pst.setString(3,textField_2.getText());
					pst.setString(4,textField_3.getText());
					pst.setString(5,textField_4.getText());
					pst.setString(6,textField_5.getText());
					pst.setString(7,textField_6.getText());
					pst.setString(8,textField_7.getText());
					
					pst.execute(); 			//  sursa precompilata (cererea) este lansata in executie
					
					JOptionPane.showMessageDialog(null, "Data Saved");		// afisare mesaj care valideaza inserarea inregistrarii
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
			}
		});
		btnNewButton.setBounds(217, 68, 108, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");			// buton pentru update
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "UPDATE Film SET film_id = '"+textField.getText()+"', film_titlu = '"+textField_1.getText()+"', film_an = '"+textField_2.getText()+"', film_durata = '"+textField_3.getText()+"', film_limba = '"+textField_4.getText()+"', film_tara = '"+textField_5.getText()+"', regizor_id = '"+textField_6.getText()+"', descriere = '"+textField_7.getText()+"' WHERE film_id = '"+textField.getText()+"' "; // declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					
					pst.execute(); 				//  sursa precompilata (cererea) este lansata in executie
					
					JOptionPane.showMessageDialog(null, "Data Updated");		// afisare mesaj care valideaza modificarea inregistrarii
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();		// refresh la tabela
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(381, 11, 108, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");		// buton pentru delete
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action = JOptionPane.showConfirmDialog(null, "Do you really want to delete?", "Delete", JOptionPane.YES_NO_OPTION);   // mesaj care intreaba daca se doreste sau nu stergerea inregistrarii
				if(action == 0) {     // aleg YES
				try {
					String query = "DELETE FROM Film WHERE film_id = '"+textField.getText()+"' ";   // declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);			// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					
					pst.execute(); 		//  sursa precompilata (cererea) este lansata in executie
					
					JOptionPane.showMessageDialog(null, "Data Deleted");		// afisare mesaj care valideaza stergerea inregistrarii
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();   // refresh la tabela
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(381, 68, 108, 23);
		contentPane.add(btnNewButton_2);
		
		comboBoxName = new JComboBox<String>();			// declarare buton tip JcomboBox
		comboBoxName.addActionListener(new ActionListener() {		// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT * FROM Film WHERE film_titlu = ?";		// declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);		// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					pst.setString(1, (String)comboBoxName.getSelectedItem());		// cast in String pentru valoarea din butonul JcomboBox
					ResultSet rs = pst.executeQuery();								// sursa precompilata (cererea) este lansata in executie
					
					while(rs.next()) {												// cand am selectat o valoare => campurile (dupa care doresc sa fac un update/delete) sunt setate la valorile corespunzatoare inregistrarii in care se gaseste titlul fimului	
						textField.setText(rs.getString("film_id"));
						textField_1.setText(rs.getString("film_titlu"));
						textField_2.setText(rs.getString("film_an"));
						textField_3.setText(rs.getString("film_durata"));
						textField_4.setText(rs.getString("film_limba"));
						textField_5.setText(rs.getString("film_tara"));
						textField_6.setText(rs.getString("regizor_id"));
						textField_7.setText(rs.getString("descriere"));
					}
					
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();		// refresh la tabela
			}	
		});
		comboBoxName.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBoxName.setBounds(10, 281, 181, 50);
		contentPane.add(comboBoxName);
		
		JButton btnNewButton_3 = new JButton("Go to Actor");	 // redirectionare catre tabela cu actori (vizualizare + modificare inregistrari)
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ModifActor object = new ModifActor();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(541, 11, 108, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Go to Review");	// redirectionare catre tabela cu review-uri (vizualizare + modificare inregistrari)
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ModifReview object = new ModifReview();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBounds(541, 70, 108, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Execute SQL");		// redirectionare catre interfata cu cereri
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ExecuteSQLQuery object = new ExecuteSQLQuery();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_5.setBounds(701, 11, 108, 23);
		contentPane.add(btnNewButton_5);
		
		refreshTable();
		fillComboBox();
		
	}

}
