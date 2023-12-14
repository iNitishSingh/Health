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

public class PaymentPage extends AbstractComponents {
	WebDriver driver;
	WebDriverWait wait;
	HashMap<String, String> quotedata;

	@FindBy(css = "div[class='col-md-6 col-lg-4 ng-tns-c32-1']")
	private WebElement PaymentTemplate;

	@FindBy(css = "div[class='theme-card text-center pb-xl-0 ng-tns-c32-1']")
	private List<WebElement> AllPaymentMethods;

	@FindBy(xpath = "//button[contains(text(),'Validate KYC')]")
	private WebElement ValidateKYCButton;

	@FindBy(css = "label[for='acceptTnC']")
	private WebElement AcceptConditionsCheckbox;

	public PaymentPage(WebDriver driver, HashMap<String, String> quotedata) throws FileNotFoundException, IOException {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		Duration timeout = Duration.ofSeconds(20);
		wait = new WebDriverWait(driver, timeout);
		this.quotedata = quotedata;
	}

	public void makePayment(String paymentType) throws FileNotFoundException, IOException, InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(PaymentTemplate));
		wait.until(ExpectedConditions.visibilityOfAllElements(AllPaymentMethods));
//		TO take control to payment options
		findElement("Online");
		if (paymentType.equals("Cash")) {

			WebElement paymentElement = AllPaymentMethods.stream().filter(method -> method.getText().contains("Cash"))
					.findFirst().orElse(null);
			cashPayment(paymentElement);

		} else if (paymentType.equals("Cheque")) {
			WebElement paymentElement = AllPaymentMethods.stream().filter(method -> method.getText().contains("Cheque"))
					.findFirst().orElse(null);
			chequePayment(paymentElement);

		} else {
			WebElement paymentElement = AllPaymentMethods.stream().filter(method -> method.getText().contains("Online"))
					.findFirst().orElse(null);
			onlinePayment(paymentElement, "Netbanking");
		}

	}

	private void cashPayment(WebElement paymentElement) {
	}

	private void chequePayment(WebElement paymentElement) {
	}

	private void onlinePayment(WebElement paymentElement, String onlinepaymenttype)
			throws FileNotFoundException, IOException, InterruptedException {
		paymentElement.findElement(By.tagName("svg")).click();
		findElement("Online Payment");

		if (onlinepaymenttype.equalsIgnoreCase("Netbanking")) {
			WebElement targerElement = driver.findElement(
					By.xpath("//ng-select[@formcontrolname='onlinePaymentMode'] //span[@class='ng-arrow-wrapper']"));
			selectDropdownValue(targerElement, onlinepaymenttype);
		} else {
//			For Wallet
			WebElement targerElement = driver
					.findElement(By.xpath("//ng-select[@formcontrolname='onlinePaymentMode'] //div //span"));
			selectDropdownValue(targerElement, onlinepaymenttype);
		}

		findElement("Validate KYC");
		AcceptConditionsCheckbox.click();
		ValidateKYCButton.click();
		KycPage kycpageobj = new KycPage(driver, quotedata);
		kycpageobj.kycDetails(quotedata.get("KYCType"));
//		wait.until(ExpectedConditions.wi)
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
