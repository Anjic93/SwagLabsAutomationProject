package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SortMenuTest extends BaseTest {

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
    public void verifyThatSortDropDownMenuWorks() {
        productsPage.clickOnSortDropDown();
        Assert.assertTrue(productsPage.dropDownOption2.isDisplayed());
    }

    @Test(priority = 20)
    public void userCanChangeSortOptionFromDropDownMenu() {
        productsPage.clickOnSortDropDown();
        Assert.assertTrue(productsPage.dropDownOption2.isDisplayed());

        for(int i=0; i<3; i++) { //loop for iteration through drop down menu
            productsPage.selectDropDownOption();
            String selectedOption = productsPage.getSelectedOption();  //to get only selected option
            System.out.println("Selected option: " + selectedOption);
            Assert.assertEquals(selectedOption, productsPage.getSortMenuName()); //to verify that sort order is by selected option
            System.out.println("Sort Menu title: " + productsPage.getSortMenuName());
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
