package pageClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.bytebuddy.asm.Advice.AllArguments;

public class ConvertToProposalPage extends AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;
	HashMap<String, String> quotedata;
	AbstractComponents abstractobj;

	public ConvertToProposalPage(WebDriver driver, HashMap<String, String> quotedata)
			throws FileNotFoundException, IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		Duration timeout = Duration.ofSeconds(20);
		wait = new WebDriverWait(driver, timeout);
		this.quotedata = quotedata;
	}

	@FindBy(xpath = "//span[contains(text(),'DETAILS')]")
	private List<WebElement> InsuredDataTemplates;

	@FindBy(xpath = "//ng-select[@id='title'] //input")
	private WebElement CustomerTitle;

	@FindBy(css = "label[for='fmale']")
	private WebElement GenderFmaale;

	@FindBy(css = "label[for='other']")
	private WebElement GenderOther;

	@FindBy(css = "ng-select[formcontrolname='mstatus']")
	private WebElement MaritalStatusDropdown;

	@FindBy(xpath = "//div //div[@role='option']")
	private List<WebElement> MaritalStatusValues;

	@FindBy(css = "label[for='indian']")
	private WebElement NationalityIndian;

	@FindBy(css = "label[for='nt_other']")
	private WebElement NationalityOther;

	@FindBy(css = "input[placeholder='Address']")
	private WebElement Address;

	@FindBy(css = "input[placeholder='Nearest landmark']")
	private WebElement Landmark;

	@FindBy(css = "input[placeholder='Village, District']")
	private WebElement District;

	@FindBy(xpath = "//button[contains(text(),'Next')]")
	private WebElement NextButton;

	@FindBy(css = "input[placeholder='First name']")
	private List<WebElement> FirstNames;

	@FindBy(css = "input[placeholder='Middle name']")
	private List<WebElement> MiddleNames;

	@FindBy(css = "input[placeholder='Last name']")
	private List<WebElement> LastNames;

	@FindBy(xpath = "//div[contains(@class,'ng-pristine')]")
	private List<WebElement> ListOfMemberDetailsTemplate;

	@FindBy(xpath = "//div //div //span[contains(@class,'ng-option-label')]")
	private List<WebElement> TitleList;

	@FindBy(xpath = "//div //div //div[@class='row']")
	private List<WebElement> BMITemplates;

	@FindBy(css = "div[role='option']")
	private List<WebElement> AllDropdownBMITeplate;

	@FindBy(css = "label[for*='reltn']")
	private List<WebElement> MedicalDetailsInsuredList;

	@FindBy(css = "label[for*='pre_existing_illness']")
	private WebElement MedicalDetailsDiseaseCheckbox;

	@FindBy(css = "div[class*='flex-box-100 pb-15 ng-star-inserted']")
	private List<WebElement> AllSevenQuestionsTemplate;

	@FindBy(css = "div[class='ng-star-inserted'] div[class*='nominee-detail']")
	private WebElement NomineeTemplate;

	@FindBy(className = "ng-arrow-wrapper")
	private WebElement NomineeDropdown;

	@FindBy(css = "span[class*='ng-option-label ng-star-inserted']")
	private List<WebElement> NomineeDropdownValues;

	@FindBy(id = "nominee_contribution0")
	private WebElement NomineeContribution;

	@FindBy(css = "div[class='radio-container'] div[class*='theme-radio']")
	List<WebElement> NomineeGenders;

	@FindBy(id = "pan_no")
	private WebElement PanNumber;
	@FindBy(id = "annual_income")
	private WebElement AnnualIncome;

	@FindBy(id = "landline_no")
	private WebElement LandlineNumber;

	@FindBy(id = "gst_no")
	private WebElement GSTNumber;

	@FindBy(css = "svg[class*='tick-img']")
	private List<WebElement> Tick;

	@FindBy(xpath = "//button[contains(text(),'Review Proposal')]")
	private WebElement ReviewProposalButton;

	private void insuredTemplateDataMethod(WebElement insuredTemplate, String insured_Title, String insured_FirstName,
			String insured_MiddleName, String insured_LastName) throws InterruptedException {
		wait.until(
				ExpectedConditions.elementToBeClickable(insuredTemplate.findElement(By.className("ng-arrow-wrapper"))));
		insuredTemplate.findElement(By.className("ng-arrow-wrapper")).click();
		TitleList.stream().filter(title -> title.getText().equalsIgnoreCase(insured_Title)).findFirst().orElse(null)
				.click();
		insuredTemplate.findElement(By.cssSelector("input[placeholder='First name']")).sendKeys(insured_FirstName);
		insuredTemplate.findElement(By.cssSelector("input[placeholder='Middle name']")).sendKeys(insured_MiddleName);
		insuredTemplate.findElement(By.cssSelector("input[placeholder='Last name']")).sendKeys(insured_LastName);
	}

	private void proposalDetailCustomerDetailsPage1() throws InterruptedException, FileNotFoundException, IOException {
		wait.until(ExpectedConditions.visibilityOfAllElements(InsuredDataTemplates));
		Thread.sleep(2000);
		InsuredDataTemplates.stream().filter(details -> details.getText().contains("CUSTOMER DETAILS")).findFirst()
				.orElse(null).click();
		MaritalStatusDropdown.click();
		MaritalStatusValues.stream().filter(k -> k.getText().equalsIgnoreCase(quotedata.get("Cust_MaritalStatus")))
				.findFirst().orElse(null).click();
		if (quotedata.get("Cust_Nationality").equalsIgnoreCase("Indian")) {
			NationalityIndian.click();
		} else {
			NationalityOther.click();
		}
		abstractobj = new AbstractComponents(driver);
		// abstractobj.findElement("Next");
		Address.sendKeys(quotedata.get("Cust_Address"));
		Landmark.sendKeys(quotedata.get("Cust_Landmark"));
		District.sendKeys(quotedata.get("Cust_District"));
		NextButton.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(ListOfMemberDetailsTemplate));
	}

	private void proposalDetailMemberDetailsPage2InsuredTemplate(WebElement insured, String insured_Title,
			String insured_FirstName, String insured_MiddleName, String insured_LastName, String insured_Gender,
			String insured_Relationship) throws InterruptedException {
		insuredTemplateDataMethod(insured, insured_Title, insured_FirstName, insured_MiddleName, insured_LastName);
		List<WebElement> genders = driver.findElements(By.xpath("//div[@class='radio-container'] //div"));
		if (insured_Relationship.equalsIgnoreCase("Spouse")) {
			genders.stream().filter(gender -> gender.getText().equalsIgnoreCase(insured_Gender)).findFirst()
					.orElse(null).click();
		}
	}

	private void proposalDetailMemberDetailsPage2() throws InterruptedException {
		if (quotedata.get("IsSpouseIncluded").equalsIgnoreCase("Yes")) {
			WebElement Spouse = ListOfMemberDetailsTemplate.stream().filter(name -> name.getText().contains("SPOUSE"))
					.findFirst().orElse(null);
			proposalDetailMemberDetailsPage2InsuredTemplate(Spouse, quotedata.get("Spouse_Title"),
					quotedata.get("Spouse_FirstName"), quotedata.get("Spouse_MiddleName"),
					quotedata.get("Spouse_LastName"), quotedata.get("Spouse_Gender"), "Spouse");
			/*
			 * insuredTemplateDataMethod(spouse, quotedata.get("Spouse_Title"),
			 * quotedata.get("Spouse_FirstName"), quotedata.get("Spouse_MiddleName"),
			 * quotedata.get("Spouse_LastName")); List<WebElement> genders =
			 * driver.findElements(By.xpath("//div[@class='radio-container'] //div"));
			 * genders.stream().filter(gender ->
			 * gender.getText().equalsIgnoreCase(quotedata.get("Spouse_Gender")))
			 * .findFirst().orElse(null).click();
			 */
		}

		if (quotedata.get("IsFatherIncluded").equalsIgnoreCase("Yes")) {
			// abstractobj.findElement("father");
			WebElement Father = ListOfMemberDetailsTemplate.stream().filter(name -> name.getText().contains("FATHER"))
					.findFirst().orElse(null);
			proposalDetailMemberDetailsPage2InsuredTemplate(Father, quotedata.get("Father_Title"),
					quotedata.get("Father_FirstName"), quotedata.get("Father_MiddleName"),
					quotedata.get("Father_LastName"), quotedata.get("Father_Gender"), "Father");

		}
		if (quotedata.get("IsMotherIncluded").equalsIgnoreCase("Yes")) {
//		scrollPageDown();

			WebElement Mother = ListOfMemberDetailsTemplate.stream().filter(name -> name.getText().contains("MOTHER"))
					.findFirst().orElse(null);
			proposalDetailMemberDetailsPage2InsuredTemplate(Mother, quotedata.get("Mother_Title"),
					quotedata.get("Mother_FirstName"), quotedata.get("Mother_MiddleName"),
					quotedata.get("Mother_LastName"), quotedata.get("Mother_Gender"), "Mother");
		}

		if (quotedata.get("IsSonIncluded").equalsIgnoreCase("Yes")) {
//		scrollPageDown();
			WebElement Son = ListOfMemberDetailsTemplate.stream().filter(name -> name.getText().contains("SON1"))
					.findFirst().orElse(null);
			proposalDetailMemberDetailsPage2InsuredTemplate(Son, quotedata.get("Son_Title"),
					quotedata.get("Son_FirstName"), quotedata.get("Son_MiddleName"), quotedata.get("Son_LastName"),
					quotedata.get("Son_Gender"), "Son");

		}

		if (quotedata.get("IsDaughterIncluded").equalsIgnoreCase("Yes")) {
//		scrollPageDown();
			// abstractobj.findElement("Daughter");
			WebElement Daughter = ListOfMemberDetailsTemplate.stream()
					.filter(name -> name.getText().contains("DAUGHTER1")).findFirst().orElse(null);
			proposalDetailMemberDetailsPage2InsuredTemplate(Daughter, quotedata.get("Daughter_Title"),
					quotedata.get("Daughter_FirstName"), quotedata.get("Daughter_MiddleName"),
					quotedata.get("Daughter_LastName"), quotedata.get("Daughter_Gender"), "Daughter");

		}
		if (quotedata.get("IsFatherInLawIncluded").equalsIgnoreCase("Yes")) {
			// abstractobj.findElement("FatherInLaw");
//		scrollPageDown();
			WebElement FatherInLaw = ListOfMemberDetailsTemplate.stream()
					.filter(name -> name.getText().contains("FatherInLaw")).findFirst().orElse(null);
			proposalDetailMemberDetailsPage2InsuredTemplate(FatherInLaw, quotedata.get("FatherInLaw_Title"),
					quotedata.get("FatherInLaw_FirstName"), quotedata.get("FatherInLaw_MiddleName"),
					quotedata.get("FatherInLaw_LastName"), quotedata.get("FatherInLaw_Gender"), "FatherInLaw");

		}
		if (quotedata.get("IsMotherInLawIncluded").equalsIgnoreCase("Yes")) {
			// //abstractobj.findElement("MotherInLaw");
//		scrollPageDown();
			WebElement MotherInLaw = ListOfMemberDetailsTemplate.stream()
					.filter(name -> name.getText().contains("MotherInLaw")).findFirst().orElse(null);
			proposalDetailMemberDetailsPage2InsuredTemplate(MotherInLaw, quotedata.get("MotherInLaw_Title"),
					quotedata.get("MotherInLaw_FirstName"), quotedata.get("MotherInLaw_MiddleName"),
					quotedata.get("MotherInLaw_LastName"), quotedata.get("MotherInLaw_Gender"), "MotherInLaw");

		}
		scrollPageToEnd();
		NextButton.click();
	}

	private void proposalDetailBMIDetailsPage3InsuredTemplate(WebElement InsuredTemplate, String Insured_Height,
			String Insured_Weight) {
		InsuredTemplate.findElement(By.cssSelector("ng-select[formcontrolname*='height']")).click();
		AllDropdownBMITeplate.stream().filter(height -> height.getText().contains("CM")).findFirst().orElse(null)
				.click();
		InsuredTemplate.findElement(By.cssSelector("input[placeholder='CM']")).sendKeys(Insured_Height);
		InsuredTemplate.findElement(By.cssSelector("input[placeholder='Weight (kg)']")).sendKeys(Insured_Weight);
	}

	private void proposalDetailBMIDetailsPage3() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfAllElements(BMITemplates));
		if (quotedata.get("IsSelfIncluded").equalsIgnoreCase("Yes")) {
			// abstractobj.findElement("self");
			WebElement SelfTemplate = BMITemplates.stream().filter(template -> template.getText().contains("SELF"))
					.findFirst().orElse(null);
			proposalDetailBMIDetailsPage3InsuredTemplate(SelfTemplate, quotedata.get("Self_Height"),
					quotedata.get("Self_Weight"));
			/*
			 * SelfTemplate.findElement(By.cssSelector(
			 * "ng-select[formcontrolname*='height']")).click();
			 * AllDropdownBMITeplate.stream().filter(height ->
			 * height.getText().contains("CM")).findFirst().orElse(null) .click();
			 * SelfTemplate.findElement(By.cssSelector("input[placeholder='CM']")).sendKeys(
			 * quotedata.get("Self_Height"));
			 * SelfTemplate.findElement(By.cssSelector("input[placeholder='Weight (kg)']"))
			 * .sendKeys(quotedata.get("Self_Weight"));
			 */
		}

		if (quotedata.get("IsSpouseIncluded").equalsIgnoreCase("Yes")) {
			// abstractobj.findElement("Spouse");
			WebElement SpouseTemplate = BMITemplates.stream().filter(template -> template.getText().contains("SPOUSE"))
					.findFirst().orElse(null);
			proposalDetailBMIDetailsPage3InsuredTemplate(SpouseTemplate, quotedata.get("Spouse_Height"),
					quotedata.get("Spouse_Weight"));
		}

		if (quotedata.get("IsFatherIncluded").equalsIgnoreCase("Yes")) {
			// abstractobj.findElement("Father");
			WebElement FatherTemplate = BMITemplates.stream().filter(template -> template.getText().contains("FATHER"))
					.findFirst().orElse(null);
			proposalDetailBMIDetailsPage3InsuredTemplate(FatherTemplate, quotedata.get("Father_Height"),
					quotedata.get("Father_Weight"));
		}
		if (quotedata.get("IsMotherIncluded").equalsIgnoreCase("Yes")) {
			// abstractobj.findElement("Mother");
			scrollPageDown();
			WebElement MotherTemplate = BMITemplates.stream().filter(template -> template.getText().contains("MOTHER"))
					.findFirst().orElse(null);
			proposalDetailBMIDetailsPage3InsuredTemplate(MotherTemplate, quotedata.get("Mother_Height"),
					quotedata.get("Mother_Weight"));

		}
		if (quotedata.get("IsSonIncluded").equalsIgnoreCase("Yes")) {
			scrollPageDown();
			// abstractobj.findElement("Son");
			WebElement SonTemplate = BMITemplates.stream().filter(template -> template.getText().contains("SON1"))
					.findFirst().orElse(null);
			proposalDetailBMIDetailsPage3InsuredTemplate(SonTemplate, quotedata.get("Son_Height"),
					quotedata.get("Son_Weight"));

		}
		if (quotedata.get("IsDaughterIncluded").equalsIgnoreCase("Yes")) {
			scrollPageDown();
			// abstractobj.findElement("Daughter");
			WebElement DaughterTemplate = BMITemplates.stream()
					.filter(template -> template.getText().contains("DAUGHTER1")).findFirst().orElse(null);
			proposalDetailBMIDetailsPage3InsuredTemplate(DaughterTemplate, quotedata.get("Daughter_Height"),
					quotedata.get("Daughter_Weight"));

		}
		if (quotedata.get("IsFatherInLawIncluded").equalsIgnoreCase("Yes")) {
			scrollPageDown();
			// abstractobj.findElement("FatherInLaw");
			WebElement FatherInLawTemplate = BMITemplates.stream()
					.filter(template -> template.getText().contains("FATHERINLAW")).findFirst().orElse(null);
			proposalDetailBMIDetailsPage3InsuredTemplate(FatherInLawTemplate, quotedata.get("FatherInLaw_Height"),
					quotedata.get("FatherInLaw_Weight"));
		}
		if (quotedata.get("IsMotherInLawIncluded").equalsIgnoreCase("Yes")) {
			scrollPageDown();
			// //abstractobj.findElement("MotherInLaw");
			WebElement MotherInLawTemplate = BMITemplates.stream()
					.filter(template -> template.getText().contains("MOTHERINLAW")).findFirst().orElse(null);
			proposalDetailBMIDetailsPage3InsuredTemplate(MotherInLawTemplate, quotedata.get("MotherInLaw_Height"),
					quotedata.get("MotherInLaw_Weight"));

		}

		NextButton.click();
	}

	private void proposalDetailMedicalDetailsPage4() {
		wait.until(ExpectedConditions.visibilityOfAllElements(MedicalDetailsInsuredList));
		if (quotedata.get("Self_AnyMedicalDisclosureFlag").equalsIgnoreCase("Yes")) {
			MedicalDetailsInsuredList.stream().filter(insured -> insured.getText().contains("Self")).findFirst()
					.orElse(null).click();
			MedicalDetailsDiseaseCheckbox.click();

			if (quotedata.get("M1Q11") != null) {

				AllSevenQuestionsTemplate.stream().filter(template -> template.getText().contains("1")).findFirst()
						.orElse(null).findElement(By.cssSelector("div[class='toggle-btn']")).click();
				
//				Each Question block from q1 to q4 
				//div[class*='flex-box-100 pb-15 ng-star-inserted'] div[class*='row']
			}
		}

		NextButton.click();
	}

	private void proposalDetailLifestyleDetailsPage5() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class*='col-12']"))));
		NextButton.click();
	}

	private void proposalDetailNomineeDetailsPage6() throws FileNotFoundException, IOException, InterruptedException {
		NomineeTemplate.findElement(By.cssSelector("input[placeholder='Name']")).sendKeys(quotedata.get("NomineeName"));
		abstractobj = new AbstractComponents(driver);
		abstractobj.dateSelection(NomineeTemplate, quotedata.get("NomineeDOB"));
		wait.until(ExpectedConditions.elementToBeClickable(NomineeDropdown));

		NomineeDropdown.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(NomineeDropdownValues));
		NomineeDropdownValues.stream()
				.filter(relationship -> relationship.getText().contains(quotedata.get("NomineeRelationship")))
				.findFirst().orElseGet(null).click();
		NomineeContribution.sendKeys(quotedata.get("NomineeContribution"));
		NomineeGenders.stream().filter(gender -> gender.getText().contains(quotedata.get("NomineeGender"))).findFirst()
				.orElse(null).click();
		NextButton.click();
	}

	private void proposalDetailAdditionalDetailsPage7() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(PanNumber));
		PanNumber.sendKeys(quotedata.get("PANNumber"));
		AnnualIncome.sendKeys(quotedata.get("AnnualIncome"));
		LandlineNumber.sendKeys(quotedata.get("LandlineNumber"));
		GSTNumber.sendKeys(quotedata.get("GSTNumber"));

		NextButton.click();
		wait.until(ExpectedConditions.visibilityOfAllElements(InsuredDataTemplates));
		System.out.println(Tick.size());
		// abstractobj.findElement("Review");
		scrollPageToEnd();
		wait.until(ExpectedConditions.elementToBeClickable(ReviewProposalButton));
		ReviewProposalButton.click();
	}

	public void proposalDetail() throws FileNotFoundException, IOException, InterruptedException {
		proposalDetailCustomerDetailsPage1();
		proposalDetailMemberDetailsPage2();
		proposalDetailBMIDetailsPage3();
		proposalDetailMedicalDetailsPage4();
		proposalDetailLifestyleDetailsPage5();
		proposalDetailNomineeDetailsPage6();
		proposalDetailAdditionalDetailsPage7();
	}

}
