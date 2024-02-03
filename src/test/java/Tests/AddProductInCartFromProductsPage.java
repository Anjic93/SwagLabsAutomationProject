package Tests;

import Base.BaseTest;
import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductsPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddProductInCartFromProductsPage extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        cartPage = new CartPage();

        String validUsername = excelReader.getStringData("Credentials", 1, 0);
        String validPassword = excelReader.getStringData("Credentials", 1, 1);
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        loginPage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(productsPage.getTitle(), "Products");
        Assert.assertTrue(productsPage.productList.isDisplayed());
    }

    @Test(priority = 10)
    public void checkIfCartIsEmpty() {  //to verify that cart is empty before adding products
        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertEquals(cartPage.getCartTitle(), "Your Cart");
        Assert.assertTrue(cartPage.productsListInCart.isEmpty());

        cartPage.clickOnContinueShoppButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test(priority = 20)
    public void userCanAddSingleProductToCart() {
        productsPage.clickOnAddToCartProdButton();  //for adding one product to the cart
        Assert.assertTrue(productsPage.cartBadge.isDisplayed());
        productsPage.verifyCartBadgeNumberOfAddedProducts();

        productsPage.clickOnCartIcon();  //to verify that added product is in the cart
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.productsListInCart.getFirst().isDisplayed());

        cartPage.clickOnContinueShoppButton();  //to go back on products page and continue shopping
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test(priority = 25)
    public void userCanAddMultipleProductsToCart() {
        productsPage.clickOnMultipleAddToCartProdButton();  //for adding multiple products to the cart
        Assert.assertTrue(productsPage.cartBadge.isDisplayed());
        productsPage.verifyCartBadgeNumberOfAddedProducts();

        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.productsListInCart.getFirst().isDisplayed());

        cartPage.clickOnContinueShoppButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }



    @Test(priority = 30)
    public void userCanRemoveProductFromCartFromProducts() {
        productsPage.clickOnAddToCartProdButton();
        productsPage.assertRemoveButton();  //to verify that remove button is displayed after adding product
        productsPage.clickOnRemoveButton();
        isElementPresent(productsPage.cartBadge);  //to verify that cart badge is displayed after adding product

        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.productsListInCart.isEmpty());

        cartPage.clickOnContinueShoppButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }


    @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
