package testCases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Ho {

	@Test
	public void test() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://qualitykiosk.com/");
		Actions action = new Actions(driver);
		Thread.sleep(15000);
		//action.keyDown(Keys.CONTROL).keyDown("f").build().perform();
		action.sendKeys(Keys.CONTROL,"f").perform();
		
		Thread.sleep(5000);
		
	}
}
