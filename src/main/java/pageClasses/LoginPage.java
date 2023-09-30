package pageClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	@FindBy(xpath = "//mr-4 //span")
	WebElement HealtProducts;
	
	@FindBy(xpath = "(//div[@class='card-text-wrapper'] //span[contains(text(),'Health')])[1]")
	WebElement HealthTemplate;
	
	@FindBy(css = ".swal2-confirm")
	WebElement Alert;
WebDriverWait wait;
	public LoginPage(WebDriver driver) throws FileNotFoundException, IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		Duration timeout = Duration.ofSeconds(15);
		wait = new WebDriverWait(driver, timeout);
	}

	public void Login() throws FileNotFoundException, IOException, InterruptedException {

		driver.get(prop.getProperty("url"));
		Username.sendKeys(prop.getProperty("username"));
		SubmitButton.click();
//		wait.until(ExpectedConditions.visibilityOf(ConfirmButton));

		wait.until(ExpectedConditions.visibilityOf(UserPassword));
		UserPassword.sendKeys(prop.getProperty("password"));
		SignInButton.click();
		wait.until(ExpectedConditions.visibilityOf(LocationDropDown));
		LocationDropDown.click();
		Actions act = new Actions(driver);
		act.sendKeys(LocationDropDown, prop.getProperty("location")).build().perform();
		// Streams to search all branches parallely
		Branches.stream().filter(branch -> branch.getText().equalsIgnoreCase(prop.getProperty("location"))).findFirst()
				.orElse(null).click();
		wait.until(ExpectedConditions.elementToBeClickable(ConfirmButton));
		ConfirmButton.click();
		Thread.sleep(10000);
//		wait.until(ExpectedConditions.visibilityOf(HealthTemplate));
		HealthTemplate.click();
	}

}
