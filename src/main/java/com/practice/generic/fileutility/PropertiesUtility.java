package com.practice.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {

	public String getDataFromPropertiesFile(String key) throws IOException {

		FileInputStream fis = new FileInputStream("./configAppData/commonData.properties");
		Properties pro = new Properties();
		pro.load(fis);

		String data = pro.getProperty(key);
		return data;
	}
}
