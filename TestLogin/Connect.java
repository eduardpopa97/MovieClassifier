package jdbcdemo;
import java.sql.*;
import javax.swing.*;
public class Connect {
	Connection con = null;
	public static Connection conexiuneBD()
	{
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-2UOCII1;databaseName=Rezervare;user=sa;password=parola");
			
			return con;
		}catch(Exception exc)
		{
			JOptionPane.showMessageDialog(null, exc);
			return null;
		}
	}

}
