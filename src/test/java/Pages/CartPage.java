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

    @FindBy(className = "title")
    public WebElement cartTitle;

    @FindBy(className = "cart_item")
    public List<WebElement> productsListInCart;

    @FindBy(className = "inventory_item_name")
    public WebElement productNameInList;

    @FindBy(id = "continue-shopping")
    public WebElement continueShoppButton;

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

}
