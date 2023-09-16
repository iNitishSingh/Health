package pageClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AbstractComponents {

	WebDriver driver;
	Properties prop = new Properties();

	@FindBy(css = "select[title='Select year']")
	WebElement CalenderYear;

	@FindBy(css = "select[title='Select month']")
	WebElement CalenderMonth;

	@FindBy(className = "col-md-6.col-lg-4.col-xl-3.ng-star-inserted")
	List<WebElement> InsuredsTemplate;

	@FindBy(xpath = "//select[@title='Select year'] //option[@class='ng-star-inserted']")
	List<WebElement> ListOfAllYears;

	@FindBy(xpath = "//select[@title='Select month'] //option[@class='ng-star-inserted']")
	List<WebElement> ListOfAllMonths;

	@FindBy(css = ".btn-light")
	List<WebElement> ListOfAllDates;

	@FindBy(css = ".calendar-icon")
	WebElement CalendarIcon;

	@FindBy(css = "ng-select[placeholder='Select']")
	List<WebElement> SumInsuredIcon;

	@FindBy(css = "div[role='option']")
	List<WebElement> ListOfSumInsuredValues;

	/* Reusable Utility : Whenever required can be iterated from base test */
	public WebElement getDate(String Insuredname, String dateOfBirth) {
		String[] date = dateOfBirth.split("/");
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

		// Respective Member Canlender Icon Selection

		WebElement InsuredTemplate = InsuredsTemplate.stream().filter(i -> i
				.findElement(By.cssSelector(".form-field.col-md-12.complete label")).getText().contains(Insuredname))
				.findFirst().orElse(null);
		InsuredTemplate.findElement(By.cssSelector(".calendar-icon")).click();
		// DOB Selection
		ListOfAllYears.stream().filter(s -> s.getText().contains(date[2])).findFirst().orElse(null).click();

		ListOfAllMonths.stream().filter(s -> s.getText().contains(NumberToMonth.get(date[1]))).findFirst().orElse(null)
				.click();

		WebElement dataSelected = ListOfAllDates.stream().filter(s -> s.getText().contains(date[0])).findFirst()
				.orElse(null);

		return dataSelected;
	}

	public void getSumInsured(String Insuredname, String SI) {
		WebElement InsuredTemplate = InsuredsTemplate.stream().filter(i -> i
				.findElement(By.cssSelector(".form-field.col-md-12.complete label")).getText().contains(Insuredname))
				.findFirst().orElse(null);
		InsuredTemplate.findElement(By.cssSelector("ng-select[placeholder='Select']")).click();
		ListOfSumInsuredValues.stream().filter(s -> s.getText().contains(SI)).findFirst().orElse(null).click();
	}

	public AbstractComponents(WebDriver driver) throws FileNotFoundException, IOException {
		this.driver = driver;
		prop.load(new FileInputStream(
				new File(System.getProperty("user.dir") + "//src//main//java//TestData//global.properties")));
	}

	public void dateSelection() {
	}

	public Object[][] getPagesData(String TestcaseID, String sheetName) throws InvalidFormatException, IOException {

		HashMap<String, String> quoteeachrowdata = new HashMap<>();
		HashMap<String, String> proposaleachrowdata = new HashMap<>();

		HashMap<String, String> map = null;

		XSSFWorkbook workbook = new XSSFWorkbook(
				new File(System.getProperty("user.dir") + "//src//main//java//TestData//TestDataExcel.xlsx"));
		Iterator<Sheet> sheetIt = workbook.sheetIterator();

		while (sheetIt.hasNext()) {

			Sheet sheet = sheetIt.next();

			if (sheet.getSheetName() == sheetName) {

				Iterator<Row> rowIt = sheet.rowIterator();

				Row firstrow = rowIt.next();
				while (rowIt.hasNext()) {
					Row datarow = rowIt.next();
					int size = firstrow.getLastCellNum();

					if (datarow.getCell(0).getStringCellValue().equalsIgnoreCase(TestcaseID)
							&& sheet.getSheetName().equalsIgnoreCase("QuotePage")) {

						for (int i = 0; i < size; i++) {

							if ((datarow.getCell(i).getCellType() != CellType.STRING)) {
								DataFormatter formatnumerictostring = new DataFormatter();
								quoteeachrowdata.put(firstrow.getCell(i).getStringCellValue(),
										formatnumerictostring.formatCellValue(datarow.getCell(i)));

							}

							else {
								quoteeachrowdata.put(firstrow.getCell(i).getStringCellValue(),
										datarow.getCell(i).getStringCellValue());
							}
						}
						map = quoteeachrowdata;
					} else if (datarow.getCell(0).getStringCellValue().equalsIgnoreCase(TestcaseID)
							&& sheet.getSheetName().equalsIgnoreCase("ProposalPage")) {

						for (int i = 0; i < size; i++) {

							if ((datarow.getCell(i).getCellType() != CellType.STRING)) {
								DataFormatter formatnumerictostring = new DataFormatter();
								proposaleachrowdata.put(firstrow.getCell(i).getStringCellValue(),
										formatnumerictostring.formatCellValue(datarow.getCell(i)));

							}

							else {
								proposaleachrowdata.put(firstrow.getCell(i).getStringCellValue(),
										datarow.getCell(i).getStringCellValue());
							}
						}
						map = proposaleachrowdata;
					}

				}

			}

		}
		return new Object[][] { { map } };

	}

}
