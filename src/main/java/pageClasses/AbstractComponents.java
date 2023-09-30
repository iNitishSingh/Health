package pageClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AbstractComponents {

	WebDriver driver;
	Properties prop = new Properties();

	public AbstractComponents(WebDriver driver) throws FileNotFoundException, IOException {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		prop.load(new FileInputStream(
				new File(System.getProperty("user.dir") + "\\src\\main\\java\\TestData\\global.properties")));
	}

	public void pageScreenshot(String testcasename) {

		try {
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE),
					new File(System.getProperty("user.dir") + "\\src\\main\\java\\testScreenshots\\" + testcasename));
		} catch (WebDriverException e) {
			System.out.println("Could not find driver");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}

	}
	
	public void elementScreenshotS(String testcasename, WebElement element) throws WebDriverException, IOException {
		
		File file = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir") + "\\src\\main\\java\\testScreenshots1\\demo.png"));
		
	}

	

}
