package pageClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FinalizeProposalPage extends AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//h3[contains(text(),'REVIEW PROPOSAL')]")
	private	WebElement ReviewProposal;
	@FindBy(xpath = "//button[contains(text(),'Finalize Proposal')]")
	private		WebElement FinalizeProposalButton;
	@FindBy(xpath = "//h3[contains(text(),'FINALIZED PROPOSAL')]")
	private		WebElement FinalizeProposal;
	@FindBy (xpath = "//button[contains(text(),'Pay Now')]")
	private	WebElement PayNowButton;
	@FindBy(xpath = "//div[starts-with(text(),' PRU')]")
	private	WebElement ProposalNumber;
	
	public FinalizeProposalPage(WebDriver driver) throws FileNotFoundException, IOException {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		Duration timeout = Duration.ofSeconds(20);
		wait = new WebDriverWait(driver, timeout);
	}
	
	public void verifyProposalDetails() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(ReviewProposal));
		findElement("Finalize");
		FinalizeProposalButton.click();
		wait.until(ExpectedConditions.visibilityOf(FinalizeProposal));
		System.out.println(ProposalNumber.getText());
		findElement("Pay");
		PayNowButton.click();
		
		
	}

}
