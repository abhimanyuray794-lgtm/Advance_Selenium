package practice.advance.selenium;

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

public class Questionno1 {
	@Test
	public void printNameOfProduct() throws InterruptedException, EncryptedDocumentException, IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.clinique.in/");
		
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		WebElement crossButton = driver.findElement(By.xpath("//button[@id='dismissBtn']"));
//		wait.until(ExpectedConditions.elementToBeClickable(crossButton)).click();
		
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
		
		String psco = "//div[contains(@class,'gnav-desktop-c')]/child::nav/child::div/child::div[position()=4]/descendant::div[@class='gnav-desktop-sub-nav-content']";
		WebElement skinCareOption = driver.findElement(By.xpath(psco));
		System.out.println(skinCareOption.getText());
		
		System.out.println("********************************");
		
		 // Create Excel workbook
		FileInputStream fis = new FileInputStream("./configData/Book2.xlsx");
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet("Skincare Option");
        if (sheet == null) {
            sheet = workbook.createSheet("Skincare Option");
        }
        
     // Write header
        Row header = sheet.getRow(0);
		if (header == null) {
			header = sheet.createRow(0);
		}

		Cell cell0 = header.getCell(0);
		if (cell0 == null) {
			cell0 = header.createCell(0, CellType.STRING);
		}
		cell0.setCellValue("Sl No");
		
		Cell cell1 = header.getCell(1);
		if (cell1 == null) {
			cell1 = header.createCell(1, CellType.STRING);
		}
		cell1.setCellValue("Option Name");

        // Write skincare options into Excel
        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue(skinCareOption.getText());
        
        // Auto-size columns
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        // Save Excel file
        FileOutputStream fos = new FileOutputStream("./configData/Book2.xlsx");
        workbook.write(fos);
        workbook.close();

        System.out.println("Skincare option exported to SkincareOption.xlsx");
			
	}
}

