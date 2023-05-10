import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifActor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboBoxName;

	public void refreshTable() {		// functia refreshTable are rolul de a da refresh la tabela imediat dupa ce are loc o modificare (update, insert, delete) ca sa nu mai fiu nevoit sa apas LoadActor
		try {						
			String query = "SELECT * FROM Actor";		// afisare tabela Actor
			PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
			ResultSet rs = pst.executeQuery(); 							  // sursa precompilata (cererea) este lansata in executie
			table.setModel(DbUtils.resultSetToTableModel(rs));			  // afisare rezultate cerere sub forma de tabela
			pst.close();												  // eliberare spatiu memorie
			rs.close();
		    }
		catch (Exception e) {
			e.printStackTrace();
			}
		
	}
	
	public void fillComboBox() {						// din butonul JcomboBox pot sa selectez numele actorului
		try {											// cand am selectat o valoare => campurile (dupa care doresc sa fac un update/delete) sunt setate la valorile corespunzatoare inregistrarii in care se gaseste numele actorului	
			String query = "SELECT * FROM Actor";
			PreparedStatement pst = conexiune.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				comboBoxName.addItem(rs.getString("actor_nume"));
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
					ModifActor frame = new ModifActor();
					frame.setVisible(true);					// afisare interfata ModifActor
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
	private JTextField textField_3;						// definire campuri (id, nume, prenume, sex)
	/**
	 * Create the frame.
	 */
	public ModifActor() {
		conexiune = Connect.conexiuneBD();				// conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 531, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BtnLoad = new JButton("LoadActor");		
		BtnLoad.setFont(new Font("Tahoma", Font.BOLD, 11));
		BtnLoad.addActionListener(new ActionListener() {		// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT * FROM Actor";		// afisez tot continutul tabelei Actor
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					ResultSet rs = pst.executeQuery(); 							 //  sursa precompilata (cererea) este lansata in executie
					table.setModel(DbUtils.resultSetToTableModel(rs));			// afisare rezultat cerere in tabela tabel
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		BtnLoad.setBounds(219, 11, 108, 23);
		contentPane.add(BtnLoad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(201, 84, 304, 247);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("ActorID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 103, 46, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(105, 100, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblActornume = new JLabel("ActorNume");
		lblActornume.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblActornume.setBounds(10, 136, 70, 14);
		contentPane.add(lblActornume);
		
		textField_1 = new JTextField();
		textField_1.setBounds(105, 133, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblActorprenume = new JLabel("ActorPrenume");
		lblActorprenume.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblActorprenume.setBounds(9, 172, 86, 14);
		contentPane.add(lblActorprenume);
		
		textField_2 = new JTextField();
		textField_2.setBounds(105, 169, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblActorsex = new JLabel("ActorSex");
		lblActorsex.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblActorsex.setBounds(10, 209, 59, 14);
		contentPane.add(lblActorsex);
		
		textField_3 = new JTextField();
		textField_3.setBounds(105, 206, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");				// buton pentru insert
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "INSERT INTO Actor (actor_id, actor_nume, actor_prenume, actor_sex) VALUES (?,?,?,?)";	// declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);		 // creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					pst.setString(1,textField.getText());							// cast in String pentru valorile din cele 4 campuri din interfata 
					pst.setString(2,textField_1.getText());
					pst.setString(3,textField_2.getText());
					pst.setString(4,textField_3.getText());
					
					pst.execute(); 													  //  sursa precompilata (cererea) este lansata in executie
					
					JOptionPane.showMessageDialog(null, "Data Saved");				 // afisare mesaj care valideaza inserarea inregistrarii
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();  // refresh la tabela
			}
		});
		btnNewButton.setBounds(47, 237, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnUpdate = new JButton("Update");					// buton pentru update
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "UPDATE Actor SET actor_id = '"+textField.getText()+"', actor_nume = '"+textField_1.getText()+"', actor_prenume ='"+textField_2.getText()+"', actor_sex = '"+textField_3.getText()+"' WHERE actor_id = '"+textField.getText()+"' ";  // declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);			// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
				
					pst.execute(); 		 //  sursa precompilata (cererea) este lansata in executie
					
					JOptionPane.showMessageDialog(null, "Data Updated");		// afisare mesaj care valideaza modificarea inregistrarii
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();				// refresh la tabela
			}	
		});
		
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnUpdate.setBounds(47, 271, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnNewButton_1 = new JButton("Delete");		// buton pentru delete
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action = JOptionPane.showConfirmDialog(null, "Do you really want to delete?", "Delete", JOptionPane.YES_NO_OPTION);		// mesaj care intreaba daca se doreste sau nu stergerea inregistrarii
				if(action == 0) {			// aleg YES
				try {
					String query = "DELETE FROM Actor WHERE actor_id ='"+textField.getText()+"' ";	 // declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);		// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
				
					pst.execute(); 		//  sursa precompilata (cererea) este lansata in executie
					
					JOptionPane.showMessageDialog(null, "Data Deleted");	// afisare mesaj care valideaza stergerea inregistrarii
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();		// refresh la tabela
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(47, 308, 89, 23);
		contentPane.add(btnNewButton_1);
		
		comboBoxName = new JComboBox<String>();				// declarare buton tip JcomboBox
		comboBoxName.addActionListener(new ActionListener() {		// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed(ActionEvent arg0) {		
				try {
					String query = "SELECT * FROM Actor WHERE actor_nume = ?";	// declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
					PreparedStatement pst = conexiune.prepareStatement(query);  // creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					pst.setString(1, (String)comboBoxName.getSelectedItem());	// cast in String pentru valoarea din butonul JcomboBox
					ResultSet rs = pst.executeQuery();							// sursa precompilata (cererea) este lansata in executie
					
					while(rs.next()) {
						textField.setText(rs.getString("actor_id"));			// cand am selectat o valoare => campurile (dupa care doresc sa fac un update/delete) sunt setate la valorile corespunzatoare inregistrarii in care se gaseste numele actorului	
						textField_1.setText(rs.getString("actor_nume"));
						textField_2.setText(rs.getString("actor_prenume"));
						textField_3.setText(rs.getString("actor_sex"));
					}
					
					pst.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();		// refresh la tabela
			}
		});
		comboBoxName.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBoxName.setBounds(10, 11, 181, 51);
		contentPane.add(comboBoxName);
		
		JButton btnNewButton_2 = new JButton("Go to Film");     // redirectionare catre tabela cu filme (vizualizare + modificare inregistrari)
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ModifFilm object = new ModifFilm();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(219, 50, 108, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Go to Review");	// redirectionare catre tabela cu review-uri (vizualizare + modificare inregistrari)
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ModifReview object = new ModifReview();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(370, 11, 108, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Execute SQL");	// redirectionare catre interfata cu cereri
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ExecuteSQLQuery object = new ExecuteSQLQuery();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBounds(370, 50, 108, 23);
		contentPane.add(btnNewButton_4);
		
		refreshTable();
		fillComboBox();
	}
}
