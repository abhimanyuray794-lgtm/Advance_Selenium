package using.genericutility;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.generic.fileutility.ExcelUtility;

public class Login {
	
	@Test (dataProvider = "sendDataForLogin")
		public void loginToApp(String username, String password ) {
			
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			
			driver.findElement(By.name("username")).sendKeys(username);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
		}
		
		@DataProvider
		public Object[][] sendDataForLogin() throws EncryptedDocumentException, IOException {
			
			ExcelUtility elib = new ExcelUtility();
			
			int rowCount = elib.getRowCount("Sheet1");
			
			Object[][] obj = new Object[rowCount][2];

			for (int i = 0; i < rowCount; i++) {
				
				obj[i][0] = elib.readDataFromExcel("Sheet1", i+1, 0);
				obj[i][1] = elib.readDataFromExcel("Sheet1", i+1, 1);
	
//				System.out.println("Row " + (i + 1) + " is null");
				}
			
			return obj;
		}
	}
