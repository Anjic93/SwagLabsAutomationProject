package Tests;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.ProductsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class FooterTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        loginPage = new LoginPage();
        productsPage = new ProductsPage();


        String validUsername = excelReader.getStringData("Credentials",1,0);
        String validPassword= excelReader.getStringData("Credentials",1,1);
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(productsPage.getTitle(), "Products");
        Assert.assertTrue(productsPage.productList.isDisplayed());
    }


    @Test(priority = 10)
    public void userCanClickOnTwitterIcon() {
        productsPage.clickOnTwitter();
        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles()); //new tab windows list
        driver.switchTo().window(listaTabova.get(1)); //to proceed on new tab window when social media opens
        Assert.assertEquals(driver.getCurrentUrl(), "https://twitter.com/saucelabs");
    }

    @Test(priority = 20)
    public void userCanClickOnFacebookIcon() {
        productsPage.clickOnFacebook();
        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/saucelabs");
    }

    @Test(priority = 30)
    public void userCanClickOnLinkedinIcon() {
        productsPage.clickOnLinkedin();
        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova.get(1));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/company/sauce-labs/");
    }


    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
