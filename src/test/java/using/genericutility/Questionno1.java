package using.genericutility;

	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.List;

	import org.apache.poi.EncryptedDocumentException;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.CellType;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.Test;

import com.practice.generic.fileutility.ExcelUtility;

	public class Questionno1 {
		@Test
		public void printNameOfProduct() throws InterruptedException, EncryptedDocumentException, IOException {
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get("https://www.clinique.in/");
			
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//			WebElement crossButton = driver.findElement(By.xpath("//button[@id='dismissBtn']"));
//			wait.until(ExpectedConditions.elementToBeClickable(crossButton)).click();
			
			System.out.println("********************************");
			
			WebElement skinCare = driver.findElement(By.xpath("//div[contains(@class,'gnav-desktop-n')]/child::a[contains(text(),'Skincare')]"));
			Actions action = new Actions(driver);
			action.moveToElement(skinCare).perform();
			System.out.println(skinCare.getText());
			
			System.out.println("********************************");
			
			String pscwoe = "//div[contains(@class,'gnav-desktop-c')]/child::nav/child::div[@class='gnav-desktop-top-level-row']";
			WebElement skinCareWithOtherElement = driver.findElement(By.xpath(pscwoe));
			System.out.println(skinCareWithOtherElement.getText());
			
			System.out.println("********************************");
			
			String psco = "//div[contains(@class,'gnav-desktop-c')]/child::nav/child::div/child::div[position()=4]/descendant::div[@class='gnav-desktop-sub-nav-content']//a";
			List<WebElement> skinCareOptions = driver.findElements(By.xpath(psco));
			for (WebElement result : skinCareOptions) {
				System.out.println(result.getText());
			}
			
			System.out.println("********************************");
			
			 // Create Excel workbook with header
			ExcelUtility elib = new ExcelUtility();
			elib.writeDataInExcel("Skincare Options", 0, 0, "Sl No");
			elib.writeDataInExcel("Skincare Options", 0, 1, "Option Name");
			
			for(int i = 0; i<skinCareOptions.size(); i++) {
				elib.writeDataInExcel("Skincare Options", i+1, 0, String.valueOf(i+1));
				elib.writeDataInExcel("Skincare Options", i+1, 1, skinCareOptions.get(i).getText());
			}
			elib.autoSizeColumns(2);

	        System.out.println("Skincare option exported to SkincareOption.xlsx");
				
		}
	}
