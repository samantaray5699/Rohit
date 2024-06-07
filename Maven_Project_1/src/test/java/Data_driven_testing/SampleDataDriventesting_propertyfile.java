package Data_driven_testing;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SampleDataDriventesting_propertyfile {

	public static void main(String[] args) throws IOException {
		//step-1 get the java representation object of the physical property file
		FileInputStream fis= new FileInputStream("C:\\Users\\adity\\OneDrive\\Desktop\\commondata.properties");
		//step-2 using property class object class load all keys
		Properties pobj= new Properties();
		pobj.load(fis);
		//step-3 get the value based on key
		System.out.println(pobj.getProperty("browser"));
		System.out.println(pobj.getProperty("url"));
	}

}
