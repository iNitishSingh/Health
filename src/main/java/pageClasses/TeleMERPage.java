package pageClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TeleMERPage extends AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;
	HashMap<String, String> quotedata;
	@FindBy(css = "ng-select[formcontrolname='connectvia'] span[class='ng-arrow-wrapper']")
	private WebElement CallOptionsDropdown;
	@FindBy(css = "ng-select[formcontrolname='language'] span[class='ng-arrow-wrapper']")
	private WebElement SelectLanguageDropdown;
	@FindBy(xpath = "//button[contains(text(),'Review')]")
	private WebElement ReviewButton;
	@FindBy(xpath = "//div[contains(text(),'BOOK TELE-MEDICAL APPOINTMENT')]")
	private WebElement teleMERComponent;

	public TeleMERPage(WebDriver driver) throws FileNotFoundException, IOException {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		Duration timeout = Duration.ofSeconds(20);
		wait = new WebDriverWait(driver, timeout);
	}

	public void selectTeleMERDetails(HashMap<String, String> quotedata) {
		this.quotedata = quotedata;
		wait.until(ExpectedConditions.visibilityOf(teleMERComponent));
//		Calloptions selection
		selectDropdownValue(CallOptionsDropdown, quotedata.get("TeleMERCallOptions"));
//		LanguageSelection
		selectDropdownValue(SelectLanguageDropdown, quotedata.get("TeleMERSelectLanguage"));
		ReviewButton.click();

	}

}
