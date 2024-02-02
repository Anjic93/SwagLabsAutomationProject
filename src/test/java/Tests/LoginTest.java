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

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        loginPage = new LoginPage();
        productsPage = new ProductsPage();

    }

    @Test(priority = 10)
    public void userCanLogIn() {
        String validUsername = excelReader.getStringData("Credentials",1,0);
        String validPassword= excelReader.getStringData("Credentials",1,1);
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(productsPage.getTitle(), "Products");
        Assert.assertTrue(productsPage.productList.isDisplayed());
    }

    @Test(priority = 20)
    public void userCanLogOut() {
        String validUsername = excelReader.getStringData("Credentials",1,0);
        String validPassword= excelReader.getStringData("Credentials",1,1);
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(productsPage.getTitle(), "Products");
        Assert.assertTrue(productsPage.productList.isDisplayed());
        productsPage.clickOnBurgerButton();
        productsPage.clickOnLogoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.loginButton.isDisplayed());
    }
    @Test(priority = 30)
    public void userCanNotLogInWithInvalidUsername() {
        for (int i = 1; i < 4; i++) {
            String invalidUsername = excelReader.getStringData("Credentials", i, 2);
            String validPassword= excelReader.getStringData("Credentials",1,1);
            loginPage.inputUsername(invalidUsername);
            loginPage.inputPassword(validPassword);
            loginPage.clickOnLoginButton();
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            Assert.assertTrue(loginPage.errorMessage.isDisplayed());
            Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
        }
    }

   @Test(priority = 40)
    public void userCanNotLogInWithInvalidPassword() {
       for (int i = 1; i < 4; i++) {
           String validUsername = excelReader.getStringData("Credentials", 1, 0);
           String invalidPassword = excelReader.getStringData("Credentials", i, 3);
           loginPage.inputUsername(validUsername);
           loginPage.inputPassword(invalidPassword);
           loginPage.clickOnLoginButton();
           Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
           Assert.assertTrue(loginPage.errorMessage.isDisplayed());
           Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
       }
   }

    @Test(priority = 50)
    public void userCanNotLogInWithInvalidUsernameAndPassword() {
        for (int i = 1; i < 4; i++) {
            String invalidUsername = excelReader.getStringData("Credentials", i, 2);
            String invalidPassword = excelReader.getStringData("Credentials", i, 3);
            loginPage.inputUsername(invalidUsername);
            loginPage.inputPassword(invalidPassword);
            loginPage.clickOnLoginButton();
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
            Assert.assertTrue(loginPage.errorMessage.isDisplayed());
            Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service");
        }
    }

    @Test(priority = 60)
    public void userCanNotLogInWithEmptyUsernameAndPasswordFields() {
        String invalidUsername = "";
        String invalidPassword = "";
        loginPage.inputUsername(invalidUsername);
        loginPage.inputPassword(invalidPassword);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }



    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}

