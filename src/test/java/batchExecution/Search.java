package batchExecution;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;


public class Search {
@Test(invocationCount = 3, threadPoolSize = 2)
public void searchTestCase() {
	WebDriver driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://demowebshop.tricentis.com/");
	
	driver.findElement(By.id("small-searchterms")).sendKeys("books");
	driver.findElement(By.xpath("//input[@type='submit']")).click();
	Reporter.log("Report passed");
	driver.close();
}
}
