package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	private static final String TEST_DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCaertTestData.xlsx";
	public static Workbook book;
	public static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {

		System.out.println("reading data from sheet: " + sheetName);

		Object[][] data = null;

		FileInputStream fis;
		try {
			fis = new FileInputStream(TEST_DATA_SHEET_PATH);

			book = WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);
			int row = sheet.getLastRowNum();
			int col = sheet.getRow(0).getLastCellNum();
			data = new Object[row][col];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

}
