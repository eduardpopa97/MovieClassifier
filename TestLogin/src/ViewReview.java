import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class ViewReview extends JFrame {

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
					ViewReview frame = new ViewReview();
					frame.setVisible(true);		// afisare interfata ViewReview
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
	public ViewReview() {
		conexiune = Connect.conexiuneBD();	// conectare la baza de date prin intermediul unui driver numit jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// caracteristici interfata (dimensiuni, etc.)
		setBounds(100, 100, 924, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton BtnLoad = new JButton("LoadReview");
		BtnLoad.setFont(new Font("Tahoma", Font.BOLD, 11));	
		BtnLoad.addActionListener(new ActionListener() {	// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT * FROM Review";	// afisez tot continutul tabelei Review
					PreparedStatement pst = conexiune.prepareStatement(query);	// creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
					ResultSet rs = pst.executeQuery(); 					//  sursa precompilata (cererea) este lansata in executie
					table.setModel(DbUtils.resultSetToTableModel(rs));	// afisare rezultat cerere in tabela tabel
					pst.close();
					rs.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		BtnLoad.setBounds(50, 33, 108, 23);
		contentPane.add(BtnLoad);
		
		JButton btnNewButton_3 = new JButton("Go to Actor");		// redirectionare catre tabela cu actori (numai vizualizare de date)
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ViewActor object = new ViewActor();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(256, 33, 108, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Go to Film");			// redirectionare catre tabela cu filme (numai vizualizare de date)
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ViewFilm object = new ViewFilm();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_4.setBounds(518, 33, 108, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Execute SQL");		// redirectionare catre tabela cu cereri
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ExecuteSQLQueryUser object = new ExecuteSQLQueryUser();
				object.setVisible(true);
				dispose();
			}
		});
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_5.setBounds(736, 33, 108, 23);
		contentPane.add(btnNewButton_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 888, 232);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
