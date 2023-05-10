import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;


public class Interfata extends JFrame {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection conexiune = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfata frame = new Interfata();		
					frame.setVisible(true);				// afisare interfata pentru admin
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Interfata() {
		conexiune = Connect.conexiuneBD();					// conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectatiCeTabel = new JLabel("Selectati ce tabel doriti sa modificati");  // creare eticheta (titlul interfetei)
		lblSelectatiCeTabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblSelectatiCeTabel.setBounds(70, 25, 328, 20);
		contentPane.add(lblSelectatiCeTabel);
		
		JButton btnExit = new JButton("Exit");						
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.addActionListener(new ActionListener() {			// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed(ActionEvent e) {
				Component frmLoginCheck = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginCheck, "Confirm if you want to exit", "Login Check", // afisare meniu in care aleg daca doresc sau nu sa ies din aplicatie
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) { 
				System.exit(0);										// iesire aplicatie
				}
			}
		});
		btnExit.setBounds(50, 136, 121, 23);
		contentPane.add(btnExit);

	
		JButton btnNewButton = new JButton("Select table");				// declarare buton "Select table" cu caracteristicile sale (marime, tip scris)
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(50, 82, 121, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Execute SQL");        // addActionListener este folosit pentru a atribui functionalitate unui buton
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
				ExecuteSQLQuery object = new ExecuteSQLQuery();		// declarare obiect tip ExecuteSQlQuery
				object.setVisible(true);							// afisare interfata pentru executia de cereri
				dispose();											// inchiderea ferestrei anterioare
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(50, 198, 121, 23);
		contentPane.add(btnNewButton_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();       // definire buton JcomboBox cu valori de tip String
		comboBox.addItem("ModifActor");								// butonul poate avea 3 valori (ModifActor, ModifFilm, ModifReview)
		comboBox.addItem("ModifFilm");								// butonul selecteaza ce interfata (de vizualizat date + de modificat date : update, insert, delete => ADMIN) se va deschide
		comboBox.addItem("ModifReview");
		comboBox.setBounds(261, 83, 121, 20);
		contentPane.add(comboBox);
		
		btnNewButton.addActionListener(new ActionListener() {		// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed(ActionEvent arg0) {
				try {
					switch((String)comboBox.getSelectedItem()) {	// in functie de valoarea acestui buton de tip JcomboBox se va alege ce interfata din urmatoarele 3 se va deschide
					case "ModifActor": ModifActor object1 = new ModifActor();
									   object1.setVisible(true);
									   break;
					case "ModifFilm":  ModifFilm object2 = new ModifFilm();
									   object2.setVisible(true);
									   break;
					case "ModifReview":ModifReview object3 = new ModifReview();
									   object3.setVisible(true);
									   break;
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				dispose();											// inchidere fereastra anterioara
				}
		});
	    }
}




