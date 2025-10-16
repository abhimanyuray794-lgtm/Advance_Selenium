package com.practice.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String readDataFromExcel(String sheetName, int rowNum, int cellNum)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./configData/Book2.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		Row row = sh.getRow(rowNum);
		Cell cel = row.getCell(cellNum);
		String data = cel.toString();
		wb.close();
		return data;
	}

	public void writeDataInExcel(String sheetName, int rowNum, int cellNum, String value)
			throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./configData/Book2.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet(sheetName);
        if (sh == null) {
            sh = wb.createSheet(sheetName);
        }

		Row row = sh.getRow(rowNum);
		if (row == null) {
			row = sh.createRow(rowNum);
		}

		Cell cell = row.getCell(cellNum);
		if (cell == null) {
			cell = row.createCell(cellNum, CellType.STRING);
		}
		cell.setCellValue(value);
		
		FileOutputStream fos = new FileOutputStream("./configData/Book2.xlsx");
        wb.write(fos);
		wb.close();
	}

	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./configData/Book2.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		return rowCount;
	}
	
	 // Auto-size all used columns
	Sheet sheet;
    public void autoSizeColumns(int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}
