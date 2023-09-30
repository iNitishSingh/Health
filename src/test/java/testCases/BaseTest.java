package testCases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import TestData.DataMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageClasses.ConvertToProposalPage;
import pageClasses.LoginPage;
import pageClasses.ProductSelection;
import pageClasses.QuotePage;

public class BaseTest {

	WebDriver driver;

	@Test(dataProvider = "testData")
	public void demo(HashMap<String, String> quotedata)
			throws FileNotFoundException, IOException, InterruptedException, InvalidFormatException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
			
		driver.manage().window().maximize();
		LoginPage login = new LoginPage(driver);
		login.Login();
		ProductSelection prodselection = new ProductSelection(driver);
		prodselection.selectProduct();
		QuotePage quotepageobj = new QuotePage(driver);
		quotepageobj.quoteGeneration(quotedata);
		 ConvertToProposalPage proposalpageobj = new ConvertToProposalPage(driver,quotedata);
		 proposalpageobj.proposalDetail();
	}

	@DataProvider
	public Object[][] testData() throws FileNotFoundException, IOException, InvalidFormatException {
		DataMapper mapper = new DataMapper();
		return mapper.getPagesData("TC001", "QuotePage");
	}

}
