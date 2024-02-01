package Tests;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartCheckout extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        loginPage = new LoginPage();
        productsPage = new ProductsPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        purchaseOverviewPage = new PurchaseOverviewPage();
        purchaseCompletePage = new PurchaseCompletePage();


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
    public void userCanProceedToCheckout() {
        productsPage.clickOnAddToCartProdButton();
        Assert.assertTrue(productsPage.cartBadge.isDisplayed());
        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.productsListInCart.getFirst().isDisplayed());
        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(checkoutPage.getCheckoutPageTitle(), "Checkout: Your Information");
        Assert.assertTrue(checkoutPage.checkoutForm.isDisplayed());
    }

    @Test(priority = 20)
    public void userCanFinishPurchase() {
        productsPage.clickOnAddToCartProdButton();
        Assert.assertTrue(productsPage.cartBadge.isDisplayed());
        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.productsListInCart.getFirst().isDisplayed());

        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(checkoutPage.getCheckoutPageTitle(), "Checkout: Your Information");
        Assert.assertTrue(checkoutPage.checkoutForm.isDisplayed());

        checkoutPage.inputFirstName("Petar");
        checkoutPage.inputLastName("Petrovic");
        checkoutPage.inputPostalCode("11000");
        checkoutPage.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertEquals(purchaseOverviewPage.overviewPageTitleName(), "Checkout: Overview");
        Assert.assertTrue(purchaseOverviewPage.purchaseInfo.isDisplayed());

        purchaseOverviewPage.clickOnFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");
        Assert.assertEquals(purchaseCompletePage.completePageTitleName(), "Checkout: Complete!");
        Assert.assertTrue(purchaseCompletePage.thankYouMsg.isDisplayed());
        Assert.assertEquals(purchaseCompletePage.getThankYouMsg(), "Thank you for your order!");
        Assert.assertTrue(purchaseCompletePage.backHomeButton.isDisplayed());

        purchaseCompletePage.clickOnBackHomeButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

    }

    @Test(priority = 30)
    public void userCantFinishPurchaseWithEmptyForm() {
        productsPage.clickOnAddToCartProdButton();
        Assert.assertTrue(productsPage.cartBadge.isDisplayed());
        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.productsListInCart.getFirst().isDisplayed());

        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(checkoutPage.getCheckoutPageTitle(), "Checkout: Your Information");
        Assert.assertTrue(checkoutPage.checkoutForm.isDisplayed());

        checkoutPage.inputFirstName("");
        checkoutPage.inputLastName("");
        checkoutPage.inputPostalCode("");
        checkoutPage.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutPage.errorMessage.isDisplayed());
    }

    @Test(priority = 40)
    public void userCantFinishPurchaseWithEmptyFirstNameField() {
        productsPage.clickOnAddToCartProdButton();
        Assert.assertTrue(productsPage.cartBadge.isDisplayed());
        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.productsListInCart.getFirst().isDisplayed());

        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(checkoutPage.getCheckoutPageTitle(), "Checkout: Your Information");
        Assert.assertTrue(checkoutPage.checkoutForm.isDisplayed());

        checkoutPage.inputFirstName("");
        checkoutPage.inputLastName("Petrovic");
        checkoutPage.inputPostalCode("11000");
        checkoutPage.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutPage.errorMessage.isDisplayed());
    }

    @Test(priority = 50)
    public void userCantFinishPurchaseWithEmptyLastNameField() {
        productsPage.clickOnAddToCartProdButton();
        Assert.assertTrue(productsPage.cartBadge.isDisplayed());
        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.productsListInCart.getFirst().isDisplayed());

        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(checkoutPage.getCheckoutPageTitle(), "Checkout: Your Information");
        Assert.assertTrue(checkoutPage.checkoutForm.isDisplayed());

        checkoutPage.inputFirstName("Petar");
        checkoutPage.inputLastName("");
        checkoutPage.inputPostalCode("11000");
        checkoutPage.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutPage.errorMessage.isDisplayed());
    }

    @Test(priority = 50)
    public void userCantFinishPurchaseWithEmptyPostalCodeField() {
        productsPage.clickOnAddToCartProdButton();
        Assert.assertTrue(productsPage.cartBadge.isDisplayed());
        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.productsListInCart.getFirst().isDisplayed());

        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(checkoutPage.getCheckoutPageTitle(), "Checkout: Your Information");
        Assert.assertTrue(checkoutPage.checkoutForm.isDisplayed());

        checkoutPage.inputFirstName("Petar");
        checkoutPage.inputLastName("Petrovic");
        checkoutPage.inputPostalCode("");
        checkoutPage.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertTrue(checkoutPage.errorMessage.isDisplayed());
    }

    @Test(priority = 60)
    public void userCanCancelCheckout() {
        productsPage.clickOnAddToCartProdButton();
        Assert.assertTrue(productsPage.cartBadge.isDisplayed());
        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.productsListInCart.getFirst().isDisplayed());

        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(checkoutPage.getCheckoutPageTitle(), "Checkout: Your Information");
        Assert.assertTrue(checkoutPage.checkoutForm.isDisplayed());

        checkoutPage.clickOnCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.checkoutButton.isDisplayed());
    }

    @Test(priority = 70)
    public void userCanCancelPurchase() {
        productsPage.clickOnAddToCartProdButton();
        Assert.assertTrue(productsPage.cartBadge.isDisplayed());
        productsPage.clickOnCartIcon();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");
        Assert.assertTrue(cartPage.productsListInCart.getFirst().isDisplayed());

        cartPage.clickOnCheckoutButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-one.html");
        Assert.assertEquals(checkoutPage.getCheckoutPageTitle(), "Checkout: Your Information");
        Assert.assertTrue(checkoutPage.checkoutForm.isDisplayed());

        checkoutPage.inputFirstName("Petar");
        checkoutPage.inputLastName("Petrovic");
        checkoutPage.inputPostalCode("11000");
        checkoutPage.clickOnContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-step-two.html");
        Assert.assertEquals(purchaseOverviewPage.overviewPageTitleName(), "Checkout: Overview");
        Assert.assertTrue(purchaseOverviewPage.purchaseInfo.isDisplayed());

        purchaseOverviewPage.clickOnCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(productsPage.getTitle(), "Products");
        Assert.assertTrue(productsPage.productList.isDisplayed());
    }


       @AfterMethod
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
