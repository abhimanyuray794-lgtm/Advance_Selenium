package practice.advance.selenium;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

public class ReadMultipleDataFromExcel {
	@Test
public void sendDataForLogin() throws EncryptedDocumentException, IOException {
		
		FileInputStream fis = new FileInputStream("./configData/Book2.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum();
		
		for (int i = 0; i <= rowCount; i++) {
			Row row = sheet.getRow(i+1);
			if(row != null) {
				
			String data1 = (row.getCell(0) != null) ? row.getCell(0).toString() : "Row " + (i + 1) + " and cell 0 is null";
			String data2 = (row.getCell(1) != null) ? row.getCell(1).toString() : "Row " + (i + 1) + " and cell 1 is null";

			
			System.out.println(data1 + "\t" + data2);
			
			}else {
				System.out.println("Row " + (i + 1) + " is null");
			}
		}
	}
}
