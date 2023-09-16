package testCases;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Ho {

	public static void main(String[] args) throws InvalidFormatException, IOException {
	
		Map<String, String> NumberToMonth = new HashMap();
		NumberToMonth.put("01", "Jan");
		NumberToMonth.put("02", "Feb");
		NumberToMonth.put("03", "Mar");
		NumberToMonth.put("04", "Apr");
		NumberToMonth.put("05", "May");
		NumberToMonth.put("06", "Jun");
		NumberToMonth.put("07", "Jul");
		NumberToMonth.put("08", "Aug");
		NumberToMonth.put("09", "Sep");
		NumberToMonth.put("10", "Oct");
		NumberToMonth.put("11", "Nov");
		NumberToMonth.put("12", "Dec");
		
	
	}
}