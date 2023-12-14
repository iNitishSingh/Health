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

public class KycPage extends AbstractComponents {
	WebDriver driver;
	WebDriverWait wait;
	HashMap<String, String> quotedata;
	@FindBy(css = "div[class='detail-container-modal modal-md']")
	private WebElement KYCTemplate;

	@FindBy(css = "label[for='byProducer']")
	private WebElement ProducerKYCCheckbox;

	@FindBy(css = "label[for='byCustomer']")
	private WebElement CustomerKYCCheckbox;

	@FindBy(xpath = "//button[contains(text(),'Submit')]")
	private WebElement SubmitButton;

	@FindBy(id = "main-rows")
	private WebElement KycPage;

	@FindBy(id = "pan_no")
	private WebElement PANNumberField;

	@FindBy(id = "pan_no_btn")
	private WebElement KYCSubmitButton;

	@FindBy(xpath = "//h5[contains(text(),'KYC Successful!')]")
	private WebElement KYCSuccessfullText;

	@FindBy(css = "div[class='arya-result-div']")
	private WebElement KYCResulePage;

	@FindBy(xpath = "//button[contains(text(),'PROCEED')]")
	private WebElement KYCProceedButton;

	public KycPage(WebDriver driver, HashMap<String, String> quotedata) throws FileNotFoundException, IOException {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		Duration timeout = Duration.ofSeconds(20);
		wait = new WebDriverWait(driver, timeout);
		this.quotedata = quotedata;
	}

	protected void kycDetails(String KYCType) {
		wait.until(ExpectedConditions.visibilityOf(KYCTemplate));
		if (KYCType.contains("Customer")) {
			customerKYC();
		} else {
			producerKYC();
		}
	}

	private void producerKYC() {
		ProducerKYCCheckbox.click();
		SubmitButton.click();
		wait.until(ExpectedConditions.visibilityOf(KycPage));
		PANNumberField.clear();
		PANNumberField.sendKeys(quotedata.get("KYCPAN"));
		KYCSubmitButton.click();
		wait.until(ExpectedConditions.visibilityOf(KYCResulePage));

		if (KYCResulePage.getText().contains("KYC Successful!")) {
			KYCProceedButton.click();
		} else {
			System.out.println("Could not verify KYC details due to invalid KYC details");
		}

	}

	private void customerKYC() {

	}
}
