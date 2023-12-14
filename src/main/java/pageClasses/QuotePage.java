package pageClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
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
	HashMap<String, String> quotedata;
	Actions action;
	WebDriverWait wait;

	public QuotePage(WebDriver driver) throws FileNotFoundException, IOException {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		Duration timeout = Duration.ofSeconds(20);
		wait = new WebDriverWait(driver, timeout);
		action = new Actions(driver);
	}

	@FindBy(xpath = "//button[contains(text(),'Quick Quote')]")
	private WebElement QuickQuote;

	@FindBy(css = "ng-select[formcontrolname='pincode']")
	private WebElement Pincode;

	@FindBy(css = "label[for='new_business']")
	private WebElement NewBusiness;

	@FindBy(css = "label[for='portability']")
	private WebElement Portability;

	@FindBy(xpath = "//ng-select[@id='Plan'] //span[@class='ng-arrow-wrapper']")
	private WebElement Plan;

	@FindBy(css = "div[role='option']")
	private List<WebElement> PlanTypes;

	@FindBy(xpath = "//button[contains(text(),'Proceed')]")
	private WebElement ProceedButton;

	@FindBy(id = "not_included")
	private WebElement SelfIncludedCheckbox;

	@FindBy(css = "label[for='mem_is_aig_emp']")
	private WebElement SelfIsAnEmpCheckbox;

	@FindBy(xpath = "//label[@for='checkbox3']")
	private WebElement Self;

	@FindBy(xpath = "//label[@for='checkbox4']")
	private WebElement Spouse;

	@FindBy(xpath = "//label[@for='checkbox5']")
	private WebElement Father;

	@FindBy(xpath = "//label[@for='checkbox6']")
	private WebElement Mother;

	@FindBy(xpath = "//label[@for='checkbox7']")
	private WebElement Son;

	@FindBy(xpath = "//label[@for='checkbox8']")
	private WebElement Daughter;

	@FindBy(xpath = "//label[@for='checkbox9']")
	private WebElement FatherInLaw;

	@FindBy(xpath = "//label[@for='checkbox10']")
	private WebElement MotherInLaw;

	@FindBy(css = "div[class*=\"row question flex-box-100 ng-star-inserted\"] div[class=\"toggle-btn\"]")
	private WebElement NRIQ2;

	@FindBy(css = "input[formcontrolname='Father_dob']")
	private List<WebElement> FatherDOB;

	@FindBy(xpath = "//select[@title='Select year'] //option[@class='ng-star-inserted']")
	static List<WebElement> ListOfAllYears;

	@FindBy(xpath = "//select[@title='Select month'] //option[@class='ng-star-inserted']")
	static List<WebElement> ListOfAllMonths;

	@FindBy(css = ".btn-light")
	static List<WebElement> ListOfAllDates;

	@FindBy(css = "div[role='option']")
	static List<WebElement> ListOfSumInsuredValues;

	@FindBy(css = "ng-select[formcontrolname='Tenure']")
	private WebElement Tenure;

	@FindBy(className = "tata-card")
	static List<WebElement> VarientCards;

	@FindBy(xpath = "//button[contains(text(),'Share')]")
	private WebElement ShareButton;

	@FindBy(xpath = "//button[contains(text(),'Save')]")
	private WebElement SaveButton;

	@FindBy(css = "ng-select[name='title']")
	private WebElement TitleField;

	@FindBy(css = "div[role='option']")
	private List<WebElement> TitleList;

	@FindBy(id = "cus")
	private WebElement CustFirstName;

	@FindBy(id = "midname")
	private WebElement CustMiddleName;

	@FindBy(id = "lastname")
	private WebElement CustLastName;

	@FindBy(id = "email")
	private WebElement CustEmail;

	@FindBy(id = "phn")
	private WebElement CustMobile;

	@FindBy(css = ".modal-body button")
	private WebElement QuoteShareButton;

	@FindBy(css = "div[class='modal-body']")
	private WebElement QuoteTemplate;

	@FindBy(xpath = "//div[@class='modal-body'] //button[@type='button']")
	private WebElement ViewQuoteButton;

	@FindBy(css = "div[class='card-content pb-0']")
	private WebElement ReviewQuotePage;

	@FindBy(xpath = "//button[contains(text(),'Convert To Proposal')]")
	private WebElement ConvertToProposalButton;

	@FindBy(css = "div[class*='form-field radio-field col-md-6 col-lg-4 col-xl-3']  input[aria-autocomplete*='list']")
	private WebElement TenureDropdown;
	@FindBy(css = "div[class*='ng-option ng-star-inserted ng-option-marked']")
	private WebElement TenureDropdownValue;

	@FindBy(xpath = "//button[contains(text(),'Calculate')]")
	private WebElement CalculateButton;

	public void quoteGeneration(HashMap<String, String> quotedata)
			throws InterruptedException, FileNotFoundException, IOException {
		this.quotedata = quotedata;
		quoteGenerationPlanDetailsPage();
		quoteGenerationFamilyMembersPage();
		varientSelection();
		custContactDetails();
	}

	private void quoteGenerationPlanDetailsPage() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(QuickQuote));
		QuickQuote.click();
		wait.until(ExpectedConditions.visibilityOf(Pincode));
