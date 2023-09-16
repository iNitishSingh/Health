package pageClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponents {

	WebDriver driver;
//	WebDriverWait wait = new WebDriverWait(driver, Duration.)
	

	@FindBy(id = "user-name")
	WebElement Username;

	@FindBy(css = "button[type='submit']")
	WebElement SubmitButton;

	@FindBy(css = ".btn")
	WebElement ConfirmButton;

	@FindBy(css = "ng-select[role='listbox']")
	WebElement LocationDropDown;

	@FindBy(css = ".btn")
	WebElement SignInButton;

	@FindBy(css = "input[placeholder='Password']")
	WebElement UserPassword;

	@FindBy(css = ".ng-option-label")
	List<WebElement> Branches;

	@FindBy(xpath = "//span[contains(text(),'Health')]")
	WebElement HealtProducts;

	@FindBy(css = ".swal2-confirm")
	WebElement Alert;

	public LoginPage(WebDriver driver) throws FileNotFoundException, IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void Login()  throws FileNotFoundException, IOException, InterruptedException {

		
		driver.get(prop.getProperty("url"));
		Username.sendKeys(prop.getProperty("username"));
		SubmitButton.click();
//		wait.until(ExpectedConditions.visibilityOf(ConfirmButton));

		Thread.sleep(3000);

		UserPassword.sendKeys(prop.getProperty("password"));
		SignInButton.click();

		Thread.sleep(15000);

		LocationDropDown.click();
		Actions act = new Actions(driver);
		act.sendKeys(LocationDropDown, prop.getProperty("location")).build().perform();
		// Streams to search all branches parallely
		Branches.stream().filter(branch -> branch.getText().equalsIgnoreCase(prop.getProperty("location"))).findFirst()
				.orElse(null).click();
		ConfirmButton.click();
		Thread.sleep(15000);
		HealtProducts.click();
		Thread.sleep(3000);
	}

}
