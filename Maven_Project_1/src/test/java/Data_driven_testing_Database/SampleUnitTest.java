package Data_driven_testing_Database;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class SampleUnitTest {
	String expected_createdby="Adit";
	boolean flag=false;
	@Test
	public void sample() throws Throwable
	{
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
					String actual_createdby = result.getString(2);
					if(actual_createdby.equals(expected_createdby))
					{
						System.out.println(expected_createdby+" is available");
						flag=true;
					}
				}
				if( flag ==false)
				{
					System.out.println(expected_createdby+"is not available");
					Assert.fail();//otherwise it will show pass
				}
				con.close();
				
	}

}