//		this.quotedata = quotedata;
		Pincode.click();
		action.sendKeys(quotedata.get("Pincode")).build().perform();
		Thread.sleep(1000);
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
	}

	private void quoteGenerationFamilyMembersPage() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(Self));

		if (quotedata.get("IsSelfNotIncluded").equalsIgnoreCase("Yes")) {
			SelfIncludedCheckbox.click();
		}
		if (quotedata.get("InMemberEmployee").equalsIgnoreCase("Yes")) {
			wait.until(ExpectedConditions.visibilityOf(SelfIsAnEmpCheckbox));
			SelfIsAnEmpCheckbox.click();
		}

		if (quotedata.get("RelationshipSelf").equalsIgnoreCase("Self")) {
			wait.until(ExpectedConditions.visibilityOf(Self));
			wait.until(ExpectedConditions.elementToBeClickable(Self));
			quoteGenerationInsuredDetails(Self, "Primary", quotedata.get("DOBSelf"), quotedata.get("SISelf"));

		}
		if (quotedata.get("RelationshipSpouse").equalsIgnoreCase("Spouse")) {

			quoteGenerationInsuredDetails(Spouse, "Spouse", quotedata.get("DOBSpouse"), quotedata.get("SISpouse"));

		}
		if (quotedata.get("RelationshipFather").equalsIgnoreCase("Father")) {
			quoteGenerationInsuredDetails(Father, "Father", quotedata.get("DOBFather"), quotedata.get("SIFather"));

		}
		if (quotedata.get("RelationshipMother").equalsIgnoreCase("Mother")) {
			quoteGenerationInsuredDetails(Mother, "Mother", quotedata.get("DOBMother"), quotedata.get("SIMother"));

		}
		if (quotedata.get("RelationshipSon").equalsIgnoreCase("Son1")) {
			quoteGenerationInsuredDetails(Son, "Son", quotedata.get("DOBSon"), quotedata.get("SISon"));

		}
		if (quotedata.get("RelationshipDaughter").equalsIgnoreCase("Daughter1")) {
			quoteGenerationInsuredDetails(Daughter, "Daughter", quotedata.get("DOBDaughter"),
					quotedata.get("SIDaughter"));
		}
		if (quotedata.get("RelationshipFatherInLaw").equalsIgnoreCase("FatherInLaw")) {
			quoteGenerationInsuredDetails(FatherInLaw, "FatherInLaw", quotedata.get("DOBFatherInLaw"),
					quotedata.get("SIFatherInLaw"));
		}
		if (quotedata.get("RelationshipMotherInLaw").equalsIgnoreCase("MotherInLaw")) {
			quoteGenerationInsuredDetails(MotherInLaw, "MotherInLaw", quotedata.get("DOBMotherInLaw"),
					quotedata.get("SIMotherInLaw"));

		}
		scrollPageToEnd();
		wait.until(ExpectedConditions.elementToBeClickable(ProceedButton));
		ProceedButton.click();
	}

	private void quoteGenerationInsuredDetails(WebElement insured, String relationshipInsured, String DOBInsured,
			String SIInsured) throws InterruptedException {
		scrollPageUp();
		wait.until(ExpectedConditions.visibilityOf(insured));
		Thread.sleep(500);
		insured.click();
		List<WebElement> kk = driver.findElements(By.cssSelector("div[class*='dob-cards'] div[class*='theme-card']"));
		for (WebElement k : kk) {
			if (k.getText().contains(relationshipInsured)) {

				insuredDOBAndSI(k, DOBInsured, SIInsured);
			}
		}

	}

	private void varientSelection() throws InterruptedException {
		if (quotedata.get("Tenure").contains("1")) {
			wait.until(ExpectedConditions.visibilityOfAllElements(VarientCards));
			for (WebElement varientCard : VarientCards) {
				if (varientCard.getText().contains("Medicare Premier")) {
					varientCard.click();
					ShareButton.click();
				}
			}
		} else {
			wait.until(ExpectedConditions.visibilityOfAllElements(VarientCards));
			TenureDropdown.click();
			action.sendKeys(quotedata.get("Tenure")).build().perform();
			action.sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(1000);
			scrollPageToEnd();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(CalculateButton));
			CalculateButton.click();
			Thread.sleep(1000);
			wait.until(ExpectedConditions.visibilityOfAllElements(VarientCards));
			scrollPageUp();
			Thread.sleep(1000);
			for (WebElement varientCard : VarientCards) {
				if (varientCard.getText().contains("Medicare Premier")) {
					varientCard.click();
					scrollPageToEnd();
					Thread.sleep(1000);
					nriQuestionsSelection();
					CalculateButton.click();
					scrollPageToEnd();
					wait.until(ExpectedConditions.elementToBeClickable(ShareButton));
					ShareButton.click();
				}
			}
		}
	}

	private void nriQuestionsSelection() {
		if (quotedata.get("NRIQ2").equalsIgnoreCase("Yes")) {
			scrollPageToEnd();
			NRIQ2.click();
		}
	}

	private void custContactDetails() throws InterruptedException, WebDriverException, IOException {
		TitleField.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(TitleList));
		TitleList.stream().filter(title -> title.getText().equalsIgnoreCase(quotedata.get("CustTitle"))).findFirst()
				.orElse(null).click();
		CustFirstName.sendKeys(quotedata.get("CustFirstName"));
		CustMiddleName.sendKeys(quotedata.get("CustMiddleName"));
		CustLastName.sendKeys(quotedata.get("CustLastName"));
		CustEmail.sendKeys(quotedata.get("CustEmailID"));
		CustMobile.sendKeys(quotedata.get("CustMobileNo"));
		QuoteShareButton.click();
		wait.until(ExpectedConditions.visibilityOf(ViewQuoteButton));
		wait.until(ExpectedConditions.elementToBeClickable(ViewQuoteButton));
		ViewQuoteButton.click();
		wait.until(ExpectedConditions.visibilityOf(ReviewQuotePage));
		scrollPageToEnd();
		wait.until(ExpectedConditions.elementToBeClickable(ConvertToProposalButton));
		Thread.sleep(1000);
		ConvertToProposalButton.click();

	}

	private synchronized void insuredDOBAndSI(WebElement insuredTemplate, String DOB, String SI)
			throws InterruptedException {
		WebElement localInsuredTemplate = insuredTemplate;
		String localDOB = DOB;
		String localSI = SI;

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

		if (siSelected == null) {
			refreshPage();
			insuredDOBAndSI(localInsuredTemplate, localDOB, localSI);
		}

		siSelected.click();

	}

}
