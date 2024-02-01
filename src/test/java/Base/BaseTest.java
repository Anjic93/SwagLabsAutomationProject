package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {

    public static WebDriver driver;
    public ExcelReader excelReader;
    public LoginPage loginPage;
    public ProductsPage productsPage;
    public CartPage cartPage;
    public IndividualProductPage individualProductPage;
    public CheckoutPage checkoutPage;
    public PurchaseOverviewPage purchaseOverviewPage;
    public PurchaseCompletePage purchaseCompletePage;


    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        excelReader = new ExcelReader("Credentials.xlsx");
    }

    public boolean isElementPresent(WebElement el){
        try{
            el.isDisplayed();
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

}
