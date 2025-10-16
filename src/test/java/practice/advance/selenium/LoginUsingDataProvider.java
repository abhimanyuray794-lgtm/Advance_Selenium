package practice.advance.selenium;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginUsingDataProvider {
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
		
		FileInputStream fis = new FileInputStream("./configData/Book2.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum();
		
		Object[][] obj = new Object[rowCount][2];

		for (int i = 0; i <= rowCount; i++) {
			Row row = sheet.getRow(i+1);
			if(row != null) {
				
			obj[i][0] = row.getCell(0).toString();
			obj[i][1] = row.getCell(1).toString();;
			}else {
				System.out.println("Row " + (i + 1) + " is null");
			}
		}
		workbook.close();
		return obj;
	}
}
