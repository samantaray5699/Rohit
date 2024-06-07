package Data_driven_testing_Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.mysql.jdbc.Driver;

public class GetDatafromExcelandPassItIntoDatabase {

	public static void main(String[] args) throws Throwable {

		Random ran= new Random();
		int num = ran.nextInt(20);
		FileInputStream fis = new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\datadriven\\Testdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String project_id = wb.getSheet("Sheet3").getRow(1).getCell(0).toString()+num;
		String createdby = wb.getSheet("Sheet3").getRow(1).getCell(1).toString();
		String ondate= wb.getSheet("Sheet3").getRow(1).getCell(2).toString();
		String project_name = wb.getSheet("Sheet3").getRow(1).getCell(3).toString();
		String status = wb.getSheet("Sheet3").getRow(1).getCell(4).toString();
		String team_size = wb.getSheet("Sheet3").getRow(1).getCell(5).toString();
		System.out.println(project_id);

		Driver driverref= new Driver();
		DriverManager.registerDriver(driverref);
		Connection con = DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects", "root@%","root");
		con.createStatement().executeUpdate(" insert into project value('"+project_id+"','"+createdby+"','"+ondate+"','"+project_name+"','"+status+"','"+team_size+"');");
		con.close();

















	}

}
