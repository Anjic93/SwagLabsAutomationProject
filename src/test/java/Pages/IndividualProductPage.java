package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Random;

public class IndividualProductPage extends BaseTest {

    public IndividualProductPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_details_name.large_size")
    public WebElement individualProductTitle;

    @FindBy(id = "back-to-products")
    public WebElement backToProductsButton;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory ")
    public List<WebElement> addToCartButtonInd;

    @FindBy(css = ".btn.btn_secondary.btn_small.btn_inventory")
    public WebElement removeButton;


    //-----------------------


    public String getIndProductTitle() {
        return individualProductTitle.getText();
    }

    public void clickOnAddToCartIndButton() {
        Random random = new Random();
        int randomNumber = random.nextInt(addToCartButtonInd.size());
        addToCartButtonInd.get(randomNumber).click();
    }

    public void checkRemoveButton() {
        if(removeButton.getText().contains("Remove")) {
            removeButton.isDisplayed();
        }

    }


}
