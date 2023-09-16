package testCases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageClasses.AbstractComponents;
import pageClasses.LoginPage;
import pageClasses.ProductSelection;

public class BaseTest {

	WebDriver driver;

	@Test(dataProvider = "testData")
	public void demo(HashMap<String, String> input) throws FileNotFoundException, IOException, InterruptedException, InvalidFormatException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(7000);
		LoginPage login = new LoginPage(driver);
		login.Login();
		ProductSelection prodselection = new ProductSelection(driver);
		prodselection.selectProduct();
		

	}

	@DataProvider(name = "testData")
	public void testData() throws FileNotFoundException, IOException, InvalidFormatException {
//		AbstractComponents abstractobj = new AbstractComponents(driver);
//		return abstractobj.getPagesData("TC001");

	}

}
