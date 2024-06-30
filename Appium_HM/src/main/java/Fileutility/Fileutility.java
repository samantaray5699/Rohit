package Fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * @author aditya
 *File utility
 */
public class Fileutility {

	public String getdataFromPropertyFile(String key) throws IOException
	{
		FileInputStream fis= new FileInputStream("./AppData/CommonData.properties");
		Properties pobj= new Properties();
		pobj.load(fis);
		String value= pobj.getProperty(key);
		return value;
	}
}
