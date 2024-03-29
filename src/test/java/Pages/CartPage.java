package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {
    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")  //cart page title
    public WebElement cartTitle;

    @FindBy(className = "cart_item")  //list of added products in cart(displayed only when products are added)
    public List<WebElement> productsListInCart;

    @FindBy(className = "inventory_item_name")  //name of added product in cart
    public WebElement productNameInList;

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppButton;
    @FindBy(id = "checkout")
    public WebElement checkoutButton;

    //-------------------------

    public String getCartTitle() {
        return cartTitle.getText();
    }

    public String getProductNameInList() {
        return productNameInList.getText();
    }

    public void clickOnContinueShoppButton() {
        continueShoppButton.click();
    }

    public void clickOnCheckoutButton() {
        checkoutButton.click();
    }
}
