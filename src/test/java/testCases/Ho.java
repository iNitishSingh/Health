package testCases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Ho {

	public static void main(String[] args) throws InvalidFormatException, IOException {
	
		System.out.println(System.getProperty("user.dir") + "\\src\\main\\java\\testScreenshots\\");
//		FileUtils.copyFile(new File("C:\\Users\\pshinde6\\Desktop\\test111.txt"), new File(System.getProperty("user.dir") + "\\src\\main\\java\\testScreenshots1\\demo.txt"));
	}
}