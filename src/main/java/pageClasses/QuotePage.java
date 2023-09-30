package pageClasses;

import java.awt.RenderingHints.Key;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.KeyManagerFactorySpi;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class QuotePage extends AbstractComponents {
	WebDriver driver;
	static HashMap<String, String> quotedata;

	WebDriverWait wait;

	public QuotePage(WebDriver driver) throws FileNotFoundException, IOException {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		Duration timeout = Duration.ofSeconds(15);
		wait = new WebDriverWait(driver, timeout);
	}

	@FindBy(xpath = "//button[contains(text(),'Quick Quote')]")
	WebElement QuickQuote;

	@FindBy(css = "ng-select[formcontrolname='pincode']")
	WebElement Pincode;

	@FindBy(css = "label[for='new_business']")
	WebElement NewBusiness;

	@FindBy(css = "label[for='portability']")
	WebElement Portability;

	@FindBy(xpath = "//ng-select[@id='Plan'] //span[@class='ng-arrow-wrapper']")
	WebElement Plan;

	@FindBy(css = "div[role='option']")
	List<WebElement> PlanTypes;

	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	WebElement ProceedButton;

	@FindBy(id = "not_included")
	WebElement SelfIncludedCheckbox;

	@FindBy(css = "label[for='mem_is_aig_emp']")
	WebElement SelfIsAnEmpCheckbox;

	@FindBy(xpath = "//label[@for='checkbox3']")
	WebElement Self;

	@FindBy(xpath = "//label[@for='checkbox4']")
	WebElement Spouse;

	@FindBy(xpath = "//label[@for='checkbox5']")
	WebElement Father;

	@FindBy(xpath = "//label[@for='checkbox6']")
	WebElement Mother;

	@FindBy(xpath = "//label[@for='checkbox7']")
	WebElement Son;

	@FindBy(xpath = "//label[@for='checkbox8']")
	WebElement Daughter;

	@FindBy(xpath = "//label[@for='checkbox9']")
	WebElement FatherInLaw;

	@FindBy(xpath = "//label[@for='checkbox10']")
	WebElement MotherInLaw;

	@FindBy(css = "input[formcontrolname='spouse_dob']")
	List<WebElement> SpouseDOB;

	@FindBy(xpath = "//select[@title='Select year'] //option[@class='ng-star-inserted']")
	static List<WebElement> ListOfAllYears;

	@FindBy(xpath = "//select[@title='Select month'] //option[@class='ng-star-inserted']")
	static List<WebElement> ListOfAllMonths;

	@FindBy(css = ".btn-light")
	static List<WebElement> ListOfAllDates;

	@FindBy(css = "div[role='option']")
	static List<WebElement> ListOfSumInsuredValues;

	@FindBy(css = "ng-select[formcontrolname='Tenure']")
	WebElement Tenure;

	@FindBy(className = "tata-card")
	static List<WebElement> VarientCards;

	@FindBy(xpath = "//button[contains(text(),'Share')]")
	WebElement ShareButton;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	WebElement SaveButton;

	@FindBy(css = "ng-select[name='title']")
	WebElement TitleField;

	@FindBy(css = "div[role='option']")
	List<WebElement> TitleList;

	@FindBy(id = "cus")
	WebElement CustFirstName;

	@FindBy(id = "midname")
	WebElement CustMiddleName;

	@FindBy(id = "lastname")
	WebElement CustLastName;

	@FindBy(id = "email")
	WebElement CustEmail;

	@FindBy(id = "phn")
	WebElement CustMobile;

	@FindBy(css = ".modal-body button")
	WebElement QuoteShareButton;

	@FindBy(css = "div[class='modal-body']")
	WebElement QuoteTemplate;

	@FindBy(xpath = "//div[@class='modal-body'] //button[@type='button']")
	WebElement ViewQuoteButton;

	@FindBy(xpath = "//button[contains(text(),'Convert To Proposal')]")
	WebElement ConvertToProposalButton;

	public void quoteGeneration(HashMap<String, String> quotedata)
			throws InterruptedException, FileNotFoundException, IOException {
		wait.until(ExpectedConditions.visibilityOf(QuickQuote));

		QuickQuote.click();

		wait.until(ExpectedConditions.visibilityOf(Pincode));
		this.quotedata = quotedata;
		Pincode.click();
		Actions action = new Actions(driver);
		action.sendKeys(quotedata.get("Pincode")).build().perform();

		Thread.sleep(1500);
		List<WebElement> allpin = driver.findElements(By.cssSelector(".ng-option"));
		for (WebElement pin : allpin) {
			if (pin.getText().contains(quotedata.get("Pincode"))) {
				pin.click();
				break;
			}
		}

		if (quotedata.get("BusinessType").equalsIgnoreCase("New Business")) {
			NewBusiness.click();
		} else {
			Portability.click();
		}

		Plan.click();

		driver.findElements(By.cssSelector("div[role='option']")).stream()
				.filter(plantype -> plantype.getText().equalsIgnoreCase(quotedata.get("PlanType"))).findFirst()
				.orElse(null).click();

		ProceedButton.click();
		wait.until(ExpectedConditions.visibilityOf(Self));

		if (quotedata.get("IsSelfIncluded").equalsIgnoreCase("Yes")) {
			SelfIncludedCheckbox.click();
		}
		if (quotedata.get("InMemberEmployee").equalsIgnoreCase("Yes")) {
			wait.until(ExpectedConditions.visibilityOf(SelfIsAnEmpCheckbox));
			SelfIsAnEmpCheckbox.click();
		}

		if (quotedata.get("RelationshipSelf").equalsIgnoreCase("Self")) {
			Self.click();

			List<WebElement> kk = driver
					.findElements(By.cssSelector("div[class*='dob-cards'] div[class*='theme-card']"));

			for (WebElement k : kk) {
				if (k.getText().contains("Primary")) {

					QuotePage.insuredDOBAndSI(k, "11/11/1994", quotedata.get("SISelf"));
				}
			}
		}
		if (quotedata.get("RelationshipSpouse").equalsIgnoreCase("Spouse")) {
			Spouse.click();

			List<WebElement> kk = driver
					.findElements(By.cssSelector("div[class*='dob-cards'] div[class*='theme-card']"));

			for (WebElement k : kk) {
				if (k.getText().contains("Spouse")) {

					QuotePage.insuredDOBAndSI(k, quotedata.get("DOBSpouse"), quotedata.get("SISpouse"));
				}
			}
		}
		if (quotedata.get("RelationshipFather").equalsIgnoreCase("Father")) {
			Father.click();

			List<WebElement> kk = driver
					.findElements(By.cssSelector("div[class*='dob-cards'] div[class*='theme-card']"));

			for (WebElement k : kk) {
				if (k.getText().contains("Father")) {

					QuotePage.insuredDOBAndSI(k, quotedata.get("DOBFather"), quotedata.get("SIFather"));
				}
			}
		}

		ProceedButton.click();

		wait.until(ExpectedConditions.visibilityOfAllElements(VarientCards));

		QuotePage quotepageobj = new QuotePage(driver);
		quotepageobj.varientSelection();
		quotepageobj.custContactDetails();

	}

	public void varientSelection() throws InterruptedException {
		for (WebElement varientCard : VarientCards) {

			if (varientCard.getText().contains(quotedata.get("Varient"))) {
				varientCard.click();
				ShareButton.click();
				Thread.sleep(1000);
			}

		}

	}

	public void custContactDetails() throws InterruptedException, WebDriverException, IOException {
		TitleField.click();
		TitleList.stream().filter(title -> title.getText().equalsIgnoreCase(quotedata.get("CustTitle"))).findFirst()
				.orElse(null).click();
		CustFirstName.sendKeys(quotedata.get("CustFirstName"));
		CustMiddleName.sendKeys(quotedata.get("CustMiddleName"));
		CustLastName.sendKeys(quotedata.get("CustLastName"));
		CustEmail.sendKeys(quotedata.get("CustEmailID"));
		CustMobile.sendKeys(quotedata.get("CustMobileNo"));
		QuoteShareButton.click();
		wait.until(ExpectedConditions.visibilityOf(ViewQuoteButton));
		driver.findElement(By.xpath("//div[@class='modal-body'] //button[@type='button']")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(ConvertToProposalButton));
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h3[contains(text(),'Review')]"))));
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,5000);");

		Actions act =new Actions(driver);
	String control=	Keys.chord(Keys.CONTROL,"F");
		act.sendKeys(control).build().perform();
		act.sendKeys("Proposal").build().perform();
		act.sendKeys(Keys.ESCAPE).build().perform();
		
		Thread.sleep(1500);
		ConvertToProposalButton.click();

	}

	public synchronized static void insuredDOBAndSI(WebElement insuredTemplate, String DOB, String SI)
			throws InterruptedException {

		String[] date = DOB.split("/");
		Map<String, String> NumberToMonth = new HashMap<String, String>();
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

		insuredTemplate.findElement(By.className("svg-sm")).click();

		ListOfAllYears.stream().filter(s -> s.getText().contains(date[2])).findFirst().orElse(null).click();

		ListOfAllMonths.stream().filter(s -> s.getText().contains(NumberToMonth.get(date[1]))).findFirst().orElse(null)
				.click();

		WebElement dataSelected = ListOfAllDates.stream().filter(s -> s.getText().contains(date[0])).findFirst()
				.orElse(null);

		dataSelected.click();

		insuredTemplate.findElement(By.cssSelector("ng-select[placeholder='Select']")).click();
		WebElement siSelected = ListOfSumInsuredValues.stream()
				.filter(s -> s.getText().trim().replace(",", "").contains(SI)).findFirst().orElse(null);
		siSelected.click();

	}
}
