package pageClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class QuotePage extends AbstractComponents {
	WebDriver driver;

	@FindBy(xpath = "//button[contains(text(),'Quick Quote')]")
	WebElement QuickQuote;

	@FindBy(css = "ng-select[formcontrolname='pincode']")
	WebElement PincodeDropdown;

	@FindBy(id = "af1ade2f6c08-0")
	WebElement Pincode;

	@FindBy(id = "new_business")
	WebElement NewBusiness;

	@FindBy(id = "Plan")
	WebElement Plan;

	@FindBy(css = "div[role='option']")
	List<WebElement> PlanTypes;

	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	WebElement ProceedButton;

	@FindBy(id = "not_included")
	WebElement SelfIncludedCheckbox;

	@FindBy(id = "mem_is_aig_emp")
	WebElement SelfIsAnEmpCheckbox;

	@FindBy(id = "checkbox4")
	WebElement Spouse;

	@FindBy(id = "checkbox5")
	WebElement Father;

	@FindBy(id = "checkbox6")
	WebElement Mother;

	@FindBy(id = "checkbox7")
	WebElement Son;

	@FindBy(id = "checkbox8")
	WebElement Daughter;

	@FindBy(id = "checkbox9")
	WebElement FatherInLaw;

	@FindBy(id = "checkbox10")
	WebElement MotherInLaw;

	@FindBy(css = "input[formcontrolname='spouse_dob']")
	List<WebElement> SpouseDOB;

	public QuotePage(WebDriver driver) throws FileNotFoundException, IOException {
		super(driver);
		this.driver = driver;
	}

	public void quoteGeneration(HashMap<String, String> quotedata) throws InterruptedException {
		QuickQuote.click();
		Thread.sleep(5000);
		Actions act = new Actions(driver);
		Pincode.sendKeys(quotedata.get("Pincode"));
		
		
	}

}
