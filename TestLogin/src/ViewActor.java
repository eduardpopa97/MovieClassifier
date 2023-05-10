import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.sql.*;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewActor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewActor frame = new ViewActor();
					frame.setVisible(true);		// afisare interfata // afisare interfata ViewActor
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
	public ViewActor() {
		conexiune = Connect.conexiuneBD();	// conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 547, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BtnLoad = new JButton("LoadActor");
		BtnLoad.setFont(new Font("Tahoma", Font.BOLD, 11));
		BtnLoad.addActionListener(new ActionListener() {	// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT * FROM Actor";	// afisez tot continutul tabelei Actor
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					ResultSet rs = pst.executeQuery(); 	//  sursa precompilata (cererea) este lansata in executie
					table.setModel(DbUtils.resultSetToTableModel(rs));	// afisare rezultat cerere in tabela tabel
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		BtnLoad.setBounds(10, 30, 108, 23);
		contentPane.add(BtnLoad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 505, 247);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("Go to Film");		// redirectionare catre tabela cu filme (numai vizualizare de date)
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ViewFilm object = new ViewFilm();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(149, 30, 108, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Go to Review");		// redirectionare catre tabela cu review-uri (numai vizualizare de date)
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ViewReview object = new ViewReview();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(281, 30, 108, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Execute SQL");		// redirectionare catre tabela cu cereri 
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ExecuteSQLQueryUser object = new ExecuteSQLQueryUser();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBounds(407, 30, 108, 23);
		contentPane.add(btnNewButton_4);
		
	}
}

