package Data_driven_testing_Database;


import java.sql.Connection;
import java.sql.DriverManager;

import com.mysql.jdbc.Driver;

public class ExcuteNonSelectQueryTest {

	public static void main(String[] args) throws Throwable {
		
		String project="TY_PROJ_1111";
		String createdby="Aditya";
		String date="02/02/2023";
		String name="instagram";
		String status="completed";
		String count="99";
		
		
		
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		int result = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%","root")
			.createStatement().
		executeUpdate(" insert into project value('"+project+"','Aditya','15/06/2024','facebook','done','5');");
		System.out.println(result);
		
		

	}

}
