package pageClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductSelection extends AbstractComponents {

	WebDriver driver;
	@FindBy(css = ".theme-value")
	List<WebElement> AllProducts;

	@FindBy(css = ".swal2-confirm")
	WebElement Alert;

	public ProductSelection(WebDriver driver) throws FileNotFoundException, IOException {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectProduct() throws InterruptedException {
		Alert.click();
		AllProducts.stream().filter(prod -> prod.getText().equalsIgnoreCase(prop.getProperty("product"))).findFirst()
				.orElse(null).click();
		Thread.sleep(3000);

	}
}
