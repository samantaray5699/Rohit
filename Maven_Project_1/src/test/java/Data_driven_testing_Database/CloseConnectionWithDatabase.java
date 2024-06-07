package Data_driven_testing_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class CloseConnectionWithDatabase {

	public static void main(String[] args) throws Throwable {

		Connection con=null;
		try {
			Driver driverref= new Driver();
			DriverManager.registerDriver(driverref);
			con = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%","root");
			Statement stat = con.createStatement();	
			ResultSet result = stat.executeQuery("select * from project");
			while(result.next())
			{
				String res = result.getString(1)+"\t" +result.getString(2)+"\t" +result.getString(3)+"\t"
						+result.getString(4)+"\t" +result.getString(5)+"\t" +result.getString(6);
				System.out.println(res);

			}
		}catch(Exception e)
		{
		}
		finally {
			con.close();
			System.out.println("=====closed=====");
		}
		

	}

}
