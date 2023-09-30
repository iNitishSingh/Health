package pageClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConvertToProposalPage extends AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;
	HashMap<String, String> quotedata;

	public ConvertToProposalPage(WebDriver driver, HashMap<String, String> quotedata)
			throws FileNotFoundException, IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		Duration timeout = Duration.ofSeconds(10);
		wait = new WebDriverWait(driver, timeout);
		this.quotedata = quotedata;
	}

	@FindBy(xpath = "//span[contains(text(),'DETAILS')]")
	List<WebElement> InsuredDataTemplates;

	@FindBy(xpath = "//ng-select[@id='title'] //input")
	WebElement CustomerTitle;

	@FindBy(css = "label[for='fmale']")
	WebElement GenderFmaale;

	@FindBy(css = "label[for='other']")
	WebElement GenderOther;

	@FindBy(css = "ng-select[formcontrolname='mstatus']")
	WebElement MaritalStatusDropdown;

	@FindBy(xpath = "//div //div[@role='option']")
	List<WebElement> MaritalStatusValues;

	@FindBy(css = "label[for='indian']")
	WebElement NationalityIndian;

	@FindBy(css = "label[for='nt_other']")
	WebElement NationalityOther;
	
	@FindBy(css = "input[placeholder='Address']")
	WebElement Address;
	
	@FindBy(css = "input[placeholder='Nearest landmark']")
	WebElement Landmark;
	
	@FindBy(css = "input[placeholder='Village, District']")
	WebElement District;
	
	@FindBy(xpath ="//button[contains(text(),'Next')]" )
	WebElement NextButton;
	
	@FindBy (css = "input[placeholder='First name']")
	List<WebElement> FirstNames;
	
	@FindBy (css = "input[placeholder='Middle name']")
	List<WebElement> MiddleNames;
	
	@FindBy (css = "input[placeholder='Last name']")
	List<WebElement> LastNames;

	public void proposalDetail() {

		wait.until(ExpectedConditions.visibilityOfAllElements(InsuredDataTemplates));
		InsuredDataTemplates.stream().filter(details -> details.getText().contains("CUSTOMER DETAILS")).findFirst()
				.orElse(null).click();
		MaritalStatusDropdown.click();
		MaritalStatusValues.stream().filter(k -> k.getText().equalsIgnoreCase(quotedata.get("Gender"))).findFirst()
				.orElse(null).click();
		if (quotedata.get("Nationality").equalsIgnoreCase("Indian")) {
			NationalityIndian.click();
		} else {
			NationalityOther.click();
		}
		

	}

	public void customerDetailsGrid() {
	}

	public void memberDetailsGrid() {
	}

	public void BMIDetailsGrid() {
	}

	public void medicalDetailsGrid() {
	}

	public void lifestyleDetailsGrid() {
	}

	public void nomineeDetailsGrid() {
	}

	public void additionalDetailsGrid() {
	}

}
