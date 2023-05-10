import java.awt.Component;
import java.sql.*;
import java.awt.EventQueue;
import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;


public class LoginCheck extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private static LoginCheck frame;
	private JButton btnReset;
	JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginCheck();
					frame.setVisible(true);    // afisare interfata logare
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conexiune = null;
	private JLabel lblLoginAs;                 // declararea celor 3 campuri pentru logare
	private JPasswordField passwordField;
	private JComboBox<String> comboBox;

	/**
	 * Create the frame.
	 */
	
	public LoginCheck() {
		conexiune = Connect.conexiuneBD();		// conexiune cu baza de date prin intermiul unui driver jdbc
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setBounds(100, 100, 463, 321);         // definire caracteristicilor pentru fereastra interfetei (dimensiuni)
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");    // definire caracteristici eticheta Username 
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername.setBounds(213, 55, 59, 14);
		contentPane.add(lblUsername);
		
		textField = new JTextField();					// definirea primului camp de completat (dimensiune)
		textField.setBounds(318, 52, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		       
		JLabel lblPassword = new JLabel("Password");       // definire caracteristici eticheta Password 
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setBounds(213, 95, 59, 14);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {    // addActionListener este folosit pentru a atribui functionalitate unui buton
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
                    String query = "SELECT * FROM User_parola WHERE nume=? AND parola=? AND Tip_utilizator = ?"; // declaram o variabila de tip String care retine sirul de caractere ce reprezinta cererea
                    PreparedStatement stm = conexiune.prepareStatement(query);                                   // creare cerere SQL precompilata (folosita in executarea cererii - faza intermediara)
                    stm.setString(1, textField.getText());                                                       // cast in String pentru valorile din cele 3 campuri din interfata 
                    stm.setString(2, passwordField.getText());
                    stm.setString(3, (String) comboBox.getSelectedItem());
                    ResultSet rs = stm.executeQuery();                                                           //  sursa precompilata (cererea) este lansata in executie
                    int i = 0;
                    while(rs.next())																			 // rs.next() verifica daca datele introduse in campuri se potrivesc cu datele unei inregistrari printr-o cautare secventiala in baza de date
                    {
                        i++;                                                                                     // mutare cursor pe pozitia urmatoare
                    }
                    if(i>0) {
                        JOptionPane.showMessageDialog(null, " User si parola corecte.\n Conectat la baza de date.");  // mesaj date corecte
                        frame.dispose();																			  // inchidere fereastra curenta
                        if((String) comboBox.getSelectedItem() == "Admin")     // daca ma loghez ca admin se va deschide o interfata pentru admin (Interfata este pentru admin)
                        	{Interfata object = new Interfata();	
                             object.setVisible(true);							// afisare interfata admin
                        	}
                        else 
                        {
                        	InterfataUser object = new InterfataUser();			// daca ma loghez ca client se va deschide o interfata pentru client (InterfataUser este pentru client)
                            object.setVisible(true);							// afisare interfata client
                        }
                    }
                    else
                    JOptionPane.showMessageDialog(null, "User si parola incorecte."); // mesaj date incorecte
                    rs.close();														  // eliberare spatiu memorie
                    stm.close();
                }catch(Exception exc) {
                    JOptionPane.showMessageDialog(null, exc);
                }
			}
		
		});
		btnNewButton.setBounds(274, 237, 89, 23);
		contentPane.add(btnNewButton);
		
		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnReset.addActionListener(new ActionListener() {			// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed (ActionEvent arg0) {
				textField.setText(null);						  	// setarea cu NULL (golire camp) a primelor 2 campuri  (al treilea camp este JcomboBox si nu poate fi setat pe NULL deoarece este proiectat sa aiba un numar de 2 valori posibile)
				passwordField.setText(null);
			}
		});
		btnReset.setBounds(274, 169, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.addActionListener(new ActionListener() {			// addActionListener este folosit pentru a atribui functionalitate unui buton
			public void actionPerformed(ActionEvent e) {
				Component frmLoginCheck = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frmLoginCheck, "Confirm if you want to exit", "Login Check",   // afisare meniu in care aleg daca doresc sau nu sa ies din aplicatie
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
				System.exit(0);									// iesire aplicatie
				}
			}
		});
		btnExit.setBounds(274, 203, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblLoginSystem = new JLabel("Login System");     // scriere titlu
		lblLoginSystem.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLoginSystem.setBounds(162, 11, 117, 20);
		contentPane.add(lblLoginSystem);
		
		lblNewLabel = new JLabel(" ");
		lblNewLabel.setBackground(Color.WHITE);
		Image image = new ImageIcon(this.getClass().getResource("user.png")).getImage();  // adaugare imagine in interfata de logare
		lblNewLabel.setIcon(new ImageIcon(image));
		lblNewLabel.setBounds(10, 55, 193, 205);
		contentPane.add(lblNewLabel);
		 
		lblLoginAs = new JLabel("Login as");	     // definire caracteristici text Login as
		lblLoginAs.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLoginAs.setBounds(213, 137, 59, 14);
		contentPane.add(lblLoginAs);
		
		passwordField = new JPasswordField();		// definire caracteristici eticheta Password (PasswordField inlocuieste caracterele cu buline)
		passwordField.setBounds(318, 92, 86, 20);
		contentPane.add(passwordField);
		
		comboBox = new JComboBox<String>();			// definire buton JcomboBox cu valori de tip String
		comboBox.addItem("Admin");					// butonul poate avea 2 valori (Admin sau Client)
		comboBox.addItem("Client");
		comboBox.setBounds(318, 134, 86, 20);
		contentPane.add(comboBox);
		
	}
}
