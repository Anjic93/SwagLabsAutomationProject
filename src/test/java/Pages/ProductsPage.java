package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class ProductsPage extends BaseTest {

    public ProductsPage() {
        PageFactory.initElements(driver, this);
    }


    @FindBy(className = "title")   //page title
    public WebElement productsTitle;
    @FindBy(id = "react-burger-menu-btn")  //burger menu
    public WebElement burgerButton;

    @FindBy(id = "logout_sidebar_link")  //logout button
    public WebElement logoutButton;
    @FindBy(className = "inventory_list")  //list with all products on page
    public WebElement productList;

    @FindBy(className = "inventory_item_name")  //clickable title of single product
    public List<WebElement> products;

    @FindBy(className = "shopping_cart_link")   //shopping cart icon
    public WebElement cartIcon;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory ")  //Add to cart button for every product
    public List<WebElement> addToCartButtonProd;

    @FindBy(className = "shopping_cart_badge")  //badge on cart icon with number of added products
                                                // (displayed only when there are products in cart)
    public WebElement cartBadge;
    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")  //single Remove button of added product
    public WebElement removeButton;

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")  //multiple Remove buttons for all added products
    public List<WebElement> removeButtonCount;

    @FindBy(className = "product_sort_container")  //drop down sort menu
    public WebElement sortDropDown;

    @FindBy(className = "active_option")  //drop down sort menu displayed name
    public WebElement sortMenuName;

    @FindBy(tagName = "option")
    public List<WebElement> dropDownOption;  //list of all options from sort drop down menu
    @FindBy(tagName = "option")
    public WebElement dropDownOption2;   //single option from sort drop down menu

    @FindBy(className = "social_twitter")
    public WebElement twitter;

    @FindBy(className = "social_facebook")
    public WebElement facebook;

    @FindBy(className = "social_linkedin")
    public WebElement linkedin;


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

  public void clickOnMultipleAddToCartProdButton() {   //for adding multiple products to the cart,
                                                                        // but in random order
       for (int i = 0; i < addToCartButtonProd.size(); i++) {
           Random random = new Random();
           int randomNumber = random.nextInt(addToCartButtonProd.size());
           addToCartButtonProd.get(randomNumber).click();
       }
   }


    public void assertRemoveButton() {    //to verify that remove button is displayed after adding product
        if (removeButton.getText().contains("Remove")) {
            removeButton.isDisplayed();
        }
    }

public void clickOnRemoveButton() {
    if (removeButton.getText().contains("Remove")) {
        removeButton.click();
    }
}


    public void verifyCartBadgeNumberOfAddedProducts() {  //to verify that number of added products is correctly
                                                        // displayed on cart badge(numbers should be same)

        String badgeText = cartBadge.getText();  //get number from the badge in String
        int badgeNumber = Integer.parseInt(badgeText);  //parse badge number from String to Integer
        System.out.println("Number of products in cart, on cart badge: " + badgeNumber);

        int addedProductsCount = removeButtonCount.size(); //number of added products by counting remove buttons
        System.out.println("Number of added products from Products page: " + addedProductsCount);

        if(badgeNumber == addedProductsCount) {  //to verify that number on the cart badge is equal to the
                                                                                // number of added products
            System.out.println("Number of added products is equal to cart badge number");
        } else {
            System.out.println("Number of added products is not equal to cart badge number");
        }
    }

    public void clickOnSortDropDown() {
    sortDropDown.click();
    }

    public String getSortMenuName() {
        return sortMenuName.getText();
    }

    public void selectDropDownOption() {
        Random random = new Random();
        int randomNumber2 = random.nextInt(dropDownOption.size());
        dropDownOption.get(randomNumber2).click();
            }

    public String getSelectedOption() {  //to get name of selected option from sort drop down menu
        Select dropdownMenu = new Select(sortDropDown);
        return dropdownMenu.getFirstSelectedOption().getText();
    }


    public void clickOnTwitter() {
    twitter.click();
    }

    public void clickOnFacebook() {
    facebook.click();
    }

    public void clickOnLinkedin() {
    linkedin.click();
    }

    }












