package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;

import java.io.IOException;


//During testing, a Maven project was created for the reason that it is easier for me to insert libraries
// via a POM file, in which it is also easier to follow the work and the elements are defined in only one place

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
        //I am testing on the Chrome browser, because according to the source,
        //Chrome is currently used by more than 50% of users in the world

        excelReader = new ExcelReader("Credentials.xlsx");
        //To avoid hardcoding I use DataDrivenTesting so that I can more easily change/add test data or
        //compare the obtained result with the expected result
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
