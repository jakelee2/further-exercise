package KitchenOrders;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class GetPropertyValues {
	String res = "";
	InputStream inputStream;
 
	public String getPropValues(String propName) throws IOException {
 
		try {
			Properties prop = new Properties();
			String propFileName = "./src/main/java/KitchenOrders/config.properties";
 
//			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			inputStream = new FileInputStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + 
						propFileName + "' not found in the path");
			}
 
			Date time = new Date(System.currentTimeMillis());
 
			// get the property value and print it out
//			String howManyOrdersPerSecond = prop.getProperty("howManyOrdersPerSecond"); 
			res = prop.getProperty(propName); 
			String result = propName + " = " + res ;
			System.out.println(result + "\nProgram Ran on " + time);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return res;
	}
}
