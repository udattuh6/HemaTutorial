package ownProject.AppiumProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class TestData {
	@DataProvider(name="InputData")
	public Object[][] getData() throws FileNotFoundException, IOException {
		Object[][] obj = getDataFromExcel(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\inputData.xlsx");
		System.out.println(obj.length);
		System.out.println("data is: " + obj[1][0]);
		return obj;
	}
	
	public Object[][] getDataFromExcel(String fileName) throws FileNotFoundException, IOException {
		
		XSSFWorkbook xss = new XSSFWorkbook(new FileInputStream(fileName));
		XSSFSheet sheet = xss.getSheet("Sheet1");
		XSSFRow rows = sheet.getRow(sheet.getLastRowNum());
		Object[][] aL = new Object[sheet.getLastRowNum()+1][rows.getLastCellNum()] ;
		
		for(int i=1; i< sheet.getLastRowNum()-sheet.getFirstRowNum();i++) {
			
			Row r = sheet.getRow(i);
			for(int j=0; j< r.getLastCellNum(); j++) {
				
				aL[i-1][j]=(r.getCell(j).toString());
			
			}
		}
		return aL;
	}

}
