package pageClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductSelection extends AbstractComponents {

	WebDriver driver;
	@FindBy(css = ".theme-value")
	List<WebElement> AllProducts;

	@FindBy(css = ".swal2-confirm")
	WebElement Alert;
	WebDriverWait wait;

	public ProductSelection(WebDriver driver) throws FileNotFoundException, IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		Duration timeout = Duration.ofSeconds(10);
		wait = new WebDriverWait(driver, timeout);
	}

	public void selectProduct() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(Alert));
		Alert.click();
		Thread.sleep(2000);
		AllProducts.stream().filter(prod -> prod.getText().equalsIgnoreCase(prop.getProperty("product"))).findFirst()
				.orElse(null).click();

	}
}
