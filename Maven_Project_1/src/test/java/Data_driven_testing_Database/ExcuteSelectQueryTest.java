package Data_driven_testing_Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExcuteSelectQueryTest {

	public static void main(String[] args) throws Throwable {
		//Step1 - load and register the database driver
		Driver driverref= new Driver();
		DriverManager.registerDriver(driverref);
		//Step2 - Connect to database
		Connection con = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%","root");
		//Step3 - Create sql statement
		Statement stat = con.createStatement();
		//Step4 - Execute select query & get the result
		ResultSet result = stat.executeQuery("select * from project");
		while(result.next())
		{
			String res = result.getString(1)+"\t" +result.getString(2)+"\t" +result.getString(3)+"\t"
		+result.getString(4)+"\t" +result.getString(5)+"\t" +result.getString(6);
			System.out.println(res);
			
		}
		con.close();
		//Step5 - Close the connection

	}
}
