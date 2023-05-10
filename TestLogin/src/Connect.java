import java.sql.*;
import javax.swing.*;
public class Connect {
	Connection con = null; 
	// clasa Connection este necesara pentru realizarea conexiunii cu baza de date, dar si pentru executarea de cereri
	public static Connection conexiuneBD()
	{
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://EDY-PC;databaseName=ProiectBD;user=sa;password=11IUNIE1997"); 
			// functia getConnection incearca sa stabileasca o conexiune catre baza de date indicata de url-ul dat ca argument
			
			return con;
		}catch(Exception exc)
		{  // in cazul in care apare o eroare la conexiune se va afisa un mesaj (DRIVER NOT FOUND)
			JOptionPane.showMessageDialog(null, exc); 
			return null;
		}
	}

}

