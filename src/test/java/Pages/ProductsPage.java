package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class ProductsPage extends BaseTest {

    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }


    @FindBy(className = "title")
    public WebElement productsTitle;
    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerButton;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutButton;
    @FindBy(className = "inventory_list")
    public WebElement productList;

    @FindBy(className = "inventory_item_name")
    public List<WebElement> products;

    @FindBy(className = "shopping_cart_link")
    public WebElement cartIcon;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory ")
    public List<WebElement> addToCartButtonProd;

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartBadge;
    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    public WebElement removeButton;


    //-------------------------

public String getTitle() {
    return productsTitle.getText();
}
    public void clickOnBurgerButton() {
        burgerButton.click();
    }
    public void clickOnLogoutButton() {
        logoutButton.click();
    }

public void clickOnCartIcon() {
    cartIcon.click();
}

public void addRandomProduct() {
    Random random = new Random();
    int randomNumber = random.nextInt(products.size());
    products.get(randomNumber).click();
}

public void clickOnAddToCartProdButton() {
    Random random = new Random();
    int randomNumber = random.nextInt(addToCartButtonProd.size());
    addToCartButtonProd.get(randomNumber).click();
}

  public void clickOnMultipleAddToCartProdButton() {
       for (int i = 0; i < addToCartButtonProd.size(); i++) {
           Random random = new Random();
           int randomNumber = random.nextInt(addToCartButtonProd.size());
           addToCartButtonProd.get(randomNumber).click();
       }
   }


    public void assertRemoveButton() {
        if (removeButton.getText().contains("Remove")) {
            removeButton.isDisplayed();
        }
    }

public void clickOnRemoveButton() {
    if (removeButton.getText().contains("Remove")) {
        removeButton.click();
    }
}
}







