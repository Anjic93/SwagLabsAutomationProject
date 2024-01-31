package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.IndividualProductPage;
import Pages.LoginPage;
import Pages.ProductsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddProductInCartFromIndividualProductPage extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        cartPage = new CartPage();
        individualProductPage = new IndividualProductPage();

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
    public void checkIfCartIsEmpty() {
        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertEquals(cartPage.getCartTitle(), "Your Cart");
        Assert.assertTrue(cartPage.productsListInCart.isEmpty());
        cartPage.clickOnContinueShoppButton();
    }
    @Test(priority = 20)
    public void userCanAddProductFromIndividualProductPage() {
        productsPage.addRandomProduct();
        String addedProduct = individualProductPage.getIndProductTitle();
        System.out.println(addedProduct);
        Assert.assertTrue(individualProductPage.backToProductsButton.isDisplayed());

        individualProductPage.clickOnAddToCartIndButton();
        individualProductPage.checkRemoveButton();
        Assert.assertTrue(productsPage.cartBadge.isDisplayed());
        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.productsListInCart.getFirst().isDisplayed());
        Assert.assertEquals(cartPage.getProductNameInList(), addedProduct);
    }




     /*  @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

   */
}